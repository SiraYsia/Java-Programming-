package graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * <P>
 * This class represents a general "directed graph", which could be used for any
 * purpose. The graph is viewed as a collection of vertices, which are sometimes
 * connected by weighted, directed edges.
 * </P>
 * 
 * <P>
 * This graph will never store duplicate vertices.
 * </P>
 * 
 * <P>
 * The weights will always be non-negative integers.
 * </P>
 * 
 * <P>
 * The WeightedGraph will be capable of performing three algorithms:
 * Depth-First-Search, Breadth-First-Search, and Djikatra's.
 * </P>
 * 
 * <P>
 * The Weighted Graph will maintain a collection of "GraphAlgorithmObservers",
 * which will be notified during the performance of the graph algorithms to
 * update the observers on how the algorithms are progressing.
 * </P>
 */
public class WeightedGraph<V> {
	protected Map<V, Map<V, Integer>> weightedGraph;

	/*
	 * STUDENTS: You decide what data structure(s) to use to implement this class.
	 * 
	 * You may use any data structures you like, and any Java collections that we
	 * learned about this semester. Remember that you are implementing a weighted,
	 * directed graph.
	 */

	/*
	 * Collection of observers. Be sure to initialize this list in the constructor.
	 * The method "addObserver" will be called to populate this collection. Your
	 * graph algorithms (DFS, BFS, and Dijkstra) will notify these observers to let
	 * them know how the algorithms are progressing.
	 */
	private Collection<GraphAlgorithmObserver<V>> observerList;

	/**
	 * Initialize the data structures to "empty", including the collection of
	 * GraphAlgorithmObservers (observerList).
	 */
	public WeightedGraph() {
		this.weightedGraph = new HashMap<>();
		this.observerList = new LinkedList<>();

	}

	/**
	 * Add a GraphAlgorithmObserver to the collection maintained by this graph
	 * (observerList).
	 * 
	 * @param observer
	 */
	public void addObserver(GraphAlgorithmObserver<V> observer) {
		this.observerList.add(observer);
	}

	/**
	 * Add a vertex to the graph. If the vertex is already in the graph, throw an
	 * IllegalArgumentException.
	 * 
	 * @param vertex vertex to be added to the graph
	 * @throws IllegalArgumentException if the vertex is already in the graph
	 */
	public void addVertex(V vertex) {
		if (containsVertex(vertex)) {
			throw new IllegalArgumentException();
		}
		weightedGraph.put(vertex, null);
	}

