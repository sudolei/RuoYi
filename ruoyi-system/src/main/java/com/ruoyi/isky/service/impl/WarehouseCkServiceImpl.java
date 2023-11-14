package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.WarehouseCkMapper;
import com.ruoyi.isky.domain.WarehouseCk;
import com.ruoyi.isky.service.IWarehouseCkService;
import com.ruoyi.common.core.text.Convert;

/**
 * 仓库
 服务层实现
 * 
 * @author sunlei
 * @date 2019-11-07
 */
@Service
public class WarehouseCkServiceImpl implements IWarehouseCkService 
{
	@Autowired
	private WarehouseCkMapper warehouseCkMapper;

	/**
     * 查询仓库
信息
     * 
     * @param id 仓库
ID
     * @return 仓库
信息
     */
    @Override
	public WarehouseCk selectWarehouseCkById(Integer id)
	{
	    return warehouseCkMapper.selectWarehouseCkById(id);
	}
	
	/**
     * 查询仓库
列表
     * 
     * @param warehouseCk 仓库
信息
     * @return 仓库
集合
     */
	@Override
	public List<WarehouseCk> selectWarehouseCkList(WarehouseCk warehouseCk)
	{
	    return warehouseCkMapper.selectWarehouseCkList(warehouseCk);
	}
	
    /**
     * 新增仓库

     * 
     * @param warehouseCk 仓库
信息
     * @return 结果
     */
	@Override
	public int insertWarehouseCk(WarehouseCk warehouseCk)
	{
	    return warehouseCkMapper.insertWarehouseCk(warehouseCk);
	}
	
	/**
     * 修改仓库

     * 
     * @param warehouseCk 仓库
信息
     * @return 结果
     */
	@Override
	public int updateWarehouseCk(WarehouseCk warehouseCk)
	{
	    return warehouseCkMapper.updateWarehouseCk(warehouseCk);
	}

	/**
     * 删除仓库
对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWarehouseCkByIds(String ids)
	{
		return warehouseCkMapper.deleteWarehouseCkByIds(Convert.toStrArray(ids));
	}
	
}
