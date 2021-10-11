package freakyWecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

public class settings {
	public saveSettings savVar;
 
	//constructor
	public settings(saveSettings savVar1)
 {
		savVar = savVar1;
		
 }

	void language()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("language: e - english, g - german, n - norwegian");
	    System.out.println("Option: ");
	    String eingabe = sc.next();
	    switch (eingabe)
	    {
	    case "e":
	    	savVar.setLanguage("e");
	    	break;
	    case "g":
	    	savVar.setLanguage("g");
	    	break;
	    case "n":
	    	savVar.setLanguage("n");
	    	break;
	    default:
	    	language();
	    	break;
	    }
	}
	
	dBOptions dBOsettings()
	{
		dBOptions DBO = new dBOptions();
		
		System.out.println("Datenbank Name");
		System.out.print("Wahl: ");
	    
		Scanner sc = new Scanner(System.in);
		
		DBO.setdBName( sc.next()) ;
		
		System.out.println("Datenbank Benutzer Name");
		System.out.print("Wahl: ");
	    
		sc = new Scanner(System.in);
		
		DBO.setdBUser( sc.next()) ;
		
		System.out.println("Benutzer Passwort");
		System.out.print("Wahl: ");
	    
		sc = new Scanner(System.in);
		
		DBO.setdBPassword( sc.next()) ;
		
		return DBO;
	}
	
	String zeitZone()
	{
		String[] timeZoneIDs = TimeZone.getAvailableIDs();
		for(int k = 0;k<timeZoneIDs.length;k++)
			System.out.println(k+" "+timeZoneIDs[k]);
		Scanner sc = new Scanner(System.in);
	    System.out.print("Wahl: ");
	    String eingabe = sc.next();
	    
		return timeZoneIDs[Integer.parseInt(eingabe)];
		
	}
	
	void dName()
	{
		//________________________________________
	    System.out.println("***Dateiname***");
	    
	    System.out.println(savVar.getPfad());
		File f1 = new File(savVar.getPfad());
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f1.listFiles()));
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(f1.list()));
		for (int i=0; i < names.size();i++)
			System.out.println(names.get(i));
		Scanner sc = new Scanner(System.in);
	    System.out.print("Wahl: ");
	    String eingabe = sc.next();
	    
	    for (int i=0;i<names.size();i++)
	    {
	    	if(eingabe.equals(names.get(i)))
	    	{
	    		String endung = names.get(i).substring(names.get(i).length()-4);
	    		if (endung.equals(".mp3"))
	    		{savVar.setdName( names.get(i).substring(0,names.get(i).length()-4));
	    		System.out.println("Dateiname: "+savVar.getdName());}
	    	}	
	    }
	}
	
	void PfadUndName()
	{
		File f = null;
		try {
		System.out.println("***Pfad***");
		System.out.println(savVar.getPfad());
		
		
	
		f = new File(savVar.getPfad());
	
	
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
		for (int i=0; i < names.size();i++)
			System.out.println(names.get(i));
		Scanner sc = new Scanner(System.in);
	    System.out.print("Wahl: ");
	    String eingabe = sc.next();
	    switch (eingabe)
	    {
	    case "..":
	    	savVar.setPfad(f.getParent());
	    	PfadUndName();
	    	break;
	    case ".":
	    	
	    	dName();
	    
	    }
	    for (int i=0;i<names.size();i++)
	    {
	    	if(eingabe.equals(names.get(i)))
	    		{
	    		String temp = savVar.getPfad();
	    		if(temp.length()>2)
	    		{
	    		String letztesZeichen = temp.substring(temp.length()-2,temp.length()-1);
	    		if (letztesZeichen.equals("/"))
	    		{	//System.out.println("compares /");
	    			savVar.setPfad(savVar.getPfad()+names.get(i));
	    		}else {
	    			//System.out.println("dont Equals");
	    		
	    			savVar.setPfad(savVar.getPfad()+"/"+names.get(i));
	    		}
	    		}else
	    		{
	    			if (savVar.getPfad().equals("/"))
	    			{
	    				savVar.setPfad(savVar.getPfad()+names.get(i));
	    			}
	    			else
	    			{
	    				savVar.setPfad(savVar.getPfad()+"/"+names.get(i));
	    			}
	    		}
	    		PfadUndName();
	    	}
	    		
	    }
		}
		catch(Exception e)
		{
			
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")){
			    //Betriebssystem ist Windows-basiert
				savVar.setPfad("C:/");
			}
			else if (os.contains("osx")){
			    //Betriebssystem ist Apple OSX
				savVar.setPfad("/");
			}      
			else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			    //Betriebssystem ist Linux/Unix basiert
				savVar.setPfad("/");
			}
			
			
			PfadUndName();
		}
	}
	
	void weckerAnzeigen()
	{
		if(savVar.sizeOfEndTimes()>0)
		{
			for(int i = 0; i<savVar.sizeOfEndTimes();i++)
			{
				System.out.println(Integer.toString(i)+". "+savVar.getNameOfWecker(i));
				long yourmilliseconds = savVar.getEndTime(i);
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
				Date resultdate = new Date(yourmilliseconds);
				System.out.println(sdf.format(resultdate));
			}
			Scanner sc0 = new Scanner(System.in);
			String eingabe0 = sc0.next();
		}
		else
		{
			System.out.println("Keine Wecker eingestellt.");
		}
	}
	
	//delete alarm clock
void weckerEntfernen()
{
	if(savVar.sizeOfEndTimes()>0)
	{
	for(int i = 0; i<savVar.sizeOfEndTimes();i++)
	{
		System.out.println(Integer.toString(i)+". "+savVar.getNameOfWecker(i));
	}
	Scanner sc0 = new Scanner(System.in);
	String eingabe0 = sc0.next();

	if(Integer.parseInt(eingabe0)<savVar.sizeOfEndTimes())
	{	
		savVar.deleteEndTime(Integer.parseInt(eingabe0));
	}
	else
		System.out.println("Nummer nicht vorhanden");
	}
	else
	{
		System.out.println("Keine Wecker eingestellt.");
	}
}
 
//add alarm clock
void weckerHinzufuegen()
	{
		System.out.println("Test größe: "+savVar.sizeOfEndTimes());
		for (int i = 0;i<savVar.sizeOfEndTimes();i++)
		{
			long yourmilliseconds = savVar.getEndTime(i);
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
			
			
			Date resultdate = new Date(yourmilliseconds);
			
		}
		
		System.out.println();
		
		
		
	
		
		long yourmilliseconds = System.currentTimeMillis();
		
		Date resultdate = new Date(yourmilliseconds);
		
		
		long epoch = /*resultdate.getTime()*/System.currentTimeMillis()%86400000; 
		//long epoch = TimeZone.getTimeZone( "Europe/Berlin").inDaylightTime( System.currentTimeMillis() );
		
		
		long stunden1 = epoch/(60*60*1000);
		
		ZonedDateTime now = ZonedDateTime.now( ZoneId.of(/* "Europe/Berlin"*/savVar.getZeitZoneGespeichert() ) );
		ZoneId z = now.getZone();
		ZoneRules zoneRules = z.getRules();
		Boolean isDst = zoneRules.isDaylightSavings( now.toInstant() );
		
		
		TimeZone timezone = TimeZone.getDefault();

		stunden1 = stunden1 + timezone.getRawOffset()/(60*60*1000);
		
		if(isDst)
		stunden1=stunden1+1;
	
		

		
		long minuten1 = (epoch%(60*60*1000))/(60*1000);
		long sekunden1=(epoch%(60*1000))/1000;

		long heuteBeginn = System.currentTimeMillis()-stunden1*(60*60*1000)-minuten1*(60*1000)-sekunden1*1000;
		long morgenBeginn = heuteBeginn+86400000;
		
		DBConnect DBC = new DBConnect();	
		
		//System.out.println("1. Heute");
		System.out.printf("1.");
		DBC.getTranslation(varContainer.savVar.getDBO(),11,varContainer.savVar.getLanguage());
		
		
		//System.out.println("2. Morgen");
		System.out.printf("2.");
		DBC.getTranslation(varContainer.savVar.getDBO(),12,varContainer.savVar.getLanguage());
		
		
		Scanner sc = new Scanner(System.in);
		String eingabe = sc.next();
	    


    
		int hours = 0;
		int minutes=0;
		int seconds=0;
		String name="";
		
		String s;
		
		//System.out.println("Stunden");
		DBC.getTranslation(varContainer.savVar.getDBO(),13,varContainer.savVar.getLanguage());
		
		try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        s = bufferRead.readLine();
		        
		        
		        if(hours <24)
		        {	 
		        	System.out.println("eingegeben: "+s);
			        hours=Integer.valueOf(s);
		        }	
		        else hours=0;
		   } 
		catch(Exception e)
		   {
			 
		   }
		
		DBC.getTranslation(varContainer.savVar.getDBO(),14,varContainer.savVar.getLanguage());
		try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        s  = bufferRead.readLine();
		       
		        if(minutes < 60)
		        {	 
		        	System.out.println("eingegeben: "+s);
		            minutes = Integer.valueOf(s);;
		        }	
		        else minutes = 0;
		        	
		   } 
		catch(Exception e)
		   {
			 
		   }
		

		DBC.getTranslation(varContainer.savVar.getDBO(),15,varContainer.savVar.getLanguage());
		try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        s  = bufferRead.readLine();
		        System.out.println("eingegeben: "+s);
		        name = s;
		   } 
		catch(Exception e)
		   {
			 
		   }
		
		
        
		long tagBeginnStunden=heuteBeginn%86400000/(60*60*1000);
		long tagBeginnMinuten=heuteBeginn%86400000%(60*60*1000)/(60*1000);
		
		long endTime; 
		
		switch (eingabe)
	    {
    	case "1":
    		endTime = (long)(minutes*60*1000)+(long)(hours*60*60*1000)+ heuteBeginn;
            savVar.setEndTime(endTime);
            savVar.setNameOfWecker(name);
    		break;
    	case "2":
    		endTime = (long)(minutes*60*1000)+(long)(hours*60*60*1000)+ morgenBeginn;
            savVar.setEndTime(endTime);
            savVar.setNameOfWecker(name);
    		break;
       default : 
    		break;
    	}

	}
	
