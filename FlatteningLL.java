public class FlatteningLL {

    static class Node {
        int data;
        Node next;
        Node child;

        Node(int data){
            this.data = data;
        }
        
    }

    private static Node buildLLFromArr(int[] arr){
        Node head = new Node(arr[0]);
        Node temp = head;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            temp.child = new Node(arr[i]);
            temp = temp.child;
        }
        return head;
        // TC -> O(n)
    }

    private static void traverse(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.child;
        }
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node mergeLLs(Node head1, Node head2){
        if((head1 == null) && (head2 == null)){
            return null;
        }
        Node dummyNode = new Node(-1);
        Node temp = dummyNode;

        Node t1 = head1;
        Node t2 = head2;

        while ((t1 != null) && (t2 != null)) {
            if(t1.data < t2.data){
                temp.child = t1;
                t1 = t1.child;
                temp = temp.child;

            }else{
                temp.child = t2;
                t2 = t2.child;
                temp = temp.child;
            }
            temp.next = null;
        }
        if(t1 != null){
            temp.child = t1;
        }else{
            temp.child = t2;
        }
        return dummyNode.child;
        // TC -> O(2 x m)
        // SC -> O(1)
    }

    private static Node flattenRecursive(Node head){
        if(head.next == null){
            return head;
        }

        Node mergedHead = flattenRecursive(head.next);
        return mergeLLs(head, mergedHead);
        // TC -> O(n x 2 x m)
        // SC -> O(1)
    }

    private static Node flattenLL(Node head){
        if((head == null) || (head.next == null)){
            return head;
        }

        return flattenRecursive(head);
        // TC -> O(2 x n x m)
        // SC -> O(1)
        // where n is the length and m is the depth 
    }


    public static void main(String[] args) {
        int[] arr1 = {3};
        Node head = buildLLFromArr(arr1);
        Node temp = head;
        int[] arr2 = {2, 10};
        temp.next = buildLLFromArr(arr2);
        temp = temp.next;
        int[] arr3 = {1, 7, 11, 12};
        temp.next = buildLLFromArr(arr3);
        temp = temp.next;
        int[] arr4 = {4, 9};
        temp.next = buildLLFromArr(arr4);
        temp = temp.next;
        int[] arr5 = {5, 6, 8};
        temp.next = buildLLFromArr(arr5);
        temp = temp.next;
        temp.next = null;

        Node flattenedLLHead = flattenLL(head);
        traverse(flattenedLLHead);


    }
}
