package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureQrcodessMapper;
import com.ruoyi.isky.domain.FigureQrcodess;
import com.ruoyi.isky.service.IFigureQrcodessService;
import com.ruoyi.common.core.text.Convert;

/**
 * 二维码 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureQrcodessServiceImpl implements IFigureQrcodessService 
{
	@Autowired
	private FigureQrcodessMapper figureQrcodessMapper;

	/**
     * 查询二维码信息
     * 
     * @param id 二维码ID
     * @return 二维码信息
     */
    @Override
	public FigureQrcodess selectFigureQrcodessById(Integer id)
	{
	    return figureQrcodessMapper.selectFigureQrcodessById(id);
	}
	
	/**
     * 查询二维码列表
     * 
     * @param figureQrcodess 二维码信息
     * @return 二维码集合
     */
	@Override
	public List<FigureQrcodess> selectFigureQrcodessList(FigureQrcodess figureQrcodess)
	{
	    return figureQrcodessMapper.selectFigureQrcodessList(figureQrcodess);
	}
	
    /**
     * 新增二维码
     * 
     * @param figureQrcodess 二维码信息
     * @return 结果
     */
	@Override
	public int insertFigureQrcodess(FigureQrcodess figureQrcodess)
	{
	    return figureQrcodessMapper.insertFigureQrcodess(figureQrcodess);
	}
	
	/**
     * 修改二维码
     * 
     * @param figureQrcodess 二维码信息
     * @return 结果
     */
	@Override
	public int updateFigureQrcodess(FigureQrcodess figureQrcodess)
	{
	    return figureQrcodessMapper.updateFigureQrcodess(figureQrcodess);
	}

	/**
     * 删除二维码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureQrcodessByIds(String ids)
	{
		return figureQrcodessMapper.deleteFigureQrcodessByIds(Convert.toStrArray(ids));
	}
	
}
