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
 * 展品信息
 *
 * @author 
 * @email
 */
@TableName("zhanpin")
public class ZhanpinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhanpinEntity() {

	}

	public ZhanpinEntity(T t) {
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
     * 展品名称
     */
    @ColumnInfo(comment="展品名称",type="varchar(200)")
    @TableField(value = "zhanpin_name")

    private String zhanpinName;


    /**
     * 展品封面
     */
    @ColumnInfo(comment="展品封面",type="varchar(200)")
    @TableField(value = "zhanpin_photo")

    private String zhanpinPhoto;


    /**
     * 视频
     */
    @ColumnInfo(comment="视频",type="varchar(200)")
    @TableField(value = "zhanpin_video")

    private String zhanpinVideo;


    /**
     * 展品类型
     */
    @ColumnInfo(comment="展品类型",type="int(11)")
    @TableField(value = "zhanpin_types")

    private Integer zhanpinTypes;


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
     * 展品热度
     */
    @ColumnInfo(comment="展品热度",type="int(11)")
    @TableField(value = "meishuguan_clicknum")

    private Integer meishuguanClicknum;


    /**
     * 展品详情
     */
    @ColumnInfo(comment="展品详情",type="longtext")
    @TableField(value = "zhanpin_content")

    private String zhanpinContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zhanpin_delete")

    private Integer zhanpinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：展品名称
	 */
    public String getZhanpinName() {
        return zhanpinName;
    }
    /**
	 * 设置：展品名称
	 */

    public void setZhanpinName(String zhanpinName) {
        this.zhanpinName = zhanpinName;
    }
    /**
	 * 获取：展品封面
	 */
    public String getZhanpinPhoto() {
        return zhanpinPhoto;
    }
    /**
	 * 设置：展品封面
	 */

    public void setZhanpinPhoto(String zhanpinPhoto) {
        this.zhanpinPhoto = zhanpinPhoto;
    }
    /**
	 * 获取：视频
	 */
    public String getZhanpinVideo() {
        return zhanpinVideo;
    }
    /**
	 * 设置：视频
	 */

    public void setZhanpinVideo(String zhanpinVideo) {
        this.zhanpinVideo = zhanpinVideo;
    }
    /**
	 * 获取：展品类型
	 */
    public Integer getZhanpinTypes() {
        return zhanpinTypes;
    }
    /**
	 * 设置：展品类型
	 */

    public void setZhanpinTypes(Integer zhanpinTypes) {
        this.zhanpinTypes = zhanpinTypes;
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
	 * 获取：展品热度
	 */
    public Integer getMeishuguanClicknum() {
        return meishuguanClicknum;
    }
    /**
	 * 设置：展品热度
	 */

    public void setMeishuguanClicknum(Integer meishuguanClicknum) {
        this.meishuguanClicknum = meishuguanClicknum;
    }
    /**
	 * 获取：展品详情
	 */
    public String getZhanpinContent() {
        return zhanpinContent;
    }
    /**
	 * 设置：展品详情
	 */

    public void setZhanpinContent(String zhanpinContent) {
        this.zhanpinContent = zhanpinContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZhanpinDelete() {
        return zhanpinDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZhanpinDelete(Integer zhanpinDelete) {
        this.zhanpinDelete = zhanpinDelete;
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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Zhanpin{" +
            ", id=" + id +
            ", zhanpinName=" + zhanpinName +
            ", zhanpinPhoto=" + zhanpinPhoto +
            ", zhanpinVideo=" + zhanpinVideo +
            ", zhanpinTypes=" + zhanpinTypes +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", meishuguanClicknum=" + meishuguanClicknum +
            ", zhanpinContent=" + zhanpinContent +
            ", zhanpinDelete=" + zhanpinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
