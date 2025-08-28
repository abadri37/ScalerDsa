package Tuf.Day2.Arrays2;

public class FindDuplicateNumber {
    public static void main(String[] args) {
        // Input array with one duplicate number (length = n + 1, values in range [1, n])
        int[] nums = {4, 1, 7, 9, 2, 6, 10, 3, 5, 8, 7};

        // Initialize two pointers (slow and fast)
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Detect the cycle
        // Move slow by 1 step and fast by 2 steps until they meet
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find the entrance to the cycle (duplicate number)
        // Move one pointer to the start, then move both by 1 step
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        // The point of intersection is the duplicate number
        System.out.println("The repeating number is " + slow);
    }
}
