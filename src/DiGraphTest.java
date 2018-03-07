/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 5
 **  03-09-2018
 */

import java.util.*;

public class DiGraphTest
{
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the number of vertices");
      int size = scanner.nextInt();
      DiGraph graph = new DiGraph(size);  // Change?
      boolean running = true;
      System.out.println("Choose one of the following operations:");
      System.out.println("- add edge (enter a)");
      System.out.println("- delete edge (enter d) ");
      System.out.println("- edge count (enter e)");
      System.out.println("- vertex count (enter v)");
      System.out.println("- print graph (enter p)");
      System.out.println("- Quit (enter q)");
      int i = 0;
      int j = 0;
      while (running)
      {
         System.out.println("Choose an option");
         String choice = scanner.next();
         String letter = choice.substring(0,1);
         switch (letter)
         {
            case "a":  // Add
               System.out.println("Enter the indexes of the edge you wish to add");
               i = scanner.nextInt();
               j = scanner.nextInt();
               graph.addEdge(i, j);  // Change?
               break;
            case "d":  // delete
               System.out.println("Enter the indexes of the edge you wish to delete");
               i = scanner.nextInt();
               j = scanner.nextInt();
               graph.deleteEdge(i, j);  // Change?
               break;
            case "v": // Vertex Count
               System.out.println("There are " + graph.vertexCount() + " vertices");  // Change?
               break;
            case "e":  // Edge count
               System.out.println("There are " + graph.edgeCount() + " edges");  // Change?
               break;
            case "p":  // Print
               graph.print();  // Change?
               break;
            case "q":
               System.out.println("Quitting");
               running = false;
               break;
            default:
               System.out.println("Invalid choice");
               break;
         }
      }
   }
}