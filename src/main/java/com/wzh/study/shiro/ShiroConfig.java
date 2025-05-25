package com.wzh.study.shiro;


import jakarta.servlet.Filter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("customHashedCredentialsMatcher")
    public CustomHashedCredentialsMatcher customHashedCredentialsMatcher(){
        CustomHashedCredentialsMatcher customHashedCredentialsMatcher = new CustomHashedCredentialsMatcher();
        customHashedCredentialsMatcher.setHashAlgorithmName("MD5");
        return customHashedCredentialsMatcher;
    }

    @Bean("customRealm")
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(customHashedCredentialsMatcher());
        return customRealm;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm());
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);
        return defaultWebSecurityManager;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
        CustomRealm customRealm = (CustomRealm) context.getBean("customRealm");
        manager.setRealm(customRealm);
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);




        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("customFilter",new CustomFilter());
        shiroFilterFactoryBean.setFilters(filters);




        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/layui/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/api/user/login","anon");
        filterChainDefinitionMap.put("/api/user/userLogin","anon");
        filterChainDefinitionMap.put("/api/user/token","anon");
        filterChainDefinitionMap.put("/api/index/404","anon");
        filterChainDefinitionMap.put("/api/index","anon");
        filterChainDefinitionMap.put("/test.html","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/api/home","anon");
        filterChainDefinitionMap.put("/api/user","anon");
        filterChainDefinitionMap.put("/api/role","anon");
        filterChainDefinitionMap.put("/api/permission","anon");
        filterChainDefinitionMap.put("/**", "customFilter,authc"); // 使用自定义过滤器

        shiroFilterFactoryBean.setLoginUrl("/api/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }


}
