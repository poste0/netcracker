<%@page isErrorPage="true" %>
<%@page contentType="charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="styles/errorPage.css"/>
    </head>
    <body>
        <div id="error">Ooops.</div>
        <div id="message">
                <%=(request.getParameter("message") != null) ? "Division by zero." : "Some error. Please contact admin."%>
        </div>
        <form method="post">
            <button name="back">
                back
            </button>
            <%
                if (request.getParameter("back") != null) {
            %>
            <jsp:forward page="datasource.jsp"/>
            <%
                }
            %>
        </form>
    </body>
</html>
