package com.dao;

import com.entity.ZhanpinLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhanpinLiuyanView;

/**
 * 展品留言 Dao 接口
 *
 * @author 
 */
public interface ZhanpinLiuyanDao extends BaseMapper<ZhanpinLiuyanEntity> {

   List<ZhanpinLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
