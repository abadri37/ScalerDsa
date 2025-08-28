package Tuf.Day8.Greedy;

import java.util.Arrays;

public class MinimumNoOfPlatforms {

    public static void main(String[] args) {
        int N = 6; // total number of trains (not actually used in logic here)
        int[] start = new int[]{900, 940, 950, 1100, 1500, 1800};
        int[] end = new int[]{910, 1200, 1120, 1130, 1900, 2000};

        System.out.println("The Minimum No. of Platforms required is " + MinimumNoOfPlatforms(start, end));
    }

    public static int MinimumNoOfPlatforms(int[] start, int[] end) {
        if (start.length < 1 || end.length < 1) {
            return 0; // edge case: no trains → no platforms
        }

        // Step 1: Sort arrival and departure arrays separately
        // ❓ Why separately? → Because we only care about the order of events (arrivals & departures),
        // not about which arrival belongs to which departure.
        Arrays.sort(start);
        Arrays.sort(end);

        // Step 2: Initialize variables
        int result = 1;        // final answer (minimum number of platforms required at any time)
        int totalPlatforms = 1;// current number of platforms needed
        int i = 1;             // pointer for arrivals (start array)
        int j = 0;             // pointer for departures (end array)

        // Step 3: Traverse both arrays like in merge process
        // ❓ Why like merge? → Because we need to check "which event happens next" (arrival vs departure).
        while (i < start.length && j < end.length) {
            // Case 1: Next train arrives before the earliest one departs
            if (start[i] < end[j]) {
                totalPlatforms++; // need one more platform
                i++;              // move to next arrival
            }
            // Case 2: A train departs before (or at the same time) the next one arrives
            else {
                totalPlatforms--; // one platform is freed
                j++;              // move to next departure
            }

            // Track maximum platforms needed at any moment
            result = Math.max(result, totalPlatforms);
        }

        return result;
    }
}