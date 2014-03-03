package theModel;

import java.io.Serializable;

public class Student implements Serializable {
	
	public static final long serialVersionUID = 1;
	
	public Student(String uid, String first, String last, String degree){
		this.uid = uid;
		this.fName = first.trim();
		this.sName = last.trim();
		this.degree = degree.trim();
		
	}
	public Student(){}
	
	private String uid,
				   fName,
				   sName,
				   degree;

	public String toString(){
		return uid + ": " + sName + ", " + fName + " : " + degree; 
	}
	
	public String name(){
		return fName + " " + sName;
	}
	
	public String id(){
		return uid;
	}
	
	public String firstName(){
		return fName;
	}
	public String lastName(){
		return sName;
	}
	public String degree(){
		return degree;
	}

	
	//Generated getters and setters
	public String getUid() {
		return uid;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	public String getSName() {
		return sName;
	}
	public void setSName(String sName) {
		this.sName = sName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	
}
