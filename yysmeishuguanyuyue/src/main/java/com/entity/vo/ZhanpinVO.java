package com.entity.vo;

import com.entity.ZhanpinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 展品信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhanpin")
public class ZhanpinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 展品名称
     */

    @TableField(value = "zhanpin_name")
    private String zhanpinName;


    /**
     * 展品封面
     */

    @TableField(value = "zhanpin_photo")
    private String zhanpinPhoto;


    /**
     * 视频
     */

    @TableField(value = "zhanpin_video")
    private String zhanpinVideo;


    /**
     * 展品类型
     */

    @TableField(value = "zhanpin_types")
    private Integer zhanpinTypes;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 展品热度
     */

    @TableField(value = "meishuguan_clicknum")
    private Integer meishuguanClicknum;


    /**
     * 展品详情
     */

    @TableField(value = "zhanpin_content")
    private String zhanpinContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "zhanpin_delete")
    private Integer zhanpinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：展品名称
	 */
    public String getZhanpinName() {
        return zhanpinName;
    }


    /**
	 * 获取：展品名称
	 */

    public void setZhanpinName(String zhanpinName) {
        this.zhanpinName = zhanpinName;
    }
    /**
	 * 设置：展品封面
	 */
    public String getZhanpinPhoto() {
        return zhanpinPhoto;
    }


    /**
	 * 获取：展品封面
	 */

    public void setZhanpinPhoto(String zhanpinPhoto) {
        this.zhanpinPhoto = zhanpinPhoto;
    }
    /**
	 * 设置：视频
	 */
    public String getZhanpinVideo() {
        return zhanpinVideo;
    }


    /**
	 * 获取：视频
	 */

    public void setZhanpinVideo(String zhanpinVideo) {
        this.zhanpinVideo = zhanpinVideo;
    }
    /**
	 * 设置：展品类型
	 */
    public Integer getZhanpinTypes() {
        return zhanpinTypes;
    }


    /**
	 * 获取：展品类型
	 */

    public void setZhanpinTypes(Integer zhanpinTypes) {
        this.zhanpinTypes = zhanpinTypes;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：展品热度
	 */
    public Integer getMeishuguanClicknum() {
        return meishuguanClicknum;
    }


    /**
	 * 获取：展品热度
	 */

    public void setMeishuguanClicknum(Integer meishuguanClicknum) {
        this.meishuguanClicknum = meishuguanClicknum;
    }
    /**
	 * 设置：展品详情
	 */
    public String getZhanpinContent() {
        return zhanpinContent;
    }


    /**
	 * 获取：展品详情
	 */

    public void setZhanpinContent(String zhanpinContent) {
        this.zhanpinContent = zhanpinContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZhanpinDelete() {
        return zhanpinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZhanpinDelete(Integer zhanpinDelete) {
        this.zhanpinDelete = zhanpinDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
