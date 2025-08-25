import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BalancedBinaryTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode buildTreeFromInput(String[] input){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int n = input.length;
        int i = 1;
        while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            if( i< n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
            if( i< n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;

        }

        return root;
    }

    private static int checkIfTreeIsBalanced(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftHeight = checkIfTreeIsBalanced(root.left);
        if(leftHeight == -1){
            return -1;
        }

        int rightHeight = checkIfTreeIsBalanced(root.right);
        if(rightHeight == -1){
            return -1;
        }

        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);

        // TC -> O(n) since we are trversing each node once
        // SC -> O(n) here the space will be the auxilliary space used by the memory stack (not any direct space)
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
        String input[] = sc.nextLine().split(("\\s+"));
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        int result = checkIfTreeIsBalanced(root);
        if(result == -1){
            System.out.println("Tree is not balanced.");
        }else{
            System.out.println("Tree is balanced.");
            
        }
        sc.close();
    }
}
