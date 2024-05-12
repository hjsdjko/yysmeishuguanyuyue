package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.MeishuguanLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 美术馆留言
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("meishuguan_liuyan")
public class MeishuguanLiuyanView extends MeishuguanLiuyanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 美术馆
		/**
		* 美术馆编号
		*/

		@ColumnInfo(comment="美术馆编号",type="varchar(200)")
		private String meishuguanUuidNumber;
		/**
		* 美术馆名称
		*/

		@ColumnInfo(comment="美术馆名称",type="varchar(200)")
		private String meishuguanName;
		/**
		* 美术馆照片
		*/

		@ColumnInfo(comment="美术馆照片",type="varchar(200)")
		private String meishuguanPhoto;
		/**
		* 美术馆地点
		*/

		@ColumnInfo(comment="美术馆地点",type="varchar(200)")
		private String meishuguanAddress;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 美术馆类型
		*/
		@ColumnInfo(comment="美术馆类型",type="int(11)")
		private Integer meishuguanTypes;
			/**
			* 美术馆类型的值
			*/
			@ColumnInfo(comment="美术馆类型的字典表值",type="varchar(200)")
			private String meishuguanValue;
		/**
		* 门票数量
		*/

		@ColumnInfo(comment="门票数量",type="int(11)")
		private Integer meishuguanKucunNumber;
		/**
		* 门票价格
		*/
		@ColumnInfo(comment="门票价格",type="decimal(10,2)")
		private Double meishuguanNewMoney;
		/**
		* 美术馆热度
		*/

		@ColumnInfo(comment="美术馆热度",type="int(11)")
		private Integer meishuguanClicknum;
		/**
		* 结束时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="结束时间",type="timestamp")
		private Date jieshuTime;
		/**
		* 美术馆介绍
		*/

		@ColumnInfo(comment="美术馆介绍",type="longtext")
		private String meishuguanContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer meishuguanDelete;
	//级联表 用户
		/**
		* 用户名称
		*/

		@ColumnInfo(comment="用户名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yonghuDelete;



	public MeishuguanLiuyanView() {

	}

	public MeishuguanLiuyanView(MeishuguanLiuyanEntity meishuguanLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, meishuguanLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 美术馆

		/**
		* 获取： 美术馆编号
		*/
		public String getMeishuguanUuidNumber() {
			return meishuguanUuidNumber;
		}
		/**
		* 设置： 美术馆编号
		*/
		public void setMeishuguanUuidNumber(String meishuguanUuidNumber) {
			this.meishuguanUuidNumber = meishuguanUuidNumber;
		}

		/**
		* 获取： 美术馆名称
		*/
		public String getMeishuguanName() {
			return meishuguanName;
		}
		/**
		* 设置： 美术馆名称
		*/
		public void setMeishuguanName(String meishuguanName) {
			this.meishuguanName = meishuguanName;
		}

		/**
		* 获取： 美术馆照片
		*/
		public String getMeishuguanPhoto() {
			return meishuguanPhoto;
		}
		/**
		* 设置： 美术馆照片
		*/
		public void setMeishuguanPhoto(String meishuguanPhoto) {
			this.meishuguanPhoto = meishuguanPhoto;
		}

		/**
		* 获取： 美术馆地点
		*/
		public String getMeishuguanAddress() {
			return meishuguanAddress;
		}
		/**
		* 设置： 美术馆地点
		*/
		public void setMeishuguanAddress(String meishuguanAddress) {
			this.meishuguanAddress = meishuguanAddress;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 美术馆类型
		*/
		public Integer getMeishuguanTypes() {
			return meishuguanTypes;
		}
		/**
		* 设置： 美术馆类型
		*/
		public void setMeishuguanTypes(Integer meishuguanTypes) {
			this.meishuguanTypes = meishuguanTypes;
		}


			/**
			* 获取： 美术馆类型的值
			*/
			public String getMeishuguanValue() {
				return meishuguanValue;
			}
			/**
			* 设置： 美术馆类型的值
			*/
			public void setMeishuguanValue(String meishuguanValue) {
				this.meishuguanValue = meishuguanValue;
			}

		/**
		* 获取： 门票数量
		*/
		public Integer getMeishuguanKucunNumber() {
			return meishuguanKucunNumber;
		}
		/**
		* 设置： 门票数量
		*/
		public void setMeishuguanKucunNumber(Integer meishuguanKucunNumber) {
			this.meishuguanKucunNumber = meishuguanKucunNumber;
		}

		/**
		* 获取： 门票价格
		*/
		public Double getMeishuguanNewMoney() {
			return meishuguanNewMoney;
		}
		/**
		* 设置： 门票价格
		*/
		public void setMeishuguanNewMoney(Double meishuguanNewMoney) {
			this.meishuguanNewMoney = meishuguanNewMoney;
		}

		/**
		* 获取： 美术馆热度
		*/
		public Integer getMeishuguanClicknum() {
			return meishuguanClicknum;
		}
		/**
		* 设置： 美术馆热度
		*/
		public void setMeishuguanClicknum(Integer meishuguanClicknum) {
			this.meishuguanClicknum = meishuguanClicknum;
		}

		/**
		* 获取： 结束时间
		*/
		public Date getJieshuTime() {
			return jieshuTime;
		}
		/**
		* 设置： 结束时间
		*/
		public void setJieshuTime(Date jieshuTime) {
			this.jieshuTime = jieshuTime;
		}

		/**
		* 获取： 美术馆介绍
		*/
		public String getMeishuguanContent() {
			return meishuguanContent;
		}
		/**
		* 设置： 美术馆介绍
		*/
		public void setMeishuguanContent(String meishuguanContent) {
			this.meishuguanContent = meishuguanContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getMeishuguanDelete() {
			return meishuguanDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setMeishuguanDelete(Integer meishuguanDelete) {
			this.meishuguanDelete = meishuguanDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}


	@Override
	public String toString() {
		return "MeishuguanLiuyanView{" +
			", meishuguanUuidNumber=" + meishuguanUuidNumber +
			", meishuguanName=" + meishuguanName +
			", meishuguanPhoto=" + meishuguanPhoto +
			", meishuguanAddress=" + meishuguanAddress +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", meishuguanKucunNumber=" + meishuguanKucunNumber +
			", meishuguanNewMoney=" + meishuguanNewMoney +
			", meishuguanClicknum=" + meishuguanClicknum +
			", jieshuTime=" + DateUtil.convertString(jieshuTime,"yyyy-MM-dd") +
			", meishuguanContent=" + meishuguanContent +
			", meishuguanDelete=" + meishuguanDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
