package app;

/* KOMENTIM I NAVIGATORIT

Kontrolleri i ribbonit edhe i menus duhet me pas ni pane qe me e mbush masane,
Navigatior.navigate(pane qe mbushet, edhe pane qe vjen) -> shembulli tek Admini qysh e kum ba

Metodat niher spi fshij amo besoj e ndreqi ni menyr qysh me u en kahmos.

*/

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;



interface OverallPages{
    public static String DASHBOARD = "overall-dashboard.fxml";
    public static String ERROR404 = "overall-error404.fxml";

    public static final String cssForActiveSection = "-fx-background-color: #A5CEF2; -fx-background-radius:10px;";
    public static final String LOGIN = "overall-login.fxml";
    public static final String CHANGEPASSWORD = "changePassword.fxml";
}

interface AdminPages{

    public final static String ADMIN_MENU = "admin-menu.fxml";

    public final static String ADMIN_SUPERVISORMENU = "admin-supervisorMenu.fxml";
    public final static String ADMIN_SUPERVISORMENU_ADDSUPERVISOR = "admin-supervisorMenu-addSupervisor.fxml";
    public final static String ADMIN_SUPERVISORMENU_EDITSUPERVISOR = "admin-superVisorMenu-showAndEditSupervisor.fxml";


}
interface UserPages {

}


public class Navigatior implements AdminPages, UserPages, OverallPages {

    public static void navigate(Event event, String form) {
        Node eventNode = (Node) event.getSource();

        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form) {
        Pane formPane = loadPane(form);
        stage.setWidth(stage.getWidth());
        stage.setHeight(stage.getHeight());
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }

    //Per kur klikohet ribboni, qe me e qu pane jo stringun
    public static void navigate(Stage stage, Pane mainPane) {
        stage.setWidth(stage.getWidth());
        stage.setHeight(stage.getHeight());
        Scene newScene = new Scene(mainPane);
        stage.setScene(newScene);
        stage.show();
    }

    //Shton Pane brenda panit aktual - BLENDI
    public static void navigate(Pane pane, String addedPane) {
        Pane formPane = loadPane(addedPane);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    //Kthen Pane me pane te shtuar aktual
    public static Pane addPane(Pane pane, String addedPane) {
        Pane formPane = loadPane(addedPane);
        //Nese e ka veq ni element(dmth ribonin) leje, nese ka ka shum fshije t fundit(tu e lan ribbonin)
        if (formPane.getChildren().size() > 1) {
            formPane.getChildren().remove(formPane.getChildren().size() - 1);
        }
        pane.getChildren().add(formPane);
        return pane;
    }

    private static Pane loadPane(String page) {
        FXMLLoader loader = new FXMLLoader(
                Navigatior.class.getResource(page)
        );
        try {
            return loader.load();
        } catch (IOException ioe) {
            System.out.println("Error ne load");
            try {
                StackPane pane = (new FXMLLoader(Navigatior.class.getResource(Navigatior.ERROR404))).load();
                pane.setMaxHeight(650);
                return pane;

            } catch (Exception e) {
                System.out.println("Nuk u gjet pane Error 404");
                return null;
            }

        }
    }

    public static void closeStageAfterDelay(Event event, Duration delay) {
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        Timeline timeline = new Timeline(new KeyFrame(delay, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        }));
        timeline.play();
    }


    public static void navigateNewStage(String page) {
        Scene scene = new Scene(loadPane(page));
        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }


}
