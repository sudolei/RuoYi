package com.ruoyi.isky.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 印数统计表 seg_figure_yzfrom
 *
 * @author sunlei
 * @date 2020-06-24
 */
public class FigureYzfrom extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户
     */
    private Integer userId;
    /**
     * 4C+4C
     */

    @Excel(name = "4c+4c")
    private Integer c8;
    /**
     * 4C+1C
     */
    @Excel(name = "4c+1c")
    private Integer c5;
    /**
     * 4C+0
     */
    @Excel(name = "4c+0")
    private Integer c4;
    /**
     * 1C+1C
     */
    private Integer c2;
    /**
     * 1C+0
     */
    private Integer c1;
    /**
     * 印刷总张数
     */
    private Integer call;
    /**
     * 浪费4C+4C
     */
    private Integer c8f;
    /**
     * 浪费4C+1C
     */
    private Integer c5f;
    /**
     * 浪费4C+0
     */
    private Integer c4f;
    /**
     * 浪费1C+1C
     */
    private Integer c2f;
    /**
     * 浪费1C+0
     */
    private Integer c1f;
    /**
     * 浪费总张数
     */
    private Integer callf;

    private Integer lc8;
    /**
     * 4C+1C
     */
    private Integer lc5;
    /**
     * 4C+0
     */
    private Integer lc4;
    /**
     * 1C+1C
     */
    private Integer lc2;
    /**
     * 1C+0
     */
    private Integer lc1;

    public Integer getLc8() {
        return lc8;
    }

    public void setLc8(Integer lc8) {
        this.lc8 = lc8;
    }

    public Integer getLc5() {
        return lc5;
    }

    public void setLc5(Integer lc5) {
        this.lc5 = lc5;
    }

    public Integer getLc4() {
        return lc4;
    }

    public void setLc4(Integer lc4) {
        this.lc4 = lc4;
    }

    public Integer getLc2() {
        return lc2;
    }

    public void setLc2(Integer lc2) {
        this.lc2 = lc2;
    }

    public Integer getLc1() {
        return lc1;
    }

    public void setLc1(Integer lc1) {
        this.lc1 = lc1;
    }

    /**
     * 浪费4C+4C
     */
    private Integer c8qj;
    /**
     * 浪费4C+1C
     */
    private Integer c5qj;
    /**
     * 浪费4C+0
     */
    private Integer c4qj;
    /**
     * 浪费1C+1C
     */
    private Integer c2qj;
    /**
     * 浪费1C+0
     */
    private Integer c1qj;
    /**
     * 浪费总张数
     */
    private Integer callqj;


    /**
     * 浪费4C+4C
     */
    private Integer c8kz;
    /**
     * 浪费4C+1C
     */
    private Integer c5kz;
    /**
     * 浪费4C+0
     */
    private Integer c4kz;
    /**
     * 浪费1C+1C
     */
    private Integer c2kz;
    /**
     * 浪费1C+0
     */
    private Integer c1kz;
    /**
     * 浪费总张数
     */
    private Integer callkz;


    /**
     * 浪费4C+4C
     */
    private Integer c8ys;
    /**
     * 浪费4C+1C
     */
    private Integer c5ys;
    /**
     * 浪费4C+0
     */
    private Integer c4ys;
    /**
     * 浪费1C+1C
     */
    private Integer c2ys;
    /**
     * 浪费1C+0
     */
    private Integer c1ys;
    /**
     * 浪费总张数
     */
    private Integer callys;


    /**
     * 浪费4C+4C
     */
    private Integer c8kf;
    /**
     * 浪费4C+1C
     */
    private Integer c5kf;
    /**
     * 浪费4C+0
     */
    private Integer c4kf;
    /**
     * 浪费1C+1C
     */
    private Integer c2kf;
    /**
     * 浪费1C+0
     */
    private Integer c1kf;
    /**
     * 浪费总张数
     */
    private Integer callkf;


    /**
     * 浪费4C+4C
     */
    private Integer c8sc;
    /**
     * 浪费4C+1C
     */
    private Integer c5sc;
    /**
     * 浪费4C+0
     */
    private Integer c4sc;
    /**
     * 浪费1C+1C
     */
    private Integer c2sc;
    /**
     * 浪费1C+0
     */
    private Integer c1sc;
    /**
     * 浪费总张数
     */
    private Integer callsc;


    /**
     * 浪费4C+4C
     */
    private Integer c8fh;
    /**
     * 浪费4C+1C
     */
    private Integer c5fh;
    /**
     * 浪费4C+0
     */
    private Integer c4fh;
    /**
     * 浪费1C+1C
     */
    private Integer c2fh;
    /**
     * 浪费1C+0
     */
    private Integer c1fh;
    /**
     * 浪费总张数
     */
    private Integer callfh;


    private Integer zys;
    private Integer zysd;
    private Integer zyss;

    private Integer wys;
    private Integer wysd;
    private Integer wyss;

    private Integer zds;
    private Integer wds;


    private Integer bk;
    private Integer xk;
    private Integer xg;
    private Integer gm;
    private Integer gc;
    private Integer zg;
    private Integer yf;
    private Integer tb;
    private Integer lk;
    private Integer cz;
    private Integer mtx;

    public Integer getMtx() {
        return mtx;
    }

    public void setMtx(Integer mtx) {
        this.mtx = mtx;
    }

    public Integer getCz() {
        return cz;
    }

    public void setCz(Integer cz) {
        this.cz = cz;
    }

    private String ctime;

    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getBk() {
        return bk;
    }

    public void setBk(Integer bk) {
        this.bk = bk;
    }

    public Integer getXk() {
        return xk;
    }

    public void setXk(Integer xk) {
        this.xk = xk;
    }

    public Integer getXg() {
        return xg;
    }

    public void setXg(Integer xg) {
        this.xg = xg;
    }

    public Integer getGm() {
        return gm;
    }

    public void setGm(Integer gm) {
        this.gm = gm;
    }

    public Integer getGc() {
        return gc;
    }

    public void setGc(Integer gc) {
        this.gc = gc;
    }

    public Integer getZg() {
        return zg;
    }

    public void setZg(Integer zg) {
        this.zg = zg;
    }

    public Integer getYf() {
        return yf;
    }

    public void setYf(Integer yf) {
        this.yf = yf;
    }

    public Integer getTb() {
        return tb;
    }

    public void setTb(Integer tb) {
        this.tb = tb;
    }

    public Integer getLk() {
        return lk;
    }

    public void setLk(Integer lk) {
        this.lk = lk;
    }

    public Integer getZys() {
        return zys;
    }

    public void setZys(Integer zys) {
        this.zys = zys;
    }

    public Integer getZysd() {
        return zysd;
    }

    public void setZysd(Integer zysd) {
        this.zysd = zysd;
    }

    public Integer getZyss() {
        return zyss;
    }

    public void setZyss(Integer zyss) {
        this.zyss = zyss;
    }

    public Integer getWys() {
        return wys;
    }

    public void setWys(Integer wys) {
        this.wys = wys;
    }

    public Integer getWysd() {
        return wysd;
    }

    public void setWysd(Integer wysd) {
        this.wysd = wysd;
    }

    public Integer getWyss() {
        return wyss;
    }

    public void setWyss(Integer wyss) {
        this.wyss = wyss;
    }

    public Integer getZds() {
        return zds;
    }

    public void setZds(Integer zds) {
        this.zds = zds;
    }

    public Integer getWds() {
        return wds;
    }

    public void setWds(Integer wds) {
        this.wds = wds;
    }


    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getC8qj() {
        return c8qj;
    }

    public void setC8qj(Integer c8qj) {
        this.c8qj = c8qj;
    }

    public Integer getC5qj() {
        return c5qj;
    }

    public void setC5qj(Integer c5qj) {
        this.c5qj = c5qj;
    }

    public Integer getC4qj() {
        return c4qj;
    }

    public void setC4qj(Integer c4qj) {
        this.c4qj = c4qj;
    }

    public Integer getC2qj() {
        return c2qj;
    }

    public void setC2qj(Integer c2qj) {
        this.c2qj = c2qj;
    }

    public Integer getC1qj() {
        return c1qj;
    }

    public void setC1qj(Integer c1qj) {
        this.c1qj = c1qj;
    }

    public Integer getCallqj() {
        return callqj;
    }

    public void setCallqj(Integer callqj) {
        this.callqj = callqj;
    }

    public Integer getC8kz() {
        return c8kz;
    }

    public void setC8kz(Integer c8kz) {
        this.c8kz = c8kz;
    }

    public Integer getC5kz() {
        return c5kz;
    }

    public void setC5kz(Integer c5kz) {
        this.c5kz = c5kz;
    }

    public Integer getC4kz() {
        return c4kz;
    }

    public void setC4kz(Integer c4kz) {
        this.c4kz = c4kz;
    }

    public Integer getC2kz() {
        return c2kz;
    }

    public void setC2kz(Integer c2kz) {
        this.c2kz = c2kz;
    }

    public Integer getC1kz() {
        return c1kz;
    }

    public void setC1kz(Integer c1kz) {
        this.c1kz = c1kz;
    }

    public Integer getCallkz() {
        return callkz;
    }

    public void setCallkz(Integer callkz) {
        this.callkz = callkz;
    }

    public Integer getC8ys() {
        return c8ys;
    }

    public void setC8ys(Integer c8ys) {
        this.c8ys = c8ys;
    }

    public Integer getC5ys() {
        return c5ys;
    }

    public void setC5ys(Integer c5ys) {
        this.c5ys = c5ys;
    }

    public Integer getC4ys() {
        return c4ys;
    }

    public void setC4ys(Integer c4ys) {
        this.c4ys = c4ys;
    }

    public Integer getC2ys() {
        return c2ys;
    }

    public void setC2ys(Integer c2ys) {
        this.c2ys = c2ys;
    }

    public Integer getC1ys() {
        return c1ys;
    }

    public void setC1ys(Integer c1ys) {
        this.c1ys = c1ys;
    }

    public Integer getCallys() {
        return callys;
    }

    public void setCallys(Integer callys) {
        this.callys = callys;
    }

    public Integer getC8kf() {
        return c8kf;
    }

    public void setC8kf(Integer c8kf) {
        this.c8kf = c8kf;
    }

    public Integer getC5kf() {
        return c5kf;
    }

    public void setC5kf(Integer c5kf) {
        this.c5kf = c5kf;
    }

    public Integer getC4kf() {
        return c4kf;
    }

    public void setC4kf(Integer c4kf) {
        this.c4kf = c4kf;
    }

    public Integer getC2kf() {
        return c2kf;
    }

    public void setC2kf(Integer c2kf) {
        this.c2kf = c2kf;
    }

    public Integer getC1kf() {
        return c1kf;
    }

    public void setC1kf(Integer c1kf) {
        this.c1kf = c1kf;
    }

    public Integer getCallkf() {
        return callkf;
    }

    public void setCallkf(Integer callkf) {
        this.callkf = callkf;
    }

    public Integer getC8sc() {
        return c8sc;
    }

    public void setC8sc(Integer c8sc) {
        this.c8sc = c8sc;
    }

    public Integer getC5sc() {
        return c5sc;
    }

    public void setC5sc(Integer c5sc) {
        this.c5sc = c5sc;
    }

    public Integer getC4sc() {
        return c4sc;
    }

    public void setC4sc(Integer c4sc) {
        this.c4sc = c4sc;
    }

    public Integer getC2sc() {
        return c2sc;
    }

    public void setC2sc(Integer c2sc) {
        this.c2sc = c2sc;
    }

    public Integer getC1sc() {
        return c1sc;
    }

    public void setC1sc(Integer c1sc) {
        this.c1sc = c1sc;
    }

    public Integer getCallsc() {
        return callsc;
    }

    public void setCallsc(Integer callsc) {
        this.callsc = callsc;
    }

    public Integer getC8fh() {
        return c8fh;
    }

    public void setC8fh(Integer c8fh) {
        this.c8fh = c8fh;
    }

    public Integer getC5fh() {
        return c5fh;
    }

    public void setC5fh(Integer c5fh) {
        this.c5fh = c5fh;
    }

    public Integer getC4fh() {
        return c4fh;
    }

    public void setC4fh(Integer c4fh) {
        this.c4fh = c4fh;
    }

    public Integer getC2fh() {
        return c2fh;
    }

    public void setC2fh(Integer c2fh) {
        this.c2fh = c2fh;
    }

    public Integer getC1fh() {
        return c1fh;
    }

    public void setC1fh(Integer c1fh) {
        this.c1fh = c1fh;
    }

    public Integer getCallfh() {
        return callfh;
    }

    public void setCallfh(Integer callfh) {
        this.callfh = callfh;
    }

    /**  */
    private Date createDate;
    /**  */
    private Date updateDate;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setC8(Integer c8) {
        this.c8 = c8;
    }

    public Integer getC8() {
        return c8;
    }

    public void setC5(Integer c5) {
        this.c5 = c5;
    }

    public Integer getC5() {
        return c5;
    }

    public void setC4(Integer c4) {
        this.c4 = c4;
    }

    public Integer getC4() {
        return c4;
    }

    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    public Integer getC2() {
        return c2;
    }

    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    public Integer getC1() {
        return c1;
    }

    public void setCall(Integer call) {
        this.call = call;
    }

    public Integer getCall() {
        return call;
    }

    public void setC8f(Integer c8f) {
        this.c8f = c8f;
    }

    public Integer getC8f() {
        return c8f;
    }

    public void setC5f(Integer c5f) {
        this.c5f = c5f;
    }

    public Integer getC5f() {
        return c5f;
    }

    public void setC4f(Integer c4f) {
        this.c4f = c4f;
    }

    public Integer getC4f() {
        return c4f;
    }

    public void setC2f(Integer c2f) {
        this.c2f = c2f;
    }

    public Integer getC2f() {
        return c2f;
    }

    public void setC1f(Integer c1f) {
        this.c1f = c1f;
    }

    public Integer getC1f() {
        return c1f;
    }

    public void setCallf(Integer callf) {
        this.callf = callf;
    }

    public Integer getCallf() {
        return callf;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("c8", getC8())
                .append("c5", getC5())
                .append("c4", getC4())
                .append("c2", getC2())
                .append("c1", getC1())
                .append("call", getCall())
                .append("c8f", getC8f())
                .append("c5f", getC5f())
                .append("c4f", getC4f())
                .append("c2f", getC2f())
                .append("c1f", getC1f())
                .append("callf", getCallf())
                .append("createBy", getCreateBy())
                .append("createDate", getCreateDate())
                .append("updateBy", getUpdateBy())
                .append("updateDate", getUpdateDate())
                .append("remark", getRemark())
                .toString();
    }
}
