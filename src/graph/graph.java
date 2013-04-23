/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.*;
import java.io.*;

/**
 *
 * @author Avinash
 */
public class graph {

    Vector<vertex> vlist;
    Vector<edge> elist;
    Vector<String> slist;
    Comparator<vertex> c;
    Comparator<vertex> nc;
    Vector<vertex> rlist;
    priorityqueue pqueue;

        public graph() {
                vlist = new Vector<vertex>();
                elist = new Vector<edge>();
                slist = new Vector<String>();/* constructor for the graph class*/
                nc = new nameComparator();
                rlist = new Vector<vertex>();
                pqueue = new priorityqueue();
                    }


        /*Build Graph is a Function that creates two vector elist and vlist ,elist contains edges
         and vlist contains vertexs.

         INPUT:It takes a .txt file as input which contains data in the format
                vertex1 vertex2 edge weight.
         Output:It Creates elist vector that has all the edges and vlist vector
               which contain all the vertexes and It also creates adjacency list of each vertex

         */
         public void buildgraph(String path) {

              String[] temp;
              float weight;

                try {
                      FileInputStream fstream = new FileInputStream(path);
                      DataInputStream in = new DataInputStream(fstream);
                      BufferedReader br = new BufferedReader(new InputStreamReader(in));
                      String strLine;
                      while ((strLine = br.readLine()) != null)
                       {

                            String delimiter = " ";
                            temp = strLine.split(delimiter);
                            
                            if (!slist.contains(temp[0]))
                               {
                                vertex v = new vertex(temp[0]); /*While loop Creates vertexes*/
                                slist.add(temp[0]);
                                vlist.add(v);
                               }
                           
                            if (!slist.contains(temp[1]))
                              {
                                vertex v1 = new vertex(temp[1]);
                                slist.add(temp[1]);
                                vlist.add(v1);
                              }

                     }//end of while
            //Close the input stream
                in.close();
                     } catch (Exception e) {
                                System.err.println("Error: " + e.getMessage());
                }

            /* create the Edges*/

              try {
                    FileInputStream fstream = new FileInputStream(path);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
            
                    
                    while ((strLine = br.readLine()) != null)
                      {
                        String delimiter = " ";
                        temp = strLine.split(delimiter);
                        vertex head = null;
                        vertex tail = null;
                    
                        
                        for (int i = 0; i < vlist.size(); i++)
                                {
                                    head = vlist.elementAt(i);
                                        if (head.name.equals(temp[0])) {
                                                 break;
                                            }
                                }


                        for (int i = 0; i < vlist.size(); i++)
                                {
                                    tail = vlist.elementAt(i);
                                        if (tail.name.equals(temp[1])) {
                                                break;
                                            }
                                }

                        weight = Float.parseFloat(temp[2]);
                        edge e = new edge(head, tail, weight);
                        elist.add(e);

                
                        
                        
                        for (int i = 0; i < vlist.size(); i++)
                             {
                                  head = vlist.elementAt(i);
                                        if (head.name.equals(temp[1])) {
                                            break;
                                        }
                             }


                        for (int i = 0; i < vlist.size(); i++)
                          {
                              tail = vlist.elementAt(i);
                                if (tail.name.equals(temp[0])) {
                                    break;
                                    }
                          }

                        weight = Float.parseFloat(temp[2]);
                        edge e1 = new edge(head, tail, weight);
                        elist.add(e1);


            }//end of while and edge creation
            //Close the input stream
            in.close();
                    } catch (Exception e) {//Catch exception if any
                                System.err.println("Error: " + e.getMessage());
                                }

        /*creation of adjacecy LiSt*/



              for (int i = 0; i < vlist.size(); i++) {

                   vertex v = vlist.elementAt(i);

                   for (int j = 0; j < elist.size(); j++) {
                            edge e = elist.elementAt(j);
                            vertex h = e.getHead();

                            if (v.name.equals(h.name)) {
                            v.setAlist(e.getTail());
                            }

                        }
                }//end of creation of adjacency list

}//end of build graph


