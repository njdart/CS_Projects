//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cs21120.assignment2;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class ClasspathInspector {
  static boolean DEBUG = false;

  public ClasspathInspector() {
  }

  public static List<Class> getAllKnownClasses() throws MalformedURLException {
    ArrayList classFiles = new ArrayList();
    List classLocations = getClassLocationsForCurrentClasspath();
    Iterator i$ = classLocations.iterator();

    while(i$.hasNext()) {
      File file = (File)i$.next();
      classFiles.addAll(getClassesFromPath(file));
    }

    return classFiles;
  }

  public static List<Class> getMatchingClasses(Class interfaceOrSuperclass) throws MalformedURLException {
    ArrayList matchingClasses = new ArrayList();
    List classes = getAllKnownClasses();
    log("checking %s classes", new Object[]{Integer.valueOf(classes.size())});
    Iterator i$ = classes.iterator();

    while(i$.hasNext()) {
      Class clazz = (Class)i$.next();
      if(interfaceOrSuperclass.isAssignableFrom(clazz)) {
        if(!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && !clazz.isAnonymousClass() && !clazz.isSynthetic()) {
          try {
            Constructor e = clazz.getConstructor(new Class[0]);
            if(e != null) {
              matchingClasses.add(clazz);
              log("class %s is assignable from %s", new Object[]{interfaceOrSuperclass, clazz});
            }
          } catch (NoSuchMethodException var6) {
            ;
          }
        }
      } else {
        log("class %s is NOT assignable from %s", new Object[]{interfaceOrSuperclass, clazz});
      }
    }

    return matchingClasses;
  }

  public static List<Class> getMatchingClasses(Class interfaceOrSuperclass, File file) throws MalformedURLException {
    ArrayList matchingClasses = new ArrayList();
    ArrayList classes = new ArrayList();
    classes.addAll(getClassesFromPath(file));
    log("checking %s classes", new Object[]{Integer.valueOf(classes.size())});
    Iterator i$ = classes.iterator();

    while(i$.hasNext()) {
      Class clazz = (Class)i$.next();
      if(interfaceOrSuperclass.isAssignableFrom(clazz)) {
        if(!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && !clazz.isAnonymousClass() && !clazz.isSynthetic()) {
          Constructor con = null;
          Object con2 = null;

          try {
            con = clazz.getConstructor(new Class[0]);
          } catch (NoSuchMethodException var9) {
            ;
          }

          if(con == null) {
            log("class %s is NOT does not have a suitable constructor", new Object[]{clazz});
          } else {
            matchingClasses.add(clazz);
            log("class %s is assignable from %s", new Object[]{interfaceOrSuperclass, clazz});
          }
        }
      } else {
        log("class %s is NOT assignable from %s", new Object[]{interfaceOrSuperclass, clazz});
      }
    }

    return matchingClasses;
  }

  public static List<Class> getMatchingClasses(Class interfaceOrSuperclass, URL url) throws MalformedURLException {
    ArrayList matchingClasses = new ArrayList();
    ArrayList classes = new ArrayList();
    classes.addAll(getClassesFromJar(url));
    log("checking %s classes", new Object[]{Integer.valueOf(classes.size())});
    Iterator i$ = classes.iterator();

    while(i$.hasNext()) {
      Class clazz = (Class)i$.next();
      if(interfaceOrSuperclass.isAssignableFrom(clazz)) {
        if(!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && !clazz.isAnonymousClass() && !clazz.isSynthetic()) {
          Constructor con = null;
          Object con2 = null;

          try {
            con = clazz.getConstructor(new Class[0]);
          } catch (NoSuchMethodException var9) {
            ;
          }

          if(con == null) {
            log("class %s is NOT does not have a suitable constructor", new Object[]{clazz});
          } else {
            matchingClasses.add(clazz);
            log("class %s is assignable from %s", new Object[]{interfaceOrSuperclass, clazz});
          }
        }
      } else {
        log("class %s is NOT assignable from %s", new Object[]{interfaceOrSuperclass, clazz});
      }
    }

    return matchingClasses;
  }

  private static Collection<? extends Class> getClassesFromPath(File path) throws MalformedURLException {
    return path.isDirectory()?getClassesFromDirectory(path):getClassesFromJarFile(path);
  }

  private static String fromFileToClassName(String fileName) {
    return fileName.substring(0, fileName.length() - 6).replaceAll("/|\\\\", "\\.");
  }

  private static List<Class> getClassesFromJarFile(File path) throws MalformedURLException {
    ArrayList classes = new ArrayList();
    log("getClassesFromJarFile: Getting classes for %s", new Object[]{path});
    URL[] urls = new URL[]{path.toURI().toURL()};
    URLClassLoader classLoader = new URLClassLoader(urls, ClasspathInspector.class.getClassLoader());

    try {
      if(path.canRead()) {
        JarFile e = new JarFile(path);
        Enumeration en = e.entries();

        while(en.hasMoreElements()) {
          JarEntry entry = (JarEntry)en.nextElement();
          if(entry.getName().endsWith("class")) {
            String className = fromFileToClassName(entry.getName());
            log("\tgetClassesFromJarFile: found %s", new Object[]{className});
            loadClass(classes, className, classLoader);
          }
        }
      }

      return classes;
    } catch (Exception var8) {
      throw new RuntimeException("Failed to read classes from jar file: " + path, var8);
    }
  }

  private static List<Class> getClassesFromJar(URL path) throws MalformedURLException {
    ArrayList classes = new ArrayList();
    log("getClassesFromJarFile: Getting classes for %s", new Object[]{path});
    URL[] urls = new URL[]{path};
    URLClassLoader classLoader = new URLClassLoader(urls, ClasspathInspector.class.getClassLoader());

    try {
      JarInputStream e = new JarInputStream(path.openStream());

      for(JarEntry entry = e.getNextJarEntry(); entry != null; entry = e.getNextJarEntry()) {
        if(entry.getName().endsWith("class")) {
          String className = fromFileToClassName(entry.getName());
          log("\tgetClassesFromJarFile: found %s", new Object[]{className});
          loadClass(classes, className, classLoader);
        }
      }

      return classes;
    } catch (Exception var7) {
      throw new RuntimeException("Failed to read classes from jar file: " + path, var7);
    }
  }

  private static List<Class> getClassesFromDirectory(File path) throws MalformedURLException {
    ArrayList classes = new ArrayList();
    log("getClassesFromDirectory: Getting classes for " + path, new Object[0]);
    URL[] urls = new URL[]{path.toURI().toURL()};
    URLClassLoader classLoader = new URLClassLoader(urls, ClasspathInspector.class.getClassLoader());
    List jarFiles = listFiles(path, new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.endsWith(".jar");
      }
    }, false);
    Iterator classFiles = jarFiles.iterator();

    while(classFiles.hasNext()) {
      File substringBeginIndex = (File)classFiles.next();
      classes.addAll(getClassesFromJarFile(substringBeginIndex));
    }

    List classFiles1 = listFiles(path, new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.endsWith(".class");
      }
    }, true);
    int substringBeginIndex1 = path.getAbsolutePath().length() + 1;
    Iterator i$ = classFiles1.iterator();

    while(i$.hasNext()) {
      File classfile = (File)i$.next();
      String className = classfile.getAbsolutePath().substring(substringBeginIndex1);
      className = fromFileToClassName(className);
      log("Found class %s in path %s: ", new Object[]{className, path});
      loadClass(classes, className, classLoader);
    }

    return classes;
  }

  private static List<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
    ArrayList files = new ArrayList();
    File[] entries = directory.listFiles();
    File[] arr$ = entries;
    int len$ = entries.length;

    for(int i$ = 0; i$ < len$; ++i$) {
      File entry = arr$[i$];
      if(filter == null || filter.accept(directory, entry.getName())) {
        files.add(entry);
      }

      if(recurse && entry.isDirectory()) {
        files.addAll(listFiles(entry, filter, recurse));
      }
    }

    return files;
  }

  public static List<File> getClassLocationsForCurrentClasspath() {
    ArrayList urls = new ArrayList();
    String javaClassPath = System.getProperty("java.class.path");
    String facemorplib = "facemorphlib.jar";
    if(!javaClassPath.contains(facemorplib.subSequence(0, facemorplib.length()))) {
      String arr$ = (new File(ClasspathInspector.class.getResource("").getPath())).getParentFile().getParent();
      arr$ = arr$.substring(0, arr$.length() - 1);
      System.out.println("path = " + arr$);
    }

    log("Classpath(locations) = " + javaClassPath, new Object[0]);
    if(javaClassPath != null) {
      String[] var7 = javaClassPath.split(File.pathSeparator);
      int len$ = var7.length;

      for(int i$ = 0; i$ < len$; ++i$) {
        String path = var7[i$];
        urls.add(new File(path));
      }
    }

    return urls;
  }

  public static URL normalize(URL url) throws MalformedURLException {
    String spec = url.getFile();
    int i = spec.indexOf("!/");
    if(i != -1) {
      spec = spec.substring(0, spec.indexOf("!/"));
    }

    url = new URL(url, spec);
    String file = url.getFile();
    int i1 = file.indexOf(58);
    if(i1 != -1) {
      String drive = file.substring(i1 - 1, 2).toUpperCase();
      url = new URL(url, file.substring(0, i1 - 1) + drive + file.substring(i1));
    }

    return url;
  }

  private static void log(String pattern, Object... args) {
    if(DEBUG) {
      System.out.printf(pattern + "\n", args);
    }

  }

  private static void loadClass(List<Class> classes, String className, ClassLoader loader) {
    try {
      Class iae = Class.forName(className, false, loader);
      classes.add(iae);
    } catch (ClassNotFoundException var4) {
      log("ClassNotFoundException: Could not load class %s: %s", new Object[]{className, var4});
    } catch (NoClassDefFoundError var5) {
      log("NoClassDefFoundError: Could not load class %s: %s", new Object[]{className, var5});
    } catch (IllegalAccessError var6) {
      log("IllegalAccessError: Could not load class %s: %s", new Object[]{className, var6});
    }

  }
}
