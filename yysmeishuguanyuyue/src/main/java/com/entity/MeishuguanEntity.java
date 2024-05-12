package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 美术馆
 *
 * @author 
 * @email
 */
@TableName("meishuguan")
public class MeishuguanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public MeishuguanEntity() {

	}

	public MeishuguanEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 美术馆编号
     */
    @ColumnInfo(comment="美术馆编号",type="varchar(200)")
    @TableField(value = "meishuguan_uuid_number")

    private String meishuguanUuidNumber;


    /**
     * 美术馆名称
     */
    @ColumnInfo(comment="美术馆名称",type="varchar(200)")
    @TableField(value = "meishuguan_name")

    private String meishuguanName;


    /**
     * 美术馆照片
     */
    @ColumnInfo(comment="美术馆照片",type="varchar(200)")
    @TableField(value = "meishuguan_photo")

    private String meishuguanPhoto;


    /**
     * 美术馆地点
     */
    @ColumnInfo(comment="美术馆地点",type="varchar(200)")
    @TableField(value = "meishuguan_address")

    private String meishuguanAddress;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 美术馆类型
     */
    @ColumnInfo(comment="美术馆类型",type="int(11)")
    @TableField(value = "meishuguan_types")

    private Integer meishuguanTypes;


    /**
     * 门票数量
     */
    @ColumnInfo(comment="门票数量",type="int(11)")
    @TableField(value = "meishuguan_kucun_number")

    private Integer meishuguanKucunNumber;


    /**
     * 门票价格
     */
    @ColumnInfo(comment="门票价格",type="decimal(10,2)")
    @TableField(value = "meishuguan_new_money")

    private Double meishuguanNewMoney;


    /**
     * 美术馆热度
     */
    @ColumnInfo(comment="美术馆热度",type="int(11)")
    @TableField(value = "meishuguan_clicknum")

    private Integer meishuguanClicknum;


    /**
     * 结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="结束时间",type="timestamp")
    @TableField(value = "jieshu_time")

    private Date jieshuTime;


    /**
     * 美术馆介绍
     */
    @ColumnInfo(comment="美术馆介绍",type="longtext")
    @TableField(value = "meishuguan_content")

    private String meishuguanContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "meishuguan_delete")

    private Integer meishuguanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间     homeMain
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间     homeMain
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间     homeMain
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Meishuguan{" +
            ", id=" + id +
            ", meishuguanUuidNumber=" + meishuguanUuidNumber +
            ", meishuguanName=" + meishuguanName +
            ", meishuguanPhoto=" + meishuguanPhoto +
            ", meishuguanAddress=" + meishuguanAddress +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", meishuguanTypes=" + meishuguanTypes +
            ", meishuguanKucunNumber=" + meishuguanKucunNumber +
            ", meishuguanNewMoney=" + meishuguanNewMoney +
            ", meishuguanClicknum=" + meishuguanClicknum +
            ", jieshuTime=" + DateUtil.convertString(jieshuTime,"yyyy-MM-dd") +
            ", meishuguanContent=" + meishuguanContent +
            ", meishuguanDelete=" + meishuguanDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
