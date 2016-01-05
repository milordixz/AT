import ru.rostelecom.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 26.11.2015.
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login =  request.getParameter("login");
             String password =  request.getParameter("password");

          int status =  UsersDAO.checkUser(login,password);
            if (status == 1) response.getOutputStream().write("Такой пользователь есть!".getBytes());
            else response.getOutputStream().write("Такого пользователя нет!".getBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
