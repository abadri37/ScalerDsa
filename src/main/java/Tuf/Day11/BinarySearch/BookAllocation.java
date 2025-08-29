package Tuf.Day11.BinarySearch;

public class BookAllocation {
    public static void main(String[] args) {
        int[] pages = new int[]{12, 34, 67, 90};
        int m = 2; // number of students

        // Q: What are we trying to minimize?
        // A: The maximum number of pages assigned to any student.
        System.out.println("The Minimum Maximum Pages that can be allocated is " + solve(pages, m));
    }

    public static int solve(int[] pages, int m) {
        // low = maximum single book (since one student must get at least that much)
        // high = sum of all books (if one student takes all books)
        int low = Integer.MIN_VALUE;
        int high = 0;
        for (int page : pages) {
            low = Math.max(low, page);
            high += page;
        }

        int result = -1;

        // Q: Why binary search?
        // A: Because the answer (minimized max pages) lies in the range [low, high].
        //    If a "maxPages" value is possible, higher values are also possible,
        //    so we can binary search to minimize it.
        while (low <= high) {
            int maxPages = (low + high) / 2;

            // Q: What does isPossible() check?
            // A: Whether we can allocate books to <= m students such that
            //    no student gets more than "maxPages".
            if (isPossible(pages, m, maxPages)) {
                result = maxPages;
                high = maxPages - 1; // try smaller maximum
            } else {
                low = maxPages + 1; // increase limit
            }
        }
        return result;
    }

    public static boolean isPossible(int[] pages, int m, int maxPages) {
        int pageSum = 0;
        int students = 1; // at least 1 student will get books

        for (int i = 0; i < pages.length; i++) {
            // Q: Why do we check this condition?
            // A: If adding this book exceeds maxPages,
            //    we need to allocate it to a new student.
            if (pageSum + pages[i] > maxPages) {
                students++;
                pageSum = pages[i];

                // If we need more than m students, allocation fails
                if (students > m) {
                    return false;
                }
            } else {
                pageSum += pages[i];
            }
        }
        return true; // Successfully allocated within m students
    }
}