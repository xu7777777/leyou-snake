package com.leyou.handler;

import com.alibaba.fastjson.JSONObject;
import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.Output;
import com.leyou.util.OutputUtil;
import com.leyou.util.SessionContext;
import com.leyou.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    private static HashSet<String> noNeedLoginUri = new HashSet<>();

    static {
        Environment env = SpringContextUtils.applicationContext.getEnvironment();
        noNeedLoginUri.add(env.getProperty("server.servlet.context-path")+"/opUser/login");
    }

    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //安全性校验 不是login请求的情况下
        String accessToken = request.getHeader("bops-atk");
        if (!noNeedLoginUri.contains(request.getRequestURI()) && !SessionContext.isNormalUser(accessToken)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            Output output = OutputUtil.fail(ErrorEnum.INVALID_TOKEN.getCode(), ErrorEnum.INVALID_TOKEN.getMsg());
            response.getWriter().println(JSONObject.toJSONString(output));
            return false;
        }
//        把当前token放进threadLocal
        SessionContext.setCurrentToken(accessToken);
        return true;
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
