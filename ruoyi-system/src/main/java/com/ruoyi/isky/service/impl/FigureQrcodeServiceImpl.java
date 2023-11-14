package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureQrcodeMapper;
import com.ruoyi.isky.domain.FigureQrcode;
import com.ruoyi.isky.service.IFigureQrcodeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 二维码 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureQrcodeServiceImpl implements IFigureQrcodeService 
{
	@Autowired
	private FigureQrcodeMapper figureQrcodeMapper;

	/**
     * 查询二维码信息
     * 
     * @param id 二维码ID
     * @return 二维码信息
     */
    @Override
	public FigureQrcode selectFigureQrcodeById(Integer id)
	{
	    return figureQrcodeMapper.selectFigureQrcodeById(id);
	}
	
	/**
     * 查询二维码列表
     * 
     * @param figureQrcode 二维码信息
     * @return 二维码集合
     */
	@Override
	public List<FigureQrcode> selectFigureQrcodeList(FigureQrcode figureQrcode)
	{
	    return figureQrcodeMapper.selectFigureQrcodeList(figureQrcode);
	}
	
    /**
     * 新增二维码
     * 
     * @param figureQrcode 二维码信息
     * @return 结果
     */
	@Override
	public int insertFigureQrcode(FigureQrcode figureQrcode)
	{
	    return figureQrcodeMapper.insertFigureQrcode(figureQrcode);
	}
	
	/**
     * 修改二维码
     * 
     * @param figureQrcode 二维码信息
     * @return 结果
     */
	@Override
	public int updateFigureQrcode(FigureQrcode figureQrcode)
	{
	    return figureQrcodeMapper.updateFigureQrcode(figureQrcode);
	}

	/**
     * 删除二维码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureQrcodeByIds(String ids)
	{
		return figureQrcodeMapper.deleteFigureQrcodeByIds(Convert.toStrArray(ids));
	}
	
}
