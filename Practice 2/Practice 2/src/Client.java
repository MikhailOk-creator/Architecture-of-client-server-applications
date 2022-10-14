import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private Client() {}
    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(null);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a: ");
            double a = scanner.nextDouble();
            System.out.println("Enter b: ");
            double b = scanner.nextDouble();
            System.out.println("Enter c: ");
            double c = scanner.nextDouble();

            // Looking up the registry for the remote object
            Quadratic_Equation stub = (Quadratic_Equation) registry.lookup("Quadratic_Equation");
            // Calling the remote method using the obtained object
            stub.solve(a, b, c);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
