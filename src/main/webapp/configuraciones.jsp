<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Data.DataSesiones" %>
<%@ page import="Entities.System.SistemaBoton" %>
<%@ page import="Data.DataBoton" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Auxiliares.FuncionesAuxiliares" %>
<%@ page import="Data.DataPersonaEmpleado" %>
<%@ page import="Entities.Persona.PersonaPerfil" %>
<%@ page import="Data.DataPerfil" %>
<%@ page import="java.util.Map" %>
<%@ page import="Controladores.*" %>
<%@ page import="Entities.System.SistemaGlobalConfig" %>
<%@ page import="Entities.System.SistemaModulo" %>
<%@ page errorPage="/404error.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 04/06/2020
  Time: 21:42
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

    <title>Configs</title>
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

        <div class="main text-center" style="overflow-y: scroll;">
            <h1 style="margin-bottom: 3%">Configuraciones del Sistema</h1>
            <%
                String inputCodModulo="";
                String inputCodigo="";
                String inputNombreParametro="";

                String inputValorAtributo="";


                if (request.getParameter("inputIDModulo")!=null)
                    inputCodModulo = request.getParameter("inputIDModulo");
                if (request.getParameter("inputCodigo")!=null)
                    inputCodigo = request.getParameter("inputCodigo");
                if (request.getParameter("inputNombreParametro")!=null)
                    inputNombreParametro = request.getParameter("inputNombreParametro");
                if (request.getParameter("inputValorAtributo")!=null)
                    inputValorAtributo = request.getParameter("inputValorAtributo");

            %>
            <form name = "filterForm" method="get" action="configuraciones.jsp" style="margin-bottom:2%" >
                <div class="row col-sm-12 justify-content-center" >
                    <div class="input-group col-sm-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputIDModulo">Modulo</label>
                        </div>
                        <select class="custom-select" id="inputIDModulo" name="inputIDModulo" >
                            <option value="" selected>all</option>>
                            <%
                                for (Map.Entry<Integer, String> en : new BuscarModulos().getAllCodigos().entrySet() ){
                                    out.print("<option value=\"" + en.getValue()+ "\">" + en.getKey() + "- " + en.getValue() + "</option>");
                                }
                            %>
                        </select>
                    </div>

                    <div class="input-group col-sm-2 text-center" style="margin-left: 3%;">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputCodigo">Codigo</label>
                        </div>
                        <input type="text" class="form-control" placeholder="all" name="inputCodigo" id="inputCodigo" >
                    </div>

                    <div class="input-group col-sm-2 text-center" style="margin-left: 3%;">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputNombreParametro">Parametro</label>
                        </div>
                        <input type="text" class="form-control" placeholder="all" name="inputNombreParametro" id="inputNombreParametro" >
                    </div>

                    <div class="input-group col-sm-2 text-center" style="margin-left: 3%;">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputValorAtributo">Atributo</label>
                        </div>
                        <input type="text" class="form-control" placeholder="all" name="inputValorAtributo" id="inputValorAtributo" >
                    </div>

                    <input type="submit" value="Filtrar" class="btn btn-primary" style="margin-left: 3%; ">
                </div>
            </form>

            <div class="table-wrapper-scroll-y my-custom-scrollbar" style="height: 35%">
                <table class="table">
                    <thead>
                    <tr class="thead-dark">
                        <th scope="col">ID</th>
                        <th scope="col">ID - Modulo</th>
                        <th scope="col">Codigo</th>
                        <th scope="col">Parametro</th>
                        <th scope="col">Nombre Atributo</th>
                        <th scope="col">Valor Atributo</th>
                        <th scope="col">Accion</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        SistemaGlobalConfig g = new SistemaGlobalConfig();
                        g.setModulo(new SistemaModulo(inputCodModulo));
                        g.setCodigo(inputCodigo);
                        g.setNombreParametro(inputNombreParametro);
                        g.setValorAtributo(inputValorAtributo);
                        for (SistemaGlobalConfig p : new BuscarGlobalConfig().buscarGlobalConfigByFilter(g)){
                            out.print("<tr>");
                            out.print("<th scope=\"row\">"+p.getId()+"</th>");
                            out.print("<td>"+p.getModulo().getId() + " - "+ p.getModulo().getNombre() +"</td>");
                            out.print("<td>"+p.getCodigo() +"</td>");
                            out.print("<td>"+p.getNumeroParametro() + " - "+ p.getNombreParametro()+"</td>");
                            out.print("<td>"+p.getNombreAtributo() +"</td>");
                            out.print("<td>"+p.getValorAtributo() +"</td>");

                            out.print("<form name=\"usr\" method=\"get\" action=\"adminModificarConfig.jsp\">");
                            out.print("<input type=\"hidden\" name=\"usr\" value=\""+p.getId()+"\">");
                            out.print("<td><button class=\"btn btn-primary\"><i class=\"fas fa-user-edit\"></i> </button></td>");
                            out.print("</form>");
                        }%>
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