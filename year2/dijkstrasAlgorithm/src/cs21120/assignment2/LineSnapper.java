//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cs21120.assignment2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineSnapper extends JFrame {
  JFileChooser jFileChooser = new JFileChooser();
  ImagePanel ip;
  private JMenuItem LoadImage;
  private JMenuItem SelectSnapper;
  private ButtonGroup buttonGroup1;
  private JMenuItem exit;
  private JMenu jMenu1;
  private JMenu jMenu2;
  private JMenuBar jMenuBar1;
  private JScrollPane jScrollPane1;
  private JRadioButtonMenuItem viewEdgesRadioButton;
  private JRadioButtonMenuItem viewImageRadioButton;

  public LineSnapper(){
    this(null);
  }

  public LineSnapper(String[] args) {
    this.initComponents();
    this.ip = new ImagePanel((BufferedImage)null);
    this.jScrollPane1.setViewportView(this.ip);
    this.jScrollPane1.setSize(300, 300);
    this.jScrollPane1.getViewport().setExtentSize(new Dimension(300, 300));
    this.doLayout();
    this.pack();
    if(args != null){
      if(args.length == 3) {
        SetSnapperFromName(args[0], args[1]);
        SetImageFromName(args[2]);
      }
    }
  }

  private void initComponents() {
    this.buttonGroup1 = new ButtonGroup();
    this.jScrollPane1 = new JScrollPane();
    this.jMenuBar1 = new JMenuBar();
    this.jMenu1 = new JMenu();
    this.LoadImage = new JMenuItem();
    this.SelectSnapper = new JMenuItem();
    this.exit = new JMenuItem();
    this.jMenu2 = new JMenu();
    this.viewImageRadioButton = new JRadioButtonMenuItem();
    this.viewEdgesRadioButton = new JRadioButtonMenuItem();
    this.setDefaultCloseOperation(3);
    this.jScrollPane1.setPreferredSize(new Dimension(400, 400));
    this.getContentPane().add(this.jScrollPane1, "Center");
    this.jMenu1.setText("File");
    this.LoadImage.setText("Load Image");
    this.LoadImage.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        LineSnapper.this.LoadImageActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.LoadImage);
    this.SelectSnapper.setText("Select Snapper");
    this.SelectSnapper.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        LineSnapper.this.SelectSnapperActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.SelectSnapper);
    this.exit.setText("Exit");
    this.exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        LineSnapper.this.exitActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.exit);
    this.jMenuBar1.add(this.jMenu1);
    this.jMenu2.setText("View");
    this.buttonGroup1.add(this.viewImageRadioButton);
    this.viewImageRadioButton.setText("view image");
    this.viewImageRadioButton.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        LineSnapper.this.viewImageRadioButtonStateChanged(evt);
      }
    });
    this.jMenu2.add(this.viewImageRadioButton);
    this.buttonGroup1.add(this.viewEdgesRadioButton);
    this.viewEdgesRadioButton.setSelected(true);
    this.viewEdgesRadioButton.setText("view edge weights");
    this.viewEdgesRadioButton.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        LineSnapper.this.viewEdgesRadioButtonStateChanged(evt);
      }
    });
    this.jMenu2.add(this.viewEdgesRadioButton);
    this.jMenuBar1.add(this.jMenu2);
    this.setJMenuBar(this.jMenuBar1);
    this.pack();
  }

  public static JFileChooser setUpFileDialog(JFileChooser jFileChooser1, String type, String... exts) {
    FileNameExtensionFilter filter = new FileNameExtensionFilter(type, exts);
    File curDir = jFileChooser1.getCurrentDirectory();
    jFileChooser1 = new JFileChooser();
    jFileChooser1.setCurrentDirectory(curDir);
    jFileChooser1.setFileFilter(filter);
    return jFileChooser1;
  }

  private void LoadImageActionPerformed(ActionEvent evt) {
    this.jFileChooser = setUpFileDialog(this.jFileChooser, "Images", new String[]{"jpg", "gif", "png", "bmp", "tiff", "jp2"});
    int ok = this.jFileChooser.showOpenDialog(this);
    File f = this.jFileChooser.getSelectedFile();
    if(f != null && ok == 0) {
      try {
        BufferedImage e = ImageIO.read(f);
        this.ip.setImage(e);
        this.ip.paint(this.ip.getGraphics());
      } catch (IOException var5) {
        System.out.println("Unable to load image");
        JOptionPane.showMessageDialog(this, "Unable to load image " + var5, "Unable to load image", 0);
      }

    }
  }

  private void viewImageRadioButtonStateChanged(ChangeEvent evt) {
    if(this.viewImageRadioButton.isSelected()) {
      this.ip.setViewMode(ImagePanel.VIEW_IMAGE);
    } else {
      this.ip.setViewMode(ImagePanel.VIEW_EDGES);
    }

  }

  private void viewEdgesRadioButtonStateChanged(ChangeEvent evt) {
    if(this.viewEdgesRadioButton.isSelected()) {
      this.ip.setViewMode(ImagePanel.VIEW_EDGES);
    } else {
      this.ip.setViewMode(ImagePanel.VIEW_IMAGE);
    }

  }

  private void SetSnapperFromName(String jar, String name){
    System.out.println("looking for jar at " + jar + " with package name " + name);
    try {
      URLClassLoader child = new URLClassLoader(new URL[] { new URL("file://" + jar) }, this.getClass().getClassLoader());
      Class classToLoad = Class.forName(name, true, child);

      this.ip.setSnapper((ISnapper) classToLoad.newInstance());
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Expected arg 0 to be jar file (eg /home/foo/bar/assignment.jar)");
      System.out.println("Expected arg 1 to be package.class (eg cs21120.assignment2.solution.abc123)");
      e.printStackTrace();
      System.exit(1);
    }
  }

  private void SetImageFromName(String name){
    try {
      BufferedImage img = ImageIO.read(new File(name));
      this.ip.setImage(img);
    } catch (IOException e) {
      System.out.println("Expected arg 1 to be image (eg /home/foo/bar/img.jpg)");
      e.printStackTrace();
      System.exit(1);
    }
  }

  private void SelectSnapperActionPerformed(ActionEvent evt) {
    try {
      ClassSelector ex = new ClassSelector(this, true, "Choose ISnapper class", ISnapper.class);
      ex.setVisible(true);
      Class clazz = ex.getSelection();
      ISnapper snapper = (ISnapper)clazz.newInstance();
      if(snapper != null) {
        this.ip.setSnapper(snapper);
      }
    } catch (MalformedURLException var5) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var5);
    } catch (InstantiationException var6) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var6);
    } catch (IllegalAccessException var7) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var7);
    }

  }

  private void exitActionPerformed(ActionEvent evt) {
    System.exit(0);
  }

  public static void main(final String[] args) {
    System.out.println(args.toString());
    try {
      LookAndFeelInfo[] ex = UIManager.getInstalledLookAndFeels();
      int len$ = ex.length;

      for(int i$ = 0; i$ < len$; ++i$) {
        LookAndFeelInfo info = ex[i$];
        if("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException var5) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var5);
    } catch (InstantiationException var6) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var6);
    } catch (IllegalAccessException var7) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var7);
    } catch (UnsupportedLookAndFeelException var8) {
      Logger.getLogger(LineSnapper.class.getName()).log(Level.SEVERE, (String)null, var8);
    }

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        (new LineSnapper(args)).setVisible(true);
      }
    });
  }
}
