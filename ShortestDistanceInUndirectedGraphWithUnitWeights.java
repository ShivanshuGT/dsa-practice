import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestDistanceInUndirectedGraphWithUnitWeights {

    static class Pair{
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }

    static class QueueEntry {
        int nodeValue;
        int distance;
        QueueEntry(int nodeValue, int distance){
            this.nodeValue = nodeValue;
            this.distance = distance;
        }
        
    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        int edges = edgeInfo.size();
        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + 2xe)
        // SC -> O(2xe)
    }

    private static int[] findShortestDistance(int nodes, List<List<Pair>> graph, int sourceNode){
        int[] distance = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[sourceNode] = 0;

        Queue<QueueEntry> queue = new LinkedList<>();
        queue.add(new QueueEntry(sourceNode, 0));

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int node = entry.nodeValue;
            int nodeDistance = entry.distance;

            // traversing the neighbours
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                if(nodeDistance + 1 < distance[neighbour.nodeValue]){
                    distance[neighbour.nodeValue] = nodeDistance + 1;
                    queue.add(new QueueEntry(neighbour.nodeValue, distance[neighbour.nodeValue]));
                }
            }
        }

        for (int i = 0; i < nodes; i++) {
            if(distance[i] == Integer.MAX_VALUE){
                distance[i] = -1;
            }
        }
        return distance;
        // TC -> O(n + 2xe)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int sourceNode = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        int[] shortestDistance = findShortestDistance(n, graph, sourceNode);
        for (int i = 0; i < n; i++) {
            System.out.println("Distance of node "+ i + " from " + sourceNode + " is " + shortestDistance[i] + " units.");
        }
        sc.close();
    }
}
