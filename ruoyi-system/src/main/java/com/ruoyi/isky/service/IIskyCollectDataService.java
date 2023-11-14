package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.IskyCollectData;
import java.util.List;

/**
 * 数据采集 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IIskyCollectDataService 
{
	/**
     * 查询数据采集信息
     * 
     * @param id 数据采集ID
     * @return 数据采集信息
     */
	public IskyCollectData selectIskyCollectDataById(Integer id);
	
	/**
     * 查询数据采集列表
     * 
     * @param iskyCollectData 数据采集信息
     * @return 数据采集集合
     */
	public List<IskyCollectData> selectIskyCollectDataList(IskyCollectData iskyCollectData);
	
	/**
     * 新增数据采集
     * 
     * @param iskyCollectData 数据采集信息
     * @return 结果
     */
	public int insertIskyCollectData(IskyCollectData iskyCollectData);
	
	/**
     * 修改数据采集
     * 
     * @param iskyCollectData 数据采集信息
     * @return 结果
     */
	public int updateIskyCollectData(IskyCollectData iskyCollectData);
		
	/**
     * 删除数据采集信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIskyCollectDataByIds(String ids);
	
}
