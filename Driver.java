
package MaxFlow;
import java.awt.*;
import java.util.Scanner;

import MaxFlow.MaximumFlowSolution.Graph;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	 Scanner input = new Scanner(System.in);	
		
         		 
      
        System.out.println("Enter the amount vertices : " );
       int vertices = input.nextInt();
       int sink= vertices-1;
       int source= 0;
         
	    int[][] gMatrix= new int[vertices][vertices]; 
        
        for(int i=0; i<vertices; i++) {       	
        	for(int j=0; j<vertices; j++) {
                gMatrix[i][j]= 0;
        	} 
        }
        
       
          int ans =0; 
        	while(ans != 9999 ) {
        		System.out.println("Enter the edge capacities from the direction  ");
        		System.out.println("From : "); 
        		int from= input.nextInt();
        		System.out.println("To :"); 
        		int to= input.nextInt();
        		 
        		System.out.println(" Edge capacity ");
 
        		ans = input.nextInt();
        		gMatrix[from-1][to-1]= ans;
        		System.out.println("Do you want to continue 1 for yes or 2 for no ");
        		int choice = input.nextInt();
        		
        		if(choice== 2) {
        			break;
        		}
        		

        	
        }
	
		        Graph g = new Graph(vertices, gMatrix);
		
		        int max_flow = g.MaxFlow(source, sink);
		      System.out.println("Maximum flow : " + max_flow);


		    }

	

}
