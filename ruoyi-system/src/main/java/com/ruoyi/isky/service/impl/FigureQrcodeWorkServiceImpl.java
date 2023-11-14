package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureQrcodeWorkMapper;
import com.ruoyi.isky.domain.FigureQrcodeWork;
import com.ruoyi.isky.service.IFigureQrcodeWorkService;
import com.ruoyi.common.core.text.Convert;

/**
 * 二维码内容 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureQrcodeWorkServiceImpl implements IFigureQrcodeWorkService 
{
	@Autowired
	private FigureQrcodeWorkMapper figureQrcodeWorkMapper;

	/**
     * 查询二维码内容信息
     * 
     * @param id 二维码内容ID
     * @return 二维码内容信息
     */
    @Override
	public FigureQrcodeWork selectFigureQrcodeWorkById(Integer id)
	{
	    return figureQrcodeWorkMapper.selectFigureQrcodeWorkById(id);
	}
	
	/**
     * 查询二维码内容列表
     * 
     * @param figureQrcodeWork 二维码内容信息
     * @return 二维码内容集合
     */
	@Override
	public List<FigureQrcodeWork> selectFigureQrcodeWorkList(FigureQrcodeWork figureQrcodeWork)
	{
	    return figureQrcodeWorkMapper.selectFigureQrcodeWorkList(figureQrcodeWork);
	}
	
    /**
     * 新增二维码内容
     * 
     * @param figureQrcodeWork 二维码内容信息
     * @return 结果
     */
	@Override
	public int insertFigureQrcodeWork(FigureQrcodeWork figureQrcodeWork)
	{
	    return figureQrcodeWorkMapper.insertFigureQrcodeWork(figureQrcodeWork);
	}
	
	/**
     * 修改二维码内容
     * 
     * @param figureQrcodeWork 二维码内容信息
     * @return 结果
     */
	@Override
	public int updateFigureQrcodeWork(FigureQrcodeWork figureQrcodeWork)
	{
	    return figureQrcodeWorkMapper.updateFigureQrcodeWork(figureQrcodeWork);
	}

	/**
     * 删除二维码内容对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureQrcodeWorkByIds(String ids)
	{
		return figureQrcodeWorkMapper.deleteFigureQrcodeWorkByIds(Convert.toStrArray(ids));
	}
	
}
