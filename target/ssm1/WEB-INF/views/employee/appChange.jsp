<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 18-3-7
  Time: 上午11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>提出异动申请</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<table width="960" align="center"
       background="${pageContext.request.contextPath}/resources/images/bodybg.jpg">
    <tr>
        <td>
            <div align="center">
                <span>请填写异动申请</span><br>
                <form action="processApp" method="post">
                    <input type="hidden" name="attId" value="${attId}">
                    <label for="type">申请类别</label>
                    <select name="typeId" id="type">
                        <c:forEach items="${requestScope.types}" var="type">
                            <option value="${type.getId()}">${type.getTypeName()}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="reason">申请理由</label>
                    <textarea name="reason" id="reason" cols="30" rows="5"></textarea>
                    <br>
                    <input type="submit" value="提交">
                    <input type="reset" value="重新填写">
                </form>
            </div>
        </td>
    </tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>
