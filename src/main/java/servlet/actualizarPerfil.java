package servlet;

import Controladores.ActualizarEmpleado;
import Controladores.ActualizarPerfil;
import Controladores.TieneAccionPermitida;
import Entities.Notificaciones.Notificacion;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

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
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

@WebServlet("/actualizarPerfil")
public class actualizarPerfil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*HttpSession session = req.getSession(true);
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
        }*/
        HttpSession session = req.getSession(true);
        PersonaEmpleado e = (PersonaEmpleado) req.getAttribute("empleado");
        PersonaPerfil p = new PersonaPerfil();
        p.setId(Integer.parseInt((String) session.getAttribute("idPerfil")));
        session.removeAttribute("idPerfil");
        p.setCodigo(req.getParameter("inputCodigo"));
        p.setName(req.getParameter("inputNombre"));
        p.setDesc(req.getParameter("inputDesc"));
        p.setOwner(new PersonaEmpleado(Integer.parseInt(req.getParameter("inputOwner"))));
        p.setBackup(new PersonaEmpleado(Integer.parseInt(req.getParameter("inputBackup"))));
        /*Recupero el arraylist de botones*/
        String botones[] = req.getParameterValues("botones");
        ArrayList<SistemaBoton> buttons = new ArrayList<SistemaBoton>();
        if (botones!=null){
            if (botones.length != 0) {
                for (String s : botones) {
                    buttons.add(new SistemaBoton(Integer.parseInt(s)));
                }
            }
        }
        p.setBotones(buttons);

        /*Recupero el arraylist de acciones*/
        String[] acciones = req.getParameterValues("acciones");
        ArrayList<SistemaAccion> actions = new ArrayList<SistemaAccion>();
        if (acciones!=null) {
            if(acciones.length != 0) {
                for (String s : acciones){
                    actions.add(new SistemaAccion(Integer.parseInt(s)));
                }
            }
        }
        p.setAcciones(actions);
        new ActualizarPerfil().actualizarPerfil(e,p);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
