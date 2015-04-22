package com.aerialglass.fpm.Activities;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aerialglass.fpm.Classes.AIRPORT;
import com.aerialglass.fpm.Classes.CUSTOM_FLIGHTPLAN;
import com.aerialglass.fpm.Classes.FLIGHTPLAN;
import com.aerialglass.fpm.Classes.ROUTE;
import com.aerialglass.fpm.Classes.WAYPOINT;
import com.aerialglass.fpm.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private FLIGHTPLAN demoFlightPlan;
    private double currentLatitude = 0;
    private double currentLongitude = 0;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setUpMapIfNeeded();
        setUpMap();

        createDemoFlightPlan();
        placeFlightPlan();


//        monitor();
    }

    public void monitor(View v)
    {
        currentLatitude = Double.parseDouble(demoFlightPlan.getCurrentWaypoint().getLatitude());
        currentLongitude = Double.parseDouble(demoFlightPlan.getCurrentWaypoint().getLongitude());
//        currentLatitude = currentLatitude + 0.5;  //every  time change this so that it gets closer to the destination
//        currentLongitude = currentLongitude - 5;  //every  time change this so that it gets closer to the destination
        demoFlightPlan.moveToNextWaypoint();  //call this every time the current location reaches the current waypoint
                                                //maybe change the current location 5 times, then call this method
                                                //just make sure the progress looks normal, sort of real


        //below this line leave alone
        placeFlightPlan();
        if (demoFlightPlan.getRoute().getWaypoints().size() == 0)
        {
            toast("DESTINATION REACHED");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
            setUpMap();
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    private void createDemoFlightPlan() {
        String demoName = "DEMO FLIGHT PLAN";
        Date demoDate = new Date(); //gives current date

        AIRPORT demoDeparture = new AIRPORT("ELP-El Paso International Airport", 1, "X", "1");
        demoDeparture.setWaypoint(new WAYPOINT("1", "31.8", "-106.366667"));

        currentLatitude = Double.parseDouble(demoDeparture.getWaypoint().getLatitude());
        currentLongitude = Double.parseDouble(demoDeparture.getWaypoint().getLongitude());

        AIRPORT demoDestination = new AIRPORT("SFO-San Francisco International Airport", 1, "X", "1");
        demoDestination.setWaypoint(new WAYPOINT("2", "37.616667", "-122.366667"));

        ROUTE demoRoute = new ROUTE(demoDeparture, demoDestination);

        //fill in wayppoints with data that makes sense

//        demoRoute.addWaypoint(new WAYPOINT("3", "1", "1"));
//        demoRoute.addWaypoint(new WAYPOINT("4", "1", "1"));
//        demoRoute.addWaypoint(new WAYPOINT("5", "1", "1"));
        demoRoute.addWaypoint(new WAYPOINT("5", "32.951314", "-109.454495"));
        demoRoute.addWaypoint(new WAYPOINT("6", "33.447144", "-112.068746"));
        demoRoute.addWaypoint(new WAYPOINT("7", "33.636358", "-116.163634"));
        demoRoute.addWaypoint(new WAYPOINT("8", "34.391023", "-118.545272"));
        demoRoute.addWaypoint(new WAYPOINT("9", "36.776017", "-119.717837"));
        demoRoute.addWaypoint(new WAYPOINT("10", "37.639285", " -120.997033"));
//        demoRoute.addWaypoint(new WAYPOINT("10", "1", "1"));

        demoFlightPlan = new CUSTOM_FLIGHTPLAN(demoName, demoDate, demoRoute);
    }

    private void placeDeparture(AIRPORT airport)
    {
        double latitude = Double.parseDouble(airport.getWaypoint().getLatitude());
        double longitude = Double.parseDouble(airport.getWaypoint().getLongitude());

        final LatLng MELBOURNE = new LatLng(latitude, longitude);
        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.stoplight)));
    }

    private void placeDestination(AIRPORT airport)
    {
        double latitude = Double.parseDouble(airport.getWaypoint().getLatitude());
        double longitude = Double.parseDouble(airport.getWaypoint().getLongitude());

        final LatLng MELBOURNE = new LatLng(latitude, longitude);
        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.finishflagmedium)));
    }

    private void placeCurrentLocation(double latitude, double longitude)
    {
        final LatLng MELBOURNE = new LatLng(latitude, longitude);
        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.planesmallest)));
    }

    private void placeMarker(WAYPOINT waypoint)
    {
        double latitude = Double.parseDouble(waypoint.getLatitude());
        double longitude = Double.parseDouble(waypoint.getLongitude());

        final LatLng MELBOURNE = new LatLng(latitude, longitude);
        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.greenflagmedium)));

//        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(waypoint.getID()));
    }

    private void placeFlightPlan()
    {
        mMap.clear();
        placeDeparture(demoFlightPlan.getRoute().getDepartureAirport());

        placeCurrentLocation(currentLatitude, currentLongitude);

        List<WAYPOINT> waypoints = demoFlightPlan.getRoute().getWaypoints();
        for(int i = 0; i < waypoints.size(); i++)
        {
            placeMarker(waypoints.get(i));
        }
        placeDestination(demoFlightPlan.getRoute().getDestinationAirport());
    }

    private void setUpMap() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(31.767672, -106.502183)).title("Marker"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(19, 99)).title("Marker2"));
      //  mMap.addMarker(new MarkerOptions().position(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude())).title("Location"));
    }

    public void toast(String message)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,message,duration);
        toast.show();
    }

    private void pause2()
    {
        Thread closeActivity = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    // Do some stuff
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });
    }

    private void pause()
    {
        isPaused = true;
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                toast("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                toast("done!");
                isPaused = false;
            }
        }.start();
    }
}
