package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.TransactionDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class TransactionTable implements TransactionDAO {
    private static TransactionTable instance;
    Database db = Database.getInstance();
    ArrayList<Transaction> transactions;
    private TransactionTable(){db = Database.getInstance();}
    @Override
    public ArrayList<Transaction> getAllTransactions() {
        String query = "SELECT * FROM " + TABLE_TRANSACTIONS;

        transactions = new ArrayList<>();
        try {
            Statement getTransactions = db.getConnection().createStatement();
            ResultSet resultSet = getTransactions.executeQuery(query);

            while (resultSet.next()){
                transactions.add(new Transaction(
                        resultSet.getInt(TRANSACTIONS_COLUMN_ID),
                        resultSet.getString(TRANSACTIONS_COLUMN_SKU),
                        resultSet.getInt(TRANSACTIONS_COLUMN_CLIENT),
                        resultSet.getString(TRANSACTIONS_COLUMN_DATE),
                        resultSet.getInt(TRANSACTIONS_COLUMN_QUANTITY),
                        resultSet.getInt(TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transaction getTransaction(int id) {
        String query = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + TRANSACTIONS_COLUMN_ID + " = " +id;

        try {
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet data = getTransaction.executeQuery(query);
            if (data.next()){
                Transaction transaction = new Transaction(
                        data.getInt(TRANSACTIONS_COLUMN_ID),
                        data.getString(TRANSACTIONS_COLUMN_SKU),
                        data.getInt(TRANSACTIONS_COLUMN_CLIENT),
                        data.getString(TRANSACTIONS_COLUMN_DATE),
                        data.getInt(TRANSACTIONS_COLUMN_QUANTITY),
                        data.getInt(TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID)
                );
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TransactionTable getInstance(){
        if (instance == null) {
            instance = new TransactionTable();
        }
        return instance;
    }
}
