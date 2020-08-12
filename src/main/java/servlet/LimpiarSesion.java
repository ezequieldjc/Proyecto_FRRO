package servlet;

import Controladores.*;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaLogLogins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/limpiarSesion")
public class LimpiarSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req!=null){
            HttpSession session = req.getSession(true);
            try {
                PersonaEmpleado e = new DataPersonaEmpleado().getByUsrYPass(new PersonaEmpleado(req.getParameter("username"), req.getParameter("p4ssw0rd")));
                if (new TieneAccionPermitida().tieneAccionPermitida(e, new SistemaAccion("CLS"))) {
                    new ManejoSesiones().limpiarSesion(e);
                    session.setAttribute("sesionLimpiada",true);
                }
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } catch (SQLException ef){
                ef.printStackTrace();
            }
        }
    }
}