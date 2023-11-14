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
import com.ruoyi.isky.domain.IskyPhoneType;
import com.ruoyi.isky.service.IIskyPhoneTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 手机壳型号 信息操作处理
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Controller
@RequestMapping("/isky/iskyPhoneType")
public class IskyPhoneTypeController extends BaseController
{
    private String prefix = "isky/iskyPhoneType";
	
	@Autowired
	private IIskyPhoneTypeService iskyPhoneTypeService;
	
	@RequiresPermissions("isky:iskyPhoneType:view")
	@GetMapping()
	public String iskyPhoneType()
	{
	    return prefix + "/iskyPhoneType";
	}
	
	/**
	 * 查询手机壳型号列表
	 */
	@RequiresPermissions("isky:iskyPhoneType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyPhoneType iskyPhoneType)
	{
		startPage();
        List<IskyPhoneType> list = iskyPhoneTypeService.selectIskyPhoneTypeList(iskyPhoneType);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出手机壳型号列表
	 */
	@RequiresPermissions("isky:iskyPhoneType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyPhoneType iskyPhoneType)
    {
    	List<IskyPhoneType> list = iskyPhoneTypeService.selectIskyPhoneTypeList(iskyPhoneType);
        ExcelUtil<IskyPhoneType> util = new ExcelUtil<IskyPhoneType>(IskyPhoneType.class);
        return util.exportExcel(list, "iskyPhoneType");
    }
	
	/**
	 * 新增手机壳型号
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存手机壳型号
	 */
	@RequiresPermissions("isky:iskyPhoneType:add")
	@Log(title = "手机壳型号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyPhoneType iskyPhoneType)
	{		
		return toAjax(iskyPhoneTypeService.insertIskyPhoneType(iskyPhoneType));
	}

	/**
	 * 修改手机壳型号
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyPhoneType iskyPhoneType = iskyPhoneTypeService.selectIskyPhoneTypeById(id);
		mmap.put("iskyPhoneType", iskyPhoneType);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存手机壳型号
	 */
	@RequiresPermissions("isky:iskyPhoneType:edit")
	@Log(title = "手机壳型号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyPhoneType iskyPhoneType)
	{		
		return toAjax(iskyPhoneTypeService.updateIskyPhoneType(iskyPhoneType));
	}
	
	/**
	 * 删除手机壳型号
	 */
	@RequiresPermissions("isky:iskyPhoneType:remove")
	@Log(title = "手机壳型号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyPhoneTypeService.deleteIskyPhoneTypeByIds(ids));
	}
	
}
