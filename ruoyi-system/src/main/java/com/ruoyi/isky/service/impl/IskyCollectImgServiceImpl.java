package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyCollectImgMapper;
import com.ruoyi.isky.domain.IskyCollectImg;
import com.ruoyi.isky.service.IIskyCollectImgService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采集数据图片 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class IskyCollectImgServiceImpl implements IIskyCollectImgService 
{
	@Autowired
	private IskyCollectImgMapper iskyCollectImgMapper;

	/**
     * 查询采集数据图片信息
     * 
     * @param id 采集数据图片ID
     * @return 采集数据图片信息
     */
    @Override
	public IskyCollectImg selectIskyCollectImgById(Integer id)
	{
	    return iskyCollectImgMapper.selectIskyCollectImgById(id);
	}
	
	/**
     * 查询采集数据图片列表
     * 
     * @param iskyCollectImg 采集数据图片信息
     * @return 采集数据图片集合
     */
	@Override
	public List<IskyCollectImg> selectIskyCollectImgList(IskyCollectImg iskyCollectImg)
	{
	    return iskyCollectImgMapper.selectIskyCollectImgList(iskyCollectImg);
	}
	
    /**
     * 新增采集数据图片
     * 
     * @param iskyCollectImg 采集数据图片信息
     * @return 结果
     */
	@Override
	public int insertIskyCollectImg(IskyCollectImg iskyCollectImg)
	{
	    return iskyCollectImgMapper.insertIskyCollectImg(iskyCollectImg);
	}
	
	/**
     * 修改采集数据图片
     * 
     * @param iskyCollectImg 采集数据图片信息
     * @return 结果
     */
	@Override
	public int updateIskyCollectImg(IskyCollectImg iskyCollectImg)
	{
	    return iskyCollectImgMapper.updateIskyCollectImg(iskyCollectImg);
	}

	/**
     * 删除采集数据图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyCollectImgByIds(String ids)
	{
		return iskyCollectImgMapper.deleteIskyCollectImgByIds(Convert.toStrArray(ids));
	}
	
}
