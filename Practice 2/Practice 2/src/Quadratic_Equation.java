import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Quadratic_Equation extends Remote {
    void solve(double a, double b, double c) throws RemoteException;
}
