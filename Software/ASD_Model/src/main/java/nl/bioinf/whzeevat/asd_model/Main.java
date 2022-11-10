package nl.bioinf.whzeevat.asd_model;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    private Main main;

    public static void main(String[] args) {

        Main main = new Main();
        String input = "src/main/resources/unknown.arff";
        String output = "src/main/resources/results.arff";

        try {
            AbstractClassifier classifier = main.readClassifier();
            Instances instances = ArffFile.loadArff(input);
            Instances newInstances = main.classifyNewInstance(classifier, instances);
            ArffFile.saveArff(newInstances, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Instances classifyNewInstance(AbstractClassifier model, Instances unknownInstances) throws Exception {

        Instances labeled = new Instances(unknownInstances);
        System.out.println("Added unknown labels -> ");
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double clsLabel = model.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(clsLabel);

            String format_label = "no";
            if (clsLabel > 0) format_label = "yes";
            System.out.println(format_label);
        }
        return labeled;
    }

    public AbstractClassifier readClassifier() throws Exception {
        //InputStream input = getClass().getClassLoader().getResourceAsStream("simplelogistic.model");
        return (AbstractClassifier) SerializationHelper.read(new FileInputStream("data/simplelogistic.model"));
    }

    public Main getMain() {
        return main;
    }

}
