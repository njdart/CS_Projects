package theModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Module implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String code;
	private ArrayList<Student> enrolledStudents = new ArrayList<Student>();
	
	//getters and setters
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Module(String code){
		this.code = code;
	}
	
	//FUCK YOU BEAN! Json was my friend!... 
	public Module(){
	}
	
	
	public void addThisStudent(Student s){
		enrolledStudents.add(s);
	}
	
	public String code(){
		return code;
	}
	
	public ArrayList<Student> getStudents(){
		return enrolledStudents;
	}
	
	public String toString(){
		String enrolled = "[";
		for(Student s : enrolledStudents)
			enrolled+= s.name()+", ";
		enrolled += "]";
		return code + " with students " + enrolled;
	}
	
	public Student findStudentById(String id){
		for(Student s : enrolledStudents)
			if(s.id() == id)
				return s;
		return null;
	}

}
