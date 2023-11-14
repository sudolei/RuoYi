package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyPhoneMbMapper;
import com.ruoyi.isky.domain.IskyPhoneMb;
import com.ruoyi.isky.service.IIskyPhoneMbService;
import com.ruoyi.common.core.text.Convert;

/**
 * 模板 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Service
public class IskyPhoneMbServiceImpl implements IIskyPhoneMbService 
{
	@Autowired
	private IskyPhoneMbMapper iskyPhoneMbMapper;

	/**
     * 查询模板信息
     * 
     * @param id 模板ID
     * @return 模板信息
     */
    @Override
	public IskyPhoneMb selectIskyPhoneMbById(Integer id)
	{
	    return iskyPhoneMbMapper.selectIskyPhoneMbById(id);
	}
	
	/**
     * 查询模板列表
     * 
     * @param iskyPhoneMb 模板信息
     * @return 模板集合
     */
	@Override
	public List<IskyPhoneMb> selectIskyPhoneMbList(IskyPhoneMb iskyPhoneMb)
	{
	    return iskyPhoneMbMapper.selectIskyPhoneMbList(iskyPhoneMb);
	}
	
    /**
     * 新增模板
     * 
     * @param iskyPhoneMb 模板信息
     * @return 结果
     */
	@Override
	public int insertIskyPhoneMb(IskyPhoneMb iskyPhoneMb)
	{
	    return iskyPhoneMbMapper.insertIskyPhoneMb(iskyPhoneMb);
	}
	
	/**
     * 修改模板
     * 
     * @param iskyPhoneMb 模板信息
     * @return 结果
     */
	@Override
	public int updateIskyPhoneMb(IskyPhoneMb iskyPhoneMb)
	{
	    return iskyPhoneMbMapper.updateIskyPhoneMb(iskyPhoneMb);
	}

	/**
     * 删除模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyPhoneMbByIds(String ids)
	{
		return iskyPhoneMbMapper.deleteIskyPhoneMbByIds(Convert.toStrArray(ids));
	}
	
}
