package Tuf.Day14.StacksQueues2;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheImplementation {

    // Doubly Linked List Node to store key-value pair
    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;                  // Maximum size of cache
    private Map<Integer, Node> cache;      // HashMap for O(1) access to nodes

    private final Node head;               // Dummy head of the doubly linked list
    private final Node tail;               // Dummy tail of the doubly linked list

    public LRUCacheImplementation(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // Initialize dummy head and tail
        head = new Node(0, 0);
        tail = new Node(0, 0);

        // Connect head and tail
        head.next = tail;
        tail.prev = head;
    }

    // Retrieve value from cache
    public int get(int key) {
        if (!cache.containsKey(key)) {
            System.out.println("The Cache does not contain key " + key);
            return -1; // Not found
        }
        Node node = cache.get(key);

        // Since accessed, move it to the front (most recently used)
        moveToHead(node);

        System.out.println("Key retrieved: " + node.key);
        return node.value;
    }

    // Insert or update a value in the cache
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update existing node and move it to head
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }

        // Create new node
        Node node = new Node(key, value);
        cache.put(key, node);
        addToHead(node);

        // If capacity exceeded, remove LRU node (from tail)
        if (cache.size() > capacity) {
            Node tailNode = removeTail();
            cache.remove(tailNode.key);
            System.out.println("Key removed: " + tailNode.key + " during adding new key " + key);
        }
    }

    // Move node to the front (most recently used)
    public void moveToHead(Node node) {
        removeNode(node);  // First remove it from current position
        addToHead(node);   // Then add it right after head
    }

    // Add node right after head
    public void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // Remove a node from the linked list
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Remove last node (LRU) before tail
    public Node removeTail() {
        Node last = tail.prev;
        removeNode(last);  // remove last node, not tail itself
        return last;
    }

    public static void main(String[] args) {
        // Create cache with capacity 2
        LRUCacheImplementation cache = new LRUCacheImplementation(2);

        // Insert key-value pairs
        cache.put(1, 100);             // Cache: {1=100}
        cache.put(2, 200);             // Cache: {1=100, 2=200}

        // Access key 1 → makes it most recently used
        System.out.println(cache.get(1)); // Output: 100, Cache order: {2=200, 1=100}

        // Insert key 3 → Evicts LRU (key 2)
        cache.put(3, 300);             // Cache: {1=100, 3=300}
        System.out.println(cache.get(2)); // Output: -1 (evicted)

        // Insert key 4 → Evicts LRU (key 1)
        cache.put(4, 400);             // Cache: {3=300, 4=400}
        System.out.println(cache.get(1)); // Output: -1 (evicted)

        // Access remaining keys
        System.out.println(cache.get(3)); // Output: 300
        System.out.println(cache.get(4)); // Output: 400
    }
}