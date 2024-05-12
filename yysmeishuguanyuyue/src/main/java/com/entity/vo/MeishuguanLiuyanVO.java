package com.entity.vo;

import com.entity.MeishuguanLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 美术馆留言
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("meishuguan_liuyan")
public class MeishuguanLiuyanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 美术馆
     */

    @TableField(value = "meishuguan_id")
    private Integer meishuguanId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 留言内容
     */

    @TableField(value = "meishuguan_liuyan_text")
    private String meishuguanLiuyanText;


    /**
     * 留言时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
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
	 * 设置：美术馆
	 */
    public Integer getMeishuguanId() {
        return meishuguanId;
    }


    /**
	 * 获取：美术馆
	 */

    public void setMeishuguanId(Integer meishuguanId) {
        this.meishuguanId = meishuguanId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：留言内容
	 */
    public String getMeishuguanLiuyanText() {
        return meishuguanLiuyanText;
    }


    /**
	 * 获取：留言内容
	 */

    public void setMeishuguanLiuyanText(String meishuguanLiuyanText) {
        this.meishuguanLiuyanText = meishuguanLiuyanText;
    }
    /**
	 * 设置：留言时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：留言时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
