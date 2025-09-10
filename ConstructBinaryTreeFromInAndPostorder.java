import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ConstructBinaryTreeFromInAndPostorder {

    static class  TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode build(HashMap<Integer, Integer> inorderMap, String[] inorder, int inStart, int inEnd, String[] postorder, int postStart, int postEnd){
        if(inStart > inEnd || postStart > postEnd){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(postorder[postEnd]));

        int rootIndexInorder = inorderMap.get(root.data);
        int numberOfElementsOnLeftHandSide = rootIndexInorder - inStart;

        root.left = build(inorderMap, inorder, inStart, rootIndexInorder-1, postorder, postStart, postStart+numberOfElementsOnLeftHandSide-1);
        root.right = build(inorderMap, inorder, rootIndexInorder+1, inEnd, postorder, postStart+numberOfElementsOnLeftHandSide, postEnd-1);
        return root;

        // TC -> O(nlog(n)) (log(n) for using map as well
        // SC -> O(n)
    }

    private static TreeNode buildTreeFromInAndPostOrderTraversal(String[] inorder, String postorder[]){
        int inorderLength = inorder.length;
        int postorderLength = postorder.length;
        HashMap<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorderLength; i++) {
            inorderMap.put(Integer.parseInt(inorder[i]), i);
        }

        return build(inorderMap, inorder, 0, inorderLength-1, postorder, 0, postorderLength-1);
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
        String[] inorder = sc.nextLine().split("\\s+");
        String[] postorder = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInAndPostOrderTraversal(inorder, postorder);
        System.out.println(levelOrderTraversal(root));
        sc.close();
    }
}
