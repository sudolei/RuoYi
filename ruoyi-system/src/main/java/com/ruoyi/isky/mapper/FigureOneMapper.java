package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.FigureOne;
import java.util.List;	

/**
 * 一次性二维码 数据层
 * 
 * @author sunlei
 * @date 2019-08-03
 */
public interface FigureOneMapper 
{
	/**
     * 查询一次性二维码信息
     * 
     * @param id 一次性二维码ID
     * @return 一次性二维码信息
     */
	public FigureOne selectFigureOneById(Integer id);
	
	/**
     * 查询一次性二维码列表
     * 
     * @param figureOne 一次性二维码信息
     * @return 一次性二维码集合
     */
	public List<FigureOne> selectFigureOneList(FigureOne figureOne);
	
	/**
     * 新增一次性二维码
     * 
     * @param figureOne 一次性二维码信息
     * @return 结果
     */
	public int insertFigureOne(FigureOne figureOne);
	
	/**
     * 修改一次性二维码
     * 
     * @param figureOne 一次性二维码信息
     * @return 结果
     */
	public int updateFigureOne(FigureOne figureOne);
	
	/**
     * 删除一次性二维码
     * 
     * @param id 一次性二维码ID
     * @return 结果
     */
	public int deleteFigureOneById(Integer id);
	
	/**
     * 批量删除一次性二维码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureOneByIds(String[] ids);
	
}