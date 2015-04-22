package com.aerialglass.fpm.Activities;

import com.aerialglass.fpm.Classes.*;

import java.util.ArrayList;
import java.util.List;

import com.aerialglass.fpm.R;
import com.aerialglass.fpm.R.id;
import com.aerialglass.fpm.R.layout;
import com.aerialglass.fpm.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PreferencesActivity extends ActionBarActivity {

	SharedPreferences appPrefs = null;
	SharedPreferences.Editor appEdit = null;
	List<String> avoidAirportsList = new ArrayList<String>();
	DATABASE_HANDLER dbh = new DATABASE_HANDLER(this);
	
	List<AIRPORT> demoAirports = new ArrayList<AIRPORT>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);

		appPrefs = getSharedPreferences("com.aerialglass.fpm", MODE_PRIVATE);
		appEdit = appPrefs.edit();
		dbh.start();

		createDemoAirports();
		
		populatePreferences();
		createAirportsList();
		createAvoidAirportsList();
		createTextListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preferences, menu);
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
	
	private void createDemoAirports()
	{
        demoAirports.add(new AIRPORT("ELP-El Paso International Airport",1,"X", "1"));
        demoAirports.add(new AIRPORT("HOU-William P. Hobby Airport",1,"X", "1"));
        demoAirports.add(new AIRPORT("DEN-Denver International Airport",1,"X", "1"));
        demoAirports.add(new AIRPORT("SFO-San Francisco International Airport",1,"X", "1"));
        demoAirports.add(new AIRPORT("SAN-San Diego International Airport",1,"X", "1"));
	}
	
	private void toggleListsState(boolean listState)
	{
		ListView lvAirportsAvoid = (ListView)findViewById(R.id.lvAirportsAvoid);
		ListView lvAirports = (ListView)findViewById(R.id.lvAirports);
		lvAirportsAvoid.setClickable(listState);
		lvAirportsAvoid.setEnabled(listState);
		lvAirportsAvoid.invalidate();
		
		lvAirports.setClickable(listState);
		lvAirports.setEnabled(listState);
		lvAirports.invalidate();
	}

	private void refreshAvoidAirportsList()
	{
		List<String> airportsList = new ArrayList<String>();
		List<AIRPORT> airports = dbh.getAirports();
		
		if (airports == null)
		{
			ListView listview = (ListView)findViewById(R.id.lvAirportsAvoid);
			listview.setAdapter(null);
			return;
		}
		
		for(int i = 0; i < airports.size(); i++)
		{
			airportsList.add(airports.get(i).getName());
		}
		
		final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.customview,airportsList);
		final ListView listview = (ListView)findViewById(R.id.lvAirportsAvoid);
		listview.setAdapter(adapter);
	}

	public void createAvoidAirportsList()
	{
		List<String> airportsList = new ArrayList<String>();
		List<AIRPORT> airports = dbh.getAirports();
		
		if (airports == null)
		{
			return;
		}
		
		for(int i = 0; i < airports.size(); i++)
		{
			airportsList.add(airports.get(i).getName());
		}
		
		final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.customview,airportsList);
		final ListView listview = (ListView)findViewById(R.id.lvAirportsAvoid);
		listview.setAdapter(adapter);

		OnItemClickListener mMessageClickedHandler = new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView parent, View v, int position, long id) 
			{
				TextView selectedItem = (TextView)parent.getChildAt(position);
				dbh.deleteAirport(selectedItem.getText().toString());
				refreshAvoidAirportsList();
			}
		};
		listview.setOnItemClickListener(mMessageClickedHandler);
	}

	public void createAirportsList()
	{
		List<String> airportsList = new ArrayList<String>();
		for(int i = 0; i < demoAirports.size(); i++)
		{
			airportsList.add(demoAirports.get(i).getName());
		}
		
		final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.customview,airportsList);

		final ListView listview = (ListView)findViewById(R.id.lvAirports);
		listview.setAdapter(adapter);

		OnItemClickListener mMessageClickedHandler = new OnItemClickListener()
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
				String selectedAirport = selectedItem.getText().toString();
				if (dbh.insertAirport(new AIRPORT(selectedAirport, 1, "X", "1")) == false)
				{
					toast("Selected airport is already \non the avoid list");
					return;
				}
				refreshAvoidAirportsList();
			}
		};
		listview.setOnItemClickListener(mMessageClickedHandler);
	}
	
	public void createTextListener()
	{
		EditText terrainTE = (EditText) findViewById(R.id.etAvoidTerrain);
		terrainTE.addTextChangedListener(new TextWatcher() {

	        @Override
	        public void afterTextChanged(Editable s) {}
	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	        	EditText terrainTE = (EditText) findViewById(R.id.etAvoidTerrain);
	    		String terrainValue = terrainTE.getText().toString();
	    		appEdit.putString("TerrainValue", terrainValue);
	    		appEdit.commit();
	        } 
	    });
	}

	public void populatePreferences()
	{
		CheckBox avoidTerrainChk = (CheckBox) findViewById(R.id.chkAvoidTerrain);
		boolean avoidTerrain = appPrefs.getBoolean("AvoidTerrain", false);
		avoidTerrainChk.setChecked(avoidTerrain);

		CheckBox avoidWaterChk = (CheckBox) findViewById(R.id.chkAvoidWater);
		boolean avoidWater = appPrefs.getBoolean("AvoidWater", false);
		avoidWaterChk.setChecked(avoidWater);

		CheckBox avoidAirportsChk = (CheckBox) findViewById(R.id.chkAvoidAirports);
		boolean avoidAirports = appPrefs.getBoolean("AvoidAirports", false);
		avoidAirportsChk.setChecked(avoidAirports);
		
		toggleListsState(avoidAirports);

		EditText terrainTE = (EditText) findViewById(R.id.etAvoidTerrain);
		String terrainValue = appPrefs.getString("TerrainValue", "0");
		terrainTE.setText(terrainValue);
	}

	public void onClick_AvoidTerrain(View v)
	{
		CheckBox avoidTerrainChk = (CheckBox) findViewById(R.id.chkAvoidTerrain);
		boolean avoidTerrain = avoidTerrainChk.isChecked();
		appEdit.putBoolean("AvoidTerrain", avoidTerrain);
		appEdit.commit();
	}

	public void onClick_AvoidWater(View v)
	{
		CheckBox avoidWaterChk = (CheckBox) findViewById(R.id.chkAvoidWater);
		boolean avoidWater = avoidWaterChk.isChecked();
		appEdit.putBoolean("AvoidWater", avoidWater);
		appEdit.commit();
	}

	public void onClick_AvoidAirports(View v)
	{
		CheckBox avoidAirportsChk = (CheckBox) findViewById(R.id.chkAvoidAirports);
		boolean avoidAirports = avoidAirportsChk.isChecked();
		appEdit.putBoolean("AvoidAirports", avoidAirports);
		appEdit.commit();
		
		toggleListsState(avoidAirports);
	}
}
