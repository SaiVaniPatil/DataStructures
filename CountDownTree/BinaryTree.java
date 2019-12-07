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
       
    
    Node r; // root node for a tree
    
    //depth is length from root to u
    //go from root to parent till parent = null
    public int depth(Node u, int depth)
    {
        if(u.parent!=null)
        {   depth++;
            depth(u.parent,depth);
        }
        
            //this is root node
            return depth;
        
        
    }
    
    //depth is length from root to u
    //go from root to parent till parent = null
    public int depth (Node u)
    {
        int depth =0;
        while (u.parent!=null)
        {
            depth++;
            u = u.parent;
            
        }
        return depth;
        
    }
    
    //no of nodes fron there
    public int sizeOfNode(Node u)
    {
        if(u!=null)
        {
            return (1+sizeOfNode(u.left)+sizeOfNode(u.right));
        }
        else 
        {
            return 0;
        }
        
    }
    
    //height of node
    //height is from u to lowermost node
    public int height(Node u)
    {
        if(u==null) return 0;
        return 1+Math.max(height(u.left),height(u.right));
    }
    
    
    //traversing a binary tree
    //touching all nodes in a tree .. traverse left tree and traverse right tree recursively
    
    public void traverse(Node u)
    {
        if(u!=null)
        {
            traverse(u.left);
            traverse(u.right);
        }
        
    }
    
    //disadvantage if tree is large, the stack will be full with recursive calls and stackoverflow exception may occur
    //therefore we go with a  code where if we are 
//1. coming from parent, go to left 
    //2. coming from left go to right
    //3. coming from right go to parent
    
    public void traverse2()
    { 
        Node u = r,prev = null,next=null;
       while (u!=null)
       {
           if(prev == u.parent)
           {
               if(u.left!=null) next =u.left;
               else if(u.right!=null) next = u.right;
               else next = u.parent;
           }
           else if(prev ==u.left)
           {
               if(u.right!=null) next = u.right;
               else next = u.parent;
           }
           else if(prev == u.right)
           {
              next = u.parent;
               
           }
           prev =u;
           u = next;
               
       }
    }
    
    //find height of tree
    public void sizeOfTheTree()
    { 
        Node u = r,prev = null,next=null;
        int height =0;
       while (u!=null)
       {
           if(prev == u.parent)
           {
               height++;
               if(u.left!=null) next =u.left;
               else if(u.right!=null) next = u.right;
               else next = u.parent;
           }
           else if(prev ==u.left)
           {
               if(u.right!=null) next = u.right;
               else next = u.parent;
           }
           else if(prev == u.right)
           {
              next = u.parent;
               
           }
           prev =u;
           u = next;
               
       }
      
    }
    
    public void rebuild(Node u)
    {
        int ns = size(u);cn
    }
    
    
    private class Node {
    int value;
    Node left;
    Node right;
    Node parent;
    
    Node(int value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        
        
        
    }
}
}
