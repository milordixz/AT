package ru.rostelecom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 25.11.2015.
 */
public class UsersDAO {



    static Connection conn;
    static PreparedStatement pst;


    public static int insertUser (Users u){
    int status = 0;
         try{
             conn = ConnectionFactory.getCon();
             pst = conn.prepareStatement("insert into test VALUES (?,?,?,?,?)");
             pst.setInt(1,u.getId());
             pst.setString(2,u.getLogin());
             pst.setString(3,u.getPassword());
             pst.setString(4,u.getLastName());
             pst.setString(5,u.getFirstName());
             status = pst.executeUpdate();
             conn.close();
         }catch (Exception ex){
             System.out.println(ex);
         }

    return status;

    }
    public static List<Users> getUsers ()  throws SQLException{


            conn = ConnectionFactory.getCon();
            pst = conn.prepareStatement("SELECT test.id, test.login,test.password,test.firstname,test.lastname FROM test");
            ResultSet resultSet = pst.executeQuery();
    ArrayList<Users> user = new ArrayList<Users>();


        while(resultSet.next()){

                int id =  resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                user.add(new Users(id,login,password,firstName,lastName));

            }
        conn.close();




        return user;
    }


    public static boolean checkUser(String login, String password) throws SQLException {
        int status = 0;
        conn = ConnectionFactory.getCon();

            pst = conn.prepareStatement("SELECT  test.login,test.password FROM test WHERE test.login =? AND  test.password =?");
            pst.setString(1, login);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();
            boolean myResultSetEmpty = isMyResultSetEmpty(resultSet);
            System.out.println(pst.toString());
            System.out.println(resultSet);
            conn.close();

        return myResultSetEmpty;
    }


    public static boolean isMyResultSetEmpty(ResultSet rs) throws SQLException {

        if (!rs.isBeforeFirst() && rs.getRow() == 0){
            return true;

        }

        else return false;
    }
}
