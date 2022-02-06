package Core.Entity;

public class PatientInfo {
    private String name;
    private String surName;
    private String birthId;
    private String birthDate;

    public PatientInfo() {
        this.name = "";
        this.surName = "";
        this.birthId = "";
    }

    public PatientInfo(String name, String surName, String birthId, String birthDate) {
        this.name = name;
        this.surName = surName;
        this.birthId = birthId;
        this.birthDate = birthDate;
    }
}
