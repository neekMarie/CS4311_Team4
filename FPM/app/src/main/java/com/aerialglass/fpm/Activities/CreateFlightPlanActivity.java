package com.aerialglass.fpm.Activities;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aerialglass.fpm.R;
import com.aerialglass.fpm.Classes.AIRPORT;
import com.aerialglass.fpm.Classes.ROUTE;
import com.aerialglass.fpm.Classes.WAYPOINT;

public class CreateFlightPlanActivity extends ActionBarActivity {
	
	private List<WAYPOINT> demoWaypoints = new ArrayList<WAYPOINT>();
	private AIRPORT selectedDeparture;
    private WAYPOINT selectedWaypoint;
    private AIRPORT selectedDestination;
    private ROUTE route = new ROUTE();
    private List<AIRPORT> demoAirports = new ArrayList<AIRPORT>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_flight_plan);
		
		createDemoAirports();
		createDepartureSpinner();
		createDestinationSpinner();
		
		createDemoWaypoints();
		createWaypointsList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_flight_plan, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void createDemoWaypoints()
    {
        demoWaypoints.add(new WAYPOINT("1","12","21"));
        demoWaypoints.add(new WAYPOINT("2","20","22"));
        demoWaypoints.add(new WAYPOINT("3","30","23"));
        demoWaypoints.add(new WAYPOINT("4","50","24"));
        demoWaypoints.add(new WAYPOINT("5","70","25"));
    }
	
	private void createDemoAirports()
	{
		AIRPORT blankAirport = new AIRPORT(" ", 1, " ", " ");
		blankAirport.setWaypoint(new WAYPOINT(" "," "," "));
		demoAirports.add(blankAirport);

        AIRPORT ep = new AIRPORT("ELP-El Paso International Airport",1,"X", "1");
		demoAirports.add(ep);
        ep.setWaypoint(new WAYPOINT("1", "31.8", "-106.366667"));

		demoAirports.add(new AIRPORT("HOU-William P. Hobby Airport",1,"X", "1"));
		demoAirports.add(new AIRPORT("DEN-Denver International Airport",1,"X", "1"));

        AIRPORT sfo = new AIRPORT("SFO-San Francisco International Airport",1,"X", "1");
        demoAirports.add(sfo);
        sfo.setWaypoint(new WAYPOINT("2", "37.616667", "-122.366667"));


		demoAirports.add(new AIRPORT("SAN-San Diego International Airport",1,"X", "1"));
	}

    private void setSelectedWaypoint(String waypointToString)
    {
        for (int i = 0; i < demoWaypoints.size(); i++)
        {
            if (demoWaypoints.get(i).toString().equals(waypointToString))
            {
                selectedWaypoint = demoWaypoints.get(i);
            }
        }
    }
    
    public void createDestinationSpinner()
	{
		final Spinner sp = (Spinner) findViewById(R.id.spDestination);
		List<String> SpinnerArray = new ArrayList<String>();
		SpinnerArray.add(" ");
		
		for(int i = 0; i < demoAirports.size(); i++)
        {
            SpinnerArray.add(demoAirports.get(i).getName());
        }
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.customview, SpinnerArray);		
		adapter.setDropDownViewResource(R.layout.customview);

		sp.setOnTouchListener(new AdapterView.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					sp.setAdapter(adapter);
					destinationSelectionListener();
				}
				return false;
			}
		});
	}
    
	public void createDepartureSpinner()
	{
		final Spinner sp = (Spinner) findViewById(R.id.spDeparture);
		List<String> SpinnerArray = new ArrayList<String>();
		SpinnerArray.add(" ");
		
		for(int i = 0; i < demoAirports.size(); i++)
        {
            SpinnerArray.add(demoAirports.get(i).getName());
        }
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.customview, SpinnerArray);		
		adapter.setDropDownViewResource(R.layout.customview);

		sp.setOnTouchListener(new AdapterView.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					sp.setAdapter(adapter);
					departureSelectionListener();
				}
				return false;
			}
		});
	}
	
	public void destinationSelectionListener()
	{
		final Spinner sp = (Spinner) findViewById(R.id.spDestination);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				
				String selected = sp.getSelectedItem().toString();
				for (int i = 0; i < demoAirports.size(); i++)
				{
					if (demoAirports.get(i).getName().equals(selected))
					{
						selectedDestination = demoAirports.get(i);
						showSelectedDestination();
					}
				}
				
			}
			public void onNothingSelected(AdapterView<?> parent) {
				
	        }
	    });
	}

	public void departureSelectionListener()
	{
		final Spinner sp = (Spinner) findViewById(R.id.spDeparture);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				
				String selected = sp.getSelectedItem().toString();
				for (int i = 0; i < demoAirports.size(); i++)
				{
					if (demoAirports.get(i).getName().equals(selected))
					{
						selectedDeparture = demoAirports.get(i);
						showSelectedDeparture();
					}
				}
				
			}
			public void onNothingSelected(AdapterView<?> parent) {
				
	        }
	    });
	}
	
    public void createWaypointsList()
    {
        List<String> waypointsList = new ArrayList<String>();
        for(int i = 0; i < demoWaypoints.size(); i++)
        {
            waypointsList.add(demoWaypoints.get(i).toString());
        }

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.customview,waypointsList);

        final ListView listview = (ListView)findViewById(R.id.lvWaypoints);
        listview.setAdapter(adapter);

        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener()
        {
            public void toast(String message)
            {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context,message,duration);
                toast.show();
            }

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                TextView selectedItem = (TextView)parent.getChildAt(position);
                String selectedWaypoint = selectedItem.getText().toString();
                setSelectedWaypoint(selectedWaypoint);
                showWaypointDetails();
                toast(selectedWaypoint.toString());
            }
        };
        listview.setOnItemClickListener(mMessageClickedHandler);
    }
    
    public void createCurrentRouteList()
    {
        List<String> waypointsList = new ArrayList<String>();
        
        for(int i = 0; i < route.getWaypoints().size(); i++)
        {
            waypointsList.add(route.getWaypoints().get(i).toString());
        }

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.customview,waypointsList);

        final ListView listview = (ListView)findViewById(R.id.lvCurrentRoute);
        listview.setAdapter(adapter);

        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener()
        {
            public void toast(String message)
            {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context,message,duration);
                toast.show();
            }

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                TextView selectedItem = (TextView)parent.getChildAt(position);
                String selectedWaypoint = selectedItem.getText().toString();
                setSelectedWaypoint(selectedWaypoint);
                showWaypointDetails();
            }
        };
        listview.setOnItemClickListener(mMessageClickedHandler);
    }
    
    public void onClick_add(View v)
    {
    	if (!route.addWaypoint(selectedWaypoint))
    	{
    		toast("waypoint is already \non the route");
    		return;
    	}
    	refreshCurrentRoute();
    }
    
    public void onClick_remove(View v)
    {
    	if (!route.removeWaypoint(selectedWaypoint))
    	{
    		toast("waypoint is not on \nthe current route");
    		return;
    	}
    	refreshCurrentRoute();
    }
    
    public void onClick_save(View v)
    {
    	toast("successfully saved");
    }
    
    public void onClick_clear(View v)
    {
    	toast("clear not implemented yet");
    }
    
    private void showWaypointDetails()
    {
    	TextView tvLatitude = (TextView) findViewById(R.id.tvLatitudeValW);
    	TextView tvLongitude = (TextView) findViewById(R.id.tvLongitudeValW);
    	TextView tvCourse = (TextView) findViewById(R.id.tvCourseValW);
    	TextView tvDistance = (TextView) findViewById(R.id.tvDistanceValW);
    	
    	tvLatitude.setText(selectedWaypoint.getLatitude());
    	tvLongitude.setText(selectedWaypoint.getLongitude());
    	tvCourse.setText("N");
    	
    	String distance = route.getDistance(selectedWaypoint)+"";
    	
    	tvDistance.setText(distance);
    }
    
     private void showSelectedDeparture()
     {
    	 TextView tvLatitude = (TextView) findViewById(R.id.tvLatitudeVal);
    	 TextView tvLongitude = (TextView) findViewById(R.id.tvLongitudeVal);
    	 TextView tvCourse = (TextView) findViewById(R.id.tvCourseVal);
    	 TextView tvDistance = (TextView) findViewById(R.id.tvDistanceVal);
    	 
    	 tvLatitude.setText(selectedDeparture.getWaypoint().getLatitude());
    	 tvLongitude.setText(selectedDeparture.getWaypoint().getLongitude());
    	 tvCourse.setText("N");

//    	 String distance = route.getDistance(selectedWaypoint)+"";

    	 tvDistance.setText("0");
     }
     
     private void showSelectedDestination()
     {
    	 TextView tvLatitude = (TextView) findViewById(R.id.tvLatitudeValD);
    	 TextView tvLongitude = (TextView) findViewById(R.id.tvLongitudeValD);
    	 TextView tvCourse = (TextView) findViewById(R.id.tvCourseValD);
    	 TextView tvDistance = (TextView) findViewById(R.id.tvDistanceValD);
    	 
    	 tvLatitude.setText(selectedDestination.getWaypoint().getLatitude());
    	 tvLongitude.setText(selectedDestination.getWaypoint().getLongitude());
    	 tvCourse.setText("N");

//    	 String distance = route.getDistance(selectedWaypoint)+"";

    	 tvDistance.setText("0");
     }
    
    private void refreshCurrentRoute()
    {
    	List<String> waypointsList = new ArrayList<String>();

    	for(int i = 0; i < route.getWaypoints().size(); i++)
    	{
    		waypointsList.add(route.getWaypoints().get(i).toString());
    	}

    	final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.customview,waypointsList);

    	final ListView listview = (ListView)findViewById(R.id.lvCurrentRoute);
    	listview.setAdapter(adapter);
    }
    
    private void toast(String message)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,message,duration);
        toast.show();
    }
}
