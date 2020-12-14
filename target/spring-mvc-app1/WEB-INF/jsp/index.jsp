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
<FORM action="delete.html" method="post">
    <TABLE>
        <TR>
            <TH>&nbsp;</TH>
            <TH>Наименование специализации</TH>
            <TH>Является ли специальность узкой</TH>
            <TH>Количество врачей данной специальности</TH>
            <TH>Ставка заработной платы</TH>
            <TH>Сумма затрат на оплату зарплаты врачам</TH>
        </TR>
        <c:forEach var="specializations" items="${specializations}">
            <TR>
                <TD>
                    <INPUT type="checkbox" name="id"
                           value="${specializations.id}">

                </TD>
                <TD>
                    <A href="edit.html?id=${specializations.id}">
                            ${specializations.getName()}
                    </A>
                </TD>
                <TD>${specializations.isNarrow()}</TD>
                <TD>
                    <A href="indexDocs.html?id=${specializations.id}">
                        ${specializations.getAmountOfDocs()}
                </TD>
                <TD>${specializations.getWageRate()}</TD>
                <TD>${specializations.getCosts()}</TD>
            </TR>
        </c:forEach>
    </TABLE>
    <P>
        <A href="edit.html">Добавить</A>
        <BUTTON type="submit">Удалить</BUTTON>
    </P>
</FORM>
</body>
</html>
