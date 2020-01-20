package com.pemila.aries.web.bydb.config;

import com.pemila.aries.web.bydb.entity.User;
import com.pemila.aries.web.bydb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getUserByUserName(userName);
		if(user == null){
			throw new UsernameNotFoundException("用户不存在!");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
	}
}
