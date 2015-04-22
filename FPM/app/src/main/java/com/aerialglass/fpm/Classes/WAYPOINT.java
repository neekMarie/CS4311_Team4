package com.aerialglass.fpm.Classes;

/**
 * object representing a waypoint
 * waypoints consist of an ID, latitude, and Longitude
 * @author AERIAL GLASS
 * 
 * modified: Victor Noe 3/9/2015
 */
public class WAYPOINT 
{
	private String id;
	private String latitude;
	private String longitude;
	
	public WAYPOINT(String nID, String nLatitude, String nLongitude)
	{
		id = nID;
		latitude = nLatitude;
		longitude = nLongitude;
	}
	
	public String getID()
	{
		return id;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public String toString()
	{
		return "Latitude: " + latitude + "\n" + "Longitude: " + longitude;
	}
}
