package Tuf.Day4.Arrays4;

import java.util.HashMap;
import java.util.Map;

public class TotalSubarrayXor {
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        int K = 6;
        System.out.println("Count = " + countSubarraysWithXor(arr, K));
    }

    public static int countSubarraysWithXor(int[] nums, int target) {
        int count = 0;

        // Map to store frequency of prefix XORs seen so far
        Map<Integer, Integer> map = new HashMap<>();

        // To store prefix XOR up to current index
        int prefixXor = 0;

        for (int i = 0; i < nums.length; i++) {
            // Calculate prefix XOR up to index i
            prefixXor = prefixXor ^ nums[i];

            // If prefix XOR itself is equal to target, increment count
            if (prefixXor == target) {
                count++;
            }

            // Compute the value needed to get target XOR from current prefixXor
            // prefixXor ^ target = some previous prefixXor ⇒ subarray in between has XOR = target
            int y = prefixXor ^ target;

            // If this value has occurred before, it means there are subarrays ending at index i with XOR = target
            if (map.containsKey(y)) {
                count += map.get(y);
            }

            // Update the frequency of the current prefixXor in the map
            map.put(prefixXor, map.getOrDefault(prefixXor, 0) + 1);
        }

        return count;
    }
}
