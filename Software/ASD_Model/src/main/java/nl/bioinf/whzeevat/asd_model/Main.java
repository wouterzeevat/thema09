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
    private static String helpMessage = "java -jar ASD_Model-1.0.0-all.jar input_file_loc.arff output_file_loc.arff";

    public static void main(String[] args) {

        Main main = new Main();

        if (args.length < 2) {
            System.out.println("The command needs more arguments!");
            System.out.println(helpMessage);
            return;
        }

        String input = args[0];
        String output = args[1];

        try {

            AbstractClassifier classifier = main.readClassifier();
            System.out.println("Successfully loaded " + classifier.toString().split(":")[0] + " classifier!");
            Instances instances = ArffFile.loadArff(input);
            Instances newInstances = main.classifyNewInstance(classifier, instances);
            ArffFile.saveArff(newInstances, output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Instances classifyNewInstance(AbstractClassifier model, Instances unknownInstances) throws Exception {

        Instances labeled = new Instances(unknownInstances);
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double clsLabel = model.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(clsLabel);

            String format_label = "no";
            if (clsLabel > 0) format_label = "yes";
            labels.add(format_label);
        }
        System.out.println("Added unknown labels -> " + labels.toString());
        return labeled;
    }

    public AbstractClassifier readClassifier() throws Exception {
        System.out.println("Loading model...");
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("simplelogistic.model");
            return (AbstractClassifier) SerializationHelper.read(file);
        } catch (Exception e) {
            System.out.println("Model not found!");
        }
        return null;
    }

    public Main getMain() {
        return main;
    }

}
