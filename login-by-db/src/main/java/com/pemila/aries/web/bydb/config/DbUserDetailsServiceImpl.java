package com.pemila.aries.web.bydb.config;

import com.pemila.aries.web.bydb.entity.UserEntity;
import com.pemila.aries.web.bydb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pemila
 * @date 2020/1/20 10:45
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbUserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	/**
	 * 使用hasRole来定义资源需要拥有的角色时，配置项会在hasRole方法的参数上添加前缀ROLE_,
	 * 因此在自定义的userDetailService设定用户角色时需要给该用户已有的角色前添加ROLE_前缀
	 */
	private static final String AUTHOR_ROLE_PREFIX = "ROLE_";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userService.getUserByUsername(username);
		if(userEntity == null){
			throw new UsernameNotFoundException("用户不存在!");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(AUTHOR_ROLE_PREFIX +"USER"));
		return new User(userEntity.getUsername(), userEntity.getPassword(),authorities);
	}
}
