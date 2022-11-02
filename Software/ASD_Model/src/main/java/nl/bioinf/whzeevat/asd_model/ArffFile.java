package nl.bioinf.whzeevat.asd_model;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArffFile {

    public static Instances loadArff(String datafile) throws IOException {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(datafile);
            Instances data = source.getDataSet();
            // setting class attribute if the data format does not provide this information
            // For example, the XRFF format saves the class attribute information as well
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("could not read from file");
        }
    }

    public static void saveArff(Instances instances, String toWhere) throws IOException {
        Path path = Paths.get(toWhere);
        Files.writeString(path, instances.toString());
    }

}
