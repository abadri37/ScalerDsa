package Tuf.Day4.Arrays4;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println("The Length of Longest Substring is " + lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0; // To store the length of the longest substring found
        char[] ch = s.toCharArray(); // Convert the string into a character array
        int start = 0; // Start index of the current window (substring)
        Map<Character, Integer> map = new HashMap<>(); // Stores the latest index of each character

        for (int end = 0; end < ch.length; end++) {
            // If character already seen and is inside current window,
            // move the start pointer to the right of the last occurrence
            if (map.containsKey(ch[end])) {
                start = Math.max(start, map.get(ch[end]) + 1);
            }

            // Update or insert the character's index into the map
            map.put(ch[end], end);

            // Update the max length of the substring seen so far
            max = Math.max(max, end - start + 1);
        }

        return max; // Return the length of the longest substring without repeating characters
    }
}
