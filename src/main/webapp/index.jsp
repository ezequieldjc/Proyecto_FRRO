<%@ page import="Controladores.BuscarGlobalConfig" %>
<%@ page import="Entities.Persona.PersonaPersona" %>
<%@ page import="Data.DataPersonaEmpleado" %>
<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Entities.Persona.PersonaPerfil" %>
<html>
<title>Bienvenido jd</title>
<body>
<h2>Hello World!</h2>
<%
    for (int x =0 ; x<10; x++){
        out.print("hola mundo<br>");
    }
%>

    <a href="login.jsp">Logueate rey!</a>

    <%
        for (PersonaEmpleado p : new DataPersonaEmpleado().getAll()){
            out.print(p.getApellido() + " <br>");
        }
    %>


</body>
</html>
