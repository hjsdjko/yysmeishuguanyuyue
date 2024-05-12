
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
 * 参观预约
 * 后端接口
 *
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/meishuguanOrder")
public class MeishuguanOrderController {
    private static final Logger logger = LoggerFactory.getLogger(MeishuguanOrderController.class);

    private static final String TABLE_NAME = "meishuguanOrder";

    @Autowired
    private MeishuguanOrderService meishuguanOrderService;


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
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("page方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (false)
            return R.error(511, "永不会进入");
        else if ("用户".equals(role))
            params.put("yonghuId", request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = meishuguanOrderService.queryPage(params);

        //字典表数据转换
        List<MeishuguanOrderView> list = (List<MeishuguanOrderView>) page.getList();
        for (MeishuguanOrderView c : list) {
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "列表查询", list.toString());
        return R.ok().put("data", page);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        logger.debug("info方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        MeishuguanOrderEntity meishuguanOrder = meishuguanOrderService.selectById(id);
        if (meishuguanOrder != null) {
            //entity转view
            MeishuguanOrderView view = new MeishuguanOrderView();
            BeanUtils.copyProperties(meishuguanOrder, view);//把实体数据重构到view中
            //级联表 美术馆
            //级联表
            MeishuguanEntity meishuguan = meishuguanService.selectById(meishuguanOrder.getMeishuguanId());
            if (meishuguan != null) {
                BeanUtils.copyProperties(meishuguan, view, new String[]{"id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
                view.setMeishuguanId(meishuguan.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(meishuguanOrder.getYonghuId());
            if (yonghu != null) {
                BeanUtils.copyProperties(yonghu, view, new String[]{"id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "单条数据查看", view.toString());
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }

    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MeishuguanOrderEntity meishuguanOrder, HttpServletRequest request) {
        logger.debug("save方法:,,Controller:{},,meishuguanOrder:{}", this.getClass().getName(), meishuguanOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if (false)
            return R.error(511, "永远不会进入");
        else if ("用户".equals(role))
            meishuguanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        meishuguanOrder.setCreateTime(new Date());
        meishuguanOrder.setInsertTime(new Date());
        meishuguanOrderService.insert(meishuguanOrder);

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "新增", meishuguanOrder.toString());
        return R.ok();
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MeishuguanOrderEntity meishuguanOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,meishuguanOrder:{}", this.getClass().getName(), meishuguanOrder.toString());
        MeishuguanOrderEntity oldMeishuguanOrderEntity = meishuguanOrderService.selectById(meishuguanOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            meishuguanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        meishuguanOrderService.updateById(meishuguanOrder);//根据id更新
        List<String> strings = caozuorizhiService.clazzDiff(meishuguanOrder, oldMeishuguanOrderEntity, request, new String[]{"updateTime"});
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "修改", strings.toString());
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request) {
        logger.debug("delete:,,Controller:{},,ids:{}", this.getClass().getName(), ids.toString());
        List<MeishuguanOrderEntity> oldMeishuguanOrderList = meishuguanOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        meishuguanOrderService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "删除", oldMeishuguanOrderList.toString());
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName, HttpServletRequest request) {
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}", this.getClass().getName(), fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<MeishuguanOrderEntity> meishuguanOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields = new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if (lastIndexOf == -1) {
                return R.error(511, "该文件没有后缀");
            } else {
                String suffix = fileName.substring(lastIndexOf);
                if (!".xls".equals(suffix)) {
                    return R.error(511, "只支持后缀为xls的excel文件");
                } else {
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if (!file.exists()) {
                        return R.error(511, "找不到上传文件，请联系管理员");
                    } else {
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for (List<String> data : dataList) {
                            //循环
                            MeishuguanOrderEntity meishuguanOrderEntity = new MeishuguanOrderEntity();
//                            meishuguanOrderEntity.setMeishuguanOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            meishuguanOrderEntity.setMeishuguanId(Integer.valueOf(data.get(0)));   //美术馆 要改的
//                            meishuguanOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            meishuguanOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //预约人数 要改的
//                            meishuguanOrderEntity.setMeishuguanOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            meishuguanOrderEntity.setMeishuguanOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            meishuguanOrderEntity.setInsertTime(date);//时间
//                            meishuguanOrderEntity.setCreateTime(date);//时间
                            meishuguanOrderList.add(meishuguanOrderEntity);


                            //把要查询是否重复的字段放入map中
                            //订单编号
                            if (seachFields.containsKey("meishuguanOrderUuidNumber")) {
                                List<String> meishuguanOrderUuidNumber = seachFields.get("meishuguanOrderUuidNumber");
                                meishuguanOrderUuidNumber.add(data.get(0));//要改的
                            } else {
                                List<String> meishuguanOrderUuidNumber = new ArrayList<>();
                                meishuguanOrderUuidNumber.add(data.get(0));//要改的
                                seachFields.put("meishuguanOrderUuidNumber", meishuguanOrderUuidNumber);
                            }
                        }

                        //查询是否重复
                        //订单编号
                        List<MeishuguanOrderEntity> meishuguanOrderEntities_meishuguanOrderUuidNumber = meishuguanOrderService.selectList(new EntityWrapper<MeishuguanOrderEntity>().in("meishuguan_order_uuid_number", seachFields.get("meishuguanOrderUuidNumber")));
                        if (meishuguanOrderEntities_meishuguanOrderUuidNumber.size() > 0) {
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for (MeishuguanOrderEntity s : meishuguanOrderEntities_meishuguanOrderUuidNumber) {
                                repeatFields.add(s.getMeishuguanOrderUuidNumber());
                            }
                            return R.error(511, "数据库的该表中的 [订单编号] 字段已经存在 存在数据为:" + repeatFields.toString());
                        }
                        meishuguanOrderService.insertBatch(meishuguanOrderList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "批量新增", meishuguanOrderList.toString());
                        return R.ok();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(511, "批量插入数据异常，请联系管理员");
        }
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("list方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = meishuguanOrderService.queryPage(params);

        //字典表数据转换
        List<MeishuguanOrderView> list = (List<MeishuguanOrderView>) page.getList();
        for (MeishuguanOrderView c : list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "列表查询", list.toString());
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        logger.debug("detail方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        MeishuguanOrderEntity meishuguanOrder = meishuguanOrderService.selectById(id);
        if (meishuguanOrder != null) {


            //entity转view
            MeishuguanOrderView view = new MeishuguanOrderView();
            BeanUtils.copyProperties(meishuguanOrder, view);//把实体数据重构到view中

            //级联表
            MeishuguanEntity meishuguan = meishuguanService.selectById(meishuguanOrder.getMeishuguanId());
            if (meishuguan != null) {
                BeanUtils.copyProperties(meishuguan, view, new String[]{"id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setMeishuguanId(meishuguan.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(meishuguanOrder.getYonghuId());
            if (yonghu != null) {
                BeanUtils.copyProperties(yonghu, view, new String[]{"id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "单条数据查看", view.toString());
            return R.ok().put("data", view);
        } else {
            return R.error(511, "查不到数据");
        }
    }


    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MeishuguanOrderEntity meishuguanOrder, HttpServletRequest request) {
        logger.debug("add方法:,,Controller:{},,meishuguanOrder:{}", this.getClass().getName(), meishuguanOrder.toString());
        MeishuguanEntity meishuguanEntity = meishuguanService.selectById(meishuguanOrder.getMeishuguanId());
        if (meishuguanEntity == null) {
            return R.error(511, "查不到该美术馆");
        }
        // Double meishuguanNewMoney = meishuguanEntity.getMeishuguanNewMoney();

        if (false) {
        } else if (meishuguanEntity.getMeishuguanNewMoney() == null) {
            return R.error(511, "门票价格不能为空");
        } else if ((meishuguanEntity.getMeishuguanKucunNumber() - meishuguanOrder.getBuyNumber()) < 0) {
            return R.error(511, "购买数量不能大于库存数量");
        }

        //计算所获得积分
        Double buyJifen = 0.0;
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
        if (yonghuEntity == null)
            return R.error(511, "用户不能为空");
        if (yonghuEntity.getNewMoney() == null)
            return R.error(511, "用户金额不能为空");
        double balance = yonghuEntity.getNewMoney() - meishuguanEntity.getMeishuguanNewMoney() * meishuguanOrder.getBuyNumber();//余额
        if (balance < 0)
            return R.error(511, "余额不够支付");
        meishuguanOrder.setMeishuguanOrderTypes(101); //设置订单状态为已支付
        meishuguanOrder.setMeishuguanOrderTruePrice(meishuguanEntity.getMeishuguanNewMoney() * meishuguanOrder.getBuyNumber()); //设置实付价格
        meishuguanOrder.setYonghuId(userId); //设置订单支付人id
        meishuguanOrder.setMeishuguanOrderUuidNumber(String.valueOf(new Date().getTime()));
        meishuguanOrder.setInsertTime(new Date());
        meishuguanOrder.setCreateTime(new Date());
        meishuguanEntity.setMeishuguanKucunNumber(meishuguanEntity.getMeishuguanKucunNumber() - meishuguanOrder.getBuyNumber());
        meishuguanService.updateById(meishuguanEntity);
        meishuguanOrderService.insert(meishuguanOrder);//新增订单
        //更新第一注册表
        yonghuEntity.setNewMoney(balance);//设置金额
        yonghuService.updateById(yonghuEntity);


        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "前台新增", meishuguanOrder.toString());
        return R.ok();
    }

    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("order方法:,,Controller:{},,params:{}", this.getClass().getName(), params.toString());
        String meishuguanOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");


        String data = String.valueOf(params.get("meishuguans"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> meishuguans = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<MeishuguanOrderEntity> meishuguanOrderList = new ArrayList<>();
        //商品表
        List<MeishuguanEntity> meishuguanList = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : meishuguans) {
            //取值
            Integer meishuguanId = Integer.valueOf(String.valueOf(map.get("meishuguanId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            MeishuguanEntity meishuguanEntity = meishuguanService.selectById(meishuguanId);//购买的商品
            String id = String.valueOf(map.get("id"));

            //判断商品的库存是否足够
            if (meishuguanEntity.getMeishuguanKucunNumber() < buyNumber) {
                //商品库存不足直接返回
                return R.error(meishuguanEntity.getMeishuguanName() + "的库存不足");
            } else {
                //商品库存充足就减库存
                meishuguanEntity.setMeishuguanKucunNumber(meishuguanEntity.getMeishuguanKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            MeishuguanOrderEntity meishuguanOrderEntity = new MeishuguanOrderEntity<>();

            //赋值订单信息
            meishuguanOrderEntity.setMeishuguanOrderUuidNumber(meishuguanOrderUuidNumber);//订单编号
            meishuguanOrderEntity.setMeishuguanId(meishuguanId);//美术馆
            meishuguanOrderEntity.setYonghuId(userId);//用户
            meishuguanOrderEntity.setBuyNumber(buyNumber);//预约人数 ？？？？？？
            meishuguanOrderEntity.setMeishuguanOrderTypes(101);//订单类型
            meishuguanOrderEntity.setInsertTime(new Date());//添加时间
            meishuguanOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            //计算金额
            Double money = new BigDecimal(meishuguanEntity.getMeishuguanNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

            if (yonghuEntity.getNewMoney() - money < 0) {
                return R.error("余额不足,请充值！！！");
            } else {
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额


                meishuguanOrderEntity.setMeishuguanOrderTruePrice(money);

            }
            meishuguanOrderList.add(meishuguanOrderEntity);
            meishuguanList.add(meishuguanEntity);

        }
        meishuguanOrderService.insertBatch(meishuguanOrderList);
        meishuguanService.updateBatchById(meishuguanList);
        yonghuService.updateById(yonghuEntity);

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "前台订单", params.toString());
        return R.ok();
    }


    /**
     * 退款
     */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request) {
        logger.debug("refund方法:,,Controller:{},,id:{}", this.getClass().getName(), id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        MeishuguanOrderEntity meishuguanOrder = meishuguanOrderService.selectById(id);//当前表service
        Integer buyNumber = meishuguanOrder.getBuyNumber();
        Integer meishuguanId = meishuguanOrder.getMeishuguanId();
        if (meishuguanId == null)
            return R.error(511, "查不到该美术馆");
        MeishuguanEntity meishuguanEntity = meishuguanService.selectById(meishuguanId);
        if (meishuguanEntity == null)
            return R.error(511, "查不到该美术馆");
        Double meishuguanNewMoney = meishuguanEntity.getMeishuguanNewMoney();
        if (meishuguanNewMoney == null)
            return R.error(511, "美术馆价格不能为空");

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
        if (yonghuEntity == null)
            return R.error(511, "用户不能为空");
        if (yonghuEntity.getNewMoney() == null)
            return R.error(511, "用户金额不能为空");
        Double zhekou = 1.0;

        //计算金额
        Double money = meishuguanEntity.getMeishuguanNewMoney() * buyNumber * zhekou;
        //计算所获得积分
        Double buyJifen = 0.0;
        yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


        meishuguanEntity.setMeishuguanKucunNumber(meishuguanEntity.getMeishuguanKucunNumber() + buyNumber);

        meishuguanOrder.setMeishuguanOrderTypes(102);//设置订单状态为已退款
        meishuguanOrderService.updateAllColumnById(meishuguanOrder);//根据id更新
        yonghuService.updateById(yonghuEntity);//更新用户信息
        meishuguanService.updateById(meishuguanEntity);//更新订单中美术馆的信息
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "前台退款", meishuguanOrder.toString());

        return R.ok();
    }

    /**
     * 完成
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id, HttpServletRequest request) {
        logger.debug("refund:,,Controller:{},,ids:{}", this.getClass().getName(), id.toString());
        MeishuguanOrderEntity meishuguanOrderEntity = meishuguanOrderService.selectById(id);
        meishuguanOrderEntity.setMeishuguanOrderTypes(103);//设置订单状态为已完成
        meishuguanOrderService.updateById(meishuguanOrderEntity);

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")), TABLE_NAME, String.valueOf(request.getSession().getAttribute("username")), "前台完成", meishuguanOrderEntity.toString());
        return R.ok();
    }


}

