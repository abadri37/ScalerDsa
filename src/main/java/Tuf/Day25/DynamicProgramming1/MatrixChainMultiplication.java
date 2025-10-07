package Tuf.Day25.DynamicProgramming1;

public class MatrixChainMultiplication {

    /**
     * Function to calculate the minimum number of scalar multiplications
     * required to multiply a chain of matrices.
     *
     * @param nums Array representing dimensions of matrices.
     *             If nums = [10, 15, 20, 25], it represents:
     *             A1 = 10 x 15
     *             A2 = 15 x 20
     *             A3 = 20 x 25
     * @return Minimum number of multiplications needed
     */
    public int matrixMultiplication(int[] nums) {
        int n = nums.length; // Number of matrix dimensions
        int[][] dp = new int[n][n]; // dp[i][j] stores min cost to multiply Ai...Aj

        // Base case:
        // A single matrix requires 0 multiplications
        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        // l = length of sub-chain (starting from 2 to n-1)
        // We consider all chains of length 2, 3, ..., n-1
        for (int l = 2; l < n; l++) {
            // i = starting index of sub-chain
            for (int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1; // Ending index of sub-chain
                dp[i][j] = Integer.MAX_VALUE; // Initialize to infinity

                // Try all possible split points
                // k = index where we split the chain into two parts: Ai..Ak and A(k+1)..Aj
                for (int k = i; k < j; k++) {
                    // Total cost = cost of left + cost of right + cost to multiply resulting matrices
                    int cost = dp[i][k] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j];

                    // Update minimum cost for this sub-chain
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        // dp[1][n-1] stores the minimum cost to multiply the entire chain of matrices
        return dp[1][n - 1];
    }

    // Example usage
    public static void main(String[] args) {
        MatrixChainMultiplication mcm = new MatrixChainMultiplication();

        // Example 1:
        int[] nums1 = {10, 15, 20, 25};
        // Matrices:
        // A1 = 10x15
        // A2 = 15x20
        // A3 = 20x25
        // Minimum multiplications: ((A1A2)A3) = 8000
        System.out.println("Minimum multiplications: " + mcm.matrixMultiplication(nums1));

        // Example 2:
        int[] nums2 = {40, 20, 30, 10, 30};
        // Matrices:
        // A1 = 40x20, A2 = 20x30, A3 = 30x10, A4 = 10x30
        System.out.println("Minimum multiplications: " + mcm.matrixMultiplication(nums2));
    }
}