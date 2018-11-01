package com.jk.controller;

import com.jk.model.Tree;
import com.jk.service.MainService;
import com.jk.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @RequestMapping(value="/authImage",method=RequestMethod.GET)
    public String authImage(){
        return "authImage";
    }

    //获取验证码图片
    @RequestMapping(value="/getImage",method= RequestMethod.GET)
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int w = 100, h = 30;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }

    //验证验证码是否正确
    @RequestMapping(value="validImage",method=RequestMethod.GET)
    public String validImage(HttpServletRequest request,HttpSession session){
        String code = request.getParameter("code");
        Object verCode = session.getAttribute("verCode");
        if (null == verCode) {
            request.setAttribute("errmsg", "验证码已失效，请重新输入");
            return "验证码已失效，请重新输入";
        }
        String verCodeStr = verCode.toString();
        LocalDateTime localDateTime = (LocalDateTime)session.getAttribute("codeTime");
        long past = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if(verCodeStr == null || code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)){
            request.setAttribute("errmsg", "验证码错误");
            return "验证码错误";
        } else if((now-past)/1000/60>5){
            request.setAttribute("errmsg", "验证码已过期，重新获取");
            return "验证码已过期，重新获取";
        } else {
            //验证成功，删除存储的验证码
            session.removeAttribute("verCode");
            return "200";
        }
    }

}
