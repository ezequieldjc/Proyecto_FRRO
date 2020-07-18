package Controladores;

import Data.DataSesiones;
import Entities.Persona.PersonaEmpleado;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class VerificarMenu {
    public void VerificarMenu(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(true);
            PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
            if (e == null) {
                //response.sendRedirect("login.jsp");
                resp.sendRedirect("login.jsp");
            } else {
                if (!(new DataSesiones().tieneSesionAbierta(e))) {
                    session.invalidate();
                    resp.sendRedirect("login.jsp");
                    //request.getRequestDispatcher("login.jsp").forward(request,response);
                }
            }
            e.setNotificaciones(new BuscarNotificaciones().getAllByUsuario(e));
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }

    }
}

