import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class doWork {

    //Function to return all tokens after scanner work
    public static ArrayList<String> getTokens(String line, String del) {
        Scanner reader = new Scanner(line);//Make scanner
        reader.useDelimiter(del);

        ArrayList<String> list = new ArrayList<String>();//Make array to be returned

        while(reader.hasNext()) {//Fill array
            list.add(reader.next());
        }

        return list;
    }

    //Function to return an arraylist given the enumerator of buttons
    public static ArrayList<AbstractButton> getButtonArray(Enumeration<AbstractButton> enm) {
        ArrayList<AbstractButton> buttons = new ArrayList<AbstractButton>();//Make array to be returned

        while(enm.hasMoreElements()) {//Fill array
            buttons.add(enm.nextElement());
        }

        return buttons;
    }
}
