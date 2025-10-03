import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DetectCycleInUndirectedGraphUsingBFS {

    static class Pair {
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    static class QueuePair {
        int node;
        int parentNode;
        QueuePair(int node, int parentNode){
            this.node = node;
            this.parentNode = parentNode;
        }
        
    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = edgeInfo.size();

        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + e)
        // SC -> O(2xe)
    }

    private static boolean detectCycleBFS(int source, List<List<Pair>> graph, int[] visited){
        Queue<QueuePair> queue = new LinkedList<>();
        queue.add(new QueuePair(source, -1));
        visited[source] = 1;

        while (!queue.isEmpty()) {
            QueuePair entry = queue.poll();
            int node = entry.node;
            int parentNode = entry.parentNode;

            // checking each adjacent node of the current node
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                if(visited[neighbour.nodeValue] != 1){
                    // this node is not visited yet
                    queue.add(new QueuePair(neighbour.nodeValue, node));
                    visited[neighbour.nodeValue] = 1;
                }else{
                    // this node has been visited earlier
                    if(neighbour.nodeValue == parentNode){
                        continue;
                    }else{
                        // it is a cycle
                        return true;
                    }
                }
            }
        }
        return false;
        // TC -> O(n + 2xe)
        // SC -> O(n)
    }

    private static boolean detectCycle(int nodes, List<List<Pair>> graph){
        int visited[] = new int[nodes+1];
        for (int i = 1; i <= nodes; i++) {
            if(visited[i] != 1){
                if(detectCycleBFS(i, graph, visited)){
                    return true;
                }
            }
        }
        return false;
        // TC -> O(n + 2xe)
        // SC -> O(n)
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
        System.out.println(detectCycle(n, graph));
        sc.close();
    }
}
