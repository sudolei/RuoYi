package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.isky.Base64Image;
import com.ruoyi.common.isky.ImageTools;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/we")
public class BoController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(BoController.class);
    private String prefix = "isky/bo";

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
    private IFigureLogService logService;


    @GetMapping({""})
    public String index() {
        return this.prefix + "/login";
    }

    @GetMapping({"/index"})
    public String login() {
        return this.prefix + "/login";
    }


    @GetMapping({"/branch"})
    public String branch(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
//        FigureType figureType = new FigureType();
//        figureType.setTypeId("11");
//        List typeList = this.typeService.selectFigureTypeList(figureType);
//        mmap.put("typeList", typeList);
        return this.prefix + "/branch";
    }

    @GetMapping({"/select"})
    public String select(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        String tid = request.getParameter("tid");
        session.setAttribute("mtid", tid);

        FigureType figureType = new FigureType();
        figureType.setTypeId(tid);
        List typeList = this.typeService.selectFigureTypeList(figureType);
        mmap.put("typeList", typeList);
        mmap.put("tid", tid);
        return this.prefix + "/select";
    }


    @GetMapping({"/picture"})
    public String picture(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String bno = request.getParameter("bno");
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        mmap.put("bno", bno);
        String mtid = (String) session.getAttribute("mtid");
        if (mtid.equals("12")) {
            return this.prefix + "/picture";
        } else {
            return this.prefix + "/pictureTl";
        }


    }

    @GetMapping({"/originalpic"})
    public String originalpic(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String tid = request.getParameter("tid");
        String ttid = request.getParameter("ttid");
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        // 12是明信片  13是台历
        if (ttid.equals("13")) {
            FigureImages images = new FigureImages();
            images.setTpId(tid);
            List<FigureImages> list = imagesService.selectFigureImagesList(images);
            int areaNum = list.stream().collect(Collectors.summingInt(FigureImages::getAreaNum));
            mmap.put("areaNum", areaNum);
            mmap.put("mtid", ttid);
            return this.prefix + "/originalTl";
        } else {
            return this.prefix + "/originalMxp";
        }

    }

    @PostMapping({"/getFileNum"})
    @ResponseBody
    public AjaxResult getFileNum(String bno) {
        FigureLog log = new FigureLog();
        log.setBno(bno);
        List logLists = this.logService.selectFigureLogList(log);
        return success(String.valueOf(logLists.size()));
    }

    @GetMapping({"/order"})
    public String order(HttpServletRequest request, String orderId, ModelMap mmap) {
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        FigureLogin login = loginService.selectFigureLoginById(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        FigureOrderImage orderImage = new FigureOrderImage();
        orderImage.setOrderId(Integer.parseInt(orderId));
        List<FigureOrderImage> orderImageList = orderImageService.selectFigureOrderImageList(orderImage);
        int picNum = 0;
        for (FigureOrderImage image : orderImageList) {
            picNum += Integer.parseInt(image.getImageNum());
        }

        FigureOrder order = orderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        order.setTaobao(login.getTaobao());
        order.setTelephone(login.getTelephone());
        order.setUsername(login.getUsername());
        order.setPicNum(String.valueOf(picNum));

        String qrcodePath = QrcodeUtil.createQC(orderId);
        mmap.put("dto", order);
        mmap.put("qrcode", qrcodePath);
        String mtid = (String) session.getAttribute("mtid");
        if (mtid.equals("12")) {
            return prefix + "/order";
        } else {
            return prefix + "/orderTl";
        }

    }



    @GetMapping({"/editPic"})
    public String editPic(HttpServletRequest request, String imageId, String tpid, String bno, String suffix, ModelMap mmap) {
        boolean isMoblie = IskyUtil.JudgeIsMoblie(request);
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        FigureType figureType = typeService.selectFigureTypeById(Integer.parseInt(tpid));
        String isFull = figureType.getIsFull();
        if (isFull.equals("1")) {
            mmap.put("width", 300);
            mmap.put("height", 206);
            mmap.put("imageId", imageId);
            mmap.put("isMoblie", Boolean.valueOf(isMoblie));
            mmap.put("fileName", imageId + "." + suffix);
            mmap.put("imageUrl", "../profile/upload/zzoom/" + imageId + "." + suffix);
        } else {
            FigureImages images = new FigureImages();
            images.setTpId(tpid);
            List imagesList = imagesService.selectFigureImagesList(images);
            images = (FigureImages) imagesList.get(0);
            FigureImageProperty property = new FigureImageProperty();
            property.setImageId(images.getId());
            List propertyList = propertyService.selectFigureImagePropertyList(property);
            property = (FigureImageProperty) propertyList.get(0);
            String width = property.getZoomWidth();
            String height = property.getZoomHeight();
            mmap.put("textXsite", images.getTextXsite());
            mmap.put("textYsite", images.getTextYsite());
            mmap.put("width", Integer.valueOf(Integer.parseInt(width) + 3));
            mmap.put("height", Integer.valueOf(Integer.parseInt(height) + 2));
            mmap.put("imageId", imageId);
            mmap.put("bno", bno);
            mmap.put("isMoblie", Boolean.valueOf(isMoblie));
            mmap.put("fileName", imageId + "." + suffix);
            mmap.put("imageUrl", "../profile/upload/zzoom/" + imageId + "." + suffix);
        }
        return prefix + "/editPic";
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
        Base64Image.GenerateImage(imgBase64Blob, filepath);

        FigureType figureType = typeService.selectFigureTypeById(Integer.valueOf(Integer.parseInt(typeid)));
        String isfull = figureType.getIsFull();

        //如果不是满版的
        if (isfull.equals("0")) {
            String imageTypeId = figureType.getTypeId();
            FigureImages images = new FigureImages();
            images.setTpId(typeid);
            List imagesList = imagesService.selectFigureImagesList(images);
            images = (FigureImages) imagesList.get(0);
            FigureImageProperty property = new FigureImageProperty();
            property.setImageId(images.getId());
            List propertyList = propertyService.selectFigureImagePropertyList(property);
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
                pysite = y - zoom_height / 2.0F;
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
//            int width = ImageTools.getImgWidth(new File(imgUlr));
//
//            int tx = Integer.parseInt(images.getTextXsite());
//            int ty = Integer.parseInt(images.getTextYsite());
//            float zoom = 350.0F / width;
//            tx = (int)(zoom * tx);
//            ty = (int)(zoom * ty);
//
//            ImgWriteText.exportImg(rsPath + fileName, rsPath + fileName, mytext, tx, ty, 20);
//        }

            xsite = xsite.split("\\.")[0];
            ysite = ysite.split("\\.")[0];
            Thread thread = new Thread(new editRunble(fileName, Integer.parseInt(xsite), Integer.parseInt(ysite), property, images, mytext, angle, scale, imageTypeId));
            thread.start();
        } else {
            Base64Image.GenerateImage(imgBase64Blob, rsPath + fileName);
            Thread thread = new Thread(new editFullRunble(fileName, Integer.parseInt(xsite), Integer.parseInt(ysite), mytext, angle, scale));
            thread.start();
        }
        return success();
    }


    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/orderPic"})
    @ResponseBody
    public AjaxResult orderPic(HttpServletRequest request, String mydatas, String bno, String picNum) {
        HttpSession session = request.getSession();

        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        JSONArray jsonArray = JSONArray.fromObject(mydatas);

        if (StringUtils.isEmpty(picNum)) {
            picNum = String.valueOf(jsonArray.size());
        }

        FigureLogin login = this.loginService.selectFigureLoginById(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        FigureOrder order = new FigureOrder();
        order.setUserId(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        order.setPicNum(picNum);
        order.setPrint("0");
        order.setDsm("12");
        order.setBno(bno);
        this.orderService.insertFigureOrder(order);
        int oid = order.getId().intValue();

        Object[] jsonObj = jsonArray.toArray();
        FigureOrderImage image = null;
        String fileNames = "";
        int nums = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            image = new FigureOrderImage();
            JSONObject jo = JSONObject.fromObject(jsonObj[i]);
            int num = Integer.parseInt(jo.getString("num"));
            image.setOrderId(Integer.valueOf(oid));
            image.setImageUrl(jo.getString("fileName"));
            image.setImageNum(jo.getString("num"));

            this.orderImageService.insertFigureOrderImage(image);
            fileNames = fileNames + jo.getString("fileName") + ",";

            nums += num;
        }

        order.setPicNum(String.valueOf(nums));
        orderService.updateFigureOrder(order);
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


    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/orderTlPic"})
    @ResponseBody
    public AjaxResult orderTlPic(HttpServletRequest request, String mydatas, String picNum) {
        HttpSession session = request.getSession();

        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        FigureLogin login = this.loginService.selectFigureLoginById(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        FigureOrder order = new FigureOrder();
        order.setUserId(Integer.valueOf(Integer.parseInt(u_s_e_id)));
        order.setPicNum(picNum);
        order.setPrint("0");
        order.setDsm("13");
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
            image.setImageNum("1");
            this.orderImageService.insertFigureOrderImage(image);
            fileNames = fileNames + jo.getString("fileName") + ",";
        }
        String[] os = fileNames.split(",");
        String pdfPath = Global.getUploadPath() + "pdf/";
        String orderPath = Global.getUploadPath() + "rs/";
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(Integer.valueOf(oid));
        List orderImgList = this.orderImageService.selectFigureOrderImageList(images);
        return success(String.valueOf(oid));
    }

    class editRunble implements Runnable {
        private String fileName;
        private FigureImageProperty property;
        private FigureImages images;
        private String imageTypeId;
        private int x;
        private int y;
        private String mytext;
        private String angle;
        private String scale;

        public editRunble(String fileName, int x, int y, FigureImageProperty property, FigureImages images, String mytext, String angle, String scale, String imageTypeId) {
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
//            String originaPath = Global.getUploadPath() + "origina/";
            String jpgPath = Global.getUploadPath() + "jpg/";
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
                originaProperty = IskyUtil.printImageTags(new File(jpgPath + this.fileName));
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
            String czPicPath = jpgPath + this.fileName;//操作图片路径

            boolean isW = true;
            if (intAngle.intValue() % 360 != 0) {
                ThumbUtil.rotate(jpgPath + this.fileName, origina_width, origina_height, intAngle, editPath, this.fileName);
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
            float sfhX = 1F * this.x * sfhzoom / realZoom;
            float sfhY = 1F * this.y * sfhzoom / realZoom;

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
            String jpgPath = Global.getUploadPath() + "jpg/";


//            Float ymsfbl = 1.8F;
            Float ymsfbl = 1F;
            int width = 1795;
            int height = 1228;
            int property_zoom_width = 300; //模板要合成空白区域宽度(缩放后)
            int property_zoom_height = 205;//模板要合成空白区域高度(缩放后)

            Map originaProperty = null;
            try {
                originaProperty = IskyUtil.printImageTags(new File(jpgPath + this.fileName));
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
            String czPicPath = jpgPath + this.fileName;
            /**
             * 如果有旋转角度，需要先旋转，旋转原图
             */
            boolean isW = true;
            if (intAngle.intValue() % 360 != 0) {
                ThumbUtil.rotate(jpgPath + this.fileName, origina_width, origina_height, intAngle, editPath, this.fileName);
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


    @PostMapping({"/uploadDropZone"})
    @ResponseBody
    public AjaxResult
    uploadDropZone(@RequestParam("file") MultipartFile m, HttpServletRequest request) {
        String typeid = request.getHeader("typeid");
        String bno = request.getHeader("bno");
        String filePath = Global.getUploadPath() + "origina/";
        String jpgPath = Global.getUploadPath() + "jpg/";
        FigureLog log = new FigureLog();
        log.setBno(bno);
        List list = this.logService.selectFigureLogList(log);
        int tno = 0;
        log.setState("0");
        boolean flag = false;
//        if ((list != null) && (list.size() > 0)) {
//            if (list.size() >= intNums) {
//                log.setState("1");
//                list = this.logService.selectFigureLogList(log);
//                if ((list != null) && (list.size() > 0)) {
//                    log = (FigureLog) list.get(0);
//                    tno = log.getOrders().intValue();
//                } else {
//                    flag = true;
//                }
//            } else {
//                tno = list.size();
//            }
//        }
        if (flag) {
            JSONObject jo = new JSONObject();
            jo.put("code", Integer.valueOf(1));
            return success(jo.toString());
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }

        File jpgF = new File(jpgPath);
        if (!jpgF.exists()) {
            jpgF.mkdirs();
        }

        String fileName = null;
        try {
            fileName = FileUploadUtils.upload(filePath, m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultFile = filePath + fileName;
        IskyUtil.createJpg(resultFile, jpgPath + fileName);

        if (log.getState().equals("0")) {
            log.setBno(bno);
            log.setTypeId(Integer.valueOf(Integer.parseInt(typeid)));
            log.setFileName(fileName);
            log.setState("0");
            log.setOrders(Integer.valueOf(tno));
            this.logService.insertFigureLog(log);
        } else {
            log.setBno(bno);
            log.setTypeId(Integer.valueOf(Integer.parseInt(typeid)));
            log.setFileName(fileName);
            log.setState("0");
            log.setOrders(Integer.valueOf(tno));
            this.logService.updateFigureLog(log);
        }
        Thread thread = new Thread(new DropzonephotoRunble(typeid, fileName, bno));
        thread.start();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("fileName", fileName);
        ajax.put("tno", tno);
        ajax.put("code", 0);
        return ajax;
    }


    class DropzonephotoRunble implements Runnable {
        private String typeid;
        private String fileName;
        private String bno;


        public DropzonephotoRunble(String typeid, String fileName, String bno) {
            this.typeid = typeid;
            this.fileName = fileName;
            this.bno = bno;
        }


        @Override
        public void run() {
            String zoomPath = Global.getUploadPath() + "zzoom/";
            String zzzoomPath = Global.getUploadPath() + "zzzoom/";
            String cutPath = Global.getUploadPath() + "zcut/";
            String rsPath = Global.getUploadPath() + "zrs/";
            String jpgPath = Global.getUploadPath() + "jpg/";

            FigureType figureType = typeService.selectFigureTypeById(Integer.valueOf(Integer.parseInt(this.typeid)));
            FigureImages image = new FigureImages();
            image.setTpId(this.typeid);
            List images = imagesService.selectFigureImagesList(image);

            String isfull = figureType.getIsFull();
            if (!isfull.equals("0")) {
                String files = jpgPath + this.fileName;
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
                    ThumbUtil.rotate(files, width, height, 90, jpgPath, fileName);
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
                Thread thread = new Thread(new photoFullRunble(figureType, fileName));
                thread.start();
            } else {
                FigureImages figureImages = (FigureImages) images.get(0);
                List imgsArray = new ArrayList();
                String files = jpgPath + this.fileName;
                System.out.println(files);
                FigureImageProperty property = new FigureImageProperty();
                property.setImageId(figureImages.getId());
                List propertyList = propertyService.selectFigureImagePropertyList(property);
                if (!StringUtils.isEmpty(propertyList)) {
                    property = (FigureImageProperty) propertyList.get(0);
                }
                String isround = property.getIsround();

                float zoom_width = Float.parseFloat(property.getZoomWidth());
                float zoom_height = Float.parseFloat(property.getZoomHeight());
                int width = IskyUtil.getImgWidth(new File(files));
                int height = IskyUtil.getImgHeight(new File(files));
                if ((zoom_width > zoom_height) && (height > width)) {
                    ThumbUtil.rotate(files, width, height, 90, jpgPath, fileName);
                    width = height;
                }

                if ((zoom_height > zoom_width) && (width > height)) {
                    ThumbUtil.rotate(files, width, height, 90, jpgPath, fileName);
                    width = height;
                }

                float zoom = zoom_width / width;

                String zoomResult = ThumbUtil.zoomPic(this.fileName, files, zoom, zoomPath);

                int zoom_img_height = ImageTools.getImgHeight(new File(zoomResult));

                if (zoom_img_height < zoom_height) {
                    zoom = zoom_height / zoom_img_height;
                    ThumbUtil.zoomPic(this.fileName, zoomResult, zoom, zoomPath);
                }

                int cut_width = (int) zoom_width + 3;
                int cut_height = (int) zoom_height + 3;


                String cutResult = ThumbUtil.cutPicCenter(this.fileName, zoomResult, cut_width, cut_height, cutPath);

                int xsite = Integer.parseInt(property.getZoomXsite());
                int ysite = Integer.parseInt(property.getZoomYsite());

                float pxsite = xsite - zoom_width / 2.0F - 1.0F;
                float pysite = ysite - zoom_height / 2.0F - 1.0F;
                String mbpic = Global.getUploadPath() + "template/" + figureImages.getImageZoomUrl();

                String successUrl = ThumbUtil.syntesis((int) pxsite, (int) pysite, mbpic, cutResult, rsPath, this.fileName);

                if (isround.equals("1")) {
                    ThumbUtil.syntesis(0, 0, successUrl, mbpic, rsPath, this.fileName);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileName", this.fileName);
                jsonObject.put("id", figureImages.getId());
                imgsArray.add(jsonObject);

                Thread thread = new Thread(new photoRunble(figureImages, property, this.fileName, width, height));
                thread.start();
            }


        }
    }

    class photoFullRunble implements Runnable {
        private FigureImages images;
        private FigureImageProperty property;
        private String fileName;
        private FigureType figureType;
        String filePath = Global.getUploadPath() + "origina/";
        String zoomPath = Global.getUploadPath() + "zoom/";
        String rsPath = Global.getUploadPath() + "rs/";
        String jpgPath = Global.getUploadPath() + "jpg/";

        public photoFullRunble(FigureType figureType, String fileName) {
            this.figureType = figureType;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String files = filePath + this.fileName;
            float mxp_width = 1795f;
            float mxp_height = 1228f;
            Map imageProperty = null;
            try {
                imageProperty = IskyUtil.printImageTags(new File(files));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int width = Integer.parseInt(imageProperty.get("width").toString());
            int height = Integer.parseInt(imageProperty.get("height").toString());
            if (height > width) {
                ThumbUtil.rotate(files, width, height, 90, jpgPath, fileName);
                width = height;
            }
            float zoom = mxp_width / width;
            String zoomResult = ThumbUtil.zoomPic(this.fileName, jpgPath + fileName, zoom, this.zoomPath);
            int zheight = IskyUtil.getImgHeight(new File(zoomResult));
            if (zheight < mxp_height) {
                zoom = mxp_height / zheight;
                ThumbUtil.zoomPic(this.fileName, zoomResult, zoom, this.zoomPath);
            }
            int cut_width = (int) mxp_width + 3;
            int cut_height = (int) mxp_height + 2;
            ThumbUtil.cutPicCenter(this.fileName, zoomResult, cut_width, cut_height, this.rsPath);
        }
    }

    class photoRunble implements Runnable {
        private FigureImages images;
        private FigureImageProperty property;
        private String fileName;
        private int width;
        private int height;
        String jpgPath = Global.getUploadPath() + "jpg/";
        String zoomPath = Global.getUploadPath() + "zoom/";
        String cutPath = Global.getUploadPath() + "cut/";
        String rsPath = Global.getUploadPath() + "rs/";

        public photoRunble(FigureImages figureImages, FigureImageProperty property, String fileName, int width, int height) {
            this.images = figureImages;
            this.property = property;
            this.fileName = fileName;
            this.width = width;
            this.height = height;
        }

        public void run() {
            long start = System.currentTimeMillis();
            String files = this.jpgPath + this.fileName;
            String isround = this.property.getIsround();

            float zoom_width = Float.parseFloat(this.property.getWidth());
            float zoom_height = Float.parseFloat(this.property.getHeight());

            float zoom = zoom_width / width;
            String zoomResult = ThumbUtil.zoomPic(this.fileName, files, zoom, this.zoomPath);

            int height = IskyUtil.getImgHeight(new File(zoomResult));

            if (height < zoom_height) {
                zoom = zoom_height / height;
                ThumbUtil.zoomPic(this.fileName, zoomResult, zoom, this.zoomPath);
            }

            int cut_width = (int) zoom_width + 3;
            int cut_height = (int) zoom_height + 3;
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
        }
    }
}
