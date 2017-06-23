import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/23.
 */
@javax.servlet.annotation.WebServlet(name = "HelloWorldServlet",urlPatterns ={"/hello"})
public class HelloWorldServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write("<h1> hello first servlet!</h1>");
    }
}
