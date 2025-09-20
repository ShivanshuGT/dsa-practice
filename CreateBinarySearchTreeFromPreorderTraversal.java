import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class CreateBinarySearchTreeFromPreorderTraversal {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
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

    private static TreeNode insertNodeInABinarySearchTree(TreeNode root, int target){
        if(root == null){
            return new TreeNode(target);
        }

        TreeNode current = root;
        while (true) {
            if(current.data <= target){
                if(current.right == null){
                    current.right = new TreeNode(target);
                    break;
                }
                current = current.right;
            }else{
                if(current.left == null){
                    current.left = new TreeNode(target);
                    break;
                }
                current = current.left;
            }
        }
        return root;
        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static TreeNode buildBetter(String[] preorder){
        if(preorder.length == 0 || preorder[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(preorder[0]));
        int n = preorder.length;
        for (int i = 1; i < n; i++) {
            root = insertNodeInABinarySearchTree(root, Integer.parseInt(preorder[i]));
        }
        return root;
        // TC -> O(n x log(n))
        // SC -> O(1)

    }

    private static TreeNode buildFromPreorderAndInorder(HashMap<Integer, Integer> map, String[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(preorder[preStart]));
        int indexOfRootInorder = map.get(root.data);
        int numberOfElementsOnLeft = indexOfRootInorder - inStart;

        root.left = buildFromPreorderAndInorder(map, preorder, preStart+1, preStart + numberOfElementsOnLeft, inorder, inStart, indexOfRootInorder-1);
        root.right = buildFromPreorderAndInorder(map, preorder, preStart+numberOfElementsOnLeft+1, preEnd, inorder, indexOfRootInorder+1, inEnd);
        return root;
        // TC -> O(n)
        // SC -> O(n)
    }

    private static TreeNode buildBrute(String[] preroder){
        int n = preroder.length;
        int[] inorder = new int[n];
        for (int i = 0; i < n; i++) {
            inorder[i] =  Integer.parseInt(preroder[i]);
        }
        Arrays.sort(inorder); // O(n x log(n))
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return buildFromPreorderAndInorder(map, preroder, 0, n-1, inorder, 0, n-1);
        // TC -> O(n x log(n))
        // SC -> O(n)
    }

    private static TreeNode buildOptimal(String[] preorder){
        if(preorder.length == 0 || preorder[0].equals("null")){
            return null;
        }
        return buildOptimalHelper(preorder, Integer.MAX_VALUE, new int[1]);
        // TC -> O(3 x n)
        // SC -> O(memory stack space)

    }

    private static TreeNode buildOptimalHelper(String[] preorder, int upperBound, int[] index){
        if(index[0] == preorder.length || Integer.parseInt(preorder[index[0]]) > upperBound){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(preorder[index[0]]));
        index[0] += 1;
        root.left = buildOptimalHelper(preorder, root.data, index);
        root.right = buildOptimalHelper(preorder, upperBound, index);
        return root;
        // TC -> O(3 x n) (since we are visiting each node thrice)
        // SC -> O(n) (memory stack space)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] preorder = sc.nextLine().split("\\s+");
        // TreeNode root = buildBrute(preorder);
        // TreeNode root = buildBetter(preorder);
        TreeNode root = buildOptimal(preorder);
        System.out.println(levelOrderTraversal(root));
        sc.close();
    }
}
