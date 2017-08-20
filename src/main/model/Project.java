package main.model;


import main.view.controller.HomePageController;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static main.model.Project.ProjectString.*;

public class Project {
    private static String projectLocation;
    private static Project project;
    private JSONObject projectRoot;
    private JSONObject bcr_pv;
    private JSONObject bcr_diesel;
    private JSONObject ibcr;
    private JSONObject lcca_pv;
    private JSONObject lcca_diesel;

    private Project() {
        projectLocation = "";

        projectRoot = new JSONObject();

        bcr_pv = new JSONObject();
        bcr_diesel = new JSONObject();
        ibcr = new JSONObject();
        lcca_diesel = new JSONObject();
        lcca_pv = new JSONObject();

        projectRoot.put(BCR_DIESEL, bcr_diesel);
        projectRoot.put(BCR_PV, bcr_pv);
        projectRoot.put(IBCR, ibcr);
        projectRoot.put(LCCA_DIESEL, lcca_diesel);
        projectRoot.put(LCCA_PV, lcca_pv);

    }

    public static Project getInstance() {

        if ((project == null)) {
            project = new Project();
        }
        return project;
    }

    public static String getProjectLocation() {
        return projectLocation;
    }

    public static void setProjectLocation(String projectLocation) {
        Project.projectLocation = projectLocation;
    }

    public JSONObject getProjectRoot() {
        return projectRoot;
    }

    public void setProjectRoot(JSONObject projectRoot) {

        this.projectRoot = projectRoot;
    }

    public JSONObject getBcr_pv() {
        return (JSONObject) projectRoot.get(BCR_PV);
    }

    public void setBcr_pv(JSONObject bcr_pv) {
        this.bcr_pv = bcr_pv;
        projectRoot.put(BCR_PV, bcr_pv);
        saveFile();
    }

    public JSONObject getBcr_diesel() {
        return (JSONObject) projectRoot.get(BCR_DIESEL);
    }

    public void setBcr_diesel(JSONObject bcr_diesel) {
        this.bcr_diesel = bcr_diesel;
        projectRoot.put(BCR_DIESEL, bcr_diesel);
        saveFile();

    }

    public JSONObject getIbcr() {
        return (JSONObject) projectRoot.get(IBCR);
    }

    public void setIbcr(JSONObject ibcr) {
        this.ibcr = ibcr;
        projectRoot.put(IBCR, ibcr);
        saveFile();
    }

    public JSONObject getLcca_pv() {
        return (JSONObject) projectRoot.get(LCCA_PV);
    }

    public void setLcca_pv(JSONObject lcca_pv) {
        this.lcca_pv = lcca_pv;
        projectRoot.put(ProjectString.LCCA_PV, lcca_pv);
        saveFile();
    }

    public JSONObject getLcca_diesel() {
        return (JSONObject) projectRoot.get(LCCA_DIESEL);
    }

    public void setLcca_diesel(JSONObject lcca_diesel) {
        this.lcca_diesel = lcca_diesel;
        projectRoot.put(ProjectString.LCCA_DIESEL, lcca_diesel);
        saveFile();
    }

    private void saveFile() {
        if (!getProjectLocation().isEmpty()) {
            try {
                String lines = getProjectRoot().toJSONString();

                FileWriter fileWriter = null;

                fileWriter = new FileWriter(getProjectLocation());
                fileWriter.write(lines);
                fileWriter.close();

                System.out.println("saved");
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("not saved");
            System.out.println(getProjectLocation());
        }

    }

    public boolean openProject(String projectDirectory) {
        setProjectLocation(projectDirectory);

        JSONParser parser = new JSONParser();

        try (Scanner in = new Scanner(new FileReader(projectDirectory))) {
            StringBuilder sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            String lines = sb.toString();

            JSONObject proj = (JSONObject) parser.parse(lines);
            setProjectRoot(proj);

            System.out.println("opened");

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void createNewFile(String projectDirectory) {
        project = new Project();
        setProjectLocation(projectDirectory);
    }

    public class ProjectString {
        public static final String BCR_PV = "bcr_pv";
        public static final String BCR_DIESEL = "bcr_diesel";
        public static final String IBCR = "ibcr";
        public static final String LCCA_PV = "lcca_pv";
        public static final String LCCA_DIESEL = "lcca_diesel";
    }


}
