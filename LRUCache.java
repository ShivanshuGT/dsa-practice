import java.util.HashMap;
import java.util.Map;

class Node{
    int key;
    int value;
    Node next;
    Node prev;

    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    private int capacity;

    LRUCache(int capacity){
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
        this.map = new HashMap<>();
    }

    private void insertNodeAfterHead(Node node){
        Node afterHead = this.head.next;
        this.head.next = node;
        node.prev = this.head;
        afterHead.prev = node;
        node.next = afterHead;
        // TC -> O(1)
        // SC -> O(1)
    }

    private void deleteNode(Node node){
        Node prevNode = node.prev;
        prevNode.next = this.tail;
        this.tail.prev = prevNode;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int get(int key){
        if(this.map.containsKey(key)){
            Node node = this.map.get(key);
            this.deleteNode(node);
            this.insertNodeAfterHead(node);
            return node.value;
        }else{
            return -1;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public void put(int key, int value){
        if(this.map.containsKey(key)){
            // case of update
            Node node = this.map.get(key);
            this.deleteNode(node);
            node.value = value;
            this.insertNodeAfterHead(node);
            this.map.put(key, node);
        }else{
            // insert case
            Node newNode = new Node(key, value);
            if(this.map.size() == this.capacity){
                // delete the least recently used node
                Node toBeDeleted = this.tail.prev;
                this.deleteNode(toBeDeleted);
            }
            this.insertNodeAfterHead(newNode);
            this.map.put(key, newNode);
        }
        // TC -> O(1)
        // SC -> O(1)
    }
    // TC -> O(1)
    // SC -> O(2 x capacity)

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.put(2, 6);
        cache.put(4, 7);
        cache.put(8, 11);
        cache.put(7, 10);
        System.out.println(cache.get(2));
        System.out.println(cache.get(8));
        cache.put(5, 6);
        System.out.println(cache.get(7));
        cache.put(5, 7);
    }
}
