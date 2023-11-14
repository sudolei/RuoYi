package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.WarehouseLog;
import java.util.List;	

/**
 * 库存日志 数据层
 * 
 * @author sunlei
 * @date 2019-11-06
 */
public interface WarehouseLogMapper 
{
	/**
     * 查询库存日志信息
     * 
     * @param id 库存日志ID
     * @return 库存日志信息
     */
	public WarehouseLog selectWarehouseLogById(Integer id);
	
	/**
     * 查询库存日志列表
     * 
     * @param warehouseLog 库存日志信息
     * @return 库存日志集合
     */
	public List<WarehouseLog> selectWarehouseLogList(WarehouseLog warehouseLog);
	
	/**
     * 新增库存日志
     * 
     * @param warehouseLog 库存日志信息
     * @return 结果
     */
	public int insertWarehouseLog(WarehouseLog warehouseLog);
	
	/**
     * 修改库存日志
     * 
     * @param warehouseLog 库存日志信息
     * @return 结果
     */
	public int updateWarehouseLog(WarehouseLog warehouseLog);
	
	/**
     * 删除库存日志
     * 
     * @param id 库存日志ID
     * @return 结果
     */
	public int deleteWarehouseLogById(Integer id);
	
	/**
     * 批量删除库存日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWarehouseLogByIds(String[] ids);
	
}