package com.ruoyi.isky.service;

import com.ruoyi.isky.domain.FigureQrcode;
import java.util.List;

/**
 * 二维码 服务层
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public interface IFigureQrcodeService 
{
	/**
     * 查询二维码信息
     * 
     * @param id 二维码ID
     * @return 二维码信息
     */
	public FigureQrcode selectFigureQrcodeById(Integer id);
	
	/**
     * 查询二维码列表
     * 
     * @param figureQrcode 二维码信息
     * @return 二维码集合
     */
	public List<FigureQrcode> selectFigureQrcodeList(FigureQrcode figureQrcode);
	
	/**
     * 新增二维码
     * 
     * @param figureQrcode 二维码信息
     * @return 结果
     */
	public int insertFigureQrcode(FigureQrcode figureQrcode);
	
	/**
     * 修改二维码
     * 
     * @param figureQrcode 二维码信息
     * @return 结果
     */
	public int updateFigureQrcode(FigureQrcode figureQrcode);
		
	/**
     * 删除二维码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFigureQrcodeByIds(String ids);
	
}
