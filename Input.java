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
        int returnInt;
        while(true){
            try{
                String temp = JOptionPane.showInputDialog("input funny numbers hehehehe");
                returnInt = Integer.parseInt(temp);
                return returnInt;
            } catch (Exception e){
            }
        } 
    }


    //pop up dialog box with two options, one will return true and the other will return false
    public static boolean getBoolInput(){
        int temp = JOptionPane.showConfirmDialog(null, "Choose yes or no", "no", JOptionPane.YES_NO_OPTION);
        boolean returnBool = false;
        if(temp == JOptionPane.YES_OPTION){
            returnBool = true;
        }
        return returnBool;
    }


    //MAIN FUNCTION FOR TESTING OTHER FUNCTIONS, ALL FUNCTIONS SEEM TO WORK AS INTENDED
    public static void main(String[] args){
        System.out.println(getStringInput());
        System.out.println(getIntInput());
        System.out.println(getBoolInput());
    }

}
