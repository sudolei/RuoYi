package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyPhoneWorkImageMapper;
import com.ruoyi.isky.domain.IskyPhoneWorkImage;
import com.ruoyi.isky.service.IIskyPhoneWorkImageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 图片地址 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Service
public class IskyPhoneWorkImageServiceImpl implements IIskyPhoneWorkImageService 
{
	@Autowired
	private IskyPhoneWorkImageMapper iskyPhoneWorkImageMapper;

	/**
     * 查询图片地址信息
     * 
     * @param id 图片地址ID
     * @return 图片地址信息
     */
    @Override
	public IskyPhoneWorkImage selectIskyPhoneWorkImageById(Integer id)
	{
	    return iskyPhoneWorkImageMapper.selectIskyPhoneWorkImageById(id);
	}
	
	/**
     * 查询图片地址列表
     * 
     * @param iskyPhoneWorkImage 图片地址信息
     * @return 图片地址集合
     */
	@Override
	public List<IskyPhoneWorkImage> selectIskyPhoneWorkImageList(IskyPhoneWorkImage iskyPhoneWorkImage)
	{
	    return iskyPhoneWorkImageMapper.selectIskyPhoneWorkImageList(iskyPhoneWorkImage);
	}
	
    /**
     * 新增图片地址
     * 
     * @param iskyPhoneWorkImage 图片地址信息
     * @return 结果
     */
	@Override
	public int insertIskyPhoneWorkImage(IskyPhoneWorkImage iskyPhoneWorkImage)
	{
	    return iskyPhoneWorkImageMapper.insertIskyPhoneWorkImage(iskyPhoneWorkImage);
	}
	
	/**
     * 修改图片地址
     * 
     * @param iskyPhoneWorkImage 图片地址信息
     * @return 结果
     */
	@Override
	public int updateIskyPhoneWorkImage(IskyPhoneWorkImage iskyPhoneWorkImage)
	{
	    return iskyPhoneWorkImageMapper.updateIskyPhoneWorkImage(iskyPhoneWorkImage);
	}

	/**
     * 删除图片地址对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyPhoneWorkImageByIds(String ids)
	{
		return iskyPhoneWorkImageMapper.deleteIskyPhoneWorkImageByIds(Convert.toStrArray(ids));
	}
	
}
