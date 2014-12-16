package theModel;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class Model {
	
	private ArrayList<Module> moduleList = new ArrayList<Module>();
	private ArrayList<Student> studentList = new ArrayList<Student>();

	public void runTest(){
		System.out.println("\t=> 1. Load data from text files, store in the Model and print report to show base data");
		loadFromTextFile();
		printReport();
		
		System.out.println("\t=> 2. Save whole Model using Serializable");
		serializeAndSave();
		
		System.out.println("\t=> 3. Change something � for instance you might enrol one of the students on CS10310");
		Student john = new Student("doj22", "John", "Dorling", "G400");
		studentList.add(john);
		moduleList.get(1).addThisStudent(john);
		
		System.out.println("\t=> 4. Print report to show change");
		printReport();
		
		System.out.println("\t=> 5. Read Model back from the serialized file");
		studentList = null; moduleList = null;
		deserializeAndLoad();
		
		System.out.println("\t=> 6. Print report to show that you are back with old data ");
		printReport();
		
		System.out.println("\t=> 7. Save whole Model using XML");
		XmlSerializer.serializeModules(moduleList, "modules.xml");
		XmlSerializer.serializeStudents(studentList, "students.xml");
		
		Gson g = new Gson();
		String moduleGson = g.toJson(moduleList);
		String studentGson = g.toJson(studentList); 
		try(BufferedWriter s = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("students.json")));
			BufferedWriter m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("modules.json" )))){
			s.write(studentGson);
			m.write(moduleGson);
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("-->" + moduleGson + "\n\r-->" + studentGson);
		
		System.out.println("\t=> 8. Change something");
		Student james = new Student("jah12", "James", "Howard", "G600");
		studentList.add(james);
		moduleList.get(2).addThisStudent(james);
		
		System.out.println("\t=> 9. Print report to show change");
		printReport();
		
		System.out.println("\t=> 10. Read from the XML file");
		studentList = null;	moduleList = null;
		//studentList = g.fromJson(studentGson, new TypeToken<ArrayList<Student>>(){}.getType());
		//moduleList = g.fromJson(moduleGson, new TypeToken<ArrayList<Module>>(){}.getType());
		moduleList = XmlSerializer.deserializeModules("modules.xml");
		studentList = XmlSerializer.deserializeStudents("students.xml");
		
		System.out.println("\t=> 11. Print report to show that you are back with old data");
		printReport();
	}
	
	private void serializeAndSave() {
		try(ObjectOutputStream students = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("students.dat"))));
			ObjectOutputStream modules  = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("modules.dat" ))))){
			students.writeObject(studentList);
			modules.writeObject(moduleList);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void deserializeAndLoad(){
		try(ObjectInputStream students = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("students.dat"))));
			ObjectInputStream modules  = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("modules.dat" ))))){
			
			studentList = (ArrayList<Student>)students.readObject();
			moduleList  = (ArrayList<Module> )modules .readObject();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}

	public void loadFromTextFile(){
		try(Scanner students = new Scanner(new InputStreamReader(new FileInputStream("students.txt")));
			Scanner modules  = new Scanner(new InputStreamReader(new FileInputStream("modules.txt" )))){
			
			//Students file
			int studentsNum = students.nextInt(); students.nextLine();
			for(int student = 0; student < studentsNum; student++){
				String uid = students.nextLine();
				String last = students.nextLine();
				String first = students.nextLine();
				String degree = students.nextLine();
				studentList.add(new Student(uid, first, last, degree));
			}
			
			//Modules File
			int modulesNum = modules.nextInt(); modules.nextLine();			
			
			for(int module = 0; module < modulesNum; module++){
				String moduleName = modules.nextLine();
				int studentsInModule = modules.nextInt(); modules.nextLine();
				
				Module m = new Module(moduleName);
				
				for(int student = 0; student < studentsInModule; student++){
					String studentId = modules.nextLine();
					for(Student s : studentList)
						if(s.id().equals(studentId))
							m.addThisStudent(s);
				}
				moduleList.add(m);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void printReport(){
		System.out.println("===REPORT===");
		for(Module m: moduleList){
			System.out.println(m.code());
			for(Student s: m.getStudents()){
				System.out.println("-->" + s);
			}
		}
		System.out.println("============");
	}
	
	public Student findAStudent(String uuid){
		for(Student s : studentList)
			if(s.id().equals(uuid))
				return s;
		return null;
	}
	
	//////////////////////////////////
	
	public static void main(String[] args){
		Model model = new Model();
		model.runTest();
		

		System.out.println("\n\r\n\rCollect first 10% of 124!");
		
		String tokens = "hello,world,how,are,you,today,this,is,a,trivial,task,why,do,i,bother?";
		StringTokenizer tok = new StringTokenizer(tokens, ",");
		while(tok.hasMoreTokens())
			System.out.println(tok.nextToken());
		
	}

}
