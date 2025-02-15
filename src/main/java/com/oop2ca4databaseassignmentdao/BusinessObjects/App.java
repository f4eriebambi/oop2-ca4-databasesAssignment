package com.oop2ca4databaseassignmentdao.BusinessObjects;

//import com.dkit.oop.sd2.DAOs.MySqlUserDao;
//import com.dkit.oop.sd2.DAOs.UserDaoInterface;
//import com.dkit.oop.sd2.DTOs.User;
//import com.dkit.oop.sd2.Exceptions.DaoException;

import com.oop2ca4databaseassignmentdao.DAOs.ExpenseDaoInterface;
import com.oop2ca4databaseassignmentdao.DAOs.MySqlExpenseDao;
import com.oop2ca4databaseassignmentdao.DTOs.Expense;
import com.oop2ca4databaseassignmentdao.Exceptions.DaoException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("~ CA4 Database Assignment");
        App app = new App();
        app.start();
    }

    public void start() {

        Scanner keyboard = new Scanner(System.in);
        String[] menuOptions = {
                "0. Exit",
                "1. Display All Expenses and Total",
                "2. Add New Expense",
                "3. Delete Expense",
                "4. Display All Income and Total",
                "5. Add New Income",
                "6. Delete Income",
                "7. Display All Expense and Income For Particular Month [ TOTAL INCOME, EXPENSE, BALANCE ]",
                ""
        };

        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao();

        int menuChoice = -1;
        do {
            displayMenu( menuOptions , "\n--------- MENU ---------" );
            try {
                menuChoice = getMenuChoice(menuOptions.length);
                switch (menuChoice) {
                    case 1:
                        System.out.println( "- DISPLAY EXPENSES AND TOTAL FROM DATABASE ! -" );
                      Map<String, Object> result = IExpenseDao.allExpensesPlusTotal(); // Get the result Map

                        List<Expense> expenses = (List<Expense>) result.get("expenses");
                        double totalExpense = (double) result.get("totalExpense");

                        if( expenses.isEmpty() )
                            System.out.println("There are no expenses");
                        else {
                            for ( Expense expense : expenses )
                                System.out.println("Expense: " + expense.toString());
                        }
                        System.out.println("Total Expenses :: " + totalExpense);
                        System.out.println("- COMPLETED DISPLAY EXPENSES AND TOTAL FROM DATABASE ! -");
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                    default:
                        break;

                }
            }
            catch (InputMismatchException | DaoException e ) {
                System.out.println( "Invalid input - Please enter a valid option." );
                keyboard.nextLine();
            }
        }
        while( menuChoice != 0 );

        System.out.println("\r\nYour program is disconnecting from the database - Goodbye !" );

    }
//    public static void main(String[] args)
//    {
//        UserDaoInterface IUserDao = new MySqlUserDao();  //"IUserDao" -> "I" stands for for
//

    /// /        // Notice that the userDao reference is an Interface type.
    /// /        // This allows for the use of different concrete implementations.
    /// /        // e.g. we could replace the MySqlUserDao with an OracleUserDao
    /// /        // (accessing an Oracle Database)
    /// /        // without changing anything in the Interface.
    /// /        // If the Interface doesn't change, then none of the
    /// /        // code in this file that uses the interface needs to change.
    /// /        // The 'contract' defined by the interface will not be broken.
    /// /        // This means that this code is 'independent' of the code
    /// /        // used to access the database. (Reduced coupling).
    /// /
    /// /        // The Business Objects require that all User DAOs implement
    /// /        // the interface called "UserDaoInterface", as the code uses
    /// /        // only references of the interface type to access the DAO methods.
//
//        try
//        {
//            System.out.println("\nCall findAllUsers()");
//            List<User> users = IUserDao.findAllUsers();     // call a method in the DAO
//
//            if( users.isEmpty() )
//                System.out.println("There are no Users");
//            else {
//                for (User user : users)
//                    System.out.println("User: " + user.toString());
//            }
//
//            // test dao - with username and password that we know are present in the database
//            System.out.println("\nCall: findUserByUsernamePassword()");
//            String username = "smithj";
//            String password = "password";
//            User user = IUserDao.findUserByUsernamePassword(username, password);
//
//            if( user != null ) // null returned if userid and password not valid
//                System.out.println("User found: " + user);
//            else
//                System.out.println("Username with that password not found");
//
//            // test dao - with an invalid username (i.e. not in database)
//            username = "madmax";
//            password = "thunderdome";
//            user = IUserDao.findUserByUsernamePassword(username, password);
//            if(user != null)
//                System.out.println("Username: " + username + " was found: " + user);
//            else
//                System.out.println("Username: " + username + ", password: " + password +" is not valid.");
//        }
//        catch( DaoException e )
//        {
//            e.printStackTrace();
//        }
//    }
//}
//    public static void main(String[] args) {
//        App app = new App();
//        app.start();
//    }
//
//    public void start() {
//
//        System.out.println("\nSample 1 - Connecting to MySQL Database called \"test\" using MySQL JDBC Driver");
//
//        String url = "jdbc:mysql://localhost/";
//        String dbName = "finance_tracker";
//        String userName = "root";   // default
//        String password = "";       // default
//
//        try (Connection conn =
//                     DriverManager.getConnection(url + dbName, userName, password)) {
//            System.out.println("SUCCESS ! - Your program has successfully connected to the MySql Database Server. Well done.");
//            System.out.println("... we could query the database using the SQL commands you learned in DBMS...");
//            System.out.println("... but for now, we will simply close the connection.");
//
//            System.out.println("Your program is disconnecting from the database - goodbye.");
//        } catch (SQLException ex) {
//            System.out.println("Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
//            ex.printStackTrace();
//        }
//    }
    public static void displayMenu( String[] menuOptions , String menuTitle ) {
        System.out.println( menuTitle );
        System.out.println( "Choose from one of the following options :3" );
        for ( String option: menuOptions ) {
            System.out.println( option );
        }
    }

    public static int getMenuChoice( int numItems ) {
        Scanner keyboard = new Scanner( System.in );
        int choice = keyboard.nextInt();
        while ( choice < 0 || choice > numItems ) {
            System.out.printf( "Please enter a valid option (1 - %d)\n" , numItems );
            choice = keyboard.nextInt();
        }
        return choice;
    }
}