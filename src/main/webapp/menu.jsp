<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Data.DataSesiones" %>
<%@ page import="Entities.System.SistemaBoton" %>
<%@ page import="Data.DataBoton" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Auxiliares.FuncionesAuxiliares" %>
<%@ page import="Entities.System.SistemaAccion" %>
<%@ page import="Entities.Notificaciones.Notificacion" %>
<%@ page import="UI.DrawSidebar" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="Controladores.*" %>
<%@ page import="Entities.System.SistemaGlobalConfig" %><%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 24/05/2020
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <%

        SistemaGlobalConfig g = new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("ICON"));
        if (g.getValorAtributo()!=null){
            session.setAttribute("icon", g.getValorAtributo());
            out.print("<link rel=\"icon\" type=\"image/png\" href=\""+g.getValorAtributo()+"\"/>");
        }

    %>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Menu</title>
    <!-- Bootstrap CSS --><link rel="stylesheet" href="dashio/calendar.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <link rel="stylesheet" href="css/adminUsuarios.css">
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

</head>
<body>
<%
    PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
    if (e == null) {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    } else {
        if (!(new DataSesiones().tieneSesionAbierta(e))) {
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request,response);
            //request.getRequestDispatcher("login.jsp").forward(request,response);

        } else
            e.setNotificaciones(new BuscarNotificaciones().getAllByUsuario(e));
    }

