package Tuf.Day1.Arrays1;

public class StockBuySell {
    // LeetCode 121: Best Time to Buy and Sell Stock

    public static void main(String[] args) {
        // 🧠 Question: What does each index of 'prices' represent?
        // → Each index represents the stock price on that specific day.
        int[] prices = {7, 1, 5, 3, 6, 4};  // Example input (you can modify for testing)

        // 🧠 Question: Why initialize maxProfit with Integer.MIN_VALUE?
        // → So that any real profit (even 0 or negative initially) will replace this value.
        int maxProfit = Integer.MIN_VALUE;

        // 🧠 Question: Why initialize minPrice with Integer.MAX_VALUE?
        // → Because we want to track the *lowest* stock price seen so far.
        // Starting high ensures any real price will be smaller.
        int minPrice = Integer.MAX_VALUE;

        // 🧠 Question: Why iterate through all prices?
        // → Because we must check every day's price as a potential selling point.
        for (int i = 0; i < prices.length; i++) {

            // Step 1️⃣ → Track the lowest price so far (best day to buy)
            // If today’s price is less than the previous minimum, update it
            minPrice = Math.min(minPrice, prices[i]);

            // 🧠 Question: What does currentProfit mean?
            // → It’s the profit if you bought at minPrice (lowest so far)
            //    and sold at the current day’s price.
            int currentProfit = prices[i] - minPrice;

            // Step 2️⃣ → Update maxProfit if today’s profit is better than previous ones
            // 🧠 Question: Why use Math.max here?
            // → To ensure we always store the highest profit seen so far.
            maxProfit = Math.max(maxProfit, currentProfit);

            // Optional: Uncomment for debugging
            // System.out.println("Day " + i + ": Price=" + prices[i] + ", MinPrice=" + minPrice + ", CurrentProfit=" + currentProfit + ", MaxProfit=" + maxProfit);
        }

        // Step 3️⃣ → After iterating all days, maxProfit holds the best achievable profit
        // 🧠 Question: What if prices always decrease?
        // → Then maxProfit will be 0 (no profit possible since we can’t sell before buying)
        System.out.println("The Maximum Profit is " + maxProfit);
    }
}