package com.aerialglass.fpm.Classes;

/**
 * Provide aircraft's attributes.
 * Aircraft consists of color, id, model, name, normal cruising altitude and speed, maximum cruising
 * altitude and speed, minimum cruising altitude and speed, fuel consumption, and fuel consumption
 * rate.
 * @author AERIAL GLASS
 *
 * Modified by Arik on 3/15/2015.
 */
public class Aircraft {

    private String id;
    private String name;
    private String model;
    private String color;
    private int maxAltitude;
    private int maxSpeed;
    private int minAltitude;
    private int minSpeed;
    private int normAltitude;
    private int normSpeed;
    private int fuelConsumption;
    private int fuelConsumRate;

    //The values for aircraft are gotten from the AircraftCharacteristics.xml file.
    public Aircraft(String aircraftID) {
        id = aircraftID;
        //setName(AircraftParser.getName());
        //setModel(AircraftParser.getModel());
        //setColor(AircraftParser.getColor());
        //setMaxAltitude(AircraftParser.getMaxAltitude());
        //setMaxSpeed(AircraftParser.getMaxSpeed());
        //setMinAltitude(AircraftParser.getMinAltitude());
        //setMinSpeed(AircraftParser.getMinSpeed());
        //setNormAltitude(AircraftParser.getNormAltitude());
        //setNormSpeed(AircraftParser.getNormSpeed());
        //setFuelConsumRate(AircraftParser.getFuelConsumRate());
        //setFuelConsumption(timeInFlight);
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinAltitude() {
        return minAltitude;
    }

    public void setMinAltitude(int minAltitude) {
        this.minAltitude = minAltitude;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getNormAltitude() {
        return normAltitude;
    }

    public void setNormAltitude(int normAltitude) {
        this.normAltitude = normAltitude;
    }

    public int getNormSpeed() {
        return normSpeed;
    }

    public void setNormSpeed(int normSpeed) {
        this.normSpeed = normSpeed;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int timeInFlight) {
        this.fuelConsumption = timeInFlight * fuelConsumRate;   //Formula to calculate fuel consumption.
    }

    public int getFuelConsumRate() {
        return fuelConsumRate;
    }

    public void setFuelConsumRate(int fuelConsumRate) {
        this.fuelConsumRate = fuelConsumRate;
    }
}
