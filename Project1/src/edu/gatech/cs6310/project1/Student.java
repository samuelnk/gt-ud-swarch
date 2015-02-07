package edu.gatech.cs6310.project1;

import java.util.List;

public class Student {
	
	int id;
	List<Integer> list;

	public void addSchedule(List<Integer> n) 
	{
		list = n;
		
	}

	public void setId(int i) {
		// TODO Auto-generated method stub
		id = i;
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public List<Integer> getSchedule() 
	{
		return list;
	}

}
