package com.ruoyi.isky.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 传图订单表 seg_figure_order
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 用户编号 */
	private Integer userId;
	/**  */
	private String meteriaName;
	/** 数量 */
	private String picNum;
	/**  */
	private String dsm;
	/**  */
	private String orderId;
	/**  */
	private String meterialId;
	/**  */
	private Date createDate;
	/** 删除标示 */
	private String delFlag;
	/** 打印标示 */
	private String print;
	/**  */
	private String state;
	/**  */
	private String genre;

	private String taobao;

	private String telephone;

	private String username;


	private String fileName;

	private String fileTime;

	public String getFileTime() {
		return fileTime;
	}

	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getDsmList() {
		return dsmList;
	}

	public void setDsmList(List<String> dsmList) {
		this.dsmList = dsmList;
	}

	private List<String> dsmList ;

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	private String bno ;

	public String getTaobao() {
		return taobao;
	}

	public void setTaobao(String taobao) {
		this.taobao = taobao;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setMeteriaName(String meteriaName) 
	{
		this.meteriaName = meteriaName;
	}

	public String getMeteriaName() 
	{
		return meteriaName;
	}
	public void setPicNum(String picNum) 
	{
		this.picNum = picNum;
	}

	public String getPicNum() 
	{
		return picNum;
	}
	public void setDsm(String dsm) 
	{
		this.dsm = dsm;
	}

	public String getDsm() 
	{
		return dsm;
	}
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}
	public void setMeterialId(String meterialId) 
	{
		this.meterialId = meterialId;
	}

	public String getMeterialId() 
	{
		return meterialId;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setDelFlag(String delFlag)
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setPrint(String print) 
	{
		this.print = print;
	}

	public String getPrint() 
	{
		return print;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

	public String getGenre() 
	{
		return genre;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("meteriaName", getMeteriaName())
            .append("picNum", getPicNum())
            .append("dsm", getDsm())
            .append("orderId", getOrderId())
            .append("meterialId", getMeterialId())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("print", getPrint())
            .append("state", getState())
            .append("genre", getGenre())
            .toString();
    }
}
