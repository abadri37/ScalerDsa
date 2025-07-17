package DSA1.Array.Day1;

public class StockBuySell {
    public static void main(String[] args) {
        // Input prices array where each element represents the stock price on that day
        int[] prices = {7, 1, 5, 3, 6, 4};  // Change this for different test cases

        // Initialize maxProfit to the smallest possible value to ensure any valid profit will be larger
        int maxProfit = Integer.MIN_VALUE;

        // Initialize minPrice to the largest possible value to track the lowest price seen so far
        int minPrice = Integer.MAX_VALUE;

        // Iterate over all prices
        for (int i = 0; i < prices.length; i++) {
            // Update the minimum price if a lower one is found
            minPrice = Math.min(minPrice, prices[i]);

            // Calculate current potential profit by selling at current price and buying at minPrice
            int currentProfit = prices[i] - minPrice;

            // Update maxProfit if the currentProfit is greater than the previous maxProfit
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        // Output the result
        System.out.println("The Maximum Profit is " + maxProfit);
    }
}
