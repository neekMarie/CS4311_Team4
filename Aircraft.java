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
    private Integer maxAltitude;
    private Integer maxSpeed;
    private Integer minAltitude;
    private Integer minSpeed;
    private Integer normAltitude;
    private Integer normSpeed;
    private Integer fuelConsumption;
    private Integer fuelConsumRate;

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

    public Integer getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(Integer maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getMinAltitude() {
        return minAltitude;
    }

    public void setMinAltitude(Integer minAltitude) {
        this.minAltitude = minAltitude;
    }

    public Integer getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Integer minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Integer getNormAltitude() {
        return normAltitude;
    }

    public void setNormAltitude(Integer normAltitude) {
        this.normAltitude = normAltitude;
    }

    public Integer getNormSpeed() {
        return normSpeed;
    }

    public void setNormSpeed(Integer normSpeed) {
        this.normSpeed = normSpeed;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer timeInFlight) {
        this.fuelConsumption = timeInFlight * fuelConsumRate;   //Formula to calculate fuel consumption.
    }

    public Integer getFuelConsumRate() {
        return fuelConsumRate;
    }

    public void setFuelConsumRate(Integer fuelConsumRate) {
        this.fuelConsumRate = fuelConsumRate;
    }
}
