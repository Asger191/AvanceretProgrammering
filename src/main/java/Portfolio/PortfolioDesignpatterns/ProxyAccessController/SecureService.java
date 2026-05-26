package Portfolio.PortfolioDesignpatterns.ProxyAccessController;


import reflection.annotations.Log;
import reflection.annotations.Role;

public class SecureService implements SecureServiceInterface {

    @Role("admin")
    @Log(filename = "SecureService")
    public void deleteAllUsers() {
        System.out.println("Alle brugere er slettet.");
    }

    @Role("user")
    public void viewProfile() {
        System.out.println("Profil vist.");
    }

    public void help() {
        System.out.println("Hjælp åbnet.");
    }
}