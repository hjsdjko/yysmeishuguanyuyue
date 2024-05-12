package com.entity.vo;

import com.entity.MeishuguanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 美术馆
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("meishuguan")
public class MeishuguanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 美术馆编号
     */

    @TableField(value = "meishuguan_uuid_number")
    private String meishuguanUuidNumber;


    /**
     * 美术馆名称
     */

    @TableField(value = "meishuguan_name")
    private String meishuguanName;


    /**
     * 美术馆照片
     */

    @TableField(value = "meishuguan_photo")
    private String meishuguanPhoto;


    /**
     * 美术馆地点
     */

    @TableField(value = "meishuguan_address")
    private String meishuguanAddress;


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
     * 美术馆类型
     */

    @TableField(value = "meishuguan_types")
    private Integer meishuguanTypes;


    /**
     * 门票数量
     */

    @TableField(value = "meishuguan_kucun_number")
    private Integer meishuguanKucunNumber;


    /**
     * 门票价格
     */

    @TableField(value = "meishuguan_new_money")
    private Double meishuguanNewMoney;


    /**
     * 美术馆热度
     */

    @TableField(value = "meishuguan_clicknum")
    private Integer meishuguanClicknum;


    /**
     * 结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jieshu_time")
    private Date jieshuTime;


    /**
     * 美术馆介绍
     */

    @TableField(value = "meishuguan_content")
    private String meishuguanContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "meishuguan_delete")
    private Integer meishuguanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow homeMain
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
	 * 设置：美术馆编号
	 */
    public String getMeishuguanUuidNumber() {
        return meishuguanUuidNumber;
    }


    /**
	 * 获取：美术馆编号
	 */

    public void setMeishuguanUuidNumber(String meishuguanUuidNumber) {
        this.meishuguanUuidNumber = meishuguanUuidNumber;
    }
    /**
	 * 设置：美术馆名称
	 */
    public String getMeishuguanName() {
        return meishuguanName;
    }


    /**
	 * 获取：美术馆名称
	 */

    public void setMeishuguanName(String meishuguanName) {
        this.meishuguanName = meishuguanName;
    }
    /**
	 * 设置：美术馆照片
	 */
    public String getMeishuguanPhoto() {
        return meishuguanPhoto;
    }


    /**
	 * 获取：美术馆照片
	 */

    public void setMeishuguanPhoto(String meishuguanPhoto) {
        this.meishuguanPhoto = meishuguanPhoto;
    }
    /**
	 * 设置：美术馆地点
	 */
    public String getMeishuguanAddress() {
        return meishuguanAddress;
    }


    /**
	 * 获取：美术馆地点
	 */

    public void setMeishuguanAddress(String meishuguanAddress) {
        this.meishuguanAddress = meishuguanAddress;
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
	 * 设置：美术馆类型
	 */
    public Integer getMeishuguanTypes() {
        return meishuguanTypes;
    }


    /**
	 * 获取：美术馆类型
	 */

    public void setMeishuguanTypes(Integer meishuguanTypes) {
        this.meishuguanTypes = meishuguanTypes;
    }
    /**
	 * 设置：门票数量
	 */
    public Integer getMeishuguanKucunNumber() {
        return meishuguanKucunNumber;
    }


    /**
	 * 获取：门票数量
	 */

    public void setMeishuguanKucunNumber(Integer meishuguanKucunNumber) {
        this.meishuguanKucunNumber = meishuguanKucunNumber;
    }
    /**
	 * 设置：门票价格
	 */
    public Double getMeishuguanNewMoney() {
        return meishuguanNewMoney;
    }


    /**
	 * 获取：门票价格
	 */

    public void setMeishuguanNewMoney(Double meishuguanNewMoney) {
        this.meishuguanNewMoney = meishuguanNewMoney;
    }
    /**
	 * 设置：美术馆热度
	 */
    public Integer getMeishuguanClicknum() {
        return meishuguanClicknum;
    }


    /**
	 * 获取：美术馆热度
	 */

    public void setMeishuguanClicknum(Integer meishuguanClicknum) {
        this.meishuguanClicknum = meishuguanClicknum;
    }
    /**
	 * 设置：结束时间
	 */
    public Date getJieshuTime() {
        return jieshuTime;
    }


    /**
	 * 获取：结束时间
	 */

    public void setJieshuTime(Date jieshuTime) {
        this.jieshuTime = jieshuTime;
    }
    /**
	 * 设置：美术馆介绍
	 */
    public String getMeishuguanContent() {
        return meishuguanContent;
    }


    /**
	 * 获取：美术馆介绍
	 */

    public void setMeishuguanContent(String meishuguanContent) {
        this.meishuguanContent = meishuguanContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getMeishuguanDelete() {
        return meishuguanDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setMeishuguanDelete(Integer meishuguanDelete) {
        this.meishuguanDelete = meishuguanDelete;
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
	 * 设置：创建时间  show1 show2 photoShow homeMain
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow homeMain
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
