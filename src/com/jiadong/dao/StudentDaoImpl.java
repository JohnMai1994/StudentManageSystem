package com.jiadong.dao;

import com.jiadong.bean.Student;
import com.jiadong.bean.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements  StudentDao{
    @Override
    public List<Map<String , Object>> studentList(Connection connection, PageBean pageBean, Student student, String birthday, String ebirthday) {
        try {
            QueryRunner runner = new QueryRunner();
            StringBuffer sql = new StringBuffer("SELECT * FROM db_student s, db_grade g WHERE s.gradeId=g.gradeId");

            if (student!= null && !student.getStuNo().isEmpty()) {
                sql.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
            }

            if (student!= null && !student.getStuName().isEmpty()) {
                sql.append(" and s.stuName like '%" + student.getStuName() + "%'");
            }

            if (student!= null && !student.getSex().isEmpty()) {
                sql.append(" and s.sex like '%" + student.getSex() + "%'");
            }

            if (student!= null && student.getGradeId() != -1) {
                sql.append(" and s.gradeId = " + student.getGradeId());
            }

            if (!birthday.isEmpty()) {
                sql.append(" and TO_DAYS(s.birthday) >= TO_DAYS('" + birthday + "')" );
            }

            if (!ebirthday.isEmpty()) {
                sql.append(" and TO_DAYS(s.birthday) <= TO_DAYS('" + ebirthday + "')" );
            }




            if (pageBean!= null) {
                sql.append(" limit " + pageBean.getStart()  +","  + pageBean.getRows());
            }
            List<Map<String , Object>> resultStudents = runner.query(connection, sql.toString(), new MapListHandler());
            return resultStudents;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long studentCount(Connection connection,  Student student, String birthday, String ebirthday) {
        try {
            QueryRunner runner = new QueryRunner();
            ScalarHandler handler = new ScalarHandler();
            StringBuffer sql = new StringBuffer("Select count(*) from db_student s, db_grade g where s.gradeId=g.gradeId");

            if (student!= null && !student.getStuNo().isEmpty()) {
                sql.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
            }

            if (student!= null && !student.getStuName().isEmpty()) {
                sql.append(" and s.stuName like '%" + student.getStuName() + "%'");
            }

            if (student!= null && !student.getSex().isEmpty()) {
                sql.append(" and s.sex like '%" + student.getSex() + "%'");
            }

            if (student!= null && student.getGradeId() != -1) {
                sql.append(" and s.gradeId = " + student.getGradeId());
            }

            if (!birthday.isEmpty()) {
                sql.append(" and TO_DAYS(s.birthday) >= TO_DAYS('" + birthday + "')" );
            }

            if (!ebirthday.isEmpty()) {
                sql.append(" and TO_DAYS(s.birthday) <= TO_DAYS('" + ebirthday + "')" );
            }


            long count = (long) runner.query(connection, sql.toString(), handler );
            return count;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int studentDelete(Connection connection, String delIds) {

        try {
            QueryRunner runner = new QueryRunner();
            String sql = "delete from db_student where stuId in(" + delIds + ")";
            System.out.println(sql);
            int delCount = runner.update(connection, sql);
            return delCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int studentAdd(Connection connection, Student student) {
        try {
            QueryRunner runner = new QueryRunner();
            String sql = "insert into db_student values (null, ?, ?, ?, ?, ?, ?, ?)";
            int addCount = runner.update(connection, sql, student.getStuNo(), student.getStuName(), student.getSex(), student.getBirthday(), student.getGradeId(), student.getEmail(), student.getStuDesc());
            return addCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int studentModify(Connection connection, Student student) {
        try {
            QueryRunner runner = new QueryRunner();
            String sql = "update db_student set stuNo = ?, stuName = ?, sex = ?, birthday = ?, gradeId = ?, email = ?, stuDesc = ? where stuId = ?";
            int modifyCount = runner.update(connection, sql, student.getStuNo(), student.getStuName(), student.getSex(), student.getBirthday(), student.getGradeId(), student.getEmail(), student.getStuDesc(), student.getStuId());
            return modifyCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
