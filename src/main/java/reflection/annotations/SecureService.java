package reflection.annotations;

public class SecureService {

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
