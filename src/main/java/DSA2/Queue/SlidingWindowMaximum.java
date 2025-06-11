package DSA2.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem Statement:
 * Given an array A and an integer k, find the maximum element in every contiguous subarray of size k.
 *
 * Example:
 * Input  : A = [1,3,-1,-3,5,3,6,7], k = 3
 * Output : [3,3,5,5,6,7]
 *
 * Approach:
 * Use a Deque to store indices of useful elements for each window.
 * The front of the deque always contains the index of the maximum element in the current window.
 */
public class SlidingWindowMaximum {

    public static int[] slidingWindowMax(int[] A, int k) {
        int[] result = new int[A.length - k + 1]; // Result array to store max of each window
        int ret = 0;

        Deque<Integer> deque = new ArrayDeque<>(); // Stores indices of potential max elements

        for (int i = 0; i < A.length; i++) {

            // Remove indices which are out of this window's left bound
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove indices from back while current element is greater
            // Because they can't be the maximum if current element is bigger
            while (!deque.isEmpty() && A[deque.peekLast()] < A[i]) {
                deque.pollLast();
            }

            // Add current index at the end
            deque.offerLast(i);

            // Start recording results from when we reach a full window
            if (i >= k - 1) {
                result[ret++] = A[deque.peekFirst()]; // The front of deque has max element's index
            }
        }

        return result;
    }

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
}
