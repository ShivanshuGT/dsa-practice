import java.util.Stack;

public class CheckIfALinkedListIsPalindrome {

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


    private static boolean checkPalindromeBrute(Node head){
        if(head == null || (head.next == null)){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stack.add(temp.data);
            temp = temp.next;
        }

        temp = head;

        while (temp != null) {
            if(temp.data != stack.pop()){
                return false;
            }
            temp = temp.next;
        }
        return true;
        // TC -> O(2 x n)
        // SC -> O(n)
    }

    private static Node reverse(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Node temp = head;
        Node prev = null;
        while (temp!= null) {
            Node front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static boolean checkPalindromeOptimal(Node head){
        if(head == null || (head.next == null)){
            return true;
        }

        Node slow = head;
        Node fast = head;

        while ((fast.next != null) && (fast.next.next != null)) {
            slow = slow.next;
            fast = fast.next.next; 
        }

        // reverse the second half of the linked list
        Node newHead = reverse(slow.next);

        // comapre the first half and the second half now
        Node first = head;
        Node second = newHead;

        while (second != null) {
            if(first.data != second.data){
                // reversing back the reversed half
                reverse(newHead);
                return false;
            }
            first = first.next;
            second = second.next;
        }
        
        // reversing back the reversed half
        reverse(newHead);
        return true;
        // TC -> O(2 x n)
        // SC -> O(1)

    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 2, 1};
        Node head = buildLLFromArr(arr);

        System.out.println(checkPalindromeBrute(head));
        System.out.println(checkPalindromeOptimal(head));

    }
}
