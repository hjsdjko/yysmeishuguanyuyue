
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 美术馆收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/meishuguanCollection")
public class MeishuguanCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(MeishuguanCollectionController.class);

    private static final String TABLE_NAME = "meishuguanCollection";

    @Autowired
    private MeishuguanCollectionService meishuguanCollectionService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private MeishuguanService meishuguanService;//美术馆
    @Autowired
    private MeishuguanLiuyanService meishuguanLiuyanService;//美术馆留言
    @Autowired
    private MeishuguanOrderService meishuguanOrderService;//参观预约
    @Autowired
    private NewsService newsService;//公告通知
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhanpinService zhanpinService;//展品信息
    @Autowired
    private ZhanpinCollectionService zhanpinCollectionService;//展品收藏
    @Autowired
    private ZhanpinLiuyanService zhanpinLiuyanService;//展品留言
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = meishuguanCollectionService.queryPage(params);

        //字典表数据转换
        List<MeishuguanCollectionView> list =(List<MeishuguanCollectionView>)page.getList();
        for(MeishuguanCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MeishuguanCollectionEntity meishuguanCollection = meishuguanCollectionService.selectById(id);
        if(meishuguanCollection !=null){
            //entity转view
            MeishuguanCollectionView view = new MeishuguanCollectionView();
            BeanUtils.copyProperties( meishuguanCollection , view );//把实体数据重构到view中
            //级联表 美术馆
            //级联表
            MeishuguanEntity meishuguan = meishuguanService.selectById(meishuguanCollection.getMeishuguanId());
            if(meishuguan != null){
            BeanUtils.copyProperties( meishuguan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setMeishuguanId(meishuguan.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(meishuguanCollection.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody MeishuguanCollectionEntity meishuguanCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,meishuguanCollection:{}",this.getClass().getName(),meishuguanCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            meishuguanCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<MeishuguanCollectionEntity> queryWrapper = new EntityWrapper<MeishuguanCollectionEntity>()
            .eq("meishuguan_id", meishuguanCollection.getMeishuguanId())
            .eq("yonghu_id", meishuguanCollection.getYonghuId())
            .eq("meishuguan_collection_types", meishuguanCollection.getMeishuguanCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MeishuguanCollectionEntity meishuguanCollectionEntity = meishuguanCollectionService.selectOne(queryWrapper);
        if(meishuguanCollectionEntity==null){
            meishuguanCollection.setInsertTime(new Date());
            meishuguanCollection.setCreateTime(new Date());
            meishuguanCollectionService.insert(meishuguanCollection);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",meishuguanCollection.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody MeishuguanCollectionEntity meishuguanCollection, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,meishuguanCollection:{}",this.getClass().getName(),meishuguanCollection.toString());
        MeishuguanCollectionEntity oldMeishuguanCollectionEntity = meishuguanCollectionService.selectById(meishuguanCollection.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            meishuguanCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            meishuguanCollectionService.updateById(meishuguanCollection);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(meishuguanCollection, oldMeishuguanCollectionEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<MeishuguanCollectionEntity> oldMeishuguanCollectionList =meishuguanCollectionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        meishuguanCollectionService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldMeishuguanCollectionList.toString());
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<MeishuguanCollectionEntity> meishuguanCollectionList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            MeishuguanCollectionEntity meishuguanCollectionEntity = new MeishuguanCollectionEntity();
//                            meishuguanCollectionEntity.setMeishuguanId(Integer.valueOf(data.get(0)));   //美术馆 要改的
//                            meishuguanCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            meishuguanCollectionEntity.setMeishuguanCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            meishuguanCollectionEntity.setInsertTime(date);//时间
//                            meishuguanCollectionEntity.setCreateTime(date);//时间
                            meishuguanCollectionList.add(meishuguanCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        meishuguanCollectionService.insertBatch(meishuguanCollectionList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",meishuguanCollectionList.toString());
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = meishuguanCollectionService.queryPage(params);

        //字典表数据转换
        List<MeishuguanCollectionView> list =(List<MeishuguanCollectionView>)page.getList();
        for(MeishuguanCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MeishuguanCollectionEntity meishuguanCollection = meishuguanCollectionService.selectById(id);
            if(meishuguanCollection !=null){


                //entity转view
                MeishuguanCollectionView view = new MeishuguanCollectionView();
                BeanUtils.copyProperties( meishuguanCollection , view );//把实体数据重构到view中

                //级联表
                    MeishuguanEntity meishuguan = meishuguanService.selectById(meishuguanCollection.getMeishuguanId());
                if(meishuguan != null){
                    BeanUtils.copyProperties( meishuguan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setMeishuguanId(meishuguan.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(meishuguanCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody MeishuguanCollectionEntity meishuguanCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,meishuguanCollection:{}",this.getClass().getName(),meishuguanCollection.toString());
        Wrapper<MeishuguanCollectionEntity> queryWrapper = new EntityWrapper<MeishuguanCollectionEntity>()
            .eq("meishuguan_id", meishuguanCollection.getMeishuguanId())
            .eq("yonghu_id", meishuguanCollection.getYonghuId())
            .eq("meishuguan_collection_types", meishuguanCollection.getMeishuguanCollectionTypes())
//            .notIn("meishuguan_collection_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MeishuguanCollectionEntity meishuguanCollectionEntity = meishuguanCollectionService.selectOne(queryWrapper);
        if(meishuguanCollectionEntity==null){
            meishuguanCollection.setInsertTime(new Date());
            meishuguanCollection.setCreateTime(new Date());
        meishuguanCollectionService.insert(meishuguanCollection);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",meishuguanCollection.toString());
            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }

}

