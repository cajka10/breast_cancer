package Services;

import Entity.PatientRecord;
import Repository.PatientRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//TODO dorobiť Edit záznamov, loggovanie, odomykanie tabov
public class MainService {
    private ObservableList<ObservableList> data;
    private PatientRepository patientRepository;
    static final Logger logger = Logger.getLogger(MainService.class.getName());

    private String PATIENT_TABLE = "PATIENT";
    private String PATIENT_RECORD_TABLE = "PATIENT_RECORD";
    public MainService() {
        this.patientRepository = new PatientRepository();
    }

    public TableView<PatientRecord> getPatients() {
        return patientRepository.getPatientColumns(PATIENT_TABLE);
    }
    public TableView<PatientRecord> getPatientsRecords() {
        return patientRepository.getPatientColumns(PATIENT_RECORD_TABLE);
    }

    public void importData(Stage stage) {
        List<String[]> r = new ArrayList<>();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose csv file.");
        File file = fileChooser.showOpenDialog(stage);

        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // custom separator
        if (file == null) {
            return;
        }

        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(file))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()) {
            r = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String record[] :
                r) {
            PatientRecord patient = new PatientRecord();

            patient.setRadiusMean(Double.valueOf(record[0]));
            patient.setTextureMean(Double.valueOf(record[1]));
            patient.setPerimeterMean(Double.valueOf(record[2]));
            patient.setAreaMean(Double.valueOf(record[3]));
            patient.setSmoothnessMean(Double.valueOf(record[4]));
            patient.setCompactnessMean(Double.valueOf(record[5]));
            patient.setConcavityMean(Double.valueOf(record[6]));
            patient.setConcave_pointsMean(Double.valueOf(record[7]));
            patient.setSymmetryMean(Double.valueOf(record[8]));
            patient.setFractal_dimensionMean(Double.valueOf(record[9]));

            patient.setRadiusSe(Double.valueOf(record[10]));
            patient.setTextureSe(Double.valueOf(record[11]));
            patient.setPerimeterSe(Double.valueOf(record[12]));
            patient.setAreaSe(Double.valueOf(record[13]));
            patient.setSmoothnessSe(Double.valueOf(record[14]));
            patient.setCompactnessSe(Double.valueOf(record[15]));
            patient.setConcavitySe(Double.valueOf(record[16]));
            patient.setConcave_pointsSe(Double.valueOf(record[17]));
            patient.setSymmetrySe(Double.valueOf(record[18]));
            patient.setFractal_dimensionSe(Double.valueOf(record[19]));

            patient.setRadiusWorst(Double.valueOf(record[20]));
            patient.setTextureWorst(Double.valueOf(record[21]));
            patient.setPerimeterWorst(Double.valueOf(record[22]));
            patient.setAreaWorst(Double.valueOf(record[23]));
            patient.setSmoothnessWorst(Double.valueOf(record[24]));
            patient.setCompactnessWorst(Double.valueOf(record[25]));
            patient.setConcavityWorst(Double.valueOf(record[26]));
            patient.setConcave_pointsWorst(Double.valueOf(record[27]));
            patient.setSymmetryWorst(Double.valueOf(record[28]));
            patient.setFractal_dimensionWorst(Double.valueOf(record[29]));

            this.patientRepository.addPatient(patient, PATIENT_RECORD_TABLE );
        }
    }

    public void addPatientRecord(PatientRecord record) {
        if (this.patientRepository.addPatient(record, PATIENT_TABLE) > 0){
            this.logger.debug("Patient succesfully added.");
        }else{
            this.logger.debug("Error - Patient not added.");
        }
    }

    public PatientRecord getPatientById(int patientId) {
        return this.patientRepository.getPatientById(patientId, this.PATIENT_TABLE);
    }

    public void editPatient(PatientRecord record) {
        if (this.patientRepository.editPatient(record, this.PATIENT_TABLE) > 0){
            this.logger.debug("Patient: " + record.getRecordId() + " succesfully updated.");
        }else{
            this.logger.debug("Error - Patient: " + record.getRecordId() + " not updated.");
        }
    }
}
