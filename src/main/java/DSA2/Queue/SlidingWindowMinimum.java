package DSA2.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem Statement:
 * Given an integer array A and a window size k,
 * return an array where each element is the minimum in the sliding window of size k.
 *
 * Example:
 * Input  : A = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 * Output : [-1, -3, -3, -3, 3, 3]
 *
 * Approach:
 * Use a Deque to store indices of useful elements in the current window.
 * The deque will always store indices of elements in increasing order.
 * The front of the deque always contains the index of the minimum element in the current window.
 */
public class SlidingWindowMinimum {

    public static int[] slidingWindowMin(int[] A, int k) {
        int[] result = new int[A.length - k + 1]; // Result array to store min of each window
        int resultIndex = 0;

        Deque<Integer> deque = new ArrayDeque<>(); // Stores indices of potential min elements

        for (int i = 0; i < A.length; i++) {

            // Remove indices that are out of the current window's bounds
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Maintain increasing order in deque (remove greater elements from back)
            while (!deque.isEmpty() && A[deque.peekLast()] > A[i]) {
                deque.pollLast();
            }

            // Add current index to the deque
            deque.offerLast(i);

            // If window is full, store the minimum (at front of deque) in result
            if (i >= k - 1) {
                result[resultIndex++] = A[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 3, -1, -3, 5, 3, 6, 7}; // Input array
        int k = 3; // Window size

        int[] result = slidingWindowMin(A, k); // Get sliding window minimums

        // Print result
        for (int min : result) {
            System.out.print(min + " ");
        }
        System.out.println();
    }
}
