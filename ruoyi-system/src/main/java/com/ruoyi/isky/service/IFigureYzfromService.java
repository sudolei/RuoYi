package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureYzfrom;
import java.util.List;

/**
 * 印数统计 服务层
 * 
 * @author sunlei
 * @date 2020-06-24
 */
public interface IFigureYzfromService 
{
	/**
     * 查询印数统计信息
     * 
     * @param id 印数统计ID
     * @return 印数统计信息
     */
	public FigureYzfrom selectFigureYzfromById(Integer id);
	
	/**
     * 查询印数统计列表
     * 
     * @param figureYzfrom 印数统计信息
     * @return 印数统计集合
     */
	public List<FigureYzfrom> selectFigureYzfromList(FigureYzfrom figureYzfrom);


	public List<FigureYzfrom> selectFigureYzFromDate(FigureYzfrom figureYzfrom);
	
	/**
     * 新增印数统计
     * 
     * @param figureYzfrom 印数统计信息
     * @return 结果
     */
	public int insertFigureYzfrom(FigureYzfrom figureYzfrom);
	
	/**
     * 修改印数统计
     * 
     * @param figureYzfrom 印数统计信息
     * @return 结果
     */
	public int updateFigureYzfrom(FigureYzfrom figureYzfrom);
		
	/**
     * 删除印数统计信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureYzfromByIds(String ids);
	
}
