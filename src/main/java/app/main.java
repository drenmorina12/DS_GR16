package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
//        stage.show();
        Navigatior.navigateNewStage(Navigatior.LOGIN);
//        Navigatior.navigate(stage, Navigatior.ADMIN_MENU);
//        Navigatior.navigate(stage, Navigatior.ADMIN_USERMENU_EDITUSER);
    }
}
