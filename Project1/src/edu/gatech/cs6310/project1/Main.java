package edu.gatech.cs6310.project1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//import java.io.File;


public class Main 
{
	static ArrayList<List<Integer>> studentlist;
	static ArrayList<List<String>> courselist; 
	static ArrayList<List<String>> semesterlist;
	
	 public static void main(String[] args) throws IOException 
	 {
		 String filepath = "resources/student_schedule.txt";
		 String coursefile = "resources/course_desc.txt";
		 String semesterfile = "resources/semester_desc.txt";
		 HashSet<Student> students;
		 HashSet<Course> courses;
		 HashSet<Semester> semesters;
		 //Student student;
		 
		 
		 LP lp = new LP();

		 studentlist = lp.processRawFile(filepath);
		 courselist = lp.processCourseFile(coursefile);
		 semesterlist = lp.processSemesterFile(semesterfile);
		 
		 //lp.printlist(semesterlist);
		
		 students = lp.createStudents(studentlist);
		 courses = lp.createCourses(courselist);
		 semesters = lp.createSemesters(semesterlist);
		 
		 lp.oneCourseConstrain(students,courses,semesters);
		 lp.coursesPerSemesterConstrain(students,courses,semesters);
		 lp.studentsPerCourseConstrain(students,courses,semesters);
		 lp.prereqConstrain(students,courses,semesters);
		 lp.binaryStatement();
		 
		 System.out.println("CPLEX file saved in resources/students.lp");
		 
		 //lp.runGurobi();
		 lp.model();
		 System.out.println("------------------------------------------------------------");
		 System.out.println("Solution output to file resources/model.sol");
		 
		 
	 }
	
}