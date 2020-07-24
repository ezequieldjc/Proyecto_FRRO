<%@ page import="Controladores.BuscarGlobalConfig" %>
<%@ page import="Controladores.BuscarGlobalConfig" %>
<%@ page import="Entities.System.SistemaGlobalConfig" %>
<%@ page errorPage="/404error.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: ezequieldjemdjemian
  Date: 24/05/2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%

        SistemaGlobalConfig g = new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("ICON"));
        if (g.getValorAtributo()!=null){
            out.print("<link rel=\"icon\" type=\"image/png\" href=\""+g.getValorAtributo()+"\"/>");
        }
    %>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/icon-font.min.css">
    <link rel="stylesheet" type="text/css" href="css/animate.css">
    <link rel="stylesheet" type="text/css" href="css/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="css/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <%
        HttpSession sesion = request.getSession(true);
        if (sesion.getAttribute("empleado")!=null){
            request.getRequestDispatcher("menu.jsp").forward(request,response);
        }
    %>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <div class="login100-form-title" style="background-image: url(images/bg-01.jpg);">
					<span class="login100-form-title-1">
						Sign In
					</span>
                </div>

                <form class="login100-form validate-form" action="login" method="post">
                    <div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
                        <span class="label-input100">Username</span>
                        <input class="input100" type="text" name="username" placeholder="@@#####">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
                        <span class="label-input100">Password</span>
                        <input class="input100" type="password" name="p4ssw0rd" placeholder="*******">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="flex-sb-m w-full p-b-30">
                        <div class="contact100-form-checkbox">
                            <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                            <label class="label-checkbox100" for="ckb1">
                                Recordarme
                            </label>
                        </div>

                        <div>
                            <a href="#" class="txt1">
                                Olvido su pass?
                            </a>
                        </div>
                    </div>
                    <%if (sesion.getAttribute("datosIncorrectos")!=null) { %>
                    <div class="datosEquivocados text-center">
                        Datos Incorrectos!
                    </div>
                    <% sesion.removeAttribute("datosIncorrectos");
                    } %>

                    <% if (sesion.getAttribute("usuarioInhabilitado")!=null) {%>
                    <div class="datosEquivocados text-center">
                        Usuario inhabilitado!
                    </div>
                    <% sesion.removeAttribute("usuarioInhabilitado") ;
                    }%>

                    <% if (sesion.getAttribute("tieneSesionAbierta")!=null) {%>
                    <div class="datosEquivocados text-center">
                        Sesion iniciada. Cierre su sesion desde <a class="datosEquivocados hiper" href="#" data-toggle="modal" data-target="#mimodal">aqui</a>.
                    </div>
                    <% sesion.removeAttribute("tieneSesionAbierta") ;
                    }%>
                    <% if (sesion.getAttribute("sesionLimpiada")!=null) {%>
                    <div class="datosLimpiados text-center">
                        Sesion finalizada con exito. Ingrese nuevamente.
                    </div>
                    <% sesion.removeAttribute("sesionLimpiada") ;
                    }%>

                    <div class="container-login100-form-btn">
                        <button class="btn btn-success" style="width: 40%;">
                            Login
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade col-sm-12" id="mimodal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Limpiar Sesion</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">Identifiquese</div>
                                <div class="card-body">
                                    <form action="../java/servlet/LimpiarSesion.java" method="post">
                                        <div class="form-group row">
                                            <label for="email_address" class="col-md-4 col-form-label text-md-right">Username</label>
                                            <div class="col-md-6">
                                                <input type="text" id="email_address" class="form-control" name="username" required autofocus>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                            <div class="col-md-6">
                                                <input type="password" id="password" class="form-control" name="p4ssw0rd" required>
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <a href="" class="btn btn-link"> Contactar a Sistemas </a>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-danger">Confirmar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/animsition.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/select2.min.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>
    <script src="js/countdowntime.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
