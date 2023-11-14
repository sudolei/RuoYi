package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.FigureImagesMapper;
import com.ruoyi.isky.domain.FigureImages;
import com.ruoyi.isky.service.IFigureImagesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 图片集合 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class FigureImagesServiceImpl implements IFigureImagesService 
{
	@Autowired
	private FigureImagesMapper figureImagesMapper;

	/**
     * 查询图片集合信息
     * 
     * @param id 图片集合ID
     * @return 图片集合信息
     */
    @Override
	public FigureImages selectFigureImagesById(Integer id)
	{
	    return figureImagesMapper.selectFigureImagesById(id);
	}
	
	/**
     * 查询图片集合列表
     * 
     * @param figureImages 图片集合信息
     * @return 图片集合集合
     */
	@Override
	public List<FigureImages> selectFigureImagesList(FigureImages figureImages)
	{
	    return figureImagesMapper.selectFigureImagesList(figureImages);
	}
	
    /**
     * 新增图片集合
     * 
     * @param figureImages 图片集合信息
     * @return 结果
     */
	@Override
	public int insertFigureImages(FigureImages figureImages)
	{
	    return figureImagesMapper.insertFigureImages(figureImages);
	}
	
	/**
     * 修改图片集合
     * 
     * @param figureImages 图片集合信息
     * @return 结果
     */
	@Override
	public int updateFigureImages(FigureImages figureImages)
	{
	    return figureImagesMapper.updateFigureImages(figureImages);
	}

	/**
     * 删除图片集合对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFigureImagesByIds(String ids)
	{
		return figureImagesMapper.deleteFigureImagesByIds(Convert.toStrArray(ids));
	}
	
}
