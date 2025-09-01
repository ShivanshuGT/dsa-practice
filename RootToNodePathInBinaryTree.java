import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class RootToNodePathInBinaryTree {

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
        int n = input.length;
        int i = 1;
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

    private static boolean getPathFromRootToNode(TreeNode root, List<Integer> path, int target){
        if(root == null){
            return false;
        }
        path.add(root.data);
        
        if(root.data == target){
            return true;
        }

        if(getPathFromRootToNode(root.left, path, target) || getPathFromRootToNode(root.right, path, target)){
            return true;
        }
        path.remove(path.size() - 1);
        return false;
        // TC -> O(n)
        // SC -> O(H) (= O(n) in worst case, when the tree is skewed)
        
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
        List<Integer> path = new ArrayList<>();
        boolean isTargetPresent = getPathFromRootToNode(root, path, target);
        if(isTargetPresent){
            System.out.println(path);
        }else{
            System.out.println("Target is not there");
        }
        sc.close();
    }
}
