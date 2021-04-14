package Repository;

import Core.DbConnection;
import Entity.PatientRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientRepository {
    private DbConnection connection;
    int row = 0;
    public void addPatient(PatientRecord record){
        String query = "insert into public.patient_record (radius_mean, texture_mean, perimeter_mean, area_mean, smoothness_mean, compactness_mean, concavity_mean, concave_points_mean," +
                " symmetry_mean, fractal_dimension_mean, radius_se, texture_se, perimeter_se, area_se, smoothness_se, compactness_se, concavity_se, concave_points_se, symmetry_seq, " +
                " fractal_dimension_se, radius_worst, texture_worst, perimeter_worst, area_worst, smoothness_worst, compactness_worst, concavity_worst, concave_points_worst," +
                " symmetry_worst, fractal_dimension_worst) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(query)) {
            stmt.setString(1, String.valueOf(record.getRadiusMean()));
            stmt.setString(2, String.valueOf(record.getTextureMean()));
            stmt.setString(3, String.valueOf(record.getPerimeterMean()));
            stmt.setString(4, String.valueOf(record.getAreaMean()));
            stmt.setString(5, String.valueOf(record.getSmoothnessMean()));
            stmt.setString(6, String.valueOf(record.getCompactnessMean()));
            stmt.setString(7, String.valueOf(record.getConcavityMean()));
            stmt.setString(8, String.valueOf(record.getConcave_pointsMean()));
            stmt.setString(9, String.valueOf(record.getSymmetryMean()));
            stmt.setString(10, String.valueOf(record.getFractal_dimensionMean()));

            stmt.setString(11, String.valueOf(record.getRadiusSe()));
            stmt.setString(12, String.valueOf(record.getTextureSe()));
            stmt.setString(13, String.valueOf(record.getPerimeterSe()));
            stmt.setString(14, String.valueOf(record.getAreaSe()));
            stmt.setString(15, String.valueOf(record.getSmoothnessSe()));
            stmt.setString(16, String.valueOf(record.getCompactnessSe()));
            stmt.setString(17, String.valueOf(record.getConcavitySe()));
            stmt.setString(18, String.valueOf(record.getConcave_pointsSe()));
            stmt.setString(19, String.valueOf(record.getSymmetrySe()));
            stmt.setString(20, String.valueOf(record.getFractal_dimensionSe()));

            stmt.setString(21, String.valueOf(record.getRadiusWorst()));
            stmt.setString(22, String.valueOf(record.getTextureWorst()));
            stmt.setString(23, String.valueOf(record.getPerimeterWorst()));
            stmt.setString(24, String.valueOf(record.getAreaWorst()));
            stmt.setString(25, String.valueOf(record.getSmoothnessWorst()));
            stmt.setString(26, String.valueOf(record.getCompactnessWorst()));
            stmt.setString(27, String.valueOf(record.getConcavityWorst()));
            stmt.setString(28, String.valueOf(record.getConcave_pointsWorst()));
            stmt.setString(29, String.valueOf(record.getSymmetryWorst()));
            stmt.setString(30, String.valueOf(record.getFractal_dimensionWorst()));

            row = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private Connection getConnection() {
        DbConnection connection = new DbConnection();
        return connection.getDbConnection();
    }
}
