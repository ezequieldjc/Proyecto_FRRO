<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Data.DataSesiones" %>
<%@ page import="Entities.System.SistemaBoton" %>
<%@ page import="Data.DataBoton" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Auxiliares.FuncionesAuxiliares" %>
<%@ page import="Data.DataPersonaEmpleado" %>
<%@ page import="Entities.Persona.PersonaPerfil" %>
<%@ page import="java.util.Map" %>
<%@ page import="Entities.System.SistemaSesiones" %>
<%@ page import="Controladores.*" %><%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 29/05/2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sesiones</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <link rel="stylesheet" href="css/adminUsuarios.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <%
        out.print("<link rel=\"icon\" type=\"image/png\" href=\""+ request.getSession(true).getAttribute("icon")+"\"/>");
    %>
</head>
<body>
<%
    PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");

    new VerificarUsuario(request,response);

%>
<div class="wrapper" style="font-family: 'Andale Mono', Fallback, sans-serif; overflow-y: hidden;">
    <nav id="sidebar">
        <div class="sidebar-header">
            <div class="sidebar-header image"><a href="menu.jsp"><img src="images/avatar/<%out.print(e.getImg());%>" alt="User Image" class="usrImage"></a></div>
            <p class="nameAndRol" style="font-family: 'dindin', Fallback, sans-serif;">
                <%
                    out.println(e.getApellido() + ", " + e.getNombre() + " - " + e.getUsuario() + " -@" + e.getPerfil().getName());
                %>
            </p>

        </div>


        <%
            FuncionesAuxiliares fa = new FuncionesAuxiliares();
            ArrayList<SistemaBoton> botones = new BuscarBotones().buscarBotones(e);
            out.println("<ul class=\"list-unstyled components\">");
            for (SistemaBoton b : botones) {
                if (b.getIdPadre()==0 && b.getCollapse()){
                    out.println("<li>");
                    out.println("<a href=\"#"+b.getUrl()+"\" data-toggle=\"collapse\" aria-expanded=\"false\" class=\"dropdown-toggle\">"+b.getIcon()+"     "+b.getNombre()+"</a>");
                    out.println("<ul class=\"collapse list-unstyled\" id=\""+b.getUrl()+"\">");
                    for (SistemaBoton bb : botones){
                        if (bb.getIdPadre() == b.getId()){
                            out.println("<li><a href="+bb.getUrl()+">"+bb.getIcon()+"   "+bb.getNombre()+"</a></li>");
                        }
                    }
                    out.println("</ul>");
                    out.println("</li>");
                }
                if (!(b.getCollapse())){
                    if (b.getEnabled())
                        out.print("<li><a href=\""+b.getUrl()+"\">"+b.getIcon() + " " + b.getNombre() + "</a></li>");
                }
            }
            out.println("</ul>");
        %>
    </nav>

    <div class="content" id="contenido">
        <nav class="navbar navbar-expand-lg" >

            <button type="button" id="sidebarCollapse" class="btn btn-light">
                <i class="fa fa-align-justify"></i> <span>Correr</span>
            </button>

            <!--<a class="navbar-brand" href="#">Navbar</a> -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" style="margin-right: 10px;" hidden>
                    <a class="nav-link" href="https://www.google.com.ar/maps/">Maps</a>
                </li>
                <li class="nav-item active" style="margin-right: 10px; width: 50%;">
                    <%
                        int m = e.getNotificaciones().size();
                        if (m==0) {
                            out.print("<button type=\"button\" class=\"btn btn-success text-center text-light align-middle\" href=\"#\" data-toggle=\"modal\" data-target=\"#mimodal\" >");
                        }
                        else {
                            if (m<4) {
                                out.print("<button type=\"button\" class=\"btn btn-warning text-center text-light align-middle\" href=\"#\" data-toggle=\"modal\" data-target=\"#mimodal\" >");
                            } else {
                                out.print("<button type=\"button\" class=\"btn btn-danger text-center text-light align-middle\" href=\"#\" data-toggle=\"modal\" data-target=\"#mimodal\" >");
                            }
                        }
                    %>
                    <i class="fas fa-bell"></i> <span class="badge badge-light"> <% out.print(e.getNotificaciones().size());%> </span>
                    </button>
                </li>
                <li class="nav-item" style="margin-right: 10px;" hidden>
                    <button type="button" class="btn btn-warning text-center text-light align-middle">
                        <i class="fas fa-question"> </i> <span class="badge badge-light">0</span>
                    </button>
                </li>
            </ul>
        </nav>

        <div class="main text-center" style="overflow-y: scroll;">
            <h1 style="margin-bottom: 3%">Sesiones</h1>
            <%
                String idUsuario="";
                String inputNyA="";
                String status="";
                if (request.getParameter("idUsuario")!=null)
                    idUsuario = request.getParameter("idUsuario");

                if (request.getParameter("inputNyA")!=null)
                    inputNyA = request.getParameter("inputNyA");

                if (request.getParameter("inputStatus")!=null)
                    status = request.getParameter("inputStatus");
            %>
            <form name = "filterForm" method="post" action="sesiones.jsp" style="margin-bottom:2%" >
                <div class="row col-sm-12 justify-content-center" >
                    <div class="input-group col-sm-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="idUsuario">Usuario</label>
                        </div>
                        <select class="custom-select" id="idUsuario" name="idUsuario" >
                            <option value="" selected>all</option>>
                            <%
                                for (Map.Entry<Integer, String> en : new DataPersonaEmpleado().getAllNombresUsuarios().entrySet() ){
                                    out.print("<option value=\"" + en.getKey()+ "\">" + en.getKey() + "- " + en.getValue() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="input-group col-sm-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputNyA">Nombre</label>
                        </div>
                        <input type="text" class="form-control" placeholder="all" name="inputNyA" id="inputNyA" >
                    </div>
                    <div class="input-group col-sm-2 text-center">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputStatus">Estado</label>
                        </div>
                        <select class="custom-select" id="inputStatus" name="inputStatus">
                            <option value="" selected>all</option>
                            <option value="1">Abierta</option>
                            <option value="2">Cerrada</option>
                        </select>
                    </div>
                    <input type="submit" value="Filtrar" class="btn btn-primary" style="margin-left: 3%; ">
                </div>
            </form>

            <div class="table-wrapper-scroll-y my-custom-scrollbar" style="height: 35%">
                <table class="table">
                    <thead>
                    <tr class="thead-dark">
                        <th scope="col">#</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Nombre y Apellido</th>
                        <th scope="col">Fecha de Inicio</th>
                        <th scope="col">Fecha de Cierre</th>
                        <th scope="col">Duracion</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Invalidar</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                        int idd=1;
                        for (SistemaSesiones s : new BuscarSesiones().buscarSesiones(idUsuario,inputNyA,status)) {
                            if (s.getEmpleado().getId()==(e.getId()) || s.getEmpleado().getUsuario().equals("ADMIN")){
                                if(s.getEmpleado().getId()==(e.getId()))
                                    out.print("<tr class=\"table-success\">");
                                else
                                    out.print("<tr class=\"table-primary\">");
                            }
                            else
                                out.print("<tr>");
                            out.print("<th scope=\"row\">"+idd+"</th>");
                            out.print("<td>"+s.getEmpleado().getUsuario()+"</td>");
                            out.print("<td>"+s.getEmpleado().getNombre() +","+s.getEmpleado().getApellido() +"</td>");
                            out.print("<td>"+s.getFechaDesde()+"</td>");

                            if (s.getFechaHasta()!=null){
                                out.print("<td>"+s.getFechaHasta()+"</td>");
                                int x = (int) ((s.getFechaHasta().getTime() - s.getFechaDesde().getTime())/(60*1000));
                                out.print("<td> "+x + " minutos" +" </td>");
                            } else {
                                out.print("<td> null </td>");
                                out.print("<td> null </td>");
                            }

                            if (s.getFechaHasta()==null) {
                                out.print("<td> <span class=\"badge badge-pill badge-success\">Online</span></td>");
                                out.print("<td><button class=\"btn btn-warning\" data-toggle=\"modal\" data-target=\"#CambiarEstado"+s.getId()+"\" data-usr=\"usuario\"><i class=\"fas fa-user-times\"></i> </button></td>");
                                out.print("<div class=\"modal fade\" id=\"CambiarEstado"+s.getId()+"\">");
                                out.print("<div class=\"modal-dialog\">");
                                out.print("<div class=\"modal-content\">");
                                out.print("<div class=\"modal-header\">");
                                out.print("<h4 class=\"modal-title\">Confirmacion</h4>");
                                out.print("<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>");
                                out.print("</div>");
                                out.print("<div class=\"modal-body\">");
                                out.print("Desea cerrar la sesion del usuario "+s.getEmpleado().getUsuario()+"?");
                                out.print("</div>");
                                out.print("<div class=\"modal-footer\">");
                                out.print("<form action=\"cerrarSesion\" method=\"get\">");
                                out.print("<input type=\"hidden\" name=\"usr\" value=\""+s.getEmpleado().getUsuario()+"\">");
                                out.print("<button type=\"submit\" class=\"btn btn-warning\">Confirmar</button>");
                                out.print("</form>");
                                out.print("</div>");
                                out.print("</div>");
                                out.print("</div>");
                                out.print("</div>");
                            } else {
                                out.print("<td> <span class=\"badge badge-pill badge-danger\">Offline</span></td>");
                                out.print("<td><button class=\"btn btn-warning\" data-toggle=\"modal\" data-target=\"#CambiarEstado"+s.getId()+"\" data-usr=\"usuario\" disabled><i class=\"fas fa-user-times\"></i> </button></td>");
                            }


                        }
                    %>

                    </tbody>
                </table>
            </div>


        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
            $('#contenido').toggleClass('active');
        });
    });

</script>
</body>
</html>
