package criticalPath;

import java.util.*;


public class Graph {

	public class node {
		String name;
		int timeRequire;
		int ef;
		int es;
		int ls;
		int lf;
		Vector<Integer> successors;
		Vector<Integer> predecessors;
		
		node() {
			name ="";
			timeRequire = 0;
			ef =0;
			es =0;
			ls =0;
			lf =0;
		}
		
		node (String name, int timeRequire) {
			this.name = name;
			this.timeRequire = timeRequire;
			ef =0;
			es =0;
			ls =0;
			lf =0;
		}
	}
	
	private int totalVertex;
	private int totalEdge;
	private Vector<node> vertices;
	private int[][] adj;
	
	Graph (int totalV) {
		totalVertex = totalV;
		totalEdge = 0;
		adj = new int[totalV][totalV];
		for (int i=0; i< totalV; i++)
			for (int j=0; j< totalV; j++)
				adj[i][j] = 0;
	}
	
	public void addEdge(int source, int destination, int weight) {
		adj[source][destination] = weight;
		//adj[destination][source] = weight;
		totalEdge++;
	}
	
	public int vertex () {
		return totalVertex;
	}
	
	public int edge () {
		return totalEdge;
	}

	public Queue topologicalSort () {
		Queue<node> result = new LinkedList<>();

		boolean visited[] = new boolean[totalVertex]; 
		for (int i = 0; i < totalVertex; i++) 
			visited[i] = false;

		for (int i=0; i < totalVertex; i++)
			if ( visited[i] == false)
				topologicalSort(i, result, visited);
		
		
		return result;
	}

	private void topologicalSort (int v, Queue queue, boolean[] visited) {
		for (int i = 0; i < vertices.get(v).predecessors.size(); i++){
			if(visited[i] == false)
				return;
		}
		
		visited[v] = true;
		queue.add(vertices.get(v));
		for(int i=0; i < totalVertex; i++) {
			topologicalSort(i, queue, visited);
		}
		
	}

}
