
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
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
public class Graph {

    static Object[] nodesSet;

    public static void main(String[] args) throws Exception {

        System.out.println("Enter the file name to read from :");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        Scanner scan = new Scanner(new File(fileName));
        int n = 0;
        if (scan.hasNext()) {
            n = Integer.parseInt(scan.nextLine());
        }

        System.out.println("\nAdjacency Matrix \n");
        int[][] adj = new int[n][n];  // matrix n*n

        int entrypt = 0;
        boolean entryptfilled = false;
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
            if (!entryptfilled) {
                entrypt = v1;
                entryptfilled = true;
            }
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

        dfs(adj, entrypt);
        dfs2(adj, entrypt);
    }

    private static ArrayList<Integer> findneighbors(int[][] adj, int node) {
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        for (int j = 0; j < adj.length; j++) {
            if (adj[node][j] == 1) {
                nodes.add(j);
            }
        }
        return nodes;
    }

    private static void dfs(int[][] adj, int entrypt) {
        String[] c = new String[adj.length];
        System.out.println("\nVertices Visited \n");
        dfs(adj, entrypt, c);
    }

    private static void dfs(int[][] g, int i, String[] c) {
        c[i] = "grey"; // currently visiting i
        System.out.println(nodesSet[i] + " visited");
        for (Integer j : findneighbors(g, i)) {
            if (c[j] == "white") {
                c[j] = "grey";
                dfs(g, j, c);
            }
        }
        c[i] = "black"; // done visiting i
    }

    private static void dfs2(int[][] adj, int entrypt) {

        String[] c = new String[adj.length];
        Stack<Integer> s = new Stack<Integer>();
        s.push(entrypt);
        while (!s.isEmpty()) {
            int i = s.pop();
            System.out.println(nodesSet[i] + " visited");
            if (c[i] == "white") {
                c[i] = "grey";
                for (int j : findneighbors(adj, i)) {
                    s.push(j);
                }
            }
        }
    }

}
