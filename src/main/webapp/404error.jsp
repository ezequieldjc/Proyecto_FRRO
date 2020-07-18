<%@ page import="Entities.Notificaciones.Notificacion" %>
<%@ page import="Entities.Notificaciones.NotificacionCategoria" %>
<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Controladores.GrabarNotificacion" %><%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 23/05/2020
  Time: 23:58
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>404 Error!!</title>
    <%
        out.print("<link rel=\"icon\" type=\"image/png\" href=\""+ request.getSession(true).getAttribute("icon")+"\"/>");
    %>
</head>

<body>
Esta es una pagina de error dea
<!-- Bootstrap core JavaScript -->
<%
    PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
    if (e != null) {
        out.print("<a href=\"404error\" method=\"get\">Dar aviso del error</a>");
        out.print("<br>");
    }
%>
<a href="login.jsp">Login</a>
<%
    if (e != null) {
        out.print("<a href=\"menu.jsp\">Menu</a>");
        out.print("<br>");
    }
%>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

</body>


</html>