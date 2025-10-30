import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class NumberOfWaysToArriveAtDestination {

    static class Pair{
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }

    static class QueueEntry {
        int distance;
        int nodeValue;

        QueueEntry(int distance, int nodeValue){
            this.distance = distance;
            this.nodeValue = nodeValue;
        }
        
    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        int e = edgeInfo.size();

        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + 2xe)
        // SC -> O(2xe)
    }

    private static int findNumberOfWays(int nodes, int source, int target, List<List<Pair>> graph){
        int[] distance = new int[nodes];
        int[] ways = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
            ways[i] = 0;
        }

        distance[source] = 0;
        ways[source] = 1; // we can be at source by just 1 way

        // min-heap
        Comparator<QueueEntry> comparator = Comparator.comparing((QueueEntry entry) -> entry.distance).thenComparing(entry -> entry.nodeValue);
        
        Queue<QueueEntry> queue = new PriorityQueue<>(comparator);
        queue.add(new QueueEntry(0, source)); // starting from the source

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int currentDistance = entry.distance;
            int currentNode = entry.nodeValue;

            // traversing the neighbours
            List<Pair> neighbours = graph.get(currentNode);
            for (Pair neighbour : neighbours) {
                int newDistance = currentDistance + neighbour.weight;
                if(distance[neighbour.nodeValue] > newDistance){
                    // we found another path with a shorter distance
                    distance[neighbour.nodeValue] = newDistance;
                    ways[neighbour.nodeValue] = ways[currentNode];
                    queue.add(new QueueEntry(newDistance, neighbour.nodeValue));
                }else if(distance[neighbour.nodeValue] == newDistance){
                    // we found another path with the same distance
                    // adding it to the already existing ways
                    ways[neighbour.nodeValue] += ways[currentNode];
                }
            }
        }
        return ways[target];

        // TC -> O(E x log(V))
        // SC -> O(V)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int source = sc.nextInt();
        int target = sc.nextInt();

        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        System.out.println(findNumberOfWays(n, source, target, graph));
        sc.close();
    }
}
