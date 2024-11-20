package org.example;

/**
 * @author Umer Siddiqi
 * Used to draw graph and test MyGraph methods
 */
public class Main {
    public static void main(String[] args) {
        MyGraph testGraph = new MyGraph();
        //Creating 10 vertices
        for (int i = 0; i < 10; i++) {
            testGraph.addVertex(i);
        }
        //Adding corresponding edges
        testGraph.addEdge(0,2,1);
        testGraph.addEdge(0,1,10);
        testGraph.addEdge(0,3,8);
        testGraph.addEdge(1,3,2);
        testGraph.addEdge(2,4,1);
        testGraph.addEdge(2,5,7);
        testGraph.addEdge(3,6,1);
        testGraph.addEdge(3,5,8);
        testGraph.addEdge(4,7,1);
        testGraph.addEdge(5,7,1);
        testGraph.addEdge(5,8,9);
        testGraph.addEdge(6,8,1);
        testGraph.addEdge(6,9,9);
        testGraph.addEdge(7,8,2);
        testGraph.addEdge(8,9,1);
        System.out.println("Source Graph");
        MyGraph MST = MyGraph.minimumSpanningTree(testGraph, 0);
        testGraph.showGraph();
        System.out.println("Minimum Spanning Tree");
        MST.showGraph();
        MyGraph.shortestPath(testGraph,0);
    }
}