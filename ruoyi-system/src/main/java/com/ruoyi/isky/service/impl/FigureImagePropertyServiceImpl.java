package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureImagePropertyMapper;
import com.ruoyi.isky.domain.FigureImageProperty;
import com.ruoyi.isky.service.IFigureImagePropertyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 图片属性 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureImagePropertyServiceImpl implements IFigureImagePropertyService 
{
	@Autowired
	private FigureImagePropertyMapper figureImagePropertyMapper;

	/**
     * 查询图片属性信息
     * 
     * @param id 图片属性ID
     * @return 图片属性信息
     */
    @Override
	public FigureImageProperty selectFigureImagePropertyById(Integer id)
	{
	    return figureImagePropertyMapper.selectFigureImagePropertyById(id);
	}
	
	/**
     * 查询图片属性列表
     * 
     * @param figureImageProperty 图片属性信息
     * @return 图片属性集合
     */
	@Override
	public List<FigureImageProperty> selectFigureImagePropertyList(FigureImageProperty figureImageProperty)
	{
	    return figureImagePropertyMapper.selectFigureImagePropertyList(figureImageProperty);
	}
	
    /**
     * 新增图片属性
     * 
     * @param figureImageProperty 图片属性信息
     * @return 结果
     */
	@Override
	public int insertFigureImageProperty(FigureImageProperty figureImageProperty)
	{
	    return figureImagePropertyMapper.insertFigureImageProperty(figureImageProperty);
	}
	
	/**
     * 修改图片属性
     * 
     * @param figureImageProperty 图片属性信息
     * @return 结果
     */
	@Override
	public int updateFigureImageProperty(FigureImageProperty figureImageProperty)
	{
	    return figureImagePropertyMapper.updateFigureImageProperty(figureImageProperty);
	}

	/**
     * 删除图片属性对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureImagePropertyByIds(String ids)
	{
		return figureImagePropertyMapper.deleteFigureImagePropertyByIds(Convert.toStrArray(ids));
	}
	
}
