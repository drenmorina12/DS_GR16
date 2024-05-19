package service.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Admin;
import model.SupervisorTableModel;
//import model.dto.Admin.AdminProfileToControllerDto;
//import model.dto.Admin.ChangePasswordOnDb;
//import model.dto.Overall.ChangePasswordDto;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Overall.ChangePasswordDto;
import model.dto.Overall.CreateUserDto;
import model.dto.Overall.UserDto;
import model.dto.User.*;
import repository.AdminRepository;
import repository.UserRepository;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
//    public static boolean signUp(SupervisorCreateInterfaceDto supervisorData) {
//        String password = supervisorData.getPassword();
//        String confirmPassword = supervisorData.getConfirmPassword();
//
//        if(!password.equals(confirmPassword)){
//            return false;
//        }
//
//
//        String salt = PasswordHasher.generateSalt();
//        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);
//
//        SupervisorCreateModelDto superVisorNewRecord = new SupervisorCreateModelDto(
//                supervisorData.getFirstName(),
//                supervisorData.getLastName(),
//                supervisorData.getEmail(),
//                salt,
//                passwordHash
//        );
//
//        try {
//            return SupervisorRepository.create(superVisorNewRecord);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static boolean supervisorIsFoundByEmail(String email){
//        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(email);
//        if(supervisor == null){
//            return false;
//        }
//        return true;
//    }

    public static ObservableList<SupervisorTableModel> searchMbikqyresi(String search) {
        try {
            if (search.isEmpty()) {
                return FXCollections.observableArrayList(UserRepository.getAllMbikqyresiArray());

            } else {
                return FXCollections.observableArrayList(UserRepository.getSupervisorsSearch(search));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public static SupervisorProfileToControllerDto getProfileInfo(String email) {
        SupervisorTableModel supervisor = UserRepository.getSupervisorByEmail(email);
        if (supervisor == null) {
            return null;
        }
        System.out.println("Admin u murr");
        return new SupervisorProfileToControllerDto(
                supervisor.getFirstName(),
                supervisor.getLastName(),
                supervisor.getEmail()
        );
    }

//    public static void changePassword(ChangePasswordDto changeData) throws InvalidPassword {
//        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(changeData.getEmail());
//
//        if(supervisor == null){
//            throw new InvalidPassword("Supervisor is not found");
//        }
//
//        if(!supervisor.getPasswordHash().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),supervisor.getSalt()))){
//            throw new InvalidPassword("Invalid Current Password");
//        }
//        if(changeData.getNewPassword().length() < 8){
//            throw new InvalidPassword("Password too short");
//        }
//        if(!changeData.getNewPassword().equals(changeData.getConfirmPassword())){
//            throw new InvalidPassword("New and Confirm do not match");
//        }
//        if(changeData.getCurrentPassword().equals(changeData.getNewPassword())) {
//            throw new InvalidPassword("Cannot be the old Password");
//        }
//
//        String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),supervisor.getSalt());
//
//        if(!SupervisorRepository.changePassword(new ChangePasswordOnDb(supervisor.getEmail(), saltedHashed))){
//            throw new InvalidPassword("Database Connection failed");
//        };
//    }

//    public static SupervisorTableModel getSupervisorByEmail(String email){
//        return SupervisorRepository.getSupervisorByEmail(email);
//    }

    public static boolean signUp(UserDto userData) throws SQLException, InvalidPassword, InvalidEmail {
        System.out.println("Te service");
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        String email = userData.getEmail();
        String emailPattern = "^.+@.+\\..+$";

        if (!password.equals(confirmPassword)) {
            return false;
        }
        if (password.length() < 8){
            throw new InvalidPassword("Password too short");
        }

        if (!email.matches(emailPattern)){
            throw new InvalidEmail("Email format invalid!");
        }



        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        CreateUserDto createUserData = new CreateUserDto(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                salt,
                passwordHash
        );

        return UserRepository.create(createUserData);
    }

    public static void changePassword(ChangePasswordDto changeData) throws InvalidPassword {
        SupervisorTableModel supervisor = UserRepository.getSupervisorByEmail(changeData.getEmail());

        if(supervisor == null){
            throw new InvalidPassword("Supervisor is not found");
        }

        if(!supervisor.getPasswordHash().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),supervisor.getSalt()))){
            throw new InvalidPassword("Invalid Current Password");
        }
        if(changeData.getNewPassword().length() < 8){
            throw new InvalidPassword("Password too short");
        }
        if(!changeData.getNewPassword().equals(changeData.getConfirmPassword())){
            throw new InvalidPassword("New and Confirm do not match");
        }
        if(changeData.getCurrentPassword().equals(changeData.getNewPassword())) {
            throw new InvalidPassword("Cannot be the old Password");
        }

        String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),supervisor.getSalt());

        if(!UserRepository.changePassword(new ChangePasswordOnDb(supervisor.getEmail(), saltedHashed))){
            throw new InvalidPassword("Database Connection failed");
        };
    }



}
