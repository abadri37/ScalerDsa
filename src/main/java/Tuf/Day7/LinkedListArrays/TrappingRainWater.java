package Tuf.Day7.LinkedListArrays;

public class TrappingRainWater {
    public static void main(String[] args) {
        // Heights of bars (like histogram)
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("The total volume of water that can be trapped is " + trap(height));
    }

    /**
     * Problem: Given n non-negative integers representing an elevation map,
     * compute how much water it can trap after raining.
     *
     * ---------------------------
     * 📌 Formula for trapped water:
     * water[i] = min(maxHeightLeft[i], maxHeightRight[i]) - height[i]
     * ---------------------------
     *
     * Steps:
     * 1. Precompute max heights to the left of each bar.
     * 2. Precompute max heights to the right of each bar.
     * 3. Apply the formula at each index and sum up.
     */
    public static int trap(int[] height) {
        int n = height.length;

        // Arrays to store max height on left and right of each bar
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill leftMax and rightMax arrays using two pointers
        int left = height[0];
        int right = height[n - 1];
        int i = 0;
        int j = n - 1;

        while (i < n && j >= 0) {
            // keep track of tallest bar so far from left
            left = Math.max(left, height[i]);
            leftMax[i] = left;
            i++;

            // keep track of tallest bar so far from right
            right = Math.max(right, height[j]);
            rightMax[j] = right;
            j--;
        }

        // Now calculate trapped water using the formula
        int result = 0;
        for (int k = 0; k < n; k++) {
            // Applying formula: water[i] = min(leftMax[i], rightMax[i]) - height[i]
            result += Math.min(leftMax[k], rightMax[k]) - height[k];
        }

        return result;
    }
}