package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.WarehouseUserMapper;
import com.ruoyi.isky.domain.WarehouseUser;
import com.ruoyi.isky.service.IWarehouseUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户 服务层实现
 * 
 * @author sunlei
 * @date 2019-11-05
 */
@Service
public class WarehouseUserServiceImpl implements IWarehouseUserService 
{
	@Autowired
	private WarehouseUserMapper warehouseUserMapper;

	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
	public WarehouseUser selectWarehouseUserById(Integer id)
	{
	    return warehouseUserMapper.selectWarehouseUserById(id);
	}
	
	/**
     * 查询用户列表
     * 
     * @param warehouseUser 用户信息
     * @return 用户集合
     */
	@Override
	public List<WarehouseUser> selectWarehouseUserList(WarehouseUser warehouseUser)
	{
	    return warehouseUserMapper.selectWarehouseUserList(warehouseUser);
	}
	
    /**
     * 新增用户
     * 
     * @param warehouseUser 用户信息
     * @return 结果
     */
	@Override
	public int insertWarehouseUser(WarehouseUser warehouseUser)
	{
	    return warehouseUserMapper.insertWarehouseUser(warehouseUser);
	}
	
	/**
     * 修改用户
     * 
     * @param warehouseUser 用户信息
     * @return 结果
     */
	@Override
	public int updateWarehouseUser(WarehouseUser warehouseUser)
	{
	    return warehouseUserMapper.updateWarehouseUser(warehouseUser);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWarehouseUserByIds(String ids)
	{
		return warehouseUserMapper.deleteWarehouseUserByIds(Convert.toStrArray(ids));
	}
	
}
