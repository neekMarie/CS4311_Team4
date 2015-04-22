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
 * modified: Crisel Blanco 3/23/2015
 */
public class AirportParser
{
	private String DEMOairportsXML;
				
	public AirportParser()
	{
		DEMOairportsXML = ""+
		"<?xml version='1.0'?>"+
		"<data>"+
			"<airport>"+
				"<name>El Paso International Airport</name>"+
				"<operationalHours>2400</operationalHours>"+
				"<closedRunway> 8R/26L </closedRunway>"+
				"<waypoint> 1 </waypoint>"+
			"</airport>"+
			"<airport>"+
				"<name>Dallas/Fort Worth International Airport</name>"+
				"<operationalHours>2400</operationalHours>"+
				"<closedRunway>13R/31L</closedRunway>"+
				"<waypoint> 1 </waypoint>"+
			"</airport>"+
			"<airport>"+
				"<name>Dallas Love Field</name>"+
				"<operationalHours>2400</operationalHours>"+
				"<closedRunway>13R/31L</closedRunway>"+
				"<waypoint> 1 </waypoint>"+
			"</airport>"+
			"<airport>"+
				"<name>Midland International Airport</name>"+
				"<operationalHours>2400</operationalHours>"+
				"<closedRunway>16L/34R</closedRunway>"+
				"<waypoint> 1 </waypoint>"+
			"</airport>"+
			"<airport>"+
				"<name>San Antonio International Airport</name>"+
				"<operationalHours>2400</operationalHours>"+
				"<closedRunway>4/22</closedRunway>"+
				"<waypoint> 1 </waypoint>"+
			"</airport>"+
		"</data>";
	}
	
	/**
	 * gets a list with all the airports
	 * @return List<AIRPORT> list of airport objects
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<AIRPORT> getAirports() throws SAXException, IOException, ParserConfigurationException
	{
		
		List<AIRPORT> airports = new ArrayList<AIRPORT>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(DEMOairportsXML));
		
		Document doc = db.parse(is);
		NodeList airportList = doc.getElementsByTagName("airport");
		
		for(int i = 0; i < airportList.getLength(); i++)
		{
			Element airport = (Element) airportList.item(i);
			String currentName = airport.getElementsByTagName("name").item(0).getTextContent();
			int operationalHours = Integer.parseInt(airport.getElementsByTagName("operationalHours").item(0).getTextContent());
			String closedRunway = airport.getElementsByTagName("closedRunway").item(0).getTextContent();
			String waypointID = airport.getElementsByTagName("waypoint").item(0).getTextContent();
			
			airports.add(new AIRPORT(currentName, operationalHours, closedRunway, waypointID));
		}
		
		return airports;
	}
	

}
