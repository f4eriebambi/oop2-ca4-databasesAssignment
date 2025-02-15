package com.oop2ca4databaseassignmentdao.BusinessObjects;

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
        System.out.println( "~ CA4 Database Assignment" );
        System.out.println( "- ASSUME ALL INPUTS ARE VALID :3 -" );

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

                        if( expenses.isEmpty() ) {
                            System.out.println("Your expenses are empty !");
                        }
                        else {
                            for ( Expense expense : expenses ) {
                                System.out.println("Expense: " + expense.toString());
                            }
                        }
                        System.out.println("Total Expenses :: " + totalExpense);
                        System.out.println("- COMPLETED DISPLAY EXPENSES AND TOTAL FROM DATABASE ! -");
                        break;
                    case 2:
                        System.out.println( "- ADD NEW EXPENSE TO DATABASE ! -" );
                        Expense newExpense = IExpenseDao.addExpense(keyboard);
                        if ( newExpense != null ) {
                            System.out.println("New Expense Added :: " + newExpense.toString());
                        }
                        else {
                            System.out.println( "FAILED." );
                        }
                        System.out.println("- COMPLETED ADDING NEW EXPENSE TO DATABASE ! -");
                        break;
                    case 3:
                        System.out.println("- DELETE EXPENSE FROM DATABASE ! -");
                        try {
                            Expense deletedExpense = IExpenseDao.deleteExpense(keyboard);
                            if (deletedExpense != null) {
                                System.out.println("Expense Deleted :: " + deletedExpense.toString());
                                System.out.println("- COMPLETED DELETING EXPENSE FROM DATABASE ! -");
                            }
                        } catch (DaoException e) {
                            System.out.println(e.getMessage());
                            System.out.println("FAILED.");
                        }
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

    public static void displayMenu( String[] menuOptions , String menuTitle ) {
        System.out.println( menuTitle );
        System.out.println( "Choose from one of the following options ::" );
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