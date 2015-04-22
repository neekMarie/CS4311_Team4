package com.aerialglass.fpm.Classes;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DATABASE_HANDLER extends SQLiteOpenHelper 
{
	private static final String name = "com.aerialglass.fpm.Classes";

	public DATABASE_HANDLER(Context context)
	{
		super(context, name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{	
//		db.execSQL("DROP TABLE avoidAirports");
		
		try
		{db.execSQL("CREATE TABLE user (name TEXT PRIMARY KEY, lastName TEXT)");}
		catch(Exception e)
		{System.out.println("user table already exists");}
		
		try
		{db.execSQL("CREATE TABLE avoidAirports(name TEXT PRIMARY KEY, opHours TEXT, closedRunway TEXT, waypoint TEXT)");}
		catch(Exception e)
		{System.out.println("avoidAirports table already exists");}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	public void start()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		onCreate(db);
	}

	public boolean insertUser(USER user)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
			db.execSQL("INSERT INTO user(name,lastName) VALUES ('"+ user.getName() +"','"+user.getLastName() +"')");
		}
		catch(Exception e)
		{
			System.out.println("user already exists");
			return false;
		}
		return true;
	}
	
	public boolean insertAirport(AIRPORT airport)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
			db.execSQL("INSERT INTO avoidAirports(name,opHours,closedRunway, waypoint) VALUES ('"+ airport.getName() +"','"+airport.getOperationalHours()+"','"+airport.getClosedRunways()+"','"+airport.getWaypoint() +"')");
		}
		catch(Exception e)
		{
			System.out.println("airport already exists");
			return false;
		}
		return true;
	}
	
	public AIRPORT getAirport(String airportName)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM avoidAirports WHERE name = '"+airportName+"'";

		String name = null;
		String opHours = null;
		String runway = null;
		String waypoint = null;

		Cursor cursor = db.rawQuery(query,null);

		if(cursor.moveToFirst())
		{
			do{
				name = cursor.getString(0);
				opHours = cursor.getString(1);
				runway = cursor.getString(2);
				waypoint = cursor.getString(3);
			}while(cursor.moveToNext());

		}
		if(name == null)
		{return null;}

		return new AIRPORT(name,Integer.parseInt(opHours), runway, waypoint);
	}
	
	public List<AIRPORT> getAirports()
	{
		List<AIRPORT> list = new ArrayList<AIRPORT>();
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM avoidAirports";

		String name = null;
		String opHours = null;
		String runway = null;
		String waypoint = null;

		Cursor cursor = db.rawQuery(query,null);

		if(cursor.moveToFirst())
		{
			do{
				name = cursor.getString(0);
				opHours = cursor.getString(1);
				runway = cursor.getString(2);
				waypoint = cursor.getString(3);
				
				AIRPORT airport = new AIRPORT(name,Integer.parseInt(opHours), runway, waypoint);
				list.add(airport);
				
			}while(cursor.moveToNext());

		}
		if(name == null)
		{return null;}

		return list;
	}

	public USER getUser(String name)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM user WHERE name = '"+name+"'";

		String firstname = null;
		String lastname = null;

		Cursor cursor = db.rawQuery(query,null);

		if(cursor.moveToFirst())
		{
			do{
				firstname = cursor.getString(0);
				lastname = cursor.getString(1);
			}while(cursor.moveToNext());

		}
		if(firstname == null)
		{return null;}

		return new USER(firstname,lastname);
	}

	public List<USER> getUsers()
	{
		List<USER> list = new ArrayList<USER>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String query = "SELECT * FROM users";
		String firstname = null;
		String lastname = null;

		Cursor cursor = db.rawQuery(query,null);
		if(cursor.moveToFirst())
		{
			do{
				firstname = cursor.getString(0);
				lastname = cursor.getString(1);
				USER user = new USER(firstname,lastname);
				list.add(user);
			}while(cursor.moveToNext());
		}
		return list;
	}

	public void deleteUser(String name)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{db.execSQL("DELETE FROM user WHERE name = '"+name+"'");}
		catch(Exception e)
		{System.out.println("ERROR DELETE FAILED *******************************");}
	}
	
	public void deleteAirport(String name)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{db.execSQL("DELETE FROM avoidAirports WHERE name = '"+name+"'");}
		catch(Exception e)
		{System.out.println("ERROR DELETE FAILED *******************************");}
	}
}
