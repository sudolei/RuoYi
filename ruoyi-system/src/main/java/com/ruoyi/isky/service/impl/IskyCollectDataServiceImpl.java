package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyCollectDataMapper;
import com.ruoyi.isky.domain.IskyCollectData;
import com.ruoyi.isky.service.IIskyCollectDataService;
import com.ruoyi.common.core.text.Convert;

/**
 * 数据采集 服务层实现
 * 
 * @author sunlei
 * @date 2019-06-21
 */
@Service
public class IskyCollectDataServiceImpl implements IIskyCollectDataService 
{
	@Autowired
	private IskyCollectDataMapper iskyCollectDataMapper;

	/**
     * 查询数据采集信息
     * 
     * @param id 数据采集ID
     * @return 数据采集信息
     */
    @Override
	public IskyCollectData selectIskyCollectDataById(Integer id)
	{
	    return iskyCollectDataMapper.selectIskyCollectDataById(id);
	}
	
	/**
     * 查询数据采集列表
     * 
     * @param iskyCollectData 数据采集信息
     * @return 数据采集集合
     */
	@Override
	public List<IskyCollectData> selectIskyCollectDataList(IskyCollectData iskyCollectData)
	{
	    return iskyCollectDataMapper.selectIskyCollectDataList(iskyCollectData);
	}
	
    /**
     * 新增数据采集
     * 
     * @param iskyCollectData 数据采集信息
     * @return 结果
     */
	@Override
	public int insertIskyCollectData(IskyCollectData iskyCollectData)
	{
	    return iskyCollectDataMapper.insertIskyCollectData(iskyCollectData);
	}
	
	/**
     * 修改数据采集
     * 
     * @param iskyCollectData 数据采集信息
     * @return 结果
     */
	@Override
	public int updateIskyCollectData(IskyCollectData iskyCollectData)
	{
	    return iskyCollectDataMapper.updateIskyCollectData(iskyCollectData);
	}

	/**
     * 删除数据采集对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyCollectDataByIds(String ids)
	{
		return iskyCollectDataMapper.deleteIskyCollectDataByIds(Convert.toStrArray(ids));
	}
	
}
