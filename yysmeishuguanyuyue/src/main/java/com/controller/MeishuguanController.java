
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
 * 美术馆
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/meishuguan")
public class MeishuguanController {
    private static final Logger logger = LoggerFactory.getLogger(MeishuguanController.class);

    private static final String TABLE_NAME = "meishuguan";

    @Autowired
    private MeishuguanService meishuguanService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
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
        params.put("meishuguanDeleteStart",1);params.put("meishuguanDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = meishuguanService.queryPage(params);

        //字典表数据转换
        List<MeishuguanView> list =(List<MeishuguanView>)page.getList();
        for(MeishuguanView c:list){
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
        MeishuguanEntity meishuguan = meishuguanService.selectById(id);
        if(meishuguan !=null){
            //entity转view
            MeishuguanView view = new MeishuguanView();
            BeanUtils.copyProperties( meishuguan , view );//把实体数据重构到view中
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
    public R save(@RequestBody MeishuguanEntity meishuguan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,meishuguan:{}",this.getClass().getName(),meishuguan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<MeishuguanEntity> queryWrapper = new EntityWrapper<MeishuguanEntity>()
            .eq("meishuguan_name", meishuguan.getMeishuguanName())
            .eq("meishuguan_address", meishuguan.getMeishuguanAddress())
            .eq("zan_number", meishuguan.getZanNumber())
            .eq("cai_number", meishuguan.getCaiNumber())
            .eq("meishuguan_types", meishuguan.getMeishuguanTypes())
            .eq("meishuguan_kucun_number", meishuguan.getMeishuguanKucunNumber())
            .eq("meishuguan_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MeishuguanEntity meishuguanEntity = meishuguanService.selectOne(queryWrapper);
        if(meishuguanEntity==null){
            meishuguan.setZanNumber(1);
            meishuguan.setCaiNumber(1);
            meishuguan.setMeishuguanClicknum(1);
            meishuguan.setMeishuguanDelete(1);
            meishuguan.setInsertTime(new Date());
            meishuguan.setCreateTime(new Date());
            meishuguanService.insert(meishuguan);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",meishuguan.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody MeishuguanEntity meishuguan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,meishuguan:{}",this.getClass().getName(),meishuguan.toString());
        MeishuguanEntity oldMeishuguanEntity = meishuguanService.selectById(meishuguan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(meishuguan.getMeishuguanPhoto()) || "null".equals(meishuguan.getMeishuguanPhoto())){
                meishuguan.setMeishuguanPhoto(null);
        }
        if("".equals(meishuguan.getMeishuguanContent()) || "null".equals(meishuguan.getMeishuguanContent())){
                meishuguan.setMeishuguanContent(null);
        }

            meishuguanService.updateById(meishuguan);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(meishuguan, oldMeishuguanEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<MeishuguanEntity> oldMeishuguanList =meishuguanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<MeishuguanEntity> list = new ArrayList<>();
        for(Integer id:ids){
            MeishuguanEntity meishuguanEntity = new MeishuguanEntity();
            meishuguanEntity.setId(id);
            meishuguanEntity.setMeishuguanDelete(2);
            list.add(meishuguanEntity);
        }
        if(list != null && list.size() >0){
            meishuguanService.updateBatchById(list);
        }

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldMeishuguanList.toString());
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
            List<MeishuguanEntity> meishuguanList = new ArrayList<>();//上传的东西
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
                            MeishuguanEntity meishuguanEntity = new MeishuguanEntity();
//                            meishuguanEntity.setMeishuguanUuidNumber(data.get(0));                    //美术馆编号 要改的
//                            meishuguanEntity.setMeishuguanName(data.get(0));                    //美术馆名称 要改的
//                            meishuguanEntity.setMeishuguanPhoto("");//详情和图片
//                            meishuguanEntity.setMeishuguanAddress(data.get(0));                    //美术馆地点 要改的
//                            meishuguanEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            meishuguanEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            meishuguanEntity.setMeishuguanTypes(Integer.valueOf(data.get(0)));   //美术馆类型 要改的
//                            meishuguanEntity.setMeishuguanKucunNumber(Integer.valueOf(data.get(0)));   //门票数量 要改的
//                            meishuguanEntity.setMeishuguanNewMoney(data.get(0));                    //门票价格 要改的
//                            meishuguanEntity.setMeishuguanClicknum(Integer.valueOf(data.get(0)));   //美术馆热度 要改的
//                            meishuguanEntity.setJieshuTime(sdf.parse(data.get(0)));          //结束时间 要改的
//                            meishuguanEntity.setMeishuguanContent("");//详情和图片
//                            meishuguanEntity.setMeishuguanDelete(1);//逻辑删除字段
//                            meishuguanEntity.setInsertTime(date);//时间
//                            meishuguanEntity.setCreateTime(date);//时间
                            meishuguanList.add(meishuguanEntity);


                            //把要查询是否重复的字段放入map中
                                //美术馆编号
                                if(seachFields.containsKey("meishuguanUuidNumber")){
                                    List<String> meishuguanUuidNumber = seachFields.get("meishuguanUuidNumber");
                                    meishuguanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> meishuguanUuidNumber = new ArrayList<>();
                                    meishuguanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("meishuguanUuidNumber",meishuguanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //美术馆编号
                        List<MeishuguanEntity> meishuguanEntities_meishuguanUuidNumber = meishuguanService.selectList(new EntityWrapper<MeishuguanEntity>().in("meishuguan_uuid_number", seachFields.get("meishuguanUuidNumber")).eq("meishuguan_delete", 1));
                        if(meishuguanEntities_meishuguanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(MeishuguanEntity s:meishuguanEntities_meishuguanUuidNumber){
                                repeatFields.add(s.getMeishuguanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [美术馆编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        meishuguanService.insertBatch(meishuguanList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",meishuguanList.toString());
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
        List<MeishuguanView> returnMeishuguanViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("meishuguanYesnoTypes",2);
        PageUtils pageUtils = meishuguanOrderService.queryPage(params1);
        List<MeishuguanOrderView> orderViewsList =(List<MeishuguanOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(MeishuguanOrderView orderView:orderViewsList){
            Integer meishuguanTypes = orderView.getMeishuguanTypes();
            if(typeMap.containsKey(meishuguanTypes)){
                typeMap.put(meishuguanTypes,typeMap.get(meishuguanTypes)+1);
            }else{
                typeMap.put(meishuguanTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("meishuguanTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("meishuguanYesnoTypes",2);
            PageUtils pageUtils1 = meishuguanService.queryPage(params2);
            List<MeishuguanView> meishuguanViewList =(List<MeishuguanView>)pageUtils1.getList();
            returnMeishuguanViewList.addAll(meishuguanViewList);
            if(returnMeishuguanViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("meishuguanYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = meishuguanService.queryPage(params);
        if(returnMeishuguanViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnMeishuguanViewList.size();//要添加的数量
            List<MeishuguanView> meishuguanViewList =(List<MeishuguanView>)page.getList();
            for(MeishuguanView meishuguanView:meishuguanViewList){
                Boolean addFlag = true;
                for(MeishuguanView returnMeishuguanView:returnMeishuguanViewList){
                    if(returnMeishuguanView.getId().intValue() ==meishuguanView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnMeishuguanViewList.add(meishuguanView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnMeishuguanViewList = returnMeishuguanViewList.subList(0, limit);
        }

        for(MeishuguanView c:returnMeishuguanViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnMeishuguanViewList);
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
        PageUtils page = meishuguanService.queryPage(params);

        //字典表数据转换
        List<MeishuguanView> list =(List<MeishuguanView>)page.getList();
        for(MeishuguanView c:list)
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
        MeishuguanEntity meishuguan = meishuguanService.selectById(id);
            if(meishuguan !=null){

                //点击数量加1
                meishuguan.setMeishuguanClicknum(meishuguan.getMeishuguanClicknum()+1);
                meishuguanService.updateById(meishuguan);

                //entity转view
                MeishuguanView view = new MeishuguanView();
                BeanUtils.copyProperties( meishuguan , view );//把实体数据重构到view中

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
    public R add(@RequestBody MeishuguanEntity meishuguan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,meishuguan:{}",this.getClass().getName(),meishuguan.toString());
        Wrapper<MeishuguanEntity> queryWrapper = new EntityWrapper<MeishuguanEntity>()
            .eq("meishuguan_uuid_number", meishuguan.getMeishuguanUuidNumber())
            .eq("meishuguan_name", meishuguan.getMeishuguanName())
            .eq("meishuguan_address", meishuguan.getMeishuguanAddress())
            .eq("zan_number", meishuguan.getZanNumber())
            .eq("cai_number", meishuguan.getCaiNumber())
            .eq("meishuguan_types", meishuguan.getMeishuguanTypes())
            .eq("meishuguan_kucun_number", meishuguan.getMeishuguanKucunNumber())
            .eq("meishuguan_clicknum", meishuguan.getMeishuguanClicknum())
            .eq("meishuguan_delete", meishuguan.getMeishuguanDelete())
//            .notIn("meishuguan_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MeishuguanEntity meishuguanEntity = meishuguanService.selectOne(queryWrapper);
        if(meishuguanEntity==null){
                meishuguan.setZanNumber(1);
                meishuguan.setCaiNumber(1);
            meishuguan.setMeishuguanClicknum(1);
            meishuguan.setMeishuguanDelete(1);
            meishuguan.setInsertTime(new Date());
            meishuguan.setCreateTime(new Date());
        meishuguanService.insert(meishuguan);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",meishuguan.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

