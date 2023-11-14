package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.IskyPhoneMb;
import java.util.List;	

/**
 * 模板 数据层
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public interface IskyPhoneMbMapper 
{
	/**
     * 查询模板信息
     * 
     * @param id 模板ID
     * @return 模板信息
     */
	public IskyPhoneMb selectIskyPhoneMbById(Integer id);
	
	/**
     * 查询模板列表
     * 
     * @param iskyPhoneMb 模板信息
     * @return 模板集合
     */
	public List<IskyPhoneMb> selectIskyPhoneMbList(IskyPhoneMb iskyPhoneMb);
	
	/**
     * 新增模板
     * 
     * @param iskyPhoneMb 模板信息
     * @return 结果
     */
	public int insertIskyPhoneMb(IskyPhoneMb iskyPhoneMb);
	
	/**
     * 修改模板
     * 
     * @param iskyPhoneMb 模板信息
     * @return 结果
     */
	public int updateIskyPhoneMb(IskyPhoneMb iskyPhoneMb);
	
	/**
     * 删除模板
     * 
     * @param id 模板ID
     * @return 结果
     */
	public int deleteIskyPhoneMbById(Integer id);
	
	/**
     * 批量删除模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyPhoneMbByIds(String[] ids);
	
}