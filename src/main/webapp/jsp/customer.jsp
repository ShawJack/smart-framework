<%--
  Created by IntelliJ IDEA.
  User: ithink
  Date: 2017-6-18
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户列表</title>
</head>
<body>

    <table border="1">
        <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th>备注</th>
            <th>修改操作</th>
            <th>删除操作</th>
        </tr>
        <c:forEach items="${requestScope.customerList}" var="customer">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.contact}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td>${customer.remark}</td>
                <td><a href="/demo/customer_edit?id=${customer.id}">修改</a></td>
                <td><a href="/demo/customer_delete?id=${customer.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
