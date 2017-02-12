package com.dev.sample.datastructures.trees;

import com.dev.sample.vo.Node;

/**
 * http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 *
 * The implementation here is a simple BinarySearchTree implementation using Node, Comparable etc
 *
 * There are various other other implementations and design approaches of implementing a Binary Search Tree problem.
 *
 * 1) Nodes smaller than root to left and greater ones to right.
 * 2) Insert (Comparable value) : O(lg(n))
 * 3) Find (Comparable value) : O(lg(n))
 * 4) Delete (Comparable value) : O(lg(n))
 * 5) Display (Comparable value) : O(n)
 * 6) Delete (Comparable value) : O(lg(n))
 *
 */
public class BinarySearchTree {

    private static Node root;
    private Integer level = -1;

    public BinarySearchTree () {
        this.root = null;
    }

    /**
     * --> start from the root and compare root.data with searchVal.
     * --> if root.data > searchVal goto left else goto right.
     * --> if root.data = searchVal return the index else return false.
     * @param searchVal
     * @return index position found or the tree level. If no entry found return -1.
     *
     */
    protected Integer find(final Comparable searchVal) {
        return null;
    }

    /**
     * --> current == root;
     * --> start with current and check compare root.node with insertVal
     * --> if current.data compareTo insertVal > 0, goto left, else goto right.
     * --> if any point, currrent == null, insert data here. If parent is less than insertVal, insert right, else left.
     * @param insertVal
     * @return
     */
    protected Integer insert (final Comparable insertVal) {

        if (insertVal!=null) {

            Node newNode = new Node(insertVal);

            if (root == null) {
                root = newNode;
                return level = 0;
            }

            Node current = root;
            Node parent = null;

            while (true) {
                parent = current;

                if (current.getData().compareTo(insertVal) > 0) {
                    //go left
                    current = current.getLeftNode();

                    if (current == null) {
                        parent.setLeftNode(newNode);
                        return level++;
                    }

                } else if (current.getData().compareTo(insertVal) < 0) {
                    //go right

                    current = current.getRightNode();

                    if (current == null) {
                        parent.setRightNode(newNode);
                        return level++;
                    }

                } else {
                    // return since value already exists.
                    return level;
                }
            }
        }
        return level;
    }

    //display all node
    public void display(Node node) {
        if (node != null) {
            display(node.getLeftNode());
            System.out.println(node.getData());
            display(node.getRightNode());
        }
    }


    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(new Integer(21));
        binarySearchTree.insert(new Integer(20));
        binarySearchTree.insert(new Integer(15));
        binarySearchTree.insert(new Integer(17));
        binarySearchTree.insert(new Integer(18));
        binarySearchTree.insert(new Integer(26));
        binarySearchTree.insert(new Integer(27));

        binarySearchTree.display(binarySearchTree.root);


    }

}
