package Tuf.Day14.StacksQueues2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextSmallerElement {

    public static void main(String[] args) {
        // Input array
        int[] arr = new int[]{4, 8, 5, 2, 25};

        // Create an instance and call the function
        NextSmallerElement obj = new NextSmallerElement();
        int[] result = obj.nextSmallerElements(arr);

        // Print the output
        System.out.print("Next Smaller Elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }

        // Expected Output: [2, 5, 2, -1, -1]
        // Explanation:
        // For 4 → next smaller is 2
        // For 8 → next smaller is 5
        // For 5 → next smaller is 2
        // For 2 → no smaller element → -1
        // For 25 → no smaller element → -1
    }

    /**
     * Function to find the Next Smaller Element (NSE) for each element in the array.
     *
     * The "Next Smaller Element" for an element x is the first smaller number
     * that appears to the right of x in the array.
     *
     * Example:
     * arr = [4, 8, 5, 2, 25]
     * Output = [2, 5, 2, -1, -1]
     */
    public int[] nextSmallerElements(int[] arr) {
        int[] ret = new int[arr.length]; // Result array to store NSEs
        Stack<Integer> stack = new Stack<>(); // Stack to hold elements while traversing
        Map<Integer, Integer> map = new HashMap<>(); // Map to store element -> NSE mapping

        // Step 1: Traverse the array
        for (int i = 0; i < arr.length; i++) {
            // If stack is empty, push the element
            if (stack.isEmpty()) {
                stack.push(arr[i]);
            } else {
                /**
                 * While the current element is smaller than the element on top of the stack,
                 * it means this current element is the "next smaller" for that stack element.
                 */
                while (!stack.isEmpty() && stack.peek() > arr[i]) {
                    map.put(stack.pop(), arr[i]); // Pop and map it to current smaller element
                }
                // Push the current element for future comparison
                stack.push(arr[i]);
            }
        }

        // Step 2: For remaining elements in stack (no smaller elements found)
        // assign -1 as their next smaller
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // Step 3: Construct result array using the map
        for (int i = 0; i < arr.length; i++) {
            ret[i] = map.get(arr[i]);
        }

        return ret;
    }
}