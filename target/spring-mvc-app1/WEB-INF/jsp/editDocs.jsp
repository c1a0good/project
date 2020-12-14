<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${not empty doctor}">
        <c:set var="Specialization" value="${specialization.getName()}"/>
        <c:set var="LastName" value="${doctor.getLastName()}"/>
        <c:set var="FirstName" value="${doctor.getFirstName()}"/>
        <c:set var="MiddleName" value="${doctor.getMiddleName()}"/>
        <c:set var="Birthdate" value="${doctor.getBirthdate()}"/>
        <c:set var="EmploymentDate" value="${doctor.getEmploymentDate()}"/>
        <c:set var="SectionId" value="${doctor.getSectionId()}"/>
    </c:when>
    <c:otherwise>
        <c:set var="Specialization" value="${specialization.getName()}"/>
        <c:set var="LastName" value=""/>
        <c:set var="FirstName" value=""/>
        <c:set var="MiddleName" value=""/>
        <c:set var="Birthdate" value=""/>
        <c:set var="EmploymentDate" value=""/>
        <c:set var="SectionId" value="0"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <META http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<FORM action="saveDocs.html" method="post">
    <c:if test="${not empty doctor}">
        <INPUT type="hidden" name="id" value="${doctor.getId()}">
    </c:if>
    <P>Специализация:</P>
    <SELECT size="3" name="Specialization" value="${Specialization}">
        <OPTION selected value="${Specialization}">${Specialization}</OPTION>
        <c:forEach var="specializations" items="${Specializations}">
        <c:if test="${!specializations.getName().equals(Specialization)}">
            <OPTION value="${specializations.getName()}">${specializations.getName()}</OPTION>
        </c:if>
        </c:forEach>
    </SELECT>
    <P>Фамилия:</P>
    <INPUT type="text" name="LastName" value="${LastName}">
    <P>Имя:</P>
    <INPUT type="text" name="FirstName" value="${FirstName}">
    <P>Отчество:</P>
    <INPUT type="text" name="MiddleName" value="${MiddleName}">
    <P>Год рождения:</P>
    <INPUT type="text" name="Birthdate" value="${Birthdate}">
    <P>Год приёма на работу:</P>
    <INPUT type="text" name="EmploymentDate" value="${EmploymentDate}">
    <P>Номер отдела:</P>
    <INPUT type="text" name="SectionId" value="${SectionId}">
    <BUTTON type="submit">Сохранить</BUTTON>
    <A href="indexDocs.html?id=${specialization.getId()}">Назад</A>
</FORM>
</body>
</html>