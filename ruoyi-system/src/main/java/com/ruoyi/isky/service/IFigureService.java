package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.Figure;
import java.util.List;

/**
 * 传图分类 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureService 
{
	/**
     * 查询传图分类信息
     * 
     * @param id 传图分类ID
     * @return 传图分类信息
     */
	public Figure selectFigureById(Integer id);
	
	/**
     * 查询传图分类列表
     * 
     * @param figure 传图分类信息
     * @return 传图分类集合
     */
	public List<Figure> selectFigureList(Figure figure);
	
	/**
     * 新增传图分类
     * 
     * @param figure 传图分类信息
     * @return 结果
     */
	public int insertFigure(Figure figure);
	
	/**
     * 修改传图分类
     * 
     * @param figure 传图分类信息
     * @return 结果
     */
	public int updateFigure(Figure figure);
		
	/**
     * 删除传图分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureByIds(String ids);
	
}
