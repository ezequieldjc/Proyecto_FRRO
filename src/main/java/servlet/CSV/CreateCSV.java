package servlet.CSV;

import Controladores.BuscarLogins;
import Controladores.GrabarMensaje;
import Controladores.TieneAccionPermitida;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;
import Entities.System.SistemaLogLogins;
import Entities.System.SistemaMensaje;
import Entities.System.SistemaModulo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet("/downloadLogins")
public class CreateCSV extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            PersonaEmpleado e = (PersonaEmpleado) req.getSession(true).getAttribute("empleado");
            if (e!=null) {
                if (new TieneAccionPermitida().tieneAccionPermitidaByCodigoAccion(e, new SistemaAccion("CSV_LOG"))) {
                    String user = "";
                    String estado = "";
                    if (req.getParameter("inputUser") != null)
                        user = req.getParameter("inputUser");

                    if (req.getParameter("inputResultado") != null)
                        estado = req.getParameter("inputResultado");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                    LocalDateTime now = LocalDateTime.now();
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition", "attachment; filename=\"logins_at_" + dtf.format(now) + ".csv\"");

                    new GrabarMensaje(new SistemaMensaje(new SistemaModulo(3),"Se ha descargado logins_at_"+dtf.format(now)+".csv",e));

                    OutputStream outputStream = response.getOutputStream();
                    String out = "ID,USER,PasswdHasheada,FechaIntendo,Resultado\n";
                    outputStream.write(out.getBytes());
                    for (SistemaLogLogins l : new BuscarLogins().buscarLogins(user, estado)) {
                        out = (l.getId() + "," + l.getUsuario() + "," + l.getPassword() + "," + l.getTiempo() + "," + l.getResultado() + "\n");
                        outputStream.write(out.getBytes());
                    }
                    outputStream.flush();
                    outputStream.close();
                    req.getRequestDispatcher("menu.jsp").forward(req, response);
                } else {
                    req.getSession(true).setAttribute("accionNoPermitida","generar csv de logins.");
                    req.getRequestDispatcher("menu.jsp").forward(req, response);
                }
            } else {
                req.getRequestDispatcher("menu.jsp").forward(req, response);
            }
            req.getRequestDispatcher("menu.jsp").forward(req, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
