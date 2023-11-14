package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.IskyCollectImg;
import java.util.List;	

/**
 * 采集数据图片 数据层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IskyCollectImgMapper 
{
	/**
     * 查询采集数据图片信息
     * 
     * @param id 采集数据图片ID
     * @return 采集数据图片信息
     */
	public IskyCollectImg selectIskyCollectImgById(Integer id);
	
	/**
     * 查询采集数据图片列表
     * 
     * @param iskyCollectImg 采集数据图片信息
     * @return 采集数据图片集合
     */
	public List<IskyCollectImg> selectIskyCollectImgList(IskyCollectImg iskyCollectImg);
	
	/**
     * 新增采集数据图片
     * 
     * @param iskyCollectImg 采集数据图片信息
     * @return 结果
     */
	public int insertIskyCollectImg(IskyCollectImg iskyCollectImg);
	
	/**
     * 修改采集数据图片
     * 
     * @param iskyCollectImg 采集数据图片信息
     * @return 结果
     */
	public int updateIskyCollectImg(IskyCollectImg iskyCollectImg);
	
	/**
     * 删除采集数据图片
     * 
     * @param id 采集数据图片ID
     * @return 结果
     */
	public int deleteIskyCollectImgById(Integer id);
	
	/**
     * 批量删除采集数据图片
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyCollectImgByIds(String[] ids);
	
}