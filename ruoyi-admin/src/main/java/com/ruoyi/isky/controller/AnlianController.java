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
@RequestMapping("/anlian")
public class AnlianController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(AnlianController.class);
    private String prefix = "isky/anlian";

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


    @GetMapping({"/select"})
    public String select(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        FigureType figureType = new FigureType();
        figureType.setTypeId("11");
        List typeList = this.typeService.selectFigureTypeList(figureType);
        mmap.put("typeList", typeList);
        return this.prefix + "/select";
    }


    @PostMapping({"/deleteFiles"})
    @ResponseBody
    public AjaxResult deleteFiles(String bno, String mydatas) {
        if (!StringUtils.isEmpty(mydatas)) {
            mydatas = StringEscapeUtils.unescapeHtml(mydatas);
            mydatas = mydatas.replaceAll("\\\\", "");
            JSONObject jsonObject = JSONObject.fromObject(mydatas);
//            String ss = jsonObject.getString("fileName");
            String tno = jsonObject.getString("tno");
            FigureLog logParam = new FigureLog();
            logParam.setBno(bno);
            logParam.setOrders(Integer.valueOf(tno));
            List<FigureLog> logList = this.logService.selectFigureLogList(logParam);
            String ids = "";
            System.out.println("*************************************************");
            System.out.println(tno);
            System.out.println("*************************************************");
            for (FigureLog logs : logList) {
                logs.setState("1");
                this.logService.updateFigureLog(logs);
            }
        }
        return success();
    }

    @GetMapping({"/originalpic"})
    public String originalpic(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String tid = request.getParameter("tid");
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        FigureImages images = new FigureImages();
        images.setTpId(tid);
        List<FigureImages> list = imagesService.selectFigureImagesList(images);
        int areaNum = list.stream().collect(Collectors.summingInt(FigureImages::getAreaNum));
        mmap.put("areaNum", areaNum);
        return this.prefix + "/originalpic";
    }


    @PostMapping({"/uploadDropZone"})
    @ResponseBody
    public AjaxResult uploadDropZone(@RequestParam("file") MultipartFile m, HttpServletRequest request) {
        String typeid = request.getHeader("typeid");
        String bno = request.getHeader("bno");
        String filePath = Global.getUploadPath() + "origina/";

        FigureLog log = new FigureLog();
        log.setBno(bno);
        List<FigureLog> list = this.logService.selectFigureLogList(log);
        int tno = 0;
        log.setState("0");
        boolean flag = false;
        if ((list != null) && (list.size() > 0)) {
            if (list.size() >= 54) {
                log.setState("1");
                list = this.logService.selectFigureLogList(log);
                if ((list != null) && (list.size() > 0)) {
                    log = (FigureLog) list.get(0);
                    tno = log.getOrders().intValue();
                } else {
                    flag = true;
                }
            } else {
                tno = list.size();
                if(tno>12){
                    log.setState("1");
                    list = this.logService.selectFigureLogList(log);
                    if(list!=null && !list.isEmpty()){
                        FigureLog log1 = list.get(0);
                        tno = log1.getOrders();
                        log1.setState("2");
                        this.logService.updateFigureLog(log1);
                        log.setState("0");
                    }
                }
            }
        }
        if (flag) {
            JSONObject jo = new JSONObject();
            jo.put("code", Integer.valueOf(1));
            return success(jo.toString());
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String fileName = null;
        try {
            fileName = FileUploadUtils.upload(filePath, m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultFile = filePath + fileName;
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

        Thread thread = new Thread(new DropzonephotoRunble(typeid, fileName, bno, tno));
        thread.start();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("fileName", fileName);
        ajax.put("tno", tno);
        ajax.put("bno", bno);
        ajax.put("code", 0);
        return ajax;
    }


    class DropzonephotoRunble implements Runnable {
        private String typeid;
        private String fileName;
        private String bno;
        private Integer tno;

        public DropzonephotoRunble(String typeid, String fileName, String bno, int tno) {
            this.typeid = typeid;
            this.fileName = fileName;
            this.bno = bno;
            this.tno = Integer.valueOf(tno);
        }

        @Override
        public void run() {
            String filePath = Global.getUploadPath() + "origina/";
            String zoomPath = Global.getUploadPath() + "zzoom/";
            String cutPath = Global.getUploadPath() + "zcut/";
            String rsPath = Global.getUploadPath() + "zrs/";

            FigureType figureType = typeService.selectFigureTypeById(Integer.valueOf(Integer.parseInt(this.typeid)));
            FigureImages image = new FigureImages();
            image.setTpId(this.typeid);
            List images = imagesService.selectFigureImagesList(image);
            FigureImages figureImages = null;

            List imgsArray = new ArrayList();

            figureImages = (FigureImages) images.get(this.tno.intValue());

            String files = filePath + this.fileName;
            FigureImageProperty property = new FigureImageProperty();
            property.setImageId(figureImages.getId());
            List propertyList = propertyService.selectFigureImagePropertyList(property);
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

            int width = ((Integer) imageProperty.get("width")).intValue();
            int height = ((Integer) imageProperty.get("height")).intValue();

            float zoom = zoom_width / width;

            String zoomResult = ThumbUtil.zoomPic(this.fileName, files, zoom, zoomPath);

            int zoom_img_height = ImageTools.getImgHeight(new File(zoomResult));

            if (zoom_img_height < zoom_height) {
                zoom = zoom_height / zoom_img_height;
                ThumbUtil.zoomPic(this.fileName, zoomResult, zoom, zoomPath);
            }

            int cut_width = (int) zoom_width + 3;
            int cut_height = (int) zoom_height + 2;
            String cutResult = ThumbUtil.cutPicCenter(this.fileName, zoomResult, cut_width, cut_height, cutPath);

            int xsite = Integer.parseInt(property.getZoomXsite());
            int ysite = Integer.parseInt(property.getZoomYsite());

            float pxsite = xsite - zoom_width / 2.0F - 1.0F;
            float pysite = ysite - zoom_height / 2.0F;
            String mbpic = Global.getUploadPath() + "template/" + figureImages.getImageZoomUrl();

            String successUrl = ThumbUtil.syntesis((int) pxsite, (int) pysite, mbpic, cutResult, rsPath, this.fileName);

            if (isround.equals("1")) {
                ThumbUtil.syntesis(0, 0, successUrl, mbpic, rsPath, this.fileName);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileName", this.fileName);
            jsonObject.put("id", figureImages.getId());
            imgsArray.add(jsonObject);
            Thread thread = new Thread(new photoRunble(figureImages, property, this.fileName));
            thread.start();
        }
    }


    class photoRunble implements Runnable {
        private FigureImages images;
        private FigureImageProperty property;
        private String fileName;
        String filePath = Global.getUploadPath() + "origina/";
        String zoomPath = Global.getUploadPath() + "zoom/";
        String cutPath = Global.getUploadPath() + "cut/";
        String rsPath = Global.getUploadPath() + "rs/";

        public photoRunble(FigureImages images, FigureImageProperty property, String fileName) {
            this.property = property;
            this.fileName = fileName;
            this.images = images;
        }


        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String files = this.filePath + this.fileName;
            String isround = this.property.getIsround();

            float zoom_width = Float.parseFloat(this.property.getWidth());
            float zoom_height = Float.parseFloat(this.property.getHeight());

            int width = ImageTools.getImgWidth(new File(files));

            float zoom = zoom_width / width;
            String zoomResult = ThumbUtil.zoomPic(this.fileName, files, zoom, this.zoomPath);

            int height = ImageTools.getImgHeight(new File(zoomResult));

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
        }
    }


    @PostMapping({"/getFileNum"})
    @ResponseBody
    public AjaxResult getFileNum(String bno) {
        FigureLog log = new FigureLog();
        log.setBno(bno);
        log.setState("0");
        List logLists = this.logService.selectFigureLogList(log);
        return success(String.valueOf(logLists.size()));
    }


    @GetMapping({"/picture"})
    public String picture(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        String n = request.getParameter("n");
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        mmap.put("n",n);
        return this.prefix + "/picture";
    }


    @GetMapping({"/order"})
    public String order(HttpServletRequest request, String n, String orderId, ModelMap mmap) {
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }
        FigureLogin login = this.loginService.selectFigureLoginById(Integer.valueOf(Integer.parseInt(u_s_e_id)));

        FigureOrder order = this.orderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        order.setTaobao(login.getTaobao());
        order.setTelephone(login.getTelephone());
        order.setDsm(n);
        order.setUsername(login.getUsername());
        String qrcodePath = QrcodeUtil.createQC(orderId);
        mmap.put("dto", order);
        mmap.put("qrcode", qrcodePath);
        return this.prefix + "/order";
    }


    @GetMapping({"/editPic"})
    public String editPic(HttpServletRequest request, String num, String imageId, String tpid, String suffix, ModelMap mmap) {
        Integer intNum = Integer.valueOf(Integer.parseInt(num));
        System.out.println(intNum);
        boolean isMoblie = IskyUtil.JudgeIsMoblie(request);
        HttpSession session = request.getSession();
        String u_s_e_id = (String) session.getAttribute("u_s_e_id");
        if (StringUtils.isEmpty(u_s_e_id)) {
            return login();
        }

        FigureImages images = new FigureImages();
        images.setTpId(tpid);
        List imagesList = this.imagesService.selectFigureImagesList(images);
        images = (FigureImages) imagesList.get(intNum.intValue());

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
        mmap.put("tno", intNum);
        mmap.put("fileName", imageId + "." + suffix);
        mmap.put("imageUrl", "../profile/upload/zzoom/" + imageId + "." + suffix);
        return prefix + "/editPic";
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
        order.setDsm("11");
        this.orderService.insertFigureOrder(order);
        int oid = order.getId().intValue();
        JSONArray jsonArray = JSONArray.fromObject(mydatas);
        Object[] jsonObj = jsonArray.toArray();
        FigureOrderImage image = null;
        String fileNames = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            image = new FigureOrderImage();
            JSONObject jo = JSONObject.fromObject(jsonObj[i]);
            Integer tno = Integer.parseInt(jo.getString("tno"));
            image.setOrderId(Integer.valueOf(oid));
            image.setImageUrl(jo.getString("fileName"));
            image.setImageNum("1");
            image.setOrders(tno);
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

    @Log(title = "图片编辑", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadBlob"})
    @ResponseBody
    public AjaxResult uploadBlob(HttpServletRequest request, String mytext, String fileName, String file, String xsite, String ysite, Integer tno, String angle, String scale, String typeid) {
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

        FigureType figureType = this.typeService.selectFigureTypeById(Integer.valueOf(Integer.parseInt(typeid)));
        String imageTypeId = figureType.getTypeId();

        FigureImages images = new FigureImages();
        images.setTpId(typeid);
        List imagesList = this.imagesService.selectFigureImagesList(images);
        images = (FigureImages) imagesList.get(tno.intValue());
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
        return success();
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
            if (isround.equals("1")) {
                ThumbUtil.syntesis(0, 0, successUrl, mbpic, rsPath, this.fileName);
            }
        }
    }
}
