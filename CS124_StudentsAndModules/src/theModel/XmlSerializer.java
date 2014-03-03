package theModel;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XmlSerializer {
	
	public static void serializeModules(ArrayList<Module> moduleList, String f){
		try(XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)))){
			for(Module o: moduleList){
				e.writeObject(o);
			}
			e.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void serializeStudents(ArrayList<Student> studentList, String f){
		try(XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)))){
			for(Student o: studentList){
				e.writeObject(o);
			}
			e.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<Student> deserializeStudents(String f){
		ArrayList<Student> objs = new ArrayList<Student>();
		try(XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)))){
			Student obj;
			while((obj = (Student)d.readObject()) != null)
				objs.add(obj);
			return objs;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}catch(ArrayIndexOutOfBoundsException ex){
			//swallow
			return objs;
		}
	}
	
	public static ArrayList<Module> deserializeModules(String f){
		ArrayList<Module> objs = new ArrayList<Module>();
		try(XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)))){
			Module obj;
			while((obj = (Module)d.readObject()) != null)
				objs.add(obj);
			return objs;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}catch(ArrayIndexOutOfBoundsException ex){
			//swallow
			return objs;
		}
	}

}
