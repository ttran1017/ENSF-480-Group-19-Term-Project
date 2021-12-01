import javax.swing.JOptionPane;

//Class that handles all inputs
//uses a pop up bubble to handle gathering inputs

public class Input {
    //receives an input string from a pop up dialog box and returns it
    public static String getStringInput(){
        String returnString = JOptionPane.showInputDialog("Hello what is input lol");
        return returnString;
    }

    //receives an input string from a pop up dialog box and returns it as an integer
    public static int getIntInput(){
        String temp = JOptionPane.showInputDialog("Hello what is input lol");
        int returnInt = Integer.parseInt(temp);
        return returnInt;
    }


    //pop up dialog box with two options, one will return true and the other will return false
    public static boolean getBoolInput(){
        int temp = JOptionPane.showConfirmDialog(null, "true","false", JOptionPane.YES_NO_OPTION);
        boolean returnBool = false;
        if(temp>1){
            returnBool = true;
        }
        return returnBool;
    }

}
