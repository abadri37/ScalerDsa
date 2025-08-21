package DSA2.LinkedList.Day7;

public class MaxConsecutiveOnes {

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1, 0, 1};

        int maxConsecutive = findMaxConsecutiveOnes(nums);

        System.out.println("Maximum number of consecutive 1's: " + maxConsecutive);
    }

    // Returns the maximum number of consecutive 1's in the array
    public static int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0;   // Stores maximum consecutive 1's found
        int count = 0; // Current consecutive 1's count

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;            // Increment count for 1
            } else {
                count = 0;          // Reset count if 0 encountered
            }
            ret = Math.max(ret, count); // Update maximum
        }
        return ret;
    }
}