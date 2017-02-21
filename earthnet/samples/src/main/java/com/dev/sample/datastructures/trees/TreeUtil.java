package com.dev.sample.datastructures.trees;

import com.dev.sample.vo.Node;

/**
 * Created by AjayMenon on 2/20/2017.
 */
public class TreeUtil {

    /**
     * Return the root of Tree after tree construction.
     *
     * @param comparables
     * @return
     */
    public static Node createBinaryTree(Comparable[] comparables) {
        BinarySearchTree bst = new BinarySearchTree();

        for (Comparable comparable : comparables) {
            bst.insert(comparable);
        }

        return bst.getRoot();

    }
}
