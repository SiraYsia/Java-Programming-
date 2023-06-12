package graph;

import java.util.Set;

import maze.Juncture;
import maze.Maze;

/**
 * <P>
 * The MazeGraph is an extension of WeightedGraph. The constructor converts a
 * Maze into a graph.
 * </P>
 */
public class MazeGraph extends WeightedGraph<Juncture> {

	/*
	 * STUDENTS: SEE THE PROJECT DESCRIPTION FOR A MUCH MORE DETAILED EXPLANATION
	 * ABOUT HOW TO WRITE THIS CONSTRUCTOR
	 */

	/**
	 * <P>
	 * Construct the MazeGraph using the "maze" contained in the parameter to
	 * specify the vertices (Junctures) and weighted edges.
	 * </P>
	 * 
	 * <P>
	 * The Maze is a rectangular grid of "junctures", each defined by its X and Y
	 * coordinates, using the usual convention of (0, 0) being the upper left
	 * corner.
	 * </P>
	 * 
	 * <P>
	 * Each juncture in the maze should be added as a vertex to this graph.
	 * </P>
	 * 
	 * <P>
	 * For every pair of adjacent junctures (A and B) which are not blocked by a
	 * wall, two edges should be added: One from A to B, and another from B to A.
	 * The weight to be used for these edges is provided by the Maze. (The Maze
	 * methods getMazeWidth and getMazeHeight can be used to determine the number of
	 * Junctures in the maze. The Maze methods called "isWallAbove",
	 * "isWallToRight", etc. can be used to detect whether or not there is a wall
	 * between any two adjacent junctures. The Maze methods called "getWeightAbove",
	 * "getWeightToRight", etc. should be used to obtain the weights.)
	 * </P>
	 * 
	 * @param maze to be used as the source of information for adding vertices and
	 *             edges to this MazeGraph.
	 */
	public MazeGraph(Maze maze) {

		for (int row = 0; row < maze.getMazeHeight(); row++) {
			for (int col = 0; col < maze.getMazeWidth(); col++) {
				addVertex(new Juncture(col, row));
			}
		}

		Set<Juncture> junctures = weightedGraph.keySet();

		for (Juncture j : junctures) {
			// if the above of the current juncture is not a wall, add an edge leading
			// from the current juncture of the juncture above
			if (j.getY() > 0 && !(maze.isWallAbove(j))) {
				addEdge(j, new Juncture(j.getX(), j.getY() - 1), maze.getWeightAbove(j));
			}
			// if the below of the current juncture is not a wall, add an edge leading
			// from the current juncture to the juncture of the below

			if (j.getY() <= maze.getMazeHeight() && !(maze.isWallBelow(j))) {
				addEdge(j, new Juncture(j.getX(), j.getY() + 1), maze.getWeightBelow(j));
			}
			// if the left of the current juncture is not a wall, add an edge leading
			// from the current juncture to the juncture of the left
			if (j.getX() > 0 && !(maze.isWallToLeft(j))) {
				addEdge(j, new Juncture(j.getX() - 1, j.getY()), maze.getWeightToLeft(j));
			}
			// if the right of the current juncture is not a wall, add an edge leading
			// from the current juncture to the juncture of the right
			if (j.getX() <= maze.getMazeWidth() && !(maze.isWallToRight(j))) {
				addEdge(j, new Juncture(j.getX() + 1, j.getY()), maze.getWeightToRight(j));
			}
		}
	}
}