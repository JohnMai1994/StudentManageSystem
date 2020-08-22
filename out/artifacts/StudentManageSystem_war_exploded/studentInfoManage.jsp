<%--
  Created by IntelliJ IDEA.
  User: Jiadong
  Date: 2020/8/14
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.9.7/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.9.7/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.9.7/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.9.7/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        let url;

        // 回车搜索方法
        $(document).keyup(function (e) {
            var key = e.which;
            if (key == 13) {
                searchGrade();
            }
        })

        // 搜索Student方法
        function searchStudent() {
            $('#dg').datagrid('load', {
                stuNo: $('#s_stuNo').val(),
                stuName: $('#s_stuName').val(),
                sex: $('#s_sex').val(),
                birthday: $('#s_birthday').val(),
                ebirthday: $('#s_ebirthday').val(),
                gradeId: $('#s_gradeId').val()
            })
        }

        // 删除Student方法
        function deleteStudent() {

            let selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].stuId)
            }
            var ids = strIds.join(",")
            $.messager.confirm("系统提示", "您确定要删除这<font style='color: red; '><b>" + selectedRows.length + "</b></font>条数据吗？", function (r) {
                if (r) {
                    $.post("studentDel", {delIds: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统信息", "你已经成功删除<font style='color: red; '><b>" + result.delNums + "</b></font>条数据")
                            $("#dg").datagrid("reload");

                        } else {
                            $.messager.alert("系统信息", result.errorMsg)
                        }
                    }, "json")
                }
            })
        }

        // 添加Student方法

        // 打开 添加Grade dialog 的方法
        function openStudentAddDialog() {
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增学生信息');
            url = "studentSave";
        }

        function saveStudent() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    return $(this).form("validate")
                },
                success: function (result) {
                    if (result.errorMsg) {
                        $.messager.alert("系统提示", result.errorMsg);
                        return;
                    } else {
                        $.messager.alert("系统提示", "保存成功");
                        closeStudentDialog();
                        $("#dg").datagrid("reload");
                    }
                }
            })
        }

        // 打开 编辑Student dialog 的方法
        function openStudentModifyDialog() {
            let selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return
            }

            var row = selectedRows[0];
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '改变学生信息');
            $("#stuNo").val(row.stuNo);
            $("#stuName").val(row.stuName)
            $("#sex").val(row.sex);
            $("#birthday").datebox("setValue",row.birthday);
            $("#gradeId").combobox("setValue", row.gradeId);
            $("#email").val(row.email)
            $("#stuDesc").val(row.stuDesc)

            url = "studentSave?stuId=" + row.stuId;
        }


        function closeStudentDialog() {
            $("#dlg").dialog("close");
            resetValue();

        }

        function resetValue() {
            $("#stuNo").val("");
            $("#stuName").val("")
            $("#sex").val("");
            $("#birthday").datebox("setValue","");
            $("#gradeId").combobox("setValue", "");
            $("#email").val("")
            $("#stuDesc").val("")
        }

    </script>


</head>
<body style="margin: 5px">
<table id="dg" title="学生信息" class="easyui-datagrid"
       url="studentList"
       pagination="true"
       rownumbers="true" fitColumns="true" fit="true" toolbar="#toolbar">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="stuId" width="50" align="center">编号</th>
        <th field="stuNo" width="100" align="center">学生号码</th>
        <th field="stuName" width="100" align="center">学生名字</th>
        <th field="sex" width="100" align="center">性别</th>
        <th field="birthday" width="100" align="center">生日</th>
        <th field="gradeName" width="100" align="center">班级名称</th>
        <th field="email" width="150">邮箱</th>
        <th field="stuDesc" width="250" align="center">学生描述</th>
    </tr>
    </thead>

</table>

<div id="toolbar">
    <div style="">
        <a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
           style="width:15%" plain="true">添加</a>
        <a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"
           style="width:15%" plain="true">修改</a>
        <a href="javascript:deleteStudent()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
           style="width:15%" plain="true">删除</a>
    </div>
    <div>
        &nbsp;学号：
        <input type="text" name="s_stuNo" id="s_stuNo" style="width: 10%;margin: 5px">

        &nbsp;姓名：
        <input type="text" name="s_stuName" id="s_stuName" style="width: 10%; margin: 5px">

        所属班级：
        <input class="easyui-combobox" id="s_gradeId" name="s_gradeId" style="width: 20%;height: 25px;"
               data-options="editable:false, valueField:'gradeId', textField:'gradeName', url:'gradeComboList'"/>

        <br>
        &nbsp; 性别：
        <select class="easyui-combobox"  id="s_sex" name="s_sex" editable="false" style="width: 20%; height: 25px">
            <option value="">请选择...</option>
            <option value="男">男</option>
            <option value="女">女</option>
        </select>

        &nbsp;出生日期：
        <input class="easyui-datebox" name="s_birthday" id="s_birthday" width="10" editable="false" style="height: 25px"> -> <input
            class="easyui-datebox" name="s_ebirthday" id="s_ebirthday" width="10" editable="false" style="height: 25px">

        <a id="research" href="javascript:searchStudent()" class="easyui-linkbutton"
           data-options="iconCls:'icon-search'"
           style="width:15%" plain="true">搜索</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog" style="width:400px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post">
        <div style="margin:10px; " align="center">
            <label for="stuNo">学生号码：</label>
            <input id="stuNo" name="stuNo" style="width:70%" required>
        </div>

        <div style="margin:10px; " align="center">
            <label for="stuName">学生姓名：</label>
            <input id="stuName" name="stuName" style="width:70%" required>
        </div>

        <div style="margin:10px; " align="center">
            <label for="sex">学生性别：</label>
            <select id="sex" name="sex" style="width: 70%" required>
                <option value="">请选择...</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>

        <div style="margin:10px; " align="center">
            <label for="birthday">生日日期：</label>
            <input class="easyui-datebox" name="birthday" id="birthday" width="10" style="width: 70%" editable="false" required>
        </div>

        <div style="margin:10px; " align="center">
            <label for="gradeId">所属班级：</label>
            <input class="easyui-combobox" id="gradeId" name="gradeId" style="width: 70%"
                   data-options="editable:false, valueField:'gradeId', textField:'gradeName', url:'gradeComboList'" required/>
        </div>

        <div style="margin:10px; " align="center">
            <label for="email">学生邮箱：</label>
            <input id="email" name="email" style="width:70%">
        </div>

        <div style="margin:10px;" align="center">
            <label for="stuDesc">学生描述：</label>
            <textarea id="stuDesc" name="stuDesc" rows="3" style="vertical-align: top; width: 70%"> </textarea>
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveStudent()" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


</body>
</html>
