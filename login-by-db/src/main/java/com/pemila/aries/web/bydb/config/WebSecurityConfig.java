package com.pemila.aries.web.bydb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author pemila
 * @date 2020/1/20 10:40
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	@Autowired
	public void setUserDetailsService(DbUserDetailsServiceImpl dbUserDetailsService) {
		this.userDetailsService = dbUserDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * 使用hasRole来定义资源需要拥有的角色时，配置项会在hasRole方法的参数上添加前缀ROLE_,
		 * 因此在自定义的userDetailService设定用户角色时需要给该用户已有的角色前添加ROLE_前缀，
		 */

		http.authorizeRequests()
				// 不需要权限即可访问
				.antMatchers("/").permitAll()
				// user/**需要USER权限才可访问
				.antMatchers("/user/**").hasRole("USER")
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/user")
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}

	/**
	 * 添加自定义的登录校验
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
