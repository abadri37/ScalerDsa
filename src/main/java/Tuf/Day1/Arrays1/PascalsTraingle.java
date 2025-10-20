package Tuf.Day1.Arrays1;

import java.util.ArrayList;
import java.util.List;

public class PascalsTraingle {

    // 🧩 LeetCode 118: Pascal's Triangle
    // 🔹 Each row represents coefficients of the binomial expansion (a + b)^n

    public static void main(String[] args) {
        // ✅ Input: Number of rows to generate
        // ? What happens if we change this number to 1, 3, or 6?
        //   → Try changing it to see how many rows get printed.
        List<List<Integer>> result = generate(5);

        // ? How is this 2D list printed?
        //   → Each inner list represents one row of Pascal's Triangle.
        System.out.println(result);
    }

    /**
     * Generates Pascal's Triangle up to the given number of rows.
     *
     * @param numRows number of rows to generate
     * @return a 2D list representing Pascal's Triangle
     */
    public static List<List<Integer>> generate(int numRows) {
        // ✅ Final 2D list that will hold all rows of Pascal's Triangle
        // ? Why use List<List<Integer>> instead of a 2D array?
        //   → Because each row has a variable number of elements.
        List<List<Integer>> result = new ArrayList<>();

        // ✅ Outer loop → Each iteration represents one row
        // ? Why start from row = 0 and go to numRows - 1?
        //   → Because in 0-based indexing, the first row corresponds to index 0.
        for (int row = 0; row < numRows; row++) {

            // ✅ Create a new list to hold values of the current row
            // ? Why a new list for each row?
            //   → Each row must be independent; we can’t reuse the same list.
            List<Integer> list = new ArrayList<>();

            // ✅ Inner loop → Fill elements of the current row
            // ? Why does 'col' go up to 'row' (inclusive)?
            //   → Row 0 → 1 element, Row 1 → 2 elements, Row 2 → 3 elements, etc.
            for (int col = 0; col <= row; col++) {

                // ✅ First and last positions in every row are always 1
                // ? Why? Because C(n, 0) = C(n, n) = 1 in combinatorics.
                if (col == 0 || col == row) {
                    list.add(1);
                } else {
                    // ✅ For internal positions, value = sum of two numbers above it
                    // ? What do these two variables represent?
                    //   left → Value from previous row, previous column (top-left)
                    //   right → Value from previous row, same column (top-right)
                    int left = result.get(row - 1).get(col - 1);
                    int right = result.get(row - 1).get(col);

                    // ✅ Compute current cell as the sum of two numbers above it
                    // ? Why? Because of Pascal’s rule:
                    //   C(n, k) = C(n - 1, k - 1) + C(n - 1, k)
                    list.add(left + right);
                }
            }

            // ✅ Add the completed row to the final result
            // ? Why here? Because one full row must be built before moving to the next.
            result.add(list);
        }

        // ✅ Return the complete Pascal's Triangle
        // ? What happens if numRows = 0?
        //   → The result will be an empty list []
        return result;
    }
}