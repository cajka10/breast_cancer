package Services;

import Core.Entity.PatientRecord;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class TestModel {
    public String predict(PatientRecord record, String modelPath, String datasetPath) throws Exception {

        MLModel mg = new MLModel();

        Instances dataset = mg.loadData(datasetPath);

        Filter filter = new Normalize();

        // divide dataset to train dataset 80% and test dataset 20%
        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        dataset.randomize(new Debug.Random(1));// if you comment this line the accuracy of the model will be droped from 96.6% to 80%

        //Normalize dataset
        filter.setInputFormat(dataset);
        Instances datasetnor = Filter.useFilter(dataset, filter);

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);

        // build classifier with train dataset
        MultilayerPerceptron ann = (MultilayerPerceptron) mg.buildClassifier(traindataset);

        // Evaluate classifier with test dataset
        String evalSummary = mg.getModelEvaluation(ann, traindataset, testdataset);
        System.out.println("Evaluation: " + evalSummary);

        //Save model
        mg.saveModel(ann, modelPath);

        //classifiy a single instance
        ModelClassifier cls = new ModelClassifier();

        String classname =cls.classifiy(Filter.useFilter(cls.createInstance(record), filter), datasetPath);
        System.out.println("\n The class name for the instance with petallength = 1.6 and petalwidth =0.2 is  " +classname);

        return classname;

    }
}
