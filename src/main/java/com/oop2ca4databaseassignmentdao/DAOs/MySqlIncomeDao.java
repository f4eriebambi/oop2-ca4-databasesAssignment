package com.oop2ca4databaseassignmentdao.DAOs;

import com.oop2ca4databaseassignmentdao.DTOs.Income;
import com.oop2ca4databaseassignmentdao.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface {
    @Override
    public Map<String, Object> allIncomePlusTotal() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomeList = new ArrayList<>();
        double totalIncome = 0;

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM income";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int incomeID = resultSet.getInt("incomeID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("dateEarned");
                Income income = new Income(incomeID, title, amount, dateEarned);
                incomeList.add(income);

                totalIncome += amount;
            }
        }
        catch (SQLException e) {
            throw new DaoException("allIncomesPlusTotal() " + e.getMessage());
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
                throw new DaoException("allIncomesPlusTotal() " + e.getMessage());
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("income", incomeList);
        map.put("totalIncome", totalIncome);

        return map;
    }

    @Override
    public Income addIncome(Scanner keyboard) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Income newIncome = null;

        try {
            connection = this.getConnection();

            System.out.println("Please enter the title of the income :");
            String title = keyboard.nextLine();
            System.out.println("Please enter the amount of the income :");
            double amount = keyboard.nextDouble();
            keyboard.nextLine();
            System.out.println("Please enter the date earned of the income (the format YYYY-MM-DD) :");
            java.sql.Date dateEarned = java.sql.Date.valueOf(keyboard.nextLine());

            String sqlQuery = "INSERT INTO income (title, amount, dateEarned) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, title);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setDate(3, dateEarned);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if ( resultSet.next() ) {
                int incomeID = resultSet.getInt(1);
                newIncome = new Income(incomeID, title, amount, dateEarned);
            }
            else {
                throw new DaoException("No ID returned :( New Income failed to add !");
            }
        }
        catch (SQLException e) {
            throw new DaoException("addIncome() " + e.getMessage());
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
                throw new DaoException("addIncome() " + e.getMessage());
            }
        }
        return newIncome;
    }

    @Override
    public Income deleteIncome(Scanner keyboard) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Income deletedIncome = null;

        try {
            connection = this.getConnection();

            System.out.println( "Please enter the income id you would like to delete :" );
            int incomeID = keyboard.nextInt();
            keyboard.nextLine();

            String selectQuery = "SELECT * FROM income WHERE incomeID = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, incomeID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("dateEarned");
                deletedIncome = new Income(incomeID, title, amount, dateEarned);

                String query = "DELETE FROM income WHERE incomeID = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, incomeID);
                preparedStatement.executeUpdate();
            }
            else {
                throw new DaoException("No income found with that ID !");
            }
        }
        catch (SQLException e) {
            throw new DaoException(e.getMessage());
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
                throw new DaoException("deleteIncome() " + e.getMessage());
            }
        }

        return deletedIncome;
    }
}