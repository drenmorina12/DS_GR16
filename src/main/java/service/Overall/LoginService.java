package service.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.stage.Stage;
import model.Admin;
import model.SupervisorTableModel;
import model.dto.Overall.LoginDto;
import repository.AdminRepository;
import repository.UserRepository;
import repository.UserRepository;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

public class LoginService {

    public static final String ADMIN_EMAIL_DOMAIN = "@admin.com";
    public static final String SUPERVISOR_EMAIL_DOMAIN = "@uni-pr.edu";


    public static String login(LoginDto loginDto) throws InvalidEmail, InvalidPassword {
        String email = loginDto.getUserEmail();
        if (!isValidEmail(email)){
            throw new InvalidEmail("Invalid Email Format");
        }

        String password = loginDto.getUserPassword();
        if (password.isEmpty()){
            throw new InvalidPassword("Please type your password!");
        }

        String emailDomain = email.substring(email.indexOf("@"));


        if (emailDomain.equals(ADMIN_EMAIL_DOMAIN)){
            if (loginAsAdmin(loginDto)){
                System.out.println("Password is correct ----");
                SESSION.setLoggedUserEmail(email);
                SESSION.setUser(1);
                return "admin";
            }
            return null;
        } else {
            System.out.println("Email is USER!");
            if (loginAsSupervisor(loginDto)){
                SESSION.setLoggedUserEmail(email);
                SESSION.setUser(3);
                return "supervisor";
            }
            System.out.println("Login for user failed!");
            return null;
        }

    }

    private static boolean isValidEmail(String email){
        String emailPattern = "^.+@.+\\..+$";
        // Kontrollon nese emaili i pershtatet patternit
        return email.matches(emailPattern);
    }

    private static boolean loginAsAdmin(LoginDto loginDto) throws InvalidEmail, InvalidPassword {

        Admin admin = AdminRepository.getByEmail(loginDto.getUserEmail());

        if (admin == null){
            System.out.println("ERRON INSIDE loginAsAdmin");
            return false;
        }

        String password = loginDto.getUserPassword();

        String salt = admin.getSalt();
        String passwordHash = admin.getHashedPassword();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    private static boolean loginAsSupervisor(LoginDto loginDto) throws InvalidEmail {

        SupervisorTableModel supervisor = UserRepository.getSupervisorByEmail(loginDto.getUserEmail());
        if (supervisor == null){
            System.out.println("USER IS NULL");
            return false;
        }
        System.out.println("User is NOTTT null");
        String password = loginDto.getUserPassword();
        String salt = supervisor.getSalt();
        String passwordHash = supervisor.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }
}
