package edu.gatech.cs6310.project1;

public class Semester 
{
	String id;
	String name;
	String courselist;

	public void setId(String string) 
	{
		id = string;		
	}

	public void setName(String string) 
	{
		name = string;		
	}

	public void setCourseList(String string) 
	{
		courselist = string;
		
	}

	public String getName() 
	{
		return name;
	}
	
	public String getCourseList() 
	{
		return courselist;
	}

	public String getId() 
	{
		return id;
	}

}
