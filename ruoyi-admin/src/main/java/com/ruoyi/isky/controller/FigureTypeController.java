package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.isky.Block;
import com.ruoyi.common.isky.ImageTools;
import com.ruoyi.common.isky.Transparent;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ThumbUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureImageProperty;
import com.ruoyi.isky.domain.FigureImages;
import com.ruoyi.isky.domain.FigureType;
import com.ruoyi.isky.service.IFigureImagePropertyService;
import com.ruoyi.isky.service.IFigureImagesService;
import com.ruoyi.isky.service.IFigureTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 模板分类 信息操作处理
 *
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureType")
public class FigureTypeController extends BaseController {
    private String prefix = "isky/figureType";
    public static final String UPLOAD_COVER_PATH = "/profile/upload/cover";
    public static final String UPLOAD_PATH = "/profile/upload/template";

    @Autowired
    private IFigureTypeService figureTypeService;


    @Autowired
    private IFigureImagesService imagesService;

    @Autowired
    private IFigureImagePropertyService propertyService;


    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("isky:figureType:view")
    @GetMapping()
    public String figureType() {
        return prefix + "/figureType";
    }

    /**
     * 查询模板分类列表
     */
    @RequiresPermissions("isky:figureType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FigureType figureType) {
        startPage();
        List<FigureType> list = figureTypeService.selectFigureTypeList(figureType);
        return getDataTable(list);
    }


    /**
     * 导出模板分类列表
     */
    @RequiresPermissions("isky:figureType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureType figureType) {
        List<FigureType> list = figureTypeService.selectFigureTypeList(figureType);
        ExcelUtil<FigureType> util = new ExcelUtil<FigureType>(FigureType.class);
        return util.exportExcel(list, "figureType");
    }

    /**
     * 新增模板分类
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }


    /**
     * 新增保存模板分类
     */
    @RequiresPermissions("isky:figureType:add")
    @Log(title = "模板分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FigureType figureType, String templatearra) {
        String typeId = figureType.getTypeId();
        String isFull = figureType.getIsFull();
        String dir = "mxp/";
        switch (typeId) {
            case "1":
                dir = "mxp/";
                break;
            case "2":
                dir = "mxp/";
                break;
            case "3":
                dir = "poker/";
                break;
            case "4":
                dir = "calendar/";
                break;
            case "7":
                dir = "calendar/";
                break;
            case "5":
                dir = "notebook/";
                break;
            case "6":
                dir = "lomo/";
                break;
            case "8":
                dir = "lomo/";
        }
        String coverZoomPath = Global.getUploadCoverPath() + "zoom/" + dir;
        String accessPath = "/profile/upload/cover/zoom/" + dir;

        String fileName = figureType.getImgUrl();
        String url = Global.getUploadCoverPath() + dir + fileName;
        String imgResult = ThumbUtil.zoomGdccPic(fileName, url, 420, 284, coverZoomPath);
        figureType.setImgSamllUrl(accessPath + fileName);
        figureType.setAccessUrl(".." + accessPath + fileName);
        figureType.setImgUrl(dir + fileName);
        figureType.setIsFull(isFull);
        int result = this.figureTypeService.insertFigureType(figureType);
        int id = figureType.getId().intValue();
        //不是满版
        if (isFull.equals("0")) {
            String[] strArr = templatearra.split(",");
            FigureImages imgs = null;
            int order = 0;
            String filePath = Global.getUploadPath();
            String folder = "template/" + dir;
            for (String str : strArr) {
                if (!StringUtils.isEmpty(str)) {
                    String imgurl = Global.getUploadPath() + folder + str;
                    String imgZoomUrl = Global.getUploadPath() + folder + "zoom/" + str;
                    File f = new File(imgurl);
                    File zoomF = new File(imgZoomUrl);
                    int width = ImageTools.getImgWidth(f);
                    int zoomWidth = ImageTools.getImgWidth(zoomF);
                    float zooms = (float) zoomWidth / width;
                    imgs = new FigureImages();
                    imgs.setTpId(String.valueOf(id));
                    imgs.setImageUrl(dir + str);
                    imgs.setImageZoomUrl(dir + "zoom/" + str);
                    imgs.setImageWidth(String.valueOf(width));
                    imgs.setImageZoomWidth(String.valueOf(zoomWidth));
                    imgs.setZooms(String.valueOf(zooms));
                    imgs.setSort(Integer.valueOf(order));
                    this.imagesService.insertFigureImages(imgs);
                    order++;
                }
            }
            Thread thread = new Thread(new FigureTypeController.photoRunble(this, id));
            thread.start();
        }
        return toAjax(result);
    }


    class photoRunble implements Runnable {
        private int id;

        public photoRunble(FigureTypeController this$0, int id) {
            this.id = id;
        }

