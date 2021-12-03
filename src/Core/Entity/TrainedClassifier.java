package Core.Entity;

import weka.classifiers.Classifier;

public class TrainedClassifier {
    Classifier classifier;
    String evaluation;

    public TrainedClassifier() {
    }

    public TrainedClassifier(Classifier classifier, String evaluation) {
        this.classifier = classifier;
        this.evaluation = evaluation;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
