package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.ProcessUser;
import java.util.List;	

/**
 * 公司流程用户 数据层
 * 
 * @author sunlei
 * @date 2019-10-12
 */
public interface ProcessUserMapper 
{
	/**
     * 查询公司流程用户信息
     * 
     * @param id 公司流程用户ID
     * @return 公司流程用户信息
     */
	public ProcessUser selectProcessUserById(Integer id);
	
	/**
     * 查询公司流程用户列表
     * 
     * @param processUser 公司流程用户信息
     * @return 公司流程用户集合
     */
	public List<ProcessUser> selectProcessUserList(ProcessUser processUser);
	
	/**
     * 新增公司流程用户
     * 
     * @param processUser 公司流程用户信息
     * @return 结果
     */
	public int insertProcessUser(ProcessUser processUser);
	
	/**
     * 修改公司流程用户
     * 
     * @param processUser 公司流程用户信息
     * @return 结果
     */
	public int updateProcessUser(ProcessUser processUser);
	
	/**
     * 删除公司流程用户
     * 
     * @param id 公司流程用户ID
     * @return 结果
     */
	public int deleteProcessUserById(Integer id);
	
	/**
     * 批量删除公司流程用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProcessUserByIds(String[] ids);
	
}