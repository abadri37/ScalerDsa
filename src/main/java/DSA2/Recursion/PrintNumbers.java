package DSA2.Recursion;

public class PrintNumbers {
    public static void main(String[] args) {
        int n = 10;
        printDescending(n);
        System.out.println();
        printAscending(1, n);
        System.out.println();
    }

    public static void printDescending(int n) {
        if (n == 0) {
            return;
        }
        System.out.print(n + " ");
        printDescending(n - 1);
    }

    public static void printAscending(int n, int max) {
        if (n == max + 1) {
            return;
        }
        System.out.print(n + " ");
        printAscending(n + 1, max);
    }
}
