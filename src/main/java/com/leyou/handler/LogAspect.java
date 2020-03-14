package com.leyou.handler;

import com.alibaba.fastjson.JSONObject;
import com.leyou.entity.vo.Output;
import com.leyou.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
@Slf4j
public class LogAspect {

    //用来记录请求进入的时间，防止多线程时出错，这里用了ThreadLocal
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切入点，controller下面的所有类的所有公有方法，这里需要更改成自己项目的
     */
    @Pointcut("execution(public * com.leyou.controller..*.*(..))")  //controller下的所有方法
    public void controller() {
    }

    ;

    /**
     * 方法之前执行，日志打印请求信息
     *
     * @param joinPoint joinPoint
     */
    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        HttpServletRequest request = SpringContextUtils.getRequest();
        //打印当前的请求路径
        if (SpringContextUtils.getActiveProfile().equals("dev")) {  //测试环境
            System.out.println("=============uri============");
            System.out.println("uri:" + request.getRequestURI());
            System.out.println("=============header============");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                System.out.println(name + ":" + request.getHeader(name));
            }
            System.out.println("=============body============");
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                System.out.println(name + ":" + request.getParameter(name));
            }
        } else {    //生产环境
            log.info("=============uri============");
            log.info("uri:" + request.getRequestURI());
            log.info("=============header============");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                log.info(name + ":" + request.getHeader(name));
            }
            log.info("=============body============");
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                log.info(name + ":" + request.getParameter(name));
            }
        }
    }

    @Around("controller()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //继续执行被拦截的方法
        return pjp.proceed();
    }

    /**
     * 方法返回之前执行，打印才返回值以及方法消耗时间
     *
     * @param output 返回值
     */
    @AfterReturning(returning = "output", pointcut = "controller()")
    public void doAfterRunning(Output output) {
        //打印返回值信息
        if (SpringContextUtils.getActiveProfile().equals("dev")) {  //测试环境
            System.out.println("=============output============");
            System.out.println(JSONObject.toJSONString(output));
            System.out.println("Request spend times :" + (System.currentTimeMillis() - startTime.get()) + "ms");
        } else {
            log.info("=============output============");
            log.info("Response:[{}]", output);  //记录请求结果
            log.info("Request spend times : [{}ms]", System.currentTimeMillis() - startTime.get());    //记录请求耗时
        }
    }
}
