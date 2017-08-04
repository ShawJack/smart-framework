<%--
  Created by IntelliJ IDEA.
  User: ithink
  Date: 2017-6-19
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑客户</title>
</head>
<body>

<form action="/demo/customer_edit" method="post">
    <table>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="name" value="${requestScope.customer.name}"/></td>
        </tr>
        <tr>
            <td>联系人</td>
            <td><input type="text" name="contact" value="${requestScope.customer.contact}"/></td>
        </tr>
        <tr>
            <td>电话号码</td>
            <td><input type="text" name="telephone" value="${requestScope.customer.telephone}"/></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email" value="${requestScope.customer.email}"/></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input type="text" name="remark" value="${requestScope.customer.remark}"/></td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${requestScope.customer.id}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>


</body>
</html>
