package repository;

import model.Admin;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository {
    public static Admin getByEmail(String email){
        String query = "SELECT * FROM tblAdmin WHERE email = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getAdminFromResultSet(result);
            }

            return null;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static Admin getAdminFromResultSet(ResultSet result){
        try{
            int id = result.getInt("adminId");
            String firstName = result.getString("emri");
            String lastName = result.getString("mbiemri");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String PasswodHash = result.getString("passwordHash");

            return new Admin(id,firstName,lastName,email,salt,PasswodHash);
        }catch(Exception e){
            return null;
        }
    }
}
