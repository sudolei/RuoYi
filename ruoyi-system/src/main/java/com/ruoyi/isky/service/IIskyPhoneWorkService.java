package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.IskyPhoneWork;
import java.util.List;

/**
 * 手机壳产品 服务层
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public interface IIskyPhoneWorkService 
{
	/**
     * 查询手机壳产品信息
     * 
     * @param id 手机壳产品ID
     * @return 手机壳产品信息
     */
	public IskyPhoneWork selectIskyPhoneWorkById(Integer id);
	
	/**
     * 查询手机壳产品列表
     * 
     * @param iskyPhoneWork 手机壳产品信息
     * @return 手机壳产品集合
     */
	public List<IskyPhoneWork> selectIskyPhoneWorkList(IskyPhoneWork iskyPhoneWork);
	
	/**
     * 新增手机壳产品
     * 
     * @param iskyPhoneWork 手机壳产品信息
     * @return 结果
     */
	public int insertIskyPhoneWork(IskyPhoneWork iskyPhoneWork);
	
	/**
     * 修改手机壳产品
     * 
     * @param iskyPhoneWork 手机壳产品信息
     * @return 结果
     */
	public int updateIskyPhoneWork(IskyPhoneWork iskyPhoneWork);
		
	/**
     * 删除手机壳产品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyPhoneWorkByIds(String ids);
	
}
