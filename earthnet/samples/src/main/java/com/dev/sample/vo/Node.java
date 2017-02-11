package com.dev.sample.vo;

/**
 * Created by AjayMenon on 2/11/2017.
 */
public class Node<T> {
    Comparable data;

    Node<T> leftNode;

    Node<T> rightNode;

    public Node (Comparable data) {
        this.data = data;
        leftNode = null;
        rightNode = null;
    }

    public Comparable getData() {
        return data;
    }

    public void setData(Comparable data) {
        this.data = data;
    }

    public Node<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
    }

    public Node<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
    }
}
