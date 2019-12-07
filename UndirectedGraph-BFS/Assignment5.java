
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author saipati
 */
public class Assignment5 {

    static Object[] nodesSet;

    public static void main(String[] args) throws Exception {

        try {

            System.out.print("Enter the file name to read from :");
            Scanner sc = new Scanner(System.in);
            String fileName = sc.nextLine();

            Scanner scan = new Scanner(new File(fileName));
            int n = 0;
            if (scan.hasNext()) {
                n = Integer.parseInt(scan.nextLine());
            }

            System.out.println("\nAdjacency Matrix:\n");
            int[][] adj = new int[n][n];  // matrix n*n
            TreeSet verticesSet = new TreeSet();
            while (scan.hasNext()) {
                String curLine = scan.nextLine();
                String[] splitted = curLine.split("\t");
                String s0 = splitted[0].trim();
                String s1 = splitted[1].trim();
                if (!verticesSet.contains(s0)) {
                    verticesSet.add(s0);
                }
                if (!verticesSet.contains(s1)) {
                    verticesSet.add(s1);
                }
                int v0 = s0.charAt(0) - 65;
                int v1 = s1.charAt(0) - 65;
                adj[v0][v1] = adj[v1][v0] = 1;

            }
            scan.close();
            nodesSet = verticesSet.toArray();

            for (int i = -1; i < n; i++) {
                for (int j = -1; j < n; j++) {

                    if (i >= 0 && j >= 0) {
                        System.out.print(adj[i][j]);
                        System.out.print("\t");
                    } else if (i < 0 && j < 0) {
                        System.out.print(" ");
                        System.out.print("\t");

                    } else //i or j is less than 0
                    {
                        int k = i > -1 ? i : j;
                        System.out.print(nodesSet[k]);
                        System.out.print("\t");
                    }
                }
                System.out.print("\n");
            }

            int entrypt2 = 0; //starting vertex

            bfs(adj, entrypt2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void bfs(int[][] adj, int entrypt) {

        Queue q = new LinkedList();
        ArrayList resultList = new ArrayList();
        q.add(entrypt);
        resultList.add(entrypt);
        System.out.println("\nVertices Visited:\n");
        System.out.println(nodesSet[entrypt] + " visited");
        while (!q.isEmpty()) {
            int node = (int) q.remove();
            ArrayList neighbourList = findneighbors(adj, node);
            for (int i = 0; i < neighbourList.size(); i++) {
                int j = (int) neighbourList.get(i);
                if (!resultList.contains(j)) {
                    q.add(j);
                    resultList.add(j);
                    System.out.println(nodesSet[j] + " visited");
                }

            }

        }

    }

    private static ArrayList findneighbors(int[][] adj, int node) {
        ArrayList nodes = new ArrayList<>();
        for (int j = 0; j < adj.length; j++) {
            if (adj[node][j] == 1) {
                nodes.add(j);
            }
        }
        return nodes;
    }

}
