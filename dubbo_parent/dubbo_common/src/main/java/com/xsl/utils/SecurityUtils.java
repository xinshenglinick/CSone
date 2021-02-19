package com.xsl.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述
 * @创建人 wangyue
 * @创建时间2020/12/1210:48
 */

public class SecurityUtils {

    /**
     * 密码加密
     * @param password
     */
    public static String jiami(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return  passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println("===============");
        System.out.println(jiami("123456"));
    }
    /**
     * 获取权限
     * @return
     */
    public static List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return auths;
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public static String getLoginUsername(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name;
    }
}