    /*
     Print Function print the vertexes in alphabetic order and its adjacent vertex
     * if a vertex is down it appends Down in front of it
     * if a Edge is down it appends down in front of the edges

     */
public void print() {
                vertex v;
                edge e = null;
                int i;
                Collections.sort(vlist, nc);/*Inbuilt sort of the performance nlog complexity*/

                for (int z = 0; z < vlist.size(); z++) {
                    vertex h = vlist.elementAt(z);/*Vertex whose adjacent list to be printed*/


                    if (h.getUp() == 0) {
                           System.out.println(h.name + "   " + "DOWN");
                            } else {
                                System.out.println(h.name);
                                }



                        if (h.alist.isEmpty()) {
                            System.out.println("Adjacency list is empty");
                            } else {

                                    Collections.sort(h.alist, nc);

                                    for (i = 0; i < h.alist.size(); i++) {
                                        v = h.alist.elementAt(i);/*Vertex adjacent to vertex h*/
                                        System.out.print("  " + v.name + "  ");

                                        for (int j = 0; j < elist.size(); j++) {
                                             e = elist.elementAt(j);

                                                if ((e.getHead().name.equals(h.name)) && (e.getTail().name.equals(v.name)))
                                                    {
                                                    break;
                                                    }
                                            }

                                            if (e.getUp() == 0) {
                                            System.out.print(e.getWeight() + "   " + "DOWN");
                                            } else {
                                                    System.out.print(e.getWeight());
                                               }

                                           System.out.println();

                                    }//end of priting ajaceccy vertext and the edge weight
                            }//end of else
                    }//end of out for
        }//end of print*/




/*Function to that applys Dijkstras and find the shortest distance from Sourcevertex to the
  destination vertex
  
  INPUT: Source and the Destination String
  output:the path from Source to destination if exist */

 public void dijkstras(String s, String d) {
        vertex v = null;
        vertex u = null;
        edge e = null;
        vertex source = null;
        vertex temp = null;
        vertex destination = null;
        int flag = 0;
        int flag1 = 0;
        int flag2 = 0;
        Vector<vertex> t = new Vector<vertex>();

        /*initialize all the vertices distance to high number and parent to null*/
        for (int i = 0; i < vlist.size(); i++) {
            v = vlist.elementAt(i);
            v.setDistance(9999.0f);/*Initilize all the vertex Distance to a high numbe */
            v.setParent(null);
        }
/*get the destination vertex*/
       for (int i = 0; i < vlist.size(); i++) {
            destination = vlist.elementAt(i);
            if (destination.name.equals(d)) {
                flag1 = 1;
                break;
            }
            }//obtaining the destination vertex

        //check if destination vertex exit and is up
            if (flag1 == 0) {
                System.out.println("Destination Vertex"+ d +"does not exist");
                return;
            }
            else {
                if (destination.getUp() == 0) {
                System.out.println("the vertex : " + d + " is down ");
                return;
                }
            }

/*get the source vertex*/
        for (int i = 0; i < vlist.size(); i++) {
            source = vlist.elementAt(i);
            if (source.name.equals(s)) {        /*obtain the source Vertex*/
                flag2 = 1;
                break;
            }
        }
/*check if source vertex exist and is up*/
        if (flag2 == 0) {
            System.out.println("Source vertex "+ s +" does not exist");
            return;
        } else {
            if (source.getUp() == 0) {
                System.out.println("the vertex :" +  s  + "is down");
                return;
            }
        }



//set the source distance to 0
            source.setDistance(0);


         /*adding all the element in the prioriy queue*/
        for (int i = 0; i < vlist.size(); i++) {

                 v = vlist.elementAt(i);
                 pqueue.Qlist.add(v);   
            }
        
            pqueue.buildHeap();//build Heap

        /*Dijstras implementation*/
        while (!pqueue.Qlist.isEmpty()){
           //get the minimum elemet from the queue
            u = pqueue.minExtract();
            for (int i = 0; i < u.alist.size(); i++) {

                flag = 0;
                v = u.alist.elementAt(i);


                if (v.getUp() == 1){

                      for (int j = 0; j < elist.size(); j++) {
                            e = elist.elementAt(j);
                            if ((e.getHead().name.equals(u.name)) && (e.getTail().name.equals(v.name) && e.getUp() == 1)) {
                                flag = 1;
                                break;
                             }
                        }

                      if (flag == 1) {
                            if (v.getDistance() > (u.getDistance() + e.getWeight())) {
                                v.setDistance(u.getDistance() + e.getWeight());
                                v.setParent(u);
                                pqueue.buildHeap();
                            }
                        }
                    }
                }
            }//end of dijstras Implementation

        pqueue.Qlist.clear();


      /*printing the path from source to destination*/

        for (int i = 0; i < vlist.size(); i++) {
                destination = vlist.elementAt(i);
                if (destination.name.equals(d)) {
                     break;
                 }
            }//obtaining the source vetex

        temp = destination;
        t.add(temp);

        while (!temp.name.equals(s)) {
            if(temp.getParent()==null)
             {   
                System.out.println("There is no path from "+ s +"to"+ d);
                return;
             }

            for (int i = 0; i < vlist.size(); i++) {
               u = vlist.elementAt(i);
               if (u.name.equals(temp.getParent().name)) {
                            break;
                 }
             }
            temp=u;
            t.add(u);
          }

        for (int i = t.size() - 1; i >= 0; i--) {
            u = t.elementAt(i);
            System.out.print(u.name + "  ");
           }

        System.out.printf("%.2f", destination.getDistance());
        t.clear();
        System.out.println();
    }// end of dijstras

