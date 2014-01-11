package com.biz.test.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.test.entity.User;
import com.biz.test.mapper.UserMapper;
import com.biz.test.service.IUserService;
import com.mwp.core.baseservice.BaseService;

@Service("userService")
public class UserService extends BaseService implements IUserService {

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return baseDao.getMapper(UserMapper.class).list();
	}

}
