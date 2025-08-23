import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTreeIterativeInorderTraversal {
    
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

        int n = input.length;

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
            if(i < n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;

        }
        return root;

        // TC -> O(n)
        // SC -> O(n)
    }

    private static List<Integer> inOrderIterativeTraversal(TreeNode root){
        // Left Root Right
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            if(node != null){
                stack.add(node);
                node = node.left;
            }else{
                if(stack.isEmpty()){
                    // Traversal is Over
                    break;
                }
                node = stack.pop();
                result.add(node.data);
                node = node.right;
            }
        }
        return result;

        // TC -> O(n)
        // SC -> O(height of the tree) = O(n) (in worst case when the tree is skewed)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(inOrderIterativeTraversal(root));
        sc.close();
    }
}
