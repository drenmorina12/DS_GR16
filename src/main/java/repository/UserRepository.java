package repository;

import model.SupervisorTableModel;
import model.dto.User.UserEditDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {

    public static SupervisorTableModel getSupervisorByEmail(String email){
        String query = """
                SELECT * FROM tblUser
                WHERE email = ? LIMIT 1;
                """;
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getSupervisorFromResultSet(result);
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }

    private static SupervisorTableModel getSupervisorFromResultSet(ResultSet result){
        try{
            int mbikqyresiId = result.getInt("userId");
            String emri = result.getString("emri");
            String mbiemri = result.getString("mbiemri");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new SupervisorTableModel(mbikqyresiId, emri, mbiemri, email, salt, passwordHash);
        } catch (Exception e){
            return null;
        }
    }

    public static ArrayList<SupervisorTableModel> getAllMbikqyresiArray() {
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM tblUser";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getSupervisorsFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<SupervisorTableModel> getSupervisorsFromResultSet(ResultSet result) {
        ArrayList<SupervisorTableModel> array = new ArrayList<>();
        try {
            while (result.next()) {
                int mbikqyresiId = result.getInt("userId");
                String email = result.getString("email");
                String emri = result.getString("emri");
                String mbiemri = result.getString("mbiemri");
                String salt = result.getString("salt");
                String passwordHash = result.getString("passwordHash");
                array.add(new SupervisorTableModel(mbikqyresiId, emri, mbiemri, email,salt, passwordHash));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<SupervisorTableModel> getSupervisorsSearch(String search){
        Connection conn = DBConnector.getConnection();

        String query = "SELECT * FROM tblUser WHERE userId = ? OR email LIKE ? OR emri LIKE ? OR mbiemri LIKE ?";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, search);
            pst.setString(2, "%" + search + "%");
            pst.setString(3, "%" + search + "%");
            pst.setString(4, "%" + search + "%");

            ResultSet result = pst.executeQuery();

            return getSupervisorsFromResultSet(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean editMbikqyresi(UserEditDto user) {
        Connection connection = DBConnector.getConnection();
        String query = "UPDATE tblUser SET email = ?, emri = ?, mbiemri = ? WHERE userId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getFirstName());
            pst.setString(3, user.getLastName());
            pst.setInt(4, user.getId());
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSupervisor(int id) {
        Connection connection = DBConnector.getConnection();
        String query = "DELETE FROM tblUser WHERE userId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            
        }
    }

}
