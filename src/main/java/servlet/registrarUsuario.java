package servlet;

import Controladores.*;
import Entities.Persona.PersonaDocumento;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaMensaje;
import Entities.System.SistemaModulo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/registrarUsuario")
public class registrarUsuario extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.toString());
        PersonaEmpleado e = new PersonaEmpleado();
        String nombre = req.getParameter("inputNombre");
        String ape = req.getParameter("inputApellido");
        String perfil = req.getParameter("inputPerfil");
        String tipoDoc = req.getParameter("documento");
        String nroDoc = req.getParameter("inputNroDoc");
        String fNacimiento = req.getParameter("inputFNacimiento");
        String telefono = req.getParameter("inputTelefono");
        String celular = req.getParameter("inputCelular");
        String img = req.getParameter("inputImg");

        HttpSession session = req.getSession(true);
        session.setAttribute("String", "FNacimientoIncorrecta");



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        if (! new VerificarDate().isValidDate(req.getParameter("inputFNacimiento"))){
            session.setAttribute("FNacimientoIncorrecta", "SI");
            req.getRequestDispatcher("usuarioAlta.jsp").forward(req,resp);
        } else {
            PersonaEmpleado e = new PersonaEmpleado();

            e.setNombre(req.getParameter("nombre"));
            e.setApellido(req.getParameter("apellido"));
            e.setPerfil(new PersonaPerfil(Integer.valueOf(req.getParameter("perfil"))));

            e.setDoc(new PersonaDocumento(Integer.valueOf(req.getParameter("documento")),req.getParameter("inputNroDoc")));
            e.setTelefono(req.getParameter("inputTelefono"));
            e.setCelular(req.getParameter("inputCelular"));
            e.setImg(req.getParameter("inputImg"));
            e.setFechaNacimiento(new Date(req.getParameter("inputFNacimiento")));


            PersonaEmpleado creador = new PersonaEmpleado(req.getParameter("usrusr"));
            e = new RegistrarUsuario().registrarUsuario(e);


            if (e.getId()!=0){
                //Quiere decir qeu se creo el usuario correctamente
                SistemaMensaje m = new SistemaMensaje();
                m.setEmpleado(creador);
                m.setMensaje("Se ha creado al usuario: " + e.getUsuario());
                m.setSistemaModulo(new SistemaModulo(5));
                new GrabarMensaje(m);
                session.setAttribute("nuevoUsuarioCreadoCorrectamente", e);
                req.getRequestDispatcher("menu.jsp").forward(req,resp);
            } else {
                session.setAttribute("NOnuevoUsuarioCreadoCorrectamente", "NO");
            }



        }




    }
}
