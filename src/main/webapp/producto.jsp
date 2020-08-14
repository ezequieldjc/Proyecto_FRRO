<%@ page import="Entities.Persona.PersonaEmpleado" %>
<%@ page import="Data.DataSesiones" %>
<%@ page import="Entities.System.SistemaBoton" %>
<%@ page import="Data.DataBoton" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Auxiliares.FuncionesAuxiliares" %>
<%@ page import="Data.DataPersonaEmpleado" %>
<%@ page import="Entities.Persona.PersonaPerfil" %>
<%@ page import="Controladores.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="Controladores.Producto.ProductoHandler" %>
<%@ page import="Entities.Productos.Producto" %>
<%@ page import="Entities.Productos.ProductoPrecio" %>
<%@ page import="Controladores.Producto.ProductoPrecioHandler" %>
<%@ page import="Entities.Productos.ProductoStock" %>
<%@ page import="Controladores.Producto.ProductoStockHandler" %><%--
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

    int idProd = Integer.valueOf(request.getParameter("prod"));
    Producto p = new ProductoHandler().getOneByID(new Producto(idProd));

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
            <h1 style="margin-bottom: 2%">Producto: <%out.print(p.getId() + " - " + p.getCodigo());%></h1>

            <div class="row text-center justify-content-center">
                <div class="col-lg-11 col-11" style="margin-left: 3%">
                        <div class="row justify-content-center center-block" style="text-align: center;margin-top: 3%;" >
                            <div class="input-group col-4">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="codProd">Codigo</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getCodigo());%>" name="codProd" id="codProd" disabled>
                            </div>
                            <div class="input-group col-4">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="nombreProd">Nombre</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getNombre());%>" name="nombreProd" id="nombreProd" disabled>
                            </div>
                            <div class="input-group col-4">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="catProd" >Categoria</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getProductoCategoria().getNombre());%>" name="catProd" id="catProd" disabled>
                            </div>
                        </div>
                        <div class="row justify-content-center center-block" style="text-align: center;margin-top: 3%;">
                            <div class="input-group col-8">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="descProd">Descripcion</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getDesc());%>" name="descProd" id="descProd" disabled>
                            </div>
                            <div class="input-group col-2 justify-content-center">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputEstado">Estado</label>
                                </div>
                                <%
                                    if (p.getEstado())
                                        out.print("<span class=\"badge badge-success  d-flex align-items-center\" id=\"inputEstado\">Hab</span>");
                                    else
                                        out.print("<span class=\"badge badge-danger  d-flex align-items-center\" id=\"inputEstado\">Desh</span>");
                                %>
                            </div>
                        </div>
                        <div class="row justify-content-center center-block" style="text-align: center;margin-top: 3%;">
                            <div class="input-group col-5">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputProveedor">Proveedor</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getProductoProveedor().getNombre());%>" name="inputProveedor" id="inputProveedor" disabled>
                            </div>
                            <div class="input-group col-5">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputFabricante">Fabricante</label>
                                </div>
                                <input type="text" class="form-control" value="<%out.print(p.getProductoFabricante().getNombre());%>" name="inputFabricante" id="inputFabricante" disabled>
                            </div>

                        </div>
                    <div class="row justify-content-center center-block" style="text-align: center;margin-top: 3%;">
                        <div class="justify-content-around center-block">
                            <h3 style="margin-top: 2%">Listas de Precio</h3>
                            <a data-toggle="collapse" href="#ListaPrecio" role="button" aria-expanded="false" aria-controls="multiCollapseExample1" style="margin-bottom: 1%">Ver Precios</a>
                            <br>
                            <table class="table table-wrapper-scroll-y my-custom-scrollbar collapse multi-collapse col-12" id="ListaPrecio" style="margin-top: 1%; width: 140%; margin-right: 20%; height: 70%;">
                                <thead>
                                <tr class="thead-dark">
                                    <th scope="col">#</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Fecha desde</th>
                                    <th scope="col">Fecha hasta</th>
                                    <th scope="col">Valor</th>
                                    <th scope="col">Maximo Descuento</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (ProductoPrecio pp : new ProductoPrecioHandler().getAllByProducto(p)){
                                        if (pp.getFechaHasta()!=null)
                                            out.print("<tr class=\"table-secondary\">");
                                        else
                                            out.print("<tr class=\"table-light\">");

                                        out.print("<th scope=\"row\">"+pp.getId()+"</th>");
                                        out.print("<td style=\"text-align:left\">" +pp.getNombre()+"</td>");
                                        out.print("<td>"+pp.getFechaDesde()+"</td>");
                                        if (pp.getFechaHasta()==null)
                                            out.print("<td>-</td>");
                                        else
                                            out.print("<td>"+pp.getFechaHasta()+"</td>");
                                        out.print("<td>"+pp.getValor()+"</td>");
                                        out.print("<td>"+pp.getMaxDscto()+"</td>");

                                    }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row justify-content-center center-block" style="text-align: center;margin-top: 3%;">
                        <div class="justify-content-around center-block">
                            <h3 style="margin-top: 2%">Stock</h3>
                            <a data-toggle="collapse" href="#stock" role="button" aria-expanded="false" aria-controls="multiCollapseExample1" style="margin-bottom: 1%">Ver Precios</a>
                            <br>
                            <table class="table table-wrapper-scroll-y my-custom-scrollbar collapse multi-collapse col-12" id="stock" style="margin-top: 1%; width: 140%; margin-right: 20%; height: 70%;">
                                <thead>
                                <tr class="thead-dark">
                                    <th scope="col">#</th>
                                    <th scope="col">Deposito</th>
                                    <th scope="col">Stock Minimo</th>
                                    <th scope="col">Stock Actual</th>
                                    <th scope="col">Stock Maximo</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (ProductoStock ps : new ProductoStockHandler().getAllByProducto(p)){
                                        if (ps.estaOK(ps))
                                            out.print("<tr class=\"table-light\">");
                                        else
                                            out.print("<tr class=\"table-warning\">");

                                        out.print("<th scope=\"row\">"+ps.getId()+"</th>");
                                        out.print("<td style=\"text-align:left\">" +ps.getIdDeposito()+"</td>");
                                        out.print("<td>"+ps.getStockMinimo()+"</td>");
                                        out.print("<td>"+ps.getStockActual()+"</td>");
                                        out.print("<td>"+ps.getStockMaximo()+"</td>");

                                    }%>
                                </tbody>
                            </table>
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
</body>
</html>