package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.isky.Base64Image;
import com.ruoyi.common.isky.CheckMobile;
import com.ruoyi.common.isky.IskyUtil;
import com.ruoyi.common.isky.QrcodeUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ThumbUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.isky.domain.*;
import com.ruoyi.isky.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/mxph"})
public class MxpHController extends BaseController {
    private String prefix = "isky/mxp";

    @Autowired
    private IFigureLoginService loginService;

    @Autowired
    private IFigureTypeService typeService;

    @Autowired
    private IFigureImagesService imagesService;

    @Autowired
    private IFigureOrderService orderService;

    @Autowired
    private IFigureOrderImageService orderImageService;

    @Autowired
    private IFigureImagePropertyService propertyService;

    @Autowired
    private IFigureQrcodeService figureQrcodeService;

    @GetMapping({""})
    public String login() {
        return this.prefix + "/login";
    }


    @GetMapping({"/index"})
    public String login_fh() {
        return this.prefix + "/login_fh";
    }

    @GetMapping({"/select"})
    public String select(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login_fh();
        }
        FigureType figureType = new FigureType();
        figureType.setTypeId("1");
        List typeList = this.typeService.selectFigureTypeList(figureType);
        mmap.put("typeList", typeList);
        return this.prefix + "/select";
    }


    @GetMapping({"/picture"})
    public String picture(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();

        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login_fh();
        }
        return this.prefix + "/picture";
    }

    @GetMapping({"/order"})
    public String order(HttpServletRequest request, String orderId, ModelMap mmap) {
        HttpSession session = request.getSession();

        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login_fh();
        }
        FigureLogin login = this.loginService.selectFigureLoginById(Integer.valueOf(Integer.parseInt(u_s_e_id)));

        FigureOrder order = this.orderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        String qrcodePath = QrcodeUtil.createQC(orderId);
        order.setTaobao(login.getTaobao());
        order.setTelephone(login.getTelephone());
        order.setUsername(login.getUsername());
        mmap.put("dto", order);
        mmap.put("qrcode", qrcodePath);
        return this.prefix + "/order";
    }


    @GetMapping({"/editPic"})
    public String editPic(HttpServletRequest request, String imageId, String suffix ,String tpid, ModelMap mmap) {
        boolean isMoblie = IskyUtil.JudgeIsMoblie(request);
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login_fh();
        }

        FigureType figureType = typeService.selectFigureTypeById(Integer.parseInt(tpid));
        if (figureType.getIsFull().equals("1")) {
            mmap.put("width", 300);
            mmap.put("height", 206);
            mmap.put("imageId", imageId);
            mmap.put("isMoblie", Boolean.valueOf(isMoblie));
            mmap.put("fileName", imageId + "."+suffix);
            mmap.put("imageUrl", "../profile/upload/zzoom/" + imageId + "."+suffix);
        } else {
            //不是满版
            FigureImages images = new FigureImages();
            images.setTpId(tpid);
            List imagesList = this.imagesService.selectFigureImagesList(images);
            images = (FigureImages) imagesList.get(0);

            FigureImageProperty property = new FigureImageProperty();
            property.setImageId(images.getId());
            List propertyList = this.propertyService.selectFigureImagePropertyList(property);
            property = (FigureImageProperty) propertyList.get(0);
            String width = property.getZoomWidth();
            String height = property.getZoomHeight();
            mmap.put("textXsite", images.getTextXsite());
            mmap.put("textYsite", images.getTextYsite());
            mmap.put("width", Integer.valueOf(Integer.parseInt(width) + 3));
            mmap.put("height", Integer.valueOf(Integer.parseInt(height) + 2));
            mmap.put("imageId", imageId);
            mmap.put("isMoblie", Boolean.valueOf(isMoblie));
            mmap.put("fileName", imageId + "."+suffix);
            mmap.put("imageUrl", "../profile/upload/zzoom/" + imageId + "."+suffix);

        }
        return this.prefix + "/editPic";
    }

    @Log(title = "图片编辑", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadBlob"})
    @ResponseBody
    public AjaxResult uploadBlob(HttpServletRequest request, String mytext, String fileName, String file, String xsite, String ysite, String angle, String scale, String typeid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileDir = sdf.format(new Date());
        String path = Global.getUploadPath() + "zedit/";
        String rsPath = Global.getUploadPath() + "zrs/";

        String imgBase64 = request.getParameter("file");
        String imgBase64Blob = imgBase64.split(",")[1];
        String filepath = path + fileName;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }


        FigureType figureType = this.typeService.selectFigureTypeById(Integer.valueOf(Integer.parseInt(typeid)));

        String isfull = figureType.getIsFull();
        //如果不是满版的
        if (isfull.equals("0")) {
            Base64Image.GenerateImage(imgBase64Blob, filepath);
            String imageTypeId = figureType.getTypeId();
            FigureImages images = new FigureImages();
            images.setTpId(typeid);
            List imagesList = this.imagesService.selectFigureImagesList(images);
            images = (FigureImages) imagesList.get(0);
            FigureImageProperty property = new FigureImageProperty();
            property.setImageId(images.getId());
            List propertyList = this.propertyService.selectFigureImagePropertyList(property);
            property = (FigureImageProperty) propertyList.get(0);
            String isround = property.getIsround();

            int x = Integer.parseInt(property.getZoomXsite());
            int y = Integer.parseInt(property.getZoomYsite());

            float zoom_width = Float.parseFloat(property.getZoomWidth());
            float zoom_height = Float.parseFloat(property.getZoomHeight());

            float pxsite = 0.0F;
            float pysite = 0.0F;
            if (imageTypeId.equals("1")) {
                pxsite = x - zoom_width / 2.0F - 1.0F;
                pysite = y - zoom_height / 2.0F - 1.0F;
            } else {
                pxsite = x - zoom_width / 2.0F - 1.0F;
                pysite = y - zoom_height / 2.0F - 1.0F;
            }

            String mbpic = Global.getUploadPath() + "template/" + images.getImageZoomUrl();

            String successUrl = ThumbUtil.syntesis((int) pxsite, (int) pysite, mbpic, filepath, rsPath, fileName);

            if (isround.equals("1")) {
                ThumbUtil.syntesis(0, 0, successUrl, mbpic, rsPath, fileName);
            }

//        if (!StringUtils.isEmpty(mytext)) {
//            String imgUlr = Global.getUploadPath() + "template/" + images.getImageUrl();
//            int width = IskyUtil.getImgWidth(new File(imgUlr));
//
//            int tx = Integer.parseInt(images.getTextXsite());
//            int ty = Integer.parseInt(images.getTextYsite());
//            float zoom = 600.0F / width;
//            tx = (int)(zoom * tx);
//            ty = (int)(zoom * ty);
//
//            ImgWriteText.exportImg(rsPath + fileName, rsPath + fileName, mytext, tx, ty, 20);
//        }

            xsite = xsite.split("\\.")[0];
            ysite = ysite.split("\\.")[0];
            Thread thread = new Thread(new editRunblePK(fileName, Integer.parseInt(xsite), Integer.parseInt(ysite), property, images, mytext, angle, scale, imageTypeId));
            thread.start();
        } else {
            Base64Image.GenerateImage(imgBase64Blob, rsPath + fileName);
            Thread thread = new Thread(new editFullRunble(fileName, Integer.parseInt(xsite), Integer.parseInt(ysite), mytext, angle, scale));
            thread.start();
        }
        return success();
    }


    class editFullRunble implements Runnable {
        private String fileName;
        private int x;
        private int y;
        private String mytext;
        private String angle;
        private String scale;

        public editFullRunble(String fileName, int x, int y, String mytext, String angle, String scale) {
            this.fileName = fileName;
            this.x = x;
            this.y = y;
            this.mytext = mytext;
            this.angle = angle;
            this.scale = scale;
        }

        @Override
        public void run() {
            String rsPath = Global.getUploadPath() + "rs/";
            String zoomPath = Global.getUploadPath() + "zoom/";
            String zzoomPath = Global.getUploadPath() + "zzoom/";
            String editPath = Global.getUploadPath() + "edit/";
            String aeditPath = Global.getUploadPath() + "aedit/";
            String originaPath = Global.getUploadPath() + "origina/";


//            Float ymsfbl = 1.8F;
            Float ymsfbl = 1F;
            int width = 1795;
            int height = 1228;
            int property_zoom_width = 300; //模板要合成空白区域宽度(缩放后)
            int property_zoom_height = 205;//模板要合成空白区域高度(缩放后)

            Map originaProperty = null;
            try {
                originaProperty = IskyUtil.printImageTags(new File(originaPath + this.fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * 操作页面图片的宽度与高度
             */
            Map zoomProperty = null;
            try {
                zoomProperty = IskyUtil.printImageTags(new File(zzoomPath + this.fileName)); //縮放圖片的屬性
            } catch (Exception e) {
                e.printStackTrace();
            }
            int zoomPicWidth = ((Integer) zoomProperty.get("width")).intValue();//缩放图片的宽度
            int zoomPicHeight = ((Integer) zoomProperty.get("height")).intValue();//缩放图片的高度
            /**
             * 原图的宽高
             */
            int origina_width = ((Integer) originaProperty.get("width")).intValue();
            int origina_height = ((Integer) originaProperty.get("height")).intValue();
            /**
             * 旋转角度
             */
            Integer intAngle = Integer.parseInt(this.angle);
            //原图的地址
            String czPicPath = originaPath + this.fileName;
            /**
             * 如果有旋转角度，需要先旋转，旋转原图
             */
            boolean isW = true;
            if (intAngle.intValue() % 360 != 0) {
                ThumbUtil.rotate(originaPath + this.fileName, origina_width, origina_height, intAngle, editPath, this.fileName);
                czPicPath = editPath + this.fileName;
                if ((Math.abs(intAngle % 360) == 90) || (Math.abs(intAngle % 360) % 360 == 270)) {
                    isW = false;
                }
            }

            float realZoom = Float.parseFloat(this.scale);
            ThumbUtil.zoomPic(this.fileName, czPicPath, realZoom, editPath);
            /**
             * 獲取縮放圖与原图的比例
             */
            Map sfhPicProperty = null;
            try {
                sfhPicProperty = IskyUtil.printImageTags(new File(editPath + this.fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sfhPic_width = ((Integer) sfhPicProperty.get("width")).intValue();
            int sfhPic_height = ((Integer) sfhPicProperty.get("height")).intValue();
            /**
             * 計算缩放后图片与页面操作的图片之间的比例
             */
            float sfhzoom = (float) sfhPic_width / zoomPicWidth;
            if (!isW) {
                sfhzoom = (float) sfhPic_width / zoomPicHeight;
            }

            /**
             * 根据真实比例计算裁切位置
             */
            float sfhX = 1F * this.x * sfhzoom / realZoom;
            float sfhY = 1F * this.y * sfhzoom / realZoom;
            int cutWidth = (int) (sfhzoom * property_zoom_width / realZoom / ymsfbl);
            int cutHeight = (int) (sfhzoom * property_zoom_height / realZoom / ymsfbl);
            /**
             * 裁切的寬高
             *
             */
            ThumbUtil.cutAsPic(this.fileName, editPath + this.fileName, (int) sfhX, (int) sfhY, cutWidth, cutHeight, editPath);
            ThumbUtil.zoomGdccPic(this.fileName, editPath + this.fileName, width - 1, height - 1, rsPath);


        }
    }


    class editFullRunbleV implements Runnable {
        private String fileName;
        private int x;
        private int y;
        private String mytext;
        private String angle;
        private String scale;

        public editFullRunbleV(String fileName, int x, int y, String mytext, String angle, String scale) {
            this.fileName = fileName;
            this.x = x;
            this.y = y;
            this.mytext = mytext;
            this.angle = angle;
            this.scale = scale;
        }

        @Override
        public void run() {
            String rsPath = Global.getUploadPath() + "rs/";
            String zoomPath = Global.getUploadPath() + "zoom/";
            String zzoomPath = Global.getUploadPath() + "zzoom/";
            String editPath = Global.getUploadPath() + "edit/";
            String aeditPath = Global.getUploadPath() + "aedit/";
            String originaPath = Global.getUploadPath() + "origina/";


//            float ymsfbl=4F;

            float ymsfbl = 1F;
            int width = 1228;
            int height = 1795;
            int property_zoom_width = 300; //模板要合成空白区域宽度(缩放后)
            int property_zoom_height = 438;//模板要合成空白区域高度(缩放后)

            Map originaProperty = null;
            try {
                originaProperty = IskyUtil.printImageTags(new File(originaPath + this.fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * 操作页面图片的宽度与高度
             */
            Map zoomProperty = null;
            try {
                zoomProperty = IskyUtil.printImageTags(new File(zzoomPath + this.fileName)); //縮放圖片的屬性
            } catch (Exception e) {
                e.printStackTrace();
            }
            int zoomPicWidth = ((Integer) zoomProperty.get("width")).intValue();//缩放图片的宽度
            int zoomPicHeight = ((Integer) zoomProperty.get("height")).intValue();//缩放图片的高度
            /**
             * 原图的宽高
             */
            int origina_width = ((Integer) originaProperty.get("width")).intValue();
            int origina_height = ((Integer) originaProperty.get("height")).intValue();
            /**
             * 旋转角度
             */
            Integer intAngle = Integer.parseInt(this.angle);
            //原图的地址
            String czPicPath = originaPath + this.fileName;
            /**
             * 如果有旋转角度，需要先旋转，旋转原图
             */
            boolean isW = true;
            if (intAngle.intValue() % 360 != 0) {
                ThumbUtil.rotate(originaPath + this.fileName, origina_width, origina_height, intAngle, editPath, this.fileName);
                czPicPath = editPath + this.fileName;
                if ((intAngle.intValue() % 360 == 90) || (intAngle.intValue() % 360 == 270)) {
                    isW = false;
                }
            }

            float realZoom = Float.parseFloat(this.scale);
            ThumbUtil.zoomPic(this.fileName, czPicPath, realZoom, editPath);
            /**
             * 獲取縮放圖与原图的比例
             */
            Map sfhPicProperty = null;
            try {
                sfhPicProperty = IskyUtil.printImageTags(new File(editPath + this.fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sfhPic_width = ((Integer) sfhPicProperty.get("width")).intValue();
            int sfhPic_height = ((Integer) sfhPicProperty.get("height")).intValue();
            /**
             * 計算缩放后图片与页面操作的图片之间的比例
             */
            float sfhzoom = (float) sfhPic_width / zoomPicWidth;
            if (!isW) {
                sfhzoom = (float) sfhPic_width / zoomPicHeight;
            }

            /**
             * 根据真实比例计算裁切位置
             */
            float sfhX = 1F * this.x * sfhzoom / realZoom;
            float sfhY = 1F * this.y * sfhzoom / realZoom;
            int cutWidth = (int) (sfhzoom * property_zoom_width / realZoom / ymsfbl);
            int cutHeight = (int) (sfhzoom * property_zoom_height / realZoom / ymsfbl);
            /**
             * 裁切的寬高
             *
             */
            ThumbUtil.cutAsPic(this.fileName, editPath + this.fileName, (int) sfhX, (int) sfhY, cutWidth, cutHeight, editPath);
            ThumbUtil.zoomGdccPic(this.fileName, editPath + this.fileName, width - 1, height - 1, rsPath);


        }
    }


    class editRunblePK implements Runnable {
        private String fileName;
        private FigureImageProperty property;
        private FigureImages images;
        private String imageTypeId;
        private int x;
        private int y;
        private String mytext;
        private String angle;
        private String scale;

        public editRunblePK(String fileName, int x, int y, FigureImageProperty property, FigureImages images, String mytext, String angle, String scale, String imageTypeId) {
            this.fileName = fileName;
            this.property = property;
            this.images = images;
            this.x = x;
            this.y = y;
            this.imageTypeId = imageTypeId;
            this.angle = angle;
            this.mytext = mytext;
            this.scale = scale;
        }

        public void run() {
            String rsPath = Global.getUploadPath() + "rs/";
            String zoomPath = Global.getUploadPath() + "zoom/";
            String zzoomPath = Global.getUploadPath() + "zzoom/";
            String editPath = Global.getUploadPath() + "edit/";
            String aeditPath = Global.getUploadPath() + "aedit/";
            String originaPath = Global.getUploadPath() + "origina/";
            String isround = this.property.getIsround();
            /**
             * 先获取模板合成区域的宽度与高度，（原图的）
             */
            int property_width = Integer.parseInt(this.property.getWidth());//模板要合成空白区域宽度
            int property_height = Integer.parseInt(this.property.getHeight());
            int property_zoom_width = Integer.parseInt(this.property.getZoomWidth()); //模板要合成空白区域宽度(缩放后)
            int property_zoom_height = Integer.parseInt(this.property.getZoomHeight());//模板要合成空白区域高度(缩放后)
            /**
             * 操作页面图片的宽度与高度
             */
            Map zoomProperty = null;
            try {
                zoomProperty = IskyUtil.printImageTags(new File(zzoomPath + this.fileName)); //縮放圖片的屬性
            } catch (Exception e) {
                e.printStackTrace();
            }
            int zoomPicWidth = ((Integer) zoomProperty.get("width")).intValue();//缩放图片的宽度
            int zoomPicHeight = ((Integer) zoomProperty.get("height")).intValue();//缩放图片的高度
            /**
             * 获取原图的宽高
             */
            Map originaProperty = null;
            try {
                originaProperty = IskyUtil.printImageTags(new File(originaPath + this.fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //原图的宽高
            int origina_width = Integer.parseInt(originaProperty.get("width").toString());
            int origina_height = Integer.parseInt(originaProperty.get("height").toString());
            /**
             * 图片如果旋转，先旋转
             */
            Integer intAngle = Integer.valueOf(Integer.parseInt(this.angle));
            String czPicPath = originaPath + this.fileName;//操作图片路径

            boolean isW = true;
            if (intAngle.intValue() % 360 != 0) {
                ThumbUtil.rotate(originaPath + this.fileName, origina_width, origina_height, intAngle, editPath, this.fileName);
                czPicPath = editPath + this.fileName;
                if ((Math.abs(intAngle % 360) == 90) || (Math.abs(intAngle % 360) == 270)) {
                    isW = false;
                }
            }
            /**
             *縮放
             */
            float realZoom = Float.parseFloat(this.scale);
            ThumbUtil.zoomPic(this.fileName, czPicPath, realZoom, editPath);
            /**
             * 獲取縮放圖与原图的比例
             */
            Map sfhPicProperty = null;
            try {
                sfhPicProperty = IskyUtil.printImageTags(new File(editPath + this.fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sfhPic_width = ((Integer) sfhPicProperty.get("width")).intValue();
            int sfhPic_height = ((Integer) sfhPicProperty.get("height")).intValue();

            /**
             * 計算缩放后图片与页面操作的图片之间的比例
             */
            float sfhzoom = (float) sfhPic_width / zoomPicWidth;
            if (!isW) {
                sfhzoom = (float) sfhPic_width / zoomPicHeight;
            }
            /**
             * 根据真实比例计算裁切位置
             */
            float sfhX = 1.0F * this.x * sfhzoom / realZoom;
            float sfhY = 1.0F * this.y * sfhzoom / realZoom;

            int cutWidth = (int) (sfhzoom * property_zoom_width / realZoom);
            int cutHeight = (int) (sfhzoom * property_zoom_height / realZoom);
            /**
             * 裁切的寬高
             */
            ThumbUtil.cutAsPic(this.fileName, editPath + this.fileName, (int) sfhX, (int) sfhY, cutWidth + 2, cutHeight + 2, editPath);


            ThumbUtil.zoomGdccPic(this.fileName, editPath + this.fileName, property_width + 2, property_height + 2, editPath);
            int propertyX = Integer.parseInt(this.property.getXSite());
            int propertyY = Integer.parseInt(this.property.getYSite());

            float zoom_width = Float.parseFloat(this.property.getWidth());
            float zoom_height = Float.parseFloat(this.property.getHeight());

            float pxsite = propertyX - zoom_width / 2.0F - 1.0F;
            float pysite = propertyY - zoom_height / 2.0F - 1.0F;
            String mbpic = Global.getUploadPath() + "template/" + this.images.getImageUrl();

            String successUrl = ThumbUtil.syntesis((int) pxsite, (int) pysite, mbpic, editPath + this.fileName, rsPath, this.fileName);
//
            if (isround.equals("1")) {
                ThumbUtil.syntesis(0, 0, successUrl, mbpic, rsPath, this.fileName);
            }
        }
    }


    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/orderPic"})
    @ResponseBody
    public AjaxResult orderPic(HttpServletRequest request, String mydatas, String picNum) {
        HttpSession session = request.getSession();

        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        FigureLogin login = this.loginService.selectFigureLoginById(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        FigureOrder order = new FigureOrder();
        order.setUserId(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        order.setPicNum(picNum);
        order.setPrint("0");
        order.setDsm("1");
        this.orderService.insertFigureOrder(order);
        int oid = order.getId().intValue();
        JSONArray jsonArray = JSONArray.fromObject(mydatas);
        Object[] jsonObj = jsonArray.toArray();
        FigureOrderImage image = null;
        String fileNames = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            image = new FigureOrderImage();
            JSONObject jo = JSONObject.fromObject(jsonObj[i]);
            image.setOrderId(Integer.valueOf(oid));
            image.setImageUrl(jo.getString("fileName"));
            image.setImageNum(jo.getString("num"));
            this.orderImageService.insertFigureOrderImage(image);
            fileNames = fileNames + jo.getString("fileName") + ",";
        }
        String[] os = fileNames.split(",");
        String pdfPath = Global.getUploadPath() + "pdf/";
        String orderPath = Global.getUploadPath() + "rs/";
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(Integer.valueOf(oid));
        List orderImgList = this.orderImageService.selectFigureOrderImageList(images);

        ToPdf.t(pdfPath, oid + ".pdf", orderPath, os, orderImgList);

//        FigureQrcode figureQrcode = new FigureQrcode();
//        createQrcode(oid);
        return success(String.valueOf(oid));
    }


    public String createQrcode(String oid) {
        String path = "http://10.168.1.244/share?orderId=" + oid;
//        String path = "http://yishuyin/share?orderId=4421";
        String uploadPath = Global.getUploadPath() + "qrcode/";

        String uuid = oid.toString();
        String fileName = uuid + ".jpg";
        try {
            QrcodeUtil.createQrCode(path, uploadPath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "../profile/upload/qrcode/" + fileName;
    }

    @Log(title = "图片上传", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadPicture"})
    @ResponseBody
    public AjaxResult uploadPicture(@RequestParam("myfile") MultipartFile file, String typeid) {
        long start = System.currentTimeMillis();
        String filePath = Global.getUploadPath() + "origina/";
        String zoomPath = Global.getUploadPath() + "zzoom/";
        String cutPath = Global.getUploadPath() + "zcut/";
        String rsPath = Global.getUploadPath() + "zrs/";
        String jpgPath = Global.getUploadPath() + "jpg/";

        File jpgF = new File(jpgPath);
        if (!jpgF.exists()) {
            jpgF.mkdirs();
        }

        String fileName = null;
        try {
            fileName = FileUploadUtils.upload(filePath, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultFile = filePath + fileName;

        FigureLog log = new FigureLog();

        IskyUtil.createJpg(resultFile, jpgPath + fileName);
        String files = filePath + fileName;
        FigureType figureType = this.typeService.selectFigureTypeById(Integer.valueOf(Integer.parseInt(typeid)));
        String isfull = figureType.getIsFull();

        if (isfull.equals("0")) {
            FigureImages image = new FigureImages();
            image.setTpId(typeid);
            List images = this.imagesService.selectFigureImagesList(image);
            if (!StringUtils.isEmpty(images)) {
                image = (FigureImages) images.get(0);
            }
            FigureImageProperty property = new FigureImageProperty();
            property.setImageId(image.getId());
            List propertyList = this.propertyService.selectFigureImagePropertyList(property);
            if (!StringUtils.isEmpty(propertyList)) {
                property = (FigureImageProperty) propertyList.get(0);
            }
            String isround = property.getIsround();
            float zoom_width = Float.parseFloat(property.getZoomWidth());
            float zoom_height = Float.parseFloat(property.getZoomHeight());
            Map imageProperty = null;
            try {
                imageProperty = IskyUtil.printImageTags(new File(files));
            } catch (Exception e) {
                e.printStackTrace();
            }

            int width = Integer.parseInt(imageProperty.get("width").toString());
            int height = Integer.parseInt(imageProperty.get("height").toString());

            if ((zoom_width > zoom_height) && (height > width)) {
                ThumbUtil.rotate(files, width, height, 90, filePath, fileName);
                width = height;
            }

            if ((zoom_height > zoom_width) && (width > height)) {
                ThumbUtil.rotate(files, width, height, 90, filePath, fileName);
                width = height;
            }

            float zoom = zoom_width / width;

            String zoomResult = ThumbUtil.zoomPic(fileName, files, zoom, zoomPath);

            Map zoomProperty = null;
            try {
                zoomProperty = IskyUtil.printImageTags(new File(zoomResult));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int zoom_img_height = Integer.parseInt(String.valueOf(zoomProperty.get("height")));


            if (zoom_img_height < zoom_height) {
                zoom = zoom_height / zoom_img_height;
                ThumbUtil.zoomPic(fileName, zoomResult, zoom, zoomPath);
            }

            int cut_width = (int) zoom_width + 3;
            int cut_height = (int) zoom_height + 2;
            String cutResult = ThumbUtil.cutPicCenter(fileName, zoomResult, cut_width, cut_height, cutPath);

            int xsite = Integer.parseInt(property.getZoomXsite());
            int ysite = Integer.parseInt(property.getZoomYsite());

            float pxsite = xsite - zoom_width / 2.0F - 1.0F;
            float pysite = ysite - zoom_height / 2.0F - 1.0F;
            String mbpic = Global.getUploadPath() + "template/" + image.getImageZoomUrl();

            String successUrl = ThumbUtil.syntesis((int) pxsite, (int) pysite, mbpic, cutResult, rsPath, fileName);

            if (isround.equals("1")) {
                ThumbUtil.syntesis(0, 0, successUrl, mbpic, rsPath, fileName);
            }
            long end = System.currentTimeMillis();
            System.out.println("操作时间;" + (end - start));
            Thread thread = new Thread(new photoRunble(figureType, image, property, fileName));
            thread.start();
        } else {

            float mxp_width = 1795f;
            float mxp_height = 1228f;
            int zwidth = 300;
            float z = zwidth / mxp_width;
            float zoom_width = 300f;
            float zoom_height = (float) 1228f * z;

            Map imageProperty = null;
            try {
                imageProperty = IskyUtil.printImageTags(new File(files));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int width = Integer.parseInt(imageProperty.get("width").toString());
            int height = Integer.parseInt(imageProperty.get("height").toString());
            if (height > width) {
                ThumbUtil.rotate(files, width, height, 90, filePath, fileName);
                width = height;
            }
            float zoom = zoom_width / width;
            String zoomResult = ThumbUtil.zoomPic(fileName, files, zoom, zoomPath);
            Map zoomProperty = null;
            try {
                zoomProperty = IskyUtil.printImageTags(new File(zoomResult));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int zoom_img_height = Integer.parseInt(String.valueOf(zoomProperty.get("height")));
            if (zoom_img_height < zoom_height) {
                zoom = zoom_height / zoom_img_height;
                ThumbUtil.zoomPic(fileName, zoomResult, zoom, zoomPath);
            }

            int cut_width = (int) zoom_width + 3;
            int cut_height = (int) zoom_height + 2;
            ThumbUtil.cutPicCenter(fileName, zoomResult, cut_width, cut_height, rsPath);
            Thread thread = new Thread(new photoRunble(figureType, null, null, fileName));
            thread.start();
        }
        return success(fileName);
    }

    class photoRunble implements Runnable {
        private FigureImages images;
        private FigureImageProperty property;
        private String fileName;
        private FigureType figureType;
        String filePath = Global.getUploadPath() + "origina/";
        String zoomPath = Global.getUploadPath() + "zoom/";
        String cutPath = Global.getUploadPath() + "cut/";
        String rsPath = Global.getUploadPath() + "rs/";
//        String jpgPath = Global.getUploadPath() + "jpg/";

        public photoRunble(FigureType figureType, FigureImages figureImages, FigureImageProperty property, String fileName) {
            this.images = figureImages;
            this.figureType = figureType;
            this.property = property;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
//            String files = this.filePath + this.fileName;
            String isfull = this.figureType.getIsFull();
            String files = filePath + this.fileName;
            if (isfull.equals("0")) {
                String isround = this.property.getIsround();
                float zoom_width = Float.parseFloat(this.property.getWidth());
                float zoom_height = Float.parseFloat(this.property.getHeight());
                int width = IskyUtil.getImgWidth(new File(files));
                float zoom = zoom_width / width;
                String zoomResult = ThumbUtil.zoomPic(this.fileName, files, zoom, this.zoomPath);
                int height = IskyUtil.getImgHeight(new File(zoomResult));
                if (height < zoom_height) {
                    zoom = zoom_height / height;
                    ThumbUtil.zoomPic(this.fileName, zoomResult, zoom, this.zoomPath);
                }
                int cut_width = (int) zoom_width + 3;
                int cut_height = (int) zoom_height + 2;
                String cutResult = ThumbUtil.cutPicCenter(this.fileName, zoomResult, cut_width, cut_height, this.cutPath);

                int xsite = Integer.parseInt(this.property.getXSite());
                int ysite = Integer.parseInt(this.property.getYSite());

                float pxsite = xsite - zoom_width / 2.0F - 1.0F;
                float pysite = ysite - zoom_height / 2.0F - 1.0F;
                String mbpic = Global.getUploadPath() + "template/" + this.images.getImageUrl();
                String result = ThumbUtil.syntesis((int) pxsite, (int) pysite, mbpic, cutResult, this.rsPath, this.fileName);

                if (isround.equals("1")) {
                    ThumbUtil.syntesis(0, 0, result, mbpic, this.rsPath, this.fileName);
                }
                long end = System.currentTimeMillis();
                System.out.println("操作时间;" + (end - start));
            } else {
                float mxp_width = 1795f;
                float mxp_height = 1228f;


                int width = IskyUtil.getImgWidth(new File(files));
                float zoom = mxp_width / width;
                String zoomResult = ThumbUtil.zoomPic(this.fileName, files, zoom, this.zoomPath);
                int height = IskyUtil.getImgHeight(new File(zoomResult));
                if (height < mxp_height) {
                    zoom = mxp_height / height;
                    ThumbUtil.zoomPic(this.fileName, zoomResult, zoom, this.zoomPath);
                }
                int cut_width = (int) mxp_width + 3;
                int cut_height = (int) mxp_height + 2;
                ThumbUtil.cutPicCenter(this.fileName, zoomResult, cut_width, cut_height, this.rsPath);
            }
        }
    }


    @PostMapping({"/loginUser"})
    @ResponseBody
    public AjaxResult loginUser(FigureLogin figureLogin, HttpServletRequest request) {
        boolean isFromMobile = false;
        HttpSession session = request.getSession();

        if (null == session.getAttribute("ua"))
            try {
                String userAgent = request.getHeader("USER-AGENT").toLowerCase();
                if (null == userAgent) {
                    userAgent = "";
                }
                isFromMobile = CheckMobile.check(userAgent);

                if (isFromMobile) {
                    System.out.println("移动端访问");
                    session.setAttribute("ua", "mobile");
                } else {
                    System.out.println("pc端访问");
                    session.setAttribute("ua", "pc");
                }
            } catch (Exception localException) {
            }
        else isFromMobile = session.getAttribute("ua").equals("mobile");

        List loginList = this.loginService.selectFigureLoginList(figureLogin);
        FigureLogin vo = null;
        if ((loginList != null) && (loginList.size() > 0)) {
            vo = (FigureLogin) loginList.get(0);
        } else {
            this.loginService.insertFigureLogin(figureLogin);
            vo = figureLogin;
        }
        session.setAttribute("u_s_e_id", String.valueOf(vo.getId()));
        return success(String.valueOf(vo.getId()));
    }
}