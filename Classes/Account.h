/**
 * Project Rental Properties System
 */


#ifndef _ACCOUNT_H
#define _ACCOUNT_H

class Account {
public: 
    
void sendEmail();
    
void viewProperties();
private: 
    int accountType;
    String username;
    String password;
    String email;
};

#endif //_ACCOUNT_H