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
import com.ruoyi.isky.domain.FigureQrcode;
import com.ruoyi.isky.service.IFigureQrcodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 二维码 信息操作处理
 *
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureQrcode")
public class FigureQrcodeController extends BaseController {
    private String prefix = "isky/figureQrcode";

    @Autowired
    private IFigureQrcodeService figureQrcodeService;

    @RequiresPermissions("isky:figureQrcode:view")
    @GetMapping()
    public String figureQrcode() {
        return prefix + "/figureQrcode";
    }


    /**
     * 查询二维码列表
     */
    @RequiresPermissions("isky:figureQrcode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FigureQrcode figureQrcode) {
        startPage();
        List<FigureQrcode> list = figureQrcodeService.selectFigureQrcodeList(figureQrcode);
        return getDataTable(list);
    }


    /**
     * 导出二维码列表
     */
    @RequiresPermissions("isky:figureQrcode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureQrcode figureQrcode) {
        List<FigureQrcode> list = figureQrcodeService.selectFigureQrcodeList(figureQrcode);
        ExcelUtil<FigureQrcode> util = new ExcelUtil<FigureQrcode>(FigureQrcode.class);
        return util.exportExcel(list, "figureQrcode");
    }

    /**
     * 新增二维码
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存二维码
     */
    @RequiresPermissions("isky:figureQrcode:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FigureQrcode figureQrcode, HttpServletRequest request) {
//        String uploadPath = Global.getUploadPath() + "qrcode/";
//        File pathFile = new File(uploadPath);
//        if (!pathFile.exists()) {
//            pathFile.mkdirs();
//        }
        String path = "http://yishuyin.com/qrcode/";
//        String path = "http://10.168.1.221/qrcode/";
        int num = figureQrcode.getNum();
        int result = 0;
        for (int i = 0; i < num; i++) {
            result = createQrcode(figureQrcode);
        }
        return toAjax(result);
    }

    public int createQrcode(FigureQrcode figureQrcode){
        String uploadPath = Global.getUploadPath() + "qrcode/";
        File pathFile = new File(uploadPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        String userCode = figureQrcode.getCodeUser();
//        String path = "http://yishuyin.com/qrcode/";
        String path = "http://112.36.142.1/qrcode/";


        String uuid = IskyUtil.uuid();
        String fileName = uuid + ".jpg";
        try {
            QrcodeUtil.createQrCode(path + uuid, uploadPath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        figureQrcode = new FigureQrcode();
        figureQrcode.setCodeType("0");
        figureQrcode.setIsUse("0");
        figureQrcode.setIsPrint("0");
        figureQrcode.setCodeStr(uuid);
        figureQrcode.setCodeUser(userCode);
        figureQrcode.setCodeUrl(path + uuid);
        figureQrcode.setCodeImg("qrcode/" + fileName);
        figureQrcode.setCreateTime(new Date());
        int result = figureQrcodeService.insertFigureQrcode(figureQrcode);

        return result;
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        FigureQrcode figureQrcode = figureQrcodeService.selectFigureQrcodeById(id);
        mmap.put("figureQrcode", figureQrcode);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("isky:figureQrcode:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FigureQrcode figureQrcode) {
        return toAjax(figureQrcodeService.updateFigureQrcode(figureQrcode));
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("isky:figureQrcode:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(figureQrcodeService.deleteFigureQrcodeByIds(ids));
    }




}
