package servlet;

import Controladores.ActualizarEmpleado;
import Controladores.TieneAccionPermitida;
import Entities.Notificaciones.Notificacion;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

@WebServlet("/actualizarUsuario")
public class actualizarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
        if (e == null) {
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        if (new TieneAccionPermitida().tieneAccionPermitidaByCodigoAccion(e,new SistemaAccion("MUS"))){
            int idPerfil = Integer.parseInt(req.getParameter("inputPerfil"));
            String email = req.getParameter("inputEmail");
            String celular = req.getParameter("inputCelular");
            String telefono = req.getParameter("inputTelefono");
            String usuario = req.getParameter("inputUser");
            int idUsuario = Integer.parseInt(req.getParameter("inputID"));
            PersonaEmpleado pe = new PersonaEmpleado(idUsuario, usuario);
            pe.setPerfil(new PersonaPerfil(idPerfil));
            pe.setEmail(email);
            pe.setCelular(celular);
            pe.setTelefono(telefono);
            Object a = new ActualizarEmpleado().actualizarEmpleado(pe, e);
            if (a.getClass().toString().equals("class java.lang.Boolean")) {
                session.setAttribute("ABMCorrecta", "Se ha actualizado al usuario correctamente");
                resp.sendRedirect("adminUsuarioForm.jsp?usr="+pe.getUsuario());
            } else {
                Notificacion b = (Notificacion) a;
                session.setAttribute ("ABMIncorrecta", "No se ha podido actualizar al usuario "+pe.getUsuario()+". Se ha creado una notificacion (28/EAU)");
                resp.sendRedirect("adminUsuarioForm.jsp?usr="+pe.getUsuario());
            }
        } else {
            session.setAttribute("accionNoPermitida","modificar el estado de los usuarios.");
            resp.sendRedirect("adminUsuarioForm.jsp?usr="+req.getParameter("inputUser"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
