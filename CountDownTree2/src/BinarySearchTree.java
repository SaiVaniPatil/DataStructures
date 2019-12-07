
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author saipati
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree implements Comparator<T> {

    Node r;
    public BinarySearchTree(Node r) {

        super();
        this.r =r;
    }
     public void setRoot(Node r)
    {
        
        this.r =r;
        
    }

    T findEQ(T x) {
        Node u = r;
        while (u != null) {
            int comp = compare(x, (T) u.x);
            if (comp < 0) {
                u = u.left;
            } else if (comp > 0) {
                u = u.right;
            } else {
                return (T) u.x;
            }
        }
        return null;
    }

    T find(T x) {
        Node w = r, z = null;
        while (w != null) {
            int comp = compare(x, (T) w.x);
            if (comp < 0) {
                z = w;
                w = w.left;
            } else if (comp > 0) {
                w = w.right;
            } else {
                return (T) w.x;
            }
        }
        return z == null ? null : (T) z.x;
    }

    boolean add(T x) {
        Node p = findLast(x);
        return addChild(p, new Node(x));
    }

    boolean addChild(Node p, Node u) {
        if (p == null) {
            r = u; // inserting into empty tree
        } else {
            int comp = compare((T) u.x, (T) p.x);
            if (comp < 0) {
                p.left = u;
            } else if (comp > 0) {
                p.right = u;
            } else {
                return false; // u.x is already in the tree
            }
            u.parent = p;
        }
        return true;
    }

    Node findLast(T x) {
        Node w = r, prev = null;
        while (w != null) {
            prev = w;
            int comp = compare((T) x, (T) w.x);
            if (comp < 0) {
                w = w.left;
            } else if (comp > 0) {
                w = w.right;
            } else {
                return w;
            }
        }
        return prev;
    }

    void splice(Node u) {
        Node s, p;
        if (u.left != null) {
            s = u.left;
        } else {
            s = u.right;
        }
        if (u == r) {
            r = s;
            p = null;
        } else {
            p = u.parent;
            if (p.left == u) {
                p.left = s;
            } else {
                p.right = s;
            }
        }
        if (s != null) {
            s.parent = p;
        }

    }

    void remove(Node u) {
        if (u.left == null || u.right == null) {
            splice(u);
        } else {
            Node w = u.right;
            while (w.left != null) {
                w = w.left;
            }
            u.x = w.x;
            splice(w);
        }
    }

    void rotateLeft(Node u) {
        Node w = u.right;
        w.parent = u.parent;
        if (w.parent != null) {
            if (w.parent.left == u) {
                w.parent.left = w;
            } else {
                w.parent.right = w;
            }
        }
        u.right = w.left;
        if (u.right != null) {
            u.right.parent = u;
        }
        u.parent = w;
        w.left = u;
        if (u == r) {
            r = w;
            r.parent = null;
        }
    }

    void rotateRight(Node u) {
        Node w = u.left;
        w.parent = u.parent;
        if (w.parent != null) {
            if (w.parent.left == u) {
                w.parent.left = w;
            } else {
                w.parent.right = w;
            }
        }
        u.left = w.right;
        if (u.left != null) {
            u.left.parent = u;
        }
        u.parent = w;
        w.right = u;

        if (u == r) {
            r = w;
            r.parent = null;
        }
    }
    
    @Override  
  public int compare(T o1, T o2) {
    return o1.compareTo(o2);
  }

}
