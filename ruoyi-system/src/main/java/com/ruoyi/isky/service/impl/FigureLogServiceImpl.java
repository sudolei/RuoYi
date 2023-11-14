package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureLogMapper;
import com.ruoyi.isky.domain.FigureLog;
import com.ruoyi.isky.service.IFigureLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 操作日志 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureLogServiceImpl implements IFigureLogService 
{
	@Autowired
	private FigureLogMapper figureLogMapper;

	/**
     * 查询操作日志信息
     * 
     * @param id 操作日志ID
     * @return 操作日志信息
     */
    @Override
	public FigureLog selectFigureLogById(Integer id)
	{
	    return figureLogMapper.selectFigureLogById(id);
	}
	
	/**
     * 查询操作日志列表
     * 
     * @param figureLog 操作日志信息
     * @return 操作日志集合
     */
	@Override
	public List<FigureLog> selectFigureLogList(FigureLog figureLog)
	{
	    return figureLogMapper.selectFigureLogList(figureLog);
	}
	
    /**
     * 新增操作日志
     * 
     * @param figureLog 操作日志信息
     * @return 结果
     */
	@Override
	public int insertFigureLog(FigureLog figureLog)
	{
	    return figureLogMapper.insertFigureLog(figureLog);
	}
	
	/**
     * 修改操作日志
     * 
     * @param figureLog 操作日志信息
     * @return 结果
     */
	@Override
	public int updateFigureLog(FigureLog figureLog)
	{
	    return figureLogMapper.updateFigureLog(figureLog);
	}

	/**
     * 删除操作日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureLogByIds(String ids)
	{
		return figureLogMapper.deleteFigureLogByIds(Convert.toStrArray(ids));
	}
	
}
