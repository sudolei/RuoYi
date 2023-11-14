package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.WarehouseGoods;
import com.ruoyi.isky.domain.WarehouseLog;
import com.ruoyi.isky.service.IWarehouseGoodsService;
import com.ruoyi.isky.service.IWarehouseLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存日志 信息操作处理
 *
 * @author sunlei
 * @date 2019-11-06
 */
@Controller
@RequestMapping("/isky/warehouseLog")
public class WarehouseLogController extends BaseController {
    private String prefix = "isky/warehouseLog";

    @Autowired
    private IWarehouseLogService warehouseLogService;
    @Autowired
    private IWarehouseGoodsService warehouseGoodsService;

    @RequiresPermissions("isky:warehouseLog:view")
    @GetMapping()
    public String warehouseLog() {
        return prefix + "/warehouseLog";
    }

    /**
     * 查询库存日志列表
     */
    @RequiresPermissions("isky:warehouseLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WarehouseLog warehouseLog) {
        startPage();
        List<WarehouseLog> list = warehouseLogService.selectWarehouseLogList(warehouseLog);
        for (WarehouseLog log : list) {
            if(log.getGoodId()==null){
                continue;
            }
            WarehouseGoods goods = warehouseGoodsService.selectWarehouseGoodsById(log.getGoodId());
            log.setGoodsName(goods.getGoodsName());
        }
        return getDataTable(list);
    }


    /**
     * 导出库存日志列表
     */
    @RequiresPermissions("isky:warehouseLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseLog warehouseLog) {
        List<WarehouseLog> list = warehouseLogService.selectWarehouseLogList(warehouseLog);
        ExcelUtil<WarehouseLog> util = new ExcelUtil<WarehouseLog>(WarehouseLog.class);
        return util.exportExcel(list, "warehouseLog");
    }

    /**
     * 新增库存日志
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存库存日志
     */
    @RequiresPermissions("isky:warehouseLog:add")
    @Log(title = "库存日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WarehouseLog warehouseLog) {
        return toAjax(warehouseLogService.insertWarehouseLog(warehouseLog));
    }

    /**
     * 修改库存日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        WarehouseLog warehouseLog = warehouseLogService.selectWarehouseLogById(id);
        mmap.put("warehouseLog", warehouseLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存日志
     */
    @RequiresPermissions("isky:warehouseLog:edit")
    @Log(title = "库存日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WarehouseLog warehouseLog) {
        return toAjax(warehouseLogService.updateWarehouseLog(warehouseLog));
    }

    /**
     * 删除库存日志
     */
    @RequiresPermissions("isky:warehouseLog:remove")
    @Log(title = "库存日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(warehouseLogService.deleteWarehouseLogByIds(ids));
    }

}
