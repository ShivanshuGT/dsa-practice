import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class CheapestFlightWithKStops {

    static class Pair {
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    static class QueueEntry{
        int stops;
        int node;
        int fare;

        QueueEntry(int stops, int node, int fare){
            this.stops = stops;
            this.node = node;
            this.fare = fare;
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
        }

        return graph;
        // TC -> O(n + e)
        // SC -> O(e)
    }

    private static int findCheapestFlightWithAtmostKStops(int nodes, int source, int target, int stops, List<List<Pair>> graph){
        int[] fare = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            fare[i] = Integer.MAX_VALUE;
        }

        fare[source] = 0;

        Queue<QueueEntry> queue = new LinkedList<>();
        queue.add(new QueueEntry(0, source, 0)); // we start from the source node

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int currentNumberOfStops = entry.stops;
            int currentNode = entry.node;
            int currentFare = entry.fare;

            if(currentNode == target){
                continue;
            }

            // traverse the neighbours
            List<Pair> neighbours = graph.get(currentNode);
            for (Pair neighbour : neighbours) {
                int newFare = currentFare + neighbour.weight;
                if(fare[neighbour.nodeValue] > newFare && currentNumberOfStops <= stops){
                    fare[neighbour.nodeValue] = newFare;
                    queue.add(new QueueEntry(currentNumberOfStops+1, neighbour.nodeValue, newFare));
                }
            }

        }
        return fare[target] == Integer.MAX_VALUE ? -1 : fare[target];
        // TC -> O(E)  (log(V) is removed here since we are not using any priority queue here)
        // SC -> O(V)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int source = sc.nextInt();
        int target = sc.nextInt();
        int stops = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        System.out.println(findCheapestFlightWithAtmostKStops(n, source, target, stops, graph));
        sc.close();
    }
}
