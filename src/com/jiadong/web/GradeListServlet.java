package com.jiadong.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.jiadong.bean.Grade;
import com.jiadong.bean.PageBean;
import com.jiadong.dao.GradeDao;
import com.jiadong.dao.GradeDaoImpl;
import com.jiadong.util.RespUtils;
import com.jiadong.util.Utils;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class GradeListServlet extends HttpServlet {
    Connection connection = null;
    GradeDao gradeDao = new GradeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rows = req.getParameter("rows");
        String page = req.getParameter("page");
        String gradeName = req.getParameter("gradeName");
        if (gradeName == null) {
            gradeName = "";
        }
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));


        try {
            JSONObject result = new JSONObject();
//          获取数据
            connection = Utils.getConnection();
            List<Grade> grades = gradeDao.gradeList(connection, pageBean, grade);
            JSONArray jsonArray = JSONArray.fromObject(grades);
            System.out.println(jsonArray);
            int total = (int)gradeDao.gradeCount(connection, grade);
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
