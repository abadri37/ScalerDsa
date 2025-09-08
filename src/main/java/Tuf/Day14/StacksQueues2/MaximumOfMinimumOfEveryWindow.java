package Tuf.Day14.StacksQueues2;

import java.util.Arrays;
import java.util.Stack;

public class MaximumOfMinimumOfEveryWindow {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 50, 10, 70, 30};
        int[] res = maxOfMin(arr);

        System.out.println(Arrays.toString(res));
        // Expected Output: [70, 30, 20, 10, 10, 10, 10]
    }

    public static int[] maxOfMin(int[] arr) {
        int[] res = new int[arr.length];  // final result array

        // ----------------------------------------
        // Step 1: Find Previous Smaller Element (left boundary)
        // ----------------------------------------
        Stack<Integer> stack = new Stack<>();
        int[] leftSmallest = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            // Maintain a stack of increasing elements
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            // If stack empty → no smaller element → set boundary as -1
            leftSmallest[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Clear stack to reuse
        stack.clear();

        // ----------------------------------------
        // Step 2: Find Next Smaller Element (right boundary)
        // ----------------------------------------
        int[] rightSmallest = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            // Maintain increasing stack from right
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            // If stack empty → no smaller element to right → set boundary as n
            rightSmallest[i] = stack.isEmpty() ? arr.length : stack.peek();
            stack.push(i);
        }

        // ----------------------------------------
        // Step 3: Fill ans[] for each possible window length
        // ----------------------------------------
        int[] ans = new int[arr.length + 1]; // 1-based indexing

        for (int i = 0; i < arr.length; i++) {
            // length of window where arr[i] is the minimum
            int window = rightSmallest[i] - leftSmallest[i] - 1;

            // Update maximum value for this window length
            ans[window] = Math.max(arr[i], ans[window]);
        }

        // ----------------------------------------
        // Step 4: Propagate values to smaller window sizes
        // (If a larger window can achieve a value, smaller windows can at least get that value)
        // ----------------------------------------
        for (int i = arr.length - 1; i >= 0; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }

        // ----------------------------------------
        // Step 5: Build final result
        // ----------------------------------------
        for (int i = 0; i < arr.length; i++) {
            res[i] = ans[i + 1]; // shift from 1-based to 0-based
        }

        return res;
    }
}