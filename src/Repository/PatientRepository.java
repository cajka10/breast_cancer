package Repository;

import Core.DbConnection;
import Entity.PatientRecord;
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

public class PatientRepository {

    public int addPatient(PatientRecord record, String tableName) {
        int row = 0;
        String query = "insert into public." + tableName + " (radius_mean, texture_mean, perimeter_mean, area_mean, smoothness_mean, compactness_mean, concavity_mean, concave_points_mean," +
                " symmetry_mean, fractal_dimension_mean, radius_se, texture_se, perimeter_se, area_se, smoothness_se, compactness_se, concavity_se, concave_points_se, symmetry_seq, " +
                " fractal_dimension_se, radius_worst, texture_worst, perimeter_worst, area_worst, smoothness_worst, compactness_worst, concavity_worst, concave_points_worst," +
                " symmetry_worst, fractal_dimension_worst) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setDouble(1, (record.getRadiusMean()));
            stmt.setDouble(2, (record.getTextureMean()));
            stmt.setDouble(3, (record.getPerimeterMean()));
            stmt.setDouble(4, (record.getAreaMean()));
            stmt.setDouble(5, (record.getSmoothnessMean()));
            stmt.setDouble(6, (record.getCompactnessMean()));
            stmt.setDouble(7, (record.getConcavityMean()));
            stmt.setDouble(8, (record.getConcave_pointsMean()));
            stmt.setDouble(9, (record.getSymmetryMean()));
            stmt.setDouble(10, (record.getFractal_dimensionMean()));

            stmt.setDouble(11, (record.getRadiusSe()));
            stmt.setDouble(12, (record.getTextureSe()));
            stmt.setDouble(13, (record.getPerimeterSe()));
            stmt.setDouble(14, (record.getAreaSe()));
            stmt.setDouble(15, (record.getSmoothnessSe()));
            stmt.setDouble(16, (record.getCompactnessSe()));
            stmt.setDouble(17, (record.getConcavitySe()));
            stmt.setDouble(18, (record.getConcave_pointsSe()));
            stmt.setDouble(19, (record.getSymmetrySe()));
            stmt.setDouble(20, (record.getFractal_dimensionSe()));

            stmt.setDouble(21, (record.getRadiusWorst()));
            stmt.setDouble(22, (record.getTextureWorst()));
            stmt.setDouble(23, (record.getPerimeterWorst()));
            stmt.setDouble(24, (record.getAreaWorst()));
            stmt.setDouble(25, (record.getSmoothnessWorst()));
            stmt.setDouble(26, (record.getCompactnessWorst()));
            stmt.setDouble(27, (record.getConcavityWorst()));
            stmt.setDouble(28, (record.getConcave_pointsWorst()));
            stmt.setDouble(29, (record.getSymmetryWorst()));
            stmt.setDouble(30, (record.getFractal_dimensionWorst()));

            row = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            System.out.println(e.getSQLState());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return row;
    }

    private Connection getConnection() {
        DbConnection connection = new DbConnection();
        return connection.getDbConnection();
    }

    public TableView getPatientColumns(String table) {
        String query = "Select * from public." + table;
        TableView tableView = new TableView();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //Dynamic tableviews columns
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableView.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Rows
                ObservableList<Object> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Columns
                    row.add(rs.getDouble(i));
                }
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

    public PatientRecord getPatientById(int patientId, String patient_table) {
        String query = "Select * from public." + patient_table + " where patient_id = ?";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setInt(1, patientId);
            PatientRecord record = new PatientRecord();
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                record.setRecordId(rs.getInt("patient_id"));
                record.setRadiusMean(rs.getDouble("radius_mean"));
                record.setTextureMean(rs.getDouble("texture_mean"));
                record.setPerimeterMean(rs.getDouble("perimeter_mean"));
                record.setAreaMean(rs.getDouble("area_mean"));
                record.setSmoothnessMean(rs.getDouble("smoothness_mean"));
                record.setCompactnessMean(rs.getDouble("compactness_mean"));
                record.setConcave_pointsMean(rs.getDouble("concave_points_mean"));
                record.setSymmetryMean(rs.getDouble("symmetry_mean"));
                record.setFractal_dimensionMean(rs.getDouble("fractal_dimension_mean"));
                record.setConcavityMean(rs.getDouble("concavity_mean"));

                record.setRadiusSe(rs.getDouble("radius_se"));
                record.setTextureSe(rs.getDouble("texture_se"));
                record.setPerimeterSe(rs.getDouble("perimeter_se"));
                record.setAreaSe(rs.getDouble("area_se"));
                record.setSmoothnessSe(rs.getDouble("smoothness_se"));
                record.setCompactnessSe(rs.getDouble("compactness_se"));
                record.setConcavitySe(rs.getDouble("concavity_se"));
                record.setConcave_pointsSe(rs.getDouble("concave_points_se"));
                record.setSymmetrySe(rs.getDouble("symmetry_seq"));
                record.setFractal_dimensionSe(rs.getDouble("fractal_dimension_se"));

                record.setRadiusWorst(rs.getDouble("radius_worst"));
                record.setTextureWorst(rs.getDouble("texture_worst"));
                record.setPerimeterWorst(rs.getDouble("perimeter_worst"));
                record.setAreaWorst(rs.getDouble("area_worst"));
                record.setSmoothnessWorst(rs.getDouble("smoothness_worst"));
                record.setCompactnessWorst(rs.getDouble("compactness_worst"));
                record.setConcavityWorst(rs.getDouble("concavity_worst"));
                record.setConcave_pointsWorst(rs.getDouble("concave_points_worst"));
                record.setSymmetryWorst(rs.getDouble("symmetry_worst"));
                record.setFractal_dimensionWorst(rs.getDouble("fractal_dimension_worst"));
                return record;
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
