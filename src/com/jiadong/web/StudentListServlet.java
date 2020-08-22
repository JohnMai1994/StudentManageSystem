package com.jiadong.web;

import com.jiadong.bean.Grade;
import com.jiadong.bean.PageBean;
import com.jiadong.bean.Student;
import com.jiadong.dao.GradeDao;
import com.jiadong.dao.GradeDaoImpl;
import com.jiadong.dao.StudentDao;
import com.jiadong.dao.StudentDaoImpl;
import com.jiadong.util.JSONUtils;
import com.jiadong.util.RespUtils;
import com.jiadong.util.Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class StudentListServlet extends HttpServlet {
    Connection connection = null;
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String rows = req.getParameter("rows");
        String page = req.getParameter("page");

        String stuNo = req.getParameter("stuNo");
        String stuName = req.getParameter("stuName");
        String sex = req.getParameter("sex");
        String gradeId = req.getParameter("gradeId");

        String birthday = req.getParameter("birthday");
        String ebirthday = req.getParameter("ebirthday");
        /*-------------------------------*/
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Student student = new Student();
        /*-------------------------------*/
        if (stuNo == null) {
            stuNo = "";
        }
        if (stuName == null) {
            stuName = "";
        }
        if (sex == null){
            sex = "";
        }
        if (gradeId == null) {
            gradeId = "";
        }
        if (birthday == null) {
            birthday = "";
        }
        if (ebirthday == null) {
            ebirthday = "";
        }

        student.setStuNo(stuNo);
        student.setStuName(stuName);
        student.setSex(sex);
        if (!gradeId.isEmpty()) {
            student.setGradeId(Integer.parseInt(gradeId));
        }

        try {
            JSONObject result = new JSONObject();
//          获取数据
            connection = Utils.getConnection();

            List<Map<String, Object>> mapList = studentDao.studentList(connection, pageBean, student, birthday, ebirthday);
            JSONArray jsonArray = JSONUtils.fromMapList(mapList);
            int total = (int)studentDao.studentCount(connection, student, birthday, ebirthday);
//          建立返回浏览页数据
            result.put("rows", jsonArray);
            result.put("total", total);
            RespUtils.write(resp, result);


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            Utils.closeConnection(connection, null, null);
        }


    }
}
