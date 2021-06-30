package freakyWecker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jmusic.JMusicPlayerList;
import jmusic.JMusicSong;
import jmusic.MusicPlayerControl;

public class myRunnable implements Runnable {

	public static boolean sameMinute(int i)
	{
		if(getCurrentMinutes()-getMinutes(i)==0)
			return true;
		else
			return false;
	}
	//get minutes
	public static int getCurrentMinutes()
	{
		return(int)(System.currentTimeMillis()/1000/60)%60;
	}
// get minutes of alarm clock
	public static int getMinutes(int i)
	{
		int time;
		
		
			time =(int) (varContainer.savVar.getEndTime(i)/1000/60)%60;
			//System.out.println(time+" Minuten");
		return time;
	}
	
	//time between current time and alarm clocks
	public static long getTimer(int i)
	{
		long time;
		
		
			time = varContainer.savVar.getEndTime(i)/1000 - System.currentTimeMillis()/1000;
			//System.out.println("hallo");
		System.out.println("Differenz:"+time);
			return time;
	}
	
	//import external jar for this function https://github.com/rcasanovan/JMusic
	@SuppressWarnings("deprecation")
	public static void playTheSong() 
	{
		if (varContainer.firstTime == true)
		{
			varContainer.firstTime=false;
			JMusicPlayerList list = new JMusicPlayerList();
		
		
		
			String DNAME = varContainer.savVar.getdName();
			JMusicSong song = new JMusicSong(1, DNAME, "", "", "");


			list.addSongToPlayerList(song);
		

			MusicPlayerControl.initMusicPlayer(varContainer.savVar.getPfad());
		
		
			MusicPlayerControl.loadSongs(list);
		}
		
		MusicPlayerControl.playSong();
		
		System.out.println("Hauptmenü");
	    System.out.println("Wahl: ");
	    System.out.println();
	    System.out.println("1. Optionen");
		System.out.println("2. Start");
		System.out.println("3. Beenden");
		System.out.println("4. Alarm off");
		
		
	
	    
	  
		//System.exit(0);
		
        
        
		
	}
	
	//alarm clock on?
	public static boolean checkTime()
	{
		try
		{
			for(varContainer.i=0;varContainer.i<varContainer.savVar.sizeOfEndTimes();varContainer.i++)
			{
				System.out.println("size"+varContainer.savVar.sizeOfEndTimes());
				/*if(!sameMinute(varContainer.i)&&varContainer.minuteChecked==true)
					varContainer.minuteChecked=false;*/
				if(getTimer(varContainer.i)==(long)0)
				{
					
					//(varContainer.minuteChecked = true;
					//long minuten = getMinutes(varContainer.i);
					//System.out.println("true");
					//saveSettings.deleteEndTime(i);
					//System.out.println("return true");
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
		//System.out.println("false");
		return false;
	}
	
	//terminal output
	public static String printTime()
	{		
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
		Date resultdate = new Date(yourmilliseconds);
		return(sdf.format(resultdate));

	}
	
	
	//run the thread here
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try
		{
		
			while(checkTime()==false)
			{
				
					//geting Time in desire format
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
				System.out.print(printTime());
				Thread.currentThread();
			
				//Thread sleeping for 1 sec
				Thread.sleep(1000);
				//run();
				
			}
			if(checkTime()==true)
			{
				//saveSettings.deleteEndTime(varContainer.i);
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
				System.out.println(varContainer.savVar.getNameOfWecker(varContainer.i));
				playTheSong();
				//Thread.currentThread();
				
				Thread.currentThread();
				
				//Thread sleeping for 1 sec
		
			
			//Thread sleeping for 1 sec
				//Thread.sleep(1000);
				
			}	
	
			
		}
		catch (Exception e)
		{
			System.out.println("Exception in Thread Sleep :"+e);
		}
	}

}
