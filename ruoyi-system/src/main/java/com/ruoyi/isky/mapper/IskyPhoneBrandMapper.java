package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.IskyPhoneBrand;
import java.util.List;	

/**
 * 品牌 数据层
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public interface IskyPhoneBrandMapper 
{
	/**
     * 查询品牌信息
     * 
     * @param id 品牌ID
     * @return 品牌信息
     */
	public IskyPhoneBrand selectIskyPhoneBrandById(Integer id);
	
	/**
     * 查询品牌列表
     * 
     * @param iskyPhoneBrand 品牌信息
     * @return 品牌集合
     */
	public List<IskyPhoneBrand> selectIskyPhoneBrandList(IskyPhoneBrand iskyPhoneBrand);
	
	/**
     * 新增品牌
     * 
     * @param iskyPhoneBrand 品牌信息
     * @return 结果
     */
	public int insertIskyPhoneBrand(IskyPhoneBrand iskyPhoneBrand);
	
	/**
     * 修改品牌
     * 
     * @param iskyPhoneBrand 品牌信息
     * @return 结果
     */
	public int updateIskyPhoneBrand(IskyPhoneBrand iskyPhoneBrand);
	
	/**
     * 删除品牌
     * 
     * @param id 品牌ID
     * @return 结果
     */
	public int deleteIskyPhoneBrandById(Integer id);
	
	/**
     * 批量删除品牌
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyPhoneBrandByIds(String[] ids);
	
}