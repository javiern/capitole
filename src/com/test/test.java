package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

class Graph {
    int vertices;

    // matriz de adjacentes
    char[][] adjMatrix;

    //Lista de adjacentes
    private LinkedList<Integer> adjList[];

    //auxilar para extraer el camino a chequear
    private List<Integer> auxPath;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjMatrix = new char[vertices][vertices];

        this.adjList = new LinkedList[vertices];
        for (int i=0; i<vertices; i++)
        {
            this.adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v1, int v2, char color) {
        this.adjMatrix[v1][v2] = color;
        this.adjMatrix[v2][v1] = color;
        this.adjList[v1].add(v2);
    }

    // Chequea si existe un camino entre 2 nodos, y si el camino tiene color rojo
    public boolean hasPathWithRed(int s, int d) {
        int[] pathList = findPath(s, d);
        for (int i = 0; i < pathList.length-1; i++) {
            int v1 = pathList[i];
            int v2 = pathList[i+1];
            if (this.adjMatrix[v1][v2] == 'r') return true;
        }
        return false;
    }

    // devuelve un camino entre 2 nodos
    // se usa DFS (Depath First Search)
    public int[] findPath(int s, int d)
    {
        boolean[] isVisited = new boolean[vertices];
        ArrayList<Integer> pathList = new ArrayList<>();

        pathList.add(s);

        findPathUtil(s, d, isVisited, pathList);
        if (auxPath != null) return auxPath.stream().mapToInt(i -> i).toArray();
        else return new int[0];
    }

    // funcion recursiva usada para calcular el camino entre 2 nodos.
    private void findPathUtil(int u, int d, boolean[] isVisited, List<Integer> localPathList)
    {

        if (u == d) {
            //usamos una variable auxiliar para extraer el path que encontramos.
            this.auxPath = new ArrayList<>(localPathList);

            // si u y d son iguales, llegamos.
            return;
        }

        isVisited[u] = true;

        // buscamos todos los adjacentes el nodo actual
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                localPathList.add(i);
                findPathUtil(i, d, isVisited, localPathList);
                localPathList.remove(i);
            }
        }

        isVisited[u] = false;
    }
}

class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numVertex = Integer.parseInt(bufferedReader.readLine().trim());

        Graph g = new Graph(numVertex);
        int result = 0;

        //leemos la entrada y populamos el grafo
        IntStream.range(0, numVertex-1).forEach(qItr -> {
            try {
                String[] line = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int vertex1 = Integer.parseInt(line[0]);
                int vertex2 = Integer.parseInt(line[1]);
                char color = line[2].charAt(0);
                g.addEdge(vertex1-1,vertex2-1,color);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        for (int x=0; x < numVertex; x++) {
            for (int y=x+1; y < numVertex; y++) {
                for (int z=y+1; z < numVertex; z++) {
                    //if all paths in the triplet have red, the we have a winner
                    if(g.hasPathWithRed(x,y) && g.hasPathWithRed(y,z) && g.hasPathWithRed(x,z)) {
                        result++;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
