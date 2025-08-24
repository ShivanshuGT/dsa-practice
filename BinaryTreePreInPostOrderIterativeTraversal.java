import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTreePreInPostOrderIterativeTraversal {

    static class TreeNode {
        
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int data){
            this.data = data;
        }
        
    }

    static class TreeNodeNum {

        TreeNode node;
        int num;
        TreeNodeNum(TreeNode node, int num){
            this.node = node;
            this.num = num;
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

        while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;

            if(i < n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;
        }

        return root;
    }

    private static List<List<Integer>> preInPostOrderIterativeTraversals(TreeNode root){
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null){
            return ans;
        }
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();

        TreeNodeNum element = new TreeNodeNum(root, 1);
        Stack<TreeNodeNum> stack = new Stack<>();
        stack.add(element);

        while (!stack.isEmpty()) {
            TreeNodeNum current = stack.pop();
            TreeNode temp = current.node;
            if(current.num == 1){
                // preorder
                // root left right
                preOrder.add(temp.data);
                stack.add(new TreeNodeNum(temp, current.num + 1));
                if(temp.left != null){
                    stack.add(new TreeNodeNum(temp.left, 1));
                }
                
            }else if(current.num == 2){
                // inorder
                // left root right
                inOrder.add(temp.data);
                stack.add(new TreeNodeNum(temp, current.num + 1));
                if(temp.right != null){
                    stack.add(new TreeNodeNum(temp.right, 1));
                }
            }else{
                // postorder
                // left right root
                postOrder.add(temp.data);
            }
        }
        ans.add(preOrder);
        ans.add(inOrder);
        ans.add(postOrder);
        return ans;

        //TC -> O(3n) since we are traversing each node three times
        // SC -> O(n)
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(preInPostOrderIterativeTraversals(root));
        sc.close();
    }
}

