package org.example;

import java.util.*;

/**
 * @author Umer Siddiqi
 * Creates MyGraph object
 * Holds list of vertices and a Map that maps vertices to edges
 */
public class MyGraph {
    private List<Integer> vertices;
    private Map<Integer, List<Edge>> adjList;

    /**
     * Default constructor
     * Initializes vertices as an ArrayList and adjList as a Hashmap
     */
    public MyGraph(){
        vertices = new ArrayList<>();
        adjList = new HashMap<>();
    }

    /**
     * adds vertex and initializes a new LinkedList for storing edges
     * @param v added to MyGraph and adjList
     */
    public void addVertex(int v){
        vertices.add(v);
        adjList.put(v, new LinkedList<>());
    }

    /**
     * adds Edge from v1 to v2 to adjList
     * @param v1 first vertex
     * @param v2 second vertex
     * @param weight of Edge
     */
    void addEdge(int v1, int v2, int weight){
        Edge edge = new Edge(v1, v2, weight);
        adjList.get(v1).add(edge);
        adjList.get(v2).add(edge);
    }

    /**
     * Used to calculate the frontier Edge with the least weight in the given graph
     * @param g graph used to calculate min frontier edge
     * @param visited boolean array that tracks which vertices have been visited
     * @return frontier Edge with the least weight
     */
    public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited){
        Edge minEdge = new Edge(0,0,Integer.MAX_VALUE); //Assigned max value so next unvisited Edge is assigned to it
        //If a vertex is visited, finds the edge with the least weight
        for(int i = 0; i < g.vertices.size(); i++){
            if(visited[i]){ //Checks if vertex is visited
                List<Edge> edges = g.adjList.get(i);
                for(int j = 0; j < edges.size(); j++){
                    Edge e = edges.get(j);
                    //Checks if only 1 vertex of an edge is visited and the other is not, and if the edge holds less weight than current minEdge
                    if (((visited[e.getV1()] && !visited[e.getV2()]) || (!visited[e.getV1()] && visited[e.getV2()])) && (e.getWeight() < minEdge.getWeight())){
                        minEdge = e;
                    }
                }
            }
        }
        return minEdge;
    }

    /**
     * Creates and returns a minimumSpanningTree given a graph using Prim's algorithm
     * @param g given graph
     * @param startingVertex to start traversing the graph
     * @return a MyGraph object which is the minimumSpanningTree
     */
    public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex){
        MyGraph MST = new MyGraph();
        //used to track which vertices have been visited
        boolean[] visited = new boolean[g.vertices.size()];
        for(int i = 0; i < g.vertices.size(); i++){
            MST.addVertex(i); //add all vertices to minimum spanning tree
        }
        visited[startingVertex] = true;
        Edge minEdge;
        while(!isBooleanArrTrue(visited)){ //While the visited array is not filled with true values
            minEdge = getMinFrontierEdge(g, visited); //gets minimum frontier edge
            visited[minEdge.getV1()] = true; //sets both vertices connected to edge true
            visited[minEdge.getV2()] = true;
            MST.addEdge(minEdge.getV1(), minEdge.getV2(), minEdge.getWeight());//adds edge to minimum spanning tree
        }
        return MST;
    }

    /**
     * Helper method used to check if all elements in a boolean array are true
     * @param arr boolean array
     * @return true if all elements are true, false otherwise
     */
    private static boolean isBooleanArrTrue(boolean[] arr){
        for (boolean b : arr) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method used to display the contents of the graph
     */
    public void showGraph(){
        System.out.print("V = { ");
        for(int i = 0; i < vertices.size(); i++){
            System.out.print(vertices.get(i) + " ");
        }
        System.out.println("}\nAdj Lists");
        for(int i = 0; i < vertices.size(); i++){
            System.out.print(i + ": ");
            for(Edge e : adjList.get(i)){
                //System.out.print("(" + e.getV1() + ", " + e.getV2() + " ," + e.getWeight() + ") ");
                System.out.printf("(%d, %d, %d) ", e.getV1(), e.getV2(), e.getWeight());
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method used to calculate the neighboring vertex with the minimum distance
     * @param g graph to calculate from
     * @param unvisitedList list to calculate from
     * @param dist array with distances of each vertex
     * @return the vertex with the least distance
     */
    public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist){
        int minNVert = unvisitedList.get(0); //set to first element of list
        int minNDist = dist[minNVert];
        //Checks if the distance at vertex i is less than minNeighborDistance
        for(int i = 0; i < unvisitedList.size(); i++){
            if(dist[i] < minNDist){
                minNDist = dist[i]; //if true, sets minNDist to the distance at vertex i
                minNVert = unvisitedList.get(i); //minimum neighboring vertex changed to vertex at index i
            }
        }
        return minNVert;
    }

    /**
     * Calculates the shortest path to each vertex given a starting vertex in a graph
     * Prints corresponding data after paths are calculated.
     * @param g graph to calculate shortest path on
     * @param startingVertex the index at which the shortest path for each other vertex is calculated
     */
    public static void shortestPath(MyGraph g, int startingVertex){
        //Initialize relevant data structures
        List<Integer> unvisitedList = new ArrayList<>();
        int[] dist = new int[g.vertices.size()];
        int[] previous = new int[g.vertices.size()];
        boolean[] visited = new boolean[g.vertices.size()];
        //Fill distance array with infinity and previous array with -1, ensures next value will override current
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        for(int i = 0; i < g.vertices.size(); i++){
            unvisitedList.add(i);
        }
        dist[startingVertex] = 0;
        //while all vertices are not visited
        while (!unvisitedList.isEmpty()){
            //Gets closest vertex, removes it from list, and sets visited to true
            int currV = getMinDistVertex(g, unvisitedList, dist);
            for(int i = 0; i < unvisitedList.size(); i++){
                if(currV == unvisitedList.get(i)){
                    unvisitedList.remove(i);
                }
            }
            visited[currV] = true;
            //Get adjList for closest vertex to find neighboring vertices
            List<Edge> edges = g.adjList.get(currV);
            for (Edge edge : edges) {
                int neighborV;
                if (currV == edge.getV1()) {
                    neighborV = edge.getV2();
                }
                else {
                    neighborV = edge.getV1();
                }
                //Checks if neighboring vertices are visited, and whether there is a shorter path for them
                if (!visited[neighborV]) {
                    int possibleDist = dist[currV] + edge.getWeight();
                    if (possibleDist < dist[neighborV]) {
                        dist[neighborV] = possibleDist;
                        previous[neighborV] = currV;
                    }
                }
            }
        }
        //Prints out relevant shortest path data
        System.out.println("Shortest Path Data");
        System.out.println("Starting Vertex: " + startingVertex);
        System.out.printf("%6s %12s", "Vertex", "Dist");
        for(int i = 0; i < g.vertices.size(); i++){
            System.out.printf("\n%6s %12s", g.vertices.get(i), dist[i]);
        }
        System.out.printf("\n%6s %12s", "Vertex", "Previous");
        for(int i = 0; i < g.vertices.size(); i++){
            System.out.printf("\n%6s %12s", g.vertices.get(i), previous[i]);
        }
    }
}
