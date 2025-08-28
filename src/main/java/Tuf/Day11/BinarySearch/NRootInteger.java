package Tuf.Day11.BinarySearch;

public class NRootInteger {
    public static void main(String[] args) {
        int n = 3;   // nth root
        int m = 27;  // number for which we want nth root
        int result = solveNRoot(m, n);
        System.out.println("Floor value of " + n + "th root of " + m + " is " + result);
    }

    // Function to compute floor of nth root of m using Binary Search
    public static int solveNRoot(int m, int n) {
        int low = 0;
        int high = m;

        while (low <= high) {
            int mid = (low + high) / 2;
            long value = calculatePowerValue(mid, n); // mid^n

            if (value == m) {
                // Perfect root found
                return mid;
            } else if (value < m) {
                // Move right if mid^n is less than m
                low = mid + 1;
            } else {
                // Move left if mid^n is greater than m
                high = mid - 1;
            }
        }

        // When no exact root exists, high will hold the floor value
        return high;
    }

    // Helper function to calculate num^n
    public static long calculatePowerValue(int num, int n) {
        long ret = 1;
        while (n > 0) {
            ret *= num;
            n--;
        }
        return ret;
    }
}