package com.dkit.oop.oop2ca4databaseassignmentdao.BusinessObjects;

import com.dkit.oop.oop2ca4databaseassignmentdao.DAOs.MySqlUserDao;
import com.dkit.oop.oop2ca4databaseassignmentdao.DAOs.UserDaoInterface;
import com.dkit.oop.oop2ca4databaseassignmentdao.DTOs.User;
import com.dkit.oop.oop2ca4databaseassignmentdao.Exceptions.DaoException;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        UserDaoInterface IUserDao = new MySqlUserDao();

        try
        {
            System.out.println("\nCall findAllUsers()");
            List<User> users = IUserDao.findAllUsers();     // call a method in the DAO

            if( users.isEmpty() )
                System.out.println("There are no Users");
            else {
                for (User user : users)
                    System.out.println("User: " + user.toString());
            }

            // test dao - with username and password that we know are present in the database
            System.out.println("\nCall: findUserByUsernamePassword()");
            String username = "smithj";
            String password = "password";
            User user = IUserDao.findUserByUsernamePassword(username, password);

            if( user != null ) // null returned if userid and password not valid
                System.out.println("User found: " + user);
            else
                System.out.println("Username with that password not found");

            // test dao - with an invalid username (i.e. not in database)
            username = "madmax";
            password = "thunderdome";
            user = IUserDao.findUserByUsernamePassword(username, password);
            if(user != null)
                System.out.println("Username: " + username + " was found: " + user);
            else
                System.out.println("Username: " + username + ", password: " + password +" is not valid.");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
