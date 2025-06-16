package DSA2.Stack;

import java.util.Stack;

public class BalancedParanthesis {

    // Method to check if a string has balanced parentheses
    public static boolean isValid(String str) {
        char[] ch = str.toCharArray();

        // An empty string is considered valid (no unbalanced brackets)
        if (ch.length == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < ch.length; i++) {
            switch (ch[i]) {
                // If it's an opening bracket, push it to the stack
                case '(':
                case '[':
                case '{':
                    stack.push(ch[i]);
                    break;

                // If it's a closing bracket, check if it matches the top of the stack
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;

                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;

                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;

                // For any other characters, return false (not a valid bracket)
                default:
                    return false;
            }
        }

        // If the stack is empty, all brackets matched properly
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] testInputs = {
                "()",         // true
                "({[]})",     // true
                "([)]",       // false
                "{[}",        // false
                "",           // true (empty is valid)
                "((()))",     // true
                "{[(])}",     // false
                "({[({})]})"  // true
        };

        // Test each input and print the result
        for (String input : testInputs) {
            System.out.println("Input: " + input + " --> " + isValid(input));
        }
    }
}
