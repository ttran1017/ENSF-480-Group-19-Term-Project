package InteractionControllers;

import java.util.ArrayList;
import java.util.Locale;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Class that handles all inputs
//uses a pop up bubble to handle gathering inputs

public class Input {

    private static final int FIELD_SIZE = 5;
    //receives an input string from a pop up dialog box and returns it
    public static String getStringInput(String prompt)
    {
        String returnString = JOptionPane.showInputDialog(prompt);
        return returnString;
    }

    public static LocalDate getDateInput(String prompt)
    {
      prompt = prompt + "\nPlease format as dd-mm-yyyy";

      String dateInString = JOptionPane.showInputDialog(prompt);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d,MM,yyyy", Locale.ENGLISH);
      LocalDate returnDate = LocalDate.parse(dateInString, formatter);

      return returnDate;
    }

    //receives an input string from a pop up dialog box and returns it as an integer
    public static int getIntInput(String prompt)
    {
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
    public static boolean getBoolInput(String prompt)
    {
        int temp = JOptionPane.showConfirmDialog(null, prompt, "no", JOptionPane.YES_NO_OPTION);
        boolean returnBool = false;
        if(temp == JOptionPane.YES_OPTION){
            returnBool = true;
        }
        return returnBool;
    }

    public static Object getDropdownInput(String title, String prompt, Object[] options)
    {
        Object selected = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return selected;
    }

    public static Object getDropdownInputNoPref(String title, String prompt, Object[] options)
    {
        Object modifiedOptions[] = new Object[options.length+1];
        modifiedOptions[0] = "No Preference";
        for(int i = 0; i < options.length; i++)
            modifiedOptions[i+1] = options[i];
        Object selected = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE, null, modifiedOptions, modifiedOptions[0]);
        if(selected.equals("No Preference"))
            return null;
        return selected;
    }

    public static ArrayList<String> getMultiStringInput(String prompt, String[] options)
    {
        ArrayList<JTextField> fields = new ArrayList<JTextField>();
        ArrayList<String> returnVal = new ArrayList<String>();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
        for(String option : options)
        {
            panel.add(new JLabel(option));
            if(option.toLowerCase().equals("password"))
                fields.add(new JPasswordField(FIELD_SIZE));
            else
                fields.add(new JTextField(FIELD_SIZE));
            panel.add(fields.get(fields.size()-1));
        }
        JOptionPane.showConfirmDialog(null, panel , prompt, JOptionPane.OK_CANCEL_OPTION);
        for(JTextField field : fields)
        {
            returnVal.add(field.getText());
        }
        return returnVal;
    }

    //MAIN FUNCTION FOR TESTING OTHER FUNCTIONS, ALL FUNCTIONS SEEM TO WORK AS INTENDED
    public static void main(String[] args){
    }

}
