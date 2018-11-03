package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.model.AdminUser;
import com.jk.model.Tree;
import com.jk.service.MainService;
import com.jk.utils.CommonConf;
import com.jk.utils.HttpClientUtil;
import com.jk.utils.Md5Util;
import com.jk.utils.TimeUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.http.ParseException;


/**
 * <pre>com.jk.controller
 * 类名称：CommentController
 * 类描述：
 * 创建人：王震
 * 创建时间：2018/10/31  9:53
 * 修改人：王震
 * 修改时间：2018/10/31  9:53
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("comment")
public class MainController {

    @RequestMapping("index")
   public String toShowList(){
        return "indexs";
    }
    @Autowired
    private MainService mainService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("findhomepage")
    @ResponseBody
    public List<Tree> findhomepage(){
        List<Tree> menuList = mainService.finMenuTree();
        return menuList;
    }



    //验证用户名是否正确
    @RequestMapping("userName")
    @ResponseBody
    public String userName(String username){
        String flag ="1";
        boolean boo=mainService.userName(username);
        if(boo){
            flag ="1";
        }else{
            flag ="2";
        }

        return flag;
    }
    //验证登录是否通过
    @RequestMapping("login")
    @ResponseBody
    public String login(AdminUser adminUser, HttpServletRequest request){
        String flag ="1";
      AdminUser login= mainService.login(adminUser);
      if (login!=null){
            if (login.getPassword()!=adminUser.getPassword()){
                request.getSession().setAttribute("login",login);
                flag="2";
            }

      }
        return "flag";
    }
     //注册用户
    @RequestMapping("regUser")
    @ResponseBody
    public String regUser(AdminUser adminUser,HttpServletRequest request){
        String flag ="1";
        boolean boo=mainService.regUser(adminUser);
        request.getSession().setAttribute("login",adminUser);
        if(boo){
            flag ="1";
        }else{
            flag ="2";
        }
        return flag;
    }



    @RequestMapping("getCode")
    @ResponseBody
    public HashMap<String, Object> getCode(String loginNumber, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        //判断本次获取距离上次是否超过了60s
        /*String cacheKey = CommonConf.RANDOM_LOCK+loginNumber;
          Boolean hasKey = redisTemplate.hasKey(cacheKey);
        if(hasKey) {
            result.put("code", 1);
            result.put("msg", "距离上次获取时间不足60s,不能获取验证码");
            return result;
        }*/
        //判断手机号是否存在
      /* AdminUser adminUser = new AdminUser();
        adminUser.setLoginNumber(loginNumber);
        AdminUser loginUser = mainService.login(adminUser);
        if(loginUser == null) {
            result.put("code", 2);
            result.put("msg", "发送验证码失败");
            return result;
        }*/
        //随机生成验证码
        double floor = Math.floor(Math.random()*1000000);
        String code = String.valueOf(floor);
        Boolean sendSms = sendSms(loginNumber, code);
        if(!sendSms) {
            result.put("code", 3);
            result.put("msg", "发送验证码失败");
            return result;
        }
        //验证码缓存到Redis 设置验证码的有效时间
        String randomCache = CommonConf.RANDOM_CACHE+loginNumber;
        redisTemplate.opsForValue().set(randomCache, code, CommonConf.RANDOM_CACHE_TIME_OUT, TimeUnit.MINUTES);
        //设置距离下一次可获取的 锁时间
        String randomLock = CommonConf.RANDOM_LOCK+loginNumber;
        redisTemplate.opsForValue().set(randomLock, "lock", CommonConf.RANDOM_LOCK_TIME, TimeUnit.SECONDS);
        //将用户信息保存到session中
        HttpSession session = request.getSession();
        result.put("code", 0);
        result.put("msg", "成功");
        return result;
    }

    public Boolean sendSms(String phoneNumber,String code) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("accountSid", CommonConf.ACCOUNTSID);
        params.put("to", phoneNumber);
        params.put("param", code);
        String timestamp = TimeUtil.format(new Date());
        params.put("timestamp", timestamp);
        String sig = Md5Util.getMd532(CommonConf.ACCOUNTSID+CommonConf.AUTH_TOKEN+timestamp);
        params.put("sig", sig);
        params.put("templateid", CommonConf.TEMPLATEID);
        String post = HttpClientUtil.post(CommonConf.SMS_URL, params);
        JSONObject parseObject = JSON.parseObject(post);
        String respCode = parseObject.getString("respCode");
        return respCode.equals(CommonConf.RESP_CODE);
    }


}
