package com.yangyang.manage.config;

import com.yangyang.utils.utils.GenerateUsertoken;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //  this.redisTemplate=new RedisTemplate<String, String>();
        System.out.println("执行到了managedde拦截方法");
        //  HttpSession session=httpServletRequest.getSession();
        System.out.println(httpServletRequest.getRequestURL());
        System.out.println(httpServletRequest.getServletPath());
        System.out.println(httpServletRequest.getPathInfo());
        String path = httpServletRequest.getServletPath();
        String token = httpServletRequest.getHeader("token");
        LOGGER.info("token" + token);
        if (token == null) {
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter pw = httpServletResponse.getWriter();
            JSONObject res = new JSONObject();
            res.put("status",401);
            res.put("message","未发现口令");
            pw.println(res.toString());
            pw.flush();
            pw.close();
            return false;
        }
        Map<String, Object> userInfo = GenerateUsertoken.getInfoFromToken(token);
        String userId = (String) userInfo.get("user_id");
        Integer role = (Integer) userInfo.get("role");
        String token2 = redisTemplate.opsForValue().get(userId);
        LOGGER.info("redistoken" + token2);
        Long timeStamp = (Long) userInfo.get("timestamp");
        if (token2 != null || token2.equals(token)) {
            if (System.currentTimeMillis() > timeStamp) {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter pw = httpServletResponse.getWriter();
                JSONObject res = new JSONObject();
                res.put("status",401);
                res.put("message","口令过时，重新登录");
                pw.println(res.toString());
                pw.flush();
                pw.close();
                return false;
            } else {
                String newtoken = GenerateUsertoken.createToken(userId, role);
                redisTemplate.opsForValue().set(userId, newtoken);
                return true;
            }
        } else {
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter pw = httpServletResponse.getWriter();
            JSONObject res = new JSONObject();
            res.put("status",0);
            res.put("message","口令错误");
            pw.println(res.toString());
            pw.flush();
            pw.close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
