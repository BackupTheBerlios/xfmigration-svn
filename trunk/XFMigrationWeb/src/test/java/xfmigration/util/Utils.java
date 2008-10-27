package xfmigration.util;

import java.io.File;

public class Utils {

    /**
     * Ralative path to ontology folder
     */
    public static final String ONTOLOGY_FOLDER = "/ontologies/";

    /**
     * Determines current directory, where the program has been started.
     * 
     * @return a path to current program directory
     */
    public static String determineProgramBasePath() {
        File currentDir = new File(".");
        String currentDirURI = currentDir.toURI().toString();
        String currentDirString = currentDirURI.substring(0, currentDirURI.length() - 3);

        return currentDirString;
    }

    /**
     * Returns the absolute path to ontology folder
     * 
     * @return absolute path to ontology folder
     */
    public static String getOntologyFolder() {
        return determineProgramBasePath() + ONTOLOGY_FOLDER;
    }
}
