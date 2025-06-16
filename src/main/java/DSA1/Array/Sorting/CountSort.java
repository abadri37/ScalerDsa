package DSA1.Array.Sorting;

import java.util.ArrayList;
import java.util.List;

public class CountSort {
    public static void main(String[] args) {
        // Input array containing non-negative integers
        int[] arr = {
                4, 2, 2, 8, 3, 3, 1, 7, 0, 5, 6, 3, 2, 4, 6, 1, 7, 2, 3, 5,
                0, 1, 2, 4, 5, 6, 8, 7, 3, 2, 1, 0, 8, 5, 6, 7, 4, 2, 3, 1
        };

        // The maximum value in the input array (range: 0 to max)
        int max = 8;

        // Step 1: Create a count array (frequency array) of size (max + 1)
        // This will store how many times each element appears in the input
        // Space Complexity: O(k), where k = max + 1
        int[] res = new int[max + 1];

        // Step 2: Count the occurrences of each element in the input array
        // Time Complexity: O(n), where n = number of elements in the input array
        for (int i = 0; i < arr.length; i++) {
            res[arr[i]]++;
        }

        // Step 3: Construct the sorted output using the frequency (count) array
        // We'll use a dynamic list to store the sorted result
        // Time Complexity: O(n), because we add each element exactly once
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            if (res[i] > 0) {
                int cnt = res[i];
                // Add element 'i' to the result list 'cnt' number of times
                while (cnt > 0) {
                    list.add(i);
                    cnt--;
                }
            }
        }

        // Step 4: Output the sorted result
        // Time Complexity: O(n) for printing all elements
        System.out.print("The sorted array is: ");
        for (Integer in : list) {
            System.out.print(in + " ");
        }
        System.out.println(); // Move to next line after output
    }
}
