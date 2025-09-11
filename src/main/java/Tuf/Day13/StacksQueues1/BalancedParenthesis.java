package Tuf.Day13.StacksQueues1;

import java.util.Stack;

public class BalancedParenthesis {

    public static void main(String[] args) {
        // Multiple test cases
        String[] testInputs = {
                "(){}[]",       // Balanced
                "([{}])",       // Balanced (nested)
                "((())",        // Not balanced (extra '(')
                "{[()]}",       // Balanced
                "{[(])}",       // Not balanced (wrong order)
                "[]{}()",       // Balanced
                "[(])",         // Not balanced
                ""              // Balanced (empty string)
        };

        for (String input : testInputs) {
            System.out.println("Input: " + input +
                    " → Balanced? " + isValid(input));
        }
    }

    /**
     * Checks if the given string of parentheses is valid (balanced).
     *
     * A string is valid if:
     * - Open brackets are closed by the same type of brackets.
     * - Open brackets are closed in the correct order.
     *
     * @param s Input string containing '(', ')', '{', '}', '[' and ']'
     * @return true if the string is balanced, false otherwise
     */
    public static boolean isValid(String s) {
        char[] ch = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < ch.length; i++) {
            Character c = ch[i];

            switch (c) {
                case ']':
                    // If stack is empty or top is not matching '[', return false
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                case ')':
                    // If stack is empty or top is not matching '(', return false
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                case '}':
                    // If stack is empty or top is not matching '{', return false
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                default:
                    // For any opening bracket, push into stack
                    stack.push(c);
            }
        }

        // If stack is empty → all brackets matched properly
        return stack.isEmpty();
    }
}