import java.util.HashMap;
import java.util.Map;

class Node{
    int key;
    int value;
    Node next;
    Node prev;
    int frequency;

    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class DList{
    private Node head;
    private Node tail;
    int size;

    DList(){
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    public void deleteNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        this.size -= 1;
        // TC -> O(1)
        // SC -> O(1)
    }

    public void insertNodeAtFront(Node node){
        Node nextNode = this.head.next;
        this.head.next = node;
        node.prev = this.head;
        node.next = nextNode;
        nextNode.prev = node;
        this.size += 1;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int getSize(){
        return this.size;
    }

    public Node getTail(){
        return this.tail;
    }
    // TC -> O(1)
    // SC -> O(n)
}

public class LFUCache {

    private Map<Integer, Node> keyNodeMap;
    private Map<Integer, DList> frequencyListMap;
    private int capacity;
    private int size;
    private int minFrequency;

    LFUCache(int capacity){
        this.keyNodeMap = new HashMap<>();
        this.frequencyListMap = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.minFrequency = 0;
    }

    private void moveNodeToHigherFrequencyList(Node node){
        int currentFrequency = node.frequency;

        // remove the node from the current list
        DList currentList = this.frequencyListMap.get(node.frequency);
        currentList.deleteNode(node);
        // check if the current list became empty and that list was having the frequency = min frequency
        if((currentFrequency == this.minFrequency) && (currentList.getSize() == 0)){
            this.minFrequency += 1;
        }

        // increase the frequency by 1
        int newFrequency = currentFrequency + 1;
        node.frequency = newFrequency;

        DList higherFrequencyList = this.frequencyListMap.getOrDefault(newFrequency, new DList());
        higherFrequencyList.insertNodeAtFront(node);
        this.frequencyListMap.put(newFrequency, higherFrequencyList);
        this.keyNodeMap.put(node.key, node);
        // TC -> O(1)
        // SC -> O(1)
    }

    public int get(int key){
        if(this.keyNodeMap.containsKey(key)){
            Node node = this.keyNodeMap.get(key);
            // move the node to the higher frequency list
            moveNodeToHigherFrequencyList(node);
            return node.value;
        }else{
            return -1;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public void put(int key, int value){
        if(this.keyNodeMap.containsKey(key)){
            // update the already present node
            Node node = this.keyNodeMap.get(key);
            node.value = value;
            this.keyNodeMap.put(key, node);
            moveNodeToHigherFrequencyList(node);

        }else{
            // insert the new node

            if(this.capacity == this.size){
                // need to delete the lest recently used node
                DList list = this.frequencyListMap.get(this.minFrequency);
                Node tobeDeleted = list.getTail().prev;
                list.deleteNode(tobeDeleted);
                this.keyNodeMap.remove(tobeDeleted.key);
                this.size -= 1;
            }
            // if we are adding a new node then the min frequency is bound to be 1
            this.minFrequency = 1;
            DList minFrequqncyList = this.frequencyListMap.getOrDefault(this.minFrequency, new DList());
            Node node = new Node(key, value);
            node.frequency = this.minFrequency;
            this.keyNodeMap.put(key, node);
            minFrequqncyList.insertNodeAtFront(node);
            this.frequencyListMap.put(this.minFrequency, minFrequqncyList);
            this.size += 1;

        }
        // TC -> O(1)
        // SC -> O(1)
    }
    // TC -> O(1)
    // SC -> O(2 x n)

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1));
        cache.put(3, 30);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 40);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        
    }
}
