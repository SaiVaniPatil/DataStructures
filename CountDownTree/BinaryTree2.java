
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
public class BinaryTree2 {
    
    Node r;
    
    
    public int depth(Node r,Node u,int depth)
    {
        if(r==null) return 0;
        if(u ==r) return depth; 
        int levelDepth = depth(r.left,u,depth+1);
        if(levelDepth!=0)
            return levelDepth;
        levelDepth = depth(r.right,u,depth+1);
        return levelDepth;
    }
    
    //using breadthfirsttraverse
    
    //go from root and scan from left to right
    //implemnted using a queue, we need a queue of nodes
    public void traverse(){
    
          Queue<Node> q=new LinkedList<Node>();
           if(r!=null) q.add(r);
           while(!q.isEmpty())
           {
               Node u = q.remove();
               if(u.left!=null) q.add(u.left);
               if(u.right!=null) q.add(u.right);
               
           }       
        
    }
    
    public void sizeoftheTree()
    {
        int size=0;
        Queue<Node> q=new LinkedList<Node>();
           if(r!=null) q.add(r);
           while(!q.isEmpty())
           { size++;
               Node u = q.remove();
               if(u.left!=null) q.add(u.left);
               if(u.right!=null) q.add(u.right);
               
           } 
        
    }
    
    
    public void insertinBST()
    {
        
    }
    
    public void insertInBT(int x)
    {
        Node p = findLast(x); 
        
        addChild(p,new Node(x));
        
    }
    
    private void addChild(Node p, Npde u) {
        
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
                return;//this is an existing node, so we are not adding anything
        //we need to set p's childrent to new node
            }
        }            
       
       
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
    
    
    public Node searchinBST(int x)
    {   
        Node u=r;
        
        while(u!=null)
        {
            if(u.value<x) u =u.left;
            else if(u.value>x) u = u.right;
            else if(u.value == x) return u;
        }
       
        return null;
       
    }
    
     private void deleteNode(int x)
    {
        Node p = findPrevious(x);
        Node u = findLast(x);
        if(p ==null)
        {
            //u is root node
            this.r= u.left; //making tree empty
        }
        else
        {
            if(u.left!=null) p.left = u.left;
            if(u.right!=null)p.right = u.right;
        }
        
        
    }

    private Node findPrevious(int x) {
        
         Node u =r,prev =null;
        while(u!=null)
        {
            if(u.value<x) u =u.left;
            else if(u.value>x) u = u.right;
            else if(u.value == x) return prev;
            
            prev = u;
        }
        
        return prev;
        }
    
    private boolean isBalanced(Node u)
    {
        if(u ==null) return true;
        if((size(u.left) - size(u.right) <=1)&&isBalanced(u.left)&&isBalanced(u.right))
        {
            return true;
        }
        else 
           return false;
    }
    
    private int size(Node u)
    {
        if(u == null) return 0;
        else
            return (1+size(u.left)+size(u.right));
        
    }
    
    private boolean isBalanced(Node u,Height height)
    {
        if(u == null) 
        {
            height.height =0;
            return true;
        }
        /*get heights of left and right subtrees*/
        
        Height lheight =new Height();
        Height rheight = new Height();
        boolean l = isBalanced(u.left, lheight); 
        boolean r = isBalanced(u.right, rheight); 
        int lh = lheight.height, rh = rheight.height;
        
        /* Height of current node is max of heights of 
           left and right subtrees plus 1*/
        height.height = (lh > rh ? lh : rh) + 1; 
  
        /* If difference between heights of left and right 
           subtrees is more than 2 then this node is not balanced 
           so return 0 */
        if ((lh - rh >= 2) || (rh - lh >= 2)) 
            return false; 
        return l&&r;       
    }
    
    
    
    private class Height            {
        int height;
    }

    

  
    private class Node {
    int value;
    Node left;
    Node right;
   
    
    Node(int value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        
        
        
        
    }
}
}
