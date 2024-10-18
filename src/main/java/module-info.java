module org.example.warehousemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.warehousemanagementsystem to javafx.fxml;
    exports org.example.warehousemanagementsystem;
}