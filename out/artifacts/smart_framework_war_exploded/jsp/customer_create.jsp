<%--
  Created by IntelliJ IDEA.
  User: ithink
  Date: 2017-6-19
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户创建</title>
</head>
<body>

<form action="/demo/customer_create" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>联系人</td>
            <td><input type="text" name="contact"/></td>
        </tr>
        <tr>
            <td>电话号码</td>
            <td><input type="text" name="telephone"/></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input type="text" name="remark"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"/></td>
            <td><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>

</body>
</html>
