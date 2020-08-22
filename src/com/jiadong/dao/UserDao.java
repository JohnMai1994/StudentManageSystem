package com.jiadong.dao;

import com.jiadong.bean.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {

    /*
    * 登录验证
    * */

    User login(Connection connection, User user);
    List<User> queryAll (Connection connection);


}
