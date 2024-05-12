package com.entity.vo;

import com.entity.MeishuguanOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 参观预约
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("meishuguan_order")
public class MeishuguanOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "meishuguan_order_uuid_number")
    private String meishuguanOrderUuidNumber;


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
     * 预约人数
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "meishuguan_order_true_price")
    private Double meishuguanOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "meishuguan_order_types")
    private Integer meishuguanOrderTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：订单编号
	 */
    public String getMeishuguanOrderUuidNumber() {
        return meishuguanOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setMeishuguanOrderUuidNumber(String meishuguanOrderUuidNumber) {
        this.meishuguanOrderUuidNumber = meishuguanOrderUuidNumber;
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
	 * 设置：预约人数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：预约人数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getMeishuguanOrderTruePrice() {
        return meishuguanOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setMeishuguanOrderTruePrice(Double meishuguanOrderTruePrice) {
        this.meishuguanOrderTruePrice = meishuguanOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getMeishuguanOrderTypes() {
        return meishuguanOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setMeishuguanOrderTypes(Integer meishuguanOrderTypes) {
        this.meishuguanOrderTypes = meishuguanOrderTypes;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
