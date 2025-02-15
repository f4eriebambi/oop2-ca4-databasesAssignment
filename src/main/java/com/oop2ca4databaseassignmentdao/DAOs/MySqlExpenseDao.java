package com.oop2ca4databaseassignmentdao.DAOs;

import com.oop2ca4databaseassignmentdao.DTOs.Expense;
import com.oop2ca4databaseassignmentdao.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface {
    @Override
//    public List<Expense> allExpensesPlusTotal() throws DaoException {
    public Map<String, Object> allExpensesPlusTotal() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expensesList = new ArrayList<>();
        double totalExpense = 0;

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM expense";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int expenseID = resultSet.getInt("expenseID");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                Date dateIncurred = resultSet.getDate("dateIncurred");
                Expense expense = new Expense(expenseID, title, category, amount, dateIncurred);
                expensesList.add(expense);

                totalExpense += amount;
            }
        }
        catch (SQLException e) {
            throw new DaoException("allExpensesPlusTotal() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e) {
                throw new DaoException("allExpensesPlusTotal() " + e.getMessage());
            }
        }
//        return expensesList;
        Map<String, Object> map = new HashMap<>();
        map.put("expenses", expensesList);
        map.put("totalExpense", totalExpense);

        return map;
    }

    @Override
    public Expense addExpense(Scanner keyboard) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Expense newExpense = null;

        try {
            connection = this.getConnection();

            System.out.println("Please enter the title of the expense :");
            String title = keyboard.nextLine();
            System.out.println("Please enter the category of the task :");
            String category = keyboard.nextLine();
            System.out.println("Please enter the amount of the expense :");
            double amount = keyboard.nextDouble();
            keyboard.nextLine();
            System.out.println("Please enter the date incurred of the expense (the format YYYY-MM-DD) :");
            java.sql.Date dateIncurred = java.sql.Date.valueOf(keyboard.nextLine());

            String sqlQuery = "INSERT INTO expense (title, category, amount, dateIncurred) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, category);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setDate(4, dateIncurred);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                int expenseID = resultSet.getInt(1);
                newExpense = new Expense(expenseID, title, category, amount, dateIncurred);
            }
            else {
                throw new DaoException("No ID returned :( New Expense failed to add !");
            }
        }
        catch (SQLException e) {
            throw new DaoException("addExpense() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("addExpense() " + e.getMessage());
            }
        }
        return newExpense;
    }

    @Override
    public Expense deleteExpense(Scanner keyboard) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Expense deletedExpense = null;

        try {
            connection = this.getConnection();


        }
        catch (SQLException e) {
            throw new DaoException("deleteExpense() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e) {
                throw new DaoException("deleteExpense() " + e.getMessage());
            }
        }

        return deletedExpense;
    }
}