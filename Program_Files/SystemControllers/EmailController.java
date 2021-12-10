/**
 * FileName: EmailController.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;
import InteractionControllers.*;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;



public class EmailController {
  private static EmailController INSTANCE;
  private Properties prop = new Properties();
  private String username = "e1ef4b3cdd3122";
  private String password = "d7d3accee2cc02";
  private String tag = "\n\n\n\n - ENSF Team";

  /**
   * Instantiates EmailController
   */
  private EmailController(){
   prop.put("mail.smtp.auth", true);
   prop.put("mail.smtp.starttls.enable", "false");
   prop.put("mail.smtp.host", "smtp.mailtrap.io");
   prop.put("mail.smtp.port", "2525");
   prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
  }

  /**
   * Creates email controller instance
   */
  public static EmailController getInstance()
  {
    if(INSTANCE == null)
    {
      INSTANCE = new EmailController();
    }
    return INSTANCE;
  }

  /**
   * Creates and sends an email
   * @param String userEmail - takes in email of the user
   * @param String ownerEmail - takes in email of the owner
   * @param int propertyID - sets which property the email is regarding
   */
  public void sendEmail(String userEmail, String ownerEmail, int propertyID) {

    // Checks if the account is UNREGISTERED
    if(userEmail == "UNREGISTERED"){
      userEmail = "emailcontroller@ensf.com";
    }

    // Checks format of the emails
    if(!checkFormat(userEmail)){
      Output.outputMessage("Email controller failed - user address incorrectly formatted.");
      return;
    }

    if(!checkFormat(ownerEmail)){
      Output.outputMessage("Email controller failed - owner address incorrectly formatted.");
      return;
    }

     // Create Session
     Session session = Session.getInstance(prop, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
         }
     });

     // Create Message to send
     ArrayList<String> emailData = Input.getMultiStringInput("Composing Email", new String[]{"Subject","Message"});
     String subject = "Regarding " + String.valueOf(propertyID) + ": "+ emailData.get(0);
     String msg = emailData.get(1) + tag;

     try{
       // Set message details
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress(userEmail));
       message.setRecipients(
       Message.RecipientType.TO, InternetAddress.parse(ownerEmail));

       message.setSubject(subject);

       // Add MimeBodyPart
       MimeBodyPart mimeBodyPart = new MimeBodyPart();
       mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

       Multipart multipart = new MimeMultipart();
       multipart.addBodyPart(mimeBodyPart);

       message.setContent(multipart);
       // Send mail
       Transport.send(message);
     }
     catch(MessagingException e){
       throw new RuntimeException(e);
     }

     // Let user know if successful
     Output.outputMessage("Email sent successfully");
  }

  /**
   * Creates and sends an email that requests a meeting
   * @param String userEmail - takes in email of the user
   * @param String ownerEmail - takes in email of the owner
   * @param int propertyID - sets which property the email is regarding
   */
  public void setupMeeting(String userEmail, String ownerEmail, int propertyID) {

    if(userEmail == "UNREGISTERED"){
      userEmail = "emailcontroller@ensf.com";
    }

    // Check for valid email
    if(!checkFormat(userEmail)){
      Output.outputMessage("Email controller failed - user address incorrectly formatted.");
      return;
    }

    if(!checkFormat(ownerEmail)){
      Output.outputMessage("Email controller failed - owner address incorrectly formatted.");
      return;
    }

    LocalDate day = Input.getDateInput("When would you like to set up a meeting?");

     // Create Session
     Session session = Session.getInstance(prop, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
         }
     });


     try{
       // Set message details
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress(userEmail));
       message.setRecipients(
               Message.RecipientType.TO, InternetAddress.parse(ownerEmail));
       message.setSubject("ARRANGING MEETING");

       // Add MimeBodyPart
       MimeBodyPart mimeBodyPart = new MimeBodyPart();

       String msg = "SET UP MEETING\n\n" + "A request to meet up at " + day.toString()
       + " about " + String.valueOf(propertyID) + " has been made." + tag;

       mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

       Multipart multipart = new MimeMultipart();
       multipart.addBodyPart(mimeBodyPart);

       message.setContent(multipart);
       // Send mail
       Transport.send(message);
     }
     catch(MessagingException e){
       throw new RuntimeException(e);
     }

     // Let user know if successful
     Output.outputMessage("Email sent successfully");
  }

  /**
   * Creates and sends an email to notify user of some property
   * @param String address - sets address of the property the email is regarding
   * @param int ID - sets which property the email is regarding
   */
  public void sendNotification(String address, int ID)
  {
    if(!checkFormat(address)){
      Output.outputMessage("Email controller failed - address incorrectly formatted.");
      return;
    }

     // Create Session
     Session session = Session.getInstance(prop, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
         }
     });


     try{
       // Set message details
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress("emailcontroller@ensf.com"));
       message.setRecipients(
               Message.RecipientType.TO, InternetAddress.parse(address));

       // Add MimeBodyPart
       MimeBodyPart mimeBodyPart = new MimeBodyPart();

       String msg = "A property has been posted that matches your criteria!"
       + "\n\n\nPROPERTY ID: " + String.valueOf(ID) + tag;


       mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

       Multipart multipart = new MimeMultipart();
       multipart.addBodyPart(mimeBodyPart);

       message.setContent(multipart);
       // Send mail
       Transport.send(message);
     }
     catch(MessagingException e){
       throw new RuntimeException(e);
     }
  }

  /**
   * Checks if email is correctly formatted
   * @param String email - sets the email to check
   * @returns true if the email is correctly formatted
   */
  public boolean checkFormat(String email)
  {
      if(Pattern.compile("^(?:.+)@(?:\\S+)$").matcher(email).matches()){
          return true;
      }
      return false;
  }

  public static void main(String[] args) throws MessagingException
  {
    EmailController.getInstance().sendNotification("ok@s", 1);
  }

}
