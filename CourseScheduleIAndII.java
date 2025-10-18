import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class CourseScheduleIAndII {

    static class Pair {
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
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
        }
        return graph;
        // TC -> O(n + e)
        // SC -> O(e)
    }

    private static List<Integer> findPossibleSchedule(int nodes, List<List<Pair>> graph){
        int[] indegree = new int[nodes];

        //calculate the indegree of each node
        for (int i = 0; i < nodes; i++) {
            List<Pair> edges = graph.get(i);
            for (Pair edge : edges) {
                indegree[edge.nodeValue] += 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        // pushing all nodes with 0 indegree in the queue
        for (int i = 0; i < nodes; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        List<Integer> possibleSchedule = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            possibleSchedule.add(node);
            // traversing all the neighbours
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                indegree[neighbour.nodeValue] -= 1;
                if(indegree[neighbour.nodeValue] == 0){
                    queue.add(neighbour.nodeValue);
                }
            }
        }

        if(nodes == possibleSchedule.size()){
            return possibleSchedule;
        }
        return new ArrayList<>();
        // TC -> O(n + e)
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
        List<Integer> possibleSchedule = findPossibleSchedule(n, graph);
        if(possibleSchedule.size() == 0){
            System.out.println("There is no such possible schedule.");
        }else{
            for (Integer element : possibleSchedule) {
                System.out.print(element + " ");
            }
        }
        sc.close();
    }
}
