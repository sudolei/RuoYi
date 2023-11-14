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
import com.ruoyi.isky.domain.IskyPhoneWorkImage;
import com.ruoyi.isky.service.IIskyPhoneWorkImageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 图片地址 信息操作处理
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Controller
@RequestMapping("/isky/iskyPhoneWorkImage")
public class IskyPhoneWorkImageController extends BaseController
{
    private String prefix = "isky/iskyPhoneWorkImage";
	
	@Autowired
	private IIskyPhoneWorkImageService iskyPhoneWorkImageService;
	
	@RequiresPermissions("isky:iskyPhoneWorkImage:view")
	@GetMapping()
	public String iskyPhoneWorkImage()
	{
	    return prefix + "/iskyPhoneWorkImage";
	}
	
	/**
	 * 查询图片地址列表
	 */
	@RequiresPermissions("isky:iskyPhoneWorkImage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyPhoneWorkImage iskyPhoneWorkImage)
	{
		startPage();
        List<IskyPhoneWorkImage> list = iskyPhoneWorkImageService.selectIskyPhoneWorkImageList(iskyPhoneWorkImage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出图片地址列表
	 */
	@RequiresPermissions("isky:iskyPhoneWorkImage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyPhoneWorkImage iskyPhoneWorkImage)
    {
    	List<IskyPhoneWorkImage> list = iskyPhoneWorkImageService.selectIskyPhoneWorkImageList(iskyPhoneWorkImage);
        ExcelUtil<IskyPhoneWorkImage> util = new ExcelUtil<IskyPhoneWorkImage>(IskyPhoneWorkImage.class);
        return util.exportExcel(list, "iskyPhoneWorkImage");
    }
	
	/**
	 * 新增图片地址
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存图片地址
	 */
	@RequiresPermissions("isky:iskyPhoneWorkImage:add")
	@Log(title = "图片地址", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyPhoneWorkImage iskyPhoneWorkImage)
	{		
		return toAjax(iskyPhoneWorkImageService.insertIskyPhoneWorkImage(iskyPhoneWorkImage));
	}

	/**
	 * 修改图片地址
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyPhoneWorkImage iskyPhoneWorkImage = iskyPhoneWorkImageService.selectIskyPhoneWorkImageById(id);
		mmap.put("iskyPhoneWorkImage", iskyPhoneWorkImage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存图片地址
	 */
	@RequiresPermissions("isky:iskyPhoneWorkImage:edit")
	@Log(title = "图片地址", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyPhoneWorkImage iskyPhoneWorkImage)
	{		
		return toAjax(iskyPhoneWorkImageService.updateIskyPhoneWorkImage(iskyPhoneWorkImage));
	}
	
	/**
	 * 删除图片地址
	 */
	@RequiresPermissions("isky:iskyPhoneWorkImage:remove")
	@Log(title = "图片地址", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyPhoneWorkImageService.deleteIskyPhoneWorkImageByIds(ids));
	}
	
}
