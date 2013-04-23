/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.Comparator;

/**
 *
 * @author Avinash
 */
public class nameComparator implements Comparator<vertex>{

   @Override
    public int compare(vertex a,vertex b){

             return a.name.compareTo(b.name);

    }
 

}
