package com.jiadong;

import com.jiadong.bean.Grade;
import com.jiadong.bean.PageBean;
import com.jiadong.bean.User;
import com.jiadong.dao.StudentDao;
import com.jiadong.dao.StudentDaoImpl;
import com.jiadong.dao.UserDao;
import com.jiadong.dao.UserDaoImpl;
import com.jiadong.util.Utils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
//        UserDao userDao = new UserDaoImpl();
//        User user = new User();
//        user.setUserName("JessicaLi");
//        user.setPassword("654321");
//        Connection con = Utils.getConnection();
//
//
//        User currentUser = userDao.login(con, user);
//        System.out.println(currentUser);
//
//        Utils.closeConnection(con, null,null);

        Connection con = Utils.getConnection();

        PageBean pageBean = new PageBean(1,10);
        StudentDao studentDao = new StudentDaoImpl();


//        -------------------------------------------------------------------------
//        List<Map<String, Object>> maps = studentDao.studentList(con, pageBean);

//        JSONArray jsonArray = new JSONArray();
//        for (Map<String, Object> map : maps) {
//            JSONObject jsonObject = new JSONObject();
//            for (String key: map.keySet()){
//                jsonObject.put(key, map.get(key).toString());
//            }
//            jsonArray.add(jsonObject);
//        }
//        System.out.println(jsonArray);

//        JSONObject jsonObject = JSONArray.fromObject();






//        for (Map<String, Object> map : maps) {
//            JSONObject jsonObject2 = JSONObject.fromObject(map);
//            System.out.println(jsonObject2);
//        }

    }

}
