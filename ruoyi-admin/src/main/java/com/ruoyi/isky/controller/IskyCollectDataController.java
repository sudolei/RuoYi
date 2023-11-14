package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.IskyCollectData;
import com.ruoyi.isky.service.IIskyCollectDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据采集 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/iskyCollectData")
public class IskyCollectDataController extends BaseController
{
    private String prefix = "isky/iskyCollectData";
	
	@Autowired
	private IIskyCollectDataService iskyCollectDataService;
	
	@RequiresPermissions("isky:iskyCollectData:view")
	@GetMapping()
	public String iskyCollectData()
	{
	    return prefix + "/iskyCollectData";
	}
	
	/**
	 * 查询数据采集列表
	 */
	@RequiresPermissions("isky:iskyCollectData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyCollectData iskyCollectData)
	{
		startPage();
        List<IskyCollectData> list = iskyCollectDataService.selectIskyCollectDataList(iskyCollectData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出数据采集列表
	 */
	@RequiresPermissions("isky:iskyCollectData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyCollectData iskyCollectData)
    {
    	List<IskyCollectData> list = iskyCollectDataService.selectIskyCollectDataList(iskyCollectData);
        ExcelUtil<IskyCollectData> util = new ExcelUtil<IskyCollectData>(IskyCollectData.class);
        return util.exportExcel(list, "iskyCollectData");
    }
	
	/**
	 * 新增数据采集
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存数据采集
	 */
	@RequiresPermissions("isky:iskyCollectData:add")
	@Log(title = "数据采集", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyCollectData iskyCollectData)
	{		
		return toAjax(iskyCollectDataService.insertIskyCollectData(iskyCollectData));
	}

	/**
	 * 修改数据采集
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyCollectData iskyCollectData = iskyCollectDataService.selectIskyCollectDataById(id);
		mmap.put("iskyCollectData", iskyCollectData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存数据采集
	 */
	@RequiresPermissions("isky:iskyCollectData:edit")
	@Log(title = "数据采集", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyCollectData iskyCollectData)
	{		
		return toAjax(iskyCollectDataService.updateIskyCollectData(iskyCollectData));
	}
	
	/**
	 * 删除数据采集
	 */
	@RequiresPermissions("isky:iskyCollectData:remove")
	@Log(title = "数据采集", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyCollectDataService.deleteIskyCollectDataByIds(ids));
	}
	
}
