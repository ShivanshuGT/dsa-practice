import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DiameterOfBinaryTree {
     
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
       int i = 1;
       int n = input.length;

       while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            // for left child
            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
            // for right child
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

    private static int findHeight(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        return 1 + Math.max(leftHeight, rightHeight);

        // TC -> O(n)
        // SC -> O(n)
    }

    private static void findDiamaterOfBinaryTreeBrute(TreeNode root, int[] diameter){
        // here diameter is passed by refernce using an array, since we cant pass variables as reference in Java
        if(root == null){
            return ;
        }

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);

        findDiamaterOfBinaryTreeBrute(root.left, diameter);
        findDiamaterOfBinaryTreeBrute(root.right, diameter);

        // TC -> O(n x n) since we are finding height at each node
        // SC -> O(n x n) since the space complexity of findHeight() is itself O(n) and 
        // we are calling this findHeight() for all the nodes

    }

    private static int findDiamaterOfBinaryTreeOptimal(TreeNode root, int diameter[]){
        // here diameter is passed by reference using an array, since we cannot pass variables by reference in Java
        if(root == null){
            return 0;
        }
        int leftHeight = findDiamaterOfBinaryTreeOptimal(root.left, diameter);
        int rightHeight = findDiamaterOfBinaryTreeOptimal(root.right, diameter);
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
        // TC -> O(n)
        // SC -> O(n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        int diameter[] = new int[1];
        // findDiamaterOfBinaryTreeBrute(root, diameter);
        findDiamaterOfBinaryTreeOptimal(root, diameter);
        System.out.println(diameter[0]);
        sc.close();

    }
}
