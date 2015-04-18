//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cs21120.assignment2;

import cs21120.assignment2.FloatImage;
import java.awt.Point;
import java.util.LinkedList;

public interface ISnapper {
  void setSeed(int var1, int var2, FloatImage[] var3);

  LinkedList<Point> getPath(int var1, int var2);
}
