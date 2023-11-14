package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.IskyPhoneBrandModel;
import com.ruoyi.isky.domain.IskyPhoneMb;
import com.ruoyi.isky.service.IIskyPhoneBrandModelService;
import com.ruoyi.isky.service.IIskyPhoneMbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板 信息操作处理
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Controller
@RequestMapping("/isky/iskyPhoneMb")
public class IskyPhoneMbController extends BaseController
{
    private String prefix = "isky/iskyPhoneMb";
	
	@Autowired
	private IIskyPhoneMbService iskyPhoneMbService;
	@Autowired
	private IIskyPhoneBrandModelService iskyPhoneBrandModelService;

	@RequiresPermissions("isky:iskyPhoneMb:view")
	@GetMapping()
	public String iskyPhoneMb()
	{
	    return prefix + "/iskyPhoneMb";
	}
	
	/**
	 * 查询模板列表
	 */
	@RequiresPermissions("isky:iskyPhoneMb:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyPhoneMb iskyPhoneMb)
	{
		startPage();
        List<IskyPhoneMb> list = iskyPhoneMbService.selectIskyPhoneMbList(iskyPhoneMb);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出模板列表
	 */
	@RequiresPermissions("isky:iskyPhoneMb:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyPhoneMb iskyPhoneMb)
    {
    	List<IskyPhoneMb> list = iskyPhoneMbService.selectIskyPhoneMbList(iskyPhoneMb);
        ExcelUtil<IskyPhoneMb> util = new ExcelUtil<IskyPhoneMb>(IskyPhoneMb.class);
        return util.exportExcel(list, "iskyPhoneMb");
    }
	
	/**
	 * 新增模板
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		IskyPhoneBrandModel brandModel = new IskyPhoneBrandModel();
		mmap.put("models", iskyPhoneBrandModelService.selectIskyPhoneBrandModelList(brandModel));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存模板
	 */
	@RequiresPermissions("isky:iskyPhoneMb:add")
	@Log(title = "模板", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyPhoneMb iskyPhoneMb)
	{		
		return toAjax(iskyPhoneMbService.insertIskyPhoneMb(iskyPhoneMb));
	}

	/**
	 * 修改模板
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyPhoneMb iskyPhoneMb = iskyPhoneMbService.selectIskyPhoneMbById(id);
		mmap.put("iskyPhoneMb", iskyPhoneMb);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存模板
	 */
	@RequiresPermissions("isky:iskyPhoneMb:edit")
	@Log(title = "模板", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyPhoneMb iskyPhoneMb)
	{		
		return toAjax(iskyPhoneMbService.updateIskyPhoneMb(iskyPhoneMb));
	}
	
	/**
	 * 删除模板
	 */
	@RequiresPermissions("isky:iskyPhoneMb:remove")
	@Log(title = "模板", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyPhoneMbService.deleteIskyPhoneMbByIds(ids));
	}
	
}
