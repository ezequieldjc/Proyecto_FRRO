package UI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DrawModalNotificaciones {
    public DrawModalNotificaciones(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter a =  resp.getWriter();
        a.write("Hola mundo!");
        a.close();



    }
}
