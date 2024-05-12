package com.dao;

import com.entity.MeishuguanLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MeishuguanLiuyanView;

/**
 * 美术馆留言 Dao 接口
 *
 * @author 
 */
public interface MeishuguanLiuyanDao extends BaseMapper<MeishuguanLiuyanEntity> {

   List<MeishuguanLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
