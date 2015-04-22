package com.aerialglass.fpm.Classes;

import java.util.Date;
import java.util.List;

/**
 * custom flightplan composed of a name, date, and route
 * @author AERIAL GLASS
 *
 * Modified by Victor on 3/24/2015
 */
public class CUSTOM_FLIGHTPLAN extends FLIGHTPLAN
{
	
	private ROUTE tempRoute;

	public CUSTOM_FLIGHTPLAN(String nName, Date nDate, ROUTE nRoute) {
		super(nName, nDate, nRoute);
		// TODO Auto-generated constructor stub
		
		tempRoute = new ROUTE();
	}

	@Override
	public void generateFlightplan() {
		// TODO Auto-generated method stub
		setRoute(tempRoute);
	}
	
	public void setDepartureAirport(AIRPORT departure)
	{
		tempRoute.setDepartureAirport(departure);
	}
	
	public void setDestinationAirport(AIRPORT destination)
	{
		tempRoute.setDestinationAirport(destination);
	}
	
	public void addWaypoint(WAYPOINT waypoint)
	{
		tempRoute.addWaypoint(waypoint);
	}
	
	public void removeWaypoint(String id)
	{
		tempRoute.removeWaypoint(id);
	}
	
	public void setWaypoints(List<WAYPOINT> waypoints)
	{
		tempRoute.setWaypoints(waypoints);
	}

}