        public void run() {
            FigureImages images = new FigureImages();
            images.setTpId(String.valueOf(this.id));
            List<FigureImages> list = imagesService.selectFigureImagesList(images);
            String filePath = Global.getUploadPath() + "template/";

            if (!StringUtils.isEmpty(list))
                for (FigureImages img : list) {
                    String templateUrl = filePath + img.getImageUrl();
                    String templateZoomUrl = filePath + img.getImageZoomUrl();
                    int imageId = img.getId().intValue();
                    try {
                        ArrayList blockList = Transparent.getPhotoProperty(templateUrl);
                        if (!StringUtils.isEmpty(blockList)) {
                            int orderNum = 0;
                            FigureImageProperty property = null;
                            for (Iterator localIterator2 = blockList.iterator(); localIterator2.hasNext(); ) {
                                Block b = (Block) localIterator2.next();
                                String width = String.valueOf(b.getXend() - b.getXstart() + 1);
                                String height = String.valueOf(b.getYend() - b.getYstart() + 1);
                                String xsite = String.valueOf((b.getXend() - b.getXstart() + 1) / 2 + b.getXstart());
                                String ysite = String.valueOf((b.getYend() - b.getYstart() + 1) / 2 + b.getYstart());
                                property = new FigureImageProperty();
                                property.setImageId(Integer.valueOf(imageId));
                                property.setXSite(xsite);
                                property.setYSite(ysite);
                                property.setHeight(height);
                                property.setWidth(width);
                                property.setIsround("0");
                                orderNum++;
                                property.setOrderNum(Integer.valueOf(orderNum));
                                propertyService.insertFigureImageProperty(property);
                            }
                            img.setAreaNum(Integer.valueOf(orderNum));
                            imagesService.updateFigureImages(img);
                        }

                        ArrayList<Block> blockZoomList = Transparent.getPhotoProperty(templateZoomUrl);
                        if (!StringUtils.isEmpty(blockZoomList)) {
                            int orderNum = 1;
                            FigureImageProperty property = null;
                            for (Block b : blockZoomList) {
                                String width = String.valueOf(b.getXend() - b.getXstart() + 1);
                                String height = String.valueOf(b.getYend() - b.getYstart() + 1);
                                String xsite = String.valueOf((b.getXend() - b.getXstart() + 1) / 2 + b.getXstart());
                                String ysite = String.valueOf((b.getYend() - b.getYstart() + 1) / 2 + b.getYstart());

                                FigureImageProperty queryProperty = new FigureImageProperty();
                                queryProperty.setImageId(Integer.valueOf(imageId));
                                queryProperty.setOrderNum(Integer.valueOf(orderNum));
                                List queryList = propertyService.selectFigureImagePropertyList(queryProperty);
                                property = (FigureImageProperty) queryList.get(0);
                                property.setZoomXsite(xsite);
                                property.setZoomYsite(ysite);
                                property.setZoomWidth(width);
                                property.setZoomHeight(height);
                                property.setIsround("0");
                                propertyService.updateFigureImageProperty(property);
                                orderNum++;
                            }
                        }
                    } catch (IOException e) {
                        Block b;
                        int orderNum;
                        FigureImageProperty property;
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * 修改模板分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        FigureType figureType = figureTypeService.selectFigureTypeById(id);
        mmap.put("figureType", figureType);
        return prefix + "/edit";
    }

    /**
     * 修改保存模板分类
     */
    @RequiresPermissions("isky:figureType:edit")
    @Log(title = "模板分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FigureType figureType) {
        return toAjax(figureTypeService.updateFigureType(figureType));
    }

    /**
     * 删除模板分类
     */
    @RequiresPermissions("isky:figureType:remove")
    @Log(title = "模板分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        String[] idarr = Convert.toStrArray(ids);
        for (String str : idarr) {
            FigureImages imgs = new FigureImages();
            imgs.setTpId(str);
            List<FigureImages> imgList = imagesService.selectFigureImagesList(imgs);
            String delIds = "";
            for (FigureImages fi : imgList) {
                delIds = delIds + fi.getId() + ",";
            }
            imagesService.deleteFigureImagesByIds(delIds);
        }
        return toAjax(figureTypeService.deleteFigureTypeByIds(ids));
    }


    @Log(title = "封面上传", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadCover"})
    @ResponseBody
    public AjaxResult uploadCover(@RequestParam("coverImg") MultipartFile file, int typeid) {
        try {
            String dir = "mxp/";
            switch (typeid) {
                case 1:
                    dir = "mxp/";
                    break;
                case 2:
                    dir = "mxp/";
                    break;
                case 3:
                    dir = "poker/";
                    break;
                case 4:
                    dir = "calendar/";
                    break;
                case 7:
                    dir = "calendar/";
                    break;
                case 5:
                    dir = "notebook/";
                    break;
                case 6:
                    dir = "lomo/";
                    break;
                case 8:
                    dir = "lomo/";
            }

            String filePath = Global.getUploadCoverPath() + dir;

            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + "/profile/upload/cover" + dir + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @Log(title = "模板上传", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadTemplate"})
    @ResponseBody
    public AjaxResult uploadTemplate(@RequestParam("template") MultipartFile file, int typeid) {
        try {
            String dir = "mxp/";
            float zwidth = 400.0F;
            switch (typeid) {
                case 1:
                    dir = "mxp/";
                    break;
                case 2:
                    dir = "mxp/";
                    zwidth = 300.0F;
                    break;
                case 3:
                    dir = "poker/";
                    zwidth = 300.0F;
                    break;
                case 4:
                    dir = "calendar/";
                    break;
                case 7:
                    dir = "calendar/";
                    break;
                case 5:
                    dir = "notebook/";
                    break;
                case 6:
                    dir = "lomo/";
                    zwidth = 300.0F;
                    break;
                case 8:
                    dir = "lomo/";
                    zwidth = 300.0F;
            }

            String filePath = Global.getUploadPath();

            String folder = "template/" + dir;
            filePath = filePath + folder;
            String strs[] = {"png"};
            String fileName = FileUploadUtils.upload(filePath, file, strs);
            String url = serverConfig.getUrl() + "/profile/upload/template" + folder + fileName;

            int width = ImageTools.getImgWidth(new File(filePath + fileName));
            float zoom = zwidth / width;
            String templateZoomPath = Global.getUploadPath() + folder + "zoom/";
            ThumbUtil.zoomPic(fileName, filePath + fileName, zoom, templateZoomPath);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
