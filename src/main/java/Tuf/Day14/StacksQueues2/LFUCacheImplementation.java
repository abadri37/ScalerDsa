package Tuf.Day14.StacksQueues2;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCacheImplementation {

    // Stores the actual key-value pairs
    public Map<Integer, Integer> valueMap;

    // Stores frequency count of each key
    public Map<Integer, Integer> frequencyMap;

    // Maps frequency -> keys with that frequency (kept in insertion order)
    // LinkedHashSet ensures oldest inserted key can be evicted first
    public Map<Integer, LinkedHashSet<Integer>> insertionOrderMap;

    public int capacity;   // Maximum cache size
    public int minFreq;    // Current minimum frequency among all keys

    // Constructor
    public LFUCacheImplementation(int capacity) {
        this.capacity = capacity;
        valueMap = new HashMap<>();
        frequencyMap = new HashMap<>();
        insertionOrderMap = new HashMap<>();
        minFreq = 0;
    }

    // Retrieve value for a key
    public int get(int key) {
        if (!valueMap.containsKey(key)) {
            return -1; // Key not found
        }
        updateFrequency(key); // Update usage frequency since it is accessed
        return valueMap.get(key);
    }

    // Insert or update a key-value pair
    public void put(int key, int value) {
        if (capacity == 0) {
            return; // Edge case: no capacity
        }

        if (valueMap.containsKey(key)) {
            // Update value and frequency if key already exists
            valueMap.put(key, value);
            updateFrequency(key);
        } else {
            // If cache is full, remove the least frequently used key
            if (valueMap.size() >= capacity) {
                evictLFU();
            }
            // Add new key with frequency = 1
            valueMap.put(key, value);
            frequencyMap.put(key, 1);
            insertionOrderMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFreq = 1; // Reset min frequency for the newly added key
        }
    }

    // Update frequency of a given key
    public void updateFrequency(int key) {
        int freq = frequencyMap.get(key);
        frequencyMap.put(key, freq + 1); // Increase frequency

        // Remove key from current frequency bucket
        LinkedHashSet<Integer> orderedList = insertionOrderMap.get(freq);
        orderedList.remove(key);

        // If this frequency list becomes empty, remove it
        if (orderedList.isEmpty()) {
            insertionOrderMap.remove(freq);
            // If this was the minFreq bucket, increment minFreq
            if (freq == minFreq) minFreq++;
        }

        // Add key to the next higher frequency bucket
        insertionOrderMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);
    }

    // Evict least frequently used key
    public void evictLFU() {
        // Get keys with current minimum frequency
        LinkedHashSet<Integer> orderedList = insertionOrderMap.get(minFreq);

        // Pick the oldest inserted key from this frequency bucket
        int evictKey = orderedList.iterator().next();

        // Remove it from frequency bucket
        orderedList.remove(evictKey);
        if (orderedList.isEmpty()) {
            insertionOrderMap.remove(minFreq);
        }

        // Remove from main maps
        valueMap.remove(evictKey);
        frequencyMap.remove(evictKey);
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        LFUCacheImplementation cache = new LFUCacheImplementation(2);

        cache.put(1, 100);           // Cache: {1=100}, freq(1)=1
        cache.put(2, 200);           // Cache: {1=100, 2=200}, freq(1)=1, freq(2)=1

        cache.get(1);                // Access key 1 → freq(1)=2, freq(2)=1
        cache.put(3, 300);           // Cache is full → Evicts key 2 (lowest freq=1)
        // Cache: {1=100, 3=300}

        System.out.println(cache.get(2)); // -1 (not found, since evicted)
        System.out.println(cache.get(3)); // 300 (exists in cache)
    }
}