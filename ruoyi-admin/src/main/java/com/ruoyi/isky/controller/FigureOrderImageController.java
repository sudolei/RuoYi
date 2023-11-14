package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureOrderImage;
import com.ruoyi.isky.service.IFigureOrderImageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单图片 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureOrderImage")
public class FigureOrderImageController extends BaseController
{
    private String prefix = "isky/figureOrderImage";
	
	@Autowired
	private IFigureOrderImageService figureOrderImageService;
	
	@RequiresPermissions("isky:figureOrderImage:view")
	@GetMapping()
	public String figureOrderImage()
	{
	    return prefix + "/figureOrderImage";
	}
	
	/**
	 * 查询订单图片列表
	 */
	@RequiresPermissions("isky:figureOrderImage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureOrderImage figureOrderImage)
	{
		startPage();
        List<FigureOrderImage> list = figureOrderImageService.selectFigureOrderImageList(figureOrderImage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单图片列表
	 */
	@RequiresPermissions("isky:figureOrderImage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureOrderImage figureOrderImage)
    {
    	List<FigureOrderImage> list = figureOrderImageService.selectFigureOrderImageList(figureOrderImage);
        ExcelUtil<FigureOrderImage> util = new ExcelUtil<FigureOrderImage>(FigureOrderImage.class);
        return util.exportExcel(list, "figureOrderImage");
    }
	
	/**
	 * 新增订单图片
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单图片
	 */
	@RequiresPermissions("isky:figureOrderImage:add")
	@Log(title = "订单图片", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FigureOrderImage figureOrderImage)
	{		
		return toAjax(figureOrderImageService.insertFigureOrderImage(figureOrderImage));
	}

	/**
	 * 修改订单图片
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureOrderImage figureOrderImage = figureOrderImageService.selectFigureOrderImageById(id);
		mmap.put("figureOrderImage", figureOrderImage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单图片
	 */
	@RequiresPermissions("isky:figureOrderImage:edit")
	@Log(title = "订单图片", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureOrderImage figureOrderImage)
	{		
		return toAjax(figureOrderImageService.updateFigureOrderImage(figureOrderImage));
	}
	
	/**
	 * 删除订单图片
	 */
	@RequiresPermissions("isky:figureOrderImage:remove")
	@Log(title = "订单图片", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureOrderImageService.deleteFigureOrderImageByIds(ids));
	}
	
}
