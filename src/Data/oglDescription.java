package Data;

import Data.PCRace.PCRaceDesc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class oglDescription {


    ArrayList<PCRaceDesc> raceDescs = new ArrayList<PCRaceDesc>();

    public oglDescription() {
        ResultSet raceSet = dataAccess.getAllRaces();//DataAccess functions to get race table as resultset
        try {//changes the result set, to the stack of raceDesctiptions
            while (raceSet.next()) {
                PCRaceDesc temp = new PCRaceDesc();//Put each column from data base, to a temp object
                temp.setName(raceSet.getString("name"));
                temp.setAgeD(raceSet.getString("age"));
                temp.setAlignmentD(raceSet.getString("alignment"));
                temp.setSizeD(raceSet.getString("size"));
                temp.setSpeedD(raceSet.getString("speed"));
                temp.setFeatures(raceSet.getString("features"));
                temp.setLanguageD(raceSet.getString("languages"));
                temp.setSubraces(raceSet.getString("subraces"));

                raceDescs.add(temp);//put object in array
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Getters and setters
    public ArrayList<PCRaceDesc> getRaceDescs() {
        return raceDescs;
    }
}
