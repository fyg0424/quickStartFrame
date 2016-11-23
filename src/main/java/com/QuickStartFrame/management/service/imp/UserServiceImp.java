package com.QuickStartFrame.management.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuickStartFrame.management.dao.UserMapper;
import com.QuickStartFrame.management.pojo.User;
import com.QuickStartFrame.management.pojo.UserExample;
import com.QuickStartFrame.management.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User CheckUser(User user) {
		//intellJ提交代码
		UserExample uexample = new UserExample();
		uexample.createCriteria().andLoginnameEqualTo(user.getLoginname())
		.andPasswordEqualTo(user.getPassword());
		List<User> users = userMapper.selectByExample(uexample);
		if(users.size() > 0 && !users.isEmpty()){
			return users.get(0);
		}
		return new User();
		
	}

	@Override
	public User queryUserByLoginName(String loginName) {
		UserExample uExample = new UserExample();
		uExample.createCriteria().andLoginnameEqualTo(loginName);
		List<User> users = userMapper.selectByExample(uExample);
		User user = null;
		if(users.size() > 0 && !users.isEmpty()){
			user = users.get(0);
		}
		return user;
	}

}
