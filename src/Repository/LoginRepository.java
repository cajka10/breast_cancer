package Repository;

import Core.DbConnection;
import Entity.Enum.UserRole;
import Entity.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private DbConnection connection;

    public LoginRepository() {

    }

    public User login(String userName, String psswd) {
        User loggedUser = new User();
        String query = String.format("Select user_name, role_id from public.user where user_name like '%s' and password like '%s'", userName, psswd);

        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                loggedUser.setUserName(rs.getString("user_name"));
                loggedUser.setRole(UserRole.valueOf(rs.getInt("role_id")));
            }
        } catch (
                SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return loggedUser;
    }

    private Connection getConnection() {
        DbConnection connection = new DbConnection();
        return connection.getDbConnection();
    }

}
