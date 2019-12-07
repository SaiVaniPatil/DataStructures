
import java.lang.reflect.Array;
import java.util.Comparator;
import sun.security.action.GetBooleanSecurityPropertyAction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author saipati
 * @param <T>
 */
public class CountDownTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    float d = 0;
    Node r;
    
    
    public Node getRoot()
    {
        return r;
    }
    
    public void setRoot(Node r)
    {
        super.setRoot(r);
        this.r =r;
        
    }
    public CountDownTree(float d) { // create root node from default constructor
        super(null);
        this.d =d;
                        
    }   
    
     public boolean add(T x)
    {
        Node p = super.findLast(x);
         Node u = new Node(x,(int)d);
        if(p==null)
        {
            this.r =u;
            
        }
        if(super.addChild(p,u))
        {
          decrementTimer(u);
          return true;
        }
        return false;
        
    }
    
    
    private void remove(T x)
    {        
        Node u = super.findLast(x);
        if(u!=null)
        {
            super.remove(u);
            decrementTimer(u);
        }
        else
        {
            System.out.println("couldn't find node");
        }
          
    }
    
    
     private void decrementTimer(Node u) 
    {
        
        while(u.parent!=null) //root node's parent is null
        {
            u = u.parent;
            int newTimer = u.timer;
            u.timer = newTimer-1;
            
            if(u.timer ==0)
            {
                explodeSubTree(u);
                //break; doubt should we continue or leave here
            }
           
        }
        return;
    
    }
    
     private void explodeSubTree(Node u) {
        
        
        Node newRootNode = rebuild(u);  
        int size = sizeOfNode(newRootNode);        
        newRootNode.timer = ((int)Math.ceil(d*size));
       
        
    }

    Node rebuild(Node u) 
    {
        if(!isBalanced(u))
        {
            int ns = sizeOfNode(u);
            Node p = u.parent;
            Node[] a = (Node[]) Array.newInstance(Node.class, ns);
            packIntoArray(u, a, 0);
            if (p == null) {
                r = buildBalanced(a, 0, ns);
                r.parent = null;
            } else if (p.right == u) {
                p.right = buildBalanced(a, 0, ns);

                p.right.parent = p;
            } else {
                p.left = buildBalanced(a, 0, ns);
                p.left.parent = p;
            }

            return a[a.length / 2];
        }
        else
        {
            return u;
        }
    }
    
    public boolean isBalanced(Node u)
    {
        if(u ==null) return true;
        if((size(u.left) - size(u.right) <=1)&&isBalanced(u.left)&&isBalanced(u.right))
        {
            return true;
        }
        else 
           return false;
    }

    int packIntoArray(Node u, Node[] a, int i) {
        if (u == null) {
            return i;
        }
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }

    Node buildBalanced(Node[] a, int i, int ns) {
        if (ns == 0) {
            return null;
        }
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != null) {
            a[i + m].left.parent = a[i + m];
        }
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != null) {
            a[i + m].right.parent = a[i + m];
        }
        return a[i + m];
    }

    public int sizeOfNode(Node u) {
        if (u != null) {
            int size = (1 + sizeOfNode(u.left) + sizeOfNode(u.right));
            u.timer = ((int) Math.ceil(d * size));
            return size;
        } else {
            return 0;
        }

    }
    
    void print()
    {
        printInorder(this.r);
        System.out.println("pre order");
        printPreorder(this.r);
        System.out.println("post Order");
        
        
        
    }
    
    void printInorder(Node node) 
    { 
        if (node == null) 
            return; 
  
        /* first recur on left child */
        printInorder(node.left); 
  
        /* then print the data of node */
        System.out.print(node.x + " node value is "); 
         System.out.print(node.timer + " node timer is  \n "); 
  
        /* now recur on right child */
        printInorder(node.right); 
    } 
    
    void printPreorder(Node node) 
    { 
        if (node == null) 
            return; 
  
        /* first print data of node */
        System.out.print(node.x + " node value is "); 
         System.out.print(node.timer + " node timer is  \n "); 
  
        /* then recur on left sutree */
        printPreorder(node.left); 
  
        /* now recur on right subtree */
        printPreorder(node.right); 
    } 
    
    public static void main(String args[])
    {
         System.out.print("in main method ");
        CountDownTree<Integer> CDTree= new CountDownTree<Integer>(1);
        
        Node root = new Node(20,1);
        Node root2 = new Node(15,1);
        Node root3 = new Node(10,1);
        Node root4 = new Node(5,1);
        
        root.left = root2;
        root2.left = root3;
        root2.parent = root;
        root3.left = root4;
        root3.parent = root2;
        root4.parent = root3;
        
        CDTree.setRoot(root);
       CDTree.print();
       
       CDTree.add(8);
        CDTree.print();
        
        CDTree.add(2);
        CDTree.print();
        
        Node[] b = (Node[]) Array.newInstance(Node.class, 6);
        CDTree.packIntoArray(CDTree.getRoot(), b, 0);
        for(int j =0; j<b.length;j++)
        {
            System.out.print(b[j].x);
        }
        
        
        /*
       
        
        
        
        
        int[] a = {2,5,3,9,8,12,11,1,4};
        for (int i=0;i<a.length;i++)
        {
            CDTree.add(a[i]);
                    
        }
        CDTree.print();
                
        Node[] b = (Node[]) Array.newInstance(Node.class, a.length);
        CDTree.packIntoArray(CDTree.getRoot(), b, 0);
        for(int j =0; j<b.length;j++)
        {
            System.out.print(b[j].x);
        }
        
        
        /*CDTree.add(5);
        
        //CDTree.print();
        CDTree.add(2);
        //CDTree.print();
        CDTree.add(3);
        CDTree.print();
        System.out.println("\n--------");
         
        //CDTree.print();
        CDTree.add(8);
       // CDTree.print();
        CDTree.add(9);
        CDTree.print();
        
        System.out.println("\n--------");
        
        CDTree.add(12);
        
        CDTree.add(11);
        
        System.out.println("\n--------");
         CDTree.print();
        */
         
        
        
        
    }
    
}
