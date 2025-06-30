package DSA2.Recursion;

public class PowerofNumber {
    public static void main(String[] args) {
        int n = 2;
        int p = 10;
        System.out.println(power(n, p));
    }

    public static int power(int n, int p) {
        if (p == 0) {
            return 1;
        }
        return n * power(n, p - 1);
    }
}