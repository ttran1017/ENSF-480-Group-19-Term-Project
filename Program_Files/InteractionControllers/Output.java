package InteractionControllers;

import java.util.ArrayList;
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

public class Output {
    
    public static void outputMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

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
            propDisplay.add(new JLabel("Owner Email: "+prop.getOwnerEmail()));
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

    public static void main(String[] args)
    {
        ArrayList<Property> props = new ArrayList<Property>();
        for(int i = 0; i < 50; i++)
        {
            props.add(new Property());
        }
        displayProperties(props);
    }
}
