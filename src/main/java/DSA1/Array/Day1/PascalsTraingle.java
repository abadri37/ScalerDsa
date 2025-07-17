package DSA1.Array.Day1;

import java.util.ArrayList;
import java.util.List;

public class PascalsTraingle {

    public static void main(String[] args) {
        // Generate Pascal's Triangle with 5 rows
        List<List<Integer>> result = generate(5);

        // Print the result in 2D list format
        System.out.println(result);
    }

    /**
     * Generates Pascal's Triangle up to the given number of rows.
     *
     * @param numRows number of rows to generate
     * @return a 2D list representing Pascal's Triangle
     */
    public static List<List<Integer>> generate(int numRows) {
        // Final 2D list to hold all rows of Pascal's Triangle
        List<List<Integer>> result = new ArrayList<>();

        // Loop through each row from 0 to numRows - 1
        for (int row = 0; row < numRows; row++) {

            // Create a new list for the current row
            List<Integer> list = new ArrayList<>();

            // Fill the current row from column 0 to 'row' (inclusive)
            for (int col = 0; col <= row; col++) {

                // First and last positions in each row are always 1
                if (col == 0 || col == row) {
                    list.add(1);
                } else {
                    // Internal values are sum of two values from previous row:
                    // value at (row-1, col-1) + value at (row-1, col)
                    int left = result.get(row - 1).get(col - 1);
                    int right = result.get(row - 1).get(col);
                    list.add(left + right);
                }
            }

            // Add the completed row to the result
            result.add(list);
        }

        // Return the complete Pascal's Triangle
        return result;
    }
}
