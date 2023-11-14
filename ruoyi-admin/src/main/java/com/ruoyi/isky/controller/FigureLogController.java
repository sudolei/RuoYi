package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.FigureLog;
import com.ruoyi.isky.service.IFigureLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureLog")
public class FigureLogController extends BaseController
{
    private String prefix = "isky/figureLog";
	
	@Autowired
	private IFigureLogService figureLogService;
	
	@RequiresPermissions("isky:figureLog:view")
	@GetMapping()
	public String figureLog()
	{
	    return prefix + "/figureLog";
	}
	
	/**
	 * 查询操作日志列表
	 */
	@RequiresPermissions("isky:figureLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureLog figureLog)
	{
		startPage();
        List<FigureLog> list = figureLogService.selectFigureLogList(figureLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出操作日志列表
	 */
	@RequiresPermissions("isky:figureLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureLog figureLog)
    {
    	List<FigureLog> list = figureLogService.selectFigureLogList(figureLog);
        ExcelUtil<FigureLog> util = new ExcelUtil<FigureLog>(FigureLog.class);
        return util.exportExcel(list, "figureLog");
    }
	
	/**
	 * 新增操作日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存操作日志
	 */
	@RequiresPermissions("isky:figureLog:add")
	@Log(title = "操作日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FigureLog figureLog)
	{		
		return toAjax(figureLogService.insertFigureLog(figureLog));
	}

	/**
	 * 修改操作日志
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureLog figureLog = figureLogService.selectFigureLogById(id);
		mmap.put("figureLog", figureLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存操作日志
	 */
	@RequiresPermissions("isky:figureLog:edit")
	@Log(title = "操作日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureLog figureLog)
	{		
		return toAjax(figureLogService.updateFigureLog(figureLog));
	}
	
	/**
	 * 删除操作日志
	 */
	@RequiresPermissions("isky:figureLog:remove")
	@Log(title = "操作日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureLogService.deleteFigureLogByIds(ids));
	}
	
}
