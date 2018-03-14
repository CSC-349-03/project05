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
    	int length = graphAdjacencies.length;
        if (!(graphAdjacencies[from-1].contains(to-1)) && (graphAdjacencies[from-1].size() < length) && (to <= length))
        {
            graphAdjacencies[from-1].add(to-1);
            System.out.println("(" + from + ", " + to + ") edge is now added to the graph");
        }
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
                System.out.print((int)elements[0]+1);
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
        int[] indegrees = indegrees(); // NOT naturally indexed NOR numbered
        int[] results = new int[vertices]; // NOT naturally indexed, BUT naturally numbered
        LinkedList<Integer> queue = new LinkedList<Integer>(); // Contents ARE naturally numbered
        for(int i = 0; i < vertices; i++){
            if(indegrees[i] == 0){
                queue.add(i + 1);
            }
        }
        int resultIndex = 0;
        while(!queue.isEmpty()){
            if(resultIndex >= results.length + 1){ // Catch symptom of cyclic graph
                throw new IllegalArgumentException();
            }
            int vertex = queue.pop();
            results[resultIndex] = vertex;
            resultIndex++;
            Iterator<Integer> adjacencies = graphAdjacencies[vertex-1].iterator();
            while(adjacencies.hasNext()){
                int vertexToDecrement = adjacencies.next();
                indegrees[vertexToDecrement] -= 1;
                if(indegrees[vertexToDecrement] == 0){
                    queue.add(vertexToDecrement + 1);
                }
            }
        }
        if(resultIndex >= results.length + 1 || resultIndex == 0){ // Catch symptom of cyclic graph
            throw new IllegalArgumentException();
        }
        return results;
    }

    private VertexInfo[] BFS(int s) // Input naturally indexed
    {
    	VertexInfo[] bfs = new VertexInfo[graphAdjacencies.length];
    	int size = graphAdjacencies[s-1].size();
    	int level = 0;
    	bfs[s-1] = new VertexInfo(0, s); // Distance for self is 0
    	while (size > 0)
    	{
    		size = nextLevelBFS(level, bfs);
    		level++;
    	}
        return bfs;
    }

    // Searches the list for the inputs matching the level specified.  When it finds
    // one, it goes through all of its edges and checks if there's already a recorded path to it.
    // If not, it adds the VertexInfo. Returns the number of VertexInfos added
    private int nextLevelBFS(int level, VertexInfo[] bfs) // Input s naturally indexed
    {
    	int size = 0;
    	for (int i = 0; i < graphAdjacencies.length; i++)
    	{
    		if ((bfs[i] != null) && (bfs[i].distance == level)) // If it is the level we're looking for
    		{
    			Iterator<Integer> adjacencies = graphAdjacencies[i].iterator(); // NOT naturally indexed
    			while(adjacencies.hasNext())
        		{
            		int neighbor = adjacencies.next();  // NOT naturally indexed
            		if (bfs[neighbor] == null)
            		{
            			bfs[neighbor] = new VertexInfo(level+1, i+1);
            			size++;
            		}
        		}
    		}
    	}
    	return size;
    }

    // Return true if there is a path from "from" to "to"
    public boolean isTherePath(int from, int to) // Inputs naturally indexed
    {
    	VertexInfo[] bfs = BFS(from);
    	return (bfs[to-1] != null);
    }

    // Returns the shortest distance from "from" to "to", -1 if there is no path
    public int lengthOfPath(int from, int to) // Inputs naturally indexed
    {
    	VertexInfo[] bfs = BFS(from);
    	if (bfs[to-1] == null)
    	{
    		return -1;
    	}
    	else
    	{
    		return bfs[to-1].distance;
    	}
    }

    // Prints the path from "from" to "to" if "to" can be reached
    public void printPath(int from, int to) // Inputs naturally indexed
    {
    	VertexInfo[] bfs = BFS(from);
    	if (bfs[to-1] == null)
    	{
    		System.out.println("There is no path");
    	}
    	else
    	{
    		int current = to-1;  // NOT naturally indexed
    		LinkedList<Integer> queue = new LinkedList<Integer>(); // Contents NOT naturally numbered
    		while (current != (from-1)) // Add used paths in order, won't add the first
    		{
    			queue.addFirst(current);
    			current = bfs[current].parent - 1;
    		}
    		Iterator<Integer> adjacencies = queue.iterator();
    		System.out.print("From " + from);  // Print the start
            while(adjacencies.hasNext())
            {
                int next = adjacencies.next();
                System.out.print(" to " + (next+1));
            }
            System.out.println();
    	}
    }

    private TreeNode buildTree(int s) // Input is naturally indexed
    {
    	VertexInfo[] bfs = BFS(s);
    	TreeNode root = new TreeNode(s); // Treenodes will be naturally indexed
    }

    public void printTree(int s) // Input is naturally indexed
    {
    	TreeNode root = buildTree(s);
    }

    private class VertexInfo
    {
    	int distance;
    	int parent;
    	private VertexInfo(int dist, int prev)
    	{
    		distance = dist;
    		parent = prev;
    	}
    }

    private class TreeNode
    {
    	int vertex;
    	LinkedList<TreeNode> children;
    	private VertexInfo(int vert)
    	{
    		vertex = vert;
    		children = new LinkedList<TreeNode>();
    	}
    	private VertexInfo(int vert, LinkedList<TreeNode> kids)
    	{
    		vertex = vert;
    		children = kids;
    	}
    }
}
