package cs21120.assignment2.solution;

import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;
import java.awt.Point;

import com.sun.istack.internal.NotNull;
import cs21120.assignment2.*;


/**
 * The Acb12Snapper class implements from the Isnapper interface. It uses Dijkstra's
 * algorithm to determine the shortest path using pixel as nodes and the weight is
 * the difference in colour.
 * Resources used to help, code samples - graph, from blackboard.
 *
 * @author  Xander Barnes
 * @version 1.0
 * @since   2015-03-28
 */
public class Acb12Snapper implements ISnapper, Runnable {

  /** The empty map */
  private Point[][] map;
  /** The priority queue for the edges */
  private PriorityBlockingQueue<Edge> pq;
  /** The array for checking if the pixel had been used yet */
  private boolean[][] visited;
  /** The empty placeholder for the 4 images */
  private FloatImage[] edges;

  /**
   * Constructor for Acb12Snapper
   */
  public Acb12Snapper() {

  }

  /**
   * Method for setting the seed point and then calls a separate thread to create the
   * map of the shortest path. this method is called when the mouse down event is
   * called.
   *
   * @param x is the seeds x-axis value
   * @param y is the seeds y-axis value
   * @param edges2 is an array of FloatImage which contains 4 images for the weight
   * of the different directions
   */
  @Override
  public void setSeed(int x, int y, FloatImage[] edges2) {
    edges = edges2;
    map = new Point[edges[0].getWidth() ][edges[0].getHeight()];
    pq = new PriorityBlockingQueue<Edge>();
    visited = new boolean[edges[0].getWidth()][edges[0].getHeight() ];

    int[] xdiff = { 1, 1, 0, -1, -1, -1, 0, 1 };

    int[] ydiff = { 0, 1, 1, 1, 0, -1, -1, -1 };

    int[] wdiff = { 0, 1, 2, 3, 0, 1, 2, 3 };

    int[] xwdiff = { 0, 0, 0, 0, -1, -1, 0, 1 };

    int[] ywdiff = { 0, 0, 0, 0, 0, -1, -1, -1 };

    for (int k = 0; k < 8; k++) {

      int i = x + xdiff[k];

      int j = y + ydiff[k];

      if (0 <= i && i <= edges[0].getWidth() - 1) {
        if (0 <= j && j <= edges[0].getHeight() - 1) {

          Edge e = new Edge(x, y, i, j, edges[wdiff[k]].get_nocheck(x
                  + xwdiff[k], y + ywdiff[k]));
          pq.add(e);
        }
      }

    }

    visited[x][y] = true;

    //start thread//////
    Thread t = new Thread(this, "thread");
    t.start();

  }

  /**
   * Method for creating a linked list of the shortest path between two nodes using
   * the map created by the separate thread in setSeed(). This method is called when
   * the mouse is dragged.
   *
   * @param x is the mouse x-axis value
   * @param y the mouse y-axis value
   * @return LinkedList<> of points
   */
  @Override
  public LinkedList<Point> getPath(int x, int y) {

    LinkedList<Point> path = new LinkedList<Point>();
    if (0 <= x && x <= edges[0].getWidth() - 1) {
      if (0 <= y && y <= edges[0].getHeight() - 1) {
        Point p = new Point(x, y);
        while (p != null) {
          path.addFirst(p);
          p = map[p.x][p.y];
        }
      }}
    return path;

  }

  /**
   * Edge Class is used to represent a node in the priority queue. Implements
   * comparable so they can be compared in the priority queue.
   */
  public class Edge implements Comparable<Object> {
    /** The start point of the edge */
    public Point start;
    /** The end point of the edge */
    public Point end;
    /** The weight of the edge */
    public float weight;

    /**
     * Constructor for an edge
     *
     * @param startx the x value of the start node
     * @param starty the y value of the start node
     * @param endx the x value of the end node
     * @param endy the y value of the end node
     * @param weight the weight of the edge
     */
    public Edge(int startx, int starty, int endx, int endy, float weight) {
      this.start = new Point(startx, starty);
      this.end = new Point(endx, endy);
      this.weight = weight;
    }

    /**
     * Method for comparing 2 edges, for the priority Queue
     *
     * @param o the edge that gets compared against
     * @return int to work out smaller or larger
     */
    @Override @NotNull
    public int compareTo(Object o) {
      Edge e = (Edge) o;
      if (e.weight > weight)
        return -1;
      else if (e.weight < weight)
        return 1;
      return 0;
    }

  }

  /**
   * Method for overiding the run method in thread called by thread.start()
   *
   *
   */
  @Override
  public void run() {
    while (!pq.isEmpty()) {
      Edge e = pq.remove();
      if (0 < e.end.x && e.end.x < edges[0].getWidth()-1 ) {
        if (0 < e.end.y && e.end.y < edges[0].getHeight()-1 ) {
          if (!visited[e.end.x][e.end.y]) {
            visited[e.end.x][e.end.y] = true;
            map[e.end.x][e.end.y] = e.start;

            int[] xdiff = { 1, 1, 0, -1, -1, -1, 0, 1 };

            int[] ydiff = { 0, 1, 1, 1, 0, -1, -1, -1 };

            int[] wdiff = { 0, 1, 2, 3, 0, 1, 2, 3 };

            int[] xwdiff = { 0, 0, 0, 0, -1, -1, 0, 1 };

            int[] ywdiff = { 0, 0, 0, 0, 0, -1, -1, -1 };

            for (int k = 0; k < 8; k++) {

              int i = e.end.x + xdiff[k];

              int j = e.end.y + ydiff[k];

              if (0 <= i && i <= edges[0].getWidth() - 1) {
                if (0 <= j && j <= edges[0].getHeight() - 1) {

                  Edge e2 = new Edge(e.end.x, e.end.y, i, j,
                          edges[wdiff[k]].get_nocheck(e.end.x + xwdiff[k], e.end.y + ywdiff[k])+e.weight);



                  pq.add(e2);
                }
              }}
          }

        }

      }

    }



  }
}