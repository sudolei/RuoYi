package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.WarehouseCk;
import com.ruoyi.isky.domain.WarehouseGoods;
import com.ruoyi.isky.service.IWarehouseCkService;
import com.ruoyi.isky.service.IWarehouseGoodsService;
import com.ruoyi.isky.service.IWarehouseLogService;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存树 信息操作处理
 *
 * @author sunlei
 * @date 2019-11-05
 */
@Controller
@RequestMapping("/isky/warehouseGoods")
public class WarehouseGoodsController extends BaseController {
    private String prefix = "isky/warehouseGoods";

    @Autowired
    private IWarehouseGoodsService warehouseGoodsService;

    @Autowired
    private IWarehouseLogService warehouseLogService;

    @Autowired
    private IWarehouseCkService warehouseCkService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @RequiresPermissions("isky:warehouseGoods:view")
    @GetMapping()
    public String warehouseGoods() {
        return prefix + "/goods";
    }

    /**
     * 查询库存树列表
     */
    @RequiresPermissions("isky:warehouseGoods:list")
    @PostMapping("/grid")
    @ResponseBody
    public TableDataInfo grid(WarehouseGoods warehouseGoods) {
        startPage();
        List<WarehouseGoods> list = warehouseGoodsService.selectWarehouseGoodsList(warehouseGoods);
        return getDataTable(list);
    }

    @RequiresPermissions("isky:warehouseGoods:list")
    @GetMapping("/list")
    @ResponseBody
    public List<WarehouseGoods> list(WarehouseGoods warehouseGoods) {
        List<WarehouseGoods> list = warehouseGoodsService.selectWarehouseGoodsList(warehouseGoods);
        return list;
    }


    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        mmap.put("goods", warehouseGoodsService.selectWarehouseGoodsById(deptId));
        return prefix + "/tree";
    }


    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<Ztree> ztrees = warehouseGoodsService.selectDeptTree(new WarehouseGoods());
        return ztrees;
    }

    /**
     * 导出库存树列表
     */
    @RequiresPermissions("isky:warehouseGoods:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseGoods warehouseGoods) {
        List<WarehouseGoods> list = warehouseGoodsService.selectWarehouseGoodsList(warehouseGoods);
        ExcelUtil<WarehouseGoods> util = new ExcelUtil<WarehouseGoods>(WarehouseGoods.class);
        return util.exportExcel(list, "warehouseGoods");
    }

    /**
     * 新增库存树
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }


    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        mmap.put("goods", warehouseGoodsService.selectWarehouseGoodsById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存库存树
     */
    @RequiresPermissions("isky:warehouseGoods:add")
    @Log(title = "库存树", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WarehouseGoods warehouseGoods) {
        int result = warehouseGoodsService.insertWarehouseGoods(warehouseGoods);
        return toAjax(result);
    }


    /**
     * 修改库存树
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        WarehouseGoods warehouseGoods = warehouseGoodsService.selectWarehouseGoodsById(id);
        mmap.put("warehouseGoods", warehouseGoods);
        return prefix + "/edit";
    }


    /**
     * 修改库存树
     */
    @GetMapping("/editHref/{id}")
    public String editHref(@PathVariable("id") Long id, ModelMap mmap) {
        WarehouseGoods warehouseGoods = warehouseGoodsService.selectWarehouseGoodsById(id);
        mmap.put("warehouseGoods", warehouseGoods);
        return prefix + "/editHref";
    }

    /**
     * 修改库存树
     */
    @GetMapping("/editCk/{id}")
    public String editCk(@PathVariable("id") Long id, ModelMap mmap) {
        WarehouseGoods warehouseGoods = warehouseGoodsService.selectWarehouseGoodsById(id);
        mmap.put("warehouseGoods", warehouseGoods);


        WarehouseCk ck = new WarehouseCk();
        List<WarehouseCk> cks = warehouseCkService.selectWarehouseCkList(ck);
        mmap.put("cks", cks);

        return prefix + "/editCk";
    }

    /**
     * 修改保存库存树
     */
    @RequiresPermissions("isky:warehouseGoods:edit")
    @Log(title = "库存树", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WarehouseGoods warehouseGoods) {
        String ckIds = warehouseGoods.getCkIds();

        if (!StringUtils.isEmpty(ckIds)) {
            String ckNames = "";
            String[] ckIdsArray = Convert.toStrArray(ckIds);
            for (String str : ckIdsArray) {
//				WarehouseCk ck = 	warehouseCkService.selectWarehouseCkById(Integer.parseInt(str));
                String info = sysDictDataService.selectDictLabel("xyzw_ck", str);
                ckNames += info + ",";
            }
            warehouseGoods.setCkNames(ckNames);
        }
        return toAjax(warehouseGoodsService.updateWarehouseGoods(warehouseGoods));
    }
    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        if (warehouseGoodsService.selectCount(id) > 0)
        {
            return AjaxResult.warn("存在下级,不允许删除");
        }
        return toAjax(warehouseGoodsService.deleteWarehouseGoodsById(id));
    }

    /**
     * 删除库存树
     */
    @Log(title = "库存树", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(warehouseGoodsService.deleteWarehouseGoodsByIds(ids));
    }

}
