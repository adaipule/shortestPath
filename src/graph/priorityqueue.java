/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.*;
public class priorityqueue {

public Vector<vertex> Qlist;

public priorityqueue(){
    Qlist=new Vector<vertex>();
}//end of constructor

/*This function does the heapifying*/
public void minHeapify(int i)
{
   
    int l,r,minimum=0;
   vertex temp1;
   vertex temp2; 
    l=left(i);
    r=right(i);
    minimum=i;
        if(l<Qlist.size())
        {
        temp1=Qlist.elementAt(i);
        temp2=Qlist.elementAt(l);
        if(temp1.getDistance()>temp2.getDistance())
            {minimum=l;}
        
        }
    if(r<Qlist.size())
    {
        temp1=Qlist.elementAt(minimum);
        temp2=Qlist.elementAt(r);

        if(temp1.getDistance()>temp2.getDistance())
            {minimum=r;}
    
    }
    
    if(minimum!=i)
    {
            Collections.swap(Qlist,i,minimum);
    
            minHeapify(minimum);
    }

}//end of min-heapify


public int left(int i){return(2*i+1);}
public int right(int i){return((2*i)+2);}
public int parent(int i){return((i-1)/2);}
/*This functions builds heap*/
public void buildHeap()
{
  int length,i,start;
   length=Qlist.size();
   start=length/2;
   for(i=start;i>=0;i--)
    {
        minHeapify(i);
   
    }
}//end of buildheap
/*This functions removes the top element and restructure the heap*/
public vertex minExtract()
{
    vertex min;
    if(Qlist.size()<1)
    {System.out.println("HeapUnderflow");
    }
    min=Qlist.get(0);
    Collections.swap(Qlist,0,Qlist.size()-1);
    Qlist.remove(Qlist.size()-1);
    if(!Qlist.isEmpty())
    {
    minHeapify(0);
    }
    return min;
 }



}//end of priority class
  