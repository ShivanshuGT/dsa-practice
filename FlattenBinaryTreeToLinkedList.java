import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode buildTreeFromInput(String[] input){
        if(input.length==0 || input[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        int i = 1;
        int n = input.length;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

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
    }

    private static TreeNode flattenRecursive(TreeNode current, TreeNode prev){
        if(current == null){
            return null;
        }

        if(current.right != null){
            prev = flattenRecursive(current.right, prev);
        }
        if(current.left != null){
            prev = flattenRecursive(current.left, prev);
        }
        current.right = prev;
        current.left = null;
        prev = current;
        return prev;
        // TC -> O(n)
        // SC -> O(n) (memory stack space)
    }

    private static void flattenRecursiveApproach(TreeNode root){
        TreeNode prev = null;
        TreeNode current = root;
        flattenRecursive(current, prev);
        // TC -> O(n)
        // SC -> O(n)
    }

    private static void flattenUsingStack(TreeNode root){
        if(root == null){
            return ;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }

            if(!stack.isEmpty()){
                current.right = stack.peek();
            }
        }
        // TC -> O(n)
        // SC -> O(n)
    }

    private static void flattenUsingThreads(TreeNode root){
        if(root == null){
            return;
        }
        TreeNode current = root;
        while (current != null) {
            if(current.left != null){
                TreeNode prev = current.left;

                while (prev.right != null) {
                    // finding the rightmost node on the left hand side
                    prev = prev.right;
                }
                prev.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
        // TC -> O(n)
        // SC -> O(1)
    }

    private static List<Integer> printGeneratedLinkedList(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root ==  null){
            return result;
        }
        TreeNode current = root;
        while (current != null) {
            result.add(current.data);
            current = current.right;
        }
        return result;
        // TC -> O(n)
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
        String input[] = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        // flattenRecursiveApproach(root);
        // flattenUsingStack(root);
        flattenUsingThreads(root);
        System.out.println(printGeneratedLinkedList(root));
        sc.close();
    }
}
