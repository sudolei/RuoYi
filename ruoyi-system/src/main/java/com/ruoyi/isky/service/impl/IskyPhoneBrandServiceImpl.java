package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.IskyPhoneBrandMapper;
import com.ruoyi.isky.domain.IskyPhoneBrand;
import com.ruoyi.isky.service.IIskyPhoneBrandService;
import com.ruoyi.common.core.text.Convert;

/**
 * 品牌 服务层实现
 * 
 * @author sunlei
 * @date 2019-08-21
 */
@Service
public class IskyPhoneBrandServiceImpl implements IIskyPhoneBrandService 
{
	@Autowired
	private IskyPhoneBrandMapper iskyPhoneBrandMapper;

	/**
     * 查询品牌信息
     * 
     * @param id 品牌ID
     * @return 品牌信息
     */
    @Override
	public IskyPhoneBrand selectIskyPhoneBrandById(Integer id)
	{
	    return iskyPhoneBrandMapper.selectIskyPhoneBrandById(id);
	}
	
	/**
     * 查询品牌列表
     * 
     * @param iskyPhoneBrand 品牌信息
     * @return 品牌集合
     */
	@Override
	public List<IskyPhoneBrand> selectIskyPhoneBrandList(IskyPhoneBrand iskyPhoneBrand)
	{
	    return iskyPhoneBrandMapper.selectIskyPhoneBrandList(iskyPhoneBrand);
	}
	
    /**
     * 新增品牌
     * 
     * @param iskyPhoneBrand 品牌信息
     * @return 结果
     */
	@Override
	public int insertIskyPhoneBrand(IskyPhoneBrand iskyPhoneBrand)
	{
	    return iskyPhoneBrandMapper.insertIskyPhoneBrand(iskyPhoneBrand);
	}
	
	/**
     * 修改品牌
     * 
     * @param iskyPhoneBrand 品牌信息
     * @return 结果
     */
	@Override
	public int updateIskyPhoneBrand(IskyPhoneBrand iskyPhoneBrand)
	{
	    return iskyPhoneBrandMapper.updateIskyPhoneBrand(iskyPhoneBrand);
	}

	/**
     * 删除品牌对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIskyPhoneBrandByIds(String ids)
	{
		return iskyPhoneBrandMapper.deleteIskyPhoneBrandByIds(Convert.toStrArray(ids));
	}
	
}
