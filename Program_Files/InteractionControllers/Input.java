package InteractionControllers;

import javax.swing.JOptionPane;

//Class that handles all inputs
//uses a pop up bubble to handle gathering inputs

public class Input {
    //receives an input string from a pop up dialog box and returns it
    public static String getStringInput(String prompt){
        String returnString = JOptionPane.showInputDialog(prompt);
        return returnString;
    }

    //receives an input string from a pop up dialog box and returns it as an integer
    public static int getIntInput(String prompt){
        int returnInt;
        while(true){
            try{
                String temp = JOptionPane.showInputDialog(prompt);
                returnInt = Integer.parseInt(temp);
                return returnInt;
            } catch (Exception e){
            }
        } 
    }

    //pop up dialog box with two options, one will return true and the other will return false
    public static boolean getBoolInput(String prompt){
        int temp = JOptionPane.showConfirmDialog(null, prompt, "no", JOptionPane.YES_NO_OPTION);
        boolean returnBool = false;
        if(temp == JOptionPane.YES_OPTION){
            returnBool = true;
        }
        return returnBool;
    }

    //MAIN FUNCTION FOR TESTING OTHER FUNCTIONS, ALL FUNCTIONS SEEM TO WORK AS INTENDED
    public static void main(String[] args){
        System.out.println(getStringInput("hello input uwu"));
        System.out.println(getIntInput("uwu integer time !1"));
        System.out.println(getBoolInput("88w88 u lik spiders?"));
    }

}
