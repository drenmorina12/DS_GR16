module com.example.ds_gr16 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    opens controller.Admin to javafx.fxml;
    exports controller.Admin;
//    opens controller.Overall to javafx.fxml;
//    opens controller.User to javafx.fxml;



//    opens app to javafx.fxml;
    opens app to javafx.graphics;
    exports app;

    requires java.sql;
//    requires jfreechart;
//    requires jcommon;

//    opens model to javafx.base;

    exports model;

}