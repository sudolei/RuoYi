package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.IskyPhoneWorkImage;
import java.util.List;	

/**
 * 图片地址 数据层
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public interface IskyPhoneWorkImageMapper 
{
	/**
     * 查询图片地址信息
     * 
     * @param id 图片地址ID
     * @return 图片地址信息
     */
	public IskyPhoneWorkImage selectIskyPhoneWorkImageById(Integer id);
	
	/**
     * 查询图片地址列表
     * 
     * @param iskyPhoneWorkImage 图片地址信息
     * @return 图片地址集合
     */
	public List<IskyPhoneWorkImage> selectIskyPhoneWorkImageList(IskyPhoneWorkImage iskyPhoneWorkImage);
	
	/**
     * 新增图片地址
     * 
     * @param iskyPhoneWorkImage 图片地址信息
     * @return 结果
     */
	public int insertIskyPhoneWorkImage(IskyPhoneWorkImage iskyPhoneWorkImage);
	
	/**
     * 修改图片地址
     * 
     * @param iskyPhoneWorkImage 图片地址信息
     * @return 结果
     */
	public int updateIskyPhoneWorkImage(IskyPhoneWorkImage iskyPhoneWorkImage);
	
	/**
     * 删除图片地址
     * 
     * @param id 图片地址ID
     * @return 结果
     */
	public int deleteIskyPhoneWorkImageById(Integer id);
	
	/**
     * 批量删除图片地址
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyPhoneWorkImageByIds(String[] ids);
	
}