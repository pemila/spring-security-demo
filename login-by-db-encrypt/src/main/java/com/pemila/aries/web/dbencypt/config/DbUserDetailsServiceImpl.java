package com.pemila.aries.web.dbencypt.config;

import com.pemila.aries.web.dbencypt.entity.UserEntity;
import com.pemila.aries.web.dbencypt.service.UserService;
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
 * @date 2020/1/20 14:44
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbUserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userService.getUserByUsername(username);
		if(userEntity==null){
			throw new UsernameNotFoundException("用户不存在!");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return new User(username,userEntity.getPassword(),authorities);
	}
}
