package servlet;

import Controladores.*;
import Data.DataGlobalConfig;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaLogLogins;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Locale;


@WebServlet("/login")
public class login extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean flag = false;

        HttpSession session = req.getSession(true);
        try {
            if (session.getAttribute("empleado") != null) {
                //req.getRequestDispatcher("menu.jsp").forward(req, resp);
                resp.sendRedirect("menu.jsp");
            } else {
                PersonaEmpleado e = new VerificarLogin().esEmpleadoValido(new PersonaEmpleado(req.getParameter("username"), req.getParameter("p4ssw0rd")));
                if (e == null) {
                    //usuario incorrecto
                    session.setAttribute("datosIncorrectos", true);
                    //req.getRequestDispatcher("login.jsp").forward(req, resp);
                    resp.sendRedirect("login.jsp");
                } else {
                    //usuario encontrado
                    if (e.getEstado()) {
                        //Si existe, y esta habilidato
                        flag = true;
                        SistemaGlobalConfig g = new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("CLS"));
                        //Veo si el administrador quiere validar que no existan sesiones abiertas (globalConfig)
                        if (g.getValorAtributo().equals("1")) {
                            if (new ManejoSesiones().tieneSesionAbierta(e)) {
                                session.setAttribute("tieneSesionAbierta", true);
                                //req.getRequestDispatcher("login.jsp").forward(req, resp);
                                resp.sendRedirect("login.jsp");
                            } else {
                                session.setAttribute("empleado", e);
                                session.setMaxInactiveInterval(Integer.parseInt(new DataGlobalConfig().getOneByCodigo(new SistemaGlobalConfig("MII")).getValorAtributo()));
                                new ManejoSesiones(e);
                                //req.getRequestDispatcher("menu.jsp").forward(req, resp);
                                resp.sendRedirect("menu.jsp");
                            }
                        } else {
                            session.setAttribute("empleado", e);
                            new ManejoSesiones(e);
                            resp.sendRedirect("menu.jsp");
                            //req.getRequestDispatcher("menu.jsp").forward(req, resp);
                        }
                    } else {
                        //Si existe pero ta inhabilitado
                        flag = true;
                        session.setAttribute("usuarioInhabilitado", true);
                        //req.getRequestDispatcher("login.jsp").forward(req, resp);
                        resp.sendRedirect("login.jsp");
                    }
                }
                new RegistrarLogin(req.getParameter("username"), new HashearPassword().hidePwd(req.getParameter("p4ssw0rd")), flag);
            }
        } catch (SQLException ef){
            ef.printStackTrace();
        }
    }
}
