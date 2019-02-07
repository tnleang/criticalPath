package criticalPath;

import java.util.*;

import criticalPath.Graph.node;

public class main {
	
	public static void main(String[] args) {
		
		//test data
		
		Graph g= testData();
		
		
		
		

	}
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

	public class Graph {
		
		private int totalVertex;
		private int totalEdge;
		private Vector<node> vertices;
		private boolean[][] adj;
		
		Graph (int totalV, Vector<node> v ) {
			totalVertex = totalV;
			totalEdge = 0;
			vertices = v;
			adj = new boolean[totalV][totalV];
			for (int i=0; i< totalV; i++)
				for (int j=0; j< totalV; j++)
					adj[i][j] = false;
		}
		
		public void addEdge(int source, int destination) {
			adj[source][destination] = true;
			adj[destination][source] = true;
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
			
			System.out.print("Before: ");
			for (int i=0; i < vertices.size(); i++)
				System.out.print(vertices.get(i).name);
			System.out.println("");

			boolean visited[] = new boolean[totalVertex]; 
			for (int i = 0; i < totalVertex; i++) 
				visited[i] = false;

			for (int i=0; i < totalVertex; i++)
				if ( visited[i] == false)
					topologicalSort(i, result, visited);
			
			System.out.print("After: ");
			while (!result.isEmpty()) {
				System.out.print(result.peek().name + " ");
				result.remove();
			}
			return result;
		}

		private void topologicalSort (int v, Queue queue, boolean[] visited) {
			for (int i = 0; i < vertices.get(v).predecessors.size(); i++){
				if(visited[vertices.get(v).predecessors.get(i)] == false)
					return;
			}
			
			visited[v] = true;
			queue.add(vertices.get(v));
			for(int i=0; i < totalVertex; i++) {
				topologicalSort(i, queue, visited);
			}
			
		}
	}
	
	public static Graph testData () {
		Vector<node> test = new Vector<node>();
		
		node node0 = new node("A", 6);
		node0.successors.add(2);
		node0.successors.add(3);
		node0.successors.add(4);
		test.add(node0);
		
		node node1 = new node("B", 2);
		node1.successors.add(7);
		test.add(node1);
		
		node node2 = new node("C", 3);
		node2.successors.add(5);
		node2.predecessors.add(0);
		test.add(node2);
		
		node node3 = new node("D", 5);
		node3.successors.add(6);
		node3.predecessors.add(0);
		test.add(node3);
		
		node node4 = new node("E", 3);
		node4.successors.add(7);
		node4.predecessors.add(0);
		test.add(node4);
		
		node node5 = new node("F", 2);
		node5.successors.add(9);
		node5.predecessors.add(2);
		test.add(node5);
		
		node node6 = new node("G", 3);
		node6.successors.add(9);
		node6.predecessors.add(3);
		test.add(node6);
		
		node node7 = new node("H", 4);
		node7.successors.add(8);
		node7.predecessors.add(1);
		node7.predecessors.add(4);
		test.add(node7);
		
		node node8 = new node("I", 2);
		node8.successors.add(9);
		node8.predecessors.add(7);
		test.add(node8);
		
		node node9 = new node("J", 2);
		node9.predecessors.add(5);
		node9.predecessors.add(6);
		node9.predecessors.add(8);
		test.add(node9);
		
		Graph g= new Graph(10, test);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 7);
		g.addEdge(2, 5);
		g.addEdge(3, 6);
		g.addEdge(4, 7);
		g.addEdge(5, 9);
		g.addEdge(6, 9);
		g.addEdge(7, 8);
		g.addEdge(8, 9);
		
		return g;
	}
	
}
