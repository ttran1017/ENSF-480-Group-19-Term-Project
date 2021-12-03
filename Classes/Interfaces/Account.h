/**
 * Project Rental Properties System
 */


#ifndef _ACCOUNT_H
#define _ACCOUNT_H

class Account {
public: 
    int accountType;
    String username;
    String password;
    String email;
    
void sendEmail();
};

#endif //_ACCOUNT_H