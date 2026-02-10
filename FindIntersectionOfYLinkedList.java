import java.util.HashMap;

public class FindIntersectionOfYLinkedList {

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

    private static Node findIntersectionBrute(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        HashMap<Node, Integer> map = new HashMap<>();

        Node temp = head1;
        while (temp != null) {
            map.put(temp, 1);
            temp = temp.next;
        }

        temp = head2;

        while (temp != null) {
            if(map.get(temp) != null){
                return temp;
            }
            temp = temp.next;
        }
        return null;
        // TC -> O((n1 + n2) x log(n))
        // SC -> O(n1)
    }

    private static Node findIntersectionBetterHelper(Node largerHead, Node smallerHead, int diff){
        Node larger = largerHead;
        Node smaller = smallerHead;
        while (diff > 0) {
            diff -= 1;
            larger = larger.next;
        }

        while (larger != smaller) {
            larger = larger.next;
            smaller = smaller.next;
        }
        return larger;
        // TC -> O(n2) considering n2 is greater than n1
        // SC -> O(1)
    }

    private static Node findIntersectionBetter(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        int n1 = 0;
        int n2 = 0;
        Node temp = head1;

        while (temp != null) {
            n1 += 1;
            temp = temp.next;
        }

        temp = head2;

        while (temp != null) {
            n2 += 1;
            temp = temp.next;
        }
        if(n1 > n2){
            return findIntersectionBetterHelper(head1, head2, n1-n2);
        }else{
            return findIntersectionBetterHelper(head2, head1, n2-n1);
        }
        // TC -> O(n1 + (2 x n2)) considering n2 is greater than n1
        // SC -> O(1)
    }

    private static Node findIntersectionOptimal(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        Node temp1 = head1;
        Node temp2 = head2;

        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;

            if(temp1 == temp2){
                return temp1;
            }

            if(temp1 == null){
                temp1 = head2;
            }
            if(temp2 == null){
                temp2 = head1;
            }
        }
        return temp1;
        // TC -> O(n1 + n2)
        // SC -> O(1)
    }


    public static void main(String[] args) {

        Node head1 = new Node(3);
        Node temp = head1;
        temp.next = new Node(1);
        temp = temp.next;
        temp.next = new Node(4);
        Node intersection = temp.next;
        temp = temp.next;
        temp.next = new Node(6);
        temp = temp.next;
        temp.next = new Node(2);
        
        Node head2 = new Node(1);
        temp = head2;
        temp.next = new Node(2);
        temp = temp.next;
        temp.next = new Node(4);
        temp = temp.next;
        temp.next = new Node(5);
        temp = temp.next;
        temp.next = intersection;

        Node resultBrute = findIntersectionBrute(head1, head2);
        Node resultBetter = findIntersectionBetter(head1, head2);
        Node resultOptimal = findIntersectionOptimal(head1, head2);
        System.out.println(resultBrute == null ? "No intersection" : resultBrute.data);
        System.out.println(resultBetter == null ? "No intersection" : resultBetter.data);
        System.out.println(resultOptimal == null ? "No intersection" : resultOptimal.data);



        
    }   
}
