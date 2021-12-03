/**
 * Project Rental Properties System
 */


#ifndef _LOGINFORM_H
#define _LOGINFORM_H

#include "Input.h"


class LoginForm: public Input {
public: 
    
static void login();
    
/**
 * @param success
 */
static void displayStatus(boolean success);
private: 
    
static String askUsername();
    
static String askPassword();
};

#endif //_LOGINFORM_H