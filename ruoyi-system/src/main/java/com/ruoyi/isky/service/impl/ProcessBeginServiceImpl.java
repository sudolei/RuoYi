package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.ProcessBeginMapper;
import com.ruoyi.isky.domain.ProcessBegin;
import com.ruoyi.isky.service.IProcessBeginService;
import com.ruoyi.common.core.text.Convert;

/**
 * 流程订单 服务层实现
 * 
 * @author sunlei
 * @date 2019-09-30
 */
@Service
public class ProcessBeginServiceImpl implements IProcessBeginService 
{
	@Autowired
	private ProcessBeginMapper processBeginMapper;

	/**
     * 查询流程订单信息
     * 
     * @param id 流程订单ID
     * @return 流程订单信息
     */
    @Override
	public ProcessBegin selectProcessBeginById(Integer id)
	{
	    return processBeginMapper.selectProcessBeginById(id);
	}
	
	/**
     * 查询流程订单列表
     * 
     * @param processBegin 流程订单信息
     * @return 流程订单集合
     */
	@Override
	public List<ProcessBegin> selectProcessBeginList(ProcessBegin processBegin)
	{
	    return processBeginMapper.selectProcessBeginList(processBegin);
	}

    /**
     * 新增流程订单
     * 
     * @param processBegin 流程订单信息
     * @return 结果
     */
	@Override
	public int insertProcessBegin(ProcessBegin processBegin)
	{
	    return processBeginMapper.insertProcessBegin(processBegin);
	}
	
	/**
     * 修改流程订单
     * 
     * @param processBegin 流程订单信息
     * @return 结果
     */
	@Override
	public int updateProcessBegin(ProcessBegin processBegin)
	{
	    return processBeginMapper.updateProcessBegin(processBegin);
	}

	/**
     * 删除流程订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProcessBeginByIds(String ids)
	{
		return processBeginMapper.deleteProcessBeginByIds(Convert.toStrArray(ids));
	}
	
}
