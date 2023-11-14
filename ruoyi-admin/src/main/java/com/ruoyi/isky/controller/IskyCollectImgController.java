package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.IskyCollectImg;
import com.ruoyi.isky.service.IIskyCollectImgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采集数据图片 信息操作处理
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/isky/iskyCollectImg")
public class IskyCollectImgController extends BaseController
{
    private String prefix = "isky/iskyCollectImg";
	
	@Autowired
	private IIskyCollectImgService iskyCollectImgService;
	
	@RequiresPermissions("isky:iskyCollectImg:view")
	@GetMapping()
	public String iskyCollectImg()
	{
	    return prefix + "/iskyCollectImg";
	}
	
	/**
	 * 查询采集数据图片列表
	 */
	@RequiresPermissions("isky:iskyCollectImg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyCollectImg iskyCollectImg)
	{
		startPage();
        List<IskyCollectImg> list = iskyCollectImgService.selectIskyCollectImgList(iskyCollectImg);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出采集数据图片列表
	 */
	@RequiresPermissions("isky:iskyCollectImg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyCollectImg iskyCollectImg)
    {
    	List<IskyCollectImg> list = iskyCollectImgService.selectIskyCollectImgList(iskyCollectImg);
        ExcelUtil<IskyCollectImg> util = new ExcelUtil<IskyCollectImg>(IskyCollectImg.class);
        return util.exportExcel(list, "iskyCollectImg");
    }
	
	/**
	 * 新增采集数据图片
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存采集数据图片
	 */
	@RequiresPermissions("isky:iskyCollectImg:add")
	@Log(title = "采集数据图片", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyCollectImg iskyCollectImg)
	{		
		return toAjax(iskyCollectImgService.insertIskyCollectImg(iskyCollectImg));
	}

	/**
	 * 修改采集数据图片
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyCollectImg iskyCollectImg = iskyCollectImgService.selectIskyCollectImgById(id);
		mmap.put("iskyCollectImg", iskyCollectImg);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存采集数据图片
	 */
	@RequiresPermissions("isky:iskyCollectImg:edit")
	@Log(title = "采集数据图片", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyCollectImg iskyCollectImg)
	{		
		return toAjax(iskyCollectImgService.updateIskyCollectImg(iskyCollectImg));
	}
	
	/**
	 * 删除采集数据图片
	 */
	@RequiresPermissions("isky:iskyCollectImg:remove")
	@Log(title = "采集数据图片", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyCollectImgService.deleteIskyCollectImgByIds(ids));
	}
	
}
