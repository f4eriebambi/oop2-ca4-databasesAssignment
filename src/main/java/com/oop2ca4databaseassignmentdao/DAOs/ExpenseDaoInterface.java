package com.oop2ca4databaseassignmentdao.DAOs;

import com.oop2ca4databaseassignmentdao.DTOs.Expense;
import com.oop2ca4databaseassignmentdao.Exceptions.DaoException;

import java.util.Map;
import java.util.Scanner;

public interface ExpenseDaoInterface {
    public Map<String, Object> allExpensesPlusTotal() throws DaoException;
    public Expense addExpense(Scanner keyboard) throws DaoException;
    public Expense deleteExpense(Scanner keyboard) throws DaoException;
}

