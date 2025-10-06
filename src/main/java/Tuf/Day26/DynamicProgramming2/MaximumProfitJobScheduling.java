package Tuf.Day26.DynamicProgramming2;

import java.util.Arrays;

public class MaximumProfitJobScheduling {

    /*
     * Problem:
     * You are given n jobs, where each job has a start time, end time, and profit.
     * You cannot take two jobs that overlap in time.
     * Return the maximum profit you can earn from non-overlapping jobs.
     *
     * Intuition:
     * - This is a variant of the activity selection problem with weights (profits).
     * - For each job, we have two choices: take it or skip it.
     * - If we take it, we can only add profit from jobs that end before this job starts.
     * - We sort the jobs by their end time so that we can easily find previous compatible jobs.
     * - Use Dynamic Programming (DP) to store the maximum profit up to each job.
     */

    public static void main(String[] args) {
        // Example input: 6 jobs with start time, end time, and profit
        int[] startTime = {1, 2, 3, 3, 4, 6};
        int[] endTime = {3, 4, 5, 6, 6, 7};
        int[] profit = {50, 10, 40, 70, 30, 60};

        System.out.println(maxProfit(startTime, endTime, profit)); // Output: 120
    }

    public static int maxProfit(int[] startTime, int[] endTime, int[] profits) {
        int n = startTime.length;
        int maxProfit = 0;

        // DP array: dp[i] will store maximum profit considering first i jobs
        int[] dp = new int[n];

        // Step 1: Create Job objects to hold start, end, profit together
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profits[i]);
        }

        // Step 2: Sort jobs by end time
        // Sorting ensures that for each job, all previous jobs in array finish before or at current job's end
        Arrays.sort(jobs);

        // Base case: first job's profit
        dp[0] = jobs[0].profit;
        maxProfit = dp[0];

        // Step 3: Iterate through remaining jobs to calculate DP
        for (int i = 1; i < n; i++) {
            int currentStart = jobs[i].start;
            int currentProfit = jobs[i].profit;

            // Step 3a: Find the last job that doesn't overlap with current job
            int j = i - 1;
            while (j >= 0) {
                if (currentStart >= jobs[j].end) {
                    // If found, include its profit
                    currentProfit += dp[j];
                    break;
                }
                j--;
            }

            // Step 3b: DP formula
            // Max profit at i = max(including current job, excluding current job)
            // Excluding current job: dp[i - 1] (profit till previous job)
            dp[i] = Math.max(currentProfit, dp[i - 1]);

            // Step 3c: Keep track of global maximum profit
            maxProfit = Math.max(maxProfit, dp[i]);
        }

        return maxProfit;
    }

    // Job class with Comparable interface to sort by end time
    static class Job implements Comparable<Job> {
        int start, end, profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            return this.end - o.end; // Sort by end time ascending
        }
    }
}