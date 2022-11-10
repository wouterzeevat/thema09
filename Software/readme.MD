# Java wrapper
### Wouter Zeevat

This is the java wrapper. The goal of this program is to be able to classify new instances of the ASD quiz. You can classify new files or use the example file located at thema09\Software\ASD_Model\src\main\resources\unknown.arff

## Installing
Inder order to run the program. Java needs to be installed. https://www.java.com/en/download/

## Instructions
In order to run the program. You need to run the jar file throughout the command line.
First locate the working directory to the correct directory.

`cd yourlocation/thema09/Software/ASD_Model/build/libs`
or run the jar with the location ofcourse 

Then run the jar file with the correct parameters.
`java -jar ASD_Model-1.0.0-all.jar input.arff output.arff`

- input.arff: This parameter is the location to the input arff the program will classify. As told you could also use thema09\Software\ASD_Model\src\main\resources\unknown.arff. 

- output.arff: This parameter is the location of the output arff. The programm will write the classified instances in there!