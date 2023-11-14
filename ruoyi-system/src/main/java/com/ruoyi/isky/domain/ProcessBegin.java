package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 流程订单表 seg_process_begin
 * 
 * @author sunlei
 * @date 2019-10-15
 */
public class ProcessBegin extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 订单名称 */
	private String orderName;
	/** 确认信息 */
	private String confirm;
	/** 货款金额 */
	private String loanAmount;
	/** 支付金额 */
	private String payAmout;
	/** 交货时间 */
	private String deliveryTime;
	/** 客户信息 */
	private String customerInfo;
	/** 内页 */
	private String pages;
	/**  */
	private String pagesZzDate;
	/**  */
	private String pagesWcDate;
	/**  */
	private String pagesZzUser;
	/**  */
	private String pagesWcUser;
	/**  */
	private String pagesState;


	private String pageZzInfo;


	private String pagesWcInfo;

	/** 内页图片 */
	private String pagesImg;
	/** 底座 */
	private String base;
	/**  */
	private String baseZzDate;
	/**  */
	private String baseWcDate;
	/**  */
	private String baseZzUser;
	/**  */
	private String baseWcUser;
	/**  */
	private String baseState;

	private String baseZzInfo;

	private String baseWcInfo;

	/** 底座图片 */
	private String baseImg;
	/** 包装 */
	private String pack;
	/**  */
	private String packZzDate;
	/**  */
	private String packWcDate;
	/** 包装图片 */
	private String packImg;
	/**  */
	private String packZzUser;
	/**  */
	private String packWcUser;
	/**  */
	private String packState;

	private String packZzInfo;

	private String packWcInfo;

	/** 烫印 */
	private String thermoprint;
	/**  */
	private String thermoprintZzDate;
	/**  */
	private String thermoprintWcDate;
	/** 烫印图片 */
	private String thermoprintImg;
	/**  */
	private String thermoprintZzUser;
	/**  */
	private String thermoprintWcUser;
	/**  */
	private String thermoprintState;

	private String thermoprintZzInfo ;

	private String thermoprintWcInfo;
	/** 雕刻 */
	private String carving;
	/**  */
	private String carvingZzDate;
	/**  */
	private String carvingWcDate;
	/** 雕刻图片 */
	private String carvingImg;
	/**  */
	private String carvingZzUser;
	/**  */
	private String carvingWcUser;
	/**  */
	private String carvingState;

	private String carvingZzInfo;

	private String carvingWcInfo;

	public String getPagesWcInfo() {
		return pagesWcInfo;
	}

	public void setPagesWcInfo(String pagesWcInfo) {
		this.pagesWcInfo = pagesWcInfo;
	}

	public String getBaseZzInfo() {
		return baseZzInfo;
	}

	public void setBaseZzInfo(String baseZzInfo) {
		this.baseZzInfo = baseZzInfo;
	}

	public String getBaseWcInfo() {
		return baseWcInfo;
	}

	public void setBaseWcInfo(String baseWcInfo) {
		this.baseWcInfo = baseWcInfo;
	}

	public String getPackZzInfo() {
		return packZzInfo;
	}

	public void setPackZzInfo(String packZzInfo) {
		this.packZzInfo = packZzInfo;
	}

	public String getPackWcInfo() {
		return packWcInfo;
	}

	public void setPackWcInfo(String packWcInfo) {
		this.packWcInfo = packWcInfo;
	}

	public String getThermoprintZzInfo() {
		return thermoprintZzInfo;
	}

	public void setThermoprintZzInfo(String thermoprintZzInfo) {
		this.thermoprintZzInfo = thermoprintZzInfo;
	}

	public String getThermoprintWcInfo() {
		return thermoprintWcInfo;
	}

	public void setThermoprintWcInfo(String thermoprintWcInfo) {
		this.thermoprintWcInfo = thermoprintWcInfo;
	}

	public String getCarvingZzInfo() {
		return carvingZzInfo;
	}

	public void setCarvingZzInfo(String carvingZzInfo) {
		this.carvingZzInfo = carvingZzInfo;
	}

	public String getCarvingWcInfo() {
		return carvingWcInfo;
	}

	public void setCarvingWcInfo(String carvingWcInfo) {
		this.carvingWcInfo = carvingWcInfo;
	}

	/** 创建时间 */
	private Date createDate;
	/** 状态 0未开始 1制作中 2完成 */
	private String state;
	/** 销售 */
	private String sales;

	private String salesName;

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOrderName(String orderName) 
	{
		this.orderName = orderName;
	}

	public String getOrderName() 
	{
		return orderName;
	}
	public void setConfirm(String confirm) 
	{
		this.confirm = confirm;
	}

	public String getConfirm() 
	{
		return confirm;
	}
	public void setLoanAmount(String loanAmount) 
	{
		this.loanAmount = loanAmount;
	}

	public String getLoanAmount() 
	{
		return loanAmount;
	}
	public void setPayAmout(String payAmout) 
	{
		this.payAmout = payAmout;
	}

	public String getPayAmout() 
	{
		return payAmout;
	}
	public void setDeliveryTime(String deliveryTime) 
	{
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryTime() 
	{
		return deliveryTime;
	}
	public void setCustomerInfo(String customerInfo) 
	{
		this.customerInfo = customerInfo;
	}

	public String getCustomerInfo() 
	{
		return customerInfo;
	}
	public void setPages(String pages) 
	{
		this.pages = pages;
	}

	public String getPages() 
	{
		return pages;
	}
	public void setPagesZzDate(String pagesZzDate) 
	{
		this.pagesZzDate = pagesZzDate;
	}

	public String getPagesZzDate() 
	{
		return pagesZzDate;
	}
	public void setPagesWcDate(String pagesWcDate) 
	{
		this.pagesWcDate = pagesWcDate;
	}

	public String getPagesWcDate() 
	{
		return pagesWcDate;
	}
	public void setPagesZzUser(String pagesZzUser) 
	{
		this.pagesZzUser = pagesZzUser;
	}

	public String getPagesZzUser() 
	{
		return pagesZzUser;
	}
	public void setPagesWcUser(String pagesWcUser) 
	{
		this.pagesWcUser = pagesWcUser;
	}

	public String getPagesWcUser() 
	{
		return pagesWcUser;
	}
	public void setPagesState(String pagesState) 
	{
		this.pagesState = pagesState;
	}

	public String getPagesState() 
	{
		return pagesState;
	}
	public void setPagesImg(String pagesImg) 
	{
		this.pagesImg = pagesImg;
	}

	public String getPagesImg() 
	{
		return pagesImg;
	}
	public void setBase(String base) 
	{
		this.base = base;
	}

	public String getBase() 
	{
		return base;
	}
	public void setBaseZzDate(String baseZzDate) 
	{
		this.baseZzDate = baseZzDate;
	}

	public String getBaseZzDate() 
	{
		return baseZzDate;
	}
	public void setBaseWcDate(String baseWcDate) 
	{
		this.baseWcDate = baseWcDate;
	}


	public String getPageZzInfo() {
		return pageZzInfo;
	}

	public void setPageZzInfo(String pageZzInfo) {
		this.pageZzInfo = pageZzInfo;
	}


	public String getBaseWcDate() 
	{
		return baseWcDate;
	}
	public void setBaseZzUser(String baseZzUser) 
	{
		this.baseZzUser = baseZzUser;
	}

	public String getBaseZzUser() 
	{
		return baseZzUser;
	}
	public void setBaseWcUser(String baseWcUser) 
	{
		this.baseWcUser = baseWcUser;
	}

	public String getBaseWcUser() 
	{
		return baseWcUser;
	}
	public void setBaseState(String baseState) 
	{
		this.baseState = baseState;
	}

	public String getBaseState() 
	{
		return baseState;
	}
	public void setBaseImg(String baseImg) 
	{
		this.baseImg = baseImg;
	}

	public String getBaseImg() 
	{
		return baseImg;
	}
	public void setPack(String pack) 
	{
		this.pack = pack;
	}

	public String getPack() 
	{
		return pack;
	}
	public void setPackZzDate(String packZzDate) 
	{
		this.packZzDate = packZzDate;
	}

	public String getPackZzDate() 
	{
		return packZzDate;
	}
	public void setPackWcDate(String packWcDate) 
	{
		this.packWcDate = packWcDate;
	}

	public String getPackWcDate() 
	{
		return packWcDate;
	}
	public void setPackImg(String packImg) 
	{
		this.packImg = packImg;
	}

	public String getPackImg() 
	{
		return packImg;
	}
	public void setPackZzUser(String packZzUser) 
	{
		this.packZzUser = packZzUser;
	}

	public String getPackZzUser() 
	{
		return packZzUser;
	}
	public void setPackWcUser(String packWcUser) 
	{
		this.packWcUser = packWcUser;
	}

	public String getPackWcUser() 
	{
		return packWcUser;
	}
	public void setPackState(String packState) 
	{
		this.packState = packState;
	}

	public String getPackState() 
	{
		return packState;
	}
	public void setThermoprint(String thermoprint) 
	{
		this.thermoprint = thermoprint;
	}

	public String getThermoprint() 
	{
		return thermoprint;
	}
	public void setThermoprintZzDate(String thermoprintZzDate) 
	{
		this.thermoprintZzDate = thermoprintZzDate;
	}

	public String getThermoprintZzDate() 
	{
		return thermoprintZzDate;
	}
	public void setThermoprintWcDate(String thermoprintWcDate) 
	{
		this.thermoprintWcDate = thermoprintWcDate;
	}

	public String getThermoprintWcDate() 
	{
		return thermoprintWcDate;
	}
	public void setThermoprintImg(String thermoprintImg) 
	{
		this.thermoprintImg = thermoprintImg;
	}

	public String getThermoprintImg() 
	{
		return thermoprintImg;
	}
	public void setThermoprintZzUser(String thermoprintZzUser) 
	{
		this.thermoprintZzUser = thermoprintZzUser;
	}

	public String getThermoprintZzUser() 
	{
		return thermoprintZzUser;
	}
	public void setThermoprintWcUser(String thermoprintWcUser) 
	{
		this.thermoprintWcUser = thermoprintWcUser;
	}

	public String getThermoprintWcUser() 
	{
		return thermoprintWcUser;
	}
	public void setThermoprintState(String thermoprintState) 
	{
		this.thermoprintState = thermoprintState;
	}

	public String getThermoprintState() 
	{
		return thermoprintState;
	}
	public void setCarving(String carving) 
	{
		this.carving = carving;
	}

	public String getCarving() 
	{
		return carving;
	}
	public void setCarvingZzDate(String carvingZzDate) 
	{
		this.carvingZzDate = carvingZzDate;
	}

	public String getCarvingZzDate() 
	{
		return carvingZzDate;
	}
	public void setCarvingWcDate(String carvingWcDate) 
	{
		this.carvingWcDate = carvingWcDate;
	}

	public String getCarvingWcDate() 
	{
		return carvingWcDate;
	}
	public void setCarvingImg(String carvingImg) 
	{
		this.carvingImg = carvingImg;
	}

	public String getCarvingImg() 
	{
		return carvingImg;
	}
	public void setCarvingZzUser(String carvingZzUser) 
	{
		this.carvingZzUser = carvingZzUser;
	}

	public String getCarvingZzUser() 
	{
		return carvingZzUser;
	}
	public void setCarvingWcUser(String carvingWcUser) 
	{
		this.carvingWcUser = carvingWcUser;
	}

	public String getCarvingWcUser() 
	{
		return carvingWcUser;
	}
	public void setCarvingState(String carvingState) 
	{
		this.carvingState = carvingState;
	}

	public String getCarvingState() 
	{
		return carvingState;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setSales(String sales) 
	{
		this.sales = sales;
	}

	public String getSales() 
	{
		return sales;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderName", getOrderName())
            .append("confirm", getConfirm())
            .append("loanAmount", getLoanAmount())
            .append("payAmout", getPayAmout())
            .append("deliveryTime", getDeliveryTime())
            .append("customerInfo", getCustomerInfo())
            .append("pages", getPages())
            .append("pagesZzDate", getPagesZzDate())
            .append("pagesWcDate", getPagesWcDate())
            .append("pagesZzUser", getPagesZzUser())
            .append("pagesWcUser", getPagesWcUser())
            .append("pagesState", getPagesState())
            .append("pagesImg", getPagesImg())
            .append("base", getBase())
            .append("baseZzDate", getBaseZzDate())
            .append("baseWcDate", getBaseWcDate())
            .append("baseZzUser", getBaseZzUser())
            .append("baseWcUser", getBaseWcUser())
            .append("baseState", getBaseState())
            .append("baseImg", getBaseImg())
            .append("pack", getPack())
            .append("packZzDate", getPackZzDate())
            .append("packWcDate", getPackWcDate())
            .append("packImg", getPackImg())
            .append("packZzUser", getPackZzUser())
            .append("packWcUser", getPackWcUser())
            .append("packState", getPackState())
            .append("thermoprint", getThermoprint())
            .append("thermoprintZzDate", getThermoprintZzDate())
            .append("thermoprintWcDate", getThermoprintWcDate())
            .append("thermoprintImg", getThermoprintImg())
            .append("thermoprintZzUser", getThermoprintZzUser())
            .append("thermoprintWcUser", getThermoprintWcUser())
            .append("thermoprintState", getThermoprintState())
            .append("carving", getCarving())
            .append("carvingZzDate", getCarvingZzDate())
            .append("carvingWcDate", getCarvingWcDate())
            .append("carvingImg", getCarvingImg())
            .append("carvingZzUser", getCarvingZzUser())
            .append("carvingWcUser", getCarvingWcUser())
            .append("carvingState", getCarvingState())
            .append("createDate", getCreateDate())
            .append("remark", getRemark())
            .append("state", getState())
            .append("sales", getSales())
            .toString();
    }
}
