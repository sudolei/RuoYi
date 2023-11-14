package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureQrcodeWork;
import java.util.List;

/**
 * 二维码内容 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureQrcodeWorkService 
{
	/**
     * 查询二维码内容信息
     * 
     * @param id 二维码内容ID
     * @return 二维码内容信息
     */
	public FigureQrcodeWork selectFigureQrcodeWorkById(Integer id);
	
	/**
     * 查询二维码内容列表
     * 
     * @param figureQrcodeWork 二维码内容信息
     * @return 二维码内容集合
     */
	public List<FigureQrcodeWork> selectFigureQrcodeWorkList(FigureQrcodeWork figureQrcodeWork);
	
	/**
     * 新增二维码内容
     * 
     * @param figureQrcodeWork 二维码内容信息
     * @return 结果
     */
	public int insertFigureQrcodeWork(FigureQrcodeWork figureQrcodeWork);
	
	/**
     * 修改二维码内容
     * 
     * @param figureQrcodeWork 二维码内容信息
     * @return 结果
     */
	public int updateFigureQrcodeWork(FigureQrcodeWork figureQrcodeWork);
		
	/**
     * 删除二维码内容信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureQrcodeWorkByIds(String ids);
	
}
