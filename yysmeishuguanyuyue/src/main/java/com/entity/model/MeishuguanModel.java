package com.entity.model;

import com.entity.MeishuguanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 美术馆
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class MeishuguanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 美术馆编号
     */
    private String meishuguanUuidNumber;


    /**
     * 美术馆名称
     */
    private String meishuguanName;


    /**
     * 美术馆照片
     */
    private String meishuguanPhoto;


    /**
     * 美术馆地点
     */
    private String meishuguanAddress;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 美术馆类型
     */
    private Integer meishuguanTypes;


    /**
     * 门票数量
     */
    private Integer meishuguanKucunNumber;


    /**
     * 门票价格
     */
    private Double meishuguanNewMoney;


    /**
     * 美术馆热度
     */
    private Integer meishuguanClicknum;


    /**
     * 结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jieshuTime;


    /**
     * 美术馆介绍
     */
    private String meishuguanContent;


    /**
     * 逻辑删除
     */
    private Integer meishuguanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow homeMain
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：美术馆编号
	 */
    public String getMeishuguanUuidNumber() {
        return meishuguanUuidNumber;
    }


    /**
	 * 设置：美术馆编号
	 */
    public void setMeishuguanUuidNumber(String meishuguanUuidNumber) {
        this.meishuguanUuidNumber = meishuguanUuidNumber;
    }
    /**
	 * 获取：美术馆名称
	 */
    public String getMeishuguanName() {
        return meishuguanName;
    }


    /**
	 * 设置：美术馆名称
	 */
    public void setMeishuguanName(String meishuguanName) {
        this.meishuguanName = meishuguanName;
    }
    /**
	 * 获取：美术馆照片
	 */
    public String getMeishuguanPhoto() {
        return meishuguanPhoto;
    }


    /**
	 * 设置：美术馆照片
	 */
    public void setMeishuguanPhoto(String meishuguanPhoto) {
        this.meishuguanPhoto = meishuguanPhoto;
    }
    /**
	 * 获取：美术馆地点
	 */
    public String getMeishuguanAddress() {
        return meishuguanAddress;
    }


    /**
	 * 设置：美术馆地点
	 */
    public void setMeishuguanAddress(String meishuguanAddress) {
        this.meishuguanAddress = meishuguanAddress;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 设置：赞
	 */
    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 设置：踩
	 */
    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：美术馆类型
	 */
    public Integer getMeishuguanTypes() {
        return meishuguanTypes;
    }


    /**
	 * 设置：美术馆类型
	 */
    public void setMeishuguanTypes(Integer meishuguanTypes) {
        this.meishuguanTypes = meishuguanTypes;
    }
    /**
	 * 获取：门票数量
	 */
    public Integer getMeishuguanKucunNumber() {
        return meishuguanKucunNumber;
    }


    /**
	 * 设置：门票数量
	 */
    public void setMeishuguanKucunNumber(Integer meishuguanKucunNumber) {
        this.meishuguanKucunNumber = meishuguanKucunNumber;
    }
    /**
	 * 获取：门票价格
	 */
    public Double getMeishuguanNewMoney() {
        return meishuguanNewMoney;
    }


    /**
	 * 设置：门票价格
	 */
    public void setMeishuguanNewMoney(Double meishuguanNewMoney) {
        this.meishuguanNewMoney = meishuguanNewMoney;
    }
    /**
	 * 获取：美术馆热度
	 */
    public Integer getMeishuguanClicknum() {
        return meishuguanClicknum;
    }


    /**
	 * 设置：美术馆热度
	 */
    public void setMeishuguanClicknum(Integer meishuguanClicknum) {
        this.meishuguanClicknum = meishuguanClicknum;
    }
    /**
	 * 获取：结束时间
	 */
    public Date getJieshuTime() {
        return jieshuTime;
    }


    /**
	 * 设置：结束时间
	 */
    public void setJieshuTime(Date jieshuTime) {
        this.jieshuTime = jieshuTime;
    }
    /**
	 * 获取：美术馆介绍
	 */
    public String getMeishuguanContent() {
        return meishuguanContent;
    }


    /**
	 * 设置：美术馆介绍
	 */
    public void setMeishuguanContent(String meishuguanContent) {
        this.meishuguanContent = meishuguanContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getMeishuguanDelete() {
        return meishuguanDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setMeishuguanDelete(Integer meishuguanDelete) {
        this.meishuguanDelete = meishuguanDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow homeMain
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow homeMain
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
