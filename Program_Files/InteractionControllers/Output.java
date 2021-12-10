/**
 * FileName: Output.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package InteractionControllers;

import java.util.ArrayList;

import Models.Account;
import Models.Property;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Class Responsible for all Outputs
 */
public class Output {

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

    public static void displaySummary(String[][] row_data, String [] col_headers, int tListed, int tRented, int tActiveListed){
      JPanel myPanel = new JPanel();
      myPanel.setPreferredSize(new Dimension(800, 300));

      JTable table = new JTable(row_data, col_headers);
      JScrollPane pane = new JScrollPane(table);

      JLabel totListed = new JLabel();
      totListed.setText("Total Listed: " + tListed);

      JLabel totRented = new JLabel();
      totRented.setText("Total Rented: " + tRented);

      JLabel totActiveListed = new JLabel();
      totRented.setText("Total Active Listings: " + tActiveListed);

      myPanel.add(pane);
      myPanel.add(totListed);
      myPanel.add(totRented);
      myPanel.add(totActiveListed);

      JOptionPane.showMessageDialog(null, pane);
    }


    // Display Hashmap - could be overloaded
    public static void displayAccounts(ArrayList<Account> accounts){
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
                Output.outputMessage("Failed to open file!");
                System.exit(11);
            }
            mainPanel.add(propDisplay);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(1200, 800));
        JOptionPane.showMessageDialog(null, scrollPane, "Display Properties", JOptionPane.DEFAULT_OPTION);
    }
}
