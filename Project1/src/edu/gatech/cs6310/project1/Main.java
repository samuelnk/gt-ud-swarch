package edu.gatech.cs6310.project1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//import java.io.File;


public class Main 
{
	static ArrayList<List<Integer>> studentSchedule;
	
	 public static void main(String[] args) 
	 {
		 String filepath = "resources/student_schedule.txt";
		 HashSet<Student> students;
		 Student student;
		 
		 
		 LP lp = new LP();
		 lp.setfilename(filepath);
		 studentSchedule = lp.processRawFile();
		 
		 students = new HashSet<Student>();
		 
		 int i = 1;
		 for (List<Integer> n : studentSchedule)
		 {
			 student = new Student();
			 student.addSchedule(n);
			 student.setId(i);
			 students.add(student);
			 i++;
			 //System.out.println(n);
		 }
		 
		 for (Student s : students)
		 {
			 System.out.println(s.getId());
		 }
		 
		 
		 
	 }
	
}