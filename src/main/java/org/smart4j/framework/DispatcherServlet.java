package org.smart4j.framework;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器，汇聚除jsp、静态资源以外的所有请求，框架的核心
 * Created by ithink on 2017-6-17.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();

        ServletContext servletContext = config.getServletContext();

        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAssetPath() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 取得请求信息，以进一步得到处理方法
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        if(requestPath.equals("/customer")){
            System.out.print("");
        }

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if(handler != null){
            Class<?> cls = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(cls);
            Method action = handler.getAction();

            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String name = paramNames.nextElement();
                Object obj = request.getParameter(name);
                paramMap.put(name, obj);
            }

            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if(StringUtil.isNotEmpty(body)){
                String[] params = body.split("&");
                if(ArrayUtils.isNotEmpty(params)){
                    for(String param : params){
                        String[] mapping = param.split("=");
                        if(ArrayUtils.isNotEmpty(mapping) && mapping.length==2){
                            paramMap.put(mapping[0], mapping[1]);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);
            Object result = ReflectionUtil.invokeMethod(controllerBean, action, param);
            if(result instanceof View){
                View view = (View) result;
                String path = view.getPath();
                if(path.startsWith("/")){
                    response.sendRedirect(request.getContextPath() + path);
                } else{
                    Map<String, Object> model = ((View) result).getModel();
                    for(Map.Entry<String, Object> entry : model.entrySet()){
                        request.setAttribute(entry.getKey(), entry.getValue());
                    }
                    request.getRequestDispatcher(ConfigHelper.getJspPath() + path).forward(request, response);
                }
            }else if(result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if(model != null){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JsonUtil.toJson(model));
                    response.getWriter().flush();
                    response.getWriter().close();
                }
            }
        }

    }
}
