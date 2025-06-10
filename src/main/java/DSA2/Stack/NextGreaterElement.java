package DSA2.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Statement:
 * -------------------
 * Given an array of integers, for each element, find the next greater element to its right.
 * If there is no greater element, assign -1 for that index.
 *
 * Example:
 * Input  : [4, 5, 2, 10]
 * Output : [5, 10, 10, -1]
 *
 * Explanation:
 * - Next greater for 4 is 5
 * - Next greater for 5 is 10
 * - Next greater for 2 is 10
 * - There is no next greater for 10, so output -1
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        int A[] = new int[]{4, 5, 2, 10};
        int result[] = new int[A.length];

        // Initialize all elements to -1 (default if no greater element is found)
        Arrays.fill(result, -1);

        // Stack will store indices of array elements
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            // While stack is not empty and current element is greater than element at top index
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                // Set current element as next greater for the top index
                result[stack.pop()] = A[i];
            }
            // Push current index to stack
            stack.push(i);
        }

        // Print the result array
        for (int value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
