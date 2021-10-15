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
		varContainer.setSavVar(new saveSettings());
		XMLReadWrite parser = new XMLReadWrite();
		varContainer.setSavVar(parser.readXML()); 
		varContainer.setFirstTime(true);
			settings useSettings = new settings(varContainer.getSavVar());
			System.out.println(useSettings.getSavVar().getPfad()+"/"+useSettings.getSavVar().getdName());

		while (true)
		{

			
			DBConnect DBC = new DBConnect();
    		
			System.out.printf("1.");
			DBC.getTranslation(varContainer.getSavVar().getDBO(),18,varContainer.getSavVar().getLanguage());
			
			System.out.printf("2.");
			DBC.getTranslation(varContainer.getSavVar().getDBO(),1,varContainer.getSavVar().getLanguage());

			System.out.printf("3.");
			DBC.getTranslation(varContainer.getSavVar().getDBO(),2,varContainer.getSavVar().getLanguage());
			
			System.out.printf("4.");
			DBC.getTranslation(varContainer.getSavVar().getDBO(),3,varContainer.getSavVar().getLanguage());
    		
			System.out.println("5. SQL TESTFUNKTION");
			   System.out.println();
			
			   DBC.getTranslation(varContainer.getSavVar().getDBO(),4,varContainer.getSavVar().getLanguage());
	    
			   DBC.getTranslation(varContainer.getSavVar().getDBO(),5,varContainer.getSavVar().getLanguage());
	    		
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
		    				varContainer.setSavVar(useSettings.runSettings());
		    				
		    				//System.out.println("Test größe nach laden: "+varContainer.savVar.sizeOfEndTimes());
				    		for (int i = 0;i<varContainer.getSavVar().sizeOfEndTimes();i++)
				    		{
				    			long yourmilliseconds = varContainer.getSavVar().getEndTime(i);
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
		    		
		    		for (int i = 0;i<varContainer.getSavVar().sizeOfEndTimes();i++)
		    		{
		    			long yourmilliseconds = varContainer.getSavVar().getEndTime(i);
		    			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
		    			
		    			sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
		    		
		    			
		    			Date resultdate = new Date(yourmilliseconds);	
		    		}
		    		parser = new XMLReadWrite();
		    		parser.writeXML(varContainer.getSavVar());
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
		    		DBC.getTranslation(varContainer.getSavVar().getDBO(),0,varContainer.getSavVar().getLanguage());
		    		break;
		    	default : 
		    		break;
		    }	    	
		}
	}
}
