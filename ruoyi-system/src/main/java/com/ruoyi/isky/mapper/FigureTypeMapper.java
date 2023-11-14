package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.FigureType;
import java.util.List;	

/**
 * 模板分类 数据层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface FigureTypeMapper 
{
	/**
     * 查询模板分类信息
     * 
     * @param id 模板分类ID
     * @return 模板分类信息
     */
	public FigureType selectFigureTypeById(Integer id);
	
	/**
     * 查询模板分类列表
     * 
     * @param figureType 模板分类信息
     * @return 模板分类集合
     */
	public List<FigureType> selectFigureTypeList(FigureType figureType);
	
	/**
     * 新增模板分类
     * 
     * @param figureType 模板分类信息
     * @return 结果
     */
	public int insertFigureType(FigureType figureType);
	
	/**
     * 修改模板分类
     * 
     * @param figureType 模板分类信息
     * @return 结果
     */
	public int updateFigureType(FigureType figureType);
	
	/**
     * 删除模板分类
     * 
     * @param id 模板分类ID
     * @return 结果
     */
	public int deleteFigureTypeById(Integer id);
	
	/**
     * 批量删除模板分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureTypeByIds(String[] ids);
	
}