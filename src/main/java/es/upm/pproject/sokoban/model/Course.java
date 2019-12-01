package es.upm.pproject.sokoban.model;

import java.util.LinkedList;
import java.util.List;


public class Course {
    private int code;
    private String name;
    private String coordinator;

    private List<Integer> enrolledStudents;


    public Course(int code, String name, String coordinator) {
    	this.code = code;
    	this.name= name;
    	this.coordinator = coordinator;
    	this.enrolledStudents = new LinkedList<>();

    }

    public int getCode() {
        return code;
    }
    
    List<Integer> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Integer studentID) {
        enrolledStudents.add(studentID);
    }
    
    public void unEnrollStudent(Integer studentID) {
    	enrolledStudents.remove(studentID);
    }
    
    public boolean isEnrolledInCourse(Integer studentID) {
    	return enrolledStudents.contains(studentID);
    }
    
    public void restartCourse() {
    	enrolledStudents = new LinkedList<>(); 
    }
    
    public int courseSize() {
    	return getEnrolledStudents().size(); 
    }
    
}
