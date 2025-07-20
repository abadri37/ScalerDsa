package DSA1.Array.Day2;

import java.util.ArrayList;
import java.util.List;

public class InversionOfArray {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 7, 1, 3, 5};

        // The mergeSort method returns the total number of inversions in the array
        System.out.println("The total Inversions that exist is " + mergeSort(nums, 0, nums.length - 1));
    }

    // Merge Sort algorithm that also counts inversions
    public static int mergeSort(int[] nums, int low, int high) {
        // Base case: if the subarray has 0 or 1 element, it's already sorted
        if (low >= high) {
            return 0;
        }

        int count = 0;
        int mid = (low + high) / 2;

        // Recursively count inversions in left half
        count += mergeSort(nums, low, mid);

        // Recursively count inversions in right half
        count += mergeSort(nums, mid + 1, high);

        // Count cross inversions between left and right halves
        count += countPairs(nums, low, mid, high);

        // Merge the two halves
        merge(nums, low, mid, high);

        return count;
    }

    // Counts the number of cross-inversions (i.e., nums[i] > nums[j] for i in left and j in right half)
    public static int countPairs(int[] nums, int low, int mid, int high) {
        int j = mid + 1;
        int count = 0;

        for (int i = low; i <= mid; i++) {
            // While right element is less than current left element,
            // we found inversions: all remaining elements in right half will be smaller
            while (j <= high && nums[i] > nums[j]) {
                j++;
            }

            // All elements from mid+1 to j-1 form an inversion with nums[i]
            count += (j - (mid + 1));
        }

        return count;
    }

    // Standard merge step of merge sort which merges two sorted halves into one
    public static void merge(int[] nums, int low, int mid, int high) {
        List<Integer> list = new ArrayList<>();
        int left = low;
        int right = mid + 1;

        // Merge the two sorted subarrays into 'list'
        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                list.add(nums[left]);
                left++;
            } else {
                list.add(nums[right]);
                right++;
            }
        }

        // Append remaining elements of left half, if any
        while (left <= mid) {
            list.add(nums[left]);
            left++;
        }

        // Append remaining elements of right half, if any
        while (right <= high) {
            list.add(nums[right]);
            right++;
        }

        // Copy the merged list back into the original array
        for (int i = low; i <= high; i++) {
            nums[i] = list.get(i - low);
        }
    }
}
