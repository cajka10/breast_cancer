package Services;

import Core.Entity.PatientRecord;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.util.ArrayList;

public class ModelClassifier {

    private PatientRecord patientRecord;
    private ArrayList attributes;
    private ArrayList classVal;
    private Instances dataRaw;

    //radius_mean,texture_mean,perimeter_mean,area_mean,smoothness_mean,compactness_mean,concavity_mean,
    // concave_points_mean,symmetry_mean,fractal_dimension_mean,radius_se,texture_se,perimeter_se,area_se,
    // smoothness_se,compactness_se,concavity_se,concave_points_se,symmetry_se,fractal_dimension_se,radius_worst,
    // texture_worst,perimeter_worst,area_worst,smoothness_worst,compactness_worst,concavity_worst,
    // concave points_worst,symmetry_worst,fractal_dimension_worst,diagnosis
    public ModelClassifier() {
        this.patientRecord = new PatientRecord();

        attributes = new ArrayList();
        this.setAttributes();

        classVal = new ArrayList();
        classVal.add("B");
        classVal.add("M");


        attributes.add(new Attribute("class", classVal));
        dataRaw = new Instances("TestInstances", attributes, 0);
        dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
    }

    private void setAttributes() {
        attributes.add(new Attribute("radius_mean"));
        attributes.add(new Attribute("texture_mean"));
        attributes.add(new Attribute("perimeter_mean"));
        attributes.add(new Attribute("area_mean"));
        attributes.add(new Attribute("smoothness_mean"));
        attributes.add(new Attribute("compactness_mean"));
        attributes.add(new Attribute("concavity_mean"));
        attributes.add(new Attribute("concave_points_mean"));
        attributes.add(new Attribute("symmetry_mean"));
        attributes.add(new Attribute("fractal_dimension_mean"));

        attributes.add(new Attribute("radius_se"));
        attributes.add(new Attribute("texture_se"));
        attributes.add(new Attribute("perimeter_se"));
        attributes.add(new Attribute("area_se"));
        attributes.add(new Attribute("smoothness_se"));
        attributes.add(new Attribute("compactness_se"));
        attributes.add(new Attribute("concavity_se"));
        attributes.add(new Attribute("concave_points_se"));
        attributes.add(new Attribute("symmetry_se"));
        attributes.add(new Attribute("fractal_dimension_se"));

        attributes.add(new Attribute("radius_worst"));
        attributes.add(new Attribute("texture_worst"));
        attributes.add(new Attribute("perimeter_worst"));
        attributes.add(new Attribute("area_worst"));
        attributes.add(new Attribute("smoothness_worst"));
        attributes.add(new Attribute("compactness_worst"));
        attributes.add(new Attribute("concavity_worst"));
        attributes.add(new Attribute("concave_points_worst"));
        attributes.add(new Attribute("symmetry_worst"));
        attributes.add(new Attribute("fractal_dimension_worst"));
    }

    public Instances createInstance(PatientRecord record) {
        dataRaw.clear();
        double[] instanceValue1 = getInstancesFromPatientRecord(record);
        dataRaw.add(new DenseInstance(1.0, instanceValue1));
        return dataRaw;
    }

    private double[] getInstancesFromPatientRecord(PatientRecord record) {
        double[] outpurArray
                = new double[]{record.getRadiusMean(),
                record.getTextureMean(),
                record.getPerimeterMean(),
                record.getAreaMean(),
                record.getSmoothnessMean(),
                record.getCompactnessMean(),
                record.getConcavityMean(),
                record.getConcave_pointsMean(),
                record.getSymmetryMean(),
                record.getFractal_dimensionMean(),
                record.getRadiusSe(),
                record.getTextureSe(),
                record.getPerimeterSe(),
                record.getAreaSe(),
                record.getSmoothnessSe(),
                record.getCompactnessSe(),
                record.getConcavitySe(),
                record.getConcave_pointsSe(),
                record.getSymmetrySe(),
                record.getFractal_dimensionSe(),
                record.getRadiusWorst(),
                record.getTextureWorst(),
                record.getPerimeterWorst(),
                record.getAreaWorst(),
                record.getSmoothnessWorst(),
                record.getCompactnessWorst(),
                record.getConcavityWorst(),
                record.getConcave_pointsWorst(),
                record.getSymmetryWorst(),
                record.getFractal_dimensionWorst()};
        return outpurArray;
    }

    public String classifiy(Instances insts, String path) {
        String result = "Not classified!!";
        Classifier cls = null;
        try {
            cls = (MultilayerPerceptron) SerializationHelper.read(path);
            result = (String) classVal.get((int) cls.classifyInstance(insts.firstInstance()));
        } catch (Exception ex) {

        }
        return result;
    }

    public Instances getInstance() {
        return dataRaw;
    }

}
