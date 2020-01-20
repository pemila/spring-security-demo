package com.pemila.aries.web.bydb.dao;

import com.pemila.aries.web.bydb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangchao
 * @date 2020/1/20 11:04
 **/
public interface UserDao extends JpaRepository<User,Long> {
	/** 通过账号查询用户*/
	User findByUserName(String userName);

	/** 判断用户是否存在*/
	boolean existsUserByUserName(String userName);
}
