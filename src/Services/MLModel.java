package Services;


import org.apache.log4j.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

public class MLModel {
    static final Logger LOGGER = Logger.getLogger(LoginService.class.getName());

    public Instances loadData(String path) {
        Instances dataset = null;
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(path);
            dataset = source.getDataSet();
            if (dataset.classIndex() == -1)
                dataset.setClassIndex(dataset.numAttributes() - 1);
        } catch (Exception ex) {
            this.LOGGER.debug("Nepodarilo sa načítať dáta");
        }
        return dataset;
    }

    public Classifier buildClassifier(Instances traindataset) {
        MultilayerPerceptron m = new MultilayerPerceptron();
        m.setLearningRate(0.1);
        try {
            m.buildClassifier(traindataset);
        } catch (Exception ex) {
            LOGGER.debug("Nepodarilo sa natrenovať model.");
        }
        return m;
    }

    public String getModelEvaluation(Classifier model, Instances train, Instances test) {
        Evaluation eval = null;
        try {
            eval = new Evaluation(train);
            eval.evaluateModel(model, test);
        } catch (Exception e) {
            LOGGER.debug("Nepodarilo sa vyhodnotit model.");
        }
        return eval.toSummaryString("", true);
    }

    public void saveModel(Classifier model, String modelpath) {
        try {
            SerializationHelper.write(modelpath, model);
        } catch (Exception ex) {
            LOGGER.debug("Nepodarilo sa ulozit model.");
        }
    }
}

