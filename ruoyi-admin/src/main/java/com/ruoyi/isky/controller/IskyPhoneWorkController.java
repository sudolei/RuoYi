package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.IskyPhoneWork;
import com.ruoyi.isky.service.IIskyPhoneWorkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 手机壳产品 信息操作处理
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Controller
@RequestMapping("/isky/iskyPhoneWork")
public class IskyPhoneWorkController extends BaseController
{
    private String prefix = "isky/iskyPhoneWork";
	
	@Autowired
	private IIskyPhoneWorkService iskyPhoneWorkService;
	
	@RequiresPermissions("isky:iskyPhoneWork:view")
	@GetMapping()
	public String iskyPhoneWork()
	{
	    return prefix + "/iskyPhoneWork";
	}
	
	/**
	 * 查询手机壳产品列表
	 */
	@RequiresPermissions("isky:iskyPhoneWork:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyPhoneWork iskyPhoneWork)
	{
		startPage();
        List<IskyPhoneWork> list = iskyPhoneWorkService.selectIskyPhoneWorkList(iskyPhoneWork);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出手机壳产品列表
	 */
	@RequiresPermissions("isky:iskyPhoneWork:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyPhoneWork iskyPhoneWork)
    {
    	List<IskyPhoneWork> list = iskyPhoneWorkService.selectIskyPhoneWorkList(iskyPhoneWork);
        ExcelUtil<IskyPhoneWork> util = new ExcelUtil<IskyPhoneWork>(IskyPhoneWork.class);
        return util.exportExcel(list, "iskyPhoneWork");
    }
	
	/**
	 * 新增手机壳产品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存手机壳产品
	 */
	@RequiresPermissions("isky:iskyPhoneWork:add")
	@Log(title = "手机壳产品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyPhoneWork iskyPhoneWork)
	{		
		return toAjax(iskyPhoneWorkService.insertIskyPhoneWork(iskyPhoneWork));
	}

	/**
	 * 修改手机壳产品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyPhoneWork iskyPhoneWork = iskyPhoneWorkService.selectIskyPhoneWorkById(id);
		mmap.put("iskyPhoneWork", iskyPhoneWork);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存手机壳产品
	 */
	@RequiresPermissions("isky:iskyPhoneWork:edit")
	@Log(title = "手机壳产品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyPhoneWork iskyPhoneWork)
	{		
		return toAjax(iskyPhoneWorkService.updateIskyPhoneWork(iskyPhoneWork));
	}
	
	/**
	 * 删除手机壳产品
	 */
	@RequiresPermissions("isky:iskyPhoneWork:remove")
	@Log(title = "手机壳产品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyPhoneWorkService.deleteIskyPhoneWorkByIds(ids));
	}
	
}
