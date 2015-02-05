package edu.gatech.cs6310.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LP 
{
	
	public LP()
	{
		//
	}
	
	public ArrayList<List<Integer>> processRawFile(String filepath)
	{
		ArrayList<List<Integer>> studentSchedule = new ArrayList<List<Integer>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			//new FileReader("resources/student_schedule.txt"));					
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("%")) {
					continue;
				}

				line = line.replace(".", "");
				line = line.trim();

				String[] tokens = line.split(" ");

				List<Integer> list = new ArrayList<Integer>();
				for (String token : tokens) 
				{
					if (token.equals(""))
						continue;
					list.add(Integer.parseInt(token));
				}
				if (!list.isEmpty()) 
				{
					studentSchedule.add(list);					
				}
					
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentSchedule;
	}

	public ArrayList<List<String>> processCourseFile(String coursefile) {
		// TODO Auto-generated method stub
		ArrayList<List<String>> courses = new ArrayList<List<String>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(coursefile));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				line = line.trim();
				

				String[] tokens = line.split(";");

				List<String> list = new ArrayList<String>();
				for (String token : tokens) 
				{
					token.trim();
					//System.out.println(token);
					//if (token.equals(""))
						//continue;
					list.add(token);
				}
				if (!list.isEmpty()) 
				{
					courses.add(list);					
				}
					
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}


	public void printlist(ArrayList<List<String>> courses) 
	{
		// TODO Auto-generated method stub
		for (List<String> s : courses)
		{
			System.out.println(s);
		}
		
	}

	public ArrayList<List<String>> processSemesterFile(String semesterfile) {
		// TODO Auto-generated method stub
		ArrayList<List<String>> semesters = new ArrayList<List<String>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(semesterfile));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				line = line.trim();
				

				String[] tokens = line.split(":");

				List<String> list = new ArrayList<String>();
				for (String token : tokens) 
				{
					token.trim();
					//System.out.println(token);
					//if (token.equals(""))
						//continue;
					list.add(token);
				}
				if (!list.isEmpty()) 
				{
					semesters.add(list);					
				}
					
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return semesters;
	}

	public HashSet<Student> createStudents(ArrayList<List<Integer>> studentlist) {
		// TODO Auto-generated method stub
		HashSet<Student> students = new HashSet<Student>();
		int i = 1;
		for (List<Integer> n : studentlist)
		{			
			Student student = new Student();
			student.addSchedule(n);
			student.setId(i);
			students.add(student);
			i++;
			//System.out.println(n);
		 }
		
		 for (Student s : students)
		 {
			 //System.out.println(s.getId());
		 }
		return students;
	}

	public HashSet<Course> createCourses(ArrayList<List<String>> courselist) 
	{
		HashSet<Course> courses = new HashSet<Course>();
		for (List<String> n : courselist)
		{			
			Course course = new Course();
			course.setId(n.get(0));
			course.setName(n.get(1));
			course.setPrereq(n.get(2));
			courses.add(course);
		 }
		
		 for (Course s : courses)
		 {
			 //System.out.println(s.getId());
		 }
		return courses;
	}

	public HashSet<Semester> createSemesters(ArrayList<List<String>> semesterlist) 
	{
		HashSet<Semester> semesters = new HashSet<Semester>();
		for (List<String> n : semesterlist)
		{			
			Semester semester = new Semester();
			semester.setId(n.get(0));
			semester.setName(n.get(1));
			semester.setCourseList(n.get(2));
			semesters.add(semester);
		 }
		
		 for (Semester s : semesters)
		 {
			 //System.out.println(s.getName());
			 //System.out.println(s.getCourseList());
		 }
		return semesters;
	}
	
	

}
