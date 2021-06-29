package freakyWecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Clock;
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
	public static boolean checkTime()
	{
		try
		{
			for(varContainer.i=0;varContainer.i<varContainer.savVar.sizeOfEndTimes();varContainer.i++)
			{
				if(getTimer(varContainer.i)==(long)0)
				{
			
					//saveSettings.deleteEndTime(i);
					//return true
					return(true);
				}
				//else
					//return(false);
			}
			
		}
		catch (Exception e)
		{
			return(false);
		}
		//else false
		return false;
	}
	
	//alarm clock time minus current time = ?
	public static long getTimer(int i)
	{
		long time;
		
		
			time = varContainer.savVar.getEndTime(i) - System.currentTimeMillis()/1000;
		return time;
	}
	
	/*public long getTime(saveSettings savVar)
	{
		long time = System.currentTimeMillis() - savVar.getStartTime();
		time = time / 1000;
		return time;
	}*/
	
	//terminal output
	public static String printTime()
	{		
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
		Date resultdate = new Date(yourmilliseconds);
		return(sdf.format(resultdate));

	}
	
	//unused
	public static void updateTime()
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
	}
	
	
	

	
	public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException {
		//load saved data
		varContainer.savVar= new saveSettings();
		XMLReadWrite parser = new XMLReadWrite();
		varContainer.savVar = parser.readXML(); 
		
		settings useSettings = new settings(varContainer.savVar);
		//savVar = new saveSettings();
		varContainer.firstTime=true;
		// TODO Auto-generated method stub
		//Main menu
		while (true)
		{
			
			
			System.out.println("1. Optionen");
			System.out.println("2. Start");
			System.out.println("3. Beenden");
			System.out.println("4. Alarm off");
			   System.out.println();
			System.out.println("Hauptmenü");
		    System.out.println("Wahl: ");
		 
		    Scanner sc = new Scanner(System.in);
		    String eingabe = sc.next();
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
		    		
		    	default : 
		    		break;
		    }
		    	
		    	
		}
	
		

        
       /* endTime = (long)(Integer.parseInt(60) *60+Integer.parseInt(60)+ System.currentTimeMillis()/1000);
		*/
		
	}

	//import external jar for this function https://github.com/rcasanovan/JMusic
	@SuppressWarnings("deprecation")
	public static void playTheSong() 
	{
		if (varContainer.firstTime == true)
		{
			varContainer.firstTime=false;
			JMusicPlayerList list = new JMusicPlayerList();
		
		
		
			String DNAME = "play";
			JMusicSong song = new JMusicSong(1, DNAME, "", "", "");


			list.addSongToPlayerList(song);
		

			MusicPlayerControl.initMusicPlayer("/root/eclipse-workspace/FreakyWecker/src/freakyWecker");
		
		
			MusicPlayerControl.loadSongs(list);
		}
		
		MusicPlayerControl.playSong();
		
		System.out.println("Press Enter key to continue...");
	   
		Scanner sc = new Scanner(System.in);
		sc.next();
		
	    MusicPlayerControl.stopSong();
	    
	  
		//System.exit(0);
		
        
        
		
	}
	

}
