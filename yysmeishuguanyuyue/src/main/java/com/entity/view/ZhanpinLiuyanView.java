package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhanpinLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 展品留言
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhanpin_liuyan")
public class ZhanpinLiuyanView extends ZhanpinLiuyanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

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
	//级联表 展品信息
		/**
		* 展品名称
		*/

		@ColumnInfo(comment="展品名称",type="varchar(200)")
		private String zhanpinName;
		/**
		* 展品封面
		*/

		@ColumnInfo(comment="展品封面",type="varchar(200)")
		private String zhanpinPhoto;
		/**
		* 视频
		*/

		@ColumnInfo(comment="视频",type="varchar(200)")
		private String zhanpinVideo;
		/**
		* 展品类型
		*/
		@ColumnInfo(comment="展品类型",type="int(11)")
		private Integer zhanpinTypes;
			/**
			* 展品类型的值
			*/
			@ColumnInfo(comment="展品类型的字典表值",type="varchar(200)")
			private String zhanpinValue;
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
		* 展品热度
		*/

		@ColumnInfo(comment="展品热度",type="int(11)")
		private Integer meishuguanClicknum;
		/**
		* 展品详情
		*/

		@ColumnInfo(comment="展品详情",type="longtext")
		private String zhanpinContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer zhanpinDelete;



	public ZhanpinLiuyanView() {

	}

	public ZhanpinLiuyanView(ZhanpinLiuyanEntity zhanpinLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, zhanpinLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	//级联表的get和set 展品信息

		/**
		* 获取： 展品名称
		*/
		public String getZhanpinName() {
			return zhanpinName;
		}
		/**
		* 设置： 展品名称
		*/
		public void setZhanpinName(String zhanpinName) {
			this.zhanpinName = zhanpinName;
		}

		/**
		* 获取： 展品封面
		*/
		public String getZhanpinPhoto() {
			return zhanpinPhoto;
		}
		/**
		* 设置： 展品封面
		*/
		public void setZhanpinPhoto(String zhanpinPhoto) {
			this.zhanpinPhoto = zhanpinPhoto;
		}

		/**
		* 获取： 视频
		*/
		public String getZhanpinVideo() {
			return zhanpinVideo;
		}
		/**
		* 设置： 视频
		*/
		public void setZhanpinVideo(String zhanpinVideo) {
			this.zhanpinVideo = zhanpinVideo;
		}
		/**
		* 获取： 展品类型
		*/
		public Integer getZhanpinTypes() {
			return zhanpinTypes;
		}
		/**
		* 设置： 展品类型
		*/
		public void setZhanpinTypes(Integer zhanpinTypes) {
			this.zhanpinTypes = zhanpinTypes;
		}


			/**
			* 获取： 展品类型的值
			*/
			public String getZhanpinValue() {
				return zhanpinValue;
			}
			/**
			* 设置： 展品类型的值
			*/
			public void setZhanpinValue(String zhanpinValue) {
				this.zhanpinValue = zhanpinValue;
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
		* 获取： 展品热度
		*/
		public Integer getMeishuguanClicknum() {
			return meishuguanClicknum;
		}
		/**
		* 设置： 展品热度
		*/
		public void setMeishuguanClicknum(Integer meishuguanClicknum) {
			this.meishuguanClicknum = meishuguanClicknum;
		}

		/**
		* 获取： 展品详情
		*/
		public String getZhanpinContent() {
			return zhanpinContent;
		}
		/**
		* 设置： 展品详情
		*/
		public void setZhanpinContent(String zhanpinContent) {
			this.zhanpinContent = zhanpinContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZhanpinDelete() {
			return zhanpinDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZhanpinDelete(Integer zhanpinDelete) {
			this.zhanpinDelete = zhanpinDelete;
		}


	@Override
	public String toString() {
		return "ZhanpinLiuyanView{" +
			", zhanpinName=" + zhanpinName +
			", zhanpinPhoto=" + zhanpinPhoto +
			", zhanpinVideo=" + zhanpinVideo +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", meishuguanClicknum=" + meishuguanClicknum +
			", zhanpinContent=" + zhanpinContent +
			", zhanpinDelete=" + zhanpinDelete +
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
