package com.ruoyi.isky.controller;

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
import com.ruoyi.isky.domain.FigureQrcodeWork;
import com.ruoyi.isky.service.IFigureQrcodeWorkService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 二维码内容 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureQrcodeWork")
public class FigureQrcodeWorkController extends BaseController
{
    private String prefix = "isky/figureQrcodeWork";
	
	@Autowired
	private IFigureQrcodeWorkService figureQrcodeWorkService;
	
	@RequiresPermissions("isky:figureQrcodeWork:view")
	@GetMapping()
	public String figureQrcodeWork()
	{
	    return prefix + "/figureQrcodeWork";
	}
	
	/**
	 * 查询二维码内容列表
	 */
	@RequiresPermissions("isky:figureQrcodeWork:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureQrcodeWork figureQrcodeWork)
	{
		startPage();
        List<FigureQrcodeWork> list = figureQrcodeWorkService.selectFigureQrcodeWorkList(figureQrcodeWork);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出二维码内容列表
	 */
	@RequiresPermissions("isky:figureQrcodeWork:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureQrcodeWork figureQrcodeWork)
    {
    	List<FigureQrcodeWork> list = figureQrcodeWorkService.selectFigureQrcodeWorkList(figureQrcodeWork);
        ExcelUtil<FigureQrcodeWork> util = new ExcelUtil<FigureQrcodeWork>(FigureQrcodeWork.class);
        return util.exportExcel(list, "figureQrcodeWork");
    }
	
	/**
	 * 新增二维码内容
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存二维码内容
	 */
	@RequiresPermissions("isky:figureQrcodeWork:add")
	@Log(title = "二维码内容", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FigureQrcodeWork figureQrcodeWork)
	{		
		return toAjax(figureQrcodeWorkService.insertFigureQrcodeWork(figureQrcodeWork));
	}

	/**
	 * 修改二维码内容
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureQrcodeWork figureQrcodeWork = figureQrcodeWorkService.selectFigureQrcodeWorkById(id);
		mmap.put("figureQrcodeWork", figureQrcodeWork);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存二维码内容
	 */
	@RequiresPermissions("isky:figureQrcodeWork:edit")
	@Log(title = "二维码内容", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureQrcodeWork figureQrcodeWork)
	{		
		return toAjax(figureQrcodeWorkService.updateFigureQrcodeWork(figureQrcodeWork));
	}
	
	/**
	 * 删除二维码内容
	 */
	@RequiresPermissions("isky:figureQrcodeWork:remove")
	@Log(title = "二维码内容", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureQrcodeWorkService.deleteFigureQrcodeWorkByIds(ids));
	}
	
}
