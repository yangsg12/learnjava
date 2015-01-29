package com.java;

/**
 * Created by Yang on 2015/1/27.
 */
public class Node {
    private Object nodeValue;
    private Node leftChild;
    private Node rightChild;

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setNodeValue(Object nodeValue) {
        this.nodeValue = nodeValue;
    }



    public Node(Object nodeValue, Node leftChild, Node rightChild) {
        this.nodeValue = nodeValue;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(Object nodeValue, Node leftChild) {
        this(nodeValue, leftChild, null);
    }

    public Object getNodeValue() {
        return nodeValue;
    }


}
