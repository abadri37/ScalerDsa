package DSA2.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache Implementation using a Doubly Linked List + HashMap
 *
 * - getNode(key): O(1)
 * - putNode(key, value): O(1)
 *
 * Maintains order of usage: most recently used at the front, least recently used at the back.
 */
public class LRUCacheImplementation {

    // Doubly Linked List Node structure
    class Node {
        Node prev, next;
        int key, value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private final Node head;  // Dummy head node
    private final Node tail;  // Dummy tail node

    public LRUCacheImplementation(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Create dummy head and tail nodes to simplify edge operations
        head = new Node(0, 0);
        tail = new Node(0, 0);

        // Initially connect head and tail
        head.next = tail;
        tail.prev = head;
    }

    // Add or update a key-value pair
    public void putNode(int key, int val) {
        if (cache.containsKey(key)) {
            // If key exists, update the value and move node to head
            Node node = cache.get(key);
            node.value = val;
            moveToHead(node);
            return;
        }

        // Create a new node and add it to the head (most recently used)
        Node node = new Node(key, val);
        addToHead(node);
        cache.put(key, node);

        // If cache exceeds capacity, remove the least recently used node
        if (cache.size() > capacity) {
            Node tailNode = removeTail();
            cache.remove(tailNode.key);
            System.out.println("Key removed: " + tailNode.key + " during adding new key " + key);
        }
    }

    // Retrieve value for a key if present; else return -1
    public int getNode(int key) {
        if (!cache.containsKey(key)) {
            System.out.println("The Cache does not contain key " + key);
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);  // Mark as recently used
        System.out.println("Key retrieved: " + node.key);
        return node.value;
    }

    // Move a node to the head (most recently used)
    public void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // Insert a node right after the dummy head
    public void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // Disconnect a node from its current position
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Remove the least recently used node (before dummy tail)
    public Node removeTail() {
        Node last = tail.prev;
        removeNode(last);
        return last;
    }

    // Test the implementation
    public static void main(String[] args) {
        LRUCacheImplementation cache = new LRUCacheImplementation(2);

        cache.putNode(1, 100);             // Cache: {1=100}
        cache.putNode(2, 200);             // Cache: {1=100, 2=200}
        System.out.println(cache.getNode(1)); // Access key 1 → Output: 100, Cache: {2=200, 1=100}
        cache.putNode(3, 300);             // Cache: {1=100, 3=300}, Evicts key 2
        System.out.println(cache.getNode(2)); // Output: -1 (evicted)
        cache.putNode(4, 400);             // Cache: {3=300, 4=400}, Evicts key 1
        System.out.println(cache.getNode(1)); // Output: -1 (evicted)
        System.out.println(cache.getNode(3)); // Output: 300
        System.out.println(cache.getNode(4)); // Output: 400
    }
}
