package Data.Descriptions;

/*
This class is used by OGLDescription to store data about each Player Background.
This data is filled using dataAccess, that uses SQL to reach the database ogl.db
*/

public class PCBackGDesc {
    String name = null;
    String description = null;
    String skills = null;
    String languages = null;
    String equipment = null;
    String feature = null;

    public String getFrom(String from) {
        //One function to run all the other get functions, so it can be looped over if given all the names
        return switch (from) {
            case "name" -> getName();
            case "description" -> getDescription();
            case "skills" -> getSkills();
            case "languages" -> getLanguages();
            case "equipment" -> getEquipment();
            case "feature" -> getFeature();
            default -> "FAILED";
        };
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
