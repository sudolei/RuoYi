package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureImageProperty;
import com.ruoyi.isky.domain.FigureImages;
import com.ruoyi.isky.service.IFigureImagePropertyService;
import com.ruoyi.isky.service.IFigureImagesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图片属性 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureImageProperty")
public class FigureImagePropertyController extends BaseController
{
    private String prefix = "isky/figureImageProperty";




	@Autowired
	private IFigureImagesService figureImagesService;
	
	@Autowired
	private IFigureImagePropertyService figureImagePropertyService;
	
	@RequiresPermissions("isky:figureImageProperty:view")
	@GetMapping()
	public String figureImageProperty()
	{
	    return prefix + "/figureImageProperty";
	}
	
	/**
	 * 查询图片属性列表
	 */
	@RequiresPermissions("isky:figureImageProperty:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureImageProperty figureImageProperty)
	{
		startPage();
        List<FigureImageProperty> list = figureImagePropertyService.selectFigureImagePropertyList(figureImageProperty);
		return getDataTable(list);
	}

	@GetMapping({"/detail/{imageid}/{zooms}"})
	public String detail(@PathVariable("imageid") Long imageid, @PathVariable("zooms") String zooms, ModelMap mmap)
	{
		mmap.put("imageid", imageid);
		mmap.put("zooms", zooms);
		return this.prefix + "/figureImageProperty";
	}
	
	/**
	 * 导出图片属性列表
	 */
	@RequiresPermissions("isky:figureImageProperty:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureImageProperty figureImageProperty)
    {
    	List<FigureImageProperty> list = figureImagePropertyService.selectFigureImagePropertyList(figureImageProperty);
        ExcelUtil<FigureImageProperty> util = new ExcelUtil<FigureImageProperty>(FigureImageProperty.class);
        return util.exportExcel(list, "figureImageProperty");
    }
	
	/**
	 * 新增图片属性
	 */
	@GetMapping({"/add"})
	public String add(String imageId, ModelMap mmap)
	{
		mmap.put("imageId", imageId);
		return this.prefix + "/add";
	}
//	/**
//	 * 新增保存图片属性
//	 */
//	@RequiresPermissions("isky:figureImageProperty:add")
//	@Log(title = "图片属性", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//	@ResponseBody
//	public AjaxResult addSave(FigureImageProperty figureImageProperty)
//	{
//		return toAjax(figureImagePropertyService.insertFigureImageProperty(figureImageProperty));
//	}



	@RequiresPermissions({"isky:figureImageProperty:add"})
	@Log(title="图片属性", businessType=BusinessType.INSERT)
	@PostMapping({"/addBSave"})
	@ResponseBody
	public AjaxResult addBSave(FigureImageProperty p)
	{
		FigureImages figureImages = new FigureImages();
		figureImages.setTpId(p.getTpId());
		List<FigureImages> list = this.figureImagesService.selectFigureImagesList(figureImages);
		FigureImageProperty property = null;
		for (FigureImages images : list) {
			property = new FigureImageProperty();
			float zooms = Float.parseFloat(images.getZooms());
			float zoomWidth = zooms * Integer.parseInt(p.getWidth());
			float zoomHeight = zooms * Integer.parseInt(p.getHeight());
			float zoomXsite = zooms * Integer.parseInt(p.getXSite());
			float zoomYsite = zooms * Integer.parseInt(p.getYSite());

			int w = (int)zoomWidth;
			int h = (int)zoomHeight;
			int x = (int)zoomXsite;
			int y = (int)zoomYsite;

			property.setZoomWidth(String.valueOf(w));
			property.setZoomHeight(String.valueOf(h));
			property.setZoomXsite(String.valueOf(x));
			property.setZoomYsite(String.valueOf(y));
			property.setImageId(images.getId());
			property.setXSite(p.getXSite());
			property.setYSite(p.getYSite());
			property.setWidth(p.getWidth());
			property.setHeight(p.getHeight());
			property.setIsround("1");
			property.setOrderNum(Integer.valueOf(1));
			this.figureImagePropertyService.insertFigureImageProperty(property);
		}
		return success();
	}

	@RequiresPermissions({"isky:figureImageProperty:add"})
	@Log(title="图片属性", businessType=BusinessType.INSERT)
	@PostMapping({"/add"})
	@ResponseBody
	public AjaxResult addSave(FigureImageProperty figureImageProperty)
	{
		FigureImages images = this.figureImagesService.selectFigureImagesById(figureImageProperty.getImageId());
		float zooms = Float.parseFloat(images.getZooms());
		float zoomWidth = zooms * Integer.parseInt(figureImageProperty.getWidth());
		float zoomHeight = zooms * Integer.parseInt(figureImageProperty.getHeight());
		float zoomXsite = zooms * Integer.parseInt(figureImageProperty.getXSite());
		float zoomYsite = zooms * Integer.parseInt(figureImageProperty.getYSite());

		int w = (int)zoomWidth;
		int h = (int)zoomHeight;
		int x = (int)zoomXsite;
		int y = (int)zoomYsite;

		figureImageProperty.setZoomWidth(String.valueOf(w));
		figureImageProperty.setZoomHeight(String.valueOf(h));
		figureImageProperty.setZoomXsite(String.valueOf(x));
		figureImageProperty.setZoomYsite(String.valueOf(y));
		return toAjax(this.figureImagePropertyService.insertFigureImageProperty(figureImageProperty));
	}


	/**
	 * 修改图片属性
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureImageProperty figureImageProperty = figureImagePropertyService.selectFigureImagePropertyById(id);
		mmap.put("figureImageProperty", figureImageProperty);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存图片属性
	 */
	@RequiresPermissions("isky:figureImageProperty:edit")
	@Log(title = "图片属性", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureImageProperty figureImageProperty)
	{
		FigureImages images = this.figureImagesService.selectFigureImagesById(figureImageProperty.getImageId());
		float zooms = Float.parseFloat(images.getZooms());
		float zoomWidth = zooms * Integer.parseInt(figureImageProperty.getWidth());
		float zoomHeight = zooms * Integer.parseInt(figureImageProperty.getHeight());
		float zoomXsite = zooms * Integer.parseInt(figureImageProperty.getXSite());
		float zoomYsite = zooms * Integer.parseInt(figureImageProperty.getYSite());

		int w = (int)zoomWidth;
		int h = (int)zoomHeight;
		int x = (int)zoomXsite;
		int y = (int)zoomYsite;

		figureImageProperty.setZoomWidth(String.valueOf(w));
		figureImageProperty.setZoomHeight(String.valueOf(h));
		figureImageProperty.setZoomXsite(String.valueOf(x));
		figureImageProperty.setZoomYsite(String.valueOf(y));
		return toAjax(figureImagePropertyService.updateFigureImageProperty(figureImageProperty));
	}
	
	/**
	 * 删除图片属性
	 */
	@RequiresPermissions("isky:figureImageProperty:remove")
	@Log(title = "图片属性", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureImagePropertyService.deleteFigureImagePropertyByIds(ids));
	}
	
}
