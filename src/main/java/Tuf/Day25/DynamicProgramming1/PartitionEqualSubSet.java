package Tuf.Day25.DynamicProgramming1;

public class PartitionEqualSubSet {
    public static void main(String[] args) {
        // Example 1
        int[] in = new int[]{1, 2, 3, 4};
        System.out.println(solve(in)); // true → {1,4} and {2,3}

        // Example 2
        int[] in2 = new int[]{1, 2, 5};
        System.out.println(solve(in2)); // false → sum=8 (target=4), no subset = 4

        // Example 3
        int[] in3 = new int[]{3, 1, 5, 9, 12};
        System.out.println(solve(in3)); // true → {3,9,5} and {12,1,4}

        // Example 4
        int[] in4 = new int[]{2, 2, 3, 5};
        System.out.println(solve(in4)); // false → total sum = 12 (target=6), not possible

        // Example 5
        int[] in5 = new int[]{1, 1, 1, 1, 2, 2};
        System.out.println(solve(in5)); // true → {1,1,2} and {1,1,2}
    }

    // Wrapper function to check if array can be partitioned into equal sum subsets
    public static boolean solve(int[] in) {
        int sum = 0;
        for (int i : in) {
            sum += i;
        }

        // If sum is odd, partition is not possible
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2; // Each subset must equal target
        // dp[index][sum] → whether we can make "sum" using elements from index..end
        Boolean[][] dp = new Boolean[in.length][target + 1];

        return solve(in, target, 0, dp);
    }

    /**
     * Recursive function to check subset sum
     *
     * @param in    array of numbers
     * @param sum   remaining sum we want to form
     * @param index current index of array
     * @param dp    memoization table
     * @return true if subset found, false otherwise
     */
    public static boolean solve(int[] in, int sum, int index, Boolean[][] dp) {
        // ✅ Base Case 1: sum reached → subset found
        if (sum == 0) {
            return true;
        }

        // ✅ Base Case 2: ran out of items → no subset
        if (index >= in.length) {
            return false;
        }

        // ✅ If already computed, return cached result
        if (dp[index][sum] != null) {
            return dp[index][sum];
        }

        // ✅ Option 1: include current element (if it does not exceed sum)
        if (in[index] <= sum) {
            if (solve(in, sum - in[index], index + 1, dp)) {
                dp[index][sum] = true; // store result
                return true;
            }
        }

        // ✅ Option 2: exclude current element
        dp[index][sum] = solve(in, sum, index + 1, dp);

        return dp[index][sum];
    }
}
