

import ru.rostelecom.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



/**
 * Created by Admin on 25.11.2015.
 */
@WebServlet(name = "MyServlet",urlPatterns = "/index")
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("Users", UsersDAO.getUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
}
