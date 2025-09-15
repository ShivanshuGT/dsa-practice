import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SearchCeilAndFloorInBinaryTree {

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
    }

    private static TreeNode search(TreeNode root, int target){
        if(root == null){
            return null;
        }
        TreeNode current = root;

        while (current!=null && current.data!=target ) {
            if(current.data > target){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }
        return current;
        // TC -> O(log(n))
        // SC -> O(1)
        // where n is the number of the nodes
    }

    private static TreeNode ceil(TreeNode root, int target){
        // ceil is the smallest node which is greater than or equal to target
        if(root == null){
            return null;
        }
        TreeNode current = root;
        TreeNode result = null;
        while (current != null) {
            if(current.data == target){
                return current;
            }
            if(current.data > target){
                result = current;
                current = current.left;
            }
            else{
                current = current.right;
            }
        }
        return result;
        // TC -> O(log(n))
        // SC -> O(1)
        // where n is the number of nodes
    }

    private static TreeNode floor(TreeNode root, int target){
        // floor is the greatest node which is lesser than or equal to the target
        if(root == null){
            return null;
        }

        TreeNode current = root;
        TreeNode result = null;
        while (current!=null) {
            if(current.data == target){
                return current;
            }
            if(current.data > target){
                current = current.left;
            }
            else{
                result = current;
                current = current.right;
            }
        }
        return result;
        // TC -> O(log(n))
        // SC -> O(1)
        // where n is the number of nodes

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
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        TreeNode searchResult = search(root, target);
        System.out.println(searchResult == null ? "Target Not Found" : searchResult.data);
        TreeNode ceil = ceil(root, target);
        System.out.println(ceil == null ? "No Ceil found" : "Ceil -> " + ceil.data);
        TreeNode floor = floor(root, target);
        System.out.println(floor == null ? "No Floor found" : "Floor -> " + floor.data);
        sc.close();
    }
}
