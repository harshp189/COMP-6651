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
    return false;
    }
}
