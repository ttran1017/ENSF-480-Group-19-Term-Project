/**
 * Project Rental Properties System
 */


#ifndef _DATABASECONTROLLER_H
#define _DATABASECONTROLLER_H

class DatabaseController {
public: 
    
/**
 * @param username
 * @param password
 */
booean verifyRegistration(String username, String password);
    
/**
 * @param username
 * @param password
 */
boolean verifyLogin(String username, String password);
    
/**
 * @param username
 * @param password
 */
void uploadAccount(String username, String password);
    
ArrayList<Account> getAllAccounts();
    
ArrayList<Properties> getAllProperties();
    
DatabaseController getOnlyInstance();
private: 
    DatabaseController instance;
    
void DatabaseController();
};

#endif //_DATABASECONTROLLER_H