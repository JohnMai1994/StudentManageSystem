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
    <title>班级信息管理</title>
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

        // 搜索Grade方法
        function searchGrade() {
            $('#dg').datagrid('load', {
                gradeName: $('#s_greadeName').val()
            })

        }

        // 删除Grade方法
        function deleteGrade() {

            let selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return
            }
            var strIds = [];

            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].gradeId)
            }

            var ids = strIds.join(",")
            $.messager.confirm("系统提示", "您确定要删除这<font style='color: red; '><b>" + selectedRows.length + "</b></font>条数据吗？", function (r) {
                if (r) {
                    $.post("gradeDel", {delIds: ids}, function (result) {
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

        // 打开 添加Grade dialog 的方法
        function openGradeAddDialog() {
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增班级信息');
            url = "gradeSave";
        }

        function saveGrade() {
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
                        closeGradeDialog();
                        $("#dg").datagrid("reload");
                    }
                }
            })
        }

        // 打开 编辑Grade dialog 的方法
        function openGradeModifyDialog() {
            let selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return
            }

            var row = selectedRows[0];
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '改变班级信息');
            $("#gradeName").val(row.gradeName);
            $("#gradeDesc").val(row.gradeDesc);

            url = "gradeSave?gradeId=" + row.gradeId;
        }


        function closeGradeDialog() {
            $("#dlg").dialog("close");
            resetValue();

        }

        function resetValue() {
            $("#gradeName").val("");
            $("#gradeDesc").val("")
        }




    </script>
</head>
<body style="margin: 5px">
<table id="dg" title="班级信息" class="easyui-datagrid"
       url="gradeList"
       pagination="true"
       rownumbers="true" fitColumns="true" fit="true" toolbar="#toolbar">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="gradeId" width="50">编号</th>
        <th field="gradeName" width="100">班级名称</th>
        <th field="gradeDesc" width="250">班级描述</th>
    </tr>
    </thead>

</table>

<div id="toolbar">
    <div style="">
        <a href="javascript:openGradeAddDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
           style="width:15%" plain="true">添加</a>
        <a href="javascript:openGradeModifyDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"
           style="width:15%" plain="true">修改</a>
        <a href="javascript:deleteGrade()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
           style="width:15%" plain="true">删除</a>
    </div>
    <div>
        &nbsp;班级名称：
        <input type="text" name="s_gradeName" id="s_greadeName">
        <a id="research" href="javascript:searchGrade()" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width:15%" plain="true">搜索</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog" style="width:400px"
     data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
    <form id="fm" method="post">
        <div style="margin-bottom:10px; " align="center">
            <label for="gradeName">班级名称：</label>
            <input id="gradeName" name="gradeName" style="width:70%" required>
        </div>
        <div style="margin-bottom:10px;" align="center">
            <label for="gradeDesc">班级描述：</label>
            <textarea id="gradeDesc" name="gradeDesc" rows="5" style="vertical-align: top; width: 70%"> </textarea>
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveGrade()" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:closeGradeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


</body>
</html>
