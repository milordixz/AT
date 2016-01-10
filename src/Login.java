import ru.rostelecom.UsersDAO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
        JsonReader reader = Json.createReader(request.getInputStream());
        JsonObject newJson = reader.readObject();
        reader.close();

        try {

            boolean status =  UsersDAO.checkUser(newJson.getString("login").toString(), newJson.getString("password").toString());
            System.out.println(newJson.getString("login"));
            System.out.println(newJson.getString("password"));

            response.setContentType("text/html; charset=UTF-8");
            if (status == false) response.getOutputStream().write("User true".getBytes());
            else response.getOutputStream().write("Incorrect login or password".getBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
