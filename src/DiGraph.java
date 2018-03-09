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
        if (!(graphAdjacencies[from-1]).contains(to-1))
        {
            graphAdjacencies[from-1].add(to-1);
            System.out.println("(" + from + ", " + to + ") edge is now added to the graph");
        }
        // TODO: Add edge if not found
    }

    public void deleteEdge(int from, int to){
        graphAdjacencies[from-1].removeFirstOccurrence(to-1);
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
        return graphAdjacencies.length;
    }

    public void print(){
        for (int i = 0; i < graphAdjacencies.length; i++)
        {
            System.out.print((i+1) + " is connected to: ");
            Object[] elements = graphAdjacencies[i].toArray();
            if (elements.length != 0)
            {
                System.out.print(elements[0]);
            }
            for (int j = 1; j < elements.length; j++)
            {
                System.out.print(", " + ((int)elements[j]+1));
            }
            System.out.println();
        }
    }

    private int[] indegrees(){
        int[] indegrees = new int[graphAdjacencies.length];
        for(int i = 0; i < graphAdjacencies.length; i++){ // Look at the adjacency list for each node
            Iterator listSearch = graphAdjacencies[i].iterator(); // Iterate through each adjacency list
            while(listSearch.hasNext()){ // If the adjacency list mentions a node, increment that node's indegree
                Integer next = (Integer)listSearch.next();
                indegrees[next]++;
            }
        }
        return indegrees;
    }

    public int[] topSort() throws IllegalArgumentException{
        int vertices = graphAdjacencies.length;
        int[] indegrees = indegrees();
        int[] results = new int[vertices];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < vertices; i++){
            if(indegrees[i] == 0){
                queue.add(i + 1); // Natural numbering of vertices
            }
        }
        int resultIndex = 0;
        while(!queue.isEmpty()){
            if(resultIndex >= results.length){ // Catch symptom of cyclic graph
                throw new IllegalArgumentException();
            }
            int vertex = queue.pop();
            results[resultIndex] = vertex;
            resultIndex++;
            Iterator<Integer> adjacencies = graphAdjacencies[vertex-1].iterator();
            while(adjacencies.hasNext()){
                int decrementingVertex = adjacencies.next();
                indegrees[decrementingVertex] -= 1;
                if(indegrees[decrementingVertex] == 0){
                    queue.add(decrementingVertex);
                }
            }
        }
        return results;
    }
}
