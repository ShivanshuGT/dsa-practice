public class SortLL {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
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

    private static Node findMiddleNode(Node head){
        // modified tortoise hare algorithm - returns first middle node
        // in case of even length LL

        Node slow = head;
        Node fast = head.next;

        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
        // TC -> O(n/2)
        // SC -> O(1)
    }

    private static Node mergeLL(Node head1, Node head2){
        if((head1 == null) && (head2 == null)){
            return null;
        }

        Node t1 = head1;
        Node t2 = head2;
        Node dummyNode = new Node(-1);
        Node temp = dummyNode;

        while ((t1 != null) && (t2 != null)){
            if(t1.data < t2.data){
                temp.next = t1;
                t1 = t1.next;
                temp = temp.next;
            }else{
                temp.next = t2;
                t2 = t2.next;
                temp = temp.next;
            }
        }
        if(t1 != null){
            temp.next = t1;
        }else{
            temp.next = t2;
        }
        return dummyNode.next;
        // TC -> O(n1 + n2)
        // SC -> O(1)
    }

    private static Node mergeSort(Node head){
        if((head == null) || (head.next == null)){
            return head;
        }

        Node middle = findMiddleNode(head);
        Node leftHead = head;
        Node rightHead = middle.next;
        middle.next = null;

        leftHead = mergeSort(leftHead);
        rightHead = mergeSort(rightHead);
        return mergeLL(leftHead, rightHead);
        // TC -> O(log(n) x (n + n/2))
        // SC -> O(log(n)) -> recursive stack space
    }
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1,5};
        Node head = buildLLFromArr(arr);

        head = mergeSort(head);
        traverse(head);
    }
}
