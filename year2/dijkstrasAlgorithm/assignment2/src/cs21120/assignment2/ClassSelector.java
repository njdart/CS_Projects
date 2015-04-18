//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cs21120.assignment2;

import cs21120.assignment2.ClasspathInspector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.layout.GroupLayout;

public class ClassSelector extends JDialog {
  private List<Class> candidates = new ArrayList();
  Class baseClass;
  private JButton cancelButton;
  private JLabel jLabel1;
  private JScrollPane jScrollPane1;
  private JButton jarButton;
  private JList matchingList;
  private JButton okButton;

  public ClassSelector(Frame parent, boolean modal, String label, Class baseClass) throws MalformedURLException {
    super(parent, modal);
    this.baseClass = baseClass;
    this.candidates = ClasspathInspector.getMatchingClasses(baseClass);
    this.initComponents();
    this.jLabel1.setText(label);
  }

  public Class getSelection() {
    return (Class)this.matchingList.getSelectedValue();
  }

  private void initComponents() {
    this.okButton = new JButton();
    this.cancelButton = new JButton();
    this.jLabel1 = new JLabel();
    this.jScrollPane1 = new JScrollPane();
    this.matchingList = new JList();
    this.jarButton = new JButton();
    this.setDefaultCloseOperation(2);
    this.setTitle("Select Class");
    this.okButton.setText("OK");
    this.okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ClassSelector.this.okButtonActionPerformed(evt);
      }
    });
    this.cancelButton.setText("Cancel");
    this.cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ClassSelector.this.cancelButtonActionPerformed(evt);
      }
    });
    this.jLabel1.setText("Matching Classes:");
    this.matchingList.setModel(this.createListModel());
    this.matchingList.setSelectionMode(0);
    this.matchingList.setCellRenderer(this.createCellRenderer());
    this.matchingList.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        ClassSelector.this.matchingListKeyPressed(evt);
      }
    });
    this.jScrollPane1.setViewportView(this.matchingList);
    this.jarButton.setText("Add jar");
    this.jarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ClassSelector.this.jarButtonActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(this.getContentPane());
    this.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(1, this.jScrollPane1, -1, 410, 32767).add(layout.createSequentialGroup().add(this.jarButton).addPreferredGap(0, 262, 32767).add(this.cancelButton).addPreferredGap(0).add(this.okButton)).add(1, this.jLabel1)).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(49, 49, 49).add(this.jLabel1).addPreferredGap(0).add(this.jScrollPane1, -1, 199, 32767).addPreferredGap(0).add(layout.createParallelGroup(3).add(this.okButton).add(this.cancelButton).add(this.jarButton)).addContainerGap()));
    this.pack();
  }

  private void matchingListKeyPressed(KeyEvent evt) {
    if(evt.getKeyCode() == 10) {
      this.okButtonActionPerformed((ActionEvent)null);
    }

  }

  private void cancelButtonActionPerformed(ActionEvent evt) {
    this.matchingList.setSelectedIndex(-1);
    this.dispose();
  }

  private void okButtonActionPerformed(ActionEvent evt) {
    this.dispose();
  }

  private void jarButtonActionPerformed(ActionEvent evt) {
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar files", new String[]{"jar"});
    JFileChooser jFileChooser1 = new JFileChooser();
    jFileChooser1.setFileFilter(filter);
    jFileChooser1.showOpenDialog(this);
    File f = jFileChooser1.getSelectedFile();
    if(f != null) {
      try {
        this.addURL(f.toURI().toURL());
        String ex = System.getProperty("java.class.path");
        System.out.println("Classpath(before) = " + ex);
        ex = ex + File.pathSeparator + f.getPath();
        System.setProperty("java.class.path", ex);
        ex = System.getProperty("java.class.path");
        System.out.println("Classpath(after) = " + ex);
        this.candidates = ClasspathInspector.getMatchingClasses(this.baseClass);
        ((ClassSelector.Model)this.matchingList.getModel()).fireListChange();
      } catch (Exception var6) {
        JOptionPane.showMessageDialog(this, "Error loading jar: " + var6, "Error loading jar file", 0);
      }

    }
  }

  public void addURL(URL url) throws Exception {
    URL[] urls = new URL[]{url};
    URLClassLoader classLoader = new URLClassLoader(urls, ClasspathInspector.class.getClassLoader());
    Class clazz = URLClassLoader.class;
    Method method = clazz.getDeclaredMethod("addURL", new Class[]{URL.class});
    method.setAccessible(true);
    method.invoke(classLoader, new Object[]{url});
    System.out.println("Loaded " + url);
  }

  private ListModel createListModel() {
    return new ClassSelector.Model();
  }

  private ListCellRenderer createCellRenderer() {
    return new ClassSelector.Renderer();
  }

  private class Renderer implements ListCellRenderer {
    private Renderer() {
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      Class c = (Class)value;
      String packageName = c.getCanonicalName();
      packageName = packageName.substring(0, packageName.lastIndexOf("."));
      String str = c.getSimpleName() + " (" + packageName + ")";
      JLabel label = new JLabel(str);
      if(isSelected) {
        label.setBackground(Color.BLUE);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
      }

      return label;
    }
  }

  private class Model extends KeyAdapter implements ListModel {
    private List<ListDataListener> listeners = new ArrayList();

    public Model() {
    }

    public int getSize() {
      return ClassSelector.this.candidates.size();
    }

    public Object getElementAt(int index) {
      return ClassSelector.this.candidates.get(index);
    }

    public void addListDataListener(ListDataListener l) {
      this.listeners.add(l);
    }

    public void removeListDataListener(ListDataListener l) {
      this.listeners.remove(l);
    }

    public void fireListChange() {
      ListDataEvent evt = new ListDataEvent(this, 0, 0, this.getSize() - 1);
      Iterator i$ = this.listeners.iterator();

      while(i$.hasNext()) {
        ListDataListener l = (ListDataListener)i$.next();
        l.contentsChanged(evt);
      }

    }

    public void keyReleased(KeyEvent e) {
    }
  }
}
