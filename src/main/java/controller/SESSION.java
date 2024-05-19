package controller;

import model.Admin;
import model.SupervisorTableModel;

public class SESSION {
    private static Admin loggedAdmin;

    private static SupervisorTableModel loggedSupervisor;

    private static String loggedUserEmail;

    private static int user;

    private static String admin_supervisor_lastSearch = "";

    private static int admin_reset_PasswordId = 0;
    private static String admin_reset_type = "";


    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static void setLoggedAdmin(Admin loggedAdmin) {
        SESSION.loggedAdmin = loggedAdmin;
    }

    public static SupervisorTableModel getLoggedSupervisor() {return loggedSupervisor;}
    public static void setLoggedSupervisor(SupervisorTableModel loggedSupervisor) {
        SESSION.loggedSupervisor = loggedSupervisor;
    }

    public static String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public static void setLoggedUserEmail(String loggedUserEmail) {
        SESSION.loggedUserEmail = loggedUserEmail;
    }


    public static void setUser(int user) {
        SESSION.user = user;
    }

    public static String getAdmin_supervisor_lastSearch() {
        return admin_supervisor_lastSearch;
    }
    public static void setAdmin_supervisor_lastSearch(String admin_supervisor_lastSearch) {
        SESSION.admin_supervisor_lastSearch = admin_supervisor_lastSearch;
    }

    public static void setAdmin_reset_PasswordId(int admin_reset_PasswordId) {
        SESSION.admin_reset_PasswordId = admin_reset_PasswordId;
    }
    public static void setAdmin_reset_type(String admin_reset_type) {
        SESSION.admin_reset_type = admin_reset_type;
    }
}
