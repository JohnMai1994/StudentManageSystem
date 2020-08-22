package com.jiadong.web;

import com.jiadong.bean.Grade;
import com.jiadong.dao.GradeDao;
import com.jiadong.dao.GradeDaoImpl;
import com.jiadong.util.RespUtils;
import com.jiadong.util.Utils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class GradeSaveServlet extends HttpServlet {
    Connection connection = null;
    GradeDao gradeDao = new GradeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String gradeName = req.getParameter("gradeName");
        System.out.println("gradeName" + gradeName);
        String gradeDesc = req.getParameter("gradeDesc");
        System.out.println("gradeDesc" + gradeDesc);
        String gradeId = req.getParameter("gradeId");
        System.out.println("gradeId " + gradeId);
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        grade.setGradeDesc(gradeDesc);

        if (!(gradeId == null)) {
            grade.setGradeId(Integer.parseInt(gradeId));
        }


        try {
            connection = Utils.getConnection();
            JSONObject result = new JSONObject();
            int saveNums = 0;

            System.out.println(grade);

//          获取数据
            if (!(gradeId == null)) {
                saveNums = (int)gradeDao.gradeModify(connection, grade);
            } else {
                saveNums = (int)gradeDao.gradeAdd(connection, grade);
                System.out.println("我添加成功了" + saveNums);
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
