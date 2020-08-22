<%--
  Created by IntelliJ IDEA.
  User: Jiadong
  Date: 2020/8/13
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>学生信息管理系统登录界面</title>
    <script>
      function resetValue() {
        document.getElementById("userName").value = "";
        document.getElementById("password").value = "";
      }
    </script>

  </head>
  <body>
    <div align="center" style="padding-top: 100px">
      <form action="login" method="post">
      <table align="center" style="width: 1050px; height: 765px" background="image/login.jpg" border="0px">
        <tr style="height:550px">
          <td width="35%"></td>
          <td width="10%"></td>
          <td ></td>
          <td width="30%"></td>
        </tr>

        <tr style="height: 10px">
          <td width="35%"></td>
          <td width="10%"><label for="userName">用户名：</label> </td>
          <td ><input type="text" value="" name="userName" id="userName"/></td>
          <td width="30%"></td>
        </tr>
        <tr style="height: 10px">
          <td width="35%"></td>
          <td width="10%"><label for="password">密码：</label></td>
          <td ><input type="password" value="" name="password" id="password"/></td>
          <td width="30%"></td>
        </tr>
        <tr style="height: 20px">
          <td width="35%"></td>
          <td width="10%"><input type="submit" value="登录"></td>
          <td ><input type="reset" value="重置" onclick="resetValue()"></td>
          <td width="30%"></td>
        </tr>

        <tr style="height: 10%">
          <td width="35%"></td>
          <td colspan="3" style="color: red;">${error}
          </td>
        </tr>

        <tr >
          <td width="35%"></td>
          <td width="10%"></td>
          <td ></td>
          <td width="30%"></td>
        </tr>

      </table>
      </form>
    </div>


  </body>
</html>
