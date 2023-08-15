package com.strr.config;

import com.strr.admin.controller.SysAuthorityController;
import com.strr.admin.controller.SysPropertiesController;
import com.strr.admin.controller.SysRoleController;
import com.strr.admin.controller.SysUserController;
import com.strr.admin.mapper.SysAuthorityMapper;
import com.strr.admin.mapper.SysPropertiesMapper;
import com.strr.admin.mapper.SysRoleMapper;
import com.strr.admin.mapper.SysUserMapper;
import com.strr.admin.service.SysAuthorityService;
import com.strr.admin.service.SysPropertiesService;
import com.strr.admin.service.SysRoleService;
import com.strr.admin.service.SysUserService;
import com.strr.admin.service.impl.SysAuthorityServiceImpl;
import com.strr.admin.service.impl.SysPropertiesServiceImpl;
import com.strr.admin.service.impl.SysRoleServiceImpl;
import com.strr.admin.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class AdminConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean(SysAuthorityService.class)
    public SysAuthorityService sysAuthorityService(SysAuthorityMapper sysAuthorityMapper) {
        return new SysAuthorityServiceImpl(sysAuthorityMapper);
    }

    @Bean
    @ConditionalOnMissingBean(SysPropertiesService.class)
    public SysPropertiesService sysPropertiesService(SysPropertiesMapper sysPropertiesMapper) {
        return new SysPropertiesServiceImpl(sysPropertiesMapper);
    }

    @Bean
    @ConditionalOnMissingBean(SysRoleService.class)
    public SysRoleService sysRoleService(SysRoleMapper sysRoleMapper) {
        return new SysRoleServiceImpl(sysRoleMapper);
    }

    @Bean
    @ConditionalOnMissingBean(SysUserService.class)
    public SysUserService sysUserService(SysUserMapper sysUserMapper) {
        return new SysUserServiceImpl(sysUserMapper);
    }

    @Bean
    @ConditionalOnMissingBean(SysAuthorityController.class)
    public SysAuthorityController sysAuthorityController(SysAuthorityService sysAuthorityService) throws Exception {
        registerController("sysAuthorityController");
        return new SysAuthorityController(sysAuthorityService);
    }

    @Bean
    @ConditionalOnMissingBean(SysPropertiesController.class)
    public SysPropertiesController sysPropertiesController(SysPropertiesService sysPropertiesService) throws Exception {
        registerController("sysPropertiesController");
        return new SysPropertiesController(sysPropertiesService);
    }

    @Bean
    @ConditionalOnMissingBean(SysRoleController.class)
    public SysRoleController sysRoleController(SysRoleService sysRoleService) throws Exception {
        registerController("sysRoleController");
        return new SysRoleController(sysRoleService);
    }

    @Bean
    @ConditionalOnMissingBean(SysUserController.class)
    public SysUserController sysUserController(SysUserService sysUserService) throws Exception {
        registerController("sysUserController");
        return new SysUserController(sysUserService);
    }

    // 注册controller
    private void registerController(String beanName) throws Exception {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 注册controller
        Method method = requestMappingHandlerMapping.getClass().getSuperclass().getSuperclass()
                .getDeclaredMethod("detectHandlerMethods", Object.class);
        //将private改为可使用
        method.setAccessible(true);
        method.invoke(requestMappingHandlerMapping, beanName);
    }

    // 注销controller
    private void unregisterController(String beanName) {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Object controller = applicationContext.getBean(beanName);
        final Class<?> targetClass = controller.getClass();
        ReflectionUtils.doWithMethods(targetClass, method -> {
            Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
            try {
                Method createMappingMethod = RequestMappingHandlerMapping.class.
                        getDeclaredMethod("getMappingForMethod", Method.class, Class.class);
                createMappingMethod.setAccessible(true);
                RequestMappingInfo requestMappingInfo =(RequestMappingInfo)
                        createMappingMethod.invoke(requestMappingHandlerMapping, specificMethod, targetClass);
                if(requestMappingInfo != null) {
                    requestMappingHandlerMapping.unregisterMapping(requestMappingInfo);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }, ReflectionUtils.USER_DECLARED_METHODS);
    }
}
