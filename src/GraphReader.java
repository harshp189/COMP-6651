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

    public static int getCovidWard(){
        return covid_ward_index;
    }

    public static ArrayList<ArrayList<Integer>> removeEmptyVertices(ArrayList<ArrayList<Integer>> adj)
    {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(ArrayList<Integer> arr : adj)
        {
            if(arr.size() != 0)
            {
                graph.add(arr);
            }
        }
        return graph;
    }

    public static void addEdges(ArrayList<ArrayList<Integer>> adj, ArrayList<int[]> feasible_labellings_for_covid) {
        for (int[] arr : feasible_labellings_for_covid) {
            if (!adj.get(arr[0]).contains(arr[1]))
                adj.get(arr[0]).add(arr[1]);
            if (!adj.get(arr[1]).contains(arr[0]))
                adj.get(arr[1]).add(arr[0]);
        }
    }

    public static void intializeGrpah(File p_map, ArrayList<ArrayList<Integer>> adj) {

        try {
            Scanner mapReader = new Scanner(p_map);

            while (mapReader.hasNextLine()) {
                String l_line = mapReader.nextLine();
                adj.add(new ArrayList<>());
            }
            System.out.println("Loaded graph successfully");

        } catch (FileNotFoundException e) {
            System.out.println("File not found please run again with a valid file name");
            System.exit(0);


        }


    }

    public static void createGraph(File p_map, ArrayList<ArrayList<Integer>> adj) {
        try {
            Scanner mapReader = new Scanner(p_map);
            covid_ward = new ArrayList<>();
            while (mapReader.hasNextLine()) {
                String l_line = mapReader.nextLine();
                int nodeIndex = l_line.indexOf('[') + 1;
                int nodeEndIndex = l_line.indexOf(']');


                int edgeStartIndex = l_line.indexOf('{') + 1;
                int edgeEndIndex = l_line.indexOf('}');
                try {
                    int u = Integer.parseInt(l_line.substring(nodeIndex, nodeEndIndex));
                    String numberEd = l_line.substring(edgeStartIndex, edgeEndIndex);
                    String[] ch = numberEd.split(",");
                    for (String c : ch) {
                        try {
                            int v = Integer.parseInt(c);
                            if (!adj.get(u).contains(v))
                                adj.get(u).add(v);
                            if (!adj.get(v).contains(u))
                                adj.get(v).add(u);
                        } catch (Exception e) {

                        }

                    }

                } catch (Exception e) {
                    adj.remove(adj.size() - 1);
                    String numberEd = l_line.substring(edgeStartIndex, edgeEndIndex);
                    String[] ch = numberEd.split(",");
                    for (String c : ch) {
                        try {
                            int v = Integer.parseInt(c);
                            covid_ward.add(v);
                        } catch (Exception ei) {

                        }
                    }
                    covid_ward_index=covid_ward.get(0);
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }



    }


}
