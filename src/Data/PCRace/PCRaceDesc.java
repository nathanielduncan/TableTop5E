package Data.PCRace;

public class PCRaceDesc {
    String name = null;
    String ageD = null;
    String alignmentD = null;
    String sizeD = null;
    String speedD = null;
    String features = null;//This should be the names of the features it has, their descriptions should be somewhere else.
    String languageD = null;//This should be the DESCRIPTION of the races language. Language options are somewhere else
    String subraces = null;//This should be the names of the subraces, their descriptions should be somewhere else.

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
