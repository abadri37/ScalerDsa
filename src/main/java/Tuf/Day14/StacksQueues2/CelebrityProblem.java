package Tuf.Day14.StacksQueues2;

/**
 * 🔹 Problem: Celebrity Problem
 *
 * You are given a matrix M where M[i][j] == 1 means person i knows person j.
 * You need to find out if there is a "celebrity" among n people at a party.
 *
 * A celebrity is defined as:
 *   1️⃣ The celebrity knows no one.
 *   2️⃣ Everyone else knows the celebrity.
 *
 * If such a person exists, return their index. Otherwise, return -1.
 */
public class CelebrityProblem {

    /**
     * Function to find the celebrity in the party.
     *
     * @param M The input matrix (M[i][j] == 1 means i knows j)
     * @return Index of celebrity or -1 if no celebrity exists
     */
    public int celebrity(int[][] M) {
        int n = M.length;

        // Step 1: Assume the first person (0) is the celebrity candidate
        int candidate = 0;

        // Step 2: Find a potential celebrity
        // If candidate knows i, then candidate cannot be a celebrity → i becomes the new candidate
        for (int i = 1; i < n; i++) {
            if (know(candidate, i, M)) {
                candidate = i; // Move to next possible celebrity
            }
        }

        // Step 3: Verify if the candidate is a real celebrity
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;

            // If candidate knows i → candidate is not celebrity
            if (know(candidate, i, M)) {
                return -1;
            }

            // If i doesn't know candidate → candidate is not celebrity
            if (!know(i, candidate, M)) {
                return -1;
            }
        }

        // Step 4: If both conditions pass, return the celebrity index
        return candidate;
    }

    /**
     * Helper function to check if person i knows person j.
     *
     * @param i Person i
     * @param j Person j
     * @param M The input matrix
     * @return true if i knows j, false otherwise
     */
    public boolean know(int i, int j, int[][] M) {
        return M[i][j] == 1;
    }

    /**
     * Main method to test the Celebrity Problem implementation
     */
    public static void main(String[] args) {
        CelebrityProblem cp = new CelebrityProblem();

        // 🔸 Example Input:
        // 3 people: 0, 1, 2
        // M[i][j] = 1 means person i knows person j
        int[][] M = {
                {0, 1, 1},  // person 0 knows 1 and 2
                {0, 0, 1},  // person 1 knows 2
                {0, 0, 0}   // person 2 knows nobody
        };

        int result = cp.celebrity(M);

        if (result == -1)
            System.out.println("No celebrity found!");
        else
            System.out.println("Celebrity is person: " + result);

        // ✅ Expected Output:
        // Celebrity is person: 2
    }
}