package DSA2.Greedy;

import java.util.PriorityQueue;

public class FractionalKnapsack {
    public static void main(String[] args) {
        // Example input
        int[] val = {100, 60, 120};
        int[] wt = {20, 10, 30};
        long cap = 50; // Capacity of knapsack

        // Call the function and print the result
        System.out.println(fractionalKnapsack(val, wt, cap));
    }

    /**
     * Solves the Fractional Knapsack problem using a greedy approach.
     * Items are picked based on their value/weight ratio in descending order.
     *
     * @param val    array of item values
     * @param weight array of item weights
     * @param cap    maximum capacity of the knapsack
     * @return the maximum total value that can be carried
     */
    public static double fractionalKnapsack(int[] val, int[] weight, long cap) {
        double totalValue = 0.0;   // Stores final maximum value
        long totalWeight = 0;      // Tracks current weight in knapsack

        // Max-Heap (priority queue) based on ratio (value/weight).
        // Highest ratio item will be polled first.
        PriorityQueue<Item> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.ratio, a.ratio)
        );

        // Insert all items into the priority queue
        for (int i = 0; i < weight.length; i++) {
            pq.add(new Item(weight[i], val[i]));
        }

        // Process items one by one
        while (!pq.isEmpty()) {
            Item item = pq.poll(); // Get item with highest ratio

            if (totalWeight + item.weight <= cap) {
                // Case 1: Entire item can be taken
                totalWeight += item.weight;
                totalValue += item.val;
            } else {
                // Case 2: Only a fraction of the item can be taken
                long remainingCapacity = cap - totalWeight;
                totalValue += remainingCapacity * item.ratio; // take fraction
                totalWeight += remainingCapacity; // fill the knapsack
                break; // Knapsack is now full → stop
            }
        }
        return totalValue;
    }

    // Helper class to represent an item
    static class Item {
        int weight;
        int val;
        double ratio; // value-to-weight ratio

        public Item(int weight, int val) {
            this.weight = weight;
            this.val = val;
            this.ratio = (double) val / weight; // compute ratio
        }
    }
}
