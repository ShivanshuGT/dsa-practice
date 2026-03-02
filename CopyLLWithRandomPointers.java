import java.util.HashMap;

public class CopyLLWithRandomPointers {

    static class Node{
        int data;
        Node next;
        Node random;

        Node(int data){
            this.data = data;
        }
    }

    public static void traverse(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node copyListBrute(Node head){
        if(head == null){
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();
        Node temp = head;

        // storing node and its created copy in the map
        while (temp != null) {
            Node copy = new Node(temp.data);
            map.put(temp, copy);
            temp = temp.next;
        }

        temp = head;
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        // iterating over the original list
        while (temp != null) {
            Node node = map.get(temp);
            if(temp.next != null){
                node.next = map.get(temp.next);
            }else{
                node.next = null;
            }
            node.next = map.get(temp.next);
            if(temp.random != null){
                node.random = map.get(temp.random);
            }else{
                node.random = null;
            }
            res.next = node;
            res = res.next;
            temp = temp.next;
        }
        return dummyNode.next;
        // TC -> O(n x log(n))
        // SC -> O(n)
    }

    private static Node copyListOptimal(Node head){
        if(head == null){
            return null;
        }

        Node temp = head;
        // attaching new created node to the next of existing node
        while (temp != null) {
            Node newNode = new Node(temp.data);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }

        // connecting the random pointer
        temp = head;
        while (temp != null) {
            Node node = temp.next;
            if(temp.random != null){
                node.random = temp.random.next;
            }else{
                node.random = null;
            }
            temp = temp.next.next;
        }

        // restoring back the original list and retrieving the copied list
        temp = head;
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        while (temp != null) {
            Node node = temp.next;
            temp.next = node.next;
            res.next = node;
            res = res.next;
            temp = temp.next;
        }
        return dummyNode.next;
        // TC -> O(3 x n)
        // SC -> O(1)
    }
    public static void main(String[] args) {

        Node head = new Node(7);
        Node node2 = new Node(13);
        head.next = node2;
        Node node3 = new Node(11);
        node2.next = node3;
        Node node4 = new Node(10);
        node3.next = node4;
        Node node5 = new Node(1);
        node4.next = node5;

        head.random = null;
        node2.random = head;
        node3.random = node5;
        node4.random = node3;
        node5.random = head;

        // traverse(head);

        // Node copiedHead = copyListBrute(head);
        // traverse(copiedHead);
        Node copiedHead = copyListOptimal(head);
        traverse(copiedHead);
        System.out.println(copiedHead.next.random.data);
        System.out.println(copiedHead.next.next.random.data);



        
    }
}
