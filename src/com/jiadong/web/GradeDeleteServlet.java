package com.jiadong.web;

import com.jiadong.bean.Grade;
import com.jiadong.bean.PageBean;
import com.jiadong.dao.GradeDao;
import com.jiadong.dao.GradeDaoImpl;
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
import java.util.List;

public class GradeDeleteServlet extends HttpServlet {
    Connection connection = null;
    GradeDao gradeDao = new GradeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String delIds = req.getParameter("delIds");

        try {
            JSONObject result = new JSONObject();
//          获取数据
            connection = Utils.getConnection();
            int delNums = (int)gradeDao.gradeDelete(connection, delIds);
//          建立返回浏览页数据
            if (delNums > 0) {
                result.put("success", true);
                result.put("delNums", delNums);
            } else {
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
