package cs21120.assignment2.solution;

import cs21120.assignment2.FloatImage;
import cs21120.assignment2.ISnapper;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * My implementation of the ISnapper interface
 *
 * Contains sub classes MyThread (extends Thread) and Edge (implements Comparable
 *
 */
public class Nid21Snapper implements ISnapper {

  private FloatImage[] weights;
  private Edge[][] edges;
  private boolean[][] visited;
  private int width, height;
  private int startX, startY;

  /**
   * setSeed will generate a graph, using the x and y params as the starting point,
   * and store the most efficient path for every node back to the starting path
   * @param x Starting X coordinate
   * @param y Starting Y coordinate
   * @param weights An array of FloatImage of directions {E, NE, N, EW}
   * @see FloatImage
   */
  @Override
  public void setSeed(int x, int y, FloatImage[] weights) {
    System.out.println("SetSeed (" + x + "," + y + ")");
    this.weights = weights;
    this.startX = x;
    this.startY = y;
    width = weights[0].getWidth();
    height = weights[0].getHeight();
    edges = new Edge[width][height];
    visited = new boolean[width][height]; //automatically initialised to false

    new MyThread().run();
  }

  /**
   * getPath will return a path from the given X and Y coordinates to the starting point specified with
   * setSeed
   * @param x Ending X coordinate
   * @param y Ending Y coordinate
   * @return A LinkedList of Points that represents the nodes traversed from start to finish
   */
  @Override
  public LinkedList<Point> getPath(int x, int y) {
    System.out.println("getPath (" + x + "," + y + ")");
    LinkedList<Point> points = new LinkedList<Point>();

    Edge e = edges[x][y];
    while(e != null && !(e.start.x == startX && e.start.y == startY)){
      //System.out.println(e);
      points.addFirst(e.end);
      e = edges[e.start.x][e.start.y];
    }

    //System.out.println("Edges list: " + points.size());

    return points;
  }

  /**
   * Innter Thread class for running Dijkstra's algorithm in the background so we dont block the UI thread
   */
  class MyThread extends Thread {

    /**
     * * Make a list of all of the things in the graph
     * * Write 0 next to the first thing
     * * Keep doing these steps:
     *    * Find the thing that you have not yet drawn a mark next to that has the smallest number written next to it
     *    * Draw a mark next to this thing
     *    * Do these steps for each other thing connected to this place:
     *       * Add the number written next to this thing and the distance of the connection together
     *       * If the connected thing does not have a number written next to it, write the new number and the name of this thing next to the connected thing
     *       * If the connected thing has a number written next to it and the new number is smaller than the written number:
     *          * Draw a line over what is already written
     *          * Write the new number and the name of this thing instead
     *    * Stop when you have drawn a mark next to every thing in the list
     * When you have done all of these steps you can use the list to find the shortest way from the first thing to any other thing. First write down the other thing. Then keep doing these steps:
     *
     * * Find the last thing you wrote down in the list
     * * Write down the thing written next to it
     * * Stop when you find the first thing
     *
     * http://simple.wikipedia.org/wiki/Dijkstra%27s_algorithm
     */
    @Override
    public void run() {
      super.run();

      Point startPoint = new Point(startX, startY);
      visited[startPoint.x][startPoint.y] = true;
      edges[startPoint.x][startPoint.y] = null;

      PriorityBlockingQueue<Edge> edgesPriorityQueue = new PriorityBlockingQueue<Edge>();

      Point endPoint;
      for(Direction d : Direction.values()){
        endPoint = d.adjust(startPoint);
        if(isValidPoint(endPoint)) {
          Edge e = new Edge(startPoint, endPoint, getWeight(weights, d, startPoint));
          edges[endPoint.x][endPoint.y] = e;
          edgesPriorityQueue.add(e);
        }
      }

      /*  At this point:
       *  +---------> +x
       *  |
       *  |  (x-1,y+1)  (x,y+1) (x+1,y+1)
       *  |           \   |   /
       *  |            V  v  V
       *  |  (x-1,y) -> (x,y) <- (x+1,y)
       *  |            ^  ^  ^
       *  |           /   |   \
       *  |  (x-1,y-1) (x,y-1) (x+1,y-1)
       *  V
       * +y
       **/

      //Iterate through the edges in the queue, in order of weight (least first)
      while(!edgesPriorityQueue.isEmpty()){
        Edge shortestEdge = edgesPriorityQueue.remove();

        //Mark the predecesor of this node as visited
        visited[shortestEdge.end.x][shortestEdge.end.y] = true;

        //Iterate through all the directions from this node
        for(Direction direction : Direction.values()){

          //Get the start point
          Point newStartPoint = shortestEdge.end;
          //Calculate the new end point of this node
          Point newEndPoint = direction.adjust(newStartPoint);
          //For sanity sake, this node is referred to in all arrays as the node's end point
          Point thisNode = newEndPoint;
          //The predecessor to this node (also Edge shortestEdge)
          Point predecessorNode = newStartPoint;

          //Check the node is within the image
          if(isValidPoint(thisNode)){

            //Check it hasnt been visited
            if(!visited[thisNode.x][thisNode.y]){
              //get the weight from the predecessor to this node
              double newWeight = getWeight(weights, direction, predecessorNode) + shortestEdge.getTentativeWeight();

              //Make a new edge to compare with
              Edge newEdge = new Edge(predecessorNode, thisNode, newWeight);

              //if the edge hasnt been visited, set the edge
              if(edges[thisNode.x][thisNode.y] == null){
                edges[thisNode.x][thisNode.y] = newEdge;
                edgesPriorityQueue.add(edges[thisNode.x][thisNode.y]);

              //Else if the new edge is less than the existing one
              } else if(edges[thisNode.x][thisNode.y].compareTo(newEdge) > 0) {
                edges[thisNode.x][thisNode.y] = newEdge;
                edgesPriorityQueue.add(edges[thisNode.x][thisNode.y]);
              }


            }
          }
        }
      }
    }
  }

  /**
   * Gets a weight in a direction from a point out of the array of FloatImages
   * @param weights FloatImage array of weights (from setSeed)
   * @param direction Direction enum
   * @param point Point to get direction from
   * @return Weight for traveling in the given direction from the given point
   */
  private double getWeight(FloatImage[] weights, Direction direction, Point point) {
    switch(direction){
      case NORTHWEST:
      case NORTH:
      case NORTHEAST:
      case EAST:
        return weights[direction.getIndex()].get(point.x, point.y);
      default:
        Point newPoint = direction.adjust(point);
        return weights[direction.getIndex()].get(newPoint.x, newPoint.y);
    }
  }

  private boolean isValidPoint(Point p){
    return (p.x >= 0 && p.x < width  &&
            p.y >= 0 && p.y < height &&
            p.x >= 0 && p.x < width  &&
            p.y >= 0 && p.y < height);
  }

  /**
   * Enum of directions containing an x,y point adjusters and FloatImage indexes
   */
  enum Direction {
        NORTH(new Point( 0,  1), 2),
    NORTHEAST(new Point( 1,  1), 1),
         EAST(new Point( 1,  0), 0),
    SOUTHEAST(new Point( 1, -1), 3),
        SOUTH(new Point( 0, -1), 2),
    SOUTHWEST(new Point(-1, -1), 1),
         WEST(new Point(-1,  0), 0),
    NORTHWEST(new Point(-1,  1), 3);

    private int index;
    private Point offset;

    Direction(Point p, int index) {
      this.index = index;
      offset = p;
    }

    public int getIndex() {
      return index;
    }

    public Point adjust(Point p){
      return new Point(p.x + this.offset.x, p.y + this.offset.y);
    }
  }

  /**
   * An edge between two points and an associated "tentative weight".
   * This class implements comparable and can be sorted by weight (lowest first)
   */
  class Edge implements Comparable<Edge> {

    Point start, end;
    double tentativeWeight;

    /**
     * Edge constructor for creating an edge between two points with a weight
     * @param start The start Point (working away from the origin or setSeed point)
     * @param end The end Point. Should be only one x or y difference to the start point
     * @param tentativeWeight the tentative weight for the edge
     */
    public Edge(Point start, Point end, double tentativeWeight){
      this.start = start;
      this.end = end;
      this.tentativeWeight = tentativeWeight;
    }

    /**
     * CompareTo method for comparable interface
     * @param edge The edge to be cmompared with
     * @return -1 if the provided edge is greater, 0 if equal and 1 if less than this edge
     */
    @Override
    public int compareTo(Edge edge) {
      if(edge.tentativeWeight > this.tentativeWeight){
        return -1;
      } else if(edge.tentativeWeight < this.tentativeWeight){
        return 1;
      } else return 0;
    }

    /**
     * Gets the set tentative weight
     * @return the tentative weight of the edge
     */
    public double getTentativeWeight() {
      return tentativeWeight;
    }

    @Override
    public String toString() {
      return "Node (" + start.x + "," + start.y + ") -> (" + end.x + "," + end.y + ") weight " + tentativeWeight + ((visited[startX][startY])?"":"Not ") + " visited";
    }
  }
}
