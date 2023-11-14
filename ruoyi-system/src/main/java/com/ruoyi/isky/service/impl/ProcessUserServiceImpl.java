package com.ruoyi.isky.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.isky.mapper.ProcessUserMapper;
import com.ruoyi.isky.domain.ProcessUser;
import com.ruoyi.isky.service.IProcessUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 公司流程用户 服务层实现
 * 
 * @author sunlei
 * @date 2019-10-12
 */
@Service
public class ProcessUserServiceImpl implements IProcessUserService 
{
	@Autowired
	private ProcessUserMapper processUserMapper;

	/**
     * 查询公司流程用户信息
     * 
     * @param id 公司流程用户ID
     * @return 公司流程用户信息
     */
    @Override
	public ProcessUser selectProcessUserById(Integer id)
	{
	    return processUserMapper.selectProcessUserById(id);
	}
	
	/**
     * 查询公司流程用户列表
     * 
     * @param processUser 公司流程用户信息
     * @return 公司流程用户集合
     */
	@Override
	public List<ProcessUser> selectProcessUserList(ProcessUser processUser)
	{
	    return processUserMapper.selectProcessUserList(processUser);
	}
	
    /**
     * 新增公司流程用户
     * 
     * @param processUser 公司流程用户信息
     * @return 结果
     */
	@Override
	public int insertProcessUser(ProcessUser processUser)
	{
	    return processUserMapper.insertProcessUser(processUser);
	}
	
	/**
     * 修改公司流程用户
     * 
     * @param processUser 公司流程用户信息
     * @return 结果
     */
	@Override
	public int updateProcessUser(ProcessUser processUser)
	{
	    return processUserMapper.updateProcessUser(processUser);
	}

	/**
     * 删除公司流程用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProcessUserByIds(String ids)
	{
		return processUserMapper.deleteProcessUserByIds(Convert.toStrArray(ids));
	}
	
}
