package Tuf.Day8.Greedy;

import java.util.Arrays;

public class JobSequencingProblem {
    public static void main(String[] args) {
        int[][] jobs = {
                {1, 4, 20},
                {2, 1, 10},
                {3, 1, 40},
                {4, 1, 30}
        };
        int[] result = maxJobSequencing(jobs);
        System.out.println("The Maximum number of jobs and profit that can be earned is "
                + result[0] + " " + result[1]);
    }

    public static int[] maxJobSequencing(int[][] jobs) {
        // index 0 -> JobId
        // index 1 -> Deadline
        // index 2 -> Profit
        int[] ret = new int[2]; // [jobsDone, totalProfit]

        // sort by profit descending
        Arrays.sort(jobs, (a, b) -> b[2] - a[2]);

        // find max deadline
        int maxDeadline = 0;
        for (int i = 0; i < jobs.length; i++) {
            maxDeadline = Math.max(maxDeadline, jobs[i][1]);
        }

        boolean[] slots = new boolean[maxDeadline + 1];

        // try placing each job in a free slot before or at its deadline
        for (int i = 0; i < jobs.length; i++) {
            int deadline = jobs[i][1];
            for (int j = deadline; j > 0; j--) {
                if (!slots[j]) {
                    slots[j] = true;
                    ret[0]++;         // count job
                    ret[1] += jobs[i][2]; // add profit
                    break;
                }
            }
        }
        return ret;
    }
}