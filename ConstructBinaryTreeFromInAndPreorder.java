import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ConstructBinaryTreeFromInAndPreorder {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode build(HashMap<Integer, Integer> inorderMap, String[] inorder, int inStart, int inEnd, String[] preorder, int preStart, int preEnd){

        if(preStart > preEnd || inStart > inEnd){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(preorder[preStart]));

        int rootIndexInorder = inorderMap.get(root.data);
        int numberOfElementsOnLeftHandSide = rootIndexInorder - inStart;

        root.left = build(inorderMap, inorder, inStart, rootIndexInorder-1, preorder, preStart+1, preEnd+numberOfElementsOnLeftHandSide);
        root.right = build(inorderMap, inorder, rootIndexInorder+1, inEnd, preorder, preStart+numberOfElementsOnLeftHandSide+1, preEnd);
        return root;
        // TC -> O(nlogn)) (log(n) for using a map as well)
        // SC -> O(n)
    }

    private static TreeNode buildTreeFromInOrderAndPreOrderTraversal(String[] inorder, String[] preorder){
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();
        int inorderLength = inorder.length;
        int preorderLength = preorder.length;
        for (int i = 0; i < inorderLength; i++) {
            inOrderMap.put(Integer.parseInt(inorder[i]), i);
  
        }
        return build(inOrderMap, inorder, 0, inorderLength-1, preorder, 0, preorderLength-1);
        // TC -> O(nlog(n))
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
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inorderTraversal = sc.nextLine().split("\\s+");
        String[] preorderTraversal = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInOrderAndPreOrderTraversal(inorderTraversal, preorderTraversal);
        System.out.println(levelOrderTraversal(root));
        sc.close();
    }
}
