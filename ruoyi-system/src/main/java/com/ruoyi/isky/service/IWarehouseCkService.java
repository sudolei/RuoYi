package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.WarehouseCk;
import java.util.List;

/**
 * 仓库
 服务层
 * 
 * @author sunlei
 * @date 2019-11-07
 */
public interface IWarehouseCkService 
{
	/**
     * 查询仓库
信息
     * 
     * @param id 仓库
ID
     * @return 仓库
信息
     */
	public WarehouseCk selectWarehouseCkById(Integer id);
	
	/**
     * 查询仓库
列表
     * 
     * @param warehouseCk 仓库
信息
     * @return 仓库
集合
     */
	public List<WarehouseCk> selectWarehouseCkList(WarehouseCk warehouseCk);
	
	/**
     * 新增仓库

     * 
     * @param warehouseCk 仓库
信息
     * @return 结果
     */
	public int insertWarehouseCk(WarehouseCk warehouseCk);
	
	/**
     * 修改仓库

     * 
     * @param warehouseCk 仓库
信息
     * @return 结果
     */
	public int updateWarehouseCk(WarehouseCk warehouseCk);
		
	/**
     * 删除仓库
信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWarehouseCkByIds(String ids);
	
}
