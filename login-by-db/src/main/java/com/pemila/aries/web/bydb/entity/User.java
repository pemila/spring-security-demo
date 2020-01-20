package com.pemila.aries.web.bydb.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author pemila
 * @date 2020/1/20 10:53
 **/
@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 账号*/
	private String userName;
	/** 密码*/
	private String password;
	/** 昵称*/
	private String nickName;
}
