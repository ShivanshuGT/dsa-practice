import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MostStonesRemoved {

    static class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        DisjointSet(int nodes){
            for (int i = 0; i <= nodes; i++) {
                parent.add(i);
                size.add(1);
                rank.add(0);
            }
        }

        public int findParent(int node){
            if(parent.get(node) == node){
                return node;
            }
            int val = findParent(parent.get(node));
            parent.set(node, val);
            return val;
            // TC -> O(1)
        }

        public void unionByRank(int u, int v){
            int parentU  = findParent(u);
            int parentV = findParent(v);

            if(rank.get(parentU) == rank.get(parentV)){
                parent.set(parentV, parentU);
                rank.set(parentU, rank.get(parentU) + 1);
            }else if(rank.get(parentU) > rank.get(parentV)){
                parent.set(parentV, parentU);
            }else{
                parent.set(parentU, parentV);
            }

            // TC -> O(1)
        }

        public void unionBySize(int u, int v){
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(size.get(parentU) > size.get(parentV)){
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentU) + size.get(parentV));
            }else{
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentV) + size.get(parentU));
            }
            // TC -> O(1)
        }
        // SC -> O(n)
    }

    private static int findMaximumNumberOfStones(List<List<Integer>> stones){
        // find the matrix row size and col size
        int row = 0;
        int col = 0;
        for (List<Integer> stone : stones) {
            row = Math.max(row, stone.get(0));
            col = Math.max(col, stone.get(1));
        }

        // let us assume each row as a node and each col as a node
        DisjointSet disjointSet = new DisjointSet(row + col + 2);

        // iterating over the stones and connecting them
        for (List<Integer> stone : stones) {
            int node1 = stone.get(0); // for row
            int node2 = stone.get(1) + row + 1; // for col

            // connect them
            if(disjointSet.findParent(node1) != disjointSet.findParent(node2)){
                disjointSet.unionBySize(node1, node2);
            }
        }

        // finding the number of components
        int numberOfComponents = 0;
        for (int i = 0; i < row + col + 2; i++) {
            if(disjointSet.findParent(i) == i && disjointSet.size.get(i) > 1){
                numberOfComponents += 1;
            }
        }
        return stones.size() - numberOfComponents;

        // TC -> O(row + col) + O(number Of Stones)
        // SC -> O(row + col) + O(number of Stones)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> stones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stones.add(List.of(sc.nextInt(), sc.nextInt()));
        }
        System.out.println(findMaximumNumberOfStones(stones));
        sc.close();
    }
}
