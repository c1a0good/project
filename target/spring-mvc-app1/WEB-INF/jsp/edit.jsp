<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${not empty specialization}">
        <c:set var="Name" value="${specialization.getName()}"/>
        <c:if test="${specialization.isNarrow() != \"\"}">
            <c:set var="Narrow" value="checked"/>
        </c:if>
        <c:set var="WageRate" value="${specialization.getWageRate()}"/>
    </c:when>
    <c:otherwise>
        <c:set var="Name" value=""/>
        <c:set var="Narrow" value=""/>
        <c:set var="WageRate" value="100"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <META http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
    <FORM action="save.html" method="post">
        <c:if test="${not empty specialization}">
            <INPUT type="hidden" name="id" value="${specialization.getId()}">
            </c:if>
            <P>Наименование специализации:</P>
        <INPUT type="text" name="Name" value="${Name}">
            <P><INPUT type="checkbox" name="Narrow" ${Narrow}>Является ли узкой специальность</P>
            <P>Ставка заработной платы:</P>
        <INPUT type="text" name="WageRate" value="${WageRate}">
            <BUTTON type="submit">Сохранить</BUTTON>
            <A href="index.html">Назад</A>
    </FORM>
</body>
</html>
