package com.ruoyi.web.controller.isky;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.isky.domain.WarehouseCk;
import com.ruoyi.isky.service.IWarehouseCkService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 仓库
 * 信息操作处理
 * @author sunlei
 * @date 2019-11-07
 */
@Controller
@RequestMapping("/isky/warehouseCk")
public class WarehouseCkController extends BaseController {
    private String prefix = "isky/warehouseCk";

    @Autowired
    private IWarehouseCkService warehouseCkService;

    @RequiresPermissions("isky:warehouseCk:view")
    @GetMapping()
    public String warehouseCk() {
        return prefix + "/warehouseCk";
    }

    /**
     * 查询仓库
     * 列表
     */
    @RequiresPermissions("isky:warehouseCk:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WarehouseCk warehouseCk) {
        startPage();
        List<WarehouseCk> list = warehouseCkService.selectWarehouseCkList(warehouseCk);
        return getDataTable(list);
    }


    /**
     * 导出仓库
     * 列表
     */
    @RequiresPermissions("isky:warehouseCk:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseCk warehouseCk) {
        List<WarehouseCk> list = warehouseCkService.selectWarehouseCkList(warehouseCk);
        ExcelUtil<WarehouseCk> util = new ExcelUtil<WarehouseCk>(WarehouseCk.class);
        return util.exportExcel(list, "warehouseCk");
    }

    /**
     * 新增仓库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库
     */
    @RequiresPermissions("isky:warehouseCk:add")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WarehouseCk warehouseCk) {
        return toAjax(warehouseCkService.insertWarehouseCk(warehouseCk));
    }

    /**
     * 修改仓库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        WarehouseCk warehouseCk = warehouseCkService.selectWarehouseCkById(id);
        mmap.put("warehouseCk", warehouseCk);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库
     */
    @RequiresPermissions("isky:warehouseCk:edit")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WarehouseCk warehouseCk) {
        return toAjax(warehouseCkService.updateWarehouseCk(warehouseCk));
    }

    /**
     * 删除仓库
     */
    @RequiresPermissions("isky:warehouseCk:remove")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(warehouseCkService.deleteWarehouseCkByIds(ids));
    }

}
