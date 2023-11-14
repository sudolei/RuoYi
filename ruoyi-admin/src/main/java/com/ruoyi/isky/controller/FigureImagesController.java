package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureImages;
import com.ruoyi.isky.service.IFigureImagesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图片集合 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureImages")
public class FigureImagesController extends BaseController
{
    private String prefix = "isky/figureImages";
	
	@Autowired
	private IFigureImagesService figureImagesService;
	
	@RequiresPermissions("isky:figureImages:view")
	@GetMapping()
	public String figureImages()
	{
	    return prefix + "/figureImages";
	}
	
	/**
	 * 查询图片集合列表
	 */
	@RequiresPermissions("isky:figureImages:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureImages figureImages)
	{
		startPage();
        List<FigureImages> list = figureImagesService.selectFigureImagesList(figureImages);
		return getDataTable(list);
	}

	/**
	 * 查看详细
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, ModelMap mmap)
	{
		mmap.put("typeId",id);
		return prefix + "/images";
	}


	@GetMapping({"/propertyB/{tpid}"})
	public String propertyB(@PathVariable("tpid") Integer tpid, ModelMap mmap)
	{
		mmap.put("tpid", tpid);
		return this.prefix + "/propertyB";
	}
	/**
	 * 导出图片集合列表
	 */
	@RequiresPermissions("isky:figureImages:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureImages figureImages)
    {
    	List<FigureImages> list = figureImagesService.selectFigureImagesList(figureImages);
        ExcelUtil<FigureImages> util = new ExcelUtil<FigureImages>(FigureImages.class);
        return util.exportExcel(list, "figureImages");
    }
	
	/**
	 * 新增图片集合
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存图片集合
	 */
	@RequiresPermissions("isky:figureImages:add")
	@Log(title = "图片集合", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FigureImages figureImages)
	{		
		return toAjax(figureImagesService.insertFigureImages(figureImages));
	}

	/**
	 * 修改图片集合
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureImages figureImages = figureImagesService.selectFigureImagesById(id);
		mmap.put("figureImages", figureImages);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存图片集合
	 */
	@RequiresPermissions("isky:figureImages:edit")
	@Log(title = "图片集合", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureImages figureImages)
	{		
		return toAjax(figureImagesService.updateFigureImages(figureImages));
	}
	
	/**
	 * 删除图片集合
	 */
	@RequiresPermissions("isky:figureImages:remove")
	@Log(title = "图片集合", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureImagesService.deleteFigureImagesByIds(ids));
	}
	
}
