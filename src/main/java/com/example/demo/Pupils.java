package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Pupils {

    private List<PupilsItem> pupilsList;
    private FileManager fileManager;
    private boolean successFulRead;

    public Pupils() {
        this.pupilsList = new ArrayList<>();
        this.fileManager = new FileManager();
        try {
            readJsonFromFile();
            successFulRead = true;
        } catch (FileNotFoundException ioe) {
            ioe.printStackTrace();
        } catch (IOException ioe) {

            ioe.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();

        }

        if (!successFulRead)
            addMockData();

    }

    private void readJsonFromFile() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("jsonfile1.txt"));
        JSONArray arr = (JSONArray)obj;

        for (int i = 0; i < arr.size(); i++) {

            JSONObject jsonObject = (JSONObject) arr.get(i);

            String id = (String) jsonObject.get("id");
            String name = (String) jsonObject.get("name");
            String gender = (String) jsonObject.get("gender");
            String years = (String) jsonObject.get("years");
            String grade = (String) jsonObject.get("grade");
            String course = (String) jsonObject.get("course");

            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("gender: " + gender);
            System.out.println("years: " + years);
            System.out.println("grade: " + grade);
            System.out.println("course: " + course);

            PupilsItem item = new PupilsItem(id, name, gender, years, grade, course);
            addPupil(item);

        }
    }



    public List<PupilsItem> getPupilsList() {

        return pupilsList;
    }

    public List<PupilsItem> getPupils(String searchString) {
        if (searchString.equals(""))
            return pupilsList;

        ArrayList<PupilsItem> items = new ArrayList<>();
        for (PupilsItem item : pupilsList ) {

            if(item.getName().contains(searchString) || item.getId().contains(searchString) )
                items.add(item);
        }

        return items;
    }

    public PupilsItem getPupilById(String id) {

        for (PupilsItem item: pupilsList) {

            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }

    public void removePupilItem(String id) {

        for (Iterator<PupilsItem> it = pupilsList.iterator(); it.hasNext(); ) {

            PupilsItem item = it.next();
            if (id.equals(item.getId())) {

                it.remove();
            }
        }
    }

    public List <PupilsItem> updatePupil(String[] params) {

        String id = params[0];
        String name = params[1];

        for (Iterator<PupilsItem> it = pupilsList.iterator(); it.hasNext(); ) {

            PupilsItem item = it.next();
            if (item.getId().equals(id)) {

                item.setName(name);
            }
        }

        return pupilsList;
    }


    public boolean addPupil(PupilsItem item) {

        for (PupilsItem theItem: pupilsList) {

            if (theItem.getId().equals(item.getId())) {
                return false; // samma id får inte finnas
            }
        }
        pupilsList.add(item);
        return true;
    }

    private void addMockData() {

        PupilsItem.ContactInfo contactInfo = new PupilsItem().new ContactInfo("www", "070-555");
        PupilsItem item = new PupilsItem("111", "John", "man", "3", "G", "C1");
        addPupil(item);

        contactInfo = new PupilsItem().new ContactInfo("www", "070-555");
        item = new PupilsItem("112", "Lisa", "girl", "3", "G", "C2");
        addPupil(item);

        writeJsonToFile();

    }

    public void writeJsonToFile() {

        fileManager.createFile();
        fileManager.writeToFile("[");
        for (PupilsItem listItem: pupilsList) {

            JSONObject jsonObject = new JSONObject();
            putJsonData(jsonObject, listItem);
            fileManager.writeToFile(jsonObject, ",");
        }
        fileManager.writeToFile("]");
        fileManager.closeFile();

    }

    private void putJsonData(JSONObject jsonObject, PupilsItem item) {

        jsonObject.put("id", item.getId());
        jsonObject.put("name", item.getName());
        jsonObject.put("gender", item.getGender());
        jsonObject.put("years", item.getYears());
        jsonObject.put("grade", item.getGrade());
        jsonObject.put("course", item.getCourse());
    }


}
