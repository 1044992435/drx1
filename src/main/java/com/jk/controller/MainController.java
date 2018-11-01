package com.jk.controller;

import com.jk.model.Tree;
import com.jk.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        return "index";
    }
    @Autowired
    private MainService mainService;

    @RequestMapping("findhomepage")
    @ResponseBody
    public List<Tree> findhomepage(){
        List<Tree> menuList = mainService.finMenuTree();
        return menuList;
    }



}
