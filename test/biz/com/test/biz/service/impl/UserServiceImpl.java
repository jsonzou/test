package com.test.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.biz.entity.User;
import com.test.biz.mapper.UserMapper;
import com.test.biz.service.IUserService;
import com.test.core.baseservice.BaseService;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService {

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return baseDao.getMapper(UserMapper.class).list();
	}

}
