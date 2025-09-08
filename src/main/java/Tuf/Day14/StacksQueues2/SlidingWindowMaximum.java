package Tuf.Day14.StacksQueues2;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] A = new int[]{1, 3, -1, -3, 5, 3, 6, 7}; // Input array
        int k = 3; // Window size

        int[] result = slidingWindowMax(A, k); // Call sliding window max

        // Print result
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public static int[] slidingWindowMax(int[] nums, int k) {
        // Edge case: if window size is 0 or array is empty
        if (k == 0 || nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1]; // To store final max values
        Deque<Integer> deque = new ArrayDeque<>();   // Stores indices of elements
        int ret = 0; // Index for filling result[]

        // Process each element in nums
        for (int i = 0; i < nums.length; i++) {

            // 1️⃣ Remove indices from front if they are outside the current window
            // i - k + 1 is the starting index of the current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2️⃣ Remove indices from back if their value is smaller than current element
            // because they can never be maximum if a bigger element is available
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3️⃣ Add current index to the deque
            deque.offerLast(i);

            // 4️⃣ Once we have at least k elements processed,
            // add the maximum (at front of deque) to the result
            if (i >= k - 1) {
                result[ret++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}