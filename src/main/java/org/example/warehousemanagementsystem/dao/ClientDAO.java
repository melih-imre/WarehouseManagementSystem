package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Client;

import java.util.ArrayList;

public interface ClientDAO {
    public ArrayList<Client> getAllClients();
    public Client getClient(int id);
}
