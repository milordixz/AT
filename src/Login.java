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


               String FIO =    UsersDAO.checkUser(newJson.getString("login").toString(), newJson.getString("password").toString());
            System.out.println(newJson.getString("login"));
            System.out.println(newJson.getString("password"));

            response.setContentType("text/html; charset=UTF-8");
            if (!FIO.isEmpty()) {
                response.getOutputStream().write(FIO.getBytes("UTF-8"));
            } else {
                response.getOutputStream().write("Non password or login correct".getBytes("UTF-8"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
