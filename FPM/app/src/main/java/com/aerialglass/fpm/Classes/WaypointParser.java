package com.aerialglass.fpm.Classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * demo parser
 * @author AERIAL GLASS
 * 
 * modified: Victor Noe 3/9/2015
 */
public class WaypointParser 
{
	private String DEMOwaypointsXML = ""+
		"<?xml version='1.0'?>"+
		"<data>"+
			"<waypoint>"+
				"<id>1</id>"+
				"<latitude>123</latitude>"+
				"<longitude>124</longitude>"+
			"</waypoint>"+
			"<waypoint>"+
				"<id>2</id>"+
				"<latitude>23</latitude>"+
				"<longitude>57</longitude>"+
			"</waypoint>"+
			"<waypoint>"+
				"<id>3</id>"+
				"<latitude>23re</latitude>"+
				"<longitude>23432</longitude>"+
			"</waypoint>"+
			"<waypoint>"+
				"<id>4</id>"+
				"<latitude>2r23</latitude>"+
				"<longitude>fwsf</longitude>"+
			"</waypoint>"+
			"<waypoint>"+
				"<id>5</id>"+
				"<latitude>23rf</latitude>"+
				"<longitude>f345</longitude>"+
			"</waypoint>"+
		"</data>";
				
	public WaypointParser()
	{
		//for now do nothing
	}
	
	/**
	 * gets single waypoint according to ID
	 * @param id ID of required waypoint
	 * @return WAYPOINT object
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public WAYPOINT getWaypoint(String id) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(DEMOwaypointsXML));

		Document doc = db.parse(is);
		NodeList waypointList = doc.getElementsByTagName("waypoint");
		
		for (int i = 0; i < waypointList.getLength(); i++)
		{
			Element waypoint = (Element) waypointList.item(i);
			NodeList idList = doc.getElementsByTagName("id");
			String currentID = idList.item(0).getTextContent();
			
			if (currentID.equals(id))
			{
				NodeList latitudeList = doc.getElementsByTagName("latitude");
				String latitude = idList.item(0).getTextContent();
				NodeList longitudeList = doc.getElementsByTagName("longitude");
				String longitude = idList.item(0).getTextContent();
				
				WAYPOINT selectedWaypoint = new WAYPOINT(id, latitude, longitude);
				return selectedWaypoint;
			}
		}
		return null;
	}
	
	/**
	 * gets a list with all the waypoints
	 * @return List<WAYPOINT> list of waypoint objects
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<WAYPOINT> getWaypoints() throws SAXException, IOException, ParserConfigurationException
	{
		List<WAYPOINT> waypoints = new ArrayList<WAYPOINT>();
		List<String> waypointIDs = getWapointIDs();
		
		for(int i = 0; i < waypointIDs.size(); i++)
		{
			waypoints.add(getWaypoint(waypointIDs.get(i)));
		}
		return waypoints;
	}
	
	/**
	 * only used by getWaypoints()
	 * @return String list containing all the IDs for all the waypoints
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private List<String> getWapointIDs() throws SAXException, IOException, ParserConfigurationException
	{
		List<String> waypointIDs = new ArrayList<String>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(DEMOwaypointsXML));

		Document doc = db.parse(is);
		NodeList waypointList = doc.getElementsByTagName("waypoint");
		
		for (int i = 0; i < waypointList.getLength(); i++)
		{
			Element waypoint = (Element) waypointList.item(i);
			NodeList idList = doc.getElementsByTagName("id");
			String currentID = idList.item(0).getTextContent();
			waypointIDs.add(currentID);
		}
		return waypointIDs;
	}
	
	/**
	 * this is only in case we need to rewrite the xml
	 * for now it is not used
	 * @param doc
	 * @return
	 */
	private String getStringFromDoc(org.w3c.dom.Document doc)
	{
		DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
		LSSerializer lsSerializer = domImplementation.createLSSerializer();
		return lsSerializer.writeToString(doc);   
	}
}
