import java.nio.file.NotDirectoryException;

public class LinkedListImpl {

    static class Node {
        
        int data;
        Node next;

        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        Node(int data){
            this.data = data;
        }
    }

    private static Node buildLLFromArr(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node mover = head;

        for (int i = 1; i < n; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static void traverseLL(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int countLengthOfLL(Node head){
        Node temp = head;
        int ans = 0;
        while (temp != null) {
            ans += 1;
            temp = temp.next;
        }
        return ans;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node searchElementInLL(Node head, int target){
        Node temp = head;
        while (temp != null) {
            if(target == temp.data){
                return temp;
            }
            temp = temp.next;
        }
        return null;
        // TC -> O(n)
        // SC -> O(1)
    }

    // DELETION METHODS
    private static Node deleteHead(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        return head.next;
        // TC -> O(1)
        // SC -> O(1)
    }

    private static Node deleteTail(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }

        temp.next = null;
        return head;
        // TC -> O(n)
        // SC -> O(1)

    }

    private static Node deleteAtPositionK(Node head, int k){
        if(head == null){
            return null;
        }

        if(k == 1){
            // delete head
            return head.next;
        }

        int count = 0;
        Node temp = head;
        Node prev = null;

        while (temp != null) {
            count += 1;
            if(count == k){
                // delete it
                prev.next = temp.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node deleteAtValue(Node head, int val){
        if(head == null){
            return null;
        }

        if(head.data == val){
            //delete head
            return head.next;
        }

        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if(temp.data == val){
                prev.next = temp.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)

    }

    // INSERTION METHODS

    private static Node insertAtHead(Node head, int val){
        if(head == null){
            return new Node(val);
        }

        Node temp = new Node(val);
        temp.next = head;
        return temp;
        // TC -> O(1)
        // SC -> O(1)
    }

    private static Node insertAtTail(Node head, int val){
        if(head == null){
            return new Node(val);
        }
        Node temp = head;
        while (temp.next!= null) {
            temp = temp.next;
        }
        Node x = new Node(val);
        temp.next = x;
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node insertAtPositionK(Node head, int k, int val){
        if(head == null){
            if(k == 1){
                return new Node(val);
            }else{
                System.out.println("Can't insert this node");
            }
        }

        if(k == 1){
            // insert at head
            Node temp = new Node(val);
            temp.next = head;
            return temp;
        }

        int count = 0;
        Node temp = head;

        while (temp != null) {
            count += 1;
            if(count == (k-1)){
                Node x = new Node(val);
                x.next = temp.next;
                temp.next = x;
                break;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node insertAfterValueK(Node head, int k, int val){
        if(head == null){
            System.out.println("Can't insert this node");
        }

        Node temp = head;
        while (temp != null) {
            if(temp.data == k){
                Node x = new Node(val);
                x.next = temp.next;
                temp.next = x;
                break;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node insertBeforeValueK(Node head, int k, int val){
        if(head == null){
            System.out.println("Can't insert this node");
        }

        if(head.data == k){
            // insert at head
            Node temp = new Node(val);
            temp.next = head;
            return temp;
        }
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if(temp.data == k){
                Node x = new Node(val);
                prev.next = x;
                x.next = temp;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    public static void main(String[] args) {
        int[] arr = {2,7,5,3,1};
        Node head = buildLLFromArr(arr);
        // traverseLL(head);
        // System.out.println();
        // System.out.println(countLengthOfLL(head));
        // Node result = searchElementInLL(head, 5);
        // if(result == null){
        //     System.out.println("Target not found");
        // }else{
        //     System.out.println("Target found");

        // }

        // head = insertAtHead(head, 100);
        // traverseLL(head);

        // head = insertAtTail(head, 100);
        // traverseLL(head);

        // head = insertAtPositionK(head, 3, 100);
        // traverseLL(head);

        // head = insertAfterValueK(head, 1, 100);
        // traverseLL(head);

        // head = insertBeforeValueK(head, 1, 100);
        // traverseLL(head);

        // head = deleteHead(head);
        // traverseLL(head);

        // head = deleteTail(head);
        // traverseLL(head);

        // head = deleteAtPositionK(head, 3);
        // traverseLL(head);

        head = deleteAtValue(head, 7);
        traverseLL(head);
    }
}
