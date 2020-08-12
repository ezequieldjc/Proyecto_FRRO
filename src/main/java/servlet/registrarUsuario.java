package servlet;

import Controladores.ManejoSesiones;
import Entities.Persona.PersonaEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + nombre);
        System.out.println("perfil: " + nombre);
        System.out.println("tipoDoc: " + nombre);
        System.out.println("fNac: " + nombre);
        System.out.println("telefon: " + nombre);
        System.out.println("celular: " + nombre);
        System.out.println("img: " + nombre);
        System.out.println("usr : " + req.getParameter("usrusr"));



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {






        System.out.println(req.toString());
        //this.doGet(req,resp);
        String nombre = req.getParameter("nombre");
        String ape = req.getParameter("apellido");
        String perfil = req.getParameter("perfil");
        String tipoDoc = req.getParameter("documento");
        String nroDoc = req.getParameter("inputNroDoc");
        String fNacimiento = req.getParameter("inputFNacimiento");
        String telefono = req.getParameter("inputTelefono");
        String celular = req.getParameter("inputCelular");
        String img = req.getParameter("inputImg");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + ape);
        System.out.println("perfil: " + perfil);
        System.out.println("tipoDoc: " + tipoDoc);
        System.out.println("fNac: " + fNacimiento);
        System.out.println("telefon: " + telefono);
        System.out.println("celular: " + celular);
        System.out.println("img: " + img);
        System.out.println("usr : " + req.getParameter("usrusr"));

    }
}