    /*addedge Function add a edge
     INPUT:Name of the headVertex and tail vertex of the edge which has to be added to the edgelist
     OUTPUT:add the edge to edge list
     */

 public void addedge(String h, String t, String time) {

        int flag = 0;
        edge e = null;
        float weight;

        //if head vertex is not there in the vertex list create a vertex
        if (!slist.contains(h)) {
            vertex v = new vertex(h);
            slist.add(h);
            vlist.add(v);
         }
         //if head vertex is not there in the vertex list create a vertex
        if (!slist.contains(t)) {
            vertex v1 = new vertex(t);
            slist.add(t);
            vlist.add(v1);
        }

        for (int j = 0; j < elist.size(); j++) {
            e = elist.elementAt(j);
            if ((e.getHead().name.equals(h)) && (e.getTail().name.equals(t))) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
                vertex head = null;
                vertex tail = null;

                for (int i = 0; i < vlist.size(); i++) {
                head = vlist.elementAt(i);
                if (head.name.equals(h)) {
                    break;
                }
            }


            for (int i = 0; i < vlist.size(); i++) {
                tail = vlist.elementAt(i);
                if (tail.name.equals(t)) {
                    break;
                }
            }

            head.alist.add(tail);//add the vetex to its adjacency list
            weight = Float.parseFloat(time);
            edge e1 = new edge(head, tail, weight);
            elist.add(e1);
           } //end of if

        else {
            weight = Float.parseFloat(time);
            e.setWeight(weight);
           }

 }//end of add edge


    /*edgeUp Function deletes a edge
     INPUT:Name of the headVertex and tail vertex of the edge which has to be deleted
     OUTPUT:Delete the edge
     */

    public void deleteedge(String h, String t) {

        int flag1 = 0;
        int flag2 = 0;
        int flag = 0;
        int j;
        int i;
        edge e;

        vertex head = null;
        vertex tail = null;
        //get the head of the vertex
        for (i = 0; i < vlist.size(); i++)
          {
            head = vlist.elementAt(i);
            if (head.name.equals(h)) {
                flag1 = 1;
                break;
            }
         }

         //get the tail of the vertex
        for (i = 0; i < vlist.size(); i++) {
            tail = vlist.elementAt(i);
            if (tail.name.equals(t)) {
                flag2 = 1;
                break;
            }
        }


        if (flag1 == 1 && flag2 == 1) {
            for (j = 0; j < elist.size(); j++) {
                e = elist.elementAt(j);
                if ((e.getHead().name.equals(h)) && (e.getTail().name.equals(t))) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 1) {

                for (i = 0; i < head.alist.size(); i++) {
                    tail = head.alist.elementAt(i);
                    if (tail.name.equals(t)) {
                        flag2 = 1;
                        break;

                    }
                }


                head.alist.removeElementAt(i);//you need to remove element from the adjacency list too
                elist.removeElementAt(j);

            }

        }


    }//end of edgedelete
    
    
    
