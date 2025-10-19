package Tuf.Day14.StacksQueues2;

import java.util.Stack;

public class LargestRectangleInHistogram {
    //Leetcode 84

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;  // To keep track of maximum rectangle area
        int[] leftSmallest = new int[heights.length];  // stores index of nearest smaller element to the left
        int[] rightSmallest = new int[heights.length]; // stores index of nearest smaller element to the right

        Stack<Integer> stack = new Stack<>();

        // -----------------------------
        // Step 1: Find Nearest Smaller to Left (NSL)
        // -----------------------------
        for (int i = 0; i < heights.length; i++) {
            // Pop until we find a smaller height
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If stack is empty → no smaller element on left, use -1
            // Otherwise, nearest smaller index is stack.peek()
            leftSmallest[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Clear stack for reuse
        stack.clear();

        // -----------------------------
        // Step 2: Find Nearest Smaller to Right (NSR)
        // -----------------------------
        for (int i = heights.length - 1; i >= 0; i--) {
            // Pop until we find a smaller height
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If stack is empty → no smaller element on right, use n (length of array)
            // Otherwise, nearest smaller index is stack.peek()
            rightSmallest[i] = stack.isEmpty() ? heights.length : stack.peek();
            stack.push(i);
        }

        // -----------------------------
        // Step 3: Calculate area for each bar
        // -----------------------------
        for (int i = 0; i < heights.length; i++) {
            // Width = (right boundary - left boundary - 1)
            int width = rightSmallest[i] - leftSmallest[i] - 1;
            // Area using heights[i] as the smallest bar
            int area = width * heights[i];
            // Update maximum area
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}