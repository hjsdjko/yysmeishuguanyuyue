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
 * 参观预约
 *
 * @author 
 * @email
 */
@TableName("meishuguan_order")
public class MeishuguanOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public MeishuguanOrderEntity() {

	}

	public MeishuguanOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "meishuguan_order_uuid_number")

    private String meishuguanOrderUuidNumber;


    /**
     * 美术馆
     */
    @ColumnInfo(comment="美术馆",type="int(11)")
    @TableField(value = "meishuguan_id")

    private Integer meishuguanId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约人数
     */
    @ColumnInfo(comment="预约人数",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "meishuguan_order_true_price")

    private Double meishuguanOrderTruePrice;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "meishuguan_order_types")

    private Integer meishuguanOrderTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="添加时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单编号
	 */
    public String getMeishuguanOrderUuidNumber() {
        return meishuguanOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setMeishuguanOrderUuidNumber(String meishuguanOrderUuidNumber) {
        this.meishuguanOrderUuidNumber = meishuguanOrderUuidNumber;
    }
    /**
	 * 获取：美术馆
	 */
    public Integer getMeishuguanId() {
        return meishuguanId;
    }
    /**
	 * 设置：美术馆
	 */

    public void setMeishuguanId(Integer meishuguanId) {
        this.meishuguanId = meishuguanId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预约人数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 设置：预约人数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getMeishuguanOrderTruePrice() {
        return meishuguanOrderTruePrice;
    }
    /**
	 * 设置：实付价格
	 */

    public void setMeishuguanOrderTruePrice(Double meishuguanOrderTruePrice) {
        this.meishuguanOrderTruePrice = meishuguanOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getMeishuguanOrderTypes() {
        return meishuguanOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setMeishuguanOrderTypes(Integer meishuguanOrderTypes) {
        this.meishuguanOrderTypes = meishuguanOrderTypes;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MeishuguanOrder{" +
            ", id=" + id +
            ", meishuguanOrderUuidNumber=" + meishuguanOrderUuidNumber +
            ", meishuguanId=" + meishuguanId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", meishuguanOrderTruePrice=" + meishuguanOrderTruePrice +
            ", meishuguanOrderTypes=" + meishuguanOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
