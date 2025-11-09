import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class KruskalsAlgorithm {

    static class Pair {
        int weight;
        int nodeValue;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    static class DisjointSet {
    
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        DisjointSet(int nodes){
            for (int i = 0; i <= nodes; i++) {
                parent.add(i);
                rank.add(0);
                size.add(1);
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
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(rank.get(parentU) == rank.get(parentV)){
                parent.set(parentU, parentV);
                rank.set(parentV, rank.get(parentV)+1);
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

    static class MinimumSpanningTree{
        List<List<Integer>> edges;
        int weight;

        MinimumSpanningTree(List<List<Integer>> edges, int weight){
            this.edges = edges;
            this.weight = weight;
        }
    }

    private static List<List<Pair>> buildGraphFromInput(int n, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int e = edgeInfo.size();
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + e)
        // SC -> O(2 x e)
    }

    private static MinimumSpanningTree findMinimumSpanningTree(int n, List<List<Pair>> graph){
        // extract edges and their weights from the graph
        List<List<Integer>> edgesWithWeights = new ArrayList<>();

        // TC -> O(2 x e)
        for (int i = 0; i <= n; i++) {
            List<Pair> neighbours = graph.get(i);
            for (Pair neighbour : neighbours) {
                edgesWithWeights.add(List.of(neighbour.weight, i, neighbour.nodeValue));
            }
        }

        // since we want the miniumum spanning tree, therefore
        // sorting the edges in ascending order on the basis of weight
        // TC -> O(e x log(e))
        Comparator<List<Integer>> comparator = Comparator.comparingInt((entry) -> entry.get(0));
        Collections.sort(edgesWithWeights, comparator);

        List<List<Integer>> edgesInMinimumSPanningTree = new ArrayList<>();
        int weightOfMinimumSpanningTree = 0;
        // TC -> O(n)
        DisjointSet disjointSet = new DisjointSet(n);
        // iterating over the sorted edges
        // TC -> O(e)
        for (List<Integer> edgeWithWeight : edgesWithWeights) {
            int weight = edgeWithWeight.get(0);
            int u = edgeWithWeight.get(1);
            int v = edgeWithWeight.get(2);
            if(disjointSet.findParent(u) != disjointSet.findParent(v)){
                // 'u' and 'v' are not connected yet
                // we need to add this edge to our minimumSpanningTree
                edgesInMinimumSPanningTree.add(edgeWithWeight);
                weightOfMinimumSpanningTree += weight;
                disjointSet.unionByRank(u, v);
            }   
        }

        return new MinimumSpanningTree(edgesInMinimumSPanningTree, weightOfMinimumSpanningTree);
        // TC -> O(3e + e x log(e))
        // SC -> O(n + 2e)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edges.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        List<List<Pair>> graph = buildGraphFromInput(n, edges);
        MinimumSpanningTree mst = findMinimumSpanningTree(n, graph);

        if(mst != null){
            System.out.println("The weight of the minimum spanning tree of the graph is " + mst.weight);
            System.out.println("With edges as below : ");
            for (List<Integer> edge : mst.edges) {
                System.out.println("From " + edge.get(1) + " -> " + edge.get(2) + " with weight " + edge.get(0));
            }
        }
        sc.close();
    }
}
