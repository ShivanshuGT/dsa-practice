public class FindMiddleElementOfLinkedList {

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
        Node head = new Node(arr[0]);
        int n = arr.length;
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

    private static Node findMiddleNodeBrute(Node head){
        if(head == null){
            return null;
        }
        int length = 0;

        Node temp = head;
        while (temp != null) {
            length += 1;
            temp = temp.next;
        }

        int mid = (length/2) + 1;

        temp = head;
        while (temp != null) {
            mid -= 1;
            if(mid == 0){
                break;
            }
            temp = temp.next;
        }
        return temp;
        // TC -> O(n + n/2)
        // SC -> O(1)
    }

    private static Node findMiddleNodeOptimal(Node head){
        if(head == null){
            return null;
        }

        Node slow = head;
        Node fast = head;

        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
        // TC -> O(n/2)
        // SC -> O(1)

    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node head = buildLLFromArr(arr);

        Node middleNodeBrute = findMiddleNodeBrute(head);
        Node middleNodeOptimal = findMiddleNodeOptimal(head);

        System.out.println(middleNodeBrute == null ? "No middle node" : middleNodeBrute.data);
        System.out.println(middleNodeOptimal == null ? "No middle node" : middleNodeOptimal.data);


    }
}
