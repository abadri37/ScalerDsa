package DSA2.LinkedList.Day3;

public class RemoveDuplicatesSortedArray {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        // Initialize count to store number of unique elements
        int count = 0;

        // 'value' stores the last seen unique element
        int value = -1;

        // Question: What happens if the array is empty?
        // Answer: We skip the logic and count remains 0
        if (nums.length > 0) {
            value = nums[0]; // First element is always unique
            count++;         // Count becomes 1
        }

        int pointer = 1; // Points to the next position to write a unique value

        // Traverse the array from index 1 to end
        for (int i = 1; i < nums.length; i++) {
            // If current number is not equal to the last unique value
            if (nums[i] != value) {
                count++;           // Found a new unique element
                value = nums[i];   // Update the last seen unique value
                nums[pointer] = value; // Write it to the current pointer position
                pointer++;         // Move the pointer forward
            } else {
                // Question: Why do we skip duplicates?
                // Answer: Because the array should only store unique elements at the beginning
                continue;
            }
        }

        // Final Output
        System.out.println("The total unique elements in the array is " + count);
        System.out.print("The Unique elements are ");
        for (int n : nums) {
            // Print only the first 'count' elements as they are the unique ones
            if (count > 0) {
                System.out.print(n + " ");
                count--;
            }
        }
        System.out.println();
    }
}
