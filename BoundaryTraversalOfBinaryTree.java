import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BoundaryTraversalOfBinaryTree {
    
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

    private static boolean isleafNode(TreeNode node){
        return (node.left == null) && (node.right == null);
    }

    private static void leftBoundary(TreeNode root, List<Integer> traversal){
        TreeNode node = root.left;
        while (node != null && !isleafNode(node)) {
            traversal.add(node.data);
            if(node.left != null){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        // TC -> O(H) where H is the height of the tree
        // SC -> O(1)

    }

    private static void leafNodes(TreeNode root, List<Integer> traversal){
        // We will add the leaf nodes using Inorder traversal
        if(root == null){
            return ;
        }
        leafNodes(root.left, traversal);
        if(isleafNode(root)){
            // adding only the leaf nodes
            traversal.add(root.data);
        }
        leafNodes(root.right, traversal);

        // TC -> O(n)
        // SC -> O(n) which is the memory stack space
    }

    private static void rightBoundary(TreeNode root, List<Integer> traversal){
        TreeNode node = root.right;
        Stack<Integer> stack = new Stack<>();
        while (node != null && !isleafNode(node)) {
            stack.add(node.data);
            if(node.right != null){
                node = node.right;
            }else{
                node = node.left;
            }
        }
        while (!stack.isEmpty()) {
            traversal.add(stack.pop());
        }

        // TC -> O(H) 
        // SC -> O(H)
        // where H is the height of the tree
    }

    private static List<Integer> boundaryTraversal(TreeNode root){
        List<Integer> traversal = new ArrayList<>();

        if(root == null){
            return traversal;
        }
        if(!isleafNode(root)){
            traversal.add(root.data);
        }
        if(root.left != null){
            leftBoundary(root, traversal);
        }
        leafNodes(root, traversal);
        if(root.right != null){
            rightBoundary(root, traversal);
        }
        return traversal;

        // TC -> O(n)
        // SC -> O(n)
        

    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(boundaryTraversal(root));
        
        sc.close();
    }
}
