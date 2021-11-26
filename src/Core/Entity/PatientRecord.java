package Core.Entity;

import Core.Entity.Enum.TumorType;

public class PatientRecord {

    private int recordId;
    private double radiusMean;
    private double textureMean;
    private double perimeterMean;
    private double areaMean;
    private double smoothnessMean;
    private double compactnessMean;
    private double concavityMean;
    private double concave_pointsMean;
    private double symmetryMean;
    private double fractal_dimensionMean;

    private double radiusSe;
    private double textureSe;
    private double perimeterSe;
    private double areaSe;
    private double smoothnessSe;
    private double compactnessSe;
    private double concavitySe;
    private double concave_pointsSe;
    private double symmetrySe;
    private double fractal_dimensionSe;

    private double radiusWorst;
    private double textureWorst;
    private double perimeterWorst;
    private double areaWorst;
    private double smoothnessWorst;
    private double compactnessWorst;
    private double concavityWorst;
    private double concave_pointsWorst;
    private double symmetryWorst;
    private double fractal_dimensionWorst;

    private TumorType tumorType;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public double getRadiusMean() {
        return radiusMean;
    }

    public void setRadiusMean(double radiusMean) {
        this.radiusMean = radiusMean;
    }

    public double getTextureMean() {
        return textureMean;
    }

    public void setTextureMean(double textureMean) {
        this.textureMean = textureMean;
    }

    public double getPerimeterMean() {
        return perimeterMean;
    }

    public void setPerimeterMean(double perimeterMean) {
        this.perimeterMean = perimeterMean;
    }

    public double getAreaMean() {
        return areaMean;
    }

    public void setAreaMean(double areaMean) {
        this.areaMean = areaMean;
    }

    public double getSmoothnessMean() {
        return smoothnessMean;
    }

    public void setSmoothnessMean(double smoothnessMean) {
        this.smoothnessMean = smoothnessMean;
    }

    public double getCompactnessMean() {
        return compactnessMean;
    }

    public void setCompactnessMean(double compactnessMean) {
        this.compactnessMean = compactnessMean;
    }

    public double getConcavityMean() {
        return concavityMean;
    }

    public void setConcavityMean(double concavityMean) {
        this.concavityMean = concavityMean;
    }

    public double getConcave_pointsMean() {
        return concave_pointsMean;
    }

    public void setConcave_pointsMean(double concave_pointsMean) {
        this.concave_pointsMean = concave_pointsMean;
    }

    public double getSymmetryMean() {
        return symmetryMean;
    }

    public void setSymmetryMean(double symmetryMean) {
        this.symmetryMean = symmetryMean;
    }

    public double getFractal_dimensionMean() {
        return fractal_dimensionMean;
    }

    public void setFractal_dimensionMean(double fractal_dimensionMean) {
        this.fractal_dimensionMean = fractal_dimensionMean;
    }

    public double getRadiusSe() {
        return radiusSe;
    }

    public void setRadiusSe(double radiusSe) {
        this.radiusSe = radiusSe;
    }

    public double getTextureSe() {
        return textureSe;
    }

    public void setTextureSe(double textureSe) {
        this.textureSe = textureSe;
    }

    public double getPerimeterSe() {
        return perimeterSe;
    }

    public void setPerimeterSe(double perimeterSe) {
        this.perimeterSe = perimeterSe;
    }

    public double getAreaSe() {
        return areaSe;
    }

    public void setAreaSe(double areaSe) {
        this.areaSe = areaSe;
    }

    public double getSmoothnessSe() {
        return smoothnessSe;
    }

    public void setSmoothnessSe(double smoothnessSe) {
        this.smoothnessSe = smoothnessSe;
    }

    public double getCompactnessSe() {
        return compactnessSe;
    }

    public void setCompactnessSe(double compactnessSe) {
        this.compactnessSe = compactnessSe;
    }

    public double getConcavitySe() {
        return concavitySe;
    }

    public void setConcavitySe(double concavitySe) {
        this.concavitySe = concavitySe;
    }

    public double getConcave_pointsSe() {
        return concave_pointsSe;
    }

    public void setConcave_pointsSe(double concave_pointsSe) {
        this.concave_pointsSe = concave_pointsSe;
    }

    public double getSymmetrySe() {
        return symmetrySe;
    }

    public void setSymmetrySe(double symmetrySe) {
        this.symmetrySe = symmetrySe;
    }

    public double getFractal_dimensionSe() {
        return fractal_dimensionSe;
    }

    public void setFractal_dimensionSe(double fractal_dimensionSe) {
        this.fractal_dimensionSe = fractal_dimensionSe;
    }

    public double getRadiusWorst() {
        return radiusWorst;
    }

    public void setRadiusWorst(double radiusWorst) {
        this.radiusWorst = radiusWorst;
    }

    public double getTextureWorst() {
        return textureWorst;
    }

    public void setTextureWorst(double textureWorst) {
        this.textureWorst = textureWorst;
    }

    public double getPerimeterWorst() {
        return perimeterWorst;
    }

    public void setPerimeterWorst(double perimeterWorst) {
        this.perimeterWorst = perimeterWorst;
    }

    public double getAreaWorst() {
        return areaWorst;
    }

    public void setAreaWorst(double areaWorst) {
        this.areaWorst = areaWorst;
    }

    public double getSmoothnessWorst() {
        return smoothnessWorst;
    }

    public void setSmoothnessWorst(double smoothnessWorst) {
        this.smoothnessWorst = smoothnessWorst;
    }

    public double getCompactnessWorst() {
        return compactnessWorst;
    }

    public void setCompactnessWorst(double compactnessWorst) {
        this.compactnessWorst = compactnessWorst;
    }

    public double getConcavityWorst() {
        return concavityWorst;
    }

    public void setConcavityWorst(double concavityWorst) {
        this.concavityWorst = concavityWorst;
    }

    public double getConcave_pointsWorst() {
        return concave_pointsWorst;
    }

    public void setConcave_pointsWorst(double concave_pointsWorst) {
        this.concave_pointsWorst = concave_pointsWorst;
    }

    public double getSymmetryWorst() {
        return symmetryWorst;
    }

    public void setSymmetryWorst(double symmetryWorst) {
        this.symmetryWorst = symmetryWorst;
    }

    public double getFractal_dimensionWorst() {
        return fractal_dimensionWorst;
    }

    public void setFractal_dimensionWorst(double fractal_dimensionWorst) {
        this.fractal_dimensionWorst = fractal_dimensionWorst;
    }

    public TumorType getTumorType() {
        return tumorType;
    }

    public void setTumorType(TumorType tumorType) {
        this.tumorType = tumorType;
    }
}
