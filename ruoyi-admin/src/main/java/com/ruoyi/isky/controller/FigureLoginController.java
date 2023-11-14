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
import com.ruoyi.isky.domain.FigureLogin;
import com.ruoyi.isky.service.IFigureLoginService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 传图登录 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/figureLogin")
public class FigureLoginController extends BaseController
{
    private String prefix = "isky/figureLogin";
	
	@Autowired
	private IFigureLoginService figureLoginService;
	
	@RequiresPermissions("isky:figureLogin:view")
	@GetMapping()
	public String figureLogin()
	{
	    return prefix + "/figureLogin";
	}
	
	/**
	 * 查询传图登录列表
	 */
	@RequiresPermissions("isky:figureLogin:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FigureLogin figureLogin)
	{
		startPage();
        List<FigureLogin> list = figureLoginService.selectFigureLoginList(figureLogin);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出传图登录列表
	 */
	@RequiresPermissions("isky:figureLogin:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FigureLogin figureLogin)
    {
    	List<FigureLogin> list = figureLoginService.selectFigureLoginList(figureLogin);
        ExcelUtil<FigureLogin> util = new ExcelUtil<FigureLogin>(FigureLogin.class);
        return util.exportExcel(list, "figureLogin");
    }
	
	/**
	 * 新增传图登录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存传图登录
	 */
	@RequiresPermissions("isky:figureLogin:add")
	@Log(title = "传图登录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FigureLogin figureLogin)
	{		
		return toAjax(figureLoginService.insertFigureLogin(figureLogin));
	}

	/**
	 * 修改传图登录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		FigureLogin figureLogin = figureLoginService.selectFigureLoginById(id);
		mmap.put("figureLogin", figureLogin);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存传图登录
	 */
	@RequiresPermissions("isky:figureLogin:edit")
	@Log(title = "传图登录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FigureLogin figureLogin)
	{		
		return toAjax(figureLoginService.updateFigureLogin(figureLogin));
	}
	
	/**
	 * 删除传图登录
	 */
	@RequiresPermissions("isky:figureLogin:remove")
	@Log(title = "传图登录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(figureLoginService.deleteFigureLoginByIds(ids));
	}
	
}
