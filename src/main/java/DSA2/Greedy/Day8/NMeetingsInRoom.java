package DSA2.Greedy.Day8;

import java.util.Arrays;

public class NMeetingsInRoom {
    public static void main(String[] args) {
        int N = 6; // total number of meetings (not actually used in logic here)
        int[] start = new int[]{1, 3, 0, 5, 8, 5}; // start times of meetings
        int[] end = new int[]{2, 4, 6, 7, 9, 9};   // end times of meetings

        // Q: What are we trying to find here?
        // A: The maximum number of meetings that can be attended without overlapping.
        System.out.println("The Maximum Meetings that can be accommodated is " + NMeetingsInRoom(start, end));
    }

    public static int NMeetingsInRoom(int[] start, int[] end) {
        // Q: What if there are no meetings?
        if (start.length < 1 || end.length < 1) {
            return 0;
        }

        // Step 1: Combine start[] and end[] into a 2D array
        // Q: Why 2D array?
        // A: Because each meeting has both start and end time → we want to store them together.
        int[][] meetings = new int[start.length][2];
        for (int i = 0; i < start.length; i++) {
            meetings[i][0] = start[i]; // meeting start time
            meetings[i][1] = end[i];   // meeting end time
        }

        // Step 2: Sort meetings by end time (greedy choice)
        // Q: Why sort by end time?
        // A: So that we always pick the meeting that finishes earliest,
        // leaving maximum room for the next meeting.
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        // Step 3: Select the first meeting (always possible after sorting)
        int totalMeetings = 1;
        int lastEnd = meetings[0][1]; // end time of the first chosen meeting

        // Step 4: Iterate through remaining meetings
        for (int i = 1; i < meetings.length; i++) {
            int curStart = meetings[i][0];
            int curEnd = meetings[i][1];

            // Q: When can we attend the current meeting?
            // A: Only if its start time is strictly greater than
            //    the end time of the last chosen meeting.
            if (curStart > lastEnd) {
                totalMeetings++;      // choose this meeting
                lastEnd = curEnd;     // update the last chosen meeting’s end time
            }
        }

        // Q: What does totalMeetings represent now?
        // A: The maximum number of non-overlapping meetings we can attend.
        return totalMeetings;
    }
}