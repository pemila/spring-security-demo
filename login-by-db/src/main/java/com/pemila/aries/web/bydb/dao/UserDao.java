package com.pemila.aries.web.bydb.dao;

import com.pemila.aries.web.bydb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangchao
 * @date 2020/1/20 11:04
 **/
public interface UserDao extends JpaRepository<UserEntity,Long> {
	/** 通过账号查询用户*/
	UserEntity findByUsername(String username);

	/** 判断用户是否存在*/
	boolean existsUserByUsername(String username);
}
