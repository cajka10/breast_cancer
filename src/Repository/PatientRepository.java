package Repository;

import Core.DbConnection;
import Core.Entity.Enum.TumorType;
import Core.Entity.PatientRecord;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRepository {
    static final Logger logger = Logger.getLogger(PatientRepository.class.getName());

    public int addPatient(PatientRecord record, int userId){
        String query = "insert into public.PATIENT (radius_mean, texture_mean, perimeter_mean, area_mean, smoothness_mean, compactness_mean, concavity_mean, concave_points_mean," +
                " symmetry_mean, fractal_dimension_mean, radius_se, texture_se, perimeter_se, area_se, smoothness_se, compactness_se, concavity_se, concave_points_se, symmetry_se, " +
                " fractal_dimension_se, radius_worst, texture_worst, perimeter_worst, area_worst, smoothness_worst, compactness_worst, concavity_worst, concave_points_worst," +
                " symmetry_worst, fractal_dimension_worst, doctor_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        logger.debug("Adding patient started.");
        return  this.addRecordForPatient(record, query,  userId);
    }

    public int addPatientRecord(PatientRecord record){
        String query = "insert into public.PATIENT_RECORD (radius_mean, texture_mean, perimeter_mean, area_mean, smoothness_mean, compactness_mean, concavity_mean, concave_points_mean," +
                " symmetry_mean, fractal_dimension_mean, radius_se, texture_se, perimeter_se, area_se, smoothness_se, compactness_se, concavity_se, concave_points_se, symmetry_se, " +
                " fractal_dimension_se, radius_worst, texture_worst, perimeter_worst, area_worst, smoothness_worst, compactness_worst, concavity_worst, concave_points_worst," +
                " symmetry_worst, fractal_dimension_worst, class) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        logger.debug("Adding patient record started.");
        return  this.addRecordForPatient(record, query,  -1);
    }

    public int addRecordForPatient(PatientRecord record, String query, int userId) {
        int row = 0;
        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
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

            if (userId != -1)
                stmt.setInt(31, (userId));
            else
                stmt.setString(31, record.getTumorType().getValue());
            row = stmt.executeUpdate();
            conn.close();
            logger.debug("Adding patient record finished correctly.");

        } catch (SQLException e) {
            System.err.format("SQL State: %s= ?,%s", e.getSQLState(), e.getMessage());
            System.out.println(e.getSQLState());
            logger.debug("Adding patient failed.");
            logger.debug(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.debug("Adding patient failed.");
            logger.debug(e.getMessage());
        }

        return row;
    }

    private Connection getConnection() {
        DbConnection connection = new DbConnection();
        return connection.getDbConnection();
    }

    public TableView getPatientColumns(String table, int userId){
        String query = "";
        if (table.equals("PATIENT_RECORD")) {
             query = "Select record_id as \"id\", radius_mean as \"radius (mean)\", texture_mean as \"radius (texture (mean))\", " +
                    " perimeter_mean as \"perimeter (mean)\", area_mean as \"area (mean)\"," +
                    " smoothness_mean as \"smoothness (mean)\", compactness_mean as \"compactness (mean)\"," +
                    " concavity_mean as \"concavity (mean)\", concave_points_mean as \"concave points (mean)\", " +
                    " symmetry_mean as \"symmetry (mean)\", fractal_dimension_mean as \"fractal dimension (mean)\", " +
                    " radius_se as \"radius (se)\", texture_se as \"texture (mean)\", " +
                    " perimeter_se as \"perimeter (se)\", area_se as \"area (se)\"," +
                    " smoothness_se as \"smoothness (se)\", compactness_se as \"compactness (se)\"," +
                    " concavity_se as \"concavity (se)\", concave_points_se as \"concave points (se)\", " +
                    " symmetry_se as \"symmetry (se)\", fractal_dimension_se as \"fractal dimension (se)\", " +
                    " radius_worst as \"radius (worst)\", texture_worst as \"radius (texture (worst))\", " +
                    " perimeter_worst as \"perimeter (worst)\", area_worst as \"area (worst)\"," +
                    " smoothness_worst as \"smoothness (worst)\", compactness_worst as \"compactness (worst)\"," +
                    " concavity_worst as \"concavity (worst)\", concave_points_worst as \"concave points (worst)\", " +
                    " symmetry_worst as \"symmetry (worst)\", fractal_dimension_worst as \"fractal dimension (worst)\", class as  \"Nádor\" " +
                    " from public." + table;
            }
        else{
            query = "Select record_id as \"id\", pi.name, pi.surname, pi.birth_id as \"birth id\", radius_mean as \"radius (mean)\"," +
                    " texture_mean as \"radius (texture (mean))\", " +
                    " perimeter_mean as \"perimeter (mean)\", area_mean as \"area (mean)\"," +
                    " smoothness_mean as \"smoothness (mean)\", compactness_mean as \"compactness (mean)\"," +
                    " concavity_mean as \"concavity (mean)\", concave_points_mean as \"concave points (mean)\", " +
                    " symmetry_mean as \"symmetry (mean)\", fractal_dimension_mean as \"fractal dimension (mean)\", " +
                    " radius_se as \"radius (se)\", texture_se as \"texture (mean)\", " +
                    " perimeter_se as \"perimeter (se)\", area_se as \"area (se)\"," +
                    " smoothness_se as \"smoothness (se)\", compactness_se as \"compactness (se)\"," +
                    " concavity_se as \"concavity (se)\", concave_points_se as \"concave points (se)\", " +
                    " symmetry_se as \"symmetry (se)\", fractal_dimension_se as \"fractal dimension (se)\", " +
                    " radius_worst as \"radius (worst)\", texture_worst as \"radius (texture (worst))\", " +
                    " perimeter_worst as \"perimeter (worst)\", area_worst as \"area (worst)\"," +
                    " smoothness_worst as \"smoothness (worst)\", compactness_worst as \"compactness (worst)\"," +
                    " concavity_worst as \"concavity (worst)\", concave_points_worst as \"concave points (worst)\", " +
                    " symmetry_worst as \"symmetry (worst)\", fractal_dimension_worst as \"fractal dimension (worst)\", " +
                    " class from public." + table + " pt join public.patient_info pi using (birth_id)" +
                    " join public.user us on (us.user_id = pt.doctor_id ) " +
                    " where pt.active = true and doctor_id = " + userId ;

        }
        return this.getPatientColumns(query, table);
    }

    public TableView getPatientColumns(String query, String table) {
        logger.debug("Start - GetPatientColumns - " + table);
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
            }

            while (rs.next()) {
                //Rows
                ObservableList<Object> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Columns
                    try {
                        if (i == 1)
                            row.add(rs.getInt(i));
                        else
                            row.add(rs.getDouble(i));
                    } catch (Exception ex){
                        row.add(rs.getString(i).replaceAll("\\s+",""));
                    }
                }
                data.add(row);
            }
            tableView.getItems().setAll(data);
            logger.debug("END - GetPatientColumns - " + table);
        } catch (SQLException e) {
            System.out.println("SQL Failed");
            e.printStackTrace();
            logger.debug("Error getting patients records.");
            logger.debug(e.getMessage());
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.debug(e.getMessage());
            return null;
        }
        return tableView;
    }

    public PatientRecord getPatientById(int patientId, String patient_table) {
        String query = "Select * from public." + patient_table + " where record_id = ?";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setInt(1, patientId);
            PatientRecord record = new PatientRecord();
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                record.setRecordId(rs.getInt("record_id"));
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
                record.setSymmetrySe(rs.getDouble("symmetry_se"));
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

                record.setTumorType(TumorType.getValueOf(rs.getString("class").replaceAll("\\s+","")));

                return record;
            }

        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            ex.printStackTrace();
            logger.debug("Error while getting patient.");
            logger.debug(ex.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.debug(e.getMessage());
            return null;
        }
        return null;
    }

    public int editPatient(PatientRecord patient, String table){
        int row = 0;
        String query = "Update public." + table +
                " set " +
                "  radius_mean = ?, " +
                "  texture_mean = ?," +
                "  perimeter_mean = ?," +
                "  area_mean = ?," +
                "  smoothness_mean = ?," +
                "  compactness_mean = ?," +
                "  concavity_mean = ?, " +
                "  concave_points_mean = ?," +
                "  symmetry_mean = ?," +
                "  fractal_dimension_mean = ?," +
                "  radius_se= ?," +
                "  texture_se= ?," +
                "  perimeter_se= ?," +
                "  area_se= ?," +
                "  smoothness_se= ?," +
                "  compactness_se= ?," +
                "  concavity_se= ?," +
                "  concave_points_se= ?," +
                "  symmetry_se= ?," +
                "  fractal_dimension_se= ?," +
                "  radius_worst= ?," +
                "  texture_worst= ?," +
                "  perimeter_worst= ?," +
                "  area_worst= ?," +
                "  smoothness_worst= ?," +
                "  compactness_worst= ?," +
                "  concavity_worst= ?," +
                "  concave_points_worst= ?," +
                "  symmetry_worst= ?," +
                "  fractal_dimension_worst= ?," +
                "  class = ?" +
                "  where record_id = ? ";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setDouble(1, patient.getRadiusMean());
            stmt.setDouble(2, patient.getTextureMean());
            stmt.setDouble(3, patient.getPerimeterMean());
            stmt.setDouble(4, patient.getAreaMean());
            stmt.setDouble(5, patient.getSmoothnessMean());
            stmt.setDouble(6, patient.getCompactnessMean());
            stmt.setDouble(7, patient.getConcavityMean());
            stmt.setDouble(8, patient.getConcave_pointsMean());
            stmt.setDouble(9, patient.getSymmetryMean());
            stmt.setDouble(10, patient.getFractal_dimensionMean());

            stmt.setDouble(11, patient.getRadiusSe());
            stmt.setDouble(12, patient.getTextureSe());
            stmt.setDouble(13, patient.getPerimeterSe());
            stmt.setDouble(14, patient.getAreaSe());
            stmt.setDouble(15, patient.getSmoothnessSe());
            stmt.setDouble(16, patient.getCompactnessSe());
            stmt.setDouble(17, patient.getConcavitySe());
            stmt.setDouble(18, patient.getConcave_pointsSe());
            stmt.setDouble(19, patient.getSymmetrySe());
            stmt.setDouble(20, patient.getFractal_dimensionSe());

            stmt.setDouble(21, patient.getRadiusWorst());
            stmt.setDouble(22, patient.getTextureWorst());
            stmt.setDouble(23, patient.getPerimeterWorst());
            stmt.setDouble(24, patient.getAreaWorst());
            stmt.setDouble(25, patient.getSmoothnessWorst());
            stmt.setDouble(26, patient.getCompactnessWorst());
            stmt.setDouble(27, patient.getConcavityWorst());
            stmt.setDouble(28, patient.getConcave_pointsWorst());
            stmt.setDouble(29, patient.getSymmetryWorst());
            stmt.setDouble(30, patient.getFractal_dimensionWorst());

            stmt.setString(31, "B");
            stmt.setInt(32, patient.getRecordId());

            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            ex.printStackTrace();
            logger.debug("Error updating patient with id: " + patient.getRecordId());
            logger.debug(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.debug(e.getMessage());
        }
        return row;
    }

    public boolean deletePatient(int patientId) {
        String query = "update public.PATIENT " +
                " set active = false " +
                " where record_id = ? ";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setInt(1, patientId);
            return stmt.execute();
        }
        catch (SQLException ex){
            System.out.println("Connection Failed");
            ex.printStackTrace();
            logger.debug("Error deleting patient with id: " + patientId);
            logger.debug(ex.getMessage());
            return false;
        }
    }
}
