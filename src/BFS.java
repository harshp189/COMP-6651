import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    static ArrayList<int[]> findShortestPath(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v)
    {

        ArrayList<int[]> feasible_labellings_covid=new ArrayList<>();

        int[] pred = new int[v];
        int[] dist = new int[v];

        if (BFS(adj, src, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination are not connected");
            return feasible_labellings_covid;
        }

        int crawl = dest;
        while (pred[crawl] != -1) {
            int[] arr = {pred[crawl],crawl};
            feasible_labellings_covid.add(arr);
            crawl = pred[crawl];
        }


        return feasible_labellings_covid;
    }
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[])
    {
        LinkedList<Integer> queue = new LinkedList<Integer>();

        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();

            for (int i = 0; i < adj.get(u).size(); i++) {

                if (visited[adj.get(u).get(i)] == false) {

                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
