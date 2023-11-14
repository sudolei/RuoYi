package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureMapper;
import com.ruoyi.isky.domain.Figure;
import com.ruoyi.isky.service.IFigureService;
import com.ruoyi.common.core.text.Convert;

/**
 * 传图分类 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureServiceImpl implements IFigureService 
{
	@Autowired
	private FigureMapper figureMapper;

	/**
     * 查询传图分类信息
     * 
     * @param id 传图分类ID
     * @return 传图分类信息
     */
    @Override
	public Figure selectFigureById(Integer id)
	{
	    return figureMapper.selectFigureById(id);
	}
	
	/**
     * 查询传图分类列表
     * 
     * @param figure 传图分类信息
     * @return 传图分类集合
     */
	@Override
	public List<Figure> selectFigureList(Figure figure)
	{
	    return figureMapper.selectFigureList(figure);
	}
	
    /**
     * 新增传图分类
     * 
     * @param figure 传图分类信息
     * @return 结果
     */
	@Override
	public int insertFigure(Figure figure)
	{
	    return figureMapper.insertFigure(figure);
	}
	
	/**
     * 修改传图分类
     * 
     * @param figure 传图分类信息
     * @return 结果
     */
	@Override
	public int updateFigure(Figure figure)
	{
	    return figureMapper.updateFigure(figure);
	}

	/**
     * 删除传图分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureByIds(String ids)
	{
		return figureMapper.deleteFigureByIds(Convert.toStrArray(ids));
	}
	
}
