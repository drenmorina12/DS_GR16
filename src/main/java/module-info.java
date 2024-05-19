module com.example.knk_project_gr10_2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    opens controller.Admin to javafx.fxml;
    exports controller.Admin;
    opens controller.Overall to javafx.fxml;
//    opens controller.Supervisor to javafx.fxml;
//    opens controller.StudentStatistics to javafx.fxml;
//    opens controller.Student to javafx.fxml;



//    opens app to javafx.fxml;
    opens app to javafx.graphics;
    exports app;
//    exports controller.Animations;
//    opens controller.Animations to javafx.graphics;
    //   opens com.example.knk_project_gr10_2024 to javafx.fxml;

    //    exports com.example.knk_project_gr10_2024;
    requires java.sql;
//    requires jfreechart;
//    requires jcommon;

//    opens model to javafx.base;

    exports model;

//    opens controller.Help to javafx.fxml;
//    opens model.dto.Supervisor to javafx.base;
}