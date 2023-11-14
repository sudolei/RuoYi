package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.isky.MyFileUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureLog;
import com.ruoyi.isky.domain.FigureOrder;
import com.ruoyi.isky.domain.FigureOrderImage;
import com.ruoyi.isky.service.IFigureLogService;
import com.ruoyi.isky.service.IFigureOrderImageService;
import com.ruoyi.isky.service.IFigureOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 传图订单 信息操作处理
 *
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureOrder")
public class FigureOrderController extends BaseController {
    private String prefix = "isky/figureOrder";

    @Autowired
    private IFigureOrderService figureOrderService;

    @Autowired
    private IFigureOrderImageService orderImageService;
    @Autowired
    private IFigureLogService logService;


    @RequiresPermissions("isky:figureOrder:view")
    @GetMapping()
    public String figureOrder() {
        return prefix + "/figureOrder";
    }

    @RequiresPermissions("isky:figureOrder:view")
    @GetMapping({"we"})
    public String figureOrderWe() {
        return prefix + "/figureOrderWe";
    }

    @RequiresPermissions("isky:figureOrder:view")
    @GetMapping("delFile")
    public String delFile() {
        return prefix + "/delFile";
    }

    /**
     * 导出传图订单列表
     */
    @PostMapping("/deleteFiles")
    @ResponseBody
    public AjaxResult deleteFiles() {

        return null;
    }


    @RequiresPermissions({"isky:figureOrder:view"})
    @GetMapping({"main"})
    public String main() {
        return prefix + "/figureOrders";
    }

    /**
     * 查询传图订单列表
     */
    @RequiresPermissions("isky:figureOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FigureOrder figureOrder) {
        startPage();
        List<String> dsmList = new ArrayList<String>();
        dsmList.add("1");
        dsmList.add("2");
        dsmList.add("3");
        dsmList.add("4");
        dsmList.add("5");
        dsmList.add("6");
        dsmList.add("7");
        dsmList.add("8");
        dsmList.add("9");
        dsmList.add("10");
        dsmList.add("11");
        dsmList.add("14");
        dsmList.add("15");
        figureOrder.setDsmList(dsmList);
        List<FigureOrder> list = figureOrderService.selectFigureOrderList(figureOrder);
        return getDataTable(list);
    }


    /**
     * 查询传图订单列表
     */
    @RequiresPermissions("isky:figureOrder:list")
    @PostMapping("/listFile")
    @ResponseBody
    public TableDataInfo listFile(String beginTime, String endTime) {
        startPage();
        String path = Global.getUploadPath() + "/origina/";
        ArrayList<FigureOrder> fileNameList = new ArrayList<FigureOrder>();
        if (!StringUtils.isEmpty(beginTime) && !StringUtils.isEmpty(endTime)) {
            try {
                getAllFileName(path, fileNameList, beginTime, endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return getDataTable(fileNameList);
    }


    /**
     * 获取某个文件夹下的所有文件
     *
     * @param fileNameList 存放文件名称的list
     * @param path         文件夹的路径
     * @return
     */
    public static void getAllFileName(String path, ArrayList<FigureOrder> fileNameList, String beginTime, String endTime) throws ParseException {
        //ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date s = sdf.parse(beginTime);
        Date e = sdf.parse(endTime);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                long time = tempList[i].lastModified();
                Date fileTimeDate = new Date(time);
                int ii = fileTimeDate.compareTo(s);
                if (ii > 0) {
                    ii = fileTimeDate.compareTo(e);
                    if (ii < 0) {
                        FigureOrder f = new FigureOrder();
                        f.setFileName(tempList[i].getName());
                        f.setFileTime(sdf.format(fileTimeDate));
                        fileNameList.add(f);
                    }
                }
            }
            if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
                getAllFileName(tempList[i].getAbsolutePath(), fileNameList, beginTime, endTime);
            }
        }
        return;
    }

    /**
     * 查询传图订单列表
     */
    @RequiresPermissions("isky:figureOrder:list")
    @PostMapping("/weList")
    @ResponseBody
    public TableDataInfo weList(FigureOrder figureOrder) {
        startPage();
        List<String> dsmList = new ArrayList<String>();
        dsmList.add("12");
        dsmList.add("13");
        figureOrder.setDsmList(dsmList);
        List<FigureOrder> list = figureOrderService.selectFigureOrderList(figureOrder);
        return getDataTable(list);
    }

    @RequiresPermissions({"isky:figureOrder:list"})
    @GetMapping({"/detail/{orderId}"})
    public String detail(@PathVariable("orderId") Long orderId, ModelMap mmap) {
        mmap.put("orderId", orderId);
        return "/isky/figureOrderImage/orderImages";
    }


    @RequiresPermissions({"isky:figureOrder:list"})
    @PostMapping({"/lists"})
    @ResponseBody
    public TableDataInfo lists(FigureOrder figureOrder) {
        startPage();
        figureOrder.setUserId(Integer.valueOf(0));
        List list = this.figureOrderService.selectFigureOrders(figureOrder);
        return getDataTable(list);
    }


    /**
     * 导出传图订单列表
     */
    @RequiresPermissions("isky:figureOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureOrder figureOrder) {
        List<FigureOrder> list = figureOrderService.selectFigureOrderList(figureOrder);
        ExcelUtil<FigureOrder> util = new ExcelUtil<FigureOrder>(FigureOrder.class);
        return util.exportExcel(list, "figureOrder");
    }

    /**
     * 新增传图订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存传图订单
     */
    @RequiresPermissions("isky:figureOrder:add")
    @Log(title = "传图订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FigureOrder figureOrder) {
        return toAjax(figureOrderService.insertFigureOrder(figureOrder));
    }

    /**
     * 修改传图订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        FigureOrder figureOrder = figureOrderService.selectFigureOrderById(id);
        mmap.put("figureOrder", figureOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存传图订单
     */
    @RequiresPermissions("isky:figureOrder:edit")
    @Log(title = "传图订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FigureOrder figureOrder) {
        return toAjax(figureOrderService.updateFigureOrder(figureOrder));
    }

    /**
     * 删除传图订单
     */
    @RequiresPermissions("isky:figureOrder:remove")
    @Log(title = "传图订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(figureOrderService.deleteFigureOrderByIds(ids));
    }

    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downPdf"})
    public void downPdf(String orderId, HttpServletRequest request, HttpServletResponse response) {
        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        order.setPrint("1");
        this.figureOrderService.updateFigureOrder(order);

        String pdfPath = Global.getUploadPath() + "pdf/";
        String fileName = pdfPath + orderId + ".pdf";

        File f = new File(fileName);
        if (!f.exists()) {
            fileName = "D:\\jeesite\\userfiles\\pdf/" + order.getOrderId() + ".pdf";
        }
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    @Log(title = "批量压缩包下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downZips"})
    public void downZips(String ids, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("mmss");
        String[] idsArray = Convert.toStrArray(ids);
        String zipDir = Global.getUploadPath() + "zip/";
        List<String> zipList = new ArrayList<String>();
        for (String orderId : idsArray) {
            FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
            order.setPrint("1");
            this.figureOrderService.updateFigureOrder(order);
            String picturePath = Global.getUploadPath() + "picture/" + order.getOrderId() + "/";
            FigureOrderImage images = new FigureOrderImage();
            images.setOrderId(Integer.valueOf(Integer.parseInt(orderId)));
            List<FigureOrderImage> orderImgList = this.orderImageService.selectFigureOrderImageList(images);
            List fileNameList = new ArrayList();
            for (FigureOrderImage image : orderImgList) {
                fileNameList.add(image.getImageUrl());
            }
            File f = new File(zipDir);
            if (!f.exists()) {
                f.mkdirs();
            }
            String zipFileName = order.getOrderId() + ".zip";

            File zipFile = new File(picturePath + zipFileName);
            if (zipFile.exists()) {
                zipFile.delete();
            }
            FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);
            zipList.add(zipFileName);
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "") + ".zip";
        String pdfPath = Global.getUploadPath() + "zip/";
        FileUtils.zipToFiles(pdfPath, zipList, uuid, zipDir);
        String fileName = pdfPath + uuid;
        MyFileUtils.downFile(new File(fileName), request, response);


    }

    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downZip"})
    public void downZip(String orderId, HttpServletRequest request, HttpServletResponse response) {

        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        String figureOrderId  = order.getOrderId();
        order.setPrint("1");
        this.figureOrderService.updateFigureOrder(order);

        String picturePath = Global.getUploadPath() + "picture/" + order.getOrderId() + "/";
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(Integer.valueOf(Integer.parseInt(orderId)));
        List<FigureOrderImage> orderImgList = this.orderImageService.selectFigureOrderImageList(images);
        List fileNameList = new ArrayList();
        for (FigureOrderImage image : orderImgList) {
            fileNameList.add(image.getImageUrl());
        }
        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = figureOrderId + ".zip";

        File zipFile = new File(picturePath + zipFileName);
        if (zipFile.exists()) {
            zipFile.delete();
        }

        FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);

        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + figureOrderId + ".zip";
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downPokerZip"})
    public void downPokerZip(String orderId, HttpServletRequest request, HttpServletResponse response) {
        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        order.setPrint("1");
        this.figureOrderService.updateFigureOrder(order);

//        String picturePath = Global.getUploadPath() + "picture/" + order.getOrderId() + "/";
        String picturePath = Global.getUploadPath() + "rs/";
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(Integer.valueOf(Integer.parseInt(orderId)));
        List<FigureOrderImage> orderImgList = this.orderImageService.selectFigureOrderImageList(images);
        List fileNameList = new ArrayList();
        for (FigureOrderImage image : orderImgList) {
            fileNameList.add(image.getImageUrl());
        }
        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = orderId + ".zip";

        File zipFile = new File(picturePath + zipFileName);
        if (zipFile.exists()) {
            zipFile.delete();
        }

        FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);

        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + orderId + ".zip";
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downYtZip"})
    public void downYtZip(String orderId, HttpServletRequest request, HttpServletResponse response) {
        String picturePath = Global.getUploadPath() + "origina/";

        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.parseInt(orderId));
        String bno = order.getBno();
        if (StringUtils.isEmpty(bno)) {
            return;
        }
        FigureLog log = new FigureLog();
        log.setBno(bno);

        List<FigureLog> list = logService.selectFigureLogList(log);
        List fileNameList = new ArrayList();
        for (FigureLog logs : list) {
            fileNameList.add(logs.getFileName());
        }
        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = orderId + bno + ".zip";

        File zipFile = new File(picturePath + zipFileName);
        if (zipFile.exists()) {
            zipFile.delete();
        }

        FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);

        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + orderId + bno + ".zip";
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downCpZip"})
    public void downCpZip(String orderId, HttpServletRequest request, HttpServletResponse response) {
        String picturePath = Global.getUploadPath() + "zrs/";

        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.parseInt(orderId));
        String bno = order.getBno();
        if (StringUtils.isEmpty(bno)) {
            return;
        }
        FigureLog log = new FigureLog();
        log.setBno(bno);

        List<FigureLog> list = logService.selectFigureLogList(log);
        List fileNameList = new ArrayList();
        for (FigureLog logs : list) {
            fileNameList.add(logs.getFileName());
        }
        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = "z" + orderId + ".zip";

        File zipFile = new File(picturePath + zipFileName);
        if (zipFile.exists()) {
            zipFile.delete();
        }

        FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);

        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + "z" + orderId + ".zip";
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downCpcsZip"})
    public void downCpcsZip(String orderId, HttpServletRequest request, HttpServletResponse response) {
        String picturePath = Global.getUploadPath() + "rs/";

        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.parseInt(orderId));
        String bno = order.getBno();
        if (StringUtils.isEmpty(bno)) {
            return;
        }
        FigureLog log = new FigureLog();
        log.setBno(bno);

        List<FigureLog> list = logService.selectFigureLogList(log);
        List fileNameList = new ArrayList();
        for (FigureLog logs : list) {
            fileNameList.add(logs.getFileName());
        }
        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = "zcp" + orderId + ".zip";

        File zipFile = new File(picturePath + zipFileName);
        if (zipFile.exists()) {
            zipFile.delete();
        }

        FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);

        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + "zcp" + orderId + ".zip";
        MyFileUtils.downFile(new File(fileName), request, response);
    }

    @Log(title = "模板下载验证", businessType = BusinessType.OTHER)
    @PostMapping({"/downPdfValidate"})
    @ResponseBody
    public AjaxResult downPdfValidate(String orderId, HttpServletRequest request, HttpServletResponse response) {
        String pdfPath = Global.getUploadPath() + "pdf/";
        String fileName = pdfPath + orderId + ".pdf";
        File file = new File(fileName);
        String error = null;
        if ((file != null) && (file.exists())) {
            if (file.isFile()) {
                if (file.length() <= 0L) {
                    error = "该文件是一个空文件。";
                }
                if (!file.canRead())
                    error = "该文件没有读取权限。";
            } else {
                error = "该文件是一个文件夹。";
            }
            file.delete();
        } else error = "文件已丢失或不存在！";
        newPdf(Integer.parseInt(orderId));
        AjaxResult ajax = AjaxResult.success();
        if (error != null) {
            ajax.put("isPdf", false);
            return ajax;
        }
        ajax.put("isPdf", true);


        return ajax;
    }

    @Log(title = "模板下载验证", businessType = BusinessType.OTHER)
    @PostMapping({"/downZipValidate"})
    @ResponseBody
    public AjaxResult downZipValidate(String orderId, HttpServletRequest request, HttpServletResponse response) {
        FigureOrder order = this.figureOrderService.selectFigureOrderById(Integer.valueOf(Integer.parseInt(orderId)));
        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + orderId + ".zip";
        File file = new File(fileName);
        String error = null;
        if ((file != null) && (file.exists())) {
            if (file.isFile()) {
                if (file.length() <= 0L) {
                    error = "该文件是一个空文件。";
                }
                if (!file.canRead())
                    error = "该文件没有读取权限。";
            } else {
                error = "该文件是一个文件夹。";
            }
        } else error = "文件已丢失或不存在！";
        AjaxResult ajax = AjaxResult.success();
        if (error != null) {
            ajax.put("isPdf", false);
            return ajax;
        }
        ajax.put("isPdf", true);
        return ajax;
    }

    @Log(title = "手工生成PDF", businessType = BusinessType.OTHER)
    @PostMapping({"/createPdf"})
    @ResponseBody
    public AjaxResult createPdf(Integer oid) {
        FigureOrder figureOrder = figureOrderService.selectFigureOrderById(oid);
        String dsm = figureOrder.getDsm();
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(oid);
        List<FigureOrderImage> orderImgList = orderImageService.selectFigureOrderImageList(images);
        String pdfPath = Global.getUploadPath() + "pdf/";
        String orderPath = Global.getUploadPath() + "rs/";
        String str = "";
        for (FigureOrderImage order : orderImgList) {
            str = str + order.getImageUrl() + ",";
        }
        String[] os = str.split(",");

        if (dsm.equals("1") || dsm.equals("2")) {
            ToPdf.t(pdfPath, oid + ".pdf", orderPath, os, orderImgList);
        } else {
            ToPdf.tBook(pdfPath, oid + ".pdf", orderPath, os, orderImgList);
        }
        return success();
    }


    public void newPdf(Integer oid) {
        FigureOrder figureOrder = figureOrderService.selectFigureOrderById(oid);
        String dsm = figureOrder.getDsm();
        String bno = figureOrder.getBno();
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(oid);
        List<FigureOrderImage> orderImgList = orderImageService.selectFigureOrderImageList(images);
        System.out.println("--------分类------" + dsm);
        if (dsm.equals("6") || dsm.equals("8")) {
            System.out.println("--------数量------" + orderImgList.size());
            if (orderImgList.size() != 72) {
                orderImgList = new ArrayList<FigureOrderImage>();
                FigureLog log = new FigureLog();
                log.setBno(bno);
                List<FigureLog> li = logService.selectFigureLogList(log);
                for (FigureLog log1 : li) {
                    FigureOrderImage i = new FigureOrderImage();
                    i.setImageUrl(log1.getFileName());
                    orderImgList.add(i);
                }
            }
        } else if (dsm.equals("3")) {
            if (orderImgList.size() != 54) {
                orderImgList = new ArrayList<FigureOrderImage>();
                FigureLog log = new FigureLog();
                log.setBno(bno);
                List<FigureLog> li = logService.selectFigureLogList(log);
                for (FigureLog log1 : li) {
                    FigureOrderImage i = new FigureOrderImage();
                    i.setImageUrl(log1.getFileName());
                    orderImgList.add(i);
                }
            }
        }
        String pdfPath = Global.getUploadPath() + "pdf/";
        String orderPath = Global.getUploadPath() + "rs/";
        String str = "";
        for (FigureOrderImage order : orderImgList) {
            str = str + order.getImageUrl() + ",";
        }
        String[] os = str.split(",");

        if (dsm.equals("1") || dsm.equals("2")) {
            ToPdf.t(pdfPath, oid + ".pdf", orderPath, os, orderImgList);
        } else {
            ToPdf.tBook(pdfPath, oid + ".pdf", orderPath, os, orderImgList);
        }
    }


    @Log(title = "PDF下载新", businessType = BusinessType.OTHER)
    @PostMapping({"/createDownPdf"})
    @ResponseBody
    public AjaxResult createDownPdf(Integer oid) {
        FigureOrder figureOrder = figureOrderService.selectFigureOrderById(oid);
        String dsm = figureOrder.getDsm();
        FigureOrderImage images = new FigureOrderImage();
        images.setOrderId(oid);
        List<FigureOrderImage> orderImgList = orderImageService.selectFigureOrderImageList(images);
        String pdfPath = Global.getUploadPath() + "pdf/";
        String orderPath = Global.getUploadPath() + "rs/";
        String str = "";
        for (FigureOrderImage order : orderImgList) {
            str = str + order.getImageUrl() + ",";
        }
        String[] os = str.split(",");

        if (dsm.equals("1") || dsm.equals("2")) {
            ToPdf.t(pdfPath, oid + ".pdf", orderPath, os, orderImgList);
        } else {
            ToPdf.tBook(pdfPath, oid + ".pdf", orderPath, os, orderImgList);
        }
        return success();
    }


    /**
     * 删除传图订单
     */
    @RequiresPermissions("isky:figureOrder:remove")
    @Log(title = "传图订单", businessType = BusinessType.DELETE)
    @PostMapping("/removeFile")
    @ResponseBody
    public AjaxResult removeFile(String ids) {
        String idArray[] = Convert.toStrArray(ids);
        for (String str : idArray) {
            //查询订单图片数据
            FigureOrderImage image = new FigureOrderImage();
            image.setOrderId(Integer.parseInt(str));
            List<FigureOrderImage> list = orderImageService.selectFigureOrderImageList(image);

            for (FigureOrderImage img : list) {

                String fp[] = {"cut/", "edit/", "jpg/", "origina/", "zcut/", "zedit/", "zoom/", "zzoom/", "zrs/"};
                for (String s : fp) {
                    String filePath = Global.getUploadPath() + s + img.getImageUrl();
                    System.out.println(filePath);
                    File f = new File(filePath);
                    if (f.exists()) {
                        f.delete();
                    }
                }
            }
        }
        return toAjax(1);
    }

    /**
     * 删除传图订单
     */
    @RequiresPermissions("isky:figureOrder:remove")
    @Log(title = "传图订单", businessType = BusinessType.DELETE)
    @PostMapping("/removeFiles")
    @ResponseBody
    public AjaxResult removeFiles(String fileNames) {
        String idArray[] = Convert.toStrArray(fileNames);
        for (String str : idArray) {
            String fp[] = {"cut/", "edit/", "jpg/", "origina/", "zcut/", "zedit/", "zoom/", "zzoom/", "zrs/"};
            for (String s : fp) {
                String filePath = Global.getUploadPath() + s + str;
                System.out.println(filePath);
                File f = new File(filePath);
                if (f.exists()) {
                    f.delete();
                }
            }
        }
        return toAjax(1);
    }
}
