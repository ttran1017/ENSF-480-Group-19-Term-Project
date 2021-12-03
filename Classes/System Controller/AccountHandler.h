/**
 * Project Rental Properties System
 */


#ifndef _ACCOUNTHANDLER_H
#define _ACCOUNTHANDLER_H

class AccountHandler {
public: 
    
void register();
    
void registerAccount();
    
Account loginAccount();
    
void Operation2();
    
void Operation3();
private: 
    ArrayList<Account> accountList;
    DatabaseController database;
    
/**
 * @param username
 * @param password
 */
boolean createAccount(String username, String password);
    
/**
 * @param username
 * @param password
 */
boolean verifyLoginDetails(String username, String password);
    
/**
 * @param id
 */
Account getAccountByID(int id);
};

#endif //_ACCOUNTHANDLER_H