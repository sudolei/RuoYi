package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureImageProperty;
import java.util.List;

/**
 * 图片属性 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureImagePropertyService 
{
	/**
     * 查询图片属性信息
     * 
     * @param id 图片属性ID
     * @return 图片属性信息
     */
	public FigureImageProperty selectFigureImagePropertyById(Integer id);
	
	/**
     * 查询图片属性列表
     * 
     * @param figureImageProperty 图片属性信息
     * @return 图片属性集合
     */
	public List<FigureImageProperty> selectFigureImagePropertyList(FigureImageProperty figureImageProperty);
	
	/**
     * 新增图片属性
     * 
     * @param figureImageProperty 图片属性信息
     * @return 结果
     */
	public int insertFigureImageProperty(FigureImageProperty figureImageProperty);
	
	/**
     * 修改图片属性
     * 
     * @param figureImageProperty 图片属性信息
     * @return 结果
     */
	public int updateFigureImageProperty(FigureImageProperty figureImageProperty);
		
	/**
     * 删除图片属性信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureImagePropertyByIds(String ids);
	
}
