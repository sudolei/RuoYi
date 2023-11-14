package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.ProcessBegin;
import java.util.List;

/**
 * 流程订单 服务层
 * 
 * @author sunlei
 * @date 2019-09-30
 */
public interface IProcessBeginService 
{
	/**
     * 查询流程订单信息
     * 
     * @param id 流程订单ID
     * @return 流程订单信息
     */
	public ProcessBegin selectProcessBeginById(Integer id);
	
	/**
     * 查询流程订单列表
     * 
     * @param processBegin 流程订单信息
     * @return 流程订单集合
     */
	public List<ProcessBegin> selectProcessBeginList(ProcessBegin processBegin);
	
	/**
     * 新增流程订单
     * 
     * @param processBegin 流程订单信息
     * @return 结果
     */
	public int insertProcessBegin(ProcessBegin processBegin);
	
	/**
     * 修改流程订单
     * 
     * @param processBegin 流程订单信息
     * @return 结果
     */
	public int updateProcessBegin(ProcessBegin processBegin);
		
	/**
     * 删除流程订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProcessBeginByIds(String ids);
	
}
