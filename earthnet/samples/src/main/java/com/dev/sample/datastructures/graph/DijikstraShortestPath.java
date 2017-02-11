package com.dev.sample.datastructures.graph;

import com.dev.sample.vo.Vertex;

/**
 * Shortest path algorithm basically a Greedy algorithm and has its similarities with PRIM's Algorithm
 * 1) Weight on edges should be a non-negative number.
 * 2) Works both on directed and undirected graphs.
 * 3) Need datastructure Heap + Map for methods :
 *
 *         --> add (O (log n), extractMin (O (log n)), Contains (O (1)), decrease - O (log n))
 *
 *   For this algorithm to function, we would need to create a connected graph and create 3 maps (a heapMap - containing
 *   all the vertices on the graph, a distance map - which will store the distance from start node to all the other nodes,
 *   and path map - which will store the paths tranversed.)
 *
 *   We will utelize all the methods mentioned above one by one
 */
public class DijikstraShortestPath {

    public static Vertex<Integer> findShortestPath() {

        return new Vertex<Integer>();
    }

    public static void main(String[] args) {

    }
}
