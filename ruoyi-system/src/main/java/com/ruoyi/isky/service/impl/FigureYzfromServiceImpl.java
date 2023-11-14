package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureYzfromMapper;
import com.ruoyi.isky.domain.FigureYzfrom;
import com.ruoyi.isky.service.IFigureYzfromService;
import com.ruoyi.common.core.text.Convert;

/**
 * 印数统计 服务层实现
 * 
 * @author sunlei
 * @date 2020-06-24
 */
@Service
public class FigureYzfromServiceImpl implements IFigureYzfromService 
{
	@Autowired
	private FigureYzfromMapper figureYzfromMapper;

	/**
     * 查询印数统计信息
     * 
     * @param id 印数统计ID
     * @return 印数统计信息
     */
    @Override
	public FigureYzfrom selectFigureYzfromById(Integer id)
	{
	    return figureYzfromMapper.selectFigureYzfromById(id);
	}
	
	/**
     * 查询印数统计列表
     * 
     * @param figureYzfrom 印数统计信息
     * @return 印数统计集合
     */
	@Override
	public List<FigureYzfrom> selectFigureYzfromList(FigureYzfrom figureYzfrom)
	{
	    return figureYzfromMapper.selectFigureYzfromList(figureYzfrom);
	}

	@Override
	public List<FigureYzfrom> selectFigureYzFromDate(FigureYzfrom figureYzfrom) {
		return figureYzfromMapper.selectFigureYzFromDate(figureYzfrom);
	}

	/**
     * 新增印数统计
     * 
     * @param figureYzfrom 印数统计信息
     * @return 结果
     */
	@Override
	public int insertFigureYzfrom(FigureYzfrom figureYzfrom)
	{
	    return figureYzfromMapper.insertFigureYzfrom(figureYzfrom);
	}
	
	/**
     * 修改印数统计
     * 
     * @param figureYzfrom 印数统计信息
     * @return 结果
     */
	@Override
	public int updateFigureYzfrom(FigureYzfrom figureYzfrom)
	{
	    return figureYzfromMapper.updateFigureYzfrom(figureYzfrom);
	}

	/**
     * 删除印数统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureYzfromByIds(String ids)
	{
		return figureYzfromMapper.deleteFigureYzfromByIds(Convert.toStrArray(ids));
	}
	
}
