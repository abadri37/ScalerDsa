package Tuf.Day3.Arrays3;

public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2}; // Input array
        System.out.println(majorityElement(nums));  // Output the majority element
    }

    /**
     * Moore's Voting Algorithm:
     * This algorithm is used to find the majority element in an array.
     * A majority element is the element that appears more than ⌊n / 2⌋ times.
     *
     * The algorithm works in two phases:
     * 1. Find a candidate for majority element using a counter.
     * 2. (Optional) Verify the candidate actually appears more than n/2 times.
     */
    public static int majorityElement(int[] nums) {
        int count = 0;           // Counter to track balance
        int candidate = -1;      // Potential majority element

        // Phase 1: Find a candidate
        for (int i = 0; i < nums.length; i++) {
            // If count drops to zero, choose a new candidate
            if (count == 0) {
                candidate = nums[i];
            }

            // Increment count if same as candidate, else decrement
            if (candidate == nums[i]) {
                count += 1;
            } else {
                count -= 1;
            }
        }

        // Phase 2 (Optional): Verify the candidate
        // In this problem, it's guaranteed that a majority element exists,
        // so we can skip the verification step.

        return candidate; // Return the majority element
    }
}
