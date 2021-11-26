package Services;

import Core.Entity.PatientRecord;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class ModelService {

    public ModelService() {
    }

    public void predict(PatientRecord record) {
        TestModel test = new TestModel();
        try {
            test.predict(record, "E:\\breast_cancer\\", "E:\\breast_cancer\\cancerData.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void train() throws Exception {
        MLModel mg = new MLModel();
        String temp = System.getProperty("user.dir") + "\\cancerData.arff";
        System.out.println("Nacitam data .ARFF");
        Instances dataset = mg.loadData(temp);

        dataset.randomize(new Debug.Random());
        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        Filter filter = new Normalize();
        Instances datasetnor = null;

        filter.setInputFormat(dataset);
        datasetnor = Filter.useFilter(dataset, filter);

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);

        System.out.println("Pred vybudovanim classifiera!");
        MultilayerPerceptron ann = (MultilayerPerceptron) mg.buildClassifier(traindataset);

        // Evaluate classifier with test dataset
        String evalSummary = mg.getModelEvaluation(ann, traindataset, testdataset);
        double[][] confusionMatrix = mg.getConfusionMatrix(ann, traindataset, testdataset);
        System.out.println("Evaluation summary: " + evalSummary);

        System.out.println("Evaluation summary with cross validation: "
                + mg.getModelEvaluationWithCrossValidation(ann, traindataset, dataset));

        System.out.println("Confusion matrix: ");

        for (int i = 0; i < confusionMatrix.length; i++) {
            for (int j = 0; j < confusionMatrix[i].length; j++) {
                System.out.print(confusionMatrix[i][j] + " ");
            }
            System.out.println();
        }

        mg.saveModel(ann, "E:\\breast_cancer\\Models\\MPModel.bin");
    }
}
