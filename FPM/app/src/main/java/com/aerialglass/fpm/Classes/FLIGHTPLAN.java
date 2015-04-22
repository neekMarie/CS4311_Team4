package com.aerialglass.fpm.Classes;

import java.util.Date;

/**
 * flightplan composed of a name, date, and route
 * @author AERIAL GLASS
 *
 * Modified by Victor on 3/24/2015
 */
public abstract class FLIGHTPLAN 
{
	private String name;
	private Date date;
	private WAYPOINT currentWaypoint;
	private int currentWaypointIndex;
	private ROUTE route;
	
	public FLIGHTPLAN(String nName, Date nDate, ROUTE nRoute)
	{
		name = nName;
		date = nDate;
		route = nRoute;
		currentWaypointIndex = 0;
		currentWaypoint = route.getWaypoint(currentWaypointIndex);
	}
	
	public void moveToNextWaypoint()
	{
		//currentWaypointIndex++;
		//currentWaypoint = route.getWaypoint(currentWaypointIndex);
        route.removeWaypoint(currentWaypoint);

        if (route.getWaypoints().size() > 0) {
            currentWaypoint = route.getWaypoint(0);
        }
	}
	
	public ROUTE getRoute()
	{
		return route;
	}
	
	public void setRoute(ROUTE nRoute)
	{
		route = nRoute;
	}
	
	public WAYPOINT getCurrentWaypoint()
	{
		return currentWaypoint;
	}
	
	public abstract void generateFlightplan();
}
