
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
public class MyComparator<T extends Comparable<T>> implements Comparator<T> {
   @Override  
  public int compare(T o1, T o2) {
    return o1.compareTo(o2);
  }
}
   
   
