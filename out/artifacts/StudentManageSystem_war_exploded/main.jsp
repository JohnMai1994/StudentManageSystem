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
    <title>学生信息管理系统主界面</title>

<%--    <%--%>
<%--        // 权限验证--%>
<%--        if (session.getAttribute("currentUser") == null) {--%>
<%--            System.out.println("你居然想偷偷过来后台");--%>
<%--            response.sendRedirect("index.jsp");--%>
<%--            return;--%>
<%--        }--%>

<%--    %>--%>

    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.9.7/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.9.7/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.9.7/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.9.7/locale/easyui-lang-zh_CN.js"></script>
</head>

<script type="text/javascript">
    $(function () {
       // 数据
       var treeDate = [{
           text:"根",
           children:[{
               text:"班级信息管理",
               attributes:{
                   url:"gradeInfoManage.jsp"
               }
           },{
               text:"学生信息管理",
               attributes:{
                   url:"studentInfoManage.jsp"
               }
           }]

       }]
        // 实例化树菜单
        $('#tree').tree({
            data:treeDate,
            lines:true,
            onClick:function(node) {
                if (node.attributes) {
                    openTab(node.text, node.attributes.url);
                }
            }
        })


        // 新增Tab
        function openTab(text, url) {
           var tabs = $('#tabs');

           if (tabs.tabs('exists', text)){
               tabs.tabs('select', text);
           } else {
               var content="<iframe frameborder='0' scrolling='auto' style='width: 100%;height: 100%' src=" + url + "></iframe>"
               tabs.tabs("add",{
                   title: text,
                   closable: true,
                   content: content
               });
           }
        }



    });
</script>

<body class="easyui-layout">
<%--头部--%>
<div data-options="region:'north'" style="height:100px;background-color: #e2ecff;" >
    <div align="left" style="width: 80%;float: left">
        <img src="image/logo.png">
    </div>

    <div style="padding-top: 60px; padding-right: 20px">
        当前用户：<font style="color: red">${currentUser.userName}</font>
    </div>
</div>


<%--目录--%>
<div data-options="region:'west',split:true" title="导航菜单" style="width:200px;" >
    <ul id="tree"></ul>
</div>


<%--主要内容--%>
<div data-options="region:'center',title:'主页面',iconCls:'icon-ok'">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页">
            <div align="center" style="padding-top: 100px; font-size: 50px; color: red"> 欢迎使用</div>
        </div>
    </div>
</div>

<%--尾部--%>
<div data-options="region:'south',split:true" style="height:50px;" align="center"> 版权所有<a
        href="https://www.jiadong.work">www.jiadong.work</a></div>

</body>
</html>
