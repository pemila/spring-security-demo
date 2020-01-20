package com.pemila.aries.web.bydb.service.impl;

import com.pemila.aries.web.bydb.dao.UserDao;
import com.pemila.aries.web.bydb.entity.User;
import com.pemila.aries.web.bydb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pemila
 * @date 2020/1/20 11:09
 **/
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	@Override
	public void insertUser(User user) {
		if(userDao.existsUserByUserName(user.getUserName())){
			throw new RuntimeException("用户名已存在");
		}
		userDao.save(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

}
