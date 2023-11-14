package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureLoginMapper;
import com.ruoyi.isky.domain.FigureLogin;
import com.ruoyi.isky.service.IFigureLoginService;
import com.ruoyi.common.core.text.Convert;

/**
 * 传图登录 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureLoginServiceImpl implements IFigureLoginService 
{
	@Autowired
	private FigureLoginMapper figureLoginMapper;

	/**
     * 查询传图登录信息
     * 
     * @param id 传图登录ID
     * @return 传图登录信息
     */
    @Override
	public FigureLogin selectFigureLoginById(Integer id)
	{
	    return figureLoginMapper.selectFigureLoginById(id);
	}
	
	/**
     * 查询传图登录列表
     * 
     * @param figureLogin 传图登录信息
     * @return 传图登录集合
     */
	@Override
	public List<FigureLogin> selectFigureLoginList(FigureLogin figureLogin)
	{
	    return figureLoginMapper.selectFigureLoginList(figureLogin);
	}
	
    /**
     * 新增传图登录
     * 
     * @param figureLogin 传图登录信息
     * @return 结果
     */
	@Override
	public int insertFigureLogin(FigureLogin figureLogin)
	{
	    return figureLoginMapper.insertFigureLogin(figureLogin);
	}
	
	/**
     * 修改传图登录
     * 
     * @param figureLogin 传图登录信息
     * @return 结果
     */
	@Override
	public int updateFigureLogin(FigureLogin figureLogin)
	{
	    return figureLoginMapper.updateFigureLogin(figureLogin);
	}

	/**
     * 删除传图登录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureLoginByIds(String ids)
	{
		return figureLoginMapper.deleteFigureLoginByIds(Convert.toStrArray(ids));
	}
	
}