%>
<div class="wrapper" style="font-family: 'Andale Mono', Fallback, sans-serif; overflow-y: hidden;">
    <nav class="navbar-collapse" id="sidebar" style="overflow-y: visible;" >
        <div class="sidebar-header">

            <div class="sidebar-header image"><a href="menu.jsp"><img src="images/avatar/<%out.print(e.getImg());%>" alt="User Image" class="usrImage"></a></div>
            <p class="nameAndRol" style="font-family: 'dindin', Fallback, sans-serif;">
                <%
                    out.println(e.getApellido() + ", " + e.getNombre() + " - " + e.getUsuario() + " -@" + e.getPerfil().getName());
                %>
            </p>

        </div>
        <div style="overflow-y: hidden;">
            <%
                FuncionesAuxiliares fa = new FuncionesAuxiliares();
                ArrayList<SistemaBoton> botones = new BuscarBotones().buscarBotones(e);
                out.println("<ul class=\"list-unstyled components\" style=\"overflow-y: visible;\">");
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
        </div>
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
            <div class="collapse navbar-collapse" id="navbarNav">
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
            </div>
        </nav>

        <div class="main" style="overflow-y: scroll;">
            <%
                String destino = (String) session.getAttribute("paginaNoPermitida");
                if (destino != null) { %>
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>Pagina no permitida!</strong> No tienes acceso a <%out.print(destino);%>><br>
                Se ha enviado una solicitud a Sistemas. Deseas enviarles un email? Hace click <a href="#" class="alert-link">   aqui</a>.

                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%      session.removeAttribute("paginaNoPermitida");
                }
            %>

            <%
                String accion = (String) session.getAttribute("accionNoPermitida");
                if (accion != null) { %>
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>Acicon no permitida!</strong> No tienes permiso para <%out.print(accion);%><br>
                Se ha enviado una solicitud a Sistemas. Deseas enviarles un email? Hace click <a href="#" class="alert-link">aqui</a>.

                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%      session.removeAttribute("accionNoPermitida");
            }
            %>

            <%
                String errorUsuarios = (String) session.getAttribute("NOnuevoUsuarioCreadoCorrectamente");
                if (errorUsuarios != null) { %>
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>No se ha podido crear el usuario!</strong> <br>
                Se ha enviado una solicitud a Sistemas. Deseas enviarles un email? Hace click <a href="#" class="alert-link">aqui</a>.

                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%      session.removeAttribute("NOnuevoUsuarioCreadoCorrectamente");
            }
            %>

            <%
                PersonaEmpleado nuevoUsuario = (PersonaEmpleado) session.getAttribute("nuevoUsuarioCreadoCorrectamente");
                if (nuevoUsuario != null) { %>
            <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                <strong>Usuario creado Correctamente!</strong> Se ha creado al usuario <%out.print(nuevoUsuario.getUsuario());%><br>
                Su contrasena es <%out.print(new HashearPassword().unHidePwd(nuevoUsuario.getPass()));%>. <br>
                Su nuevo email es <%out.print(nuevoUsuario.getEmail());%>.<br>
                El usuario debera cambiar su contrasena en el primer inicio de sesion.

                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%      session.removeAttribute("nuevoUsuarioCreadoCorrectamente");
            }
            %>

            <%
                String usuario = (String) session.getAttribute("usuarioNoEncontrado");
                if (usuario != null) { %>
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>Usuario no encontrado!</strong> No se ha encontrado al usuario <%out.print(usuario);%><br>
                Se ha enviado un aviso a Sistemas. Deseas, igualmente, enviarles un email? Hace click <a href="#" class="alert-link">aqui</a>.

                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%      session.removeAttribute("usuarioNoEncontrado");
            }
            %>



        </div>
        <div class="modal fade" id="mimodal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content" >
                    <div class="modal-header">
                        <h4 class="modal-title text-center">Notificaciones sin leer - (<%out.print(e.getNotificaciones().size());%>)</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body table-wrapper-scroll-y my-custom-scrollbar" >
                        <div class="">
                            <table class="table table-wrapper-scroll-y my-custom-scrollbar">
                                <thead>
                                <tr class="thead-dark">
                                    <th scope="col">ID</th>
                                    <th scope="col" colspan="2">Usuario</th>
                                    <!--<th scope="col">Usuario</th>-->
                                    <th scope="col">Mensaje</th>
                                    <th scope="col">Tiempo</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    int idd=1;
                                    for (Notificacion n : e.getNotificaciones()){
                                        switch (n.getPrioridad()){
                                            case 1:
                                                out.print("<tr class=\"clickable-row bg-success\" data-href=\"notificaciones.jsp?id=" + n.getId() + "\" >");
                                                break;
                                            case 2:
                                                out.print("<tr class=\"clickable-row bg-warning\" data-href=\"notificaciones.jsp?id=" + n.getId() + "\" >");
                                                break;
                                            default:
                                                out.print("<tr class=\"clickable-row bg-danger\" data-href=\"notificaciones.jsp?id=" + n.getId() + "\" >");
                                                break;
                                        }
                                        out.print("<th scope=\"row\">"+idd+"</th>");
                                        if (n.getResponsable().getImg()!=null)
                                            out.print("<td class=\"w-25\"><img src=\"images/avatar/"+n.getResponsable().getImg()+".png\" alt=\"usuario pepe\" class=\"img-fluid\" style=\"width: 50%\"> </td>");
                                        else
                                            out.print("<td class=\"w-25\"><img src=\"images/avatar/system.png\" alt=\"usuario pepe\" class=\"img-fluid\" style=\"width: 50%\"> </td>");
                                        if(n.getResponsable().getUsuario()!=null)
                                            out.print("<td style=\"vertical-align: center\">"+ n.getResponsable().getUsuario() + "</td>");
                                        else
                                            out.print("<td>Sistema</td>");
                                        out.print("<td>"+ n.getMensaje() + "</td>");
                                        out.print("<td>"+ n.getTimeAgo() + " min ago</td>");
                                        idd+=1;
                                    }
                                %>
                                </tbody>
                            </table>
                            <a href="notificaciones.jsp" class="align-items-center">Ver todas! </a>
                        </div>

                    </div>
                </div>
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
<script>
jQuery(document).ready(function($) {
$(".clickable-row").click(function() {
window.location = $(this).data("href");
});
});
</script>
</body>
</html>
