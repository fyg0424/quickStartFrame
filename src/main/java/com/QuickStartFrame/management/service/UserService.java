package com.QuickStartFrame.management.service;

import com.QuickStartFrame.management.pojo.User;

public interface UserService {
	
	public User CheckUser(User user);
	
	public User queryUserByLoginName(String loginName);

}
