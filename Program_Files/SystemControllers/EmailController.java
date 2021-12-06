package SystemControllers;
import InteractionControllers.*;
import java.util.*;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDate;


public class EmailController {
  static Properties prop = new Properties();
  private static String username = "5e0ccfeb8924ab";
  private static String password = "c7f4caa9e658d9";

  private static String tag = "\n\n\n\n - ENSF Team";

  public EmailController(){
   prop.put("mail.smtp.auth", true);
   prop.put("mail.smtp.starttls.enable", "false");
   prop.put("mail.smtp.host", "smtp.mailtrap.io");
   prop.put("mail.smtp.port", "2525");
   prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
  }


  public static void sendEmail(String userEmail, String ownerEmail, int propertyID) {

    if(userEmail == "UNREGISTERED"){
      userEmail = "emailcontroller@ensf.com";
    }

    if(!EmailController.checkFormat(userEmail)){
      Output.outputMessage("Email controller failed - user address incorrectly formatted.");
      return;
    }

    if(!EmailController.checkFormat(ownerEmail)){
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

     ArrayList<String> emailData = Input.getMultiStringInput("Composing Email", new String[]{"Subject,Message"});
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

  public static void setupMeeting(String userEmail, String ownerEmail, int propertyID) {

    if(userEmail == "UNREGISTERED"){
      userEmail = "emailcontroller@ensf.com";
    }

    // Check for valid email
    if(!EmailController.checkFormat(userEmail)){
      Output.outputMessage("Email controller failed - user address incorrectly formatted.");
      return;
    }

    if(!EmailController.checkFormat(ownerEmail)){
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

  // Send to address that a property has been posted with a specified ID
  public static void sendNotification(String address, int ID)
  {
    if(!EmailController.checkFormat(address)){
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

       String msg = "SET UP MEETING\n\n" + "A property has been posted that matches your criteria!"
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

  public static boolean checkFormat(String email)
  {
      if(Pattern.compile("^(?:.+)@(?:\\S+)$").matcher(email).matches()){
          return true;
      }
      return false;
  }

}
