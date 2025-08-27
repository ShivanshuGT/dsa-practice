import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ZigZagTraversalOfBinaryTree {

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

    private static List<int[]> zigZagTraversal(TreeNode root){
        List<int[]> traversal = new ArrayList<>();

        if(root == null){
            return traversal;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean insertInReverse = false;
        while (!queue.isEmpty()) {
            int n = queue.size();
            int j = n;
            int arr[] = new int[n];
            int i = 0;

            while (j > 0) {
                TreeNode current = queue.poll();
                int index = insertInReverse ? (n - i - 1): i;
                arr[index] = current.data;
                if(current.left != null){
                    queue.offer(current.left);
                }
                if(current.right != null){
                    queue.offer(current.right);
                }
                j -= 1;
                i += 1;
            }
            traversal.add(arr);
            insertInReverse = !insertInReverse;
        }

        return traversal;

        // TC -> O(n)
        // SC -> O(n)

    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        // System.out.println(levelOrderTraversal(root));
        List<int[]> zigZagTraversal = zigZagTraversal(root);
        for (int[] list : zigZagTraversal) {
            int n = list.length;
            for (int i = 0; i < n; i++) {
                System.out.print(list[i] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
