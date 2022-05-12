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

    /*
     *  Function to print the direction of edges to make graph SCCs
     *  Runs in O(E + V) time.
     */
    static void orientation(ArrayList<ArrayList<Integer>> hospital_graph, int n) {

        int[] order = new int[n];
        boolean[] mark = new boolean[n];

        dfsOrientation(hospital_graph, order, mark, 1, 0);

        for (int[] edge : feasible_labellings) {
            System.out.println(edge[0] + "->" + edge[1]);
        }

    }

    static void dfsCheckFeasibleLabellings(ArrayList<ArrayList<Integer>> G, int parentofV, int v) {

        visited[v] = counter++;  //identify each vertex with a different number according to its position in the dfs tree
        low[v] = visited[v];     //check for loop, identifies the lowest numbered (from visited array) vertex that the current vertex can reach.


        for (int w : G.get(v)) {

            if (visited[w] == -1) {
                dfsCheckFeasibleLabellings(G, v, w);

                //Check if descendants of v has connections to ancestors of v
                low[v] = Math.min(low[v], low[w]);

                //If lowest vertex reachable from descendants of v is below w, v-w is a bridge
                // if(low[w] > visited[v]
                if (low[w] == visited[w]) {
                    bridgeFound = true;
                }
            }

            // if w is not a parent of V and it has already been visited
            else if (w != parentofV)
                low[v] = Math.min(low[v], visited[w]);
        }
    }

    /*
     *  Identifies bridge edges and prints them out. This decomposes
     *  a directed graph into two-edge connected components.
     *  Runs in O(E + V) time.
     */
    static void checkFeasibleLabellings(ArrayList<ArrayList<Integer>> hospital_graph, int N)
    {
        low = new int[N];
        visited = new int[N];

        for(int i = 0 ; i < N ; i++) {
            low[i] = -1;
            visited[i] = -1;
        }

        for (int i = 0; i < N; i++)
            if (visited[i] == -1)
                dfsCheckFeasibleLabellings(hospital_graph, i, i);

    }

}
