package Repository;

import Core.DbConnection;
import Core.Entity.Enum.UserRole;
import Core.Entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public UserRepository() {

    }

    public User login(String userName) {
        User loggedUser = new User();
        String query = String.format("Select user_id, user_name, role_id, is_new from public.user where user_name like '%s' ", userName);

        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                loggedUser.setUserName(rs.getString("user_name"));
                loggedUser.setRole(UserRole.valueOf(rs.getInt("role_id")));
                loggedUser.setNew(rs.getBoolean("is_new"));
                loggedUser.setUserId(rs.getInt("user_id"));
                return loggedUser;
            }
        } catch (
                SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getPsswd(int userId){
        String psswd = "";
        String query = String.format("Select password from public.user where user_id = '%s' ", userId);

        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                psswd = rs.getString("password");
            }
        } catch (
                SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return psswd;
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

    public User getUserById(int userId){
        User user = new User();

        String query = "Select user_name, role_id, is_new from public.user where user_id = ? ";

        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setInt(0, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUserId(userId);
                user.setUserName(rs.getString("user_name"));
                user.setRole(UserRole.valueOf(rs.getInt("role_id")));
                user.setNew(rs.getBoolean("is_new"));
            }
        } catch (
                SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public TableView getUserColumns() {
        String query = "Select u.user_id, u.user_name, (select name from user_role where role_id = u.role_id) as role  from public.user u where u.active = true";
        TableView tableView = new TableView();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //Dynamic tableviews columns
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableView.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Rows
                ObservableList<Object> row = FXCollections.observableArrayList();
                row.add(rs.getInt("user_id"));
                row.add(rs.getString("user_name"));
                row.add(rs.getString("role"));

                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            tableView.getItems().setAll(data);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
        return tableView;
    }


    private Connection getConnection() {
        DbConnection connection = new DbConnection();
        return connection.getDbConnection();
    }

    public int changeUserPassword(int userId, String password) {
        int recordsUpdated = 0;
        String query = "UPDATE public.user set password = ?, is_new = false where user_id = ? ";

        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, userId);

            recordsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();

        }
        return recordsUpdated;
    }
}
