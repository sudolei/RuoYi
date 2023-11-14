package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyPhoneTypeMapper;
import com.ruoyi.isky.domain.IskyPhoneType;
import com.ruoyi.isky.service.IIskyPhoneTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 手机壳型号 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Service
public class IskyPhoneTypeServiceImpl implements IIskyPhoneTypeService 
{
	@Autowired
	private IskyPhoneTypeMapper iskyPhoneTypeMapper;

	/**
     * 查询手机壳型号信息
     * 
     * @param id 手机壳型号ID
     * @return 手机壳型号信息
     */
    @Override
	public IskyPhoneType selectIskyPhoneTypeById(Integer id)
	{
	    return iskyPhoneTypeMapper.selectIskyPhoneTypeById(id);
	}
	
	/**
     * 查询手机壳型号列表
     * 
     * @param iskyPhoneType 手机壳型号信息
     * @return 手机壳型号集合
     */
	@Override
	public List<IskyPhoneType> selectIskyPhoneTypeList(IskyPhoneType iskyPhoneType)
	{
	    return iskyPhoneTypeMapper.selectIskyPhoneTypeList(iskyPhoneType);
	}
	
    /**
     * 新增手机壳型号
     * 
     * @param iskyPhoneType 手机壳型号信息
     * @return 结果
     */
	@Override
	public int insertIskyPhoneType(IskyPhoneType iskyPhoneType)
	{
	    return iskyPhoneTypeMapper.insertIskyPhoneType(iskyPhoneType);
	}
	
	/**
     * 修改手机壳型号
     * 
     * @param iskyPhoneType 手机壳型号信息
     * @return 结果
     */
	@Override
	public int updateIskyPhoneType(IskyPhoneType iskyPhoneType)
	{
	    return iskyPhoneTypeMapper.updateIskyPhoneType(iskyPhoneType);
	}

	/**
     * 删除手机壳型号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyPhoneTypeByIds(String ids)
	{
		return iskyPhoneTypeMapper.deleteIskyPhoneTypeByIds(Convert.toStrArray(ids));
	}
	
}
