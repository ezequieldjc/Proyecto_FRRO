package servlet;

import Entities.Persona.PersonaEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cambiarImagen")
public class cambiarImagenPerfilEmpleado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
        if (e == null) {
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
