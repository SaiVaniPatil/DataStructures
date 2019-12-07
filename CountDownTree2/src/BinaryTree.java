
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saipati
 */
public class BinaryTree {

    Node r;

    public BinaryTree() {
    }
    
    

    public BinaryTree(Node r) {
        this.r = r;
    }
 
    int depth(Node u) {
        int d = 0;
        while (u != r) {
            u = u.parent;
            d++;
        }
        return d;
    }

    int size(Node u) {
        if (u == null) {
            return 0;
        }
        return 1 + size(u.left) + size(u.right);
    }

    int height(Node u) {
        if (u == null) {
            return -1;
        }
        return 1 + Math.max(height(u.left), height(u.right));
    }

    void traverse(Node u) {
        if (u == null) {
            return;
        }
        traverse(u.left);
        traverse(u.right);
    }

    void traverse2() {
        Node u = r, prev = null, next;
        while (u != null) {
            if (prev == u.parent) {
                if (u.left != null) {
                    next = u.left;
                } else if (u.right != null) {
                    next = u.right;
                } else {
                    next = u.parent;
                }
            } else if (prev == u.left) {
                if (u.right != null) {
                    next = u.right;
                } else {
                    next = u.parent;
                }
            } else {
                next = u.parent;
            }
            prev = u;
            u = next;
        }
    }

    int size2() {
        Node u = r, prev = null, next;
        int n = 0;
        while (u != null) {
            if (prev == u.parent) {
                n++;
                if (u.left != null) {
                    next = u.left;
                } else if (u.right != null) {
                    next = u.right;
                } else {
                    next = u.parent;
                }
            } else if (prev == u.left) {
                if (u.right != null) {
                    next = u.right;
                } else {
                    next = u.parent;
                }
            } else {
                next = u.parent;
            }
            prev = u;
            u = next;
        }
        return n;
    }

    void bfTraverse() {
        Queue<Node> q = new LinkedList<Node>();
        if (r != null) {
            q.add(r);
        }
        while (!q.isEmpty()) {
            Node u = q.remove();
            if (u.left != null) {
                q.add(u.left);
            }
            if (u.right != null) {
                q.add(u.right);
            }
        }
    }

    

}
