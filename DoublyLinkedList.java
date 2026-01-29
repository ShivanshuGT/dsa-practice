public class DoublyLinkedList {

    static class Node{
        int data;
        Node prev;
        Node next;

        Node(int data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        Node(int data){
            this.data = data;
        }

    }

    // INSERTION METHODS
    private static Node buildDLLFromArray(int[] arr){
        Node head = new Node(arr[0]);
        Node temp = head;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Node newNode = new Node(arr[i], temp, null);
            temp.next = newNode;
            temp = newNode;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node insertBeforeHead(Node head, int val){
        if(head == null){
            return new Node(val);
        }

        Node newHead = new Node(val, null, head);
        head.prev = newHead;
        return newHead;
        // TC -> O(1)
        // SC -> O(1)
    }

    private static Node insertBeforeTail(Node head, int val){
        if(head == null){
            return new Node(val);
        }

        if(head.next == null){
            // there is only one node
            Node newHead = new Node(val, null, head);
            head.prev = newHead;
            return newHead;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        Node secondLast = temp.prev;
        Node newNode = new Node(val, secondLast, temp);
        secondLast.next = newNode;
        temp.prev = newNode;
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node insertAtIndexK(Node head, int k, int val){
        if(head == null){
            if(k == 1){
                return new Node(val);
            }else{
                System.out.println("Can't insert this node");
                return null;
            }
        }

        if(k == 1){
            return insertBeforeHead(head, val);
        }

        int count = 0;
        Node temp = head;
        while (temp != null) {
            count += 1;
            if(count == k){
                Node back = temp.prev;
                Node newNode = new Node(val, back, temp);
                back.next = newNode;
                temp.prev = newNode;
                return head;
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
            return null;
        }

        if(head.data == k){
            return insertBeforeHead(head, val);
        }

        Node temp = head;
        while (temp != null) {
            if(temp.data == k){
                Node back = temp.prev;
                Node newNode = new Node(val, back, temp);
                back.next = newNode;
                temp.prev = newNode;
                return head;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    // DELETION METHODS
    private static Node deleteHead(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        Node temp = head.next;
        head.next = null;
        temp.prev = null;
        return temp;
        // TC -> O(1)
        // SC -> O(1)

    }

    private static Node deleteTail(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        Node back = temp.prev;
        back.next = null;
        temp.prev = null;
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node deleteAtIndex(Node head, int k){
        if(head == null){
            System.out.println("Can't delete this node");
            return null;
        }

        if(k == 1){
            return deleteHead(head);
        }

        int count = 0;
        Node temp = head;
        while (temp != null) {
            count += 1;
            if(count == k){
                Node back = temp.prev;
                Node front = temp.next;
                if(front == null){
                    // tail node ahs to be deleted
                    return deleteTail(head);
                }
                back.next = front;
                front.prev = back;
                temp.next = null;
                temp.prev = null;
                return head;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node deleteNodeWithValueK(Node head, int k){
        if(head == null){
            System.out.println("Can't delete this node");
            return null;
        }

        if(head.data == k){
            return deleteHead(head);
        }

        Node temp = head;
        while (temp != null) {
            if(temp.data == k){
                Node back = temp.prev;
                Node front = temp.next;
                if(front == null){
                    // tail node has to be deleted
                    return deleteTail(head);
                }else{
                    back.next = front;
                    front.prev = back;
                    temp.prev = null;
                    temp.next = null;
                    return head;
                }
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static void traverse(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        // TC -> O(n)
        // SC -> O(1)
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 7, 5};

        Node head = buildDLLFromArray(arr);
        // traverse(head);

        // head = insertBeforeHead(head, 100);
        // traverse(head);

        // head = insertBeforeTail(head, 100);
        // traverse(head);

        // head = insertAtIndexK(head, 5, 100);
        // traverse(head);

        // head = insertBeforeValueK(head, 50, 100);
        // traverse(head);

        // head = deleteHead(head);
        // traverse(head);

        // head = deleteTail(head);
        // traverse(head);

        // head = deleteAtIndex(head, 60);
        // traverse(head);

        head = deleteNodeWithValueK(head, 7);
        traverse(head);
    }
}
