package com.dao;

import com.entity.MeishuguanCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MeishuguanCollectionView;

/**
 * 美术馆收藏 Dao 接口
 *
 * @author 
 */
public interface MeishuguanCollectionDao extends BaseMapper<MeishuguanCollectionEntity> {

   List<MeishuguanCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
