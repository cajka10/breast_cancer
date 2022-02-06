package Services;


import org.apache.log4j.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;
import weka.core.neighboursearch.LinearNNSearch;

import java.util.Random;

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

    public Classifier buildMultilayerPerceptronClassifier(Instances trainDataset) {
        MultilayerPerceptron m = new MultilayerPerceptron();
        m.setLearningRate(0.1);
        m.setNominalToBinaryFilter(true);
        m.setNormalizeAttributes(true);
        try {
            m.buildClassifier(trainDataset);
        } catch (Exception ex) {
            LOGGER.debug("Nepodarilo sa natrenovať model MultilayerPerceptron");
        }
        return m;
    }

    public Classifier buildJ48Classifier(Instances trainDataset) {
        J48 j48 = new J48();
        j48.setReducedErrorPruning(true);
        j48.setBinarySplits(true);

        try {
            j48.buildClassifier(trainDataset);
        } catch (Exception ex) {
            LOGGER.debug("Nepodarilo sa natrenovať model J48");
        }
        return j48;
    }

    public Classifier buildKNearestNeighboursClassifier(Instances trainDataset) {
        Classifier knn = new IBk();

        try {
            knn.buildClassifier(trainDataset);
        } catch (Exception ex) {
            LOGGER.debug("Nepodarilo sa natrenovať model KNN");
        }
        return knn;
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

    public String getModelEvaluationWithCrossValidation(Classifier model, Instances train, Instances all) {
        try {
            Evaluation eval = new Evaluation(train);
            eval.crossValidateModel(model, all, 10, new Random(1));
            return eval.toSummaryString("", true);
        } catch (Exception e) {
            LOGGER.debug("Nepodarilo sa vyhodnotit model.");
        }
        return null;
    }

    public double[][] getConfusionMatrix(Classifier model, Instances train, Instances test) {
        try {
            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(model, test);
            double[][] cm = eval.confusionMatrix();
            return eval.confusionMatrix();
        } catch (Exception e) {
            LOGGER.debug("Nepodarilo sa vyhodnotit model.");
        }
        return null;
    }

    public void saveModel(Classifier model, String modelpath) {
        try {
            SerializationHelper.write(modelpath, model);
        } catch (Exception ex) {
            LOGGER.debug("Nepodarilo sa ulozit model.");
        }
    }
}

