package com.jstudio.dao;

import com.jstudio.model.User;

public interface UserDao {

	User findByUserName(String username);

}
