package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyPhoneWorkMapper;
import com.ruoyi.isky.domain.IskyPhoneWork;
import com.ruoyi.isky.service.IIskyPhoneWorkService;
import com.ruoyi.common.core.text.Convert;

/**
 * 手机壳产品 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Service
public class IskyPhoneWorkServiceImpl implements IIskyPhoneWorkService 
{
	@Autowired
	private IskyPhoneWorkMapper iskyPhoneWorkMapper;

	/**
     * 查询手机壳产品信息
     * 
     * @param id 手机壳产品ID
     * @return 手机壳产品信息
     */
    @Override
	public IskyPhoneWork selectIskyPhoneWorkById(Integer id)
	{
	    return iskyPhoneWorkMapper.selectIskyPhoneWorkById(id);
	}
	
	/**
     * 查询手机壳产品列表
     * 
     * @param iskyPhoneWork 手机壳产品信息
     * @return 手机壳产品集合
     */
	@Override
	public List<IskyPhoneWork> selectIskyPhoneWorkList(IskyPhoneWork iskyPhoneWork)
	{
	    return iskyPhoneWorkMapper.selectIskyPhoneWorkList(iskyPhoneWork);
	}
	
    /**
     * 新增手机壳产品
     * 
     * @param iskyPhoneWork 手机壳产品信息
     * @return 结果
     */
	@Override
	public int insertIskyPhoneWork(IskyPhoneWork iskyPhoneWork)
	{
	    return iskyPhoneWorkMapper.insertIskyPhoneWork(iskyPhoneWork);
	}
	
	/**
     * 修改手机壳产品
     * 
     * @param iskyPhoneWork 手机壳产品信息
     * @return 结果
     */
	@Override
	public int updateIskyPhoneWork(IskyPhoneWork iskyPhoneWork)
	{
	    return iskyPhoneWorkMapper.updateIskyPhoneWork(iskyPhoneWork);
	}

	/**
     * 删除手机壳产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyPhoneWorkByIds(String ids)
	{
		return iskyPhoneWorkMapper.deleteIskyPhoneWorkByIds(Convert.toStrArray(ids));
	}
	
}
