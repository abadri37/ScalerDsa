package DSA2.Recursion;

public class ReverseString {
    public static void main(String[] args) {
        String st = "abcd";
        System.out.println(reverse(st));
    }

    public static String reverse(String st) {
        if (st == null || st.length() <= 1) {
            return st;
        }
        return reverse(st.substring(1)) + st.charAt(0);
    }
}