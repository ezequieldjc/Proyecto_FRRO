<%@ page import="Entities.Notificaciones.Notificacion" %>
<%@ page import="Entities.Notificaciones.NotificacionCategoria" %>
<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Controladores.GrabarNotificacion" %>
<%@ page isErrorPage="true" %>
<%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 23/05/2020
  Time: 23:58
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>

<html>
<head>
    <title>404 Error!!</title>

</head>

<body style="background: #cbd3da">
<h1 class="text-center lnr-text-align-center">404 Error!</h1>
404 - Esta es una pagina de Error!
<!-- Bootstrap core JavaScript -->
<br>

<%
    PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
    if (e==null)
        out.print("<a href=\"login.jsp\">Go to Login</a>");
    else
        out.print("<a href=\"menu.jsp\">Go to Menu</a>");

%>



<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>

</body>


</html>