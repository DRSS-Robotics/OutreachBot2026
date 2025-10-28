package frc.robot;

public class Utils {
    public static double range(double[] array) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double i : array) {
            if (i <= min) {min = i;}
            if (i >= max) {max = i;}
        }
        return max - min;
      }
}
