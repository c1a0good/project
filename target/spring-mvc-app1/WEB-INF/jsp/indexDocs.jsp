<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <STYLE>
        TABLE {
            border-collapse: collapse;
        }
        TH, TD {
            border: 1px solid black;
            padding: 5px 30px 5px 10px;
        }
    </STYLE>
</head>
<body>
<FORM action="deleteDocs.html?SpecId=${specialization.getId()}" method="post">
    <TABLE>
        <TR>
            <TH>&nbsp;</TH>
            <TH>Специализация</TH>
            <TH>Фамилия</TH>
            <TH>Имя</TH>
            <TH>Отчество</TH>
            <TH>Год рождения</TH>
            <TH>Год приёма на работу</TH>
            <c:if test="${specialization.isNarrow()}"><TH>Номер отдела</TH></c:if>
            <TH>Заработная плата</TH>

        </TR>
        <c:forEach var="doctor" items="${doctors}">
            <TR>
                <TD>
                    <INPUT type="checkbox" name="id"
                           value="${doctor.getId()}">

                </TD>
                <TD>${doctor.getSpecialization()}</TD>
                <TD>
                    <A href="editDocs.html?id=${doctor.getId()}&SpecId=${specialization.getId()}">
                            ${doctor.getLastName()}
                    </A>
                </TD>
                <TD>${doctor.getFirstName()}</TD>
                <TD>${doctor.getMiddleName()}</TD>
                <TD>${doctor.getBirthdate()}</TD>
                <TD>${doctor.getEmploymentDate()}</TD>
                <c:if test="${specialization.isNarrow()}"><TD>${doctor.getSectionId()}</TD></c:if>
                <TD>${doctor.getSalary()}</TD>
            </TR>
        </c:forEach>
    </TABLE>
    <P>
        <A href="editDocs.html?id=-1&SpecId=${specialization.getId()}">Добавить</A>
        <BUTTON type="submit">Удалить</BUTTON>
    </P>
    <P>
        <A href="index.html">Назад</A>
    </P>
</FORM>
</body>
</html>
