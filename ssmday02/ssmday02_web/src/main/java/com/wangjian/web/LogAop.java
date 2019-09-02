package com.wangjian.web;

import com.wangjian.SysLog;
import com.wangjian.UserInfo;
import com.wangjian.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitime;
    private Class aClass;
    private Method method;
    //配置前置通知，在方法执行之前获得方法的名称，执行的类，还有执行之前的时间点
    @Before("execution(* com.wangjian.web.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        //得到执行前的时间
        visitime = new Date();
        //通过反射得到具体要执行的类
         aClass = joinPoint.getTarget().getClass();
         //通过反射得到要执行的方法名
        String name = joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //获取要执行的方法
        if(args==null || args.length==0){
           method = aClass.getMethod(name);

        }else {
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
             method = aClass.getMethod(name, classes);
        }
    }
    //配置后置通知，得到URL，得到方法执行的总时间，当前操作的用户，当前用户的IP
    @After("execution(* com.wangjian.web.*.*(..))")
    public void doAfter(JoinPoint joinPoint){
        //获取方法执行的时间
        long time = new Date().getTime()- visitime.getTime();
        String url="";
        if(aClass!=null&&method!=null&&aClass!=LogAop.class){
            //通过注解获取类上的路径
            RequestMapping annotationClass = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if(annotationClass!=null){
                String[] value = annotationClass.value();
                //获取方法上的路径
                RequestMapping annotationMethod = method.getAnnotation(RequestMapping.class);
                if(annotationMethod!=null){
                    String[] value1 = annotationMethod.value();
                    //得到URL
                    url=value[0]+value1[0];
                    //得到用户的ip
                    String remoteAddr = request.getRemoteAddr();
                    //得到当前操作的用户
                    SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                    UserInfo principal = (UserInfo) emptyContext.getAuthentication().getPrincipal();
                    String username = principal.getUsername();
                    //将得到数据封装到实体类中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setId(remoteAddr);
                    sysLog.setUrl(url);
                    sysLog.setMethod("类名"+aClass.getName()+"方法名"+method.getName());
                    sysLog.setVisitTime(visitime);
                    // 将日志信息存储到日志信息中
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
