package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.IskyPhoneType;
import java.util.List;	

/**
 * 手机壳型号 数据层
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public interface IskyPhoneTypeMapper 
{
	/**
     * 查询手机壳型号信息
     * 
     * @param id 手机壳型号ID
     * @return 手机壳型号信息
     */
	public IskyPhoneType selectIskyPhoneTypeById(Integer id);
	
	/**
     * 查询手机壳型号列表
     * 
     * @param iskyPhoneType 手机壳型号信息
     * @return 手机壳型号集合
     */
	public List<IskyPhoneType> selectIskyPhoneTypeList(IskyPhoneType iskyPhoneType);
	
	/**
     * 新增手机壳型号
     * 
     * @param iskyPhoneType 手机壳型号信息
     * @return 结果
     */
	public int insertIskyPhoneType(IskyPhoneType iskyPhoneType);
	
	/**
     * 修改手机壳型号
     * 
     * @param iskyPhoneType 手机壳型号信息
     * @return 结果
     */
	public int updateIskyPhoneType(IskyPhoneType iskyPhoneType);
	
	/**
     * 删除手机壳型号
     * 
     * @param id 手机壳型号ID
     * @return 结果
     */
	public int deleteIskyPhoneTypeById(Integer id);
	
	/**
     * 批量删除手机壳型号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyPhoneTypeByIds(String[] ids);
	
}