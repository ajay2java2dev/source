package com.dev.sample.datastructures.trees;

import com.dev.sample.vo.Node;

/**
 * http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * <p>
 * The implementation here is a simple BinarySearchTree implementation using Node, Comparable etc
 * <p>
 * There are various other other implementations and design approaches of implementing a Binary Search Tree problem.
 * <p>
 * 1) Nodes smaller than root to left and greater ones to right.
 * 2) Insert (Comparable value) : O(lg(n))
 * 3) Find (Comparable value) : O(lg(n))
 * 4) Delete (Comparable value) : O(lg(n))
 * 5) Display (Comparable value) : O(n)
 * 6) Delete (Comparable value) : O(lg(n))
 */
public class BinarySearchTree {

    private static Node root;
    private Integer level = -1;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * --> start from the root and compare root.data with searchVal.
     * --> if root.data > searchVal goto left else goto right.
     * --> if root.data = searchVal return the index else return false.
     *
     * @param searchVal
     * @return index position found or the tree level. If no entry found return -1.
     */
    protected Comparable find(final Comparable searchVal) {
        if (searchVal != null) {
            Node current = root;
            while (current != null) {
                if (current.getData().compareTo(searchVal) > 0) {
                    current = current.getLeftNode();
                } else if (current.getData().compareTo(searchVal) < 0) {
                    current = current.getRightNode();
                } else {
                    return current.getData();
                }
            }
        }
        return null;
    }

    /**
     * Delete a node from the BST.
     * @param deleteVal
     * @return
     */
    protected Comparable delete(final Comparable deleteVal) {
        if (deleteVal != null) {
            Node current = root;
            Node parent = root;
            boolean isLeftChild = false;

            while (current.getData().compareTo(deleteVal) != 0) {

                parent = current; //very important in deletion that the parent and current move together.

                if (current.getData().compareTo(deleteVal) > 0) {
                    isLeftChild = true;
                    current = current.getLeftNode();
                } else if (current.getData().compareTo(deleteVal) < 0) {
                    isLeftChild = false;
                    current = current.getRightNode();
                }

                if (current == null) {
                    return -1;
                }
            }

            //case 1. where we have reached a node which doesn't have left or the right node.
            if (current.getLeftNode() == null && current.getRightNode() == null) {
                if (current == root) {
                    //i.e the current and root are same. then make root = null;
                    root = null;
                }
                if (isLeftChild) {
                    //make parents left node == null
                    parent.setLeftNode(null);
                } else {
                    //make parents right node == null
                    parent.setRightNode(null);
                }
            } else if (current.getRightNode() == null) {
                if (current == root) {
                    root = current.getLeftNode();
                } else if (isLeftChild) {
                    parent.setLeftNode(current.getLeftNode());
                } else {
                    parent.setRightNode(current.getRightNode());
                }
                return 1;
            } else if (current.getLeftNode() == null) {
                if (current == root) {
                    root = current.getRightNode();
                } else if (isLeftChild) {
                    parent.setLeftNode(current.getLeftNode());
                } else {
                    parent.setRightNode(current.getRightNode());
                }
                return 1;
            } else if (current.getLeftNode() != null && current.getRightNode() != null) {
                Node successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.setLeftNode(successor);
                } else {
                    parent.setRightNode(successor);
                }

                successor.setLeftNode(current.getLeftNode());
                return 1;
            }

        }
        return null;
    }


    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.getRightNode();
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.getLeftNode();
        }
        if (successsor != deleleNode.getRightNode()) {
            successsorParent.setLeftNode(successsor.getRightNode());
            successsor.setRightNode(deleleNode.getRightNode());
        }
        return successsor;
    }

    /**
     * --> current == root;
     * --> start with current and check compare root.node with insertVal
     * --> if current.data compareTo insertVal > 0, goto left, else goto right.
     * --> if any point, currrent == null, insert data here. If parent is less than insertVal, insert right, else left.
     *
     * @param insertVal
     * @return
     */
    protected Integer insert(final Comparable insertVal) {

        if (insertVal != null) {

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
        binarySearchTree.insert(new Integer(15));
        binarySearchTree.insert(new Integer(28));
        binarySearchTree.insert(new Integer(16));
        binarySearchTree.insert(new Integer(25));
        binarySearchTree.insert(new Integer(30));
        binarySearchTree.insert(new Integer(14));
        binarySearchTree.insert(new Integer(19));
        binarySearchTree.insert(new Integer(24));
        binarySearchTree.insert(new Integer(26));
        binarySearchTree.insert(new Integer(29));
        binarySearchTree.insert(new Integer(31));
        binarySearchTree.insert(new Integer(17));
        binarySearchTree.insert(new Integer(20));

        binarySearchTree.display(binarySearchTree.root);
        System.out.println("found : " + binarySearchTree.find(15));
        System.out.println("found : " + binarySearchTree.find(28));

        binarySearchTree.delete(new Integer(25));
        binarySearchTree.display(binarySearchTree.root);

    }

}
