import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphReader {
    static int covid_ward_index=-1;
    static ArrayList<Integer> covid_ward;

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

    public static void removeBridgesForCovid(ArrayList<ArrayList<Integer>> adj, ArrayList<int[]> feasible_labellings_for_covid) {
        for (int[] arr : feasible_labellings_for_covid) {

            int rem1, rem2;
            if(adj.get(arr[0]).size() == 1)
            {
                rem1 = adj.get(arr[0]).remove(0);
                adj.get(rem1).remove((Integer) arr[0]);
            }
            if(adj.get(arr[1]).size() == 1)
            {
                rem2 = adj.get(arr[1]).remove(0);
                adj.get(rem2).remove((Integer) arr[1]);
            }
        }
    }

}