	/**
	 * Searches for a given vertex.
	 * 
	 * @param vertex the vertex we are looking for
	 * @return true if the vertex is in the graph, false otherwise.
	 */
	public boolean containsVertex(V vertex) {
		for (V verticies : weightedGraph.keySet()) {
			if (verticies.equals(vertex)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <P>
	 * Add an edge from one vertex of the graph to another, with the weight
	 * specified.
	 * </P>
	 * 
	 * <P>
	 * The two vertices must already be present in the graph.
	 * </P>
	 * 
	 * <P>
	 * This method throws an IllegalArgumentExeption in three cases:
	 * </P>
	 * <P>
	 * 1. The "from" vertex is not already in the graph.
	 * </P>
	 * <P>
	 * 2. The "to" vertex is not already in the graph.
	 * </P>
	 * <P>
	 * 3. The weight is less than 0.
	 * </P>
	 * 
	 * @param from   the vertex the edge leads from
	 * @param to     the vertex the edge leads to
	 * @param weight the (non-negative) weight of this edge
	 * @throws IllegalArgumentException when either vertex is not in the graph, or
	 *                                  the weight is negative.
	 */
	public void addEdge(V from, V to, Integer weight) {
		if (containsVertex(from) && containsVertex(to)) {
			Map<V, Integer> value = weightedGraph.get(from);
			if (value == null) {
				value = new HashMap<>();
			}
			value.put(to, weight);
			weightedGraph.put(from, value);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * <P>
	 * Returns weight of the edge connecting one vertex to another. Returns null if
	 * the edge does not exist.
	 * </P>
	 * 
	 * <P>
	 * Throws an IllegalArgumentException if either of the vertices specified are
	 * not in the graph.
	 * </P>
	 * 
	 * @param from vertex where edge begins
	 * @param to   vertex where edge terminates
	 * @return weight of the edge, or null if there is no edge connecting these
	 *         vertices
	 * @throws IllegalArgumentException if either of the vertices specified are not
	 *                                  in the graph.
	 */
	public Integer getWeight(V from, V to) {
		if (containsVertex(from) && containsVertex(to)) {
			if (weightedGraph.get(from) == null) {
				return null;
			}
			// if the succeeding vertex("to") is in the keySet of the preceding vertex's
			// corresponding map
			// return the weight else return null
			if (weightedGraph.get(from).keySet().contains(to)) {
				return weightedGraph.get(from).get(to);
			} else {
				return null;
			}
		}
		throw new IllegalArgumentException();

	}

	/**
	 * <P>
	 * This method will perform a Breadth-First-Search on the graph. The search will
	 * begin at the "start" vertex and conclude once the "end" vertex has been
	 * reached.
	 * </P>
	 * 
	 * <P>
	 * Before the search begins, this method will go through the collection of
	 * Observers, calling notifyBFSHasBegun on each one.
	 * </P>
	 * 
	 * <P>
	 * Just after a particular vertex is visited, this method will go through the
	 * collection of observers calling notifyVisit on each one (passing in the
	 * vertex being visited as the argument.)
	 * </P>
	 * 
	 * <P>
	 * After the "end" vertex has been visited, this method will go through the
	 * collection of observers calling notifySearchIsOver on each one, after which
	 * the method should terminate immediately, without processing further vertices.
	 * </P>
	 * 
	 * @param start vertex where search begins
	 * @param end   the algorithm terminates just after this vertex is visited
	 */
	public void DoBFS(V start, V end) {
		for (GraphAlgorithmObserver<V> observer : this.observerList) {
			observer.notifyBFSHasBegun();
		}
		Set<V> vistedSet = new HashSet<>();
		Queue<V> discoverd = new LinkedList<>();
		discoverd.add(start);
		// While the discovered queue is not empty
		while (!(discoverd.isEmpty())) {
			V tempVertex = discoverd.remove();// remove to process from discovered queue
			if (!(vistedSet.contains(tempVertex))) {// if tempVertex is not visited add it to the visted set
				vistedSet.add(tempVertex);

				for (GraphAlgorithmObserver<V> observer : this.observerList) {
					observer.notifyVisit(tempVertex);// before visiting notify each observer
				}
				// if the vertex is at the end, the job is done and its found
				if (tempVertex.equals(end)) {
					for (GraphAlgorithmObserver<V> observer : this.observerList) {
						observer.notifySearchIsOver();
					}
					return;
				}
				// if the vertex we are look for has neighbors and is not at the end add it to
				// the discovered queue
				if (this.weightedGraph.get(tempVertex) != null) {
					for (V succ : this.weightedGraph.get(tempVertex).keySet()) {
						if (!(vistedSet.contains(succ))) {
							discoverd.add(succ);

						}
					}
				}
			}
		}

	}

	/**
	 * <P>
	 * This method will perform a Depth-First-Search on the graph. The search will
	 * begin at the "start" vertex and conclude once the "end" vertex has been
	 * reached.
	 * </P>
	 * 
	 * <P>
	 * Before the search begins, this method will go through the collection of
	 * Observers, calling notifyDFSHasBegun on each one.
	 * </P>
	 * 
	 * <P>
	 * Just after a particular vertex is visited, this method will go through the
	 * collection of observers calling notifyVisit on each one (passing in the
	 * vertex being visited as the argument.)
	 * </P>
	 * 
	 * <P>
	 * After the "end" vertex has been visited, this method will go through the
	 * collection of observers calling notifySearchIsOver on each one, after which
	 * the method should terminate immediately, without visiting further vertices.
	 * </P>
	 * 
	 * @param start vertex where search begins
	 * @param end   the algorithm terminates just after this vertex is visited
	 */
	public void DoDFS(V start, V end) {
		for (GraphAlgorithmObserver<V> observer : this.observerList) {
			observer.notifyDFSHasBegun();
		}
		Set<V> vistedSet = new HashSet<>();
		Stack<V> discoverd = new Stack<>();
		discoverd.push(start);

		while (!(discoverd.isEmpty())) {
			V tempVertex = discoverd.pop();
			if (!(vistedSet.contains(tempVertex))) {
				vistedSet.add(tempVertex);

				for (GraphAlgorithmObserver<V> observer : this.observerList) {
					observer.notifyVisit(tempVertex);
				}
				if (tempVertex.equals(end)) {
					for (GraphAlgorithmObserver<V> obserber : this.observerList) {
						obserber.notifySearchIsOver();
					}
					return;
				}
				if (this.weightedGraph.get(tempVertex) != null) {
					for (V succ : this.weightedGraph.get(tempVertex).keySet()) {
						if (!(vistedSet.contains(succ))) {
							discoverd.push(succ);

						}
					}
				}
			}
		}

	}

	/**
	 * <P>
	 * Perform Dijkstra's algorithm, beginning at the "start" vertex.
	 * </P>
	 * 
	 * <P>
	 * The algorithm DOES NOT terminate when the "end" vertex is reached. It will
	 * continue until EVERY vertex in the graph has been added to the finished set.
	 * </P>
	 * 
	 * <P>
	 * Before the algorithm begins, this method goes through the collection of
	 * Observers, calling notifyDijkstraHasBegun on each Observer.
	 * </P>
	 * 
	 * <P>
	 * Each time a vertex is added to the "finished set", this method goes through
	 * the collection of Observers, calling notifyDijkstraVertexFinished on each one
	 * (passing the vertex that was just added to the finished set as the first
	 * argument, and the optimal "cost" of the path leading to that vertex as the
	 * second argument.)
	 * </P>
	 * 
	 * <P>
	 * After all of the vertices have been added to the finished set, the algorithm
	 * will calculate the "least cost" path of vertices leading from the starting
	 * vertex to the ending vertex. Next, it will go through the collection of
	 * observers, calling notifyDijkstraIsOver on each one, passing in as the
	 * argument the "lowest cost" sequence of vertices that leads from start to end
	 * (I.e. the first vertex in the list will be the "start" vertex, and the last
	 * vertex in the list will be the "end" vertex.)
	 * </P>
	 * 
	 * @param start vertex where algorithm will start
	 * @param end   special vertex used as the end of the path reported to observers
	 *              via the notifyDijkstraIsOver method.
	 */
	public void DoDijsktra(V start, V end) {
		for (GraphAlgorithmObserver<V> observer : this.observerList) {
			observer.notifyDijkstraHasBegun();
		}
		// keeps track the number of finished calculations for the smallest path
		int track = 0;
		Map<V, Integer> total = new HashMap<>();
		// collects the amount of visited vertices
		Set<V> visited = new HashSet<>();
		Map<V, V> pred = new HashMap<>();

		total.put(start, 0);
		pred.put(start, start);

		// Initialing lowest calculations to infinity and null for the predecessor
		for (V vertex : weightedGraph.keySet()) {
			if (!(vertex.equals(start))) {
				total.put(vertex, Integer.MAX_VALUE);
				pred.put(vertex, null);
			}
		}
		Integer totalMin = Integer.MAX_VALUE;
		// represents the not done being fully processed vertex
		V tempMin = start;

		while (track < weightedGraph.size()) {
			totalMin = Integer.MAX_VALUE;
			// a loop to find the smallest path calculation of a vertex that has not yet
			// been added to the
			// visited set
			for (V vertex : total.keySet()) {
				// if the calculation came out to be less than the the original do an update
				if (!(visited.contains(vertex)) && total.get(vertex) < totalMin) {

					tempMin = vertex;
					totalMin = total.get(vertex);
				}
			}
			visited.add(tempMin);
			track++;

			for (GraphAlgorithmObserver<V> observ : this.observerList) {
				observ.notifyDijkstraVertexFinished(tempMin, totalMin);
			}
			// goes through tempMin's predecessors
			if (weightedGraph.get(tempMin) != null) {
				for (V succ : weightedGraph.get(tempMin).keySet()) {
					if (!(visited.contains(succ))) {
						if (totalMin + weightedGraph.get(tempMin).get(succ) < total.get(succ)) {
							total.put(succ, totalMin + weightedGraph.get(tempMin).get(succ));
							pred.put(succ, tempMin);
						}
					}
				}
			}
		}
		// A linked list to give the shortest path from start to end
		LinkedList<V> shortestPath = new LinkedList<>();
		shortestPath.addFirst(end);

		V endR = pred.get(end);// endR stands for the predecessor of the end vertex

		/*
		 * while the endR is not at the start of the vertex add it to the head of the
		 * linked list for the shortestPath and update
		 */
		while (!(endR.equals(start))) {
			shortestPath.addFirst(endR);
			endR = pred.get(endR);

		}

		shortestPath.addFirst(start);// add it all the way in the beginning
		//notify each observer that the algorithm is over and give the shortest path
		for (GraphAlgorithmObserver<V> observer : this.observerList) {
			observer.notifyDijkstraIsOver(shortestPath);
		}

	}
}