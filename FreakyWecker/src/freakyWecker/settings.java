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
		//System.out.println("Test größe: "+savVar.sizeOfEndTimes());
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
	    	
	    	
	    	//savVar.setPfad(savVar.getPfad()+"/");
	    	
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
			//f= new File("/");
			
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
	//System.out.println("Eingabe Wert: "+eingabe0);
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
			
			//sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
			//df.format(date1);
			
			Date resultdate = new Date(yourmilliseconds);
			//System.out.println(savVar.getNameOfWecker(i)+sdf.format(resultdate));
			//System.out.println("long:"+resultdate.getTime());
		
		}
		
		System.out.println();
		
		//Date date1 = new Date();
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Use Berlin's time zone to format the date in
		//df.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
		//df.format(date1);
		//Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("Germany"), Locale.GERMANY);
		//cal.setTime(date1);
		
	
		
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
		
		System.out.println("1. Heute");
		System.out.println("2. Morgen");
		Scanner sc = new Scanner(System.in);
		String eingabe = sc.next();
	    


    
		int hours = 0;
		int minutes=0;
		int seconds=0;
		String name="";
		
		String s;
		
		System.out.println("Stunden");
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
		
		System.out.println("Minuten");
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
		
		System.out.println("Name");
		try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        s  = bufferRead.readLine();
		        System.out.println("eingegeben: "+s);
		        name = s;
		   } 
		catch(Exception e)
		   {
			 
		   }
		
		/*long startTime = (long)System.currentTimeMillis();
		
		savVar.setStartTime(startTime);*/
        
		long tagBeginnStunden=heuteBeginn%86400000/(60*60*1000);
		long tagBeginnMinuten=heuteBeginn%86400000%(60*60*1000)/(60*1000);
		//long weckerGestelltAuf = (System.currentTimeMillis()/86400000)+stunden1*60*60*1000+minuten1*60*1000;
		//System.out.println("Es ist "+Long.toString(stunden1)+" Uhr "+ Long.toString(minuten1));
		//System.out.println("Der Tag beginnt um "+Long.toString(tagBeginnStunden)+" Uhr "+tagBeginnMinuten);
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
    	
    	
    		
		//Sytem.out.println("Wecker gestellt auf:"+)
		
	}
	
//settings	
public saveSettings runSettings()
	{

	/*for(int j=varContainer.savVar.sizeOfEndTimes()-1;j>(-1);j--)
	{
		if(varContainer.savVar.getEndTime(j)<System.currentTimeMillis())
		{
			//System.out.println("Var kleiner");
			saveSettings.deleteEndTime(j);
		}
	}*/
		
		//savVar = new saveSettings();
		//System.out.println("Test größe im Menu: "+savVar.sizeOfEndTimes());
		//System.out.println();
		System.out.println("Optionen");
		System.out.println();
		System.out.println("1. Wecker hinzufügen");
		System.out.println("2. Wecker entfernen");
		System.out.println("3. Pfad und Dateiname der MP3");
		System.out.println("4. Wecker anzeigen");
		System.out.println("6. Zeitzone");
		System.out.println("7. Databench options");
	
		System.out.println("5. zurück");
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
		    	default : 
		    		break;
		    	
		    	
		    }
		}
		
		//savVar = new saveSettings();
		
			
	}
}
