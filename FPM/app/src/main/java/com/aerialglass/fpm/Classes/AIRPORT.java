package com.aerialglass.fpm.Classes;

/**
 * object representing an airport
 * airports consist of a name, operational hours, and closed runways
 * @author AERIAL GLASS
 * 
 * modified: Crisel Blanco 3/23/2015
 */
public class AIRPORT 
{
	private String name;
	private int operationalHours;
	private String closedRunway;
	private String waypointID;
	private WAYPOINT waypoint;
	
	public AIRPORT(String nName, int nOpHours, String nRunway, String nWaypoint)
	{
		name = nName;
		operationalHours = nOpHours;
		closedRunway = nRunway;
		waypointID = nWaypoint;
		
		waypoint = new WAYPOINT(waypointID, "1", "1");
	}
	
	public void setWaypoint(WAYPOINT nWaypoint)
	{
		waypoint = nWaypoint;
	}
	
	public WAYPOINT getWaypoint()
	{
		return waypoint;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getOperationalHours()
	{
		return operationalHours;
	}
	
	public String getClosedRunways()
	{
		return closedRunway;
	}
}
