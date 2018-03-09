/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 5
 **  03-09-2018
 */

import java.io.*;
import java.util.*;

public class DiGraph {
    private LinkedList<Integer>[] graphAdjacencies;

    public DiGraph(int N){
        graphAdjacencies = new LinkedList[N];
        for(int i = 0; i < N; i++){
            graphAdjacencies[i] = new LinkedList<Integer>();
        }
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
                System.out.print(elements[0]);
            }
            for (int j = 1; j < elements.length; j++)
            {
                System.out.print(", " + elements[j]);
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

    public int[] topSort() throws IllegalArgumentException{
        int vertices = graphAdjacencies.length;
        int[] indegrees = indegrees();
        int[] results = new int[vertices + 1]; // THIS IS INDEXED FROM 1 AS PER SPECIFICATIONS!
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < vertices; i++){
            if(indegrees[i] == 0){
                queue.add(i);
            }
        }
        int resultIndex = 1; // THIS IS INDEXED FROM 1 AS PER SPECIFICATIONS!
        while(!queue.isEmpty()){
            int vertex = queue.pop();
            results[resultIndex] = vertex;
            resultIndex++;
            Iterator<Integer> adjacencies = graphAdjacencies[resultIndex - 1].iterator();
            while(adjacencies.hasNext()){
                int decrementingVertex = adjacencies.next();
                indegrees[resultIndex - 1] -= 1;
                if(indegrees[resultIndex - 1] == 0){
                    queue.add(decrementingVertex);
                }
            }
        }
        if(results.length == vertices + 1){
            throw new IllegalArgumentException();
        }
        return results;
    }
}
