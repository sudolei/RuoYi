package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureTypeMapper;
import com.ruoyi.isky.domain.FigureType;
import com.ruoyi.isky.service.IFigureTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 模板分类 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureTypeServiceImpl implements IFigureTypeService 
{
	@Autowired
	private FigureTypeMapper figureTypeMapper;

	/**
     * 查询模板分类信息
     * 
     * @param id 模板分类ID
     * @return 模板分类信息
     */
    @Override
	public FigureType selectFigureTypeById(Integer id)
	{
	    return figureTypeMapper.selectFigureTypeById(id);
	}
	
	/**
     * 查询模板分类列表
     * 
     * @param figureType 模板分类信息
     * @return 模板分类集合
     */
	@Override
	public List<FigureType> selectFigureTypeList(FigureType figureType)
	{
	    return figureTypeMapper.selectFigureTypeList(figureType);
	}
	
    /**
     * 新增模板分类
     * 
     * @param figureType 模板分类信息
     * @return 结果
     */
	@Override
	public int insertFigureType(FigureType figureType)
	{
	    return figureTypeMapper.insertFigureType(figureType);
	}
	
	/**
     * 修改模板分类
     * 
     * @param figureType 模板分类信息
     * @return 结果
     */
	@Override
	public int updateFigureType(FigureType figureType)
	{
	    return figureTypeMapper.updateFigureType(figureType);
	}

	/**
     * 删除模板分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureTypeByIds(String ids)
	{
		return figureTypeMapper.deleteFigureTypeByIds(Convert.toStrArray(ids));
	}
	
}
