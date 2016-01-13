import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import soap.*;

/**
 * Created by Milord on 14.01.2016.
 */
@WebServlet(name = "Sertification",urlPatterns = "/soap")
public class Sertification extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonReader reader = Json.createReader(request.getInputStream());
        JsonObject newJson = reader.readObject();
        reader.close();

        Soap soap = new Soap(newJson.getString("sName"),newJson.getString("sCode"),newJson.getString("sData"));
        response.setContentType("text/html; charset=UTF-8");
        String CreateSoap;
        try {
            CreateSoap = soap.create();
            response.getOutputStream().write(CreateSoap.getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }


    }


}
