<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: iviczian
  Date: 2020. 09. 01.
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<h1>Employees</h1>

<ul>
<c:forEach var="employee" items="${employees}">
    <li>
    ${employee.name}
    </li>
</c:forEach>
</ul>

</body>
</html>
