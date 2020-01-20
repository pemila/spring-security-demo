package com.pemila.aries.web.bydb.service;

import com.pemila.aries.web.bydb.entity.User;

/**
 * @author zhangchao
 * @date 2020/1/20 11:07
 **/
public interface UserService {

	void insertUser(User user);

	User getUserByUserName(String userName);
}
