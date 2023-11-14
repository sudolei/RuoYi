package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.Figure;
import com.ruoyi.isky.service.IFigureService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 传图分类 信息操作处理
 *
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figure")
public class FigureController extends BaseController {
    private String prefix = "isky/figure";

    @Autowired
    private IFigureService figureService;

    @RequiresPermissions("isky:figure:view")
    @GetMapping()
    public String figure() {
        return prefix + "/figure";
    }

    /**
     * 查询传图分类列表
     */
    @RequiresPermissions("isky:figure:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Figure figure) {
        startPage();
        List<Figure> list = figureService.selectFigureList(figure);
        return getDataTable(list);
    }


    /**
     * 导出传图分类列表
     */
    @RequiresPermissions("isky:figure:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Figure figure) {
        List<Figure> list = figureService.selectFigureList(figure);
        ExcelUtil<Figure> util = new ExcelUtil<Figure>(Figure.class);
        return util.exportExcel(list, "figure");
    }

    /**
     * 新增传图分类
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存传图分类
     */
    @RequiresPermissions("isky:figure:add")
    @Log(title = "传图分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Figure figure) {
        return toAjax(figureService.insertFigure(figure));
    }

    /**
     * 修改传图分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Figure figure = figureService.selectFigureById(id);
        mmap.put("figure", figure);
        return prefix + "/edit";
    }

    /**
     * 修改保存传图分类
     */
    @RequiresPermissions("isky:figure:edit")
    @Log(title = "传图分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Figure figure) {
        return toAjax(figureService.updateFigure(figure));
    }

    /**
     * 删除传图分类
     */
    @RequiresPermissions("isky:figure:remove")
    @Log(title = "传图分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(figureService.deleteFigureByIds(ids));
    }

}
