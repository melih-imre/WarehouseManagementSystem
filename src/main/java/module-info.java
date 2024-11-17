module org.example.warehousemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.warehousemanagementsystem to javafx.fxml;
    exports org.example.warehousemanagementsystem;
    exports org.example.warehousemanagementsystem.Tabs;
    opens org.example.warehousemanagementsystem.Tabs to javafx.fxml;
}