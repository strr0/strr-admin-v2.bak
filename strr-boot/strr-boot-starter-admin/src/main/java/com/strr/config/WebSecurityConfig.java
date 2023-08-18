package com.strr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strr.admin.mapper.SysResourceMapper;
import com.strr.admin.mapper.SysUserDetailsMapper;
import com.strr.admin.model.SysUserDetails;
import com.strr.base.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;
import java.util.List;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private SysUserDetailsMapper sysUserDetailsMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * A Spring Security filter chain for authentication.
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 登录
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.ok(authentication.getPrincipal())));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.error()));
                    out.flush();
                    out.close();
                })
                //退出
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.ok()));
                    out.flush();
                    out.close();
                })
                // 禁用csrf
                .and()
                .csrf().disable()
                .exceptionHandling();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            List<SysUserDetails> userDetailsList = sysUserDetailsMapper.getByUsername(username);
            if (!userDetailsList.isEmpty()) {
                SysUserDetails userDetails = userDetailsList.get(0);
                userDetails.setRoleList(sysUserDetailsMapper.listRoleByUserId(userDetails.getId()));
                userDetails.setResourceList(sysResourceMapper.listByUserId(userDetails.getId()));
                return userDetails;
            }
            throw new UsernameNotFoundException(String.format("User '%s' not found.", username));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
