package servlet.desbloquearAtributos;

import Controladores.TieneAccionPermitida;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/desbloquearAtributosPerfil")
public class desbloquearAtributosPerfil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession(true);
        PersonaEmpleado e = (PersonaEmpleado) sesion.getAttribute("empleado");
        String idPerfil = (String) sesion.getAttribute("idPerfil");
        if (new TieneAccionPermitida().tieneAccionPermitidaByCodigoAccion(e,new SistemaAccion("MPE"))){
            sesion.setAttribute("bloquearAtributos", false);
            resp.sendRedirect("perfilModificacion.jsp?id="+idPerfil);
        } else {
            sesion.setAttribute("accionNoPermitida","modificar el estado de los usuarios.");
            resp.sendRedirect("perfilModificacion.jsp?id="+idPerfil);
        }

    }
}
