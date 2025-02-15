package com.oop2ca4databaseassignmentdao.DAOs;

import com.oop2ca4databaseassignmentdao.DTOs.Income;
import com.oop2ca4databaseassignmentdao.Exceptions.DaoException;

import java.util.Map;
import java.util.Scanner;

public interface IncomeDaoInterface {
    public Map<String, Object> allIncomePlusTotal() throws DaoException;
    public Income addIncome(Scanner keyboard) throws DaoException;
    public Income deleteIncome(Scanner keyboard) throws DaoException;
}