package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureOrderImage;
import java.util.List;

/**
 * 订单图片 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureOrderImageService 
{
	/**
     * 查询订单图片信息
     * 
     * @param id 订单图片ID
     * @return 订单图片信息
     */
	public FigureOrderImage selectFigureOrderImageById(Integer id);
	
	/**
     * 查询订单图片列表
     * 
     * @param figureOrderImage 订单图片信息
     * @return 订单图片集合
     */
	public List<FigureOrderImage> selectFigureOrderImageList(FigureOrderImage figureOrderImage);
	
	/**
     * 新增订单图片
     * 
     * @param figureOrderImage 订单图片信息
     * @return 结果
     */
	public int insertFigureOrderImage(FigureOrderImage figureOrderImage);
	
	/**
     * 修改订单图片
     * 
     * @param figureOrderImage 订单图片信息
     * @return 结果
     */
	public int updateFigureOrderImage(FigureOrderImage figureOrderImage);
		
	/**
     * 删除订单图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureOrderImageByIds(String ids);
	
}
