package com.example.demo;

import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private FileWriter file;
    private static final String FILENAME = "jsonfile1.txt";

    public FileManager() {

    }

    /**
     * Skapar ett nytt filobject
     */
    public boolean createFile() {

        try {
            file = new FileWriter(FILENAME);
            return true;
        } catch (IOException ioe) {

            ioe.printStackTrace();
        }

        return false;
    }

    public boolean closeFile() {

        try {
            file.close();
            return true;
        } catch (IOException ioe) {

            ioe.printStackTrace();
        } catch (NullPointerException ne) {

            ne.printStackTrace();
        }

        return false;
    }

    public void writeToFile(JSONObject jsonObject, String extra) {

        try {
            file.write(jsonObject.toJSONString() + extra);
            System.out.println("Lyckades kopiera JSON till fil...");
            System.out.println("\nJSON Object: " + jsonObject);
        } catch (IOException ioe) {

            ioe.printStackTrace();
        }
    }

    public void writeToFile(String string) {

        try {
            file.write(string);

        } catch (IOException ioe) {

            ioe.printStackTrace();
        }
    }

}