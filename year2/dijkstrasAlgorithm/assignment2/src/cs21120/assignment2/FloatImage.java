//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cs21120.assignment2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class FloatImage {
  int width;
  int height;
  float[] data;

  public FloatImage() {
    this.width = this.height = 0;
    this.data = null;
  }

  public FloatImage(int w, int h) {
    this.width = w;
    this.height = h;
    this.data = new float[this.width * this.height];
  }

  public FloatImage(int w, int h, float[] d) {
    if(d.length != w * h) {
      throw new RuntimeException("Array not a suitable size");
    } else {
      this.width = w;
      this.height = h;
      this.data = d;
    }
  }

  public FloatImage(int w, int h, double[] d) {
    this.width = w;
    this.height = h;
    if(d.length != this.width * this.height) {
      throw new RuntimeException("Array not a suitable size");
    } else {
      this.data = new float[d.length];

      for(int i = 0; i < d.length; ++i) {
        this.data[i] = (float)d[i];
      }

    }
  }

  public float[] getData() {
    return this.data;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public float get_nocheck(int x, int y) {
    return this.data[x + y * this.width];
  }

  public float get_sym(int x, int y) {
    x = Math.abs(x) % (this.width * 2);
    y = Math.abs(y) % (this.height * 2);
    if(x >= this.width) {
      x = 2 * this.width - x - 1;
    }

    if(y >= this.height) {
      y = 2 * this.height - y - 1;
    }

    return this.data[x + y * this.width];
  }

  public float get(int x, int y) {
    if(x >= this.width) {
      x = this.width - 1;
    }

    if(x < 0) {
      x = 0;
    }

    if(y >= this.height) {
      y = this.height - 1;
    }

    if(y < 0) {
      y = 0;
    }

    return this.data[x + y * this.width];
  }

  public float get(int i) {
    return this.data[i];
  }

  public void set_nocheck(int x, int y, float v) {
    this.data[x + y * this.width] = v;
  }

  public void set_nocheck(int i, float v) {
    this.data[i] = v;
  }

  public int set(int x, int y, float v) {
    if(x >= 0 && x < this.width && y >= 0 && y < this.height) {
      this.data[x + y * this.width] = v;
      return 1;
    } else {
      return 0;
    }
  }

  public void convolve(FloatImage fimg, float[][] filter, int m, int n, int scale) {
    int two_width = this.width * 2;
    int two_height = this.height * 2;

    for(int y = 0; y < this.height; ++y) {
      for(int x = 0; x < this.width; ++x) {
        float sum = 0.0F;
        int i = 0;

        for(int k = x - m * scale; i < filter.length; k += scale) {
          int p = Math.abs(k) % two_width;
          if(p >= this.width) {
            p = two_width - p - 2;
          }

          int j = 0;

          for(int l = y - n * scale; j < filter[i].length; l += scale) {
            int q = Math.abs(l) % two_height;
            if(q >= this.height) {
              q = two_height - q - 2;
            }

            sum += filter[i][j] * this.get_nocheck(p, q);
            ++j;
          }

          ++i;
        }

        fimg.set_nocheck(x, y, sum);
      }
    }

  }

  public void convolve(FloatImage fimg, FloatImage filter, int m, int n, int scale) {
    int two_width = this.width * 2;
    int two_height = this.height * 2;

    for(int y = 0; y < this.height; ++y) {
      for(int x = 0; x < this.width; ++x) {
        float sum = 0.0F;
        int i = 0;

        for(int k = x - m * scale; i < filter.getWidth(); k += scale) {
          int p = Math.abs(k) % two_width;
          if(p >= this.width) {
            p = two_width - p - 2;
          }

          int j = 0;

          for(int l = y - n * scale; j < filter.getHeight(); l += scale) {
            int q = Math.abs(l) % two_height;
            if(q >= this.height) {
              q = two_height - q - 2;
            }

            double left = (double)filter.get_nocheck(i, j) / 256.0D;
            double right = (double)this.get_nocheck(p, q) / 256.0D;
            sum = (float)((double)sum + left * right);
            ++j;
          }

          ++i;
        }

        fimg.set_nocheck(x, y, sum);
      }
    }

  }

  public boolean add(float w) {
    for(int x = 0; x < this.width * this.height; ++x) {
      this.data[x] += w;
    }

    return true;
  }

  public boolean scale(float w) {
    for(int x = 0; x < this.width * this.height; ++x) {
      this.data[x] *= w;
    }

    return true;
  }

  public void convolve(float[] filter, int m, int scale) {
    FloatImage tmp = new FloatImage(this.width, this.height);
    this.convolve_x(tmp, filter, m, scale);
    tmp.convolve_y(this, filter, m, scale);
  }

  public void convolve_x(FloatImage fimg, float[] filter, int m, int scale) {
    int two_width = this.width * 2;

    for(int y = 0; y < this.height; ++y) {
      for(int x = 0; x < this.width; ++x) {
        float sum = 0.0F;
        int i = 0;

        for(int k = x - m * scale; i < filter.length; k += scale) {
          int j = Math.abs(k) % two_width;
          if(j >= this.width) {
            j = two_width - j - 2;
          }

          sum += filter[i] * this.get_nocheck(j, y);
          ++i;
        }

        fimg.set_nocheck(x, y, sum);
      }
    }

  }

  public void convolve_y(FloatImage fimg, float[] filter, int m, int scale) {
    m *= scale;
    int two_height = this.height * 2;

    for(int x = 0; x < this.width; ++x) {
      for(int y = 0; y < this.height; ++y) {
        float sum = 0.0F;
        int i = 0;

        for(int k = y - m; i < filter.length; k += scale) {
          int j = Math.abs(k) % two_height;
          if(j >= this.height) {
            j = two_height - j - 2;
          }

          sum += filter[i] * this.get_nocheck(x, j);
          ++i;
        }

        fimg.set_nocheck(x, y, sum);
      }
    }

  }

  public boolean add(FloatImage fimg, float w) {
    if(fimg.width == this.width && fimg.height == this.height) {
      for(int x = 0; x < this.width * this.height; ++x) {
        this.data[x] += fimg.data[x] * w;
      }

      return true;
    } else {
      System.out.println("Different size images");
      return false;
    }
  }

  public boolean exp(FloatImage fimg) {
    if(this.width == fimg.width && this.height == fimg.height) {
      for(int i = 0; i < this.width * this.height; ++i) {
        this.data[i] = (float)Math.exp((double)fimg.data[i]);
      }

      return true;
    } else {
      return false;
    }
  }

  public boolean exp() {
    if(this.data == null) {
      return false;
    } else {
      for(int i = 0; i < this.width * this.height; ++i) {
        this.data[i] = (float)Math.exp((double)this.data[i]);
      }

      return true;
    }
  }

  public boolean add(FloatImage fimg) {
    if(this.width == fimg.width && this.height == fimg.height) {
      for(int i = 0; i < this.width * this.height; ++i) {
        this.data[i] += fimg.data[i];
      }

      return true;
    } else {
      return false;
    }
  }

  public void add(FloatImage fimg1, FloatImage fimg2) {
    if(fimg1.width < fimg2.width) {
      this.width = fimg1.width;
    } else {
      this.width = fimg2.width;
    }

    if(fimg1.height < fimg2.height) {
      this.height = fimg1.height;
    } else {
      this.height = fimg2.height;
    }

    this.data = new float[this.width * this.height];

    for(int y = 0; y < this.height; ++y) {
      for(int x = 0; x < this.width; ++x) {
        this.set(x, y, fimg1.get(x, y) + fimg2.get(x, y));
      }
    }

  }

  public boolean multiply(FloatImage fimg) {
    if(this.width == fimg.width && this.height == fimg.height) {
      for(int i = 0; i < this.width * this.height; ++i) {
        this.data[i] *= fimg.data[i];
      }

      return true;
    } else {
      return false;
    }
  }

  public boolean divide(FloatImage fimg, float tol) {
    if(this.width == fimg.width && this.height == fimg.height) {
      for(int i = 0; i < this.width * this.height; ++i) {
        if(Math.abs(fimg.data[i]) > tol) {
          this.data[i] /= fimg.data[i];
        }
      }

      return true;
    } else {
      return false;
    }
  }

  public void magnitude() {
    int pix = 0;

    for(int y = 0; y < this.height; ++y) {
      for(int x = 0; x < this.width; ++x) {
        this.data[pix] = Math.abs(this.data[pix]);
        ++pix;
      }
    }

  }

  public void magnitudeSquared() {
    int pix = 0;

    for(int y = 0; y < this.height; ++y) {
      for(int x = 0; x < this.width; ++x) {
        this.data[pix] *= this.data[pix];
        ++pix;
      }
    }

  }

  public void edgeMagnitude(FloatImage dx, FloatImage dy) {
    if(dx.width != this.width || dx.height != this.height) {
      this.setSize(dx.width, dx.height);
    }

    for(int i = 0; i < this.data.length; ++i) {
      this.data[i] = (float)Math.sqrt((double)(dx.data[i] * dx.data[i] + dy.data[i] * dy.data[i]));
    }

  }

  public void setSize(int w, int h) {
    this.width = w;
    this.height = h;
    this.data = new float[this.width * this.height];
  }

  public float sample(float x, float y) {
    int i = (int)x;
    int j = (int)y;
    float s = x - (float)i;
    float t = y - (float)j;
    return (float)((double)(s * t * this.get(i + 1, j + 1)) + (1.0D - (double)s) * (double)t * (double)this.get(i, j + 1) + (double)s * (1.0D - (double)t) * (double)this.get(i + 1, j) + (1.0D - (double)s) * (1.0D - (double)t) * (double)this.get(i, j));
  }

  public FloatImage copy() {
    FloatImage fimg = new FloatImage(this.width, this.height);

    for(int i = 0; i < this.width * this.height; ++i) {
      fimg.data[i] = this.data[i];
    }

    return fimg;
  }

  public void copy(FloatImage fimg) {
    this.width = fimg.width;
    this.height = fimg.height;
    this.data = new float[this.width * this.height];
    System.arraycopy(fimg.data, 0, this.data, 0, this.data.length);
  }

  public static void convertImage(BufferedImage img, FloatImage red, FloatImage green, FloatImage blue) {
    int width = img.getWidth();
    int height = img.getHeight();
    red.data = new float[width * height];
    green.width = blue.width = red.width = width;
    green.height = blue.height = red.height = height;
    green.data = new float[width * height];
    blue.data = new float[width * height];
    int[] pix1 = new int[width * height];
    PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pix1, 0, width);

    try {
      pg.grabPixels();
    } catch (InterruptedException var21) {
      System.out.println(var21);
      return;
    }

    int i = 0;

    for(int y = 0; y < height; ++y) {
      for(int x = 0; x < width; ++x) {
        Color c = new Color(pix1[i]);
        red.data[i] = (float)c.getRed();
        green.data[i] = (float)c.getGreen();
        blue.data[i] = (float)c.getBlue();
        ++i;
      }
    }

  }

  public void convertImage(BufferedImage bimg) {
    this.width = bimg.getWidth();
    this.height = bimg.getHeight();
    this.data = new float[this.width * this.height];
    int[] pix = new int[this.width * this.height];
    bimg.getRGB(0, 0, this.width, this.height, pix, 0, this.width);

    for(int y = 0; y < bimg.getHeight(); ++y) {
      for(int x = 0; x < bimg.getWidth(); ++x) {
        Color c = new Color(pix[x + this.width * y]);
        this.data[x + this.width * y] = (float)(c.getRed() + c.getGreen() + c.getBlue()) / 3.0F;
      }
    }

  }

  public BufferedImage getImage() {
    BufferedImage bf = new BufferedImage(this.width, this.height, 10);

    for(int i = 0; i < this.height; ++i) {
      for(int j = 0; j < this.width; ++j) {
        bf.setRGB(j, i, (int)this.get(j, i));
      }
    }

    return bf;
  }

  public static BufferedImage reconvertImage(FloatImage red, FloatImage green, FloatImage blue) {
    int width = red.width;
    int height = red.height;
    int[] pix = new int[width * height];
    int i = 0;

    for(int y = 0; y < height; ++y) {
      for(int x = 0; x < width; ++x) {
        float r = red.data[i];
        r = r > 255.0F?255.0F:(r < 0.0F?0.0F:r);
        float g = green.data[i];
        g = g > 255.0F?255.0F:(g < 0.0F?0.0F:g);
        float b = blue.data[i];
        b = b > 255.0F?255.0F:(b < 0.0F?0.0F:b);
        Color c = new Color((int)r, (int)g, (int)b);
        pix[i] = c.getRGB();
        ++i;
      }
    }

    BufferedImage bimg = new BufferedImage(width, height, 1);
    bimg.setRGB(0, 0, width, height, pix, 0, width);
    return bimg;
  }

  public FloatImage sobelEdge(double thresh) {
    FloatImage ret = new FloatImage(this.getWidth(), this.getHeight());

    for(int j = 1; j < this.getHeight() - 1; ++j) {
      for(int i = 1; i < this.getWidth() - 1; ++i) {
        double Gy = (double)this.get(i - 1, j + 1) + 2.0D * (double)this.get(i, j + 1) + (double)this.get(i + 1, j + 1) - ((double)this.get(i - 1, j - 1) + 2.0D * (double)this.get(i, j - 1) + (double)this.get(i + 1, j - 1));
        double Gx = (double)this.get(i + 1, j - 1) + 2.0D * (double)this.get(i + 1, j) + (double)this.get(i + 1, j + 1) - ((double)this.get(i - 1, j - 1) + 2.0D * (double)this.get(i - 1, j) + (double)this.get(i - 1, j + 1));
        float val = (float)Math.sqrt(Gy * Gy + Gx * Gx);
        if((double)val >= thresh) {
          ret.set(i, j, 1.0F);
        } else {
          ret.set(i, j, 0.0F);
        }
      }
    }

    return ret;
  }

  public FloatImage sobelEdgeMag() {
    FloatImage ret = new FloatImage(this.getWidth(), this.getHeight());

    for(int j = 1; j < this.getHeight() - 1; ++j) {
      for(int i = 1; i < this.getWidth() - 1; ++i) {
        double Gy = (double)this.get(i - 1, j + 1) + 2.0D * (double)this.get(i, j + 1) + (double)this.get(i + 1, j + 1) - ((double)this.get(i - 1, j - 1) + 2.0D * (double)this.get(i, j - 1) + (double)this.get(i + 1, j - 1));
        double Gx = (double)this.get(i + 1, j - 1) + 2.0D * (double)this.get(i + 1, j) + (double)this.get(i + 1, j + 1) - ((double)this.get(i - 1, j - 1) + 2.0D * (double)this.get(i - 1, j) + (double)this.get(i - 1, j + 1));
        float val = (float)Math.sqrt(Gy * Gy + Gx * Gx);
        ret.set(i, j, val);
      }
    }

    return ret;
  }
}
