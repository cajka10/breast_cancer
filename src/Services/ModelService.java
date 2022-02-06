package Services;

import Core.Entity.Enum.ClassifierType;
import Core.Entity.Enum.TumorType;
import Core.Entity.PatientRecord;
import Core.Entity.TrainedClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class ModelService {

    private MLModel mg;

    public ModelService() {
        this.mg = new MLModel();
    }

    public String[] predict(PatientRecord record) {
        TestModel test = new TestModel();
        try {
//            TumorType type = TumorType.valueOf(test.predict(this.getMockPatientRecord(),
//                    "E:\\breast_cancer\\cancerData.arff",
//                    "E:\\breast_cancer\\Models\\MPModel.bin", ClassifierType.MP));
            String[] result = test.classify(this.getMockPatientRecord(),
                    "E:\\breast_cancer\\Models\\MPModel.bin");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[2];
    }

    public TrainedClassifier train(ClassifierType type) {
        String temp = System.getProperty("user.dir") + "\\cancerData.arff";
        System.out.println("Nacitam data .ARFF");
        Instances dataset = mg.loadData(temp);


        dataset.randomize(new Debug.Random());
        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        Filter filter = new Normalize();
        Instances datasetnor = null;

        //TODO logging
        try {
            filter.setInputFormat(dataset);
            datasetnor = Filter.useFilter(dataset, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);

        TrainedClassifier output = new TrainedClassifier();

        System.out.println("\nMultilayerPerceptron");
        switch (type){
            case MP:
                MultilayerPerceptron multilayerPerceptron = (MultilayerPerceptron)
                        mg.buildMultilayerPerceptronClassifier(traindataset);

                output.setClassifier(multilayerPerceptron);
                output.setEvaluation(this.evaluateClassifier(multilayerPerceptron, traindataset, testdataset));
                break;
            case J48:
                System.out.println("\nJ48");
                J48 j48 = (J48) mg.buildJ48Classifier(traindataset);
                output.setClassifier(j48);
                output.setEvaluation(this.evaluateClassifier(j48, traindataset, testdataset));
                break;
            case KNN:
                System.out.println("\nKNN");
                IBk ibk = (IBk) mg.buildKNearestNeighboursClassifier(traindataset);
                output.setClassifier(ibk);
                output.setEvaluation(this.evaluateClassifier(ibk, traindataset, testdataset));
        }

        return output;
    }

    public String evaluateClassifier(Classifier ann, Instances traindataset, Instances testdataset) {
        // Evaluate classifier with test dataset
        double[][] confusionMatrix = mg.getConfusionMatrix(ann, traindataset, testdataset);
        StringBuilder builder = new StringBuilder();
        builder.append("Evaluation summary");
        builder.append(mg.getModelEvaluation(ann, traindataset, testdataset));
        builder.append("\n");

        System.out.println("Evaluation summary: " + mg.getModelEvaluation(ann, traindataset, testdataset));

        builder.append("Confusion matrix: ");

        System.out.println("Confusion matrix: ");
        for (int i = 0; i < confusionMatrix.length; i++) {
            for (int j = 0; j < confusionMatrix[i].length; j++) {
                builder.append(confusionMatrix[i][j] + " \n");
                System.out.print(confusionMatrix[i][j] + " ");
            }
            System.out.println();
        }

        return builder.toString();
    }

    public void saveModel(Classifier classifier, String path) {
//        mg.saveModel( classifier,"E:\\breast_cancer\\Models\\J48Model.bin");
        mg.saveModel(classifier, path);
    }

    private PatientRecord getMockPatientRecord() {
        PatientRecord record = new PatientRecord();

        record.setRadiusMean(17.99);
        record.setTextureMean(10.38);
        record.setPerimeterMean(122.8);
        record.setAreaMean(1001);
        record.setSmoothnessMean(0.1184);
        record.setCompactnessMean(0.2776);
        record.setConcave_pointsMean(0.3001);
        record.setSymmetryMean(0.1471);
        record.setFractal_dimensionMean(0.2419);
        record.setConcavityMean(0.07871);

        record.setRadiusSe(1.095);
        record.setTextureSe(0.9053);
        record.setPerimeterSe(8.589);
        record.setAreaSe(153.4);
        record.setSmoothnessSe(0.006399);
        record.setCompactnessSe(0.04904);
        record.setConcavitySe(0.05373);
        record.setConcave_pointsSe(0.01587);
        record.setSymmetrySe(0.03003);
        record.setFractal_dimensionSe(0.006193);

        record.setRadiusWorst(25.38);
        record.setTextureWorst(25.38);
        record.setPerimeterWorst(184.6);
        record.setAreaWorst(2019);
        record.setSmoothnessWorst(0.1622);
        record.setCompactnessWorst(0.6656);
        record.setConcavityWorst(0.7119);
        record.setConcave_pointsWorst(0.2654);
        record.setSymmetryWorst(0.4601);
        record.setFractal_dimensionWorst(0.1189);
        record.setTumorType(TumorType.BENIGM);
        return record;
    }
}
