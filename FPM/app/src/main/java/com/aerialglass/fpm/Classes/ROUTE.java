package com.aerialglass.fpm.Classes;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection of waypoints with a departure and a destination airport.
 * @author AERIAL GLASS
 *
 * Modified by Arik on 3/24/2015.
 * Modified by Victor on 3/24/2015
 */
public class ROUTE {

    private AIRPORT departureAirport = null;
    private List<WAYPOINT> waypoints = new ArrayList<WAYPOINT>();
    private AIRPORT destinationAirport = null;

    public ROUTE(AIRPORT rDepartureAirport, List<WAYPOINT> rWaypoints, AIRPORT rDestinationAirport) 
    {
        departureAirport = rDepartureAirport;
        destinationAirport = rDestinationAirport;
        waypoints = rWaypoints;
    }
    
    public ROUTE()
    {
    	//can have blank route temporarily
    }
    
    public ROUTE(AIRPORT departure, AIRPORT destination)
    {
    	departureAirport = departure;
    	destinationAirport = destination;
    }
    
    private double getDistanceFromLast(WAYPOINT waypoint)
    {
    	WAYPOINT prevWaypoint = waypoints.get(waypoints.size()-1);
    	
    	double currentLatitude = Double.parseDouble(waypoint.getLatitude());
    	double currentLongitude = Double.parseDouble(waypoint.getLongitude());
    	
    	double prevLatitude = Double.parseDouble(prevWaypoint.getLatitude());
    	double prevLongitude = Double.parseDouble(prevWaypoint.getLongitude());
    	
    	return calculateDistance(prevLatitude, prevLongitude, currentLatitude, currentLongitude);
    }
    
    public double getDistance(WAYPOINT waypoint)
    {
    	int currentWaypointIndex = getWaypointIndex(waypoint);
    	
    	if (waypoints.size() == 0)
    	{
    		return 0;
    	}
    	
    	if (currentWaypointIndex == 0)
    	{
    		return 0;
    	}
    	
    	if (!waypointExists(waypoint))
    	{
    		return getDistanceFromLast(waypoint);
    	}
    	
    	int prevWaypointIndex = currentWaypointIndex - 1;
    	WAYPOINT prevWaypoint = getWaypoint(prevWaypointIndex);
    	
    	double currentLatitude = Double.parseDouble(waypoint.getLatitude());
    	double currentLongitude = Double.parseDouble(waypoint.getLongitude());
    	
    	double prevLatitude = Double.parseDouble(prevWaypoint.getLatitude());
    	double prevLongitude = Double.parseDouble(prevWaypoint.getLongitude());
    	
    	return calculateDistance(prevLatitude, prevLongitude, currentLatitude, currentLongitude);
    }
    
    private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2)
    {
    	return Math.sqrt((Math.pow(latitude2 - latitude1, 2)) + (Math.pow(longitude2 - longitude1, 2)));
    }
    
    public boolean removeWaypoint(WAYPOINT waypoint)
    {
    	if (waypointExists(waypoint))
    	{
    		waypoints.remove(waypoint);
    		return true;
    	}
    	return false;
    }
    
    public void removeWaypoint(String id)
    {
    	WAYPOINT toRemove = getWaypoint(id);
    	waypoints.remove(toRemove);
    }
    
    public WAYPOINT getWaypoint(String id)
    {
    	for(int i = 0; i < waypoints.size(); i++)
    	{
    		if(waypoints.get(i).getID().equals(id))
    		{
    			return waypoints.get(i);
    		}
    	}
    	return null;
    }
    
    public WAYPOINT getWaypoint(int position)
    {
    	return waypoints.get(position);
    }
    
    public int getWaypointIndex(WAYPOINT waypoint)
    {
    	for (int i = 0; i < waypoints.size(); i++)
    	{
    		if (waypoints.get(i) == waypoint)
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    public boolean addWaypoint(WAYPOINT waypoint)
    {
    	if (waypointExists(waypoint))
    	{
    		return false;
    	}
    	waypoints.add(waypoint);
    	return true;
    }
    
    public void setDepartureAirport(AIRPORT departure)
    {
    	departureAirport = departure;
    }
    
    public void setDestinationAirport(AIRPORT destination)
    {
    	destinationAirport = destination;
    }
    
    public void setWaypoints(List<WAYPOINT> rWaypoints)
    {
    	waypoints = rWaypoints;
    }

    public AIRPORT getDepartureAirport() {
        return departureAirport;
    }

    public AIRPORT getDestinationAirport() {
        return destinationAirport;
    }

    public List<WAYPOINT> getWaypoints() {
        return waypoints;
    }
    
    private boolean waypointExists(WAYPOINT waypoint)
    {
    	return waypoints.contains(waypoint);
    }
}
