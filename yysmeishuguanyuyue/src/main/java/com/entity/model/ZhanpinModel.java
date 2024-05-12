package com.entity.model;

import com.entity.ZhanpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 展品信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhanpinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 展品名称
     */
    private String zhanpinName;


    /**
     * 展品封面
     */
    private String zhanpinPhoto;


    /**
     * 视频
     */
    private String zhanpinVideo;


    /**
     * 展品类型
     */
    private Integer zhanpinTypes;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 展品热度
     */
    private Integer meishuguanClicknum;


    /**
     * 展品详情
     */
    private String zhanpinContent;


    /**
     * 逻辑删除
     */
    private Integer zhanpinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
