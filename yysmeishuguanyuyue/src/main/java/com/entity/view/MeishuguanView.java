package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.MeishuguanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 美术馆
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("meishuguan")
public class MeishuguanView extends MeishuguanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 美术馆类型的值
	*/
	@ColumnInfo(comment="美术馆类型的字典表值",type="varchar(200)")
	private String meishuguanValue;




	public MeishuguanView() {

	}

	public MeishuguanView(MeishuguanEntity meishuguanEntity) {
		try {
			BeanUtils.copyProperties(this, meishuguanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "MeishuguanView{" +
			", meishuguanValue=" + meishuguanValue +
			"} " + super.toString();
	}
}
