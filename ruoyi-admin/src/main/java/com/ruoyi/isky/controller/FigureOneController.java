package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.isky.IskyUtil;
import com.ruoyi.common.isky.QrcodeUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureOne;
import com.ruoyi.isky.service.IFigureOneService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 一次性二维码 信息操作处理
 *
 * @author sunlei
 * @date 2019-08-03
 */
@Controller
@RequestMapping("/isky/figureOne")
public class FigureOneController extends BaseController {
    private String prefix = "isky/figureOne";

    @Autowired
    private IFigureOneService figureOneService;

    @RequiresPermissions("isky:figureOne:view")
    @GetMapping()
    public String figureOne() {
        return prefix + "/figureOne";
    }

    /**
     * 查询一次性二维码列表
     */
    @RequiresPermissions("isky:figureOne:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FigureOne figureOne) {
        startPage();
        List<FigureOne> list = figureOneService.selectFigureOneList(figureOne);
        return getDataTable(list);
    }


    /**
     * 导出一次性二维码列表
     */
    @RequiresPermissions("isky:figureOne:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureOne figureOne) {
        List<FigureOne> list = figureOneService.selectFigureOneList(figureOne);
        ExcelUtil<FigureOne> util = new ExcelUtil<FigureOne>(FigureOne.class);
        return util.exportExcel(list, "figureOne");
    }

    /**
     * 新增一次性二维码
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存一次性二维码
     */
    @RequiresPermissions("isky:figureOne:add")
    @Log(title = "一次性二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FigureOne figureOne) {
        Integer num = figureOne.getNum();
        String uploadPath = Global.getUploadPath() + "qrcode/";
        File pathFile = new File(uploadPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        String type = figureOne.getType();
        Integer typeid = Integer.parseInt(type);
        String dir = "begin/";
//        switch (typeid) {
//            case 1:
//                dir = "mxph/";
//                break;
//            case 2:
//                dir = "mxps/";
//                break;
//            case 3:
//                dir = "puke/";
//                break;
//            case 4:
//                dir = "tl/";
//                break;
//            case 7:
//                dir = "calendar/";
//                break;
//            case 5:
//                dir = "notebook/";
//                break;
//            case 6:
//                dir = "lomos/";
//                break;
//            case 8:
//                dir = "lomoh/";
//        }
        for (int i = 0; i < num; i++) {
            String path = "http://10.168.1.240/" + dir;
//        String path = "http://yishuyin.com/qrcode/";
            if (num == null) {
                num = 1;
            }
            String uuid = IskyUtil.uuid();
            String fileName = uuid + ".jpg";
            try {
                QrcodeUtil.createQrCode(path + uuid, uploadPath, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }


            FigureOne one = new FigureOne();
            one.setImgUrl(dir);
            one.setType(type);
            one.setIsUse("0");
            one.setCodeUuid(uuid);
            one.setCreateTime(new Date());
            one.setQrcode("qrcode/" + fileName);
            figureOneService.insertFigureOne(one);
        }
        return toAjax(1);
    }

    /**
     * 修改一次性二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        FigureOne figureOne = figureOneService.selectFigureOneById(id);
        mmap.put("figureOne", figureOne);
        return prefix + "/edit";
    }

    /**
     * 修改保存一次性二维码
     */
    @RequiresPermissions("isky:figureOne:edit")
    @Log(title = "一次性二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FigureOne figureOne) {
        return toAjax(figureOneService.updateFigureOne(figureOne));
    }

    /**
     * 删除一次性二维码
     */
    @RequiresPermissions("isky:figureOne:remove")
    @Log(title = "一次性二维码", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(figureOneService.deleteFigureOneByIds(ids));
    }

}
