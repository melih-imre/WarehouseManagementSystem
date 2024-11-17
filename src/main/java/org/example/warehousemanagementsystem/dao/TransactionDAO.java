package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Transaction;

import java.util.ArrayList;

public interface TransactionDAO {
    public ArrayList<Transaction> getAllTransactions();
    public Transaction getTransaction(int id);
}
