package Data.Descriptions;

/*
This class is used by OGLDescription to store data about each Player Class.
This data is filled using dataAccess, that uses SQL to reach the database ogl.db
*/

public class PCClassDesc {
    String name = null;
    String hitDice = null;
    String armor = null;
    String weapons = null;
    String tools = null;
    String savingThrows = null;
    String skills = null;
    String equipment = null;
    String features = null;
    String asi = null;
    String subclasses = null;

    public void clear() {//When selection is cleared in GUI, saved object loses all data
        name = null;
        hitDice = null;
        armor = null;
        weapons = null;
        tools = null;
        savingThrows = null;
        skills = null;
        equipment = null;
        features = null;
        asi = null;
        subclasses = null;
    }

    public String getFrom(String from) {
        //One function to run all the other get functions, so it can be looped over if given all the names
        switch (from) {
            case "name": return getName();
            case "hitDice": return getHitDice();
            case "armor": return getArmor();
            case "weapons": return getWeapons();
            case "tools": return getTools();
            case "savingThrows": return getSavingThrows();
            case "skills": return getSkills();
            case "equipment": return getEquipment();
            case "features": return getFeatures();
            case "asi": return getAsi();
            case "subclasses": return getSubclasses();

            default: return "FAILED";
        }
    }

//Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getWeapons() {
        return weapons;
    }

    public void setWeapons(String weapons) {
        this.weapons = weapons;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(String savingThrows) {
        this.savingThrows = savingThrows;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public  String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAsi() {
        return asi;
    }

    public void setAsi(String asi) {
        this.asi = asi;
    }

    public String getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(String subclasses) {
        this.subclasses = subclasses;
    }
}
