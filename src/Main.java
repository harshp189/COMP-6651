import java.util.*;
import java.lang.*;

public class Main {

    static ArrayList<int[]> feasible_labellings;

    static int[] visited;
    static int[] low;
    static int counter = 0;
    static boolean bridgeFound = false;


    static void dfsOrientation(ArrayList<ArrayList<Integer>> hospital_graph, int[] order, boolean[] visited_rooms, int v, int l) {

        visited_rooms[v] = true;

        order[v] = order[l] + 1;


        for (int i = 0; i < hospital_graph.get(v).size(); i++) {
            int u = hospital_graph.get(v).get(i);

            if (u == l) {
                continue;
            }
            if (order[v] < order[u]) {
                continue;
            }
            if (!visited_rooms[u]) {
                dfsOrientation(hospital_graph, order, visited_rooms, u, v);
            }

            feasible_labellings.add(new int[]{v, u});
        }

    }

}
