package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.WarehouseUser;
import com.ruoyi.isky.service.IWarehouseUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户 信息操作处理
 * 
 * @author sunlei
 * @date 2019-11-05
 */
@Controller
@RequestMapping("/isky/warehouseUser")
public class WarehouseUserController extends BaseController
{
    private String prefix = "isky/warehouseUser";
	
	@Autowired
	private IWarehouseUserService warehouseUserService;
	
	@RequiresPermissions("isky:warehouseUser:view")
	@GetMapping()
	public String warehouseUser()
	{
	    return prefix + "/warehouseUser";
	}
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("isky:warehouseUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WarehouseUser warehouseUser)
	{
		startPage();
        List<WarehouseUser> list = warehouseUserService.selectWarehouseUserList(warehouseUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@RequiresPermissions("isky:warehouseUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseUser warehouseUser)
    {
    	List<WarehouseUser> list = warehouseUserService.selectWarehouseUserList(warehouseUser);
        ExcelUtil<WarehouseUser> util = new ExcelUtil<WarehouseUser>(WarehouseUser.class);
        return util.exportExcel(list, "warehouseUser");
    }
	
	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("isky:warehouseUser:add")
	@Log(title = "用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WarehouseUser warehouseUser)
	{
		warehouseUser.setCreateTime(new Date());
		return toAjax(warehouseUserService.insertWarehouseUser(warehouseUser));
	}

	/**
	 * 修改用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WarehouseUser warehouseUser = warehouseUserService.selectWarehouseUserById(id);
		mmap.put("warehouseUser", warehouseUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("isky:warehouseUser:edit")
	@Log(title = "用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WarehouseUser warehouseUser)
	{
		warehouseUser.setUpdateTime(new Date());
		return toAjax(warehouseUserService.updateWarehouseUser(warehouseUser));
	}
	
	/**
	 * 删除用户
	 */
	@RequiresPermissions("isky:warehouseUser:remove")
	@Log(title = "用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(warehouseUserService.deleteWarehouseUserByIds(ids));
	}
	
}
