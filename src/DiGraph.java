/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 5
 **  03-09-2018
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;
import java.util.*;

public class DiGraph {
    LinkedList<Integer>[] graphAdjacencies;

    public DiGraph(int N){
        graphAdjacencies = new LinkedList[N];
    }

    public void addEdge(int from, int to){
        /*for(Integer entry : graphAdjacencies){ // Don't add if edge already connected
            if(entry.equals(to)){
                return;
            }
        }*/
        if (!(graphAdjacencies[from-1]).contains(to))
        {
            graphAdjacencies[from-1].add(to);
        }
        // TODO: Add edge if not found
    }

    public void deleteEdge(int from, int to){
        graphAdjacencies[from-1].removeFirstOccurrence(to);
    }

    public int edgeCount(){
        int sum = 0;
        for (int i = 0; i < graphAdjacencies.length; i++)
        {
            sum += graphAdjacencies[i].size();
        }
        return sum;
    }

    public int vertexCount(){
        // TODO
        return graphAdjacencies.length;
    }

    public void print(){
        for (int i = 0; i < graphAdjacencies.length; i++)
        {
            System.out.print((i+1) + "is connected to: ");
            Object[] elements = graphAdjacencies[i].toArray();
            if (elements.length != 0)
            {
                System.out.print(elements[1]);
            }
            for (int j = 1; j < elements.length; j++)
            {
                System.out.print(" , " + elements[j]);
            }
        }
    }

    private int[] indegrees(){
        int[] indegrees = new int[graphAdjacencies.length];
        for(int i = 0; i < graphAdjacencies.length; i++){ // Look at the adjacency list for each node
            Iterator listSearch = graphAdjacencies[i].iterator(); // Iterate through each adjacency list
            while(listSearch.hasNext()){ // If the adjacency list mentions a node, that node's indegree is +1
                Integer next = (Integer)listSearch.next();
                indegrees[next]++;
            }
        }
        return indegrees;
    }
}
