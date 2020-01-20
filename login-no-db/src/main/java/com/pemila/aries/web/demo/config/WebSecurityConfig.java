package com.pemila.aries.web.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 向web页面添加安全配置，只有认证过的用户可以访问
 * @author pemila
 * @date 2020/1/19 15:35
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 匹配/路径，不需要权限即可访问
				.antMatchers("/").permitAll()
				// 匹配/user及其下所有路径，需要USER权限才可访问
				.antMatchers("/user/**").hasRole("USER")
				.and()
				// 配置登录页面，登录成功跳转/user页面
				.formLogin().loginPage("/login").defaultSuccessUrl("/user")
				.and()
				// 配置登出页面，退出成功跳转至/login页面
				.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService(){
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(users.username("pemila").password("123456").roles("USER").build());
		return manager;
	}
}
