/**
 * Project Rental Properties System
 */


#ifndef _INPUT_H
#define _INPUT_H

class Input {
public: 
    
static String getStringInput();
    
static int getIntInput();
    
static bool getBoolInput();
    
static Input getOnlyInstance();
private: 
    static Input instance;
    
void Input();
};

#endif //_INPUT_H