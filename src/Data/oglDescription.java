package Data;

import Data.PCClass.PCClass;
import Data.PCClass.PCClassDesc;
import Data.PCRace.PCRaceDesc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class oglDescription {
    //This class holds information from the db,
    // changes it from resultSet to and array list,
    // DB is accessed using dataAccess class


    ArrayList<PCRaceDesc> raceDescs = new ArrayList<PCRaceDesc>();
    ArrayList<PCClassDesc> classDescs = new ArrayList<PCClassDesc>();

    public oglDescription() {//Constructor
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

        //Class information
        results = dataAccess.getAllClasses();
        try {
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

    //Getters and setters
    public ArrayList<PCRaceDesc> getRaceDescs() {
        return raceDescs;
    }
    public ArrayList<PCClassDesc> getClassDescs() {return classDescs;}
}
