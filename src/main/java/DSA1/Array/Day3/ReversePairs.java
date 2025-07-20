package DSA1.Array.Day3;

import java.util.ArrayList;
import java.util.List;

public class ReversePairs {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 3, 1};
        System.out.println("The total ReversePairs that exists is " + mergeSort(nums, 0, nums.length - 1));

        // After sorting, let's print the array to confirm it's sorted
        System.out.print("Sorted Array: ");
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }

    // Merge Sort driver method that returns the number of reverse pairs
    public static int mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return 0; // Base case: no reverse pair in one element
        }

        int count = 0;
        int mid = (low + high) / 2;

        // Recursively count in left half
        count += mergeSort(nums, low, mid);

        // Recursively count in right half
        count += mergeSort(nums, mid + 1, high);

        // Count cross pairs (i in left, j in right) such that nums[i] > 2 * nums[j]
        count += countPairs(nums, low, mid, high);

        // Merge the two sorted halves
        merge(nums, low, mid, high);

        return count;
    }

    // Count important reverse pairs across two halves: nums[i] > 2 * nums[j]
    public static int countPairs(int[] nums, int low, int mid, int high) {
        int count = 0;
        int j = mid + 1;

        for (int i = low; i <= mid; i++) {
            while (j <= high && (long) nums[i] > 2L * nums[j]) {
                j++; // Move right pointer as long as the condition holds
            }
            count += (j - (mid + 1)); // Count of valid j for current i
        }

        return count;
    }

    // Standard merge operation to maintain sorted order after counting
    public static void merge(int[] nums, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        List<Integer> list = new ArrayList<>();

        // Merge the two sorted halves into a temporary list
        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                list.add(nums[left++]);
            } else {
                list.add(nums[right++]);
            }
        }

        // Add any remaining elements
        while (left <= mid) {
            list.add(nums[left++]);
        }

        while (right <= high) {
            list.add(nums[right++]);
        }

        // Copy back to the original array
        for (int i = low; i <= high; i++) {
            nums[i] = list.get(i - low);
        }
    }
}
