package com.wy.springframework.test;

import com.wy.springframework.aop.AdvisedSupport;
import com.wy.springframework.aop.TargetSource;
import com.wy.springframework.aop.aspect.AspectJExpressionPointcut;
import com.wy.springframework.aop.framework.Cglib2AopProxy;
import com.wy.springframework.aop.framework.JdkDynamicAopProxy;
import com.wy.springframework.context.support.ClassPathXmlApplicationContext;
import com.wy.springframework.core.io.DefaultResourceLoader;
import com.wy.springframework.test.bean.Husband;
import com.wy.springframework.test.bean.IUserService;
import com.wy.springframework.test.bean.UserService;
import com.wy.springframework.test.bean.Wife;
import com.wy.springframework.test.event.CustomEvent;
import com.wy.springframework.test.interceptor.UserServiceInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestApi {
    private DefaultResourceLoader resourceLoader;

    @Test
    public void test_circle(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-dep.xml");
        final Husband husband = classPathXmlApplicationContext.getBean("husband", Husband.class);
        final Wife wife = classPathXmlApplicationContext.getBean("wife", Wife.class);
        System.out.println(husband.getWife());
        System.out.println(wife.queryHusband());
    }

    @Test
    public void test_proxy_values(){
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-proxy-values.xml");
        final IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_values(){
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring-values.xml");
        final IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }


    @Test
    public void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        final IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_property(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        final IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService);
    }

    @Test
    public void test_aop2(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:aop.xml");
        final IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());

    }


    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test(){
    }
    @Test
    public void test_xml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring2.xml");
        applicationContext.registerShutdownHook();
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1L, "123123"));
        final UserService userService1 = applicationContext.getBean("userService", UserService.class);
        final UserService userService2 = applicationContext.getBean("userService", UserService.class);
        userService2.queryUserInfo();
        System.out.println(userService1);
        System.out.println(userService2);


    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(
                "execution(* com.wy.springframework.test.bean.UserService.*(..))");
        final Class<UserService> userServiceClass = UserService.class;
        final Method method = userServiceClass.getDeclaredMethod("queryUserInfo");
        System.out.println(pointcut.matcher(userServiceClass));
        System.out.println(pointcut.matcher(method,userServiceClass));
    }
    @Test
    public void test_dynamic(){
        IUserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut(
                "execution(* com.wy.springframework.test.bean.IUserService.*(..))"
        ));
         IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxy_jdk.queryUserInfo());
        final IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(proxy_cglib.queryUserInfo());


    }
}
