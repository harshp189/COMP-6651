import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphReader {
    public static void printGraph(ArrayList<ArrayList<Integer>> adj) {
        int count = 0;
        for (ArrayList<Integer> a : adj) {
            System.out.print(count + " --> ");
            for (int aa : a)
                System.out.print(aa + " ");
            count++;
            System.out.println();
        }

    }

    public static void removeEdges(ArrayList<ArrayList<Integer>> adj, ArrayList<int[]> feasible_labellings_for_covid) {
        for (int[] arr : feasible_labellings_for_covid) {

            adj.get(arr[0]).remove(adj.get(arr[0]).indexOf(arr[1]));
            adj.get(arr[1]).remove(adj.get(arr[1]).indexOf(arr[0]));

        }
    }

}
