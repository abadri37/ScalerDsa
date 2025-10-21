package Tuf.Day2.Arrays2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🔹 Problem: Merge Overlapping Intervals (Leetcode #56)
 * -------------------------------------------------------
 * You are given an array of intervals where intervals[i] = [starti, endi].
 * Each interval represents a range between starti and endi (inclusive).
 *
 * Task:
 * -----
 * Merge all overlapping intervals and return an array of the
 * non-overlapping intervals that cover all the intervals in the input.
 *
 * Example:
 * --------
 * Input:  [[1,3], [2,6], [8,10], [15,18]]
 * Output: [[1,6], [8,10], [15,18]]
 *
 * Explanation:
 * - [1,3] and [2,6] overlap → merge into [1,6]
 * - [8,10] and [15,18] don't overlap → remain as is
 *
 * 🔹 Approach:
 * ------------
 * 1️⃣ Sort all intervals by their start time.
 * 2️⃣ Initialize a result list `merged`.
 * 3️⃣ Iterate through each interval:
 *     - If the current interval overlaps with the last one in `merged`,
 *       merge them by updating the end boundary.
 *     - Else, just add it as a new interval.
 * 4️⃣ Convert the result list into a 2D array and return.
 *
 * 🔹 Time Complexity:  O(n log n) → due to sorting
 * 🔹 Space Complexity: O(n)       → for the merged list
 */

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        // Print the original intervals
        System.out.println("Original Intervals:");
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }

        // Perform the merge operation
        int[][] mergedIntervals = merge(intervals);

        // Print the final merged result
        System.out.println("The Merged Intervals are:");
        for (int[] interval : mergedIntervals) {
            System.out.println("[" + interval[0] + " " + interval[1] + "]");
        }
    }

    /**
     * 🔹 Function: merge()
     * --------------------
     * This method merges all overlapping intervals in the given array.
     *
     * Steps:
     *  1. Sort by starting point.
     *  2. Traverse intervals and compare each with the last merged one.
     *  3. If overlapping → merge them.
     *  4. Otherwise, add the new one as a separate interval.
     */
    public static int[][] merge(int[][] intervals) {

        // Step 1️⃣: Sort intervals based on start time
        // Example: [[8,10], [1,3], [2,6]] → [[1,3], [2,6], [8,10]]
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2️⃣: List to store merged intervals dynamically
        List<List<Integer>> merged = new ArrayList<>();

        // Step 3️⃣: Traverse all intervals
        for (int i = 0; i < intervals.length; i++) {

            // Extract current interval’s start and end
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // Case A: merged list is empty → directly add the interval
            if (merged.isEmpty()) {
                merged.add(new ArrayList<>(Arrays.asList(currentStart, currentEnd)));
            } else {

                // Get the last merged interval for comparison
                List<Integer> lastInterval = merged.get(merged.size() - 1);
                int lastStart = lastInterval.get(0);
                int lastEnd = lastInterval.get(1);

                /**
                 * There can be three situations:
                 *
                 * Case 1️⃣ → Partial Overlap:
                 *   lastEnd >= currentStart && lastEnd <= currentEnd
                 *   Example: [1,3] and [2,6] → Merge into [1,6]
                 *
                 * Case 2️⃣ → Full Overlap:
                 *   lastEnd >= currentStart && lastEnd >= currentEnd
                 *   Example: [1,10] and [3,4] → Keep [1,10]
                 *
                 * Case 3️⃣ → No Overlap:
                 *   lastEnd < currentStart → Add as new interval
                 */

                if (lastEnd >= currentStart && lastEnd <= currentEnd) {
                    // Case 1️⃣: Partially overlapping intervals → update end boundary
                    merged.remove(merged.size() - 1);
                    merged.add(Arrays.asList(lastStart, currentEnd));

                } else if (lastEnd >= currentStart && lastEnd >= currentEnd) {
                    // Case 2️⃣: Fully overlapping → keep the larger one
                    merged.remove(merged.size() - 1);
                    merged.add(Arrays.asList(lastStart, lastEnd));

                } else {
                    // Case 3️⃣: Non-overlapping → simply add
                    merged.add(Arrays.asList(currentStart, currentEnd));
                }
            }
        }

        // Step 4️⃣: Convert merged list to a 2D array format
        int[][] result = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            result[i][0] = merged.get(i).get(0);
            result[i][1] = merged.get(i).get(1);
        }

        return result;
    }
}

/*
--------------------------------------------------------
🔹 DRY RUN Example: intervals = [[1,3],[2,6],[8,10],[15,18]]
--------------------------------------------------------

Step 1: Sort intervals → [[1,3], [2,6], [8,10], [15,18]]

Iteration 1:
  merged = []
  Add [1,3] → merged = [[1,3]]

Iteration 2:
  Compare [1,3] and [2,6]
  → Overlapping (3 >= 2)
  → Merge into [1,6]
  merged = [[1,6]]

Iteration 3:
  Compare [1,6] and [8,10]
  → No overlap (6 < 8)
  merged = [[1,6], [8,10]]

Iteration 4:
  Compare [8,10] and [15,18]
  → No overlap (10 < 15)
  merged = [[1,6], [8,10], [15,18]]

✅ Final Output = [[1,6], [8,10], [15,18]]
--------------------------------------------------------

🔹 Summary:
-----------
✔ Sort the intervals by start time.
✔ Merge consecutive overlapping ones by updating their end.
✔ Add non-overlapping ones as new entries.
✔ Efficient O(n log n) solution.
--------------------------------------------------------
*/
