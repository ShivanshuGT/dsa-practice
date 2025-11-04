import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimsAlgorithm {
    // MST of a graph is a tree having N vertices and (N-1) edges
    // where each vertex is reachable from another vertex 
    // ans the sum of weights of the edges is minimum

    static class Pair {
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    static class QueueEntry {
        int weight;
        int node;
        int parent;

        QueueEntry(int weight, int node, int parent){
            this.weight = weight;
            this.node = node;
            this.parent = parent;
        }
        
    }

    static class MST{
        List<List<Integer>> edges;
        int sum;

        MST(List<List<Integer>> edges, int sum){
            this.edges = edges;
            this.sum = sum;
        }
    }

    private static List<List<Pair>> buildGraphFromInput(int n, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();
        int e = edgeInfo.size();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(N + 2xE)
        // SC -> O(2xE)
    }

    private static MST findMinimumSpanningTree(int n, List<List<Pair>> graph){
        int[] visited = new int[n];
        List<List<Integer>> mstEdges = new ArrayList<>();

        Comparator<QueueEntry> comparator = Comparator.comparingInt((QueueEntry entry) -> entry.weight).thenComparing(query -> query.node).thenComparing(query -> query.parent);
        PriorityQueue<QueueEntry> queue = new PriorityQueue<>(comparator);
        queue.add(new QueueEntry(0, 0, -1)); // we start from node '0'
        int sum = 0;
        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int weight = entry.weight;
            int node = entry.node;
            int parent = entry.parent;

            if(visited[node] == 1){
                continue;
            }
            visited[node] = 1;
            if(parent != -1){
                mstEdges.add(List.of(parent, node, weight));
                sum += weight;
            }

            // traversing the neighbours
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                if(visited[neighbour.nodeValue] != 1){
                    // that node is still not visited
                    queue.add(new QueueEntry(neighbour.weight, neighbour.nodeValue, node));
                }
            }
            
        }

        return new MST(mstEdges, sum);
        // TC -> O(E x log(E))
        // SC -> O(N + E)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        MST mst = findMinimumSpanningTree(n, graph);
        if(mst != null){
            List<List<Integer>> mstEdges = mst.edges;
            System.out.println("Edges are as below : ");
            for (List<Integer> mstEdge : mstEdges) {
                System.out.println("Edge : " + mstEdge.get(0) + " -> " + mstEdge.get(1));
            }
            System.out.println("Sum of weight of edges of the MST is " + mst.sum);
        }
        sc.close();
    }
}
