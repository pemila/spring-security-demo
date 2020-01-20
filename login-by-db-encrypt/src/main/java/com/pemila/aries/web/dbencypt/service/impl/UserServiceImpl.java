package com.pemila.aries.web.dbencypt.service.impl;

import com.pemila.aries.web.dbencypt.dao.UserDao;
import com.pemila.aries.web.dbencypt.entity.UserEntity;
import com.pemila.aries.web.dbencypt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author pemila
 * @date 2020/1/20 14:25
 **/
@Service
@Transactional(rollbackFor = RuntimeException.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	// 密文格式化
	private final static String PASSWORD_FORMAT = "{%s}%s";

	/** 定义加密方式*/
	private final static Map<Integer, String> ENCODER_TYPE = new HashMap<>();
	private final static Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();
	static {
		ENCODER_TYPE.put(0, "noop");
		ENCODER_TYPE.put(1, "bcrypt");
		ENCODER_TYPE.put(2, "pbkdf2");
		ENCODER_TYPE.put(3, "scrypt");
		ENCODER_TYPE.put(4, "sha256");
		ENCODER_MAP.put("noop", NoOpPasswordEncoder.getInstance());
		ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
		ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
		ENCODER_MAP.put("scrypt", new SCryptPasswordEncoder());
		ENCODER_MAP.put("sha256", new StandardPasswordEncoder());
	}


	@Override
	public void insertUser(UserEntity userEntity) {
		if(userDao.existsUserByUsername(userEntity.getUsername())){
			throw new RuntimeException("用户名已存在");
		}
		// 随机使用一种加密方式
		int x = new Random().nextInt(5);
		String encoderType = ENCODER_TYPE.get(x);
		PasswordEncoder encoder = ENCODER_MAP.get(encoderType);
		userEntity.setPassword(String.format(PASSWORD_FORMAT,encoderType,encoder.encode(userEntity.getPassword())));
		userDao.save(userEntity);
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
