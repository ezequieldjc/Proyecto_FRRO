<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Entities.System.SistemaBoton" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Auxiliares.FuncionesAuxiliares" %>
<%@ page import="Entities.Persona.PersonaPerfil" %>
<%@ page import="Controladores.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="Auxiliares.ComparaBotones" %>
<%@ page import="Entities.System.SistemaAccion" %>
<%@ page import="Data.*" %>
<%@ page import="Auxiliares.ComparaAcciones" %><%--
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

    <title>Perfiles</title>
    <%
        out.print("<link rel=\"icon\" type=\"image/png\" href=\""+ request.getSession(true).getAttribute("icon")+"\"/>");
    %>
</head>
<body>
<%
    PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
    new VerificarUsuario(request,response);

    int idPerfil = Integer.parseInt(request.getParameter("id")) ;
    PersonaPerfil p = new DataPerfil().getOneByID(new PersonaPerfil(idPerfil));

    boolean bloquearAtributos = (session.getAttribute("bloquearAtributos") == null) || (boolean) session.getAttribute("bloquearAtributos");
    session.removeAttribute("bloquearAtributos");

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
        <div class="main text-center justify-content-center" style="overflow-y: scroll;">
            <h1 style="margin-bottom: 2%">Perfil: <%out.print(p.getId() + " - " + p.getName());%></h1>
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
                <div class="col-12">
                    <form action="actualizarPerfil" method="post">
                        <div class="row justify-content-center center-block" style="text-align: center;" >
                            <div class="input-group col-2">
                                        <div class="input-group-prepend">
                                            <label class="input-group-text" for="inputCodigo" >Codigo</label>
                                        </div>
                                        <input type="text" class="form-control" value="<%out.print(p.getCodigo());%>" name="inputCodigo" id="inputCodigo" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                            </div>
                            <div class="input-group col-2">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputNombre">Nombre</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getName());%>" name="inputNombre" id="inputNombre" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                            </div>
                            <div class="input-group col-4">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputDesc">Desc</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getDesc());%>" name="inputDesc" id="inputDesc" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                            </div>
                        </div>
                        <div class="row justify-content-center center-block" style="text-align: center; margin-top: 3%" >
                            <div class="input-group col-sm-2">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputOwner">Owner</label>
                                </div>
                                <select class="custom-select" id="inputOwner" name="inputOwner" <%out.print(bloquearAtributos ? "disabled" : "");%> >
                                    <option value="0" selected>null</option>>
                                    <%
                                        for (Map.Entry<Integer, String> en : new DataPersonaEmpleado().getAllNombresUsuarios().entrySet() ){
                                            if (en.getValue().equals(p.getOwner().getUsuario()))
                                                out.print("<option value=\"" + en.getKey() + "\" selected>" + en.getKey() + "- " + en.getValue() + "</option>");
                                            else
                                                out.print("<option value=\"" + en.getKey() + "\">" + en.getKey() + "- " + en.getValue() + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="input-group col-sm-2">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputBackup">Owner</label>
                                </div>
                                <select class="custom-select" id="inputBackup" name="inputBackup" <%out.print(bloquearAtributos ? "disabled" : "");%>>
                                    <option value="0" selected>null</option>>
                                    <%

                                        for (Map.Entry<Integer, String> en : new DataPersonaEmpleado().getAllNombresUsuarios().entrySet() ){
                                            if (en.getValue().equals(p.getBackup().getUsuario()))
                                                out.print("<option value=\"" + en.getKey() + "\" selected>" + en.getKey() + "- " + en.getValue() + "</option>");
                                            else
                                                out.print("<option value=\"" + en.getKey() + "\">" + en.getKey() + "- " + en.getValue() + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="row justify-content-around center-block" style="text-align: center; margin-top: 3%;">
                            <div class="justify-content-around center-block">
                                <h3 style="margin-top: 2%">Botones Permitidos</h3>
                                <a data-toggle="collapse" href="#BotonesPermitidos" role="button" aria-expanded="false" aria-controls="multiCollapseExample1" style="margin-bottom: 1%">Expandir Tabla</a>
                                <br>
                                <table class="table table-wrapper-scroll-y my-custom-scrollbar collapse multi-collapse col-12" id="BotonesPermitidos" style="margin-top: 1%; width: 140%; margin-right: 20%; height: 70%;">
                                    <thead>
                                    <tr class="thead-dark">
                                        <th scope="col">#</th>
                                        <th scope="col">Codigo</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">URL</th>
                                        <th scope="col">Menu?</th>
                                        <th scope="col">Permitido</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        int idd=1;

                                        for (SistemaBoton b : new BuscarBotones().getAll()){
                                            if (b.getIdPadre()==0){
                                                if (b.getCollapse()){
                                                    out.print("<tr class=\"table-secondary\">");
                                                } else {
                                                    out.print("<tr class=\"table-info\">");
                                                }
                                            } else {
                                                out.print("<tr>");
                                            }
                                            out.print("<th scope=\"row\">"+idd+"</th>");
                                            out.print("<td style=\"text-align:left\">" + b.getIcon() + "  " +b.getCodigo()+"</td>");
                                            out.print("<td>"+b.getNombre()+"</td>");
                                            out.print("<td>"+b.getUrl()+"</td>");
                                            if (b.getEnabled())
                                                out.print("<td>Si</td>");
                                            else
                                                out.print("<td>No</td>");
                                            if (bloquearAtributos) {
                                                if(new ComparaBotones().contiene(new DataBoton().getAllByPerfil(p), b))
                                                    out.print("<td><input type=\"checkbox\" name=\"botones\" value=\""+b.getId()+"\" checked  disabled ></td>");
                                                else
                                                    out.print("<td><input type=\"checkbox\" name=\"botones\" value=\""+b.getId()+"\" disabled></td>");
                                            } else {
                                                if(new ComparaBotones().contiene(new DataBoton().getAllByPerfil(p), b))
                                                    out.print("<td><input type=\"checkbox\" name=\"botones\" value=\""+b.getId()+"\" checked></td>");
                                                else
                                                    out.print("<td><input type=\"checkbox\" name=\"botones\" value=\""+b.getId()+"\"></td>");
                                            }


                                            idd+=1;
                                        }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="row justify-content-around center-block" style="text-align: center; margin-top: 3%;">
                            <div class="justify-content-around center-block">
                                <h3 style="margin-top: 2%">Acciones Permitidas</h3>
                                <a data-toggle="collapse" href="#AccionesPermitidas" role="button" aria-expanded="false" aria-controls="multiCollapseExample1" style="margin-bottom: 1%">Expandir Tabla</a>
                                <br>
                                <table class="table table-wrapper-scroll-y my-custom-scrollbar collapse multi-collapse col-12" id="AccionesPermitidas" style="margin-top: 1%; width: 140%; margin-right: 20%; height: 70%;">
                                    <thead>
                                    <tr class="thead-dark">
                                        <th scope="col">#</th>
                                        <th scope="col">Codigo</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Descripcion</th>
                                        <th scope="col">Permitido</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        idd=1;
                                        PersonaEmpleado em = new PersonaEmpleado();
                                        em.setPerfil(p);
                                        for (SistemaAccion a : new BuscarAcciones().getAll()){
                                            out.print("<tr>");
                                            out.print("<th scope=\"row\">"+a.getId()+"</th>");

                                            out.print("<td style=\"text-align:left\">" + a.getCodigo() +"</td>");

                                            out.print("<td>"+a.getNombre()+"</td>");

                                            out.print("<td>"+a.getDesc()+ "</td>");

                                            if (bloquearAtributos) {

                                                if (new ComparaAcciones().contiene(new BuscarAcciones().buscarAcciones(em), a))
                                                    out.print("<td><input type=\"checkbox\" name=\"acciones\" value=\""+a.getId()+"\" checked  disabled ></td>");
                                                else
                                                    out.print("<td><input type=\"checkbox\" name=\"acciones\" value=\""+a.getId()+"\" disabled></td>");
                                            } else {
                                                if (new ComparaAcciones().contiene(new BuscarAcciones().buscarAcciones(em), a))
                                                    out.print("<td><input type=\"checkbox\" name=\"acciones\" value=\""+a.getId()+"\" checked  ></td>");
                                                else
                                                    out.print("<td><input type=\"checkbox\" name=\"acciones\" value=\""+a.getId()+"\" ></td>");
                                            }
                                        }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                        <div class="d-flex justify-content-end" style="margin-right: 10%; margin-top: 3%">
                            <%session.setAttribute("idPerfil", p.getId()+"");%>
                            <button class="btn btn-danger" type="button" style="margin-right: 2%" <%out.print(bloquearAtributos ? "" : "disabled");%> ><a href="desbloquearAtributosPerfil" methods="post" <%out.print(bloquearAtributos ? "disabled" : "");%>><i class="fas fa-unlock"></i> Desbloquear campos!</a></button>
                            <button class="btn btn-success" type="submit" <%out.print(bloquearAtributos ? "disabled" : "");%>><i class="fas fa-save"></i> Guardar</button>
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