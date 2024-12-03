package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.TransactionDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Product;
import org.example.warehousemanagementsystem.pojo.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

/**
 * The TransactionTable class provides methods to interact with the transactions table in the database.
 * This class implements the TransactionDAO interface and supports CRUD operations on transaction data.
 * Additionally, it includes methods to retrieve business insights, such as top-selling products.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-16
 */

public class TransactionTable implements TransactionDAO {
    private static TransactionTable instance;
    Database db = Database.getInstance();
    ArrayList<Transaction> transactions;
    public TransactionTable(){db = Database.getInstance();}

    /**
     * Retrieves all transactions from the database.
     *
     * @return An ArrayList of Transaction objects representing all transactions in the database.
     */
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
                        resultSet.getInt(TRANSACTIONS_COLUMN_SKU),
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

    /**
     * Retrieves a transaction by its unique ID.
     *
     * @param id The ID of the transaction to retrieve.
     * @return A Transaction object if found, otherwise null.
     */

    @Override
    public Transaction getTransaction(int id) {
        String query = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + TRANSACTIONS_COLUMN_ID + " = " +id;

        try {
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet data = getTransaction.executeQuery(query);
            if (data.next()){
                Transaction transaction = new Transaction(
                        data.getInt(TRANSACTIONS_COLUMN_ID),
                        data.getInt(TRANSACTIONS_COLUMN_SKU),
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

    /**
     * Retrieves the top-selling products based on transaction quantities.
     *
     * @return An ArrayList of Object arrays, where each array contains a Product object and total quantity sold.
     */

    public ArrayList<Object[]> getTopSellingProducts() {
        ArrayList<Object[]> topSellingProducts = new ArrayList<>();

        String query = "SELECT p." + COLUMN_SKU + ", p." + COLUMN_BRAND_ID + ", p." + COLUMN_MODEL + ", p." + COLUMN_PRICE + ", SUM(t." + TRANSACTIONS_COLUMN_QUANTITY + ") AS total_sold " +
            "FROM " + TABLE_TRANSACTIONS + " t " +
            "JOIN " + TABLE_PRODUCT + " p ON t." + TRANSACTIONS_COLUMN_SKU + " = p." + COLUMN_SKU +
            " WHERE t." + TRANSACTIONS_COLUMN_QUANTITY + " > 0 " +
            " GROUP BY p." + COLUMN_SKU + ", p." + COLUMN_BRAND_ID + ", p." + COLUMN_MODEL + ", p." + COLUMN_PRICE +
            " ORDER BY total_sold DESC LIMIT 10";

        try  {
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet data = getTransaction.executeQuery(query);

            while (data.next()) {
                int sku = data.getInt("sku");
                int brandId = data.getInt("brand_id");
                String model = data.getString("model");
                int price = data.getInt("price");
                int totalSold = data.getInt("total_sold");

                Product product = new Product(sku, brandId, model, price);
                topSellingProducts.add(new Object[]{product, totalSold});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return topSellingProducts;
    }

    /**
     * Singleton pattern to get the single instance of TransactionTable.
     *
     * @return The singleton instance of TransactionTable.
     */

    public static TransactionTable getInstance(){
        if (instance == null) {
            instance = new TransactionTable();
        }
        return instance;
    }
    public void createTransaction(Transaction transaction) {
        String query = "INSERT INTO " + TABLE_TRANSACTIONS + " (" +
                TRANSACTIONS_COLUMN_ID + ", " +
                TRANSACTIONS_COLUMN_SKU + ", " +
                TRANSACTIONS_COLUMN_CLIENT + ", " +
                TRANSACTIONS_COLUMN_DATE + ", " +
                TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID + ", " +
                TRANSACTIONS_COLUMN_QUANTITY + ") VALUES (" +
                transaction.getId() + ", '" +
                transaction.getSku() + "', " +
                transaction.getClientId() + ", '" +
                transaction.getDate() + "', " +
                transaction.getProductLocationId() + ", " +
                transaction.getQuantity() + ")";

        try {
            db.getConnection().createStatement().executeUpdate(query);
            System.out.println("Transaction created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int id) {
        String query = "DELETE FROM " + TABLE_TRANSACTIONS + " WHERE " + TRANSACTIONS_COLUMN_ID + " = " + id;

        try{
            db.getConnection().createStatement().execute(query);
            System.out.println("Transaction deleted successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateTransaction(Transaction transaction) {
        String query = "UPDATE " + TABLE_TRANSACTIONS + " SET " +
                TRANSACTIONS_COLUMN_SKU + " = '" + transaction.getSku() + "', " +
                TRANSACTIONS_COLUMN_CLIENT + " = " + transaction.getClientId() + ", " +
                TRANSACTIONS_COLUMN_DATE + " = '" + transaction.getDate() + "', " +
                TRANSACTIONS_COLUMN_QUANTITY + " = " + transaction.getQuantity() + ", " +
                TRANSACTIONS_COLUMN_PRODUCT_LOCATION_ID + " = " + transaction.getProductLocationId() + " " +
                "WHERE " + TRANSACTIONS_COLUMN_ID + " = " + transaction.getId();

        try {
            db.getConnection().createStatement().executeUpdate(query);
            System.out.println("Transaction updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
