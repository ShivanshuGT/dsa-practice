import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class InorderPredecessorInBinarySearchTree {
    
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode buidlTreeFromInput(String[] input){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        queue.offer(root);
        int n = input.length;
        int i = 1;

        while (i<n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(i<n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
            if(i<n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;
        }
        return root;
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findPredecessorBrute(TreeNode root, int target){
        if(root == null){
            return Integer.MIN_VALUE;
        }

        List<Integer> inorder = new ArrayList<>();
        TreeNode current = root;
        while (current!=null) {
            if(current.left == null){
                inorder.add(current.data);
                current = current.right;
            }else{
                TreeNode prev = current.left;
                while (prev.right!=null && prev.right!=current) {
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = current;
                    current = current.left;
                }else{
                    prev.right = null;
                    inorder.add(current.data);
                    current = current.right;
                }
            }
        }
        int answer = Integer.MIN_VALUE;

        for (Integer element : inorder) {
            if(element == target){
                break;
            }
            answer = element;
        }
        return answer;
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findPredecessorBetter(TreeNode root, int target){
        if(root == null){
            return Integer.MIN_VALUE;
        }
        int answer = Integer.MIN_VALUE;

        TreeNode current = root;
        while (current!=null) {
            if(current.left == null){
                if(current.data == target){
                    break;
                }
                answer = current.data;
                current = current.right;
            }else{
                TreeNode prev = current.left;
                while (prev.right!=null && prev.right!=current) {
                    prev = prev.right;
                }
                if(prev.right==null){
                    prev.right = current;
                    current = current.left;
                }else{
                    prev.right = null;
                    if(current.data == target){
                        break;
                    }
                    answer = current.data;
                    current = current.right;
                }
            }
        }
        return answer;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findPredecessorOptimal(TreeNode root, int target){
        if(root == null){
            return Integer.MIN_VALUE;
        }

        TreeNode current = root;
        TreeNode predecessor = null;
        while (current!=null) {
            if(current.data >= target){
                current = current.left;
            }else{
                predecessor = current;
                current = current.right;
            }
        }
        return predecessor == null ? Integer.MIN_VALUE : predecessor.data;
        // TC -> O(H) where H is the height of the tree
        // SC -> O(1)
    }

    private static List<List<Integer>> levelOrderTraversal(TreeNode root){
        
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int times = queue.size();
            List<Integer> list = new ArrayList<>();
            while (times > 0) {
                if(queue.peek().left != null){
                    queue.offer(queue.peek().left);
                }
                if(queue.peek().right != null){
                    queue.offer(queue.peek().right);
                }
                list.add(queue.poll().data);
                times -= 1;
            }
            ans.add(list);
        }

        return ans;

        // TC -> O(n) since we are touching each node only once
        // SC -> O(n) since we are storing the nodes in a queue
        // where n is the number of nodes in the tree
        

    
    }


    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        int target = sc.nextInt();
        TreeNode root = buidlTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(findPredecessorBrute(root, target));
        System.out.println(findPredecessorBetter(root, target));
        System.out.println(findPredecessorOptimal(root, target));
        sc.close();
    }
}
