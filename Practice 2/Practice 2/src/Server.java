import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends Quadratic_EquationImpl {
    public Server() {}

    public static void main(String args[]) {
        try {
            // Instantiating the implementation class
            Quadratic_EquationImpl obj = new Quadratic_EquationImpl();
            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            Quadratic_Equation stub = (Quadratic_Equation) UnicastRemoteObject.exportObject(obj, 0);
            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Quadratic_Equation", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
