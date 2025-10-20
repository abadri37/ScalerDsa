package Tuf.Day16.Strings2;

public class CompareVersionNumbers {

    // LeetCode 165
    public static void main(String[] args) {
        // Multiple test inputs
        String[][] testCases = {
                {"1.2", "1.10"},   // v1 < v2 → -1
                {"1.01", "1.001"}, // equal → 0
                {"1.0", "1"},      // equal → 0
                {"2.1", "2.0.9"},  // v1 > v2 → 1
                {"3.4.5", "3.4.5.0"} // equal → 0
        };

        for (String[] testCase : testCases) {
            String v1 = testCase[0];
            String v2 = testCase[1];
            int result = compareVersionNumbers(v1, v2);
            System.out.println("Compare \"" + v1 + "\" vs \"" + v2 + "\" → " + result);
        }
    }

    /**
     * Compares two version numbers.
     * Rules:
     *  - Versions are split by '.'
     *  - Each segment is compared numerically
     *  - Missing segments are treated as '0'
     *
     * @param version1 first version string
     * @param version2 second version string
     * @return -1 if version1 < version2
     *          1 if version1 > version2
     *          0 if both versions are equal
     */
    public static int compareVersionNumbers(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // Compare up to the longest version
        int n = Math.max(v1.length, v2.length);

        for (int i = 0; i < n; i++) {
            // Parse segment or default to 0
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            // Compare numbers
            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
        return 0; // All segments equal
    }
}
