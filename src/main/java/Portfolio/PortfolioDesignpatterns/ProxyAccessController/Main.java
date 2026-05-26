package Portfolio.PortfolioDesignpatterns.ProxyAccessController;

public class Main {
    public static void main(String[] args) {

        SecureServiceInterface adminService =
                new AccessController(new SecureService(), new User("Alice", "admin"));

        SecureServiceInterface normalService =
                new AccessController(new SecureService(), new User("Bob", "user"));

        adminService.deleteAllUsers();
        normalService.deleteAllUsers();
        normalService.viewProfile();
        normalService.help();
    }
}