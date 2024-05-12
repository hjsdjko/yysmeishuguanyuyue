
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
 * 展品信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhanpin")
public class ZhanpinController {
    private static final Logger logger = LoggerFactory.getLogger(ZhanpinController.class);

    private static final String TABLE_NAME = "zhanpin";

    @Autowired
    private ZhanpinService zhanpinService;


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
    private MeishuguanCollectionService meishuguanCollectionService;//美术馆收藏
    @Autowired
    private MeishuguanLiuyanService meishuguanLiuyanService;//美术馆留言
    @Autowired
    private MeishuguanOrderService meishuguanOrderService;//参观预约
    @Autowired
    private NewsService newsService;//公告通知
    @Autowired
    private YonghuService yonghuService;//用户
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
        params.put("zhanpinDeleteStart",1);params.put("zhanpinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zhanpinService.queryPage(params);

        //字典表数据转换
        List<ZhanpinView> list =(List<ZhanpinView>)page.getList();
        for(ZhanpinView c:list){
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
        ZhanpinEntity zhanpin = zhanpinService.selectById(id);
        if(zhanpin !=null){
            //entity转view
            ZhanpinView view = new ZhanpinView();
            BeanUtils.copyProperties( zhanpin , view );//把实体数据重构到view中
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
    public R save(@RequestBody ZhanpinEntity zhanpin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhanpin:{}",this.getClass().getName(),zhanpin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZhanpinEntity> queryWrapper = new EntityWrapper<ZhanpinEntity>()
            .eq("zhanpin_name", zhanpin.getZhanpinName())
            .eq("zhanpin_video", zhanpin.getZhanpinVideo())
            .eq("zhanpin_types", zhanpin.getZhanpinTypes())
            .eq("zan_number", zhanpin.getZanNumber())
            .eq("cai_number", zhanpin.getCaiNumber())
            .eq("zhanpin_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanpinEntity zhanpinEntity = zhanpinService.selectOne(queryWrapper);
        if(zhanpinEntity==null){
            zhanpin.setZanNumber(1);
            zhanpin.setCaiNumber(1);
            zhanpin.setZhanpinDelete(1);
            zhanpin.setInsertTime(new Date());
            zhanpin.setCreateTime(new Date());
            zhanpinService.insert(zhanpin);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",zhanpin.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhanpinEntity zhanpin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhanpin:{}",this.getClass().getName(),zhanpin.toString());
        ZhanpinEntity oldZhanpinEntity = zhanpinService.selectById(zhanpin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(zhanpin.getZhanpinPhoto()) || "null".equals(zhanpin.getZhanpinPhoto())){
                zhanpin.setZhanpinPhoto(null);
        }
        if("".equals(zhanpin.getZhanpinVideo()) || "null".equals(zhanpin.getZhanpinVideo())){
                zhanpin.setZhanpinVideo(null);
        }
        if("".equals(zhanpin.getZhanpinContent()) || "null".equals(zhanpin.getZhanpinContent())){
                zhanpin.setZhanpinContent(null);
        }

            zhanpinService.updateById(zhanpin);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(zhanpin, oldZhanpinEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZhanpinEntity> oldZhanpinList =zhanpinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZhanpinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhanpinEntity zhanpinEntity = new ZhanpinEntity();
            zhanpinEntity.setId(id);
            zhanpinEntity.setZhanpinDelete(2);
            list.add(zhanpinEntity);
        }
        if(list != null && list.size() >0){
            zhanpinService.updateBatchById(list);
        }

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldZhanpinList.toString());
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
            List<ZhanpinEntity> zhanpinList = new ArrayList<>();//上传的东西
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
                            ZhanpinEntity zhanpinEntity = new ZhanpinEntity();
//                            zhanpinEntity.setZhanpinName(data.get(0));                    //展品名称 要改的
//                            zhanpinEntity.setZhanpinPhoto("");//详情和图片
//                            zhanpinEntity.setZhanpinVideo(data.get(0));                    //视频 要改的
//                            zhanpinEntity.setZhanpinTypes(Integer.valueOf(data.get(0)));   //展品类型 要改的
//                            zhanpinEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            zhanpinEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            zhanpinEntity.setMeishuguanClicknum(Integer.valueOf(data.get(0)));   //展品热度 要改的
//                            zhanpinEntity.setZhanpinContent("");//详情和图片
//                            zhanpinEntity.setZhanpinDelete(1);//逻辑删除字段
//                            zhanpinEntity.setInsertTime(date);//时间
//                            zhanpinEntity.setCreateTime(date);//时间
                            zhanpinList.add(zhanpinEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        zhanpinService.insertBatch(zhanpinList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",zhanpinList.toString());
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZhanpinView> returnZhanpinViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("zhanpinYesnoTypes",2);
        PageUtils pageUtils = zhanpinCollectionService.queryPage(params1);
        List<ZhanpinCollectionView> collectionViewsList =(List<ZhanpinCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZhanpinCollectionView collectionView:collectionViewsList){
            Integer zhanpinTypes = collectionView.getZhanpinTypes();
            if(typeMap.containsKey(zhanpinTypes)){
                typeMap.put(zhanpinTypes,typeMap.get(zhanpinTypes)+1);
            }else{
                typeMap.put(zhanpinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("zhanpinTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("zhanpinYesnoTypes",2);
            PageUtils pageUtils1 = zhanpinService.queryPage(params2);
            List<ZhanpinView> zhanpinViewList =(List<ZhanpinView>)pageUtils1.getList();
            returnZhanpinViewList.addAll(zhanpinViewList);
            if(returnZhanpinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("zhanpinYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = zhanpinService.queryPage(params);
        if(returnZhanpinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZhanpinViewList.size();//要添加的数量
            List<ZhanpinView> zhanpinViewList =(List<ZhanpinView>)page.getList();
            for(ZhanpinView zhanpinView:zhanpinViewList){
                Boolean addFlag = true;
                for(ZhanpinView returnZhanpinView:returnZhanpinViewList){
                    if(returnZhanpinView.getId().intValue() ==zhanpinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZhanpinViewList.add(zhanpinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZhanpinViewList = returnZhanpinViewList.subList(0, limit);
        }

        for(ZhanpinView c:returnZhanpinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZhanpinViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zhanpinService.queryPage(params);

        //字典表数据转换
        List<ZhanpinView> list =(List<ZhanpinView>)page.getList();
        for(ZhanpinView c:list)
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
        ZhanpinEntity zhanpin = zhanpinService.selectById(id);
            if(zhanpin !=null){


                //entity转view
                ZhanpinView view = new ZhanpinView();
                BeanUtils.copyProperties( zhanpin , view );//把实体数据重构到view中

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
    public R add(@RequestBody ZhanpinEntity zhanpin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhanpin:{}",this.getClass().getName(),zhanpin.toString());
        Wrapper<ZhanpinEntity> queryWrapper = new EntityWrapper<ZhanpinEntity>()
            .eq("zhanpin_name", zhanpin.getZhanpinName())
            .eq("zhanpin_video", zhanpin.getZhanpinVideo())
            .eq("zhanpin_types", zhanpin.getZhanpinTypes())
            .eq("zan_number", zhanpin.getZanNumber())
            .eq("cai_number", zhanpin.getCaiNumber())
            .eq("meishuguan_clicknum", zhanpin.getMeishuguanClicknum())
            .eq("zhanpin_delete", zhanpin.getZhanpinDelete())
//            .notIn("zhanpin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanpinEntity zhanpinEntity = zhanpinService.selectOne(queryWrapper);
        if(zhanpinEntity==null){
                zhanpin.setZanNumber(1);
                zhanpin.setCaiNumber(1);
            zhanpin.setZhanpinDelete(1);
            zhanpin.setInsertTime(new Date());
            zhanpin.setCreateTime(new Date());
        zhanpinService.insert(zhanpin);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",zhanpin.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

