package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyPhoneBrandModelMapper;
import com.ruoyi.isky.domain.IskyPhoneBrandModel;
import com.ruoyi.isky.service.IIskyPhoneBrandModelService;
import com.ruoyi.common.core.text.Convert;

/**
 * 品牌型号 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Service
public class IskyPhoneBrandModelServiceImpl implements IIskyPhoneBrandModelService 
{
	@Autowired
	private IskyPhoneBrandModelMapper iskyPhoneBrandModelMapper;

	/**
     * 查询品牌型号信息
     * 
     * @param id 品牌型号ID
     * @return 品牌型号信息
     */
    @Override
	public IskyPhoneBrandModel selectIskyPhoneBrandModelById(Integer id)
	{
	    return iskyPhoneBrandModelMapper.selectIskyPhoneBrandModelById(id);
	}
	
	/**
     * 查询品牌型号列表
     * 
     * @param iskyPhoneBrandModel 品牌型号信息
     * @return 品牌型号集合
     */
	@Override
	public List<IskyPhoneBrandModel> selectIskyPhoneBrandModelList(IskyPhoneBrandModel iskyPhoneBrandModel)
	{
	    return iskyPhoneBrandModelMapper.selectIskyPhoneBrandModelList(iskyPhoneBrandModel);
	}
	
    /**
     * 新增品牌型号
     * 
     * @param iskyPhoneBrandModel 品牌型号信息
     * @return 结果
     */
	@Override
	public int insertIskyPhoneBrandModel(IskyPhoneBrandModel iskyPhoneBrandModel)
	{
	    return iskyPhoneBrandModelMapper.insertIskyPhoneBrandModel(iskyPhoneBrandModel);
	}
	
	/**
     * 修改品牌型号
     * 
     * @param iskyPhoneBrandModel 品牌型号信息
     * @return 结果
     */
	@Override
	public int updateIskyPhoneBrandModel(IskyPhoneBrandModel iskyPhoneBrandModel)
	{
	    return iskyPhoneBrandModelMapper.updateIskyPhoneBrandModel(iskyPhoneBrandModel);
	}

	/**
     * 删除品牌型号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyPhoneBrandModelByIds(String ids)
	{
		return iskyPhoneBrandModelMapper.deleteIskyPhoneBrandModelByIds(Convert.toStrArray(ids));
	}
	
}
