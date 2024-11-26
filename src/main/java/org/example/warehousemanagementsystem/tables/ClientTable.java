package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ClientDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayAisle;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class ClientTable implements ClientDAO {
    private static ClientTable instance;
    Database db = Database.getInstance();
    ArrayList<Client> clients;
    public ClientTable(){db = Database.getInstance();}
    @Override
    public ArrayList<Client> getAllClients() {
        String query = "SELECT * FROM " + TABLE_CLIENTS;

        clients = new ArrayList<>();
        try {
            Statement getClients = db.getConnection().createStatement();
            ResultSet resultSet = getClients.executeQuery(query);

            while (resultSet.next()){
                clients.add(new Client(
                        resultSet.getInt(COLUMN_CLIENT_ID),
                        resultSet.getString(COLUMN_FIRST_NAME),
                        resultSet.getString(COLUMN_LAST_NAME),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_PHONE),
                        resultSet.getString(COLUMN_STREET_NUMBER),
                        resultSet.getString(COLUMN_STREET_NAME),
                        resultSet.getString(COLUMN_CITY),
                        resultSet.getString(COLUMN_STATE)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getClient(int id) {
        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + COLUMN_CLIENT_ID + " = " +id;

        try {
            Statement getClient = db.getConnection().createStatement();
            ResultSet data = getClient.executeQuery(query);
            if (data.next()){
                Client client = new Client(
                        data.getInt(COLUMN_CLIENT_ID),
                        data.getString(COLUMN_FIRST_NAME),
                        data.getString(COLUMN_LAST_NAME),
                        data.getString(COLUMN_EMAIL),
                        data.getString(COLUMN_PHONE),
                        data.getString(COLUMN_STREET_NUMBER),
                        data.getString(COLUMN_STREET_NAME),
                        data.getString(COLUMN_CITY),
                        data.getString(COLUMN_STATE)
                );
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClientTable getInstance(){
        if (instance == null) {
            instance = new ClientTable();
        }
        return instance;
    }

    public ArrayList<DisplayClient> getItems(){
        ArrayList<DisplayClient> clients = new ArrayList<DisplayClient>();
        String query = "SELECT client_id, first_name, last_name, email, phone, " +
                "street_number, street_name, city, state " +
                "FROM clients " +
                "ORDER BY client_id ASC";
        try {
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);
            while (data.next()) {
                clients.add(new DisplayClient(
                        data.getInt("client_id"),
                        data.getString("first_name"),
                        data.getString("last_name"),
                        data.getString("email"),
                        data.getString("phone"),
                        data.getString("street_number"),
                        data.getString("street_name"),
                        data.getString("city"),
                        data.getString("state")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
