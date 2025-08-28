package Tuf.Day8.Greedy;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println("Maximum Children can be satisfied with the cookie are "
                + findContentChildren(g, s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        int count = 0;

        // Q: Why do we need to sort both children’s greed and cookies?
        Arrays.sort(g); // sort children by greed factor (smallest → largest)
        Arrays.sort(s); // sort cookies by size (smallest → largest)

        int i = 0; // pointer for children
        int j = 0; // pointer for cookies

        // Q: Why do we loop until either children or cookies run out?
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                // ✅ If the current cookie is big enough for the current child:
                // Q: Why do we move both pointers forward here?
                count++;  // child is satisfied
                i++;      // move to next child
                j++;      // move to next cookie
            } else {
                // ❌ If the current cookie is too small:
                // Q: Why do we only move the cookie pointer here and not the child pointer?
                j++; // try the next bigger cookie
            }
        }

        // Q: What does count represent at the end?
        return count;
    }
}