package com.oop2ca4databaseassignmentdao.DAOs;

//import com.dkit.oop.oop2ca4databaseassignmentdao.DTOs.User;
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

//    @Override
//    public Expense deleteExpense() throws DaoException
//    {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        User user = null;
//        try
//        {
//            connection = this.getConnection();
//
//            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, user_name);
//            preparedStatement.setString(2, password);
//
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next())
//            {
//                int userId = resultSet.getInt("USER_ID");
//                String username = resultSet.getString("USERNAME");
//                String pwd = resultSet.getString("PASSWORD");
//                String lastname = resultSet.getString("LAST_NAME");
//                String firesultSettname = resultSet.getString("FIRST_NAME");
//
//                user = new User(userId, firesultSettname, lastname, username, pwd);
//            }
//        } catch (SQLException e)
//        {
//            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
//        } finally
//        {
//            try
//            {
//                if (resultSet != null)
//                {
//                    resultSet.close();
//                }
//                if (preparedStatement != null)
//                {
//                    preparedStatement.close();
//                }
//                if (connection != null)
//                {
//                    freeConnection(connection);
//                }
//            } catch (SQLException e)
//            {
//                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
//            }
//        }
//        return user;     // reference to User object, or null value
//    }
}