import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class KthLargestAndSmallestNodeInBinarySearchTree {
 
    static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
    }

    private static TreeNode buildTreefromInput(String[] input){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        int n = input.length;

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

    private static TreeNode findKthSmallestNode(TreeNode root, int k){
        if(root == null){
            return null;
        }

        TreeNode current = root;
        int count = 0;
        while (current != null) {
            if(current.left == null){
                count += 1;
                if(count == k){
                    return current;
                }
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
                    count += 1;
                    if(count == k){
                        return current;
                    }
                    current = current.right;
                }
            }
        }
        return current;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int countNumberOfNodes(TreeNode root){
        if(root == null){
            return 0;
        }
        int count = 0;

        TreeNode current = root;
        while (current!=null) {
            if(current.left == null){
                count += 1;
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
                    count += 1;
                    current = current.right;
                }
            }
        }
        return count;
        // TC -> O(n)
        // SC -> O(1)
    }


    private static TreeNode findKthLargestNode(TreeNode root, int k){
        if(root == null){
            return null;
        }

        int n = countNumberOfNodes(root);
        return findKthSmallestNode(root, n-k);
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
        String[] input = sc.nextLine().split("\\s+");
        int k = sc.nextInt();
        TreeNode root = buildTreefromInput(input);
        System.out.println(levelOrderTraversal(root));
        TreeNode kthSmallest = findKthSmallestNode(root, k);
        System.out.println(kthSmallest == null ? "No " + k + "th smallest found"  : kthSmallest.data);
        TreeNode kthLargest = findKthLargestNode(root, k);
        System.out.println(kthLargest == null ? "No " + k + "th largest found"  : kthLargest.data);

        sc.close();
    }
}
