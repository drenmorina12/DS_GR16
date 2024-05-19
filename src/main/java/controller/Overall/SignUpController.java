package controller.Overall;

import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dto.Overall.UserDto;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.User.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private ImageView imgIcon;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private PasswordField pwdConfirmPassword;


    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    private void handleSignUp(ActionEvent ae) throws SQLException, InvalidEmail, InvalidPassword {
        UserDto userSignUpData = new UserDto(
                this.txtName.getText(),
                this.txtSurname.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        boolean response = UserService.signUp(userSignUpData);

        if(response){
            Stage stage = new Stage();
            stage.setMaximized(true);
            Navigatior.navigate(stage, Navigatior.USER_MENU);
            System.out.println("Okej");
            Navigatior.closeStageAfterDelay(ae, Duration.millis(1));
        }

    }

    @FXML
    public void initialize() {
        try {
            this.imgIcon.setImage(new Image(new FileInputStream("Images/graduation2.png")));

        } catch (FileNotFoundException e) {
            System.out.println("Image not found");
        }

    }
}
