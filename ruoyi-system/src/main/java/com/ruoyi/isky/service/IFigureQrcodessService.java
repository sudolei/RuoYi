package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureQrcodess;
import java.util.List;

/**
 * 二维码 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureQrcodessService 
{
	/**
     * 查询二维码信息
     * 
     * @param id 二维码ID
     * @return 二维码信息
     */
	public FigureQrcodess selectFigureQrcodessById(Integer id);
	
	/**
     * 查询二维码列表
     * 
     * @param figureQrcodess 二维码信息
     * @return 二维码集合
     */
	public List<FigureQrcodess> selectFigureQrcodessList(FigureQrcodess figureQrcodess);
	
	/**
     * 新增二维码
     * 
     * @param figureQrcodess 二维码信息
     * @return 结果
     */
	public int insertFigureQrcodess(FigureQrcodess figureQrcodess);
	
	/**
     * 修改二维码
     * 
     * @param figureQrcodess 二维码信息
     * @return 结果
     */
	public int updateFigureQrcodess(FigureQrcodess figureQrcodess);
		
	/**
     * 删除二维码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureQrcodessByIds(String ids);
	
}
