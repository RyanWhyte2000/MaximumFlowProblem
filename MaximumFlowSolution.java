package MaxFlow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumFlowSolution {

	static class Graph {
        int vertices;
        int PassedGraph[][];

        public Graph(int vertices, int[][] PassedGraph) {
            this.vertices = vertices;
            this.PassedGraph = PassedGraph; 
        }
 
     // Returns max flow from Source to Sink in a graph
        public int MaxFlow(int source, int sink) { 
            
            int RGraph[][] = new int[vertices][vertices];

            //The Residual graph is initialized to the adjacency graph
            for (int i = 0; i <vertices ; i++) {
                for (int j = 0; j <vertices ; j++) { 
                    RGraph[i][j] = PassedGraph[i][j];
                } 
            } 

            //The parent array is used to store the path from Source to sink
            int [] parent = new int[vertices]; //holds parent of a vertex when a path if found (filled by BFS)

            int max_flow = 0; 

            while(BFS(source, sink, parent, RGraph)){
            	 
            	//This section finds the bottleneck by looping over path from BFS using parent[] array
                int bottleneckFlow = Integer.MAX_VALUE; // the bottleneckFlow is set to the highest so that when the BFS loops the value reduces 

                int end = sink;
                while(end!=source){
                    int start = parent[end];
                    bottleneckFlow = Math.min(bottleneckFlow, RGraph[start][end]);// This finds the minimum of previous bottleneck and the new capacity of the edge
                    end = start;
                    

                } 
 
                end = sink;
                while(end!=source){ 
                    int start= parent[end]; 
                    RGraph[start][end]-=bottleneckFlow;//the capacity on back edge is added by bottleneck flow
                    RGraph[end][start]+=bottleneckFlow;//the capacity on forward edge is reduced by bottleneck flow
                	   

                    end= start;
                }
            	
                //add bottleneckFlow to max value
                max_flow+=bottleneckFlow;
            }
            return max_flow;
            
            
        }
     
 public boolean BFS( int S, int T, int  parent[], int  RGraph[][]){
       
            
            boolean  visited[] = new boolean[vertices];// checks if all vertices have been visited when the path has being found
      
            Queue<Integer> BFSlog = new LinkedList<>(); //a queue is created for BFS using FIFO
          
            BFSlog.add(S); //enqueue source vertex
            
            parent[S] = -1; // the source has no parent which is why it is set to -1
            visited[S] = true;// marking source vertex if it was actually visited

            while(BFSlog.isEmpty()==false){
                int u = BFSlog.poll(); //dequeue a vertex from the queue using FIFO

                // This section checks the edges by checking the values in the row of the matrix
                for (int v = 0; v <vertices ; v++) {  
                    if(visited[v]==false && RGraph[u][v]>0) {// checks if vertex was visited and the positions in the residual graph is greater than 0 then an edge is present
                       
                    	BFSlog.add(v);//enqueue vertex
                        
                    	parent[v] = u; 
                        visited[v] = true; 
                         
                      } 
                }
                
            }
            /*for(int i = 0; i < BFSlog.size(); i++)  
                System.out.println( ((LinkedList<Integer>) BFSlog).get(i) );**/
            
            
            return visited[T]; //return true or false if a path is found to the sink
        }
    } 

   
}
