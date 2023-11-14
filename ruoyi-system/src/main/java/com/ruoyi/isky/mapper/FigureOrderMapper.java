package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.FigureOrder;
import java.util.List;	

/**
 * 传图订单 数据层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface FigureOrderMapper 
{
	/**
     * 查询传图订单信息
     * 
     * @param id 传图订单ID
     * @return 传图订单信息
     */
	public FigureOrder selectFigureOrderById(Integer id);
	
	/**
     * 查询传图订单列表
     * 
     * @param figureOrder 传图订单信息
     * @return 传图订单集合
     */
	public List<FigureOrder> selectFigureOrderList(FigureOrder figureOrder);



	public List<FigureOrder> selectFigureOrders(FigureOrder figureOrder);
	/**
     * 新增传图订单
     * 
     * @param figureOrder 传图订单信息
     * @return 结果
     */
	public int insertFigureOrder(FigureOrder figureOrder);
	
	/**
     * 修改传图订单
     * 
     * @param figureOrder 传图订单信息
     * @return 结果
     */
	public int updateFigureOrder(FigureOrder figureOrder);
	
	/**
     * 删除传图订单
     * 
     * @param id 传图订单ID
     * @return 结果
     */
	public int deleteFigureOrderById(Integer id);
	
	/**
     * 批量删除传图订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureOrderByIds(String[] ids);
	
}