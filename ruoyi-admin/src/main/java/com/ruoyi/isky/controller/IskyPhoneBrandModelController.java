package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.IskyPhoneBrand;
import com.ruoyi.isky.domain.IskyPhoneBrandModel;
import com.ruoyi.isky.service.IIskyPhoneBrandModelService;
import com.ruoyi.isky.service.IIskyPhoneBrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 品牌型号 信息操作处理
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Controller
@RequestMapping("/isky/iskyPhoneBrandModel")
public class IskyPhoneBrandModelController extends BaseController
{
    private String prefix = "isky/iskyPhoneBrandModel";

	@Autowired
	private ServerConfig serverConfig;
	@Autowired
	private IIskyPhoneBrandModelService iskyPhoneBrandModelService;
	@Autowired
	private IIskyPhoneBrandService iskyPhoneBrandService;
	
	@RequiresPermissions("isky:iskyPhoneBrandModel:view")
	@GetMapping()
	public String iskyPhoneBrandModel()
	{
	    return prefix + "/iskyPhoneBrandModel";
	}
	
	/**
	 * 查询品牌型号列表
	 */
	@RequiresPermissions("isky:iskyPhoneBrandModel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IskyPhoneBrandModel iskyPhoneBrandModel)
	{
		startPage();
        List<IskyPhoneBrandModel> list = iskyPhoneBrandModelService.selectIskyPhoneBrandModelList(iskyPhoneBrandModel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出品牌型号列表
	 */
	@RequiresPermissions("isky:iskyPhoneBrandModel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IskyPhoneBrandModel iskyPhoneBrandModel)
    {
    	List<IskyPhoneBrandModel> list = iskyPhoneBrandModelService.selectIskyPhoneBrandModelList(iskyPhoneBrandModel);
        ExcelUtil<IskyPhoneBrandModel> util = new ExcelUtil<IskyPhoneBrandModel>(IskyPhoneBrandModel.class);
        return util.exportExcel(list, "iskyPhoneBrandModel");
    }
	
	/**
	 * 新增品牌型号
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		IskyPhoneBrand brand = new IskyPhoneBrand();
		mmap.put("brands", iskyPhoneBrandService.selectIskyPhoneBrandList(brand));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存品牌型号
	 */
	@RequiresPermissions("isky:iskyPhoneBrandModel:add")
	@Log(title = "品牌型号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IskyPhoneBrandModel iskyPhoneBrandModel)
	{		
		return toAjax(iskyPhoneBrandModelService.insertIskyPhoneBrandModel(iskyPhoneBrandModel));
	}

	/**
	 * 修改品牌型号
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		IskyPhoneBrandModel iskyPhoneBrandModel = iskyPhoneBrandModelService.selectIskyPhoneBrandModelById(id);
		mmap.put("iskyPhoneBrandModel", iskyPhoneBrandModel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存品牌型号
	 */
	@RequiresPermissions("isky:iskyPhoneBrandModel:edit")
	@Log(title = "品牌型号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IskyPhoneBrandModel iskyPhoneBrandModel)
	{		
		return toAjax(iskyPhoneBrandModelService.updateIskyPhoneBrandModel(iskyPhoneBrandModel));
	}
	
	/**
	 * 删除品牌型号
	 */
	@RequiresPermissions("isky:iskyPhoneBrandModel:remove")
	@Log(title = "品牌型号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(iskyPhoneBrandModelService.deleteIskyPhoneBrandModelByIds(ids));
	}


	@Log(title = "封面上传", businessType = BusinessType.OTHER)
	@PostMapping({"/uploadCover"})
	@ResponseBody
	public AjaxResult uploadCover(@RequestParam("coverImg") MultipartFile file) {

		try {
			String dir = "model/";
			String filePath = Global.getUploadPath() + dir;
			String fileName = FileUploadUtils.upload(filePath, file);
			String url = serverConfig.getUrl() + "/profile/upload/" + dir + fileName;
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", fileName);
			ajax.put("url", url);
			return ajax;
		} catch (Exception e) {
			return AjaxResult.error(e.getMessage());
		}
	}
}
