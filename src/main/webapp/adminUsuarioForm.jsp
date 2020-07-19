<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Data.DataSesiones" %>
<%@ page import="Entities.System.SistemaBoton" %>
<%@ page import="Data.DataBoton" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Auxiliares.FuncionesAuxiliares" %>
<%@ page import="Data.DataPersonaEmpleado" %>
<%@ page import="Entities.Persona.PersonaPerfil" %>
<%@ page import="Controladores.*" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 05/06/2020
  Time: 00:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <link rel="stylesheet" href="css/adminUsuarios.css">
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">

    <title>Usuario</title>
    <%
        out.print("<link rel=\"icon\" type=\"image/png\" href=\""+ request.getSession(true).getAttribute("icon")+"\"/>");
    %>
</head>
<body>
<%
    PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
    new VerificarUsuario(request,response);

    String usr = request.getParameter("usr");
    PersonaEmpleado u = (usr == null) ? e :  new BuscarEmpleado().buscarEmpleadoByUsuario(new PersonaEmpleado(usr));

    boolean bloquearAtributos = (session.getAttribute("bloquearAtributos") == null) || (boolean) session.getAttribute("bloquearAtributos");
    session.removeAttribute("bloquearAtributos");

%>
<div class="wrapper" style="font-family: 'Andale Mono', Fallback, sans-serif; overflow-y: hidden;">
    <nav id="sidebar">
        <div class="sidebar-header">
            <div class="sidebar-header image"><a href="menu.jsp"><img src="images/avatar/<%out.print(e.getImg());%>.png" alt="User Image" class="usrImage"></a></div>
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
        <div class="main text-center justify-content-center" style="overflow-y: scroll;">
            <h1 style="margin-bottom: 2%">Usuario: <%out.print(u.getApellido() + ", " + u.getNombre());%></h1>
            <%
                String error = (String) session.getAttribute("ABMIncorrecta");
                if (error != null) { %>
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>Usuario no encontrado!</strong> <%out.print(error);%><br>
                Se ha enviado un aviso a Sistemas. Deseas, igualmente, enviarles un email? Hace click <a href="#" class="alert-link">aqui</a>.

                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%      session.removeAttribute("ABMIncorrecta");
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


            <div class="row">
                <div class="col-2">
                    <div class="col-12 justify-content-center" style="margin-top: 10%;" >
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="images/avatar/<%out.print(u.getImg());%>.png"  alt="Third slide">
                                </div>
                                <% for (String png : new BuscarNombresAvatar().buscarPNGNames()) {
                                    out.print("<div class=\"carousel-item\">");
                                    out.print("<a href=\"cambiarImagen?img="+png+"&usr="+u.getUsuario()+"\"><img class=\"d-block w-100\" src=\"images/avatar/"+png+"\" alt=\"img"+png+".png\"></a>");
                                    out.print("</div>");
                                }
                                %>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                        <a style="color: #6c757d;" aria-disabled="true" href="#">Ingresa tu imagen personalizada!</a>
                    </div>

                </div>
                <div class="col-1"></div>
                <div class="col-9">
                    <form action="actualizarUsuario" method="post">
                        <div class="row justify-content-center center-block" style="text-align: center;" >
                            <div class="input-group col-3">
                                        <div class="input-group-prepend">
                                            <label class="input-group-text" for="inputUser">Usuario</label>
                                        </div>
                                        <input type="text" class="form-control" value="<%out.print(u.getUsuario());%>" name="inputUser" id="inputUser" disabled>
                                </div>
                            <div class="input-group col-3">
                                <div class="input-group-prepend">
                                            <label class="input-group-text" for="inputPerfil">Perfil</label>
                                        </div>
                                <select class="custom-select" id="inputPerfil" name="inputPerfil" <%out.print(bloquearAtributos ? "disabled" : "");%>>

                                            <%
                                                for (Map.Entry<Integer, String> en : new BuscarPerfiles().getHashID().entrySet() ){
                                                    if (en.getKey() == u.getPerfil().getId())
                                                        out.print("<option value=\"" + en.getKey()+ "\" selected>" + en.getKey() + "- " + en.getValue() + "</option>");
                                                    else
                                                        out.print("<option value=\"" + en.getKey()+ "\">" + en.getKey() + "- " + en.getValue() + "</option>");
                                                }
                                            %>
                                        </select>
                            </div>
                            <div class="input-group col-4">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputFNacimiento">Fecha de Nacimiento</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(u.getFechaNacimiento());%>" name="inputFNacimiento" id="inputFNacimiento" disabled>
                            </div>
                            <div class="input-group col-2 justify-content-center">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputEstado">Estado</label>
                                </div>
                                <%
                                    if (u.getEstado())
                                        out.print("<span class=\"badge badge-success  d-flex align-items-center\" id=\"inputEstado\">Hab</span>");
                                    else
                                        out.print("<span class=\"badge badge-danger  d-flex align-items-center\" id=\"inputEstado\">Desh</span>");
                                %>
                            </div>
                        </div>
                        <div class="row justify-content-center center-block" style="text-align: center; margin-top: 3%;">
                                <div class="input-group col-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" for="inputApellido">Apellido</label>
                                    </div>
                                    <input type="text" class="form-control" value="<%out.print(u.getApellido());%>" name="inputApellido" id="inputApellido" disabled>
                                </div>
                                <div class="input-group col-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" for="inputNombre">Nombre</label>
                                    </div>
                                    <input type="text" class="form-control" value="<%out.print(u.getNombre());%>" name="inputNombre" id="inputNombre" disabled>
                                </div>
                                <div class="input-group col-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" for="inputTipoDoc">Tipo Doc</label>
                                    </div>
                                    <input type="text" class="form-control" value="<%out.print(u.getDoc().getId() + " - " + u.getDoc().getCodigo());%>" name="inputTipoDoc" id="inputTipoDoc" disabled>
                                </div>
                                <div class="input-group col-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" for="inputNroDoc">Nro Doc</label>
                                    </div>
                                    <input type="text" class="form-control" value="<%out.print(u.getDoc().getNumero());%>" name="inputNroDoc" id="inputNroDoc" disabled>
                                </div>
                        </div>
                        <div class="row justify-content-center center-block" style="text-align: center; margin-top: 3%;">
                            <div class="input-group col-5">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputEmail">Email</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(u.getEmail());%>" name="inputEmail" id="inputEmail" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                            </div>
                            <div class="input-group col-4">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputCelular">Celular</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(u.getCelular());%>" name="inputCelular" id="inputCelular" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                            </div>
                            <div class="input-group col-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputTelefono">Telefono</label>
                                </div>
                                <input type="text" class="form-control" name="inputTelefono" id="inputTelefono" value="<%out.print(u.getTelefono());%>" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                            </div>
                        </div>

                        <div class="d-flex justify-content-end" style="margin-right: 10%; margin-top: 3%">
                            <%request.setAttribute("usr", u.getUsuario());
                            session.setAttribute("usr", u.getUsuario());%>
                            <button class="btn btn-danger" type="button" style="margin-right: 2%" <%out.print(bloquearAtributos ? "" : "disabled");%> ><a href="desbloquearAtributosUsuario" methods="post" <%out.print(bloquearAtributos ? "disabled" : "");%>><i class="fas fa-unlock"></i> Desbloquear campos!</a></button>
                            <button class="btn btn-success" type="submit" <%out.print(bloquearAtributos ? "disabled" : "");%>><i class="fas fa-save"></i> Guardar</button>
                            <input type="text" class="form-control" value="<%out.print(u.getId());%>" name="inputID" id="inputID" hidden>
                            <input type="text" class="form-control" value="<%out.print(u.getUsuario());%>" name="inputUser" id="inputUser" hidden>
                            <input type="text" class="form-control" value="<%out.print(e.getUsuario());%>" name="usrusr" id="usrusr" hidden>
                            <input type="text" class="form-control" value="<%out.print(e.getId());%>" name="usrID" id="usrID" hidden>

                        </div>

                    </form>
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
</body>
</html>