package com.jiadong.web;

import com.jiadong.bean.User;
import com.jiadong.dao.UserDao;
import com.jiadong.dao.UserDaoImpl;
import com.jiadong.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {
    Connection con = null;
    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (userName.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "用户名or密码是空白！");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }

        user.setUserName(userName);
        user.setPassword(password);

        try {
            con = Utils.getConnection();
            User currentUser = userDao.login(con, user);

            if (currentUser == null) {
                req.setAttribute("error", "用户名or密码是错误！");
                // 服务器跳转
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            } else {
                // 获取Session (!!! 很重要的代码)
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", currentUser);

                // 客户端跳转
                resp.sendRedirect("main.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Utils.closeConnection(con, null,null);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
