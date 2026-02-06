public class RemoveNthNodeFromEndOfLinkedList {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private static Node buildLLFromArr(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < n; i++) {
            temp.next = new Node(arr[i]);
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

    private static Node removeNthNodeFromLastBrute(Node head, int n){
        if(n <= 0){
            return head;
        }
        int length = 0;

        Node temp = head;

        while (temp != null) {
            temp = temp.next;
            length += 1;
        }

        if(length == n){
            // remove head
            return head.next;
        }

        int res = length - n;
        temp = head;
        while (temp != null) {
            res -= 1;
            if(res == 0){
                // temp is at the node that is previous to the node to be deleted
                temp.next = temp.next.next;
                return head;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(len) + O(len - n)
        // SC -> O(1)

    }

    private static Node removeNthNodeFromLastOptimal(Node head, int n){
        if(n <= 0){
            return head;
        }
        Node fast = head;

        for (int i = 0; i < n; i++) {
            if(fast != null){
                fast = fast.next;
            }else{
                return head;
            }
        }

        if(fast == null){
            // remove the head scenario
            return head.next;
        }

        Node slow = head;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
        // TC -> O(len)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int n = 5;
        Node head = buildLLFromArr(arr);

        // head = removeNthNodeFromLastBrute(head, n);
        // traverse(head);

        head = removeNthNodeFromLastOptimal(head, n);
        traverse(head);


    }
}
