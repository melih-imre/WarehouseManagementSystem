module org.example.warehousemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens org.example.warehousemanagementsystem to javafx.fxml;
    exports org.example.warehousemanagementsystem;
}