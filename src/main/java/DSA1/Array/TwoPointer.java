package DSA1.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 🧠 Two Pointer Technique - Common Use Cases:
 *
 * 1. Searching in sorted arrays (like this problem).
 * 2. Reversing elements in-place.
 * 3. Removing duplicates from sorted arrays.
 * 4. Finding if a string is a palindrome.
 * 5. Merging two sorted arrays/lists.
 * 6. Problems like:
 *    - Container With Most Water
 *    - Trapping Rain Water
 *    - 3Sum / 4Sum variations
 *    - Dutch National Flag problem
 *    - Move zeroes to end (with one pointer variant)
 */
public class TwoPointer {
    public static void main(String[] args) {
        // Input: sorted array and target sum
        int[] A = {2, 7, 11, 15};
        int target = 9;

        // List to store the result pair if found
        List<Integer> list = new ArrayList<>();

        // Initialize two pointers: one at the beginning, one at the end
        int i = 0;
        int j = A.length - 1;

        // Use two-pointer technique to find the pair
        while (i < j) {
            int sum = A[i] + A[j];

            if (sum > target) {
                // If current sum is greater than target, move right pointer leftward
                j--;
            } else if (sum < target) {
                // If current sum is less than target, move left pointer rightward
                i++;
            } else {
                // If the pair adds up to the target, store it and exit the loop
                list.add(A[i]);
                list.add(A[j]);
                break;
            }
        }

        // Output the result
        if (list.isEmpty()) {
            // If no pair was found
            System.out.println("No combination exists");
        } else {
            // Print the pair that adds up to the target
            System.out.print("The elements that add up to the total sum are: ");
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
