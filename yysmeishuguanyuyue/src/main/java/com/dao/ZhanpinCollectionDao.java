package com.dao;

import com.entity.ZhanpinCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhanpinCollectionView;

/**
 * 展品收藏 Dao 接口
 *
 * @author 
 */
public interface ZhanpinCollectionDao extends BaseMapper<ZhanpinCollectionEntity> {

   List<ZhanpinCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
