package Data;

import Data.PCClass.PCClassDesc;
import Data.PCRace.PCRaceDesc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class oglDescription {
    //This class holds information from the db,
    // changes it from resultSet to and array list,
    // DB is accessed using dataAccess class


    ArrayList<PCRaceDesc> raceDescs = new ArrayList<>();
    ArrayList<PCClassDesc> classDescs = new ArrayList<>();
    ArrayList<PCBackGDesc> backGDescs = new ArrayList<>();
    Integer classColsCount;
    Integer raceColsCount;
    Integer backGColsCount;
    ArrayList<String> classColsNames = new ArrayList<>();
    ArrayList<String> raceColsNames = new ArrayList<>();
    ArrayList<String> backGColsNames = new ArrayList<>();


    public oglDescription() {//Constructor
        //Fill the arrays with description objects
        getRaceInfo();
        getClassInfo();
        getBackGInfo();

        //Get column numbers(also stores names in strings defined above)
        classColsCount = getClassCols();
        raceColsCount = getRaceCols();
        backGColsCount = getBackGCols();
    }
    private void getRaceInfo() {
        //race information
        ResultSet results = dataAccess.getAllRaces();//DataAccess functions to get race table as resultset
        try {//changes the result set, to the stack of raceDesctiptions
            while (results.next()) {
                PCRaceDesc temp = new PCRaceDesc();//Put each column from data base, to a temp object

                temp.setName(results.getString("name"));
                temp.setAgeD(results.getString("age"));
                temp.setAlignmentD(results.getString("alignment"));
                temp.setSizeD(results.getString("size"));
                temp.setSpeedD(results.getString("speed"));
                temp.setFeatures(results.getString("features"));
                temp.setLanguageD(results.getString("languages"));
                temp.setSubraces(results.getString("subraces"));

                raceDescs.add(temp);//put object in array
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getClassInfo() {
        //Class information
        ResultSet results = dataAccess.getAllClasses();
        try {
            ResultSetMetaData meta =  results.getMetaData();
            Integer count = meta.getColumnCount();

            while (results.next()) {
                PCClassDesc temp = new PCClassDesc();

                temp.setName(results.getString("name"));
                temp.setHitDice(results.getString("hitDice"));
                temp.setArmor(results.getString("armor"));
                temp.setWeapons(results.getString("weapons"));
                temp.setTools(results.getString("tools"));
                temp.setSavingThrows(results.getString("savingThrows"));
                temp.setSkills(results.getString("skills"));
                temp.setEquipment(results.getString("equipment"));
                temp.setFeatures(results.getString("features"));
                temp.setAsi(results.getString("asi"));
                temp.setSubclasses(results.getString("subclasses"));

                classDescs.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getBackGInfo() {
        //Background information
        ResultSet results = dataAccess.getAllBackgrounds();
        try {
            while (results.next()) {
                PCBackGDesc temp = new PCBackGDesc();

                temp.setName(results.getString("name"));
                temp.setDescription(results.getString("description"));
                temp.setSkills(results.getString("skills"));
                temp.setLanguages(results.getString("languages"));
                temp.setEquipment(results.getString("equipment"));
                temp.setFeature(results.getString("feature"));

                backGDescs.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Integer getClassCols() {
        ResultSetMetaData results = dataAccess.getClassColumns();
        int count = 1;
        try {
            while (true) {
                assert results != null;
                if (!(count < results.getColumnCount())) break;
                classColsNames.add(results.getColumnName(count));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classColsNames.size();
    }

    private Integer getRaceCols() {
        ResultSetMetaData results = dataAccess.getRaceColumns();
        int count = 1;
        try {
            while (true) {
                assert results != null;
                if (!(count < results.getColumnCount())) break;
                raceColsNames.add(results.getColumnName(count));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raceColsNames.size();
    }

    private Integer getBackGCols() {
        ResultSetMetaData results = dataAccess.getBackGColumns();
        Integer count = 1;
        try {
            while (true) {
                assert results != null;
                if (!(count < results.getColumnCount())) break;
                backGColsNames.add(results.getColumnName(count));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return backGColsNames.size();
    }

    //Getters and setters
    public ArrayList<PCRaceDesc> getRaceDescs() {return raceDescs;}
    public ArrayList<PCClassDesc> getClassDescs() {return classDescs;}
    public ArrayList<PCBackGDesc> getBackGDescs() {return backGDescs;}

    public ArrayList<String> getClassColsNames() {return classColsNames;}
    public ArrayList<String> getRaceColsNames() {return raceColsNames;}
    public ArrayList<String> getBackGColsNames() {return backGColsNames;}
}
