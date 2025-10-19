package Tuf.Day14.StacksQueues2;

import java.util.Stack;

public class StockSpan {
    // Stack stores pairs of [price, span]
    // Each element represents: price of stock on that day, and how many consecutive days (including today)
    // the price was less than or equal to current day's price.
    Stack<int[]> stack;

    // Constructor: initializes an empty stack
    public StockSpan() {
        stack = new Stack<>();
    }

    /**
     * next() function takes the stock price of the current day and returns
     * the "span" — number of consecutive days (up to today) for which the
     * price was less than or equal to today's price.
     *
     * @param price current day's stock price
     * @return span (number of consecutive days)
     */
    public int next(int price) {
        int span = 1; // Each day itself counts as span 1

        // While previous day's price is less than or equal to current price,
        // we combine its span with today's.
        // Example:
        // prices = [100, 80, 60, 70, 60, 75, 85]
        // when price = 75, we pop 70 because it's less than 75
        // and add its span to current day’s span.
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1]; // accumulate the span of popped element
        }

        // Push the current day's [price, span] into the stack
        stack.push(new int[]{price, span});

        // Return the computed span
        return span;
    }

    /**
     * Main method for testing the StockSpan implementation
     */
    public static void main(String[] args) {
        StockSpan stockSpan = new StockSpan();

        // Sample input (typical stock prices)
        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        System.out.println("Stock Prices: 100, 80, 60, 70, 60, 75, 85");
        System.out.print("Spans:        ");

        // Compute and print the span for each price
        for (int price : prices) {
            System.out.print(stockSpan.next(price) + " ");
        }

        // Expected Output:
        // Prices: [100, 80, 60, 70, 60, 75, 85]
        // Spans:  [1, 1, 1, 2, 1, 4, 6]
    }
}