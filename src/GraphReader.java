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

}
