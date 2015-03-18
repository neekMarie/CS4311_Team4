package com.aerialglass.fpm.Classes;

/**
 * This object represents GPS Data
 * GPS Data consists of
 * @author AERIAL GLASS
 * 
 * modified: Edgar Terrazas 3/12/2015
 */
public class GPS_DATA 
{
	private _Coordinate coordinate;
	private Date currentTime;
	private double ETA; //the estimated time of arrival
	private double distance; //distance to next waypoint
	//TODO validate units of ETA and distance
	//TODO validate purpose of distance
	
	public GPS_DATA(double distance, double x, double y)
	{
		this.distance = distance;
		this.coordinate = _Coordinate(x, y);
		calculateTime();
		calculateETA();
	}
	
	public double getX(){return coordinate.getX();}
	public double getY(){return coordinate.getY();}
	public String currentTimeString
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		return dateFormat.format(this.currentTime);
	}
	public Date getTime()
	{
		return this.currentTime;
	}
	public double getETA()
	{
		return this.ETA
	}
	public double getDistance()
	{
		return this.distance;
	}
	
	/**
	* This private class defines a coordinate object in a 2 dimensional plane
	* TODO may need to refactor this to be its own class within this package
	*/
	private class _Coordinate
	{
		private double x, y;//x and y coordinates
		
		_Coordinate(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		/**
		* Creates a coordinate object using the point (a,a)
		*/
		_Coordinate(double a)
		{
			_Coordinate(a, a);
		}
		
		public double getX()
		{
			return this.x;
		}
		
		public double getY()
		{
			return this.y;
		}
		
		public void setX(double x)
		{
			this.x = x;
		}
		
		public void setY(double y)
		{
			this.y = y;
		}
	}
}