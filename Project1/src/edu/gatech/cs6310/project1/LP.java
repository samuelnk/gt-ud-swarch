package edu.gatech.cs6310.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LP 
{
	String filename;
	
	public LP()
	{
		filename = null;
	}

	public void setfilename(String filepath) 
	{
		// TODO Auto-generated method stub
		filename = filepath;
		//System.out.println("");
		
	}
	
	public ArrayList<List<Integer>> processRawFile()
	{
		ArrayList<List<Integer>> studentSchedule = new ArrayList<List<Integer>>();
		try {
			BufferedReader reader = new BufferedReader(
				//new FileReader("resources/student_schedule.txt"));
					new FileReader(filename));
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
	
	

}
