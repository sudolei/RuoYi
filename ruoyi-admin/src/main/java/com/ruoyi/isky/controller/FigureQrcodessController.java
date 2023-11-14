package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureQrcodess;
import com.ruoyi.isky.service.IFigureQrcodessService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 二维码 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureQrcodess")
public class FigureQrcodessController extends BaseController
{
    private String prefix = "isky/figureQrcodess";
	
	@Autowired
	private IFigureQrcodessService figureQrcodessService;
	
	@RequiresPermissions("isky:figureQrcodess:view")
	@GetMapping()
	public String figureQrcodess()
	{
	    return prefix + "/figureQrcodess";
	}
	
	/**
	 * 查询二维码列表
	 */
	@RequiresPermissions("isky:figureQrcodess:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureQrcodess figureQrcodess)
	{
		startPage();
        List<FigureQrcodess> list = figureQrcodessService.selectFigureQrcodessList(figureQrcodess);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出二维码列表
	 */
	@RequiresPermissions("isky:figureQrcodess:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureQrcodess figureQrcodess)
    {
    	List<FigureQrcodess> list = figureQrcodessService.selectFigureQrcodessList(figureQrcodess);
        ExcelUtil<FigureQrcodess> util = new ExcelUtil<FigureQrcodess>(FigureQrcodess.class);
        return util.exportExcel(list, "figureQrcodess");
    }
	
	/**
	 * 新增二维码
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存二维码
	 */
	@RequiresPermissions("isky:figureQrcodess:add")
	@Log(title = "二维码", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FigureQrcodess figureQrcodess)
	{		
		return toAjax(figureQrcodessService.insertFigureQrcodess(figureQrcodess));
	}

	/**
	 * 修改二维码
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureQrcodess figureQrcodess = figureQrcodessService.selectFigureQrcodessById(id);
		mmap.put("figureQrcodess", figureQrcodess);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存二维码
	 */
	@RequiresPermissions("isky:figureQrcodess:edit")
	@Log(title = "二维码", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureQrcodess figureQrcodess)
	{		
		return toAjax(figureQrcodessService.updateFigureQrcodess(figureQrcodess));
	}
	
	/**
	 * 删除二维码
	 */
	@RequiresPermissions("isky:figureQrcodess:remove")
	@Log(title = "二维码", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureQrcodessService.deleteFigureQrcodessByIds(ids));
	}
	
}
