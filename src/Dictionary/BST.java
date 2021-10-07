/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dictionary;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author GMT
 */
public class BST {

    Node root;

    public BST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(int x) {
        Node p = new Node(x);
        if (root == null) {
            root = p;
        } else {
            Node pAfter = null;
            Node pBefore = root;
            while (pBefore != null && pBefore.key != x) {
                pAfter = pBefore;
                if (x < pBefore.key) {
                    pBefore = pBefore.left;
                } else {
                    pBefore = pBefore.right;
                }
            }
            if (pBefore != null) {
                return false;
            }
            if (x < pAfter.key) {
                pAfter.left = p;
            } else {
                pAfter.right = p;
            }
            p.father = pAfter;
        }
        return true;
    }

    public void add(int... a) {
        for (int x : a) {
            this.add(x);
        }
    }

    public void printLevelBased() {
        if (root == null) {
            System.err.println("Empty Tree");
        } else {
            LinkedList<Node> queue = new LinkedList();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                Node node = (Node) queue.removeFirst();
                System.out.println(node + ", ");
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
    }

    public void printNLR(Node p) {
        if (p != null) {
            System.out.println("" + p + ", ");
            printNLR(p.left);
            printNLR(p.right);
        }
    }

    public void printNLR() {
        if (root == null) {
            System.err.println("Empty Tree");
        } else {
            printNLR(root);
        }
    }

    private void printAlgin(Node p, int nSpace) {
        if (p != null) {
            for (int i = 0; i < nSpace; i++) {
                System.out.println(" ");
            }
            System.out.println(p);
            printAlgin(p.left, nSpace + 3);
            printAlgin(p.right, nSpace + 3);
        }
    }

    public void printAlign() {
        if (root == null) {
            System.err.println("Empty Tree");
        } else {
            printAlgin(root, 0);
        }
    }

    public void printLNR(Node p) {
        if (p != null) {
            printLNR(p.left);
            System.out.println("" + p + ", ");
            printLNR(p.right);
        }
    }

    public void printLNR() {
        if (root == null) {
            System.err.println("Empty Tree");
        } else {
            printLNR(root);
        }
    }

    public void printNRL(Node p) {
        if (p != null) {
            printNRL(p.right);
            System.out.println("" + p + ", ");
            printNRL(p.left);
        }
    }

    public void printNRL() {
        if (root == null) {
            System.err.println("Empty Tree");
        } else {
            printNRL(root);
        }
    }

    public void printPostOrder(Node p) {
        if (p != null) {
            printPostOrder(p.left);
            printPostOrder(p.right);
            System.out.println("" + p + ", ");
        }
    }

    public void printPostOrder() {
        if (root == null) {
            System.err.println("Empty Tree");
        } else {
            printPostOrder(root);
        }
    }

    public Node search(int x) {
        Node result = root;
        while (result != null) {
            if (x == result.key) {
                return result;
            } else if (x < result.key) {
                result = result.left;
            } else {
                result = result.right;
            }
        }
        return null;
    }

    public int getMin() {
        if (root == null) {
            throw new RuntimeException("Empty tree!");
        }
        Node p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.key;
    }

    public int getMax() {
        if (root == null) {
            throw new RuntimeException("Empty tree!");
        }
        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p.key;
    }

    public int getHeight() {
        class Node_Level {

            Node node;
            int level;

            Node_Level(Node p, int L) {
                node = p;
                level = L;
            }
        }
        if (root == null) {
            return 0;
        }
        LinkedList<Node_Level> q = new LinkedList();
        Node_Level v;
        int result = 0;
        q.add(new Node_Level(root, 1));
        while (!q.isEmpty()) {
            v = q.removeFirst();
            int curL = v.level;
            if (result < curL) {
                result = curL;
            }
            Node left = v.node.left;
            Node right = v.node.right;
            if (left != null) {
                q.add(new Node_Level(left, curL + 1));
            }
            if (right != null) {
                q.add(new Node_Level(right, curL + 1));
            }
        }
        return result;
    }

    public double getAverage() {
        int n = 0;
        double sum = 0;
        if (root == null) {
            return 0;
        } else {
            LinkedList<Node> queue = new LinkedList();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                Node node = (Node) queue.removeFirst();
                n++;
                sum += node.key;
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return sum / n;
    }

    private boolean removeLeaf(Node leaf) {
        if (!leaf.isLeaf()) {
            return false;
        }
        if (leaf == root && root.isLeaf()) {
            root = null;
        } else {
            Node father = leaf.father;
            if (father.left == leaf) {
                father.left = null;
            } else {
                father.right = null;
            }
        }
        return true;
    }

    private boolean removeOneChildNode(Node delNode) {
        if (delNode == null || delNode.isLeaf() || delNode.having2Child()) {
            return false;
        }
        if (delNode == root) {
            if (root.left == null) {
                root = root.right;
            } else {
                root = root.left;
            }
            root.father = null;
        } else {
            Node grandFather = delNode.father;
            Node grandChild;
            if (delNode.left == null) {
                grandChild = delNode.right;
            } else {
                grandChild = delNode.left;
            }
            if (delNode == grandFather.left) {
                grandFather.left = grandChild;
            } else {
                grandFather.father = grandFather;
            }
        }
        return true;
    }

    private boolean deleteByMerging(Node delNode) {
        if (delNode.isLeaf() || delNode.havingOneChild()) {
            return false;
        }
        Node grandFather = delNode.father;
        Node leftGrandChild = delNode.left;
        Node rightGrandChild = delNode.right;

        Node rightMost = leftGrandChild;
        while (rightMost.right != null) {
            rightMost = rightMost.right;
            rightMost.right = rightGrandChild;
            rightGrandChild.father = rightMost;
        }
        if (delNode == root) {
            root = root.left;
            root.father = null;
        } else {
            if (grandFather.left == delNode) {
                grandFather.left = leftGrandChild;
            } else {
                grandFather.right = leftGrandChild;
                leftGrandChild.father = grandFather;
            }
        }
        return true;
    }

    public boolean deleteByMerging(int x) {
        Node delNode = search(x);
        if (delNode == null) {
            return false;
        }
        if (delNode.isLeaf()) {
            return removeLeaf(delNode);
        }
        if (delNode.havingOneChild()) {
            return removeOneChildNode(delNode);
        }
        return deleteByMerging(delNode);
    }

    private boolean deleteByCopying(Node delNode){
        if(!delNode.having2Child()) return false;
        Node rightMost = delNode.left;
        while (rightMost.right!=null) {            
            rightMost=rightMost.right;
        }
        delNode.key = rightMost.key;
        if(rightMost.isLeaf()) removeLeaf(rightMost);
        if(rightMost.havingOneChild()) removeOneChildNode(rightMost);
        return true;
    }
    
    public boolean deleteByCopying(int x){
        Node delNode = search(x);
        if(delNode==null) return  false;
        if(delNode.isLeaf()) return removeLeaf(delNode);
        if(delNode.havingOneChild())
            return removeOneChildNode(delNode);
        return deleteByMerging(delNode);
    }
    
    
    
    
    
    
    
    
    
    
}
