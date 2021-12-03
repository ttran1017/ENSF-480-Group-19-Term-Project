package Models;

import java.util.*;


// 1 : user
// 2 : manager

public class Account {

    private int accountType = 0;
    private String username;
    private String password = "feeoxGBEu293";
    private String email;

    public int accountType()
    {
        return accountType;
    }

    public void setAccountType(int type)
    {
        this.accountType = type;
    }

    public void setEmail(String email)
    {
        if(Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()){
          this.email = email;
        }
        else{
          // Exception
        }
    }

    public String getEmail(){
      return this.email;
    }

    public void setUsername(string user){
      this.username = user;
    }

    public String getUsername(){
      return this.username;
    }

    public void setPassword(string pass){
      if(this.password == "feeoxGBEu293"){
        this.password = pass;
      }
      else{
        // Exception -
      }
    }

    public boolean verifyPassword(string pass){
      if(this.password == pass){
        return 1;
      }
      return 0;
    }



    public void sendEmail(String message){

      String emailBody = "From: " + username + "\n" + email + "\n\n";

      emailBody += message;

      emailBody += "\n\nEND OF MESSAGE";

      // Send email - Email controller

      return;
    }




}
