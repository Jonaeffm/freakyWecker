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
	
	public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException {
		//load saved data
		varContainer.savVar= new saveSettings();
		XMLReadWrite parser = new XMLReadWrite();
		varContainer.savVar = parser.readXML(); 
		varContainer.firstTime=true;
			settings useSettings = new settings(varContainer.savVar);
			System.out.println(useSettings.savVar.getPfad()+"/"+useSettings.savVar.getdName());

		while (true)
		{

			
			DBConnect DBC = new DBConnect();
    		
			System.out.printf("1.");
			DBC.getTranslation(varContainer.savVar.getDBO(),0,varContainer.savVar.getLanguage());
			
			System.out.printf("2.");
			DBC.getTranslation(varContainer.savVar.getDBO(),1,varContainer.savVar.getLanguage());

			System.out.printf("3.");
			DBC.getTranslation(varContainer.savVar.getDBO(),2,varContainer.savVar.getLanguage());
			
			System.out.printf("4.");
			DBC.getTranslation(varContainer.savVar.getDBO(),3,varContainer.savVar.getLanguage());
    		
			System.out.println("5. SQL TESTFUNKTION");
			   System.out.println();
			
			   DBC.getTranslation(varContainer.savVar.getDBO(),4,varContainer.savVar.getLanguage());
	    
			   DBC.getTranslation(varContainer.savVar.getDBO(),5,varContainer.savVar.getLanguage());
	    		
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
				    					
				    			Date resultdate = new Date(yourmilliseconds);
				    			
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
		    		
		    		for (int i = 0;i<varContainer.savVar.sizeOfEndTimes();i++)
		    		{
		    			long yourmilliseconds = varContainer.savVar.getEndTime(i);
		    			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
		    			
		    			sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
		    		
		    			
		    			Date resultdate = new Date(yourmilliseconds);	
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
		    		DBC = new DBConnect();
		    		DBC.getTranslation(varContainer.savVar.getDBO(),0,varContainer.savVar.getLanguage());
		    		break;
		    	default : 
		    		break;
		    }	    	
		}
	}
}
