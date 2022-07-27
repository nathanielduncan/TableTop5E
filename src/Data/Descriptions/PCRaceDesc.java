package Data.Descriptions;

/*
This class is used by OGLDescription to store data about each Player Race.
This data is filled using dataAccess, that uses SQL to reach the database ogl.db
*/

public class PCRaceDesc {
    String name = null;
    String ageD = null;
    String alignmentD = null;
    String sizeD = null;
    String speedD = null;
    String features = null;//This should be the names of the features it has, their descriptions should be somewhere else.
    String languageD = null;//This should be the DESCRIPTION of the races language. Language options are somewhere else
    String subraces = null;//This should be the names of the subraces, their descriptions should be somewhere else.

    public String getFrom(String from) {
        //One function to run all the other get functions, so it can be looped over if given all the names
        switch (from) {
            case "name": return getName();
            case "age": return getAgeD();
            case "alignment": return getAlignmentD();
            case "size": return getSizeD();
            case "speed": return getSpeedD();
            case "features": return getFeatures();
            case "languages": return getLanguageD();
            case "subraces": return getSubraces();

            default: return "FAILED";
        }
    }


    //Get and Set functions for all^^^
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeD() {
        return ageD;
    }

    public void setAgeD(String ageD) {
        this.ageD = ageD;
    }

    public String getAlignmentD() {
        return alignmentD;
    }

    public void setAlignmentD(String alignmentD) {
        this.alignmentD = alignmentD;
    }

    public String getSizeD() {
        return sizeD;
    }

    public void setSizeD(String sizeD) {
        this.sizeD = sizeD;
    }

    public String getSpeedD() {
        return speedD;
    }

    public void setSpeedD(String speedD) {
        this.speedD = speedD;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String feature) {
        this.features = feature;
    }

    public String getLanguageD() {
        return languageD;
    }

    public void setLanguageD(String languageD) {
        this.languageD = languageD;
    }

    public String getSubraces() {
        return subraces;
    }

    public void setSubraces(String subraceD) {
        this.subraces = subraceD;
    }

}
