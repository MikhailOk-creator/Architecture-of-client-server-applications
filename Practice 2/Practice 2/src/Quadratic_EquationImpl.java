import java.rmi.RemoteException;

public class Quadratic_EquationImpl implements Quadratic_Equation {
    @Override
    public void solve(double a, double b, double c) throws RemoteException {
        double d = b * b - 4 * a * c;
        if (d > 0) {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("x1 = " + x1 + ", x2 = " + x2);
        } else if (d == 0) {
            double x = -b / (2 * a);
            System.out.println("x = " + x);
        } else {
            System.out.println("No real roots");
        }
    }
}
