/**
 * Project Rental Properties System
 */


#ifndef _REGISTRATIONFORM_H
#define _REGISTRATIONFORM_H

#include "Input.h"


class RegistrationForm: public Input {
public: 
    
static void register();
    
/**
 * @param success
 */
static void displayStatus(boolean success);
private: 
    
static String askUsername();
    
static String askPassword();
    
static String askEmail();
};

#endif //_REGISTRATIONFORM_H