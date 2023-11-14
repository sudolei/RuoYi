package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureLogin;
import java.util.List;

/**
 * 传图登录 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureLoginService 
{
	/**
     * 查询传图登录信息
     * 
     * @param id 传图登录ID
     * @return 传图登录信息
     */
	public FigureLogin selectFigureLoginById(Integer id);
	
	/**
     * 查询传图登录列表
     * 
     * @param figureLogin 传图登录信息
     * @return 传图登录集合
     */
	public List<FigureLogin> selectFigureLoginList(FigureLogin figureLogin);
	
	/**
     * 新增传图登录
     * 
     * @param figureLogin 传图登录信息
     * @return 结果
     */
	public int insertFigureLogin(FigureLogin figureLogin);
	
	/**
     * 修改传图登录
     * 
     * @param figureLogin 传图登录信息
     * @return 结果
     */
	public int updateFigureLogin(FigureLogin figureLogin);
		
	/**
     * 删除传图登录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureLoginByIds(String ids);
	
}
