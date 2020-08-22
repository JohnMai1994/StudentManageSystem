package com.jiadong.web;

import com.jiadong.bean.Grade;
import com.jiadong.bean.Student;
import com.jiadong.dao.GradeDao;
import com.jiadong.dao.GradeDaoImpl;
import com.jiadong.dao.StudentDao;
import com.jiadong.dao.StudentDaoImpl;
import com.jiadong.util.RespUtils;
import com.jiadong.util.Utils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

public class StudentSaveServlet extends HttpServlet {
    Connection connection = null;
    StudentDao studentDao = new StudentDaoImpl();
    Student student = new Student();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stuId = req.getParameter("stuId");
        String stuNo = req.getParameter("stuNo");
        String stuName = req.getParameter("stuName");
        String sex = req.getParameter("sex");
        String gradeId = req.getParameter("gradeId");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String stuDesc = req.getParameter("stuDesc");

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
        if (email == null) {
            email = "";
        }
        if (stuDesc == null) {
            stuDesc = "";
        }
        if (gradeId == null){
            gradeId = "";
        }

        student.setStuNo(stuNo);
        student.setStuName(stuName);
        student.setSex(sex);
        student.setBirthday(Date.valueOf(birthday));
        student.setEmail(email);
        student.setStuDesc(stuDesc);
        if (!gradeId.isEmpty()) {
            student.setGradeId(Integer.parseInt(gradeId));
        }

        if (stuId != null) {
            student.setStuId(Integer.parseInt(stuId));
        }

        try {
            connection = Utils.getConnection();
            JSONObject result = new JSONObject();
            int saveNums = 0;


//          获取数据
            if (!(stuId == null)) {
                saveNums = (int)studentDao.studentModify(connection, student);
            } else {
                saveNums = (int)studentDao.studentAdd(connection, student);
            }


//          建立返回浏览页数据
            if (saveNums > 0) {
                result.put("success", true);
            } else {
                result.put("success", true);
                result.put("errorMsg", "删除失败");
            }
            RespUtils.write(resp, result);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            Utils.closeConnection(connection, null, null);
        }


    }
}
