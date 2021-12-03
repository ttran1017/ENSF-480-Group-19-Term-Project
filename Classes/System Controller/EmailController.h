/**
 * Project Rental Properties System
 */


#ifndef _EMAILCONTROLLER_H
#define _EMAILCONTROLLER_H

class EmailController {
public: 
    void Attribute1;
    void Attribute2;
    
/**
 * @param address
 */
static void sendEmail(String address);
    
/**
 * @param address
 */
static void setupMeeting(String address);
    
static void startUpEmailController();
private: 
    static Socket socket;
};

#endif //_EMAILCONTROLLER_H