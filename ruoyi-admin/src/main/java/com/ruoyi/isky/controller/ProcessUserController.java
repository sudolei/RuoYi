package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.ProcessUser;
import com.ruoyi.isky.service.IProcessUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司流程用户 信息操作处理
 * 
 * @author sunlei
 * @date 2019-10-12
 */
@Controller
@RequestMapping("/isky/processUser")
public class ProcessUserController extends BaseController
{
    private String prefix = "isky/processUser";
	
	@Autowired
	private IProcessUserService processUserService;
	
	@RequiresPermissions("isky:processUser:view")
	@GetMapping()
	public String processUser()
	{
	    return prefix + "/processUser";
	}
	
	/**
	 * 查询公司流程用户列表
	 */
	@RequiresPermissions("isky:processUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProcessUser processUser)
	{
		startPage();
        List<ProcessUser> list = processUserService.selectProcessUserList(processUser);
		return getDataTable(list);
	}


	
	
	/**
	 * 导出公司流程用户列表
	 */
	@RequiresPermissions("isky:processUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProcessUser processUser)
    {
    	List<ProcessUser> list = processUserService.selectProcessUserList(processUser);
        ExcelUtil<ProcessUser> util = new ExcelUtil<ProcessUser>(ProcessUser.class);
        return util.exportExcel(list, "processUser");
    }
	
	/**
	 * 新增公司流程用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公司流程用户
	 */
	@RequiresPermissions("isky:processUser:add")
	@Log(title = "公司流程用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProcessUser processUser)
	{		
		return toAjax(processUserService.insertProcessUser(processUser));
	}

	/**
	 * 修改公司流程用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ProcessUser processUser = processUserService.selectProcessUserById(id);
		mmap.put("processUser", processUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公司流程用户
	 */
	@RequiresPermissions("isky:processUser:edit")
	@Log(title = "公司流程用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProcessUser processUser)
	{		
		return toAjax(processUserService.updateProcessUser(processUser));
	}
	
	/**
	 * 删除公司流程用户
	 */
	@RequiresPermissions("isky:processUser:remove")
	@Log(title = "公司流程用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(processUserService.deleteProcessUserByIds(ids));
	}
	
}
