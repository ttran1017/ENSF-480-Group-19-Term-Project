/**
 * FileName: Input.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package InteractionControllers;

import java.util.ArrayList;
import java.util.Locale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Models.Property;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class which utilizes JOption pane for various input types
 */
public class IO {

    // Input Constants
    private static final int FIELD_SIZE = 5;

    /**
     *
     * Receives an input from a pop up dialog box and returns it
     * @param prompt Input prompt/question
     * @return String in text field
     */
    public static String getStringInput(String prompt)
    {
        String returnString = JOptionPane.showInputDialog(prompt);
        return returnString;
    }

    /**
     *
     * Receives an input from a pop up dialog box and returns it
     * @param prompt Input prompt/question
     * @return LocalDate object
     */
    public static LocalDate getDateInput(String prompt)
    {
      prompt = prompt + "\nPlease format as dd-mm-yyyy";
      String dateInString = JOptionPane.showInputDialog(prompt);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d,MM,yyyy", Locale.ENGLISH);
      LocalDate returnDate = LocalDate.parse(dateInString, formatter);
      return returnDate;
    }

    /**
     * Receives an input from a pop up dialog box and returns it
     * @param prompt Input prompt/question
     * @return Int from field
     */
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

    /**
     * Receives and input from two button
     * @param prompt Input prompt/question
     * @return true if yes, false if no
     */
    public static boolean getBoolInput(String prompt)
    {
        int temp = JOptionPane.showConfirmDialog(null, prompt, "no", JOptionPane.YES_NO_OPTION);
        boolean returnBool = false;
        if(temp == JOptionPane.YES_OPTION){
            returnBool = true;
        }
        return returnBool;
    }

    /**
     * Displays a dropdown selection of options and returns the selected option
     * @param title Prompt title
     * @param prompt Prompt message
     * @param options Array of Objects to be displayed
     * @return Selected Object
     */
    public static Object getDropdownInput(String title, String prompt, Object options[])
    {
        Object selected = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return selected;
    }

    /**
     * Displays a dropdown selection of options and returns the selected option with no preference
     * @param title Prompt title
     * @param prompt Prompt message
     * @param options Array of Objects to be displayed
     * @return null if 'No Preference' or selected Object
     */
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

    /**
     * Returns an ArrayList based on the multiple string input fields
     * @param prompt Prompt message
     * @param options Lines of string (password will have password field)
     * @return ArrayList of string for each field
     */
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

     /**
     * Output text message as pop-up box
     * @param message Output message
     */
    public static void outputMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void displayStringArray(String[][] row_data, String [] col_headers){
      JTable table = new JTable(row_data, col_headers);
      JScrollPane pane = new JScrollPane(table);
      pane.setPreferredSize(new Dimension(800, 300));
      JOptionPane.showMessageDialog(null, pane);
    }

    public static void displaySummary(String startDate, String endDate, String[][] row_data, String [] col_headers, int tListed, int tRented, int tActiveListed){
      JPanel myPanel = new JPanel();
      JLabel title = new JLabel("Summary between "+startDate+" & "+endDate);
      myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
      myPanel.setPreferredSize(new Dimension(800, 300));

      JTable table = new JTable(row_data, col_headers);
      JScrollPane pane = new JScrollPane(table);

      JLabel totListed = new JLabel();
      totListed.setText("Total Listed: " + tListed);

      JLabel totRented = new JLabel();
      totRented.setText("Total Rented: " + tRented);

      JLabel totActiveListed = new JLabel();
      totActiveListed.setText("Total Active Listings: " + tActiveListed);

      myPanel.add(title);
      myPanel.add(pane);
      myPanel.add(totListed);
      myPanel.add(totRented);
      myPanel.add(totActiveListed);

      JOptionPane.showMessageDialog(null, myPanel);
    }

    /**
     * Receives an ArrayList of type Property and displays it in a grid format
     * @param properties Properties to be displayed
     */
    public static void displayProperties(ArrayList<Property> properties)
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,3));
        for(Property prop : properties)
        {
            JPanel propDisplay = new JPanel();
            propDisplay.add(Box.createRigidArea(new Dimension(150, 10)));
            propDisplay.setLayout(new BoxLayout(propDisplay, BoxLayout.Y_AXIS));
            propDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, null));
            propDisplay.add(new JLabel("Property ID: "+prop.getPropertyID()));
            propDisplay.add(new JLabel("Property Status: "+prop.getPropertyStatus()));
            propDisplay.add(new JLabel("Days Remaining: "+prop.getDaysRemaining()));
            propDisplay.add(new JLabel("Property Type: "+prop.getPropertyType()));
            propDisplay.add(new JLabel("Property Address: "+prop.getPropertyAddress()+" "+prop.getPropertyQuadrant()));
            propDisplay.add(new JLabel("No. Of Bedrooms: "+prop.getNumBedrooms()));
            propDisplay.add(new JLabel("No. Of Bathrooms: "+prop.getNumBathrooms()));
            propDisplay.add(new JLabel("Furnished? "+ (prop.getIsFurnished() ? "Yes" : "No")));
            propDisplay.add(Box.createRigidArea(new Dimension(150, 20)));
            try
            {
                BufferedImage icon = ImageIO.read(new File(prop.getPropertyType().getFilePath()));
                JLabel imageLabel = new JLabel(new ImageIcon(icon));
                propDisplay.add(imageLabel);
                propDisplay.add(Box.createRigidArea(new Dimension(150, 10)));
            }
            catch(IOException e)
            {
                IO.outputMessage("Failed to open file!");
                System.exit(11);
            }
            mainPanel.add(propDisplay);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(1200, 800));
        JOptionPane.showMessageDialog(null, scrollPane, "Display Properties", JOptionPane.DEFAULT_OPTION);
    }
}
