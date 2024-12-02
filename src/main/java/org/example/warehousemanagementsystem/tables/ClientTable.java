

package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ClientDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class ClientTable implements ClientDAO {
    private static ClientTable instance;
    Database db = Database.getInstance();
    ArrayList<Client> clients;

    public ClientTable() {
        db = Database.getInstance();
    }

    @Override
    public ArrayList<Client> getAllClients() {
        String query = "SELECT * FROM " + TABLE_CLIENTS;

        clients = new ArrayList<>();
        try {
            Statement getClients = db.getConnection().createStatement();
            ResultSet resultSet = getClients.executeQuery(query);

            while (resultSet.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getClient(int id) {
        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + COLUMN_CLIENT_ID + " = " + id;

        try {
            Statement getClient = db.getConnection().createStatement();
            ResultSet data = getClient.executeQuery(query);
            if (data.next()) {
                return new Client(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Singleton Instance
    public static ClientTable getInstance() {
        if (instance == null) {
            instance = new ClientTable();
        }
        return instance;
    }

    // Insert a new client into the database
    public void createClient(Client client) {
        String query = "INSERT INTO " + TABLE_CLIENTS + " (" +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_LAST_NAME + ", " +
                COLUMN_EMAIL + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_STREET_NUMBER + ", " +
                COLUMN_STREET_NAME + ", " +
                COLUMN_CITY + ", " +
                COLUMN_STATE + ") VALUES ('" +
                client.getFirstName() + "', '" +
                client.getLastName() + "', '" +
                client.getEmail() + "', '" +
                client.getPhone() + "', '" +
                client.getStreetNumber() + "', '" +
                client.getStreetName() + "', '" +
                client.getCity() + "', '" +
                client.getState() + "')";

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Client created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteClient(int id) {
        String query = "DELETE FROM " + TABLE_CLIENTS + " WHERE " + COLUMN_CLIENT_ID + " = " + id;
        // Implementation for deleting a client by ID
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Client deleted successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client client) {
        String query = "UPDATE " + TABLE_CLIENTS + " SET " +
                COLUMN_FIRST_NAME + " = '" + client.getFirstName() + "', " +
                COLUMN_LAST_NAME + " = '" + client.getLastName() + "', " +
                COLUMN_EMAIL + " = '" + client.getEmail() + "', " +
                COLUMN_PHONE + " = '" + client.getPhone() + "', " +
                COLUMN_STREET_NUMBER + " = '" + client.getStreetNumber() + "', " +
                COLUMN_STREET_NAME + " = '" + client.getStreetName() + "', " +
                COLUMN_CITY + " = '" + client.getCity() + "', " +
                COLUMN_STATE + " = '" + client.getState() + "' " +
                "WHERE " + COLUMN_CLIENT_ID + " = " + client.getId();

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Client updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
