package csp.aus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import csp.base.CSP;
import csp.base.NamedVariable;
import csp.base.NotEqualConstraint;
import csp.core.Assignment;
import csp.core.Solver;
import csp.core.Variable;
import csp.solver.BacktrackingSearchSolver;
import csp.util.ArrayListSet;

/**
 * Constraint satisfaction problem for coloring the map of Australia,
 * from AIMA Section 6.1 and Fig 6.1.
 */
public class AustraliaMapCSP extends CSP {
	public static boolean adjMatrix[][];
	public static int[] assignment = new int[] {0,0,0,0,0,0,0};
	
	/**
	 * Construct and return a new instance of the CSP for coloring the map of
	 * Australia.
	 */
//	public AustraliaMapCSP() {
//			//initialize the array for storing the result
//			//Build Adjacency List	
//			Graph g = new Graph(7);
//			g.addEdge(0,1);
//			g.addEdge(0,5);
//			g.addEdge(1,2);
//			g.addEdge(1,5);
//			g.addEdge(2,5);
//			g.addEdge(2,3);
//			g.addEdge(3,5);
//			g.addEdge(3,4);
//			g.addEdge(4,5);
//			
//			
//			solve(0,7,assignment,adjMatrix);
//
//	}
	//This is Backtracking method
	static void solve(int k,int size,int[] assignment,boolean[][] adjMatrix) {
		//K is the index in the assignment
		//c is color assignment red/green/blue(1,2,3)
		for(int c = 1; c<=3; c++) {
			if(isSafe(k,c,assignment,adjMatrix)) {
				assignment[k]=c;
				
				if(k+1<7)
					solve(k+1,size,assignment,adjMatrix);
				else
					for (int element: assignment) {
						if (element == 1)
			            System.out.print("R"+"  ");
						else if (element == 2)
					    System.out.print("G"+"  ");
						else if (element == 3)
						System.out.print("B"+"  ");
			        }
				return;
			}
		}
	}
	//This is AC-3, the method to validate the constraint
	static boolean isSafe(int k, int c,int[]  assignment,boolean[][] adjMatrix) {
		for(int i = 0; i <7; i++) {
			if(adjMatrix[k][i]==true&&c==assignment[i]) 
				return false;
		}
		return true;
		
	}

	static class Graph {
		public int numVertices;
    
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
  }

    public void addEdge(int i, int j) {
              adjMatrix[i][j] = true;
              adjMatrix[j][i] = true;
  }

    public void removeEdge(int i, int j) {
              adjMatrix[i][j] = false;
              adjMatrix[j][i] = false;
  }

    public boolean isEdge(int i, int j) {
                return adjMatrix[i][j];
  }
  public String toString() {
      StringBuilder s = new StringBuilder();
      for (int i = 0; i < numVertices; i++) {
          s.append(i + ": ");
          for (boolean j : adjMatrix[i]) {
              s.append((j?1:0) + " ");
          }
          s.append("\n");
      }
      return s.toString();
    } 
  }

	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(0,1);
		g.addEdge(0,5);
		g.addEdge(1,2);
		g.addEdge(1,5);
		g.addEdge(2,5);
		g.addEdge(2,3);
		g.addEdge(3,5);
		g.addEdge(3,4);
		g.addEdge(4,5);
		
		System.out.println("Australia Map Coloring Problem (AIMA 6.1.1)");
		System.out.print("WA "+"NT "+"Q  "+"NW "+"V  "+"SA "+"T  \n");
		for (int element: assignment) {
            System.out.print("NA"+" ");
        }
//		CSP csp = new AustraliaMapCSP();
//		System.out.println(map);
//		
		System.out.println("\nBacktracking search solver:");
		System.out.print("WA "+"NT "+"Q  "+"NW "+"V  "+"SA "+"T  \n");
		solve(0,7,assignment,adjMatrix);
//		Solver solver = new BacktrackingSearchSolver();
//		long start = new Date().getTime();
//		Assignment result = solver.solve(csp);
//		long end = new Date().getTime();
//		
//		System.out.format("time: %.3f secs\n", (end-start)/1000.0);
//		System.out.println("result=" + result);
	}
}

