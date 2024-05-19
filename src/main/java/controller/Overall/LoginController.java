package controller.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dto.Overall.LoginDto;
import repository.UserRepository;
import service.Admin.AdminService;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.Overall.LoginService;

import java.util.Objects;

public class LoginController {

    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label errorMessageLabel;

    @FXML
    private void handleLogin(ActionEvent event) {

        LoginDto loginDto = new LoginDto(
                this.userEmail.getText(),
                this.userPassword.getText()
        );

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        try {

            if (Objects.equals(LoginService.login(loginDto), "admin")){

                Stage stage = new Stage();
                stage.setMaximized(true);
                Navigatior.navigate(stage, Navigatior.ADMIN_MENU);
                //Admini mu ru ne session
                SESSION.setLoggedAdmin(AdminService.getAdminByEmail(this.userEmail.getText()));
                System.out.println(SESSION.getLoggedAdmin().getFirstName());
                Navigatior.closeStageAfterDelay(event, Duration.millis(1));

            } else if (Objects.equals(LoginService.login(loginDto), "supervisor")) {

                Stage stage = new Stage();
                stage.setMaximized(true);
                Navigatior.navigate(stage, Navigatior.USER_MENU);
                //Mbikqyresi mu ru ne session
                SESSION.setLoggedSupervisor(UserRepository.getSupervisorByEmail(this.userEmail.getText()));
                System.out.println(SESSION.getLoggedSupervisor().getFirstName());
                Navigatior.closeStageAfterDelay(event, Duration.millis(1));
            }
            else {
                throw new InvalidEmail("Invalid credentials");
            }

        }catch (InvalidPassword | InvalidEmail e){
            errorMessageLabel.setText(e.getMessage());
            errorMessageLabel.setVisible(true);

        }

    }

    @FXML
    public void handleSignup(ActionEvent ae) {
        Navigatior.navigateNewStage(Navigatior.SIGNUP);
        Navigatior.closeStageAfterDelay(ae, Duration.millis(1));
    }

}
