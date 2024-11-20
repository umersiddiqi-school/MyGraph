package org.example;

/**
 * @author Umer Siddiqi
 * Used to create Edge objects
 * Each Edge object holds values for two vertices and a weight
 */
public class Edge {
    int v1;
    int v2;
    int weight;

    /**
     * Default Constructor
     * @param v1
     * @param v2
     * @param weight
     */
    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    /**
     * @return value of v1
     */
    public int getV1() {
        return v1;
    }

    /**
     * @return value of v2
     */
    public int getV2() {
        return v2;
    }

    /**
     * @return value of weight
     */
    public int getWeight() {
        return weight;
    }
}
