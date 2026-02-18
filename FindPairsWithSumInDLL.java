import java.util.ArrayList;
import java.util.List;

public class FindPairsWithSumInDLL {

    static class Node{
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node next, Node prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private static Node buildDLLFromArr(int[] arr){
        Node head = new Node(arr[0]);
        Node temp = head;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(arr[i], null, temp);
            temp.next = newNode;
            temp = newNode;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static List<List<Integer>> findPairsBrute(Node head, int target){
        if(head == null || (head.next == null)){
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Node temp1 = head;

        while(temp1.next != null){
            Node temp2 = temp1.next;
            while (temp2 != null && ((temp1.data + temp2.data) <= target)) {
                if((temp1.data + temp2.data) == target){
                    result.add(List.of(temp1.data, temp2.data));
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return result;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static List<List<Integer>> findPairsOptimal(Node head, int target){
        if(head == null || (head.next == null)){
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        Node left = head;
        Node right = head;
        // find the last node of the DLL
        while (right.next != null) {
            right = right.next;
        }

        while (left.data < right.data) {
            int sum = left.data + right.data;

            if(sum == target){
                result.add(List.of(left.data, right.data));
                left = left.next;
                right = right.prev;
            }else if(sum > target){
                right = right.prev;
            }else{
                left = left.next;
            }
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)

    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 9};
        Node head = buildDLLFromArr(arr);

        List<List<Integer>> result = findPairsBrute(head, 50);
        // List<List<Integer>> result = findPairsOptimal(head, 50);

        for (List<Integer> list : result) {
            System.out.println(list.get(0) + " " + list.get(1));
        }

    }
}
