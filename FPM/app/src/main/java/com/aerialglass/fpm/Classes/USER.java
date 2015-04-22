package com.aerialglass.fpm.Classes;

public class USER 
{
	private String name = "";
	private String lastName = "";
	
	public USER(String nName, String NlastName)
	{
		name = nName;
		lastName = NlastName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLastName()
	{
		return lastName;
	}
}
