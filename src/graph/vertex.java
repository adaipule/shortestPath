
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 *
 * @author Avinash
 */
import java.util.*;
import java.lang.*;

public class vertex {
    private int up;
    private Float distance;
    private vertex parent;
    Vector<vertex> alist;
    public String name;
    public String color;

public vertex(String n){

        up=1;
        distance=999f;
        alist=new Vector<vertex>();
        name=n;
        color="WHITE";
}//end of constructor

public void setUp(int flag){up=flag;}

public int getUp(){return up;}

public void setParent(vertex a)
{
parent=a;
}
public vertex getParent()
{
 return(parent);
}

public void setDistance(float a)
{
  distance=a;
}
public float getDistance()
{
  return (distance);
}

public void setAlist(vertex al)
{

alist.add(al);

}

public vertex getAlist(int index)
{

return (alist.elementAt(index));
}

}//end of class
