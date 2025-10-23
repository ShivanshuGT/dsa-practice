import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DjikstraAlgorithmUsingPrioirityQueue {

    static class Pair{
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }

    static class QueueEntry{
        int distance;
        int nodeValue;

        QueueEntry(int distance, int nodeValue){
            this.distance = distance;
            this.nodeValue = nodeValue;
        }
    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();
        int e = edgeInfo.size();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + 2xe)
        // SC -> O(2xe)
    }

    private static int[] findShortestDistance(int nodes, int soureNode, List<List<Pair>> graph){
        int[] shortestDistance = new int[nodes];
        // defining a priority queue with min heap functionality
        Comparator<QueueEntry> comparator = Comparator.comparingInt((QueueEntry entry) -> entry.distance).thenComparing(entry -> entry.nodeValue);
        PriorityQueue<QueueEntry> queue = new PriorityQueue<>(comparator);

        for (int i = 0; i < nodes; i++) {
            shortestDistance[i] = Integer.MAX_VALUE;
        }
        shortestDistance[soureNode] = 0;
        queue.add(new QueueEntry(0, soureNode));

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int distance = entry.distance;
            int currentNode = entry.nodeValue;

            // traversing the neighbour nodes

            List<Pair> neighbours = graph.get(currentNode);
            for (Pair neighbour : neighbours) {
                int neighbourNode = neighbour.nodeValue;
                int edgeWeight = neighbour.weight;

                if((distance + edgeWeight) < shortestDistance[neighbourNode]){
                    shortestDistance[neighbourNode] = distance + edgeWeight;
                    queue.add(new QueueEntry(shortestDistance[neighbourNode], neighbourNode));
                }
            }
        }

        return shortestDistance;
        // TC -> E x log(N)
        // SC -> O(N)
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
        int[] shortestDistance = findShortestDistance(n, sourceNode, graph);
        for (int i = 0; i < n; i++) {
            System.out.println("The shortest distance from "+ sourceNode + " to "+ i + " is " + shortestDistance[i] + " units.");
        }
        sc.close();
    }
}
