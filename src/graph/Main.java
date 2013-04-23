/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.io.*;
import java.lang.*;

/**
 *
 * @author Avinash
 */
public class Main {

    
  public static void main(String[] args) {
      
         String temp[];
         graph g=new graph();
         g.buildgraph("network.txt");

         while(true){
                System.out.println();
                System.out.println("Enter the command");
                System.out.println();

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String name = null;
                    try {
                        name = br.readLine();
                        } catch (IOException e)
                        {
                            System.out.println("Error!");
                            System.exit(1);
                        }
                    String delimiter =" ";
                    temp = name.split(delimiter);


               if( temp[0].toLowerCase().equals("addedge"))
                    {
                        System.out.println();
                        g.addedge(temp[1],temp[2],temp[3]);

                    }

               else  if( temp[0].toLowerCase().equals("deleteedge"))
                    {
                        System.out.println();
                        g.deleteedge(temp[1],temp[2]);
                    }
               else if( temp[0].toLowerCase().equals("edgedown"))
                    {
                        System.out.println();
                        g.edgeDown(temp[1],temp[2]);
                     }
               else if( temp[0].toLowerCase().equals("edgeup"))
                    {
                        System.out.println();
                        g.edgeUP(temp[1],temp[2]);
                                 
                     }
                else  if( temp[0].toLowerCase().equals("vertexdown"))
                     {
                        System.out.println();
                        g.vertexDown(temp[1]);
                     }
                else if( temp[0].toLowerCase().equals("vertexup"))
                     {
                        System.out.println();
                        g.vertexUp(temp[1]);
                     }
                else if( temp[0].toLowerCase().equals("path"))
                    {
                       System.out.println();
                       g.dijkstras(temp[1],temp[2]);
                    }
                else if( temp[0].toLowerCase().equals("reachable"))
                    {
                        System.out.println();
                        g.Rechable();
                    }
                else if( temp[0].toLowerCase().equals("print"))
                    {
                        System.out.println();
                        g.print();
                    }

                else if( temp[0].toLowerCase().equals("quit"))
                    {
                      System.exit(1);
                     }

                    else
                        {
                          System.out.println();
                          System.out.println("Enter a valid command");
                          System.out.println();

                         }

                }//end of the while
  

            }//end of main function


    }//end of the class

