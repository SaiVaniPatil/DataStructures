
import java.lang.reflect.Array;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saipati
 */

public class CountDownTree  {
        
    Node r;
    float d = 0;

    public CountDownTree(float d) {
        this.d = d;
    }
    
    
    
    public void add(int x)
    {
        Node p = findLast(x); 
        Node u = new Node(x,d);
        
        if(addChild(p,u))
        {
          decrementTimer(u); 
        }
        
    }
    
    private boolean addChild(Node p, Node u) {
        
        if(p==null) 
        {
            r =u;
        }
        else
        {
            if(p.value>u.value)
            {
                u.left = p.left;
                p.left = u.left;
            }
            else if(p.value<u.value)
            {
                u.right = p.right;
                p.right = u.right;
            }
            else
            {
                return false;//this is an existing node, so we are not adding anything
        //we need to set p's childrent to new node
            }
        }
        return true;
       
       
    }   
   
   
    //here we will get the previous node location where we can inster the new node
    //if the value id present, we will return the same node
    
    private Node findLast(int x) {
        Node u =r,prev =null;
        while(u!=null)
        {
            prev = u;
            if(u.value<x) u =u.left;
            else if(u.value>x) u = u.right;
            else if(u.value == x) return u;
        }
        return prev;
    }
      
    private void remove(int x)
    {
         
        Node u = find(x);
        if(u!=null)
        {
            remove(u);
            decrementTimer(u);
        }
        else
        {
            System.out.println("couldn't find node");
        }
          
    }
      
    private Node find(int x) {
        Node w = r, z = null;
        while (w != null) 
        {
            int comp = compare(x, w.x);
            if (comp < 0) {
            z = w;
            w = w.left;
            } else if (comp > 0) {
            w = w.right;
            } else {
            return w;
            }
            }
            return z == null ? null : z;
    }
      
      private void remove(Node u) {
            if (u.left == null || u.right == null) {
            splice(u);
            } else {
            Node w = u.right;
            while (w.left != null)
            w = w.left;
            u.x = w.x;
            splice(w);
            }
            
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

    private void decrementTimer(Node u) 
    {
        
        while(u!=r)
        {
            u = u.parent;
            int newTimer = u.getTimer();
            u.setTimer(newTimer--);
            
            if(newTimer ==0)
            {
                explodeSubTree(u);
                //break; doubt should we continue or leave here
            }
           
        }
        return;
    
    }

    //when a node's timer reaches 0, node explodes and entire tree subrooted at u is perfectly balanced
    //each node rebuilt in the the tree has timer set to Math.ceil(d*size(v)) (means Node.timer = Math.ceil(d*size(v))) where size(v) = size of subtree at that node

    private void explodeSubTree(Node u) {
        
        
        Node newRootNode = rebuild(u);  
        int size = sizeOfNode(newRootNode);        
        u.setTimer((int)Math.ceil(d*size));
       
        
    }
    
     public int sizeOfNode(Node u)
    {
        if(u!=null)
        {
            int size =  (1+sizeOfNode(u.left)+sizeOfNode(u.right));            
            u.setTimer((int)Math.ceil(d*size));
            return size;
        }
        else 
        {
            return 0;
        }
       
        
        //rebuildvoid rebuild(Node u) 
        

        
    }
    
    Node rebuild(Node u) 
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
        
        return a[a.length/2];
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
    if (ns == 0)
    return null;
    int m = ns / 2;
    a[i + m].left = buildBalanced(a, i, m);
    if (a[i + m].left != null)
    a[i + m].left.parent = a[i + m];
    a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
    if (a[i + m].right != null)
    a[i + m].right.parent = a[i + m];
    return a[i + m];
    }

    public static void main(String args[])
    {
        CountDownTree c= new CountDownTree(10);
        
        
    }
           
   
    
    private class Node {
        
        int value;
        int timer;
        Node left,right,parent;
        
        Node(int value, float d)
        {
            this.value = value;
            this.timer = (int)Math.ceil(d);
            this.left =null;
            this.right =null;
            this.parent = null;
            
        }
        private void setTimer(int t)
        {
            this.timer = t;
        }
        
        private int getTimer()
        {
            return this.timer;
        }
        
    }
}
