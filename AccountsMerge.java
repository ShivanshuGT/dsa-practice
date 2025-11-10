import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AccountsMerge {

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
                rank.set(parentV, rank.get(parentV) + 1);
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

    private static List<List<String>> mergeAccounts(List<List<String>> details){
        int n = details.size();
        DisjointSet disjointSet = new DisjointSet(n);
        Map<String, Integer> map = new HashMap<>();

        // iterating over the email names
        for (int i = 0; i < n; i++) {
            int m = details.get(i).size();
            for (int j = 1; j < m; j++) {
                String name = details.get(i).get(j);
                if(map.containsKey(name)){
                    // we need to merge the existing value in the map and 'i'
                    disjointSet.unionByRank(map.get(name), i);
                }else{
                    map.put(name, i);
                }
            }
        }

        List<List<String>> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temp.add(new ArrayList<>());
        }

        // iterating over the map
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String name = entry.getKey();
            int node = disjointSet.findParent(entry.getValue());
            temp.get(node).add(name);
        }

        List<List<String>> answer = new ArrayList<>();

        // sorting the values and putting them in the answer list
        for (int i = 0; i < n; i++) {
            List<String> values = temp.get(i);
            if(values.isEmpty()){
                // Ignore this 
                continue;
            }
            Collections.sort(values);
            List<String> list = new ArrayList<>();
            list.add(details.get(i).get(0));
            list.addAll(values);
            answer.add(list);
        }
        return answer;
        // TC -> O(n x m x log(n x m))
        // SC -> O(n x m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<String>> details = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> temp = new ArrayList<>();
            temp.add(sc.next());
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                temp.add(sc.next());
            }
            details.add(temp);
        }
        List<List<String>> mergedAccounts = mergeAccounts(details);
        int l = mergedAccounts.size();
        for (int i = 0; i < l; i++) {
            List<String> temp = mergedAccounts.get(i);
            int m = temp.size();
            System.out.print(temp.get(0) + " : ");
            for (int j = 1; j < m; j++) {
                System.out.print(temp.get(j) + " ");
            }
            System.out.println();
        }
        sc.close();
    } 
}