//settings	
public saveSettings runSettings()
	{

	DBConnect DBC = new DBConnect();	
	
	DBC.getTranslation(varContainer.savVar.getDBO(),5,varContainer.savVar.getLanguage());
	
	
		System.out.println();

		System.out.printf("1.");
		DBC.getTranslation(varContainer.savVar.getDBO(),6,varContainer.savVar.getLanguage());
		
		

		System.out.printf("2.");
		DBC.getTranslation(varContainer.savVar.getDBO(),7,varContainer.savVar.getLanguage());
		

		System.out.printf("3.");
		DBC.getTranslation(varContainer.savVar.getDBO(),8,varContainer.savVar.getLanguage());
		

		System.out.printf("4.");
		DBC.getTranslation(varContainer.savVar.getDBO(),9,varContainer.savVar.getLanguage());
		

		System.out.printf("6.");
		DBC.getTranslation(varContainer.savVar.getDBO(),17,varContainer.savVar.getLanguage());
		
		
		System.out.println("7. Databench options");

		System.out.printf("8.");
		DBC.getTranslation(varContainer.savVar.getDBO(),16,varContainer.savVar.getLanguage());
		
		
		System.out.printf("5.");
		DBC.getTranslation(varContainer.savVar.getDBO(),10,varContainer.savVar.getLanguage());
		
		
		while (true)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Optionen");
		    System.out.println("Wahl: ");
		    String eingabe = sc.next();
		    switch (eingabe)
		    {
		    	case "1":
		    		weckerHinzufuegen();
		    		break;
		    	case "2":
		    		weckerEntfernen();
		    		break;
		    	case "3":
		    		//savVar.setPfad("/");
		    		System.out.println(".. - parent");
		    		System.out.println(". - OK");
		    		PfadUndName();
		    		break;
		    	case "4":
		    		weckerAnzeigen();
		    		break;
		    	case "5":
		    		return savVar;
		    	case "6":
		    		savVar.setZeitZoneGespeichert(zeitZone());
		    		break;
		    	case "7":
		    		dBOptions DBO = dBOsettings();
		    		savVar.setDBO(DBO);
		    		break;
		    	case "8":
		    		language();
		    		break;
		    	default : 
		    		break;
		    	
		    	
		    }
		}
		
		
	}
}
