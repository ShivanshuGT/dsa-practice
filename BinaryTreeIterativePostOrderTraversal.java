import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTreeIterativePostOrderTraversal {
    
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

    private static List<Integer> postOrderIterativeTraversalUsingTwoStacks(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.add(root);

        while (!st1.isEmpty()) {
            TreeNode node = st1.pop();
            st2.add(node);
            if(node.left != null){
                st1.add(node.left);
            }
            if(node.right != null){
                st1.add(node.right);
            }
        }

        while (!st2.isEmpty()) {
            result.add(st2.pop().data);
        }
        return result;

        // TC -> O(n)
        // SC -> O(2n) since we are using two stacks here
    }

    private static List<Integer> postOrderIterativeTraversalUsingOneStack(TreeNode root){
        // Left Rigth Root
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (current != null || !stack.isEmpty()) {
            if(current != null){
                stack.add(current);
                current = current.left;
            }else{
                TreeNode temp = stack.peek().right;
                if(temp == null){
                    temp = stack.peek();
                    stack.pop();
                    result.add(temp.data);

                    while (!stack.isEmpty() && (temp == stack.peek().right)) {
                        temp = stack.peek();
                        stack.pop();
                        result.add(temp.data);
                    }
                }else{
                    current = temp;
                }
            }
        }
        return result;

        // TC -> O(2n) since we are visiting each node twice
        // SC -> O(H) = O(n) (in worst case when the tree is skewed)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(postOrderIterativeTraversalUsingTwoStacks(root));
        System.out.println(postOrderIterativeTraversalUsingOneStack(root));
        sc.close();
    }
}
