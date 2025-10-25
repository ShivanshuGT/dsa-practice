import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrintShortestPathUsingDjikstraAlgorithm {

    private static class Pair{
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }

    private static class QueueEntry {
        int distance;
        int nodeValue;

        QueueEntry(int distance, int nodeValue){
            this.distance = distance;
            this.nodeValue = nodeValue;
        }
        
    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        int e = edgeInfo.size();
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
    }

    private static List<Integer> findShortestPath(int nodes, int sourceNode, int targetNode, List<List<Pair>> graph){
        int[] distance = new int[nodes+1];
        int[] parent = new int[nodes+1]; // for backtracking

        Comparator<QueueEntry> comparator = Comparator.comparingInt((QueueEntry entry) -> entry.distance).thenComparing(entry -> entry.nodeValue);
        PriorityQueue<QueueEntry> queue = new PriorityQueue<>(comparator);

        queue.add(new QueueEntry(0, sourceNode));
        parent[sourceNode] = sourceNode;
        
        for (int i = 0; i <= nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[sourceNode] = 0;

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int nodeDistance = entry.distance;
            int node = entry.nodeValue;

            // traversing the neighbours
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                int neighbourNode = neighbour.nodeValue;
                int weight = neighbour.weight;
                if(nodeDistance + weight < distance[neighbourNode]){
                    distance[neighbourNode] = nodeDistance + weight;
                    queue.add(new QueueEntry(distance[neighbourNode], neighbourNode));
                    // remembering the parent
                    parent[neighbourNode] = node;
                }
            }


        }
        if(distance[targetNode] == Integer.MAX_VALUE){
            // we were not able to reach the target node
            return List.of(-1);
        }
        int currentNode = targetNode;
        List<Integer> shortestPath = new ArrayList<>();
        while (parent[currentNode] != currentNode) {
            shortestPath.add(currentNode);
            currentNode = parent[currentNode];
        }
        shortestPath.add(sourceNode);
        Collections.reverse(shortestPath);
        return shortestPath;
        // TC -> O(E x log(V)) + O(V)
        // SC -> O(V)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int sourceNode = sc.nextInt();
        int targetNode = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);

        List<Integer> shortestPath = findShortestPath(n, sourceNode, targetNode, graph);
        if(shortestPath.size() == 1 && shortestPath.get(0) == -1){
            System.out.println("Target Node is not reachable from source node");
        }else{
            System.out.println("The shortest path is : ");
            for (Integer node : shortestPath) {
                System.out.print(node +" ");
            }
        }
        sc.close();
    }
}