    /*edgeUp Function makes a edge up
     INPUT:Name of the headVertex and tail vertex of the edge which has to be set to up
     OUTPUT:Set the edge to up
     */

    public void edgeUP(String h, String t) {
        int j;
        edge e = null;
        int flag = 0;

      //get the edge whose head and tail matches the edge from the edge list

        for (j = 0; j < elist.size(); j++) {
            e = elist.elementAt(j);
            if ((e.getHead().name.equals(h)) && (e.getTail().name.equals(t))) {
                flag = 1;
                break;
            }
        }

        if (flag == 1) {
            e.setUp(1);
        }
    }//end of the edge up

    /*edgeDown Function makes a edge down
     INPUT:Name of the headVertex and tail vertex of the edge which has to be set to Down
     OUTPUT:Set the edge to down
     */

    public void edgeDown(String h, String t) {
        int j;
        edge e = null;
        int flag = 0;


        //get the edge whose head and tail matches the edge from the edge list
        for (j = 0; j < elist.size(); j++) {
            e = elist.elementAt(j);
            if ((e.getHead().name.equals(h)) && (e.getTail().name.equals(t))) {
                flag = 1;
                break;
            }
        }

        if (flag == 1) {
            e.setUp(0);


        }


    }//end of the edge down

    /*vertexup Function makes a vertex up
     INPUT:Name of the Vertex which has to be set to up
     OUTPUT:Set the vetex to up
     */

   public void vertexUp(String v) {

        vertex head = null;
        int flag = 0;
        int i;
//get the vetex whose name matches from the vertex list
        for (i = 0; i < vlist.size(); i++) {
            head = vlist.elementAt(i);
            if (head.name.equals(v)) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            head.setUp(1);
        }

    }//end of vertex Up


    /*vertexDown Function makes a vertex down
     INPUT:Name of the Vertex which has to be set to Down
     OUTPUT:Set the vetex to down
     */

   public void vertexDown(String v) {

        vertex head = null;
        int flag = 0;
        int i;
//get the vertex whose name matches from the vertex list
        for (i = 0; i < vlist.size(); i++) {
            head = vlist.elementAt(i);
            if (head.name.equals(v)) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            head.setUp(0);
        }

    }//end of vertex down;

    /*
     Rechable function prints all the vertex rechable from all the vertex
     * It is a modification of dfs 
     *  OUTPUT:prints all the vertices which are rechable from a given vertex
     */


    public void Rechable() {

        int i;
        vertex temp;
        vertex v;
        Collections.sort(vlist, nc);
        System.out.println();
        for (i = 0; i < vlist.size(); i++) {
            /*get the elemet from the vertex list*/
            temp = vlist.elementAt(i);
            /*check if its up*/
            if (temp.getUp() == 1) {
                System.out.println(temp.name);

                /*initialize all the vertex color to white*/
                for (int j = 0; j < vlist.size(); j++) {
                    v = vlist.elementAt(j);
                    v.color = "WHITE";
                }
                reachable_dfs(temp);
                /*sort and print the vertices in reachable list*/
                Collections.sort(rlist, nc);

                for (int j = 0; j < rlist.size(); j++) {
                    v = rlist.elementAt(j);
                    System.out.println("  " + v.name);
                }

            }
            rlist.clear();
        }
    }//end of rechable


    public void reachable_dfs(vertex temp) {
        int i;
        temp.color = "GRAY";
        vertex v;
        edge e;
        int flag=0;
        for (i = 0; i < temp.alist.size(); i++) {
            /*get the vertex in the adjacent list*/
            v = temp.alist.elementAt(i);
            if (v.color.equals("WHITE") && v.getUp() == 1) {
                for(int j=0;j<elist.size();j++)
                    {
                        e=elist.elementAt(j);
                        /*get the edge and check if it is UP*/
                        if(e.getHead().name.equals(temp.name)&&e.getTail().name.equals(v.name)&&e.getUp()==1)
                            {
                                rlist.add(v);
                                v.color = "GRAY";
                                reachable_dfs(v);
                            }
                
                 }
        

            }

        }

    }//end of reachable_dfs
}//end of the class
