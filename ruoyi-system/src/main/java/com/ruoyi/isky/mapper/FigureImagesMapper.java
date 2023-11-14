package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.FigureImages;
import java.util.List;	

/**
 * 图片集合 数据层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface FigureImagesMapper 
{
	/**
     * 查询图片集合信息
     * 
     * @param id 图片集合ID
     * @return 图片集合信息
     */
	public FigureImages selectFigureImagesById(Integer id);
	
	/**
     * 查询图片集合列表
     * 
     * @param figureImages 图片集合信息
     * @return 图片集合集合
     */
	public List<FigureImages> selectFigureImagesList(FigureImages figureImages);
	
	/**
     * 新增图片集合
     * 
     * @param figureImages 图片集合信息
     * @return 结果
     */
	public int insertFigureImages(FigureImages figureImages);
	
	/**
     * 修改图片集合
     * 
     * @param figureImages 图片集合信息
     * @return 结果
     */
	public int updateFigureImages(FigureImages figureImages);
	
	/**
     * 删除图片集合
     * 
     * @param id 图片集合ID
     * @return 结果
     */
	public int deleteFigureImagesById(Integer id);
	
	/**
     * 批量删除图片集合
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureImagesByIds(String[] ids);
	
}