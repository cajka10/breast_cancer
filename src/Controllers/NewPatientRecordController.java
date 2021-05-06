package Controllers;

import Entity.PatientRecord;
import Services.MainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPatientRecordController {

    @FXML
    private Button cancelButton;
    @FXML
    private TextField radiusMeanTextField;
    @FXML
    private TextField radiusSETextField;
    @FXML
    private TextField radiusWorstTextField;
    @FXML
    private TextField textureMeanTextField;
    @FXML
    private TextField textureSETextField1;
    @FXML
    private TextField textureWorstTextField;
    @FXML
    private TextField perimeterMeanTextField;
    @FXML
    private TextField perimeterSETextField;
    @FXML
    private TextField perimeterWorstTextField;
    @FXML
    private TextField areaMeanTextField;
    @FXML
    private TextField areaSETextField;
    @FXML
    private TextField areaWorstTextField;
    @FXML
    private TextField smoothnessMeanTextField;
    @FXML
    private TextField smoothnessSETextField;
    @FXML
    private TextField smoothnessWorstTextField;
    @FXML
    private TextField compactnessMeanTextField;
    @FXML
    private TextField compactnessSETextField;
    @FXML
    private TextField compactnessWorstTextField;
    @FXML
    private TextField concavityMeanTextField;
    @FXML
    private TextField concavitySETextField;
    @FXML
    private TextField concavityWorstTextField;
    @FXML
    private TextField concavePointsMeanTextField;
    @FXML
    private TextField concavePointsSETextField;
    @FXML
    private TextField concavePointsWorstTextField;
    @FXML
    private TextField symmetryMeanTextField;
    @FXML
    private TextField symmetrySETextField;
    @FXML
    private TextField symmetryWorstTextField;
    @FXML
    private TextField fractaDimensionMeanTextField;
    @FXML
    private TextField fractaDimensionSETextField;
    @FXML
    private TextField fractaDimensionWorstTextField;

    private MainService mainService;

    public NewPatientRecordController() {
        this.mainService = new MainService();
    }

    public void confirmButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        PatientRecord record = this.mapPatient();
        this.mainService.addPatientRecord(record);
        stage.close();
    }

    private PatientRecord mapPatient() {
        PatientRecord record = new PatientRecord();

        try {
            record.setRadiusMean(Double.parseDouble(this.radiusMeanTextField.getText().equals("") ? "0" : this.radiusMeanTextField.getText()));
            record.setRadiusSe(Double.parseDouble(this.radiusSETextField.getText().equals("") ? "0" : this.radiusSETextField.getText()));
            record.setRadiusWorst(Double.parseDouble(this.radiusWorstTextField.getText().equals("") ? "0" : this.radiusWorstTextField.getText()));

            record.setAreaMean(Double.parseDouble(this.areaMeanTextField.getText().equals("") ? "0" : this.areaMeanTextField.getText()));
            record.setAreaSe(Double.parseDouble(this.areaSETextField.getText().equals("") ? "0" : this.areaSETextField.getText()));
            record.setAreaWorst(Double.parseDouble(this.areaWorstTextField.getText().equals("") ? "0" : this.areaWorstTextField.getText()));

            record.setCompactnessMean(Double.parseDouble(this.compactnessMeanTextField.getText().equals("") ? "0" : this.compactnessMeanTextField.getText()));
            record.setCompactnessSe(Double.parseDouble(this.compactnessSETextField.getText().equals("") ? "0" : this.compactnessSETextField.getText()));
            record.setCompactnessWorst(Double.parseDouble(this.compactnessWorstTextField.getText().equals("") ? "0" : this.compactnessWorstTextField.getText()));

            record.setConcave_pointsMean(Double.parseDouble(this.concavePointsMeanTextField.getText().equals("") ? "0" : this.concavePointsMeanTextField.getText()));
            record.setConcave_pointsSe(Double.parseDouble(this.concavePointsSETextField.getText().equals("") ? "0" : this.concavePointsSETextField.getText()));
            record.setConcave_pointsWorst(Double.parseDouble(this.concavePointsWorstTextField.getText().equals("") ? "0" : this.concavePointsWorstTextField.getText()));

            record.setConcavityMean(Double.parseDouble(this.concavityMeanTextField.getText().equals("") ? "0" : this.concavityMeanTextField.getText()));
            record.setConcavitySe(Double.parseDouble(this.concavitySETextField.getText().equals("") ? "0" : this.concavitySETextField.getText()));
            record.setConcavityWorst(Double.parseDouble(this.concavityWorstTextField.getText().equals("") ? "0" : this.concavityWorstTextField.getText()));

            record.setFractal_dimensionMean(Double.parseDouble(this.fractaDimensionMeanTextField.getText().equals("") ? "0" : this.fractaDimensionMeanTextField.getText()));
            record.setFractal_dimensionSe(Double.parseDouble(this.fractaDimensionSETextField.getText().equals("") ? "0" : this.fractaDimensionSETextField.getText()));
            record.setFractal_dimensionWorst(Double.parseDouble(this.fractaDimensionWorstTextField.getText().equals("") ? "0" : this.fractaDimensionWorstTextField.getText()));

            record.setTextureMean(Double.parseDouble(this.textureMeanTextField.getText().equals("") ? "0" : this.textureMeanTextField.getText()));
            record.setTextureSe(Double.parseDouble(this.textureSETextField1.getText().equals("") ? "0" : this.textureSETextField1.getText()));
            record.setTextureWorst(Double.parseDouble(this.textureWorstTextField.getText().equals("") ? "0" : this.textureWorstTextField.getText()));

            record.setPerimeterMean(Double.parseDouble(this.perimeterMeanTextField.getText().equals("") ? "0" : this.perimeterMeanTextField.getText()));
            record.setPerimeterSe(Double.parseDouble(this.perimeterSETextField.getText().equals("") ? "0" : this.perimeterSETextField.getText()));
            record.setPerimeterWorst(Double.parseDouble(this.perimeterWorstTextField.getText().equals("") ? "0" : this.perimeterWorstTextField.getText()));

            record.setSmoothnessMean(Double.parseDouble(this.smoothnessMeanTextField.getText().equals("") ? "0" : this.smoothnessMeanTextField.getText()));
            record.setSmoothnessSe(Double.parseDouble(this.smoothnessSETextField.getText().equals("") ? "0" : this.smoothnessSETextField.getText()));
            record.setSmoothnessWorst(Double.parseDouble(this.smoothnessWorstTextField.getText().equals("") ? "0" : this.smoothnessWorstTextField.getText()));

            record.setSymmetryMean(Double.parseDouble(this.symmetryMeanTextField.getText().equals("") ? "0" : this.symmetryMeanTextField.getText()));
            record.setSymmetrySe(Double.parseDouble(this.symmetrySETextField.getText().equals("") ? "0" : this.symmetrySETextField.getText()));
            record.setSymmetryWorst(Double.parseDouble(this.symmetryWorstTextField.getText().equals("") ? "0" : this.symmetryWorstTextField.getText()));

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Zlý vstup! vstup nebol číselný.");

            alert.showAndWait();
            return null;
        }

        return record;
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
