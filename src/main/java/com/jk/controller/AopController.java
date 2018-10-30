package com.jk.controller;


import com.jk.model.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class AopController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final  static org.slf4j.Logger logger= org.slf4j.LoggerFactory.getLogger(AopController.class);


    @Pointcut("execution(public * com.jk.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void webPointCut(){

    }



    @Before("webPointCut()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        System.out.println("【注解：Before】------------------切面  before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("【注解：Before】浏览器输入的网址=URL : " + request.getRequestURL().toString());
        System.out.println("【注解：Before】HTTP_METHOD : " + request.getMethod());
        System.out.println("【注解：Before】IP : " + request.getRemoteAddr());
        System.out.println("【注解：Before】执行的业务方法名=CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("【注解：Before】业务方法获得的参数=ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }




    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webPointCut()")
    public void after(JoinPoint joinPoint){

        Logger logger = new Logger();

        //记录http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //从request中获取http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
        //url
        logger.setUrl(request.getRequestURI());
        //method
        logger.setMethod(request.getMethod());
        //ip
        logger.setIp(request.getRemoteAddr());
        //类方法
        logger.setClass_method(joinPoint.getSignature().getDeclaringTypeName()+ "."+joinPoint.getSignature().getName());

        mongoTemplate.save(logger);

        System.out.println("【注解：After】方法最后执行....."+ AopController.logger);
    }

}
