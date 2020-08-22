package com.jiadong.dao;

import com.alibaba.druid.support.json.JSONUtils;
import com.jiadong.bean.Grade;
import com.jiadong.bean.PageBean;
import com.jiadong.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GradeDaoImpl implements GradeDao{
    @Override
    public List<Grade> gradeList(Connection connection, PageBean pageBean, Grade grade) {
        try {
            QueryRunner runner = new QueryRunner();
            BeanListHandler<Grade> handlers = new BeanListHandler<>(Grade.class);
            StringBuffer sql = new StringBuffer("select * from db_grade");
            if (grade != null && !grade.getGradeName().isEmpty()){
                sql.append(" and gradeName like '%" + grade.getGradeName() + "%'");
            }

            if (pageBean!= null) {
                sql.append(" limit " + pageBean.getStart()  +","  + pageBean.getRows());
            }

            List<Grade> resultGrades = runner.query(connection, sql.toString().replaceFirst("and", "where"), handlers);
            return resultGrades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long gradeCount(Connection connection, Grade grade) {
        try {
            QueryRunner runner = new QueryRunner();
            ScalarHandler handler = new ScalarHandler();
            StringBuffer sql = new StringBuffer("Select count(*) from db_grade");
            if (!grade.getGradeName().isEmpty()) {
                sql.append(" and gradeName like '%" + grade.getGradeName() + "%'");
            }
            long count = (long) runner.query(connection, sql.toString().replaceFirst("and", "where"), handler );
            return count;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int gradeDelete(Connection connection, String delIds) {

        try {
            QueryRunner runner = new QueryRunner();
            String sql = "delete from db_grade where gradeId in(" + delIds + ")";
            System.out.println(sql);
            int delCount = runner.update(connection, sql);
            return delCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int gradeAdd(Connection connection, Grade grade) {
        try {
            QueryRunner runner = new QueryRunner();
            String sql = "insert into db_grade values (null, ?, ?)";
            int addCount = runner.update(connection, sql, grade.getGradeName(), grade.getGradeDesc());
            return addCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int gradeModify(Connection connection, Grade grade) {
        try {
            QueryRunner runner = new QueryRunner();
            String sql = "update db_grade set gradeName = ?, gradeDesc = ? where gradeId = ?";
            int modifyCount = runner.update(connection, sql, grade.getGradeName(), grade.getGradeDesc(), grade.getGradeId());
            return modifyCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
