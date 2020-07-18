package servlet;

import Controladores.ManejoSesiones;
import Entities.Persona.PersonaEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonaEmpleado e = (PersonaEmpleado) req.getSession(true).getAttribute("empleado");
        new ManejoSesiones().cerrarSesiones(e);
        req.getSession(true).invalidate();
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
