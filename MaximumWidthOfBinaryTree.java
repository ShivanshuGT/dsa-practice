import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MaximumWidthOfBinaryTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    static class TreeNodeIndex {
        TreeNode node;
        int index;

        TreeNodeIndex(TreeNode node, int index){
            this.node = node;
            this.index = index;
        }
        
    }

    private static TreeNode buildTreeFromInput(String input[]){
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

    private static int findMaxWidthOfBinaryTree(TreeNode root){
        int answer = 0;
        if(root == null){
            return answer;
        }

        TreeNodeIndex rootNodeIndex = new TreeNodeIndex(root, 0); // since we are doing 0 based indexing of the tree
        Queue<TreeNodeIndex> queue = new LinkedList<>();
        queue.offer(rootNodeIndex);

        while (!queue.isEmpty()) {
            int times = queue.size();
            int first = 0;
            int last = 0;
            int min = queue.peek().index;
            for (int i = 0; i < times; i++) {
                TreeNodeIndex currentNodeIndex = queue.poll();
                TreeNode currentNode = currentNodeIndex.node;
                int current_index = currentNodeIndex.index - min; // to avoid overflow cases in case of large values of index
                if(i == 0){
                    // it is the first node of that level
                    first = current_index;
                }
                if(i == times - 1){
                    // it is the last node of that level
                    last = current_index;
                }

                if(currentNode.left != null){
                    queue.offer(new TreeNodeIndex(currentNode.left, 2*current_index+1));
                }
                if(currentNode.right != null){
                    queue.offer(new TreeNodeIndex(currentNode.right, 2*current_index+2));
                }
            }
            answer = Math.max(answer, last-first+1);
        }
        return answer;
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
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(findMaxWidthOfBinaryTree(root));
        sc.close();
    }
}
