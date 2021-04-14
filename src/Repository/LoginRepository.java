package Repository;

import Core.DbConnection;
import Entity.Enum.UserRole;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loggedUser;
    }

    public int addUser(String userName, String psswd, int roleId) {
        int row = 0;
        String query = "INSERT INTO public.user (user_name, password, role_id) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setString(1, userName);
            stmt.setString(2, psswd);
            stmt.setInt(3, roleId);

            row = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();

        }

        return row;
    }

    private Connection getConnection() {
        DbConnection connection = new DbConnection();
        return connection.getDbConnection();
    }

}
