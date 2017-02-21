package com.dev.sample.datastructures.trees;

import com.dev.sample.vo.Node;

import java.util.HashSet;
import java.util.Queue;

/**
 * Created by AjayMenon on 12/25/2016.
 * References : https://en.wikipedia.org/wiki/Breadth-first_search,
 * <p>
 * - Bread first search is a graph traversal algorithm.
 * - The algorithm starts with a node "i" and then will visit the neighbours of i and continue visiting neighbours.
 * - A queue is used to traverse the graph.
 * - The traversal is very much similar to binary search tree traversal but in this case we need to keep a track if a
 * node has been visited or not.
 * - Invented by E.F Moore - To find the shortest path of a Maze. Also used for wire routing algorithm.
 * </p>
 */
public class BreadthFirstSearch {

    private Node rootNode;
    private HashSet<Node> nodeHashSet;
    private Queue<Node> nodeQueue;

    public static void main(String[] args) {
        Integer[] elements = {12, 22, 23, 25, 26, 21, 11, 10, 8};

    }

    public boolean traverse(Node rootNode) {

        return false;
    }

    public Node getBinaryTree(Comparable[] comparables) {
        return TreeUtil.createBinaryTree(comparables);
    }
}
