package com.jk.controller;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.*;
import com.jk.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <pre>com.jk.controller
 * 类名称：PowerController
 * 类描述：
 * 创建人：董睿晓
 * 创建时间：2018/10/31  15:22
 * 修改人：董睿晓
 * 修改时间：2018/10/31  15:22
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("admin")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @RequestMapping("queryadmin")
    @ResponseBody
    public JSONObject queryadmin( Integer rows, Integer page){
        JSONObject json = powerService.queryadmin(rows,page);
        return json;
    }

    @RequestMapping("queryrole")
    @ResponseBody
    public JSONObject queryrole( Integer rows, Integer page){
        JSONObject json = powerService.queryrole(rows,page);
        return json;
    }

    @RequestMapping("toaddrole")
    public  String toaddrole(Model model,Integer uid){
        model.addAttribute("uid",uid);
        return "role";    }

    //根据中间表查询角色
    @RequestMapping("queryRole")
    @ResponseBody
    public  List<AdminRole>  queryRole(Integer uid){
        List<AdminRole> list=powerService.queryRole(uid);
        return 	list;
    }

    //提交新增角色
    @RequestMapping("saveRole")
    @ResponseBody
    public  void saveRole(Integer userId,String roleIds){
        powerService.saveRole(userId,roleIds.split(","));
    }

    @RequestMapping("toaddpower")
    public  String toaddpower(Model model,Integer id){
        model.addAttribute("rid",id);
        return "tree";    }


    @RequestMapping("querypower")
    @ResponseBody
    public List<Tree> querypower(){
        //根据pid 查询子节点列表
        List<Tree> trees = powerService.querypower(0);
        return trees;
    }

    //根据角色查询权限树
    @RequestMapping("queryroletree")
    @ResponseBody
    public List<RoleTree> queryroletree(Integer rid){
        List<RoleTree> list =powerService.queryroletree(rid);
        return list;
    }

    @RequestMapping("addpower")
    @ResponseBody
    public  void addpower(Integer rid,String ids){
        powerService.addpower(rid,ids.split(","));
    }

    @RequestMapping("deleteadmin")
    @ResponseBody
    public  void deleteadmin(Integer uid){
        powerService.deleteadmin(uid);
    }

    @RequestMapping("deleterole")
    @ResponseBody
    public  void deleterole(Integer id){
        powerService.deleterole(id);
    }

    @RequestMapping("toaddroles")
    public  String toaddroles(Model model){ return "addroles";    }

    @RequestMapping("addroles")
    @ResponseBody
    public  void addroles(Role role){
        powerService.addroles(role);
    }

    @RequestMapping("toupdateroles")
    public  String toupdateroles(Model model,Integer id){
        Role list=powerService.queryRoles(id);
        model.addAttribute("list",list);
        return "addroles";    }

    @RequestMapping("updateroles")
    @ResponseBody
    public  void updateroles(Role role){
        powerService.updateroles(role);
    }

    @RequestMapping("deletes")
    @ResponseBody
    public  int deletes(String ids){
        int a=powerService.deletes(ids);
        return a;
    }
}
