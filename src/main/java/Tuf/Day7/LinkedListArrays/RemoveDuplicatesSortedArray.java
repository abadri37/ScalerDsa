package Tuf.Day7.LinkedListArrays;

public class RemoveDuplicatesSortedArray {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        // Initialize count to store number of unique elements
        int count = 0;

        // 'value' stores the last seen unique element
        int value = -1;

        // Handle empty array
        if (nums.length > 0) {
            value = nums[0]; // First element is always unique
            count++;         // Count becomes 1
        }

        int pointer = 1; // Points to the next position to write a unique value

        // Traverse the array to remove duplicates
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != value) { // Found a new unique element
                count++;
                value = nums[i];
                nums[pointer] = value; // Overwrite duplicate
                pointer++;
            }
        }

        // Output the results
        System.out.println("The total unique elements in the array is " + count);
        System.out.print("The Unique elements are ");
        for (int n : nums) {
            if (count > 0) { // Print only the unique elements
                System.out.print(n + " ");
                count--;
            }
        }
        System.out.println();
    }
}