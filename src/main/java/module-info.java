module com.example.mapleleafcd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mapleleafcd to javafx.fxml;
    exports com.example.mapleleafcd;
}