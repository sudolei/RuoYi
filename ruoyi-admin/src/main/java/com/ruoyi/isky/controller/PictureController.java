package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.isky.domain.FigureOrder;
import com.ruoyi.isky.domain.FigureOrderImage;
import com.ruoyi.isky.service.IFigureImagesService;
import com.ruoyi.isky.service.IFigureOrderImageService;
import com.ruoyi.isky.service.IFigureOrderService;
import com.ruoyi.isky.service.IFigureTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/pic"})
public class PictureController extends BaseController
{
    private String prefix = "isky/picture";

    @Autowired
    private IFigureTypeService typeService;

    @Autowired
    private IFigureImagesService imagesService;

    @Autowired
    private IFigureOrderService orderService;

    @Autowired
    private IFigureOrderImageService orderImageService;

    @GetMapping({"/index"})
    public String index()
    {
        return this.prefix + "/main";
    }


    @GetMapping({""})
    public String index1()
    {
        return this.prefix + "/main";
    }

    @GetMapping({"/main"})
    public String main()
    {
        return this.prefix + "/main";
    }

    @GetMapping({"/main1"})
    public String main1()
    {
        return this.prefix + "/main1";
    }

    @Log(title="图片上传", businessType=BusinessType.OTHER)
    @PostMapping({"/uploadPicture"})
    @ResponseBody
    public AjaxResult uploadPicture(@RequestParam("myfile") MultipartFile file, String typeid)
    {
        long start = System.currentTimeMillis();
        String filePath = Global.getUploadPath() + "picture/";

        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String fileName = null;
        try {
            fileName = FileUploadUtils.upload(filePath, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String files = filePath + fileName;
        return success(fileName);
    }

    @Log(title="图片上传", businessType=BusinessType.OTHER)
    @RequestMapping({"/uploadF"})
    @ResponseBody
    public AjaxResult uploadF(HttpServletRequest request, @RequestParam(value="myfile", required=true) MultipartFile multipartFile) {
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());
        String filePath = Global.getUploadPath() + "picture/";

        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String fileName = null;
        try {
            fileName = FileUploadUtils.upload(filePath, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultFile = filePath + fileName;
        System.out.println(resultFile);
        return success(fileName);
    }

    @RequestMapping(value={"/batchUpload"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
        List files = ((MultipartHttpServletRequest)request).getFiles("myfile");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); i++) {
            file = (MultipartFile)files.get(i);
            if (!file.isEmpty())
                try {
                    byte[] bytes = file.getBytes();

                    stream = new BufferedOutputStream(new FileOutputStream(new File(file
                            .getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " =>" + e.getMessage();
                }
            else {
                return "You failed to upload " + i + " becausethe file was empty.";
            }
        }
        return "upload successful";
    }
    @Log(title="图片上传", businessType=BusinessType.OTHER)
    @RequestMapping({"/uploadFf"})
    @ResponseBody
    public AjaxResult uploadFf(HttpServletRequest request, @RequestParam(value="myfile", required=true) List<MultipartFile> uploadFiles) {
        String ggoid = request.getParameter("oid");
        String fileNum = request.getParameter("fileNum");
        String len = request.getParameter("len");

        FigureOrder order = new FigureOrder();
        order.setOrderId(ggoid);
        List orders = orderService.selectFigureOrders(order);
        if ((orders != null) && (orders.size() > 0)) {
            order = (FigureOrder)orders.get(0);
        } else {
            order = new FigureOrder();
            order.setUserId(Integer.valueOf(0));
            order.setOrderId(ggoid);
            order.setPicNum(String.valueOf(uploadFiles.size()));
            order.setPrint("0");
            this.orderService.insertFigureOrder(order);
        }

        int oid = order.getId().intValue();
        FigureOrderImage image = null;

        for (MultipartFile m : uploadFiles) {
            image = new FigureOrderImage();
            System.out.println(m.getName());
            System.out.println(m.getOriginalFilename());
            String filePath = Global.getUploadPath() + "picture/" + ggoid + "/";

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

            image.setOrderId(order.getId());
            image.setImageUrl(fileName);
            this.orderImageService.insertFigureOrderImage(image);
        }

        boolean flag = false;
        int fileNumInt = Integer.parseInt(fileNum);
        int lenInt = Integer.parseInt(len);
        if (fileNumInt + 1 == lenInt) {
            flag = true;
        }

        order.setPicNum(String.valueOf(lenInt));
        this.orderService.updateFigureOrder(order);
        System.out.println("***********************************************************************");
        System.out.println(fileNumInt + "," + flag + "," + lenInt);
        System.out.println("***********************************************************************");

        return success(String.valueOf(flag));
    }
    @PostMapping({"/uploadDropZone"})
    @ResponseBody
    public AjaxResult uploadDropZone(@RequestParam("file") MultipartFile m, HttpServletRequest request) {
        String orderid = request.getHeader("orderid");
        String filePath = Global.getUploadPath() + "picture/" + orderid + "/";
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
        return success(fileName);
    }
    @Log(title="訂單上傳", businessType=BusinessType.OTHER)
    @PostMapping({"/orderPicDropZone"})
    @ResponseBody
    public AjaxResult orderPicDropZone(HttpServletRequest request, String mydatas, String orderid) { System.out.println(mydatas);
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        JSONArray jsonArray = JSONArray.fromObject(mydatas);

        FigureOrder order = new FigureOrder();
        order.setOrderId(orderid);
        List orders = this.orderService.selectFigureOrders(order);
        if ((orders != null) && (orders.size() > 0)) {
            order = (FigureOrder)orders.get(0);
            order.setPicNum(String.valueOf(jsonArray.size()));
            order.setPrint("0");
            order.setUserId(Integer.valueOf(0));
            this.orderService.updateFigureOrder(order);
        } else {
            order = new FigureOrder();
            order.setPicNum(String.valueOf(jsonArray.size()));
            order.setPrint("0");
            order.setUserId(Integer.valueOf(0));
            order.setOrderId(orderid);
            this.orderService.insertFigureOrder(order);
        }

        int oid = order.getId().intValue();

        FigureOrderImage deleteImage = new FigureOrderImage();
        deleteImage.setOrderId(Integer.valueOf(oid));
        List<FigureOrderImage> images = this.orderImageService.selectFigureOrderImageList(deleteImage);
        String ids = "";
        for (FigureOrderImage image : images) {
            ids = ids + image.getId() + ",";
        }
        this.orderImageService.deleteFigureOrderImageByIds(ids);

        FigureOrderImage image = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            image = new FigureOrderImage();
            JSONObject jo = jsonArray.getJSONObject(i);
            image.setOrderId(Integer.valueOf(oid));
            image.setImageUrl(jo.getString("orderFileName"));
            this.orderImageService.insertFigureOrderImage(image);
        }
        return success(); }

    @Log(title="订单上传", businessType=BusinessType.OTHER)
    @PostMapping({"/orderPic"})
    @ResponseBody
    public AjaxResult orderPic(HttpServletRequest request, String mydatas, String picNum) {
        String orderPath = Global.getUploadPath() + "picture/";
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        FigureOrder order = new FigureOrder();
        order.setUserId(Integer.valueOf(0));
        order.setPicNum(picNum);
        order.setPrint("0");
        this.orderService.insertFigureOrder(order);
        int oid = order.getId().intValue();
        JSONArray jsonArray = JSONArray.fromObject(mydatas);
        Object[] jsonObj = jsonArray.toArray();
        FigureOrderImage image = null;
        String fileNames = "";
        List fileNameList = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            image = new FigureOrderImage();
            JSONObject jo = JSONObject.fromObject(jsonObj[i]);
            image.setOrderId(Integer.valueOf(oid));
            image.setImageUrl(jo.getString("fileName"));
            image.setImageNum(jo.getString("num"));
            this.orderImageService.insertFigureOrderImage(image);
            fileNames = fileNames + jo.getString("fileName") + ",";
            fileNameList.add(jo.getString("fileName"));
        }
        String[] os = fileNames.split(",");
        String pdfPath = Global.getUploadPath() + "pdf/";

        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(Integer.valueOf(oid));
        List orderImgList = this.orderImageService.selectFigureOrderImageList(images);

        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = oid + ".zip";

        FileUtils.zipToFiles(orderPath, fileNameList, zipFileName, zipDir);
        return success(String.valueOf(oid));
    }

    @GetMapping({"/order"})
    public String order(HttpServletRequest request, String orderId, ModelMap mmap)
    {
        FigureOrder order = this.orderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));

        mmap.put("dto", order);
        return this.prefix + "/order";
    }
}