/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 *
 * @author Avinash
 */
public class edge {

  private  vertex head;
  private  vertex tail;
  private  float weight;
  private   int up;

      public edge(vertex h,vertex t,float w)
      {
        head=h;
        tail=t;
        weight=w;
        up=1;

      }

      public vertex getHead()
      {

        return(head);
      }

      public vertex getTail()
      {
      return tail;
      }

      public void setHead(vertex h){head=h;}
      public void setTail(vertex t){tail=t;}

      public void setWeight(float w){weight=w;}
      public float getWeight(){return weight;}

      public void setUp(int a){up=a;}
      public int getUp(){return up;}

}//end of edge class
