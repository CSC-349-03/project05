/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 5
 **  03-14-2018
 */

import java.util.*;
import java.io.*;

public class DiGraphTest
{
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the number of vertices");
      int size = scanner.nextInt();
      String flush = scanner.nextLine();
      DiGraph graph = new DiGraph(size);  // Change?
      boolean running = true;
      System.out.println("Choose one of the following operations:");
      System.out.println("- add edge (enter a)");
      System.out.println("- delete edge (enter d) ");
      System.out.println("- edge count (enter e)");
      System.out.println("- vertex count (enter v)");
      System.out.println("- print graph (enter p)");
      System.out.println("- TopSort (enter t)");
      System.out.println("- Check if there is a path (enter i)");
      System.out.println("- Length of a path (enter l)");
      System.out.println("- Shortest path (enter s)");
      System.out.println("- Print breadth first tree (enter b)");
      System.out.println("- Quit (enter q)");
      int i = 0;
      int j = 0;
      while (running)
      {
         System.out.println("Choose an option");
         String letter = scanner.nextLine();
         if (letter.length() > 1)
         {
            System.out.println("Input must be only one letter");
         }
         else
         {
            switch (letter)
            {
               case "a":  // Add
                  System.out.println("Enter the indexes of the edge you wish to add");
                  i = scanner.nextInt();
                  j = scanner.nextInt();
                  graph.addEdge(i, j);  
                  flush = scanner.nextLine();
                  break;
               case "d":  // delete
                  System.out.println("Enter the indexes of the edge you wish to delete");
                  i = scanner.nextInt();
                  j = scanner.nextInt();
                  graph.deleteEdge(i, j); 
                  flush = scanner.nextLine();
                  break;
               case "v": // Vertex Count
                  System.out.println("There are " + graph.vertexCount() + " vertices");  
                  break;
               case "e":  // Edge count
                  System.out.println("There are " + graph.edgeCount() + " edges");  
                  break;
               case "p":  // Print
                  graph.print();  
                  break;
               case "t":  // topSort
                  try
                  {
                     int[] result = graph.topSort(); 
                     System.out.print(result[0]);
                     for (i = 1; i < result.length; i++)
                     {
                        System.out.print(", " + result[i]);
                     }
                     System.out.println();
                  }
                  catch (IllegalArgumentException e)
                  {
                     System.out.println("Graph is cyclic");
                  }
                  break;
               case "i": // Checks if there is a path 
                  System.out.println("Enter the start index and then the end index you wish to check");
                  i = scanner.nextInt();
                  j = scanner.nextInt();
                  if (graph.isTherePath(i, j)) 
                  {
                     System.out.println("There is a path from " + i + " to " + j);
                  } 
                  else
                  {
                     System.out.println("There is no path from " + i + " to " + j);
                  }
                  flush = scanner.nextLine();
                  break;
               case "l":  // Length of the shortest path
                  System.out.println("Enter the start index and then the end index you wish to check");
                  i = scanner.nextInt();
                  j = scanner.nextInt();
                  System.out.println("The length from " + i + " to " + j + " is " + graph.lengthOfPath(i, j));
                  flush = scanner.nextLine();
                  break;
               case "s":  // Print shortest path
                  System.out.println("Enter the start index and then the end index you wish to check");
                  i = scanner.nextInt();
                  j = scanner.nextInt();
                  graph.printPath(i, j);
                  flush = scanner.nextLine();
                  break;
               case "b":  // Print breadth first tree
                  System.out.println("Enter the starting index of the breadth first tree");
                  i = scanner.nextInt();
                  System.out.println("Breadth First Tree:");
                  graph.printTree(i);
                  flush = scanner.nextLine();
                  break;
               case "q":
                  System.out.println("Good bye.");
                  running = false;
                  break;
               default:
                  System.out.println("Invalid choice");
                  break;
            }
         }

      }
   }
}