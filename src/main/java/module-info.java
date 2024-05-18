module com.example.ds_gr16 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ds_gr16 to javafx.fxml;
    exports com.example.ds_gr16;
}