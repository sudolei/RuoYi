package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.WarehouseLogMapper;
import com.ruoyi.isky.domain.WarehouseLog;
import com.ruoyi.isky.service.IWarehouseLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 库存日志 服务层实现
 * 
 * @author sunlei
 * @date 2019-11-06
 */
@Service
public class WarehouseLogServiceImpl implements IWarehouseLogService 
{
	@Autowired
	private WarehouseLogMapper warehouseLogMapper;

	/**
     * 查询库存日志信息
     * 
     * @param id 库存日志ID
     * @return 库存日志信息
     */
    @Override
	public WarehouseLog selectWarehouseLogById(Integer id)
	{
	    return warehouseLogMapper.selectWarehouseLogById(id);
	}
	
	/**
     * 查询库存日志列表
     * 
     * @param warehouseLog 库存日志信息
     * @return 库存日志集合
     */
	@Override
	public List<WarehouseLog> selectWarehouseLogList(WarehouseLog warehouseLog)
	{
	    return warehouseLogMapper.selectWarehouseLogList(warehouseLog);
	}
	
    /**
     * 新增库存日志
     * 
     * @param warehouseLog 库存日志信息
     * @return 结果
     */
	@Override
	public int insertWarehouseLog(WarehouseLog warehouseLog)
	{
	    return warehouseLogMapper.insertWarehouseLog(warehouseLog);
	}
	
	/**
     * 修改库存日志
     * 
     * @param warehouseLog 库存日志信息
     * @return 结果
     */
	@Override
	public int updateWarehouseLog(WarehouseLog warehouseLog)
	{
	    return warehouseLogMapper.updateWarehouseLog(warehouseLog);
	}

	/**
     * 删除库存日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWarehouseLogByIds(String ids)
	{
		return warehouseLogMapper.deleteWarehouseLogByIds(Convert.toStrArray(ids));
	}
	
}
