package service.Admin;


import model.Admin;
//import model.dto.Admin.*;

//import model.dto.Overall.ChangePasswordDto;
//import model.dto.Admin.ResetPasswordOnDb;
//import model.filter.NjoftimPagination;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.EditAdminProfileDto;
import model.dto.Admin.ResetPasswordDto;
import model.dto.Admin.ResetPasswordOnDb;
import model.dto.Overall.ChangePasswordDto;
import model.dto.Admin.ChangePasswordOnDb;
import repository.AdminRepository;
import repository.UserRepository;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;
import controller.SESSION;
//import repository.StudentRepository;


public class AdminService {
//    public static boolean login(LoginAdminDto loginData){
//
//        Admin admin = AdminRepository.getByEmail(loginData.getEmail());
//        if(admin==null){
//            System.out.println("Admini nuk u murr");
//            return false;
//
//        }
//
//        System.out.println("Admini u murr");
//
//        String password = loginData.getPassword();
//        String salt = admin.getSalt();
//        String passwordHash = admin.getHashedPassword();
//
//        //veq krahason hash me salt a eshte i njejte
//        return PasswordHasher.compareSaltedHash(
//                password,salt,passwordHash
//        );
//    }


    public static void changePassword(ChangePasswordDto changeData) throws InvalidPassword{
        Admin admin = AdminRepository.getByEmail(changeData.getEmail());

        if(admin == null){
            throw new InvalidPassword("Admin is not found");
        }

        if(!admin.getHashedPassword().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),admin.getSalt()))){
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

        String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),admin.getSalt());

            if(!AdminRepository.changePassword(new ChangePasswordOnDb(admin.getEmail(), saltedHashed))){
            throw new InvalidPassword("Database Connection failed");
        };
    }


    public static AdminProfileToControllerDto getProfileInfo(String email) {
        Admin admin = AdminRepository.getByEmail(email);
        if (admin == null) {
            return null;
        }
        System.out.println("Admin u murr");
        return new AdminProfileToControllerDto(
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail()
        );
    }




    public static void resetPassword(ResetPasswordDto resetData) throws InvalidPassword{
        if(resetData.getNewPassword().length() < 8){
            throw new InvalidPassword("Password too short");
        }

        if(!resetData.getNewPassword().equals(resetData.getConfirmPassword())){
            throw new InvalidPassword("New and Confirm do not match!");
        }

        String salt = PasswordHasher.generateSalt();
        int id = resetData.getId();
        String passwordHash = PasswordHasher.generateSaltedHash(resetData.getNewPassword(),salt);

        ResetPasswordOnDb resetPasswordOnDb = new ResetPasswordOnDb(
                id,salt,passwordHash
        );

        if(resetData.getType().equals("Supervisor")){
            if(!UserRepository.resetPassword(resetPasswordOnDb)){
                throw new InvalidPassword("Problem in Database!");
            }
        }else if(SESSION.getAdmin_reset_type().equals("Student")){
            if(!UserRepository.resetPassword(resetPasswordOnDb)){
                throw new InvalidPassword("Problem in Database!");
            }
        }

    }



    public static boolean savePersonalDetails(EditAdminProfileDto editData) {
        return AdminRepository.savePersonalDetails(editData);
    }


    public static Admin getAdminByEmail(String email){
        return AdminRepository.getByEmail(email);
    }

}
