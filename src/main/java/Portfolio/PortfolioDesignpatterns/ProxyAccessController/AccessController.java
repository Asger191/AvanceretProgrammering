package Portfolio.PortfolioDesignpatterns.ProxyAccessController;
import reflection.annotations.Role;
import reflection.annotations.Log;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class AccessController implements SecureServiceInterface {

    private final SecureServiceInterface service;
    private final User user;

    public AccessController(SecureServiceInterface service, User user) {
        this.service = service;
        this.user = user;
    }

    @Override
    public void deleteAllUsers() {
        invoke("deleteAllUsers");
    }

    @Override
    public void viewProfile() {
        invoke("viewProfile");
    }

    @Override
    public void help() {
        invoke("help");
    }

    private void invoke(String methodName) {
        boolean allowed = false;
        try {
            Method method = service.getClass().getMethod(methodName);
            Role roleAnnotation = method.getAnnotation(Role.class);
            Log logAnnotation = method.getAnnotation(Log.class);

            if (roleAnnotation == null) {
                System.out.println("Metoden '" + methodName + "' kræver ingen rolle – kaldes.");
                method.invoke(service);
                return;
            }

            String requiredRole = roleAnnotation.value();
            if (user.getRole().equals(requiredRole)) {
                System.out.println("Adgang givet til '" + methodName + "' for bruger '" + user.getName() + "'");
                method.invoke(service);
                allowed = true;
            } else {
                System.out.println("Adgang nægtet til '" + methodName + "' for bruger '" + user.getName() + "'");
            }

            if (logAnnotation != null) {
                writeLog(logAnnotation.filename(), user.getName(), methodName, allowed);
            }

        } catch (NoSuchMethodException e) {
            System.out.println("Metoden '" + methodName + "' findes ikke.");
        } catch (Exception e) {
            System.out.println("Fejl under metodekald: " + e.getMessage());
        }
    }

    private static void writeLog(String filename, String username,
                                 String methodName, boolean allowed) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename, true))) {
            out.println(LocalDateTime.now()
                    + " | User: " + username
                    + " | Method: " + methodName
                    + " | Allowed: " + allowed);
        } catch (IOException e) {
            System.out.println("Kunne ikke skrive til logfil.");
        }
    }
}