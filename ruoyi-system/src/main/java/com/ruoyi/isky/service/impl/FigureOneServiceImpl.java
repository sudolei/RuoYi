package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureOneMapper;
import com.ruoyi.isky.domain.FigureOne;
import com.ruoyi.isky.service.IFigureOneService;
import com.ruoyi.common.core.text.Convert;

/**
 * 一次性二维码 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-03
 */
@Service
public class FigureOneServiceImpl implements IFigureOneService 
{
	@Autowired
	private FigureOneMapper figureOneMapper;

	/**
     * 查询一次性二维码信息
     * 
     * @param id 一次性二维码ID
     * @return 一次性二维码信息
     */
    @Override
	public FigureOne selectFigureOneById(Integer id)
	{
	    return figureOneMapper.selectFigureOneById(id);
	}
	
	/**
     * 查询一次性二维码列表
     * 
     * @param figureOne 一次性二维码信息
     * @return 一次性二维码集合
     */
	@Override
	public List<FigureOne> selectFigureOneList(FigureOne figureOne)
	{
	    return figureOneMapper.selectFigureOneList(figureOne);
	}
	
    /**
     * 新增一次性二维码
     * 
     * @param figureOne 一次性二维码信息
     * @return 结果
     */
	@Override
	public int insertFigureOne(FigureOne figureOne)
	{
	    return figureOneMapper.insertFigureOne(figureOne);
	}
	
	/**
     * 修改一次性二维码
     * 
     * @param figureOne 一次性二维码信息
     * @return 结果
     */
	@Override
	public int updateFigureOne(FigureOne figureOne)
	{
	    return figureOneMapper.updateFigureOne(figureOne);
	}

	/**
     * 删除一次性二维码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureOneByIds(String ids)
	{
		return figureOneMapper.deleteFigureOneByIds(Convert.toStrArray(ids));
	}
	
}
