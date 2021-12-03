/**
 * Project Rental Properties System
 */


#ifndef _UNREGISTEREDACCOUNT_H
#define _UNREGISTEREDACCOUNT_H

#include "../Account.h"


class UnregisteredAccount: public Account {
public: 
    
void viewProperties();
    
void sendEmail();
private: 
    int accountType = 0;
    String username = NULL;
    String password = NULL;
    String email = NULL;
};

#endif //_UNREGISTEREDACCOUNT_H