package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.FigureLog;
import java.util.List;	

/**
 * 操作日志 数据层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface FigureLogMapper 
{
	/**
     * 查询操作日志信息
     * 
     * @param id 操作日志ID
     * @return 操作日志信息
     */
	public FigureLog selectFigureLogById(Integer id);
	
	/**
     * 查询操作日志列表
     * 
     * @param figureLog 操作日志信息
     * @return 操作日志集合
     */
	public List<FigureLog> selectFigureLogList(FigureLog figureLog);
	
	/**
     * 新增操作日志
     * 
     * @param figureLog 操作日志信息
     * @return 结果
     */
	public int insertFigureLog(FigureLog figureLog);
	
	/**
     * 修改操作日志
     * 
     * @param figureLog 操作日志信息
     * @return 结果
     */
	public int updateFigureLog(FigureLog figureLog);
	
	/**
     * 删除操作日志
     * 
     * @param id 操作日志ID
     * @return 结果
     */
	public int deleteFigureLogById(Integer id);
	
	/**
     * 批量删除操作日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureLogByIds(String[] ids);
	
}