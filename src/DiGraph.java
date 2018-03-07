/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 5
 **  03-09-2018
 */

import java.util.LinkedList;

public class DiGraph {
    LinkedList<Integer>[] graphAdjacencies;

    public DiGraph(int N){
        graphAdjacencies = new LinkedList[N];
    }

    public void addEdge(int from, int to){
        for(Integer entry : graphAdjacencies){ // Don't add if edge already connected
            if(entry.equals(to)){
                return;
            }
        }
        // TODO: Add edge if not found
    }

    public void deleteEdge(int from, int to){
        // TODO
    }

    public int edgeCount(){
        // TODO
        return -1;
    }

    public int vertexCount(){
        // TODO
        return -1;
    }

    public void print(){
        // TODO
    }
}
