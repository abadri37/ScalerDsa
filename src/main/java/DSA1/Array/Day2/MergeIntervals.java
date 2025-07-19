package DSA1.Array.Day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🔹 Leetcode-style Problem:
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 * Example:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        System.out.println("Original Intervals:");
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }

        int[][] mergedIntervals = merge(intervals);

        System.out.println("The Merged Intervals are:");
        for (int[] interval : mergedIntervals) {
            System.out.println("[" + interval[0] + " " + interval[1] + "]");
        }
    }

    public static int[][] merge(int[][] intervals) {
        // Step 1: Sort intervals based on the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Result list to store merged intervals
        List<List<Integer>> merged = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // If merged list is empty, directly add the interval
            if (merged.isEmpty()) {
                merged.add(new ArrayList<>(Arrays.asList(currentStart, currentEnd)));
            } else {
                // Get the last interval in the merged list
                List<Integer> lastInterval = merged.get(merged.size() - 1);
                int lastStart = lastInterval.get(0);
                int lastEnd = lastInterval.get(1);

                // Case 1: Overlapping intervals → merge by updating end time
                if (lastEnd >= currentStart && lastEnd <= currentEnd) {
                    merged.remove(merged.size() - 1);
                    merged.add(Arrays.asList(lastStart, currentEnd));
                }
                // Case 2: Fully overlapping (e.g., [1,10] and [3,4]) → keep larger interval
                else if (lastEnd >= currentStart && lastEnd >= currentEnd) {
                    merged.remove(merged.size() - 1);
                    merged.add(Arrays.asList(lastStart, lastEnd));
                }
                // Case 3: No overlap → add as is
                else {
                    merged.add(Arrays.asList(currentStart, currentEnd));
                }
            }
        }

        // Convert list of lists to 2D array
        int[][] result = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            result[i][0] = merged.get(i).get(0);
            result[i][1] = merged.get(i).get(1);
        }

        return result;
    }
}
