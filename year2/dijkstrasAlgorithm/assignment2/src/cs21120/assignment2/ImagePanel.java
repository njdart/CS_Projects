//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cs21120.assignment2;

import cs21120.assignment2.FloatImage;
import cs21120.assignment2.ISnapper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener, Scrollable {
  BufferedImage img;
  BufferedImage display;
  LinkedList<Point> line;
  ArrayList<LinkedList<Point>> lines = new ArrayList();
  FloatImage fimg = new FloatImage();
  FloatImage dx = new FloatImage();
  FloatImage dy = new FloatImage();
  FloatImage dxy = new FloatImage();
  FloatImage dyx = new FloatImage();
  FloatImage mag = new FloatImage();
  FloatImage[] edges = new FloatImage[4];
  ISnapper snapper;
  int viewMode = 0;
  public static int VIEW_IMAGE = 0;
  public static int VIEW_EDGES = 1;
  private int maxUnitIncrement = 1;

  public ImagePanel(BufferedImage img) {
    this.img = img;
    this.display = img;
    if(img != null) {
      this.setSize(img.getWidth(), img.getHeight());
    } else {
      this.setSize(100, 100);
    }

    this.addMouseMotionListener(this);
    this.addMouseListener(this);
  }

  public void setSnapper(ISnapper s) {
    this.snapper = s;
  }

  public void setImage(BufferedImage img) {
    this.img = img;
    this.fimg.convertImage(img);
    float[] h = new float[]{0.25F, 0.5F, 0.25F};
    float[] g = new float[]{-1.0F, 0.0F, 1.0F};
    FloatImage tmp = new FloatImage(img.getWidth(), img.getHeight());
    this.dx = new FloatImage(img.getWidth(), img.getHeight());
    this.dy = new FloatImage(img.getWidth(), img.getHeight());
    this.dxy = new FloatImage(img.getWidth(), img.getHeight());
    this.dyx = new FloatImage(img.getWidth(), img.getHeight());
    this.mag = new FloatImage(img.getWidth(), img.getHeight());
    byte k = 3;
    int l = 1;

    for(int recipsqrt2 = 1; recipsqrt2 < k; ++recipsqrt2) {
      this.fimg.convolve(h, 1, l);
      l *= 2;
    }

    this.fimg.convolve_x(tmp, h, 1, l);
    tmp.convolve_y(this.dy, g, 1, l);
    this.fimg.convolve_y(tmp, h, 1, l);
    tmp.convolve_x(this.dx, g, 1, l);
    float var8 = (float)(1.0D / Math.sqrt(2.0D));
    this.dxy.add(this.dx, var8);
    this.dxy.add(this.dy, var8);
    this.dyx.add(this.dx, var8);
    this.dyx.add(this.dy, -var8);
    gfunc(this.dx);
    gfunc(this.dy);
    gfunc(this.dxy);
    gfunc(this.dyx);
    this.dxy.scale((float)Math.sqrt(2.0D));
    this.dyx.scale((float)Math.sqrt(2.0D));
    this.edges[0] = this.dy;
    this.edges[1] = this.dyx;
    this.edges[2] = this.dx;
    this.edges[3] = this.dxy;
    this.display = FloatImage.reconvertImage(this.dy, this.dx, this.dxy);
    if(img != null) {
      this.setSize((int)((double)img.getWidth() * 1.5D), (int)((double)img.getHeight() * 1.5D));
    } else {
      this.setSize(100, 100);
    }

    this.lines = new ArrayList();
    this.repaint();
  }

  static void expGfunc(FloatImage fimg) {
    fimg.magnitudeSquared();
    fimg.scale(-1.0E-4F);
    fimg.exp();
    fimg.scale(256.0F);
  }

  static void gfunc(FloatImage fimg) {
    fimg.magnitude();
    fimg.scale(-1.0F);
    fimg.add(256.0F);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(this.viewMode == VIEW_EDGES && this.display != null) {
      g.drawImage(this.display, 0, 0, this);
    }

    if(this.viewMode == VIEW_IMAGE && this.img != null) {
      g.drawImage(this.img, 0, 0, this);
    }

    if(this.line != null) {
      this.drawLine(g, Color.RED, this.line);
    }

    Iterator i$ = this.lines.iterator();

    while(i$.hasNext()) {
      LinkedList l = (LinkedList)i$.next();
      if(l != null) {
        this.drawLine(g, Color.green, l);
      }
    }

  }

  public void setViewMode(int vm) {
    this.viewMode = vm;
    this.repaint();
  }

  public void drawLine(Graphics g, Color c, LinkedList<Point> line) {
    if(line.size() != 0) {
      Point p = (Point)line.element();
      g.setColor(c);

      Point q;
      for(Iterator i$ = line.iterator(); i$.hasNext(); p = q) {
        q = (Point)i$.next();
        g.drawLine(p.x, p.y, q.x, q.y);
      }

    }
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    if(this.snapper != null) {
      this.snapper.setSeed(e.getX(), e.getY(), this.edges);
    }
  }

  public void mouseReleased(MouseEvent e) {
    if(this.snapper != null) {
      LinkedList l = this.snapper.getPath(e.getX(), e.getY());
      if(l != null) {
        this.lines.add(l);
      }

    }
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseDragged(MouseEvent e) {
    if(this.snapper != null) {
      this.line = this.snapper.getPath(e.getX(), e.getY());
      this.paint(this.getGraphics());
    }
  }

  public void mouseMoved(MouseEvent e) {
  }

  public Dimension getPreferredSize() {
    Dimension dim = this.getParent().getSize();
    if(this.img == null) {
      return dim;
    } else {
      if(dim.getWidth() < (double)this.img.getWidth()) {
        dim.width = this.img.getWidth();
      }

      if(dim.getHeight() < (double)this.img.getHeight()) {
        dim.height = this.img.getHeight();
      }

      return dim;
    }
  }

  public Dimension getPreferredScrollableViewportSize() {
    return this.getPreferredSize();
  }

  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    boolean currentPosition = false;
    int currentPosition1;
    if(orientation == 0) {
      currentPosition1 = visibleRect.x;
    } else {
      currentPosition1 = visibleRect.y;
    }

    if(direction < 0) {
      int newPosition = currentPosition1 - currentPosition1 / this.maxUnitIncrement * this.maxUnitIncrement;
      return newPosition == 0?this.maxUnitIncrement:newPosition;
    } else {
      return (currentPosition1 / this.maxUnitIncrement + 1) * this.maxUnitIncrement - currentPosition1;
    }
  }

  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return orientation == 0?visibleRect.width - this.maxUnitIncrement:visibleRect.height - this.maxUnitIncrement;
  }

  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  public boolean getScrollableTracksViewportHeight() {
    return false;
  }
}
