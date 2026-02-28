import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLL {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    private static Node buildLLFromArr(int[] arr){
        Node head = new Node(arr[0]);
        Node temp = head;
        int n = arr.length;
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

    private static Node mergeTwoSortedLL(Node head1, Node head2){
        if((head1 == null) && (head2 == null)){
            return null;
        }

        Node temp1 = head1;
        Node temp2 = head2;
        Node dummyNode = new Node(-1);
        Node temp = dummyNode;

        while ((temp1 != null) && (temp2 != null)){
            if(temp1.data < temp2.data){
                temp.next = temp1;
                temp1 = temp1.next;
                temp = temp.next;
            }else{
                temp.next = temp2;
                temp2 = temp2.next;
                temp = temp.next;
            }
        }

        if(temp1 != null){
            temp.next = temp1;
        }else{
            temp.next = temp2;
        }

        return dummyNode.next;
        // TC -> O(n1  + n2)
        // SC -> O(1)
    }

    private static Node mergeKSortedLLBetter(List<Node> headList){
        Node head = headList.get(0);
        int n = headList.size();
        for (int i = 1; i < n; i++) {
            head = mergeTwoSortedLL(head, headList.get(i));
        }
        return head;
        // TC -> O(n x k x k)
        // SC -> O(1)
    }

    private static Node mergeKSortedLLOptimal(List<Node> headList){
        // using a min-heap
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.data));

        // storing the head of each list in pq
        for (Node node : headList) {
            pq.add(node);
        }

        Node dummyNode = new Node(-1);
        Node temp = dummyNode;

        while (!pq.isEmpty()) {
           Node node = pq.poll();
           temp.next = node;
           if(node.next != null) {
                pq.add(node.next);
           }
           temp = temp.next;
        }
        return dummyNode.next;
        // TC -> O((k x log(k)) + (2 x n x k x log(k))
        // SC -> O(k)


    }
    public static void main(String[] args) {

        List<Node> headList = new ArrayList<>();
        int[] arr1 = {2, 4, 6};
        int[] arr2 = {1, 5};
        int[] arr3 = {1, 1, 3, 7};
        int[] arr4 = {8};
        headList.add(buildLLFromArr(arr1));
        headList.add(buildLLFromArr(arr2));
        headList.add(buildLLFromArr(arr3));
        headList.add(buildLLFromArr(arr4));

        // Node mergedHead = mergeKSortedLLBetter(headList);
        Node mergedHead = mergeKSortedLLOptimal(headList);
        traverse(mergedHead);
        
    }
}
