package Tuf.Day16.Strings2;

import java.util.HashMap;
import java.util.Map;

public class CheckforAnagrams {
    public static void main(String[] args) {
        // Example 1: True case
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Input: s = " + s1 + ", t = " + t1);
        System.out.println("Output: " + checkAnagram(s1, t1)); // true

        // Example 2: False case
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Input: s = " + s2 + ", t = " + t2);
        System.out.println("Output: " + checkAnagram(s2, t2)); // false

        // Example 3: Identical strings
        String s3 = "listen";
        String t3 = "listen";
        System.out.println("Input: s = " + s3 + ", t = " + t3);
        System.out.println("Output: " + checkAnagram(s3, t3)); // true

        // Example 4: Same characters, different frequencies
        String s4 = "hello";
        String t4 = "helloo";
        System.out.println("Input: s = " + s4 + ", t = " + t4);
        System.out.println("Output: " + checkAnagram(s4, t4)); // false
    }

    /**
     * Checks if two strings are anagrams.
     * Logic:
     * 1. If lengths differ, they can't be anagrams.
     * 2. Count frequency of each character in 's' and store in map1.
     * 3. Traverse 't', decrementing counts in map1.
     *    - If a char in 't' doesn't exist in map1 → not an anagram.
     *    - If count goes negative → extra char in 't', not an anagram.
     * 4. If no mismatches, return true.
     */
    public static boolean checkAnagram(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();

        // If lengths differ, can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Count characters in string s
        for (int i = 0; i < s.length(); i++) {
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
        }

        // Decrease counts while checking characters in t
        for (int i = 0; i < t.length(); i++) {
            if (!map1.containsKey(t.charAt(i))) {
                return false; // char not in s
            } else {
                map1.put(t.charAt(i), map1.get(t.charAt(i)) - 1);
            }

            if (map1.get(t.charAt(i)) < 0) {
                return false; // extra occurrence in t
            }
        }
        return true;
    }
}