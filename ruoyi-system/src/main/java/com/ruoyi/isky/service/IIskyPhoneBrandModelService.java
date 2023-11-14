package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.IskyPhoneBrandModel;
import java.util.List;

/**
 * 品牌型号 服务层
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public interface IIskyPhoneBrandModelService 
{
	/**
     * 查询品牌型号信息
     * 
     * @param id 品牌型号ID
     * @return 品牌型号信息
     */
	public IskyPhoneBrandModel selectIskyPhoneBrandModelById(Integer id);
	
	/**
     * 查询品牌型号列表
     * 
     * @param iskyPhoneBrandModel 品牌型号信息
     * @return 品牌型号集合
     */
	public List<IskyPhoneBrandModel> selectIskyPhoneBrandModelList(IskyPhoneBrandModel iskyPhoneBrandModel);
	
	/**
     * 新增品牌型号
     * 
     * @param iskyPhoneBrandModel 品牌型号信息
     * @return 结果
     */
	public int insertIskyPhoneBrandModel(IskyPhoneBrandModel iskyPhoneBrandModel);
	
	/**
     * 修改品牌型号
     * 
     * @param iskyPhoneBrandModel 品牌型号信息
     * @return 结果
     */
	public int updateIskyPhoneBrandModel(IskyPhoneBrandModel iskyPhoneBrandModel);
		
	/**
     * 删除品牌型号信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyPhoneBrandModelByIds(String ids);
	
}
