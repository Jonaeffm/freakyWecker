package freakyWecker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import javax.xml.stream.XMLStreamException;

import jmusic.JMusicPlayerList;
import jmusic.JMusicSong;
import jmusic.MusicPlayerControl;




public class freakyWecker {

	//the thread in which the clock runs
	static Thread test ;
	
	
	/*static int i;
	static saveSettings savVar;
static boolean firstTime;
	*/
	//are the current time and one of the alarm clocks the same?
	
	
	
	
	
	/*public long getTime(saveSettings savVar)
	{
		long time = System.currentTimeMillis() - savVar.getStartTime();
		time = time / 1000;
		return time;
	}*/
	
	//terminal output
	
	
	//unused
	/*public static void updateTime()
	{
		try
		{
		
			while(checkTime()==false)
			{
				
					//geting Time in desire format
				System.out.print("\b\b\b\b\b");
				System.out.print(printTime());
				Thread.currentThread();
			
				//Thread sleeping for 1 sec
				Thread.sleep(1000);
				
			}
			if(checkTime()==true)
			{
				saveSettings.deleteEndTime(varContainer.i);
				System.out.print("\b\b\b\b\b");
				System.out.println(varContainer.savVar.getNameOfWecker(varContainer.i));
				playTheSong();
				Thread.currentThread();
			
			//Thread sleeping for 1 sec
				Thread.sleep(1000);
				
			}	
			updateTime();
			
		}
		catch (Exception e)
		{
			System.out.println("Exception in Thread Sleep :"+e);
		}
	}*/
	
	
	

	
	public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException {
		//load saved data
		varContainer.savVar= new saveSettings();
		XMLReadWrite parser = new XMLReadWrite();
		varContainer.savVar = parser.readXML(); 
		

		//savVar = new saveSettings();
		varContainer.firstTime=true;
		
		/*System.out.println("1. Winterzeit");
		System.out.println("2. Sommerzeit");
		Scanner sc = new Scanner(System.in);
		String eingabe = sc.next();
		switch (eingabe)
	    {
	    	case "1":
	    		varContainer.winterSummer = true;
	    		break;
	    	case "2":
	    		varContainer.winterSummer = false;
	    		break;
	    }*/
		
		
			settings useSettings = new settings(varContainer.savVar);
			System.out.println(useSettings.savVar.getPfad()+"/"+useSettings.savVar.getdName());
	/*	ZoneId z = now.getZone();
		ZoneRules zoneRules = z.getRules();
		Boolean isDst = zoneRules.isDaylightSavings( now.toInstant() );*/
		//varContainer.winterSummer = !isDst;
		// TODO Auto-generated method stub
		//Main menu
		while (true)
		{

			/*for(int j=varContainer.savVar.sizeOfEndTimes()-1;j>(-1);j--)
			{
				if(varContainer.savVar.getEndTime(j)<System.currentTimeMillis())
				{
					//System.out.println("Var kleiner");
					saveSettings.deleteEndTime(j);
				}
			}*/
			
			System.out.println("1. Optionen");
			System.out.println("2. Start");
			System.out.println("3. Beenden");
			System.out.println("4. Alarm off");
			System.out.println("5. SQL TESTFUNKTION");
			   System.out.println();
			System.out.println("Hauptmenü");
		    System.out.println("Wahl: ");
		 
		    Scanner sc = new Scanner(System.in);
		    String  eingabe = sc.next();
		    switch (eingabe)
		    {
		    	case "1":
		    		try
		    		{
		    			test.stop();
		    		}
		    		catch (Exception e)
		    		{
		    			
		    		}
		    				varContainer.savVar = useSettings.runSettings();
		    				
		    				//System.out.println("Test größe nach laden: "+varContainer.savVar.sizeOfEndTimes());
				    		for (int i = 0;i<varContainer.savVar.sizeOfEndTimes();i++)
				    		{
				    			long yourmilliseconds = varContainer.savVar.getEndTime(i);
				    			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
				    			
				    			sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
				    			//df.format(date1);
				    			
				    			Date resultdate = new Date(yourmilliseconds);
				    			//System.out.println(varContainer.savVar.getNameOfWecker(i)+sdf.format(resultdate));
				    			//System.out.println("long:"+resultdate.getTime());
				    		
				    		}
		    				
		    		break;
		    	case "2":
		    		try
		    		{
		    			test.stop();
		    		}
		    		catch (Exception e)
		    		{
		    			
		    		}
		    		try 
		    		{
		    			test = new Thread(new myRunnable());
		    			test.start();
		    			
		    			
		    			
		    		
		    			
		    			//updateTime(); 

		    		} 
		    		catch (Exception ie) 
		    		{ }
		    		
		    			
		    		break;
		    	
		    	case "3":
		    		try
		    		{
		    			test.stop();
		    		}
		    		catch (Exception e)
		    		{
		    			
		    		}
		    		//System.out.println("Test größe: "+varContainer.savVar.sizeOfEndTimes());
		    		//save changes
		    		for (int i = 0;i<varContainer.savVar.sizeOfEndTimes();i++)
		    		{
		    			long yourmilliseconds = varContainer.savVar.getEndTime(i);
		    			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
		    			
		    			sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
		    			//df.format(date1);
		    			
		    			Date resultdate = new Date(yourmilliseconds);
		    			//System.out.println(varContainer.savVar.getNameOfWecker(i)+sdf.format(resultdate));
		    			//System.out.println("long:"+resultdate.getTime());
		    		
		    		}

		    		
		    		parser = new XMLReadWrite();
		    		parser.writeXML(varContainer.savVar);
		    		System.exit(0);
		    		break;
		    	case"4":
		    		try
		    		{
		    			test.stop();
		    		}
		    		catch (Exception e)
		    		{
		    			
		    		}
		    		try
		    		{
		    		MusicPlayerControl.stopSong();
		    		}
		    		catch (Exception e)
		    		{
		    			
		    		}
		    		break;
		    	case"5":
		    		DBConnect DBC = new DBConnect();
		    		DBC.getTranslation(varContainer.savVar.getDBO(),0,varContainer.savVar.getLanguage());
		    		break;
		    	default : 
		    		break;
		    }
		    	
		    	
		}
	
		

        
       /* endTime = (long)(Integer.parseInt(60) *60+Integer.parseInt(60)+ System.currentTimeMillis()/1000);
		*/
		
	}



}
