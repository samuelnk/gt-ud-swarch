package edu.gatech.cs6310.project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public HashSet<Student> createStudents(ArrayList<List<Integer>> studentlist) 
	{
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

	public void oneCourseConstrain(HashSet<Student> students, HashSet<Course> courses, HashSet<Semester> semesters) throws IOException
	{
		String outfile = "resources/students.lp";
		List<String> list = new ArrayList<String>();
		String delims = "[ ,]+";
		
		FileWriter fstream = new FileWriter(outfile);
		BufferedWriter out = new BufferedWriter(fstream);
		
		out.write("Min X");
		out.newLine();
		out.write("Subject To");
		out.newLine();
		
		for (Student s : students)
		 {
			for (Course c : courses)
			{
				String temp = "";
				//System.out.println("Course:" + c.getId() + ":");
				for (Semester t : semesters)
				{
					String u = t.getCourseList();
					u.trim();
					String[] tokens = u.split(delims);
					for (String token : tokens)
					{
						token.trim();
						//token.replaceAll("^\\s+|\\s+$", "");
						list.add(token);
						//System.out.println(":" + token + ":");
					}
					if (list.contains(c.getId().trim()))
					{
						if (temp != "")
						{
							temp = temp + " + ";
						}
						temp = temp + "S" + s.getId() + "_C" + c.getId().trim() + "_T" + t.getId().trim();
						//System.out.println(t.getName() + ": " + c.getId() + ": " + c.getName());
						//System.out.println("S" + s.getId() + "_C" + c.getId() + "_T" + t.getId() + " + ");
					}
					//System.out.println(u);
					list.clear();
				}
				temp = temp + " = 1";
				//System.out.println(temp);
				out.write(temp);
				out.newLine();
				
			}
			//return;
			 
			 
		 }
		out.close();
		
	}

	public void coursesPerSemesterConstrain(HashSet<Student> students, HashSet<Course> courses, HashSet<Semester> semesters)  throws IOException
	{
		String outfile = "resources/students.lp";
		FileWriter fstream = new FileWriter(outfile, true);
		BufferedWriter out = new BufferedWriter(fstream);
		
		List<String> list = new ArrayList<String>();
		String delims = "[ ,]+";
		
		for (Student s : students)
		 {
			for (Semester t : semesters)
			{
				String temp = "";
				//System.out.println("Course:" + c.getId() + ":");
				for (Course c : courses)
				{
					String u = t.getCourseList();
					u.trim();
					String[] tokens = u.split(delims);
					for (String token : tokens)
					{
						token.trim();
						//token.replaceAll("^\\s+|\\s+$", "");
						list.add(token);
						//System.out.println(":" + token + ":");
					}
					if (list.contains(c.getId().trim()))
					{
						if (temp != "")
						{
							temp = temp + " + ";
						}
						temp = temp + "S" + s.getId() + "_C" + c.getId().trim() + "_T" + t.getId().trim();
						//System.out.println(t.getName() + ": " + c.getId() + ": " + c.getName());
						//System.out.println("S" + s.getId() + "_C" + c.getId() + "_T" + t.getId() + " + ");
					}
					//System.out.println(u);
					list.clear();
				}
				temp = temp + " <= 2";
				//System.out.println(temp);
				out.write(temp);
				out.newLine();
				
			}
			//return;			 
			//
		 }
		out.close();	
	}

	public void studentsPerCourseConstrain(HashSet<Student> students,HashSet<Course> courses, HashSet<Semester> semesters) throws IOException
	{
		String outfile = "resources/students.lp";
		FileWriter fstream = new FileWriter(outfile, true);
		BufferedWriter out = new BufferedWriter(fstream);
		
		boolean track = false;
		List<String> list = new ArrayList<String>();
		String delims = "[ ,]+";
		
		for (Semester t : semesters)
		{

			for (Course c : courses)
			{
				String temp = "";
				String ctemp = t.getCourseList();
				ctemp.trim();
				String[] tokens = ctemp.split(delims);
				for (String token : tokens)
				{
					token.trim();
					list.add(token);
				}
				
				if (list.contains(c.getId().trim()))
				{
					//System.out.println("Course: " + c.getId().trim() + " is offered in Semester: " + t.getId());
					for (Student s : students)
					{
						//track = false;
						
						List<Integer> templist = new ArrayList<Integer>();
						List<String> mycourses = new ArrayList<String>();
						templist = s.getSchedule();
						
						for (int tmp : templist)
						{
							mycourses.add(Integer.toString(tmp));
						}
						
						if (mycourses.contains((c.getId().trim())))
						{
							//System.out.println("Student: " + s.getId() + " can take class: " + c.getId().trim());
							track = true;
							
							if (temp != "")
							{
								temp = temp + " + ";
							}
							temp = temp + "S" + s.getId() + "_C" + c.getId().trim() + "_T" + t.getId().trim();
							//System.out.println(t.getName() + ": " + c.getId() + ": " + c.getName());
							//System.out.println("S" + s.getId() + "_C" + c.getId() + "_T" + t.getId() + " + ");
						}
					}//end students
					if (track)
					{
					temp = temp + " - X <= 0";
					//System.out.println(temp);
					out.write(temp);
					out.newLine();
					}
					//return;
				} //end if
				list.clear();
			} //end courses
			//return;
		} //end semester
		out.close();
	}

	public void prereqConstrain(HashSet<Student> students, HashSet<Course> courses, HashSet<Semester> semesters) throws IOException 
	{
		String outfile = "resources/students.lp";
		FileWriter fstream = new FileWriter(outfile, true);
		BufferedWriter out = new BufferedWriter(fstream);
		
		List<String> list = new ArrayList<String>();
		String delims = "[ ,]+";
		
		for (Student s : students)
		 {
			for (Course c : courses)
			{
				int m = 2;
				int t = 2;
				String temp = "";
				
				if (!c.getPrereq().trim().equals("0"))
				{
					//System.out.println(c.getId() + " : |" + c.getPrereq().trim() + "|");
					while (m <= 12)
					{
						t = m;
						temp = "";
						
						while (t >= 1)
						{
							//int ttemp = t-1;
							
							if (temp != "")
							{
								temp = temp + " + ";
							}
							
							temp = temp + "S" + s.getId() + "_C" + c.getId().trim() + "_T" + t + " - " + "S" + s.getId() + "_C" + c.getPrereq().trim() + "_T" + t;
							t = t-1;
						}
						temp = temp + " <= 0";
						//System.out.println(temp);
						out.write(temp);
						out.newLine();
						m++;						
					}
					
					
				}
								
			}
			//return;
		 }
		out.close();		
	}

	public void binaryStatement() throws IOException
	{
		String outfile = "resources/students.lp";
		FileWriter fstream = new FileWriter(outfile, true);
		BufferedWriter out = new BufferedWriter(fstream);
		
		out.write("Binary");
		out.newLine();
		
		for(int s = 1; s <= 600; s = s+1)
		{
			for(int c = 1; c <= 18; c = c+1)
			{
				for(int t = 1; t <= 12; t = t+1)
				{
					//System.out.println("S" + s + "_C" + c + "_T" + t);
					out.write("S" + s + "_C" + c + "_T" + t);
					out.newLine();

				}
			}
		}
		out.write("end");
		out.newLine();
		out.close();
	}

	public void runGurobi() throws IOException 
	{
		//Runtime.getRuntime().exec("/opt/gurobi600/linux64/bin/gurobi_cl ResultFile=resources/output.sol resources/students.lp");
		
		String[] cmd = {"/opt/gurobi600/linux64/bin/gurobi_cl", "ResultFile=/home/ubuntu/workspace/Project1/resources/output.sol", "/home/ubuntu/workspace/Project1/resources/students.lp"};
		Runtime.getRuntime().exec(cmd);
		try {
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	

}
