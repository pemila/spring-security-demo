package com.pemila.aries.web.bydb.service;

import com.pemila.aries.web.bydb.entity.UserEntity;

/**
 * @author zhangchao
 * @date 2020/1/20 11:07
 **/
public interface UserService {

	void insertUser(UserEntity userEntity);

	UserEntity getUserByUsername(String username);
}
