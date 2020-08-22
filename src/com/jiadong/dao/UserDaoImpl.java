package com.jiadong.dao;

import com.jiadong.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{

    public UserDaoImpl() {
    }

    @Override
    public User login(Connection connection, User user) {

        try {
            QueryRunner runner = new QueryRunner();
            BeanHandler<User> handler = new BeanHandler<>(User.class);
            System.out.println(user);
            String sql = "Select userName, password from db_user where userName = ? and password = ?";
            User resultUser = runner.query(connection, sql, handler, user.getUserName(), user.getPassword());
            if (resultUser != null) {
                System.out.println("Query UserName " + resultUser.getUserName() + " Success");
            }else {
                System.out.println("UserName " + user.getUserName() + " Don't Exist");
            }

            return resultUser;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> queryAll(Connection connection) {
        try {
            QueryRunner runner = new QueryRunner();
            BeanListHandler<User> handlers = new BeanListHandler<>(User.class);
            String sql = "Select userName, password from db_user ";
            List<User> users = runner.query(connection, sql, handlers);
            System.out.println("Query All Users Data Success");
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
