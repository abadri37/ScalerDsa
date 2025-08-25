package DSA2.Recursion.Day9;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = new ArrayList<>();
        generate(s, 0, new ArrayList<>(), result);
        for (List<String> list : result) {
            System.out.println(list);
        }
    }

    public static void generate(String s, int index, List<String> list, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int end = index; end < s.length(); end++) {
            String st = s.substring(index, end + 1);
            if (isPalindrome(st)) {
                list.add(st);
                generate(s, end + 1, list, result);
                list.remove(list.size() - 1);
            }
        }
    }


    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
