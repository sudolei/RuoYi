package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.IskyPhoneBrand;
import com.ruoyi.isky.service.IIskyPhoneBrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 品牌 信息操作处理
 *
 * @author sunlei
 * @date 2019-08-21
 */
@Controller
@RequestMapping("/isky/iskyPhoneBrand")
public class IskyPhoneBrandController extends BaseController {
    private String prefix = "isky/iskyPhoneBrand";

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IIskyPhoneBrandService iskyPhoneBrandService;

    @RequiresPermissions("isky:iskyPhoneBrand:view")
    @GetMapping()
    public String iskyPhoneBrand() {
        return prefix + "/iskyPhoneBrand";
    }

    /**
     * 查询品牌列表
     */
    @RequiresPermissions("isky:iskyPhoneBrand:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(IskyPhoneBrand iskyPhoneBrand) {
        startPage();
        List<IskyPhoneBrand> list = iskyPhoneBrandService.selectIskyPhoneBrandList(iskyPhoneBrand);
        return getDataTable(list);
    }


    /**
     * 导出品牌列表
     */
    @RequiresPermissions("isky:iskyPhoneBrand:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyPhoneBrand iskyPhoneBrand) {
        List<IskyPhoneBrand> list = iskyPhoneBrandService.selectIskyPhoneBrandList(iskyPhoneBrand);
        ExcelUtil<IskyPhoneBrand> util = new ExcelUtil<IskyPhoneBrand>(IskyPhoneBrand.class);
        return util.exportExcel(list, "iskyPhoneBrand");
    }

    /**
     * 新增品牌
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 新增保存品牌
     */
    @RequiresPermissions("isky:iskyPhoneBrand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(IskyPhoneBrand iskyPhoneBrand) {
        return toAjax(iskyPhoneBrandService.insertIskyPhoneBrand(iskyPhoneBrand));
    }

    /**
     * 修改品牌
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        IskyPhoneBrand iskyPhoneBrand = iskyPhoneBrandService.selectIskyPhoneBrandById(id);
        mmap.put("iskyPhoneBrand", iskyPhoneBrand);
        return prefix + "/edit";
    }

    /**
     * 修改保存品牌
     */
    @RequiresPermissions("isky:iskyPhoneBrand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(IskyPhoneBrand iskyPhoneBrand) {
        return toAjax(iskyPhoneBrandService.updateIskyPhoneBrand(iskyPhoneBrand));
    }

    /**
     * 删除品牌
     */
    @RequiresPermissions("isky:iskyPhoneBrand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(iskyPhoneBrandService.deleteIskyPhoneBrandByIds(ids));
    }


    @Log(title = "封面上传", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadCover"})
    @ResponseBody
    public AjaxResult uploadCover(@RequestParam("coverImg") MultipartFile file) {
        try {
            String dir = "brand/";
            String filePath = Global.getUploadPath() + dir;
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + "/profile/upload/" + dir + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
