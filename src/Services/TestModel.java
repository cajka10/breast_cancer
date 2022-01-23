package Services;

import Core.Entity.Enum.ClassifierType;
import Core.Entity.PatientRecord;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class TestModel {
    private MLModel mg;

    public TestModel() {
        this.mg = new MLModel();
    }


    public String predict(PatientRecord record,  String datasetPath, String modelPath, ClassifierType type) throws Exception {
        Instances dataset = mg.loadData(datasetPath);
        String classname[] = new String[2];
        Filter filter = new Normalize();

        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        dataset.randomize(new Debug.Random(1));

        //Normalize dataset
        filter.setInputFormat(dataset);
        Instances datasetnor = Filter.useFilter(dataset, filter);

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);

        if (type == ClassifierType.MP) {
            // build classifier with train dataset
            MultilayerPerceptron multilayerPerceptron = (MultilayerPerceptron) mg.buildMultilayerPerceptronClassifier(traindataset);

            // Evaluate classifier with test dataset
            System.out.println("Evaluation: " + mg.getModelEvaluation(multilayerPerceptron, traindataset, testdataset));
            //Save model
            mg.saveModel(multilayerPerceptron, modelPath);

            //classifiy a single instance
            ModelClassifier cls = new ModelClassifier();

            Instances mfp = Filter.useFilter(cls.createInstance(record), filter);
            classname = cls.classifiy(mfp, modelPath);

            System.out.println("\n****************************************************");
            System.out.println("\n The class name for the instance is  " + classname);
            System.out.println("\n****************************************************");
        } else if (type == ClassifierType.J48) {
            J48 model = (J48) mg.buildJ48Classifier(traindataset);

            System.out.println("Evaluation: " + mg.getModelEvaluation(model, traindataset, testdataset));

            mg.saveModel(model, modelPath);

            //classifiy a single instance
            ModelClassifier cls = new ModelClassifier();

            Instances mfp = Filter.useFilter(cls.createInstance(record), filter);
            classname = cls.classifiy(mfp, datasetPath);
            System.out.println("\n****************************************************");
            System.out.println("\n The class name for the instance is  " + classname);
            System.out.println("\n****************************************************");
        }

        return classname[0];
    }

    public String[] classify(PatientRecord record, String modelPath){
        ModelClassifier cls = new ModelClassifier();
        Filter filter = new Normalize();

        Instances dataset = cls.createInstance(record);
        String classname[] = new String[2];

        try {
            filter.setInputFormat(dataset);

            classname = cls.classifiy(dataset, modelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n****************************************************");
        System.out.println("\n The class name for the instance is  " + classname);
        System.out.println("\n****************************************************");
        return classname;
    }

}
