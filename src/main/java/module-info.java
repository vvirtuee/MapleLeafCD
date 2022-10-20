module com.example.mapleleafcd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mapleleafcd to javafx.fxml;
    exports com.example.mapleleafcd;
}