public class MergeTwoSortedLL {

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
        Node temp = head;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            temp = newNode;
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

    private static Node merge(Node head1, Node head2){
        if((head1 == null) && (head2 == null)){
            return null;
        }

        Node dummyNode = new Node(-1);
        Node t1 = head1;
        Node t2 = head2;
        Node temp = dummyNode;

        while ((t1 != null) && (t2 != null)) {
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
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 8, 10};
        int[] arr2 = {1, 3, 3, 6, 11, 14};
        Node head1 = buildLLFromArr(arr1);
        Node head2 = buildLLFromArr(arr2);

        Node head = merge(head1, head2);
        traverse(head);
        
    }
}
