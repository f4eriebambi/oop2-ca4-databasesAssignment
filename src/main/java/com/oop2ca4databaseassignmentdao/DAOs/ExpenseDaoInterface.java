package com.oop2ca4databaseassignmentdao.DAOs;

//import com.dkit.oop.oop2ca4databaseassignmentdao.DTOs.User;

import com.oop2ca4databaseassignmentdao.DTOs.Expense;
import com.oop2ca4databaseassignmentdao.Exceptions.DaoException;

import java.util.List;
import java.util.Map;

public interface ExpenseDaoInterface {
    public Map<String, Object> allExpensesPlusTotal() throws DaoException;

//    public User findUserByUsernamePassword(String username, String password) throws DaoException;

}

