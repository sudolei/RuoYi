package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureOrderImageMapper;
import com.ruoyi.isky.domain.FigureOrderImage;
import com.ruoyi.isky.service.IFigureOrderImageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 订单图片 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureOrderImageServiceImpl implements IFigureOrderImageService 
{
	@Autowired
	private FigureOrderImageMapper figureOrderImageMapper;

	/**
     * 查询订单图片信息
     * 
     * @param id 订单图片ID
     * @return 订单图片信息
     */
    @Override
	public FigureOrderImage selectFigureOrderImageById(Integer id)
	{
	    return figureOrderImageMapper.selectFigureOrderImageById(id);
	}
	
	/**
     * 查询订单图片列表
     * 
     * @param figureOrderImage 订单图片信息
     * @return 订单图片集合
     */
	@Override
	public List<FigureOrderImage> selectFigureOrderImageList(FigureOrderImage figureOrderImage)
	{
	    return figureOrderImageMapper.selectFigureOrderImageList(figureOrderImage);
	}
	
    /**
     * 新增订单图片
     * 
     * @param figureOrderImage 订单图片信息
     * @return 结果
     */
	@Override
	public int insertFigureOrderImage(FigureOrderImage figureOrderImage)
	{
	    return figureOrderImageMapper.insertFigureOrderImage(figureOrderImage);
	}
	
	/**
     * 修改订单图片
     * 
     * @param figureOrderImage 订单图片信息
     * @return 结果
     */
	@Override
	public int updateFigureOrderImage(FigureOrderImage figureOrderImage)
	{
	    return figureOrderImageMapper.updateFigureOrderImage(figureOrderImage);
	}

	/**
     * 删除订单图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureOrderImageByIds(String ids)
	{
		return figureOrderImageMapper.deleteFigureOrderImageByIds(Convert.toStrArray(ids));
	}
	
}
