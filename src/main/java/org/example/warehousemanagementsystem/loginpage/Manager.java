package org.example.warehousemanagementsystem.loginpage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Manager {
    private static final String CONFIG_FILE = "dbconfig.txt";

    public static void info(String serverLocation, String dbName, String username, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write("serverLocation=" + serverLocation + "\n");
            writer.write("dbName=" + dbName + "\n");
            writer.write("username=" + username + "\n");
            writer.write("password=" + password + "\n");
        }
    }

    public static Map<String, String> loadConfig() throws IOException {
        Map<String, String> config = new HashMap<>();
        File configFile = new File(CONFIG_FILE);


        if (configFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        config.put(parts[0], parts[1]);
                    }
                }
            }


        } else {
            config.put("serverLocation", "localhost");
            config.put("dbName", "defaultDB");
            config.put("username", "root");
            config.put("password", "");
        }

        return config;
    }



    public static String getServerLocation() throws IOException {
        return loadConfig().getOrDefault("serverLocation", "localhost");
    }

    public static String getDbName() throws IOException {
        return loadConfig().getOrDefault("dbName", "defaultDB");
    }

    public static String getUsername() throws IOException {
        return loadConfig().getOrDefault("username", "root");
    }

    public static String getPassword() throws IOException {
        return loadConfig().getOrDefault("password", "");
    }
}
