/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saipati
 */
public class Node<T> {
    T x;
    int timer;
    Node left,right,parent;
    Node(T value)
    {
        this.x = value;
        this.left =null;
        this.right =null;
        this.parent = null;
        this.timer =0;
        
    }
    
    Node(T value,int timer)
    {
        this.x = value;
        this.left =null;
        this.right =null;
        this.parent = null;
        this.timer = timer;
        
    }
    
}


