package DSA2.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Statement:
 * -------------------
 * Given an array of integers, for each element, find the next smaller element to its right.
 * If there is no smaller element, assign -1 for that index.
 *
 * Example:
 * Input  : [4, 8, 5, 2, 25]
 * Output : [2, 5, 2, -1, -1]
 *
 * Explanation:
 * - Next smaller for 4 is 2
 * - Next smaller for 8 is 5
 * - Next smaller for 5 is 2
 * - No smaller element after 2, so -1
 * - No smaller element after 25, so -1
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class NextSmallerElement {

    public static void main(String[] args) {
        int[] A = new int[]{4, 8, 5, 2, 25};
        int[] result = new int[A.length];

        // Initialize all elements to -1 (default if no smaller element is found)
        Arrays.fill(result, -1);

        // Stack to store indices of elements for which we're finding the next smaller
        Stack<Integer> stack = new Stack<>();

        // Iterate through the array
        for (int i = 0; i < A.length; i++) {
            // While current element is smaller than the element at index on top of the stack
            while (!stack.isEmpty() && A[i] <= A[stack.peek()]) {
                result[stack.pop()] = A[i]; // Set current element as next smaller
            }
            stack.push(i); // Push current index
        }

        // Print the result array
        for (int value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
