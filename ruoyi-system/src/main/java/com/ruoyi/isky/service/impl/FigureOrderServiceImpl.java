package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureOrderMapper;
import com.ruoyi.isky.domain.FigureOrder;
import com.ruoyi.isky.service.IFigureOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 传图订单 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureOrderServiceImpl implements IFigureOrderService 
{
	@Autowired
	private FigureOrderMapper figureOrderMapper;

	/**
     * 查询传图订单信息
     * 
     * @param id 传图订单ID
     * @return 传图订单信息
     */
    @Override
	public FigureOrder selectFigureOrderById(Integer id)
	{
	    return figureOrderMapper.selectFigureOrderById(id);
	}
	
	/**
     * 查询传图订单列表
     * 
     * @param figureOrder 传图订单信息
     * @return 传图订单集合
     */
	@Override
	public List<FigureOrder> selectFigureOrderList(FigureOrder figureOrder)
	{
	    return figureOrderMapper.selectFigureOrderList(figureOrder);
	}

	@Override
	public List<FigureOrder> selectFigureOrders(FigureOrder figureOrder) {
		return figureOrderMapper.selectFigureOrders(figureOrder);
	}

	/**
     * 新增传图订单
     * 
     * @param figureOrder 传图订单信息
     * @return 结果
     */
	@Override
	public int insertFigureOrder(FigureOrder figureOrder)
	{
	    return figureOrderMapper.insertFigureOrder(figureOrder);
	}
	
	/**
     * 修改传图订单
     * 
     * @param figureOrder 传图订单信息
     * @return 结果
     */
	@Override
	public int updateFigureOrder(FigureOrder figureOrder)
	{
	    return figureOrderMapper.updateFigureOrder(figureOrder);
	}

	/**
     * 删除传图订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureOrderByIds(String ids)
	{
		return figureOrderMapper.deleteFigureOrderByIds(Convert.toStrArray(ids));
	}
	
}
