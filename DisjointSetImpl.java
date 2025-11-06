import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisjointSetImpl {

    static class DisjointSet {
        List<Integer> rank = new ArrayList<>();
        List<Integer> parent  = new ArrayList<>();
        List<Integer> size  = new ArrayList<>();

        DisjointSet(int nodes){
            // supports both '0-based' and '1-based' indexing
            for (int i = 0; i <= nodes; i++) {
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node){
            // recursive approach is used to do path compression as well
            if(parent.get(node) == node){
                return node;
            }
            int val = findParent(parent.get(node));
            parent.set(node, val);
            return parent.get(node);
            // TC -> O(1) = O(4 x alpha)
        }

        public void unionByRank(int u, int v){
            // performs union of node 'u' and 'v'
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(rank.get(parentU) == rank.get(parentV)){
                // attaching 'u' below 'v'
                parent.set(parentU, parentV);
                rank.set(parentV, rank.get(parentV) + 1);
            }else if(rank.get(parentU) > rank.get(parentV)){
                // attaching 'v' below 'u'
                parent.set(parentV, parentU);
            }else{
                // attaching 'u' below 'v'
                parent.set(parentU, parentV);
            }
             // TC -> O(1) = O(4 x alpha)
        }

        public void unionBySize(int u, int v){
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(rank.get(parentU) > rank.get(parentV)){
                // attaching 'v' below 'u'
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentU) + size.get(parentV));
            }else{
                // attaching 'u' below 'v'
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentV) + size.get(parentU));
            }
             // TC -> O(1) = O(4 x alpha)
        }
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 7;
        DisjointSet disjointSet = new DisjointSet(n);
        // UNION BY RANK
        // disjointSet.unionByRank(1, 2);
        // disjointSet.unionByRank(2, 3);
        // disjointSet.unionByRank(4, 5);
        // disjointSet.unionByRank(6, 7);
        // disjointSet.unionByRank(5, 6);
        // System.out.println(disjointSet.findParent(3) == disjointSet.findParent(7) ? "Same" : "Not Same");
        // disjointSet.unionByRank(3, 7);
        // System.out.println(disjointSet.findParent(3) == disjointSet.findParent(7) ? "Same" : "Not Same");
        // UNION BY SIZE
        disjointSet.unionBySize(1, 2);
        disjointSet.unionBySize(2, 3);
        disjointSet.unionBySize(4, 5);
        disjointSet.unionBySize(6, 7);
        disjointSet.unionBySize(5, 6);
        System.out.println(disjointSet.findParent(3) == disjointSet.findParent(7) ? "Same" : "Not Same");
        disjointSet.unionBySize(3, 7);
        System.out.println(disjointSet.findParent(3) == disjointSet.findParent(7) ? "Same" : "Not Same");
        sc.close();
    }   
}
