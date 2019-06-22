package com.just.myproject.Intercetor;

import com.alibaba.fastjson.JSON;
import com.just.myproject.Utils.JsonData;
import com.just.myproject.Utils.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AdminIntercepter implements HandlerInterceptor {
    /**
     * 进入controller 之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token= request.getHeader("access_token");
//        System.out.println(token);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if(token==null||token.equals("")){
            printJson(response,0,"token 为空，请登陆！");
            return false;
        }
//        System.out.println(JwtUtils.checkJWT(token).get("worknum"));
        if(token!=null&&JwtUtils.checkJWT(token)!=null){
            if((int)JwtUtils.checkJWT(token).get("isAdmin")>0){
                return true;
            }
            printJson(response,0,"你没有操作权限");
        }
        printJson(response,0,"token过期,请重新登陆");
        return false;
    }

    /**
     * 调用controller之后，视图渲染之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 完成之后，用于资源清理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private static void printJson(HttpServletResponse response, int code,String message) {
        JsonData responseResult = new JsonData(code,false,message);
        String content = JSON.toJSONString(responseResult);
        printContent(response, content);
    }
    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
