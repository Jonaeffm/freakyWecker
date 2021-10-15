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
		
		
			time =(int) (varContainer.getSavVar().getEndTime(i)/1000/60)%60;
	
		return time;
	}
	
	//time between current time and alarm clocks
	public static long getTimer(int i)
	{
		long time;
		
		
			time = varContainer.getSavVar().getEndTime(i)/1000 - System.currentTimeMillis()/1000;
		
			return time;
	}
	
	//import external jar for this function https://github.com/rcasanovan/JMusic
	@SuppressWarnings("deprecation")
	public static void playTheSong() 
	{
		if (varContainer.isFirstTime() == true)
		{
			varContainer.setFirstTime(false);
			JMusicPlayerList list = new JMusicPlayerList();
		
		
		
			String DNAME = varContainer.getSavVar().getdName();
			JMusicSong song = new JMusicSong(1, DNAME, "", "", "");


			list.addSongToPlayerList(song);
		

			MusicPlayerControl.initMusicPlayer(varContainer.getSavVar().getPfad());
		
		
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
		
		
	
	    
	  

		
        
        
		
	}
	
	//alarm clock on?
	public static boolean checkTime()
	{
		try
		{
			for(varContainer.setI(0);varContainer.getI()<varContainer.getSavVar().sizeOfEndTimes();varContainer.setI(varContainer.getI() + 1))
			{
				
				if(getTimer(varContainer.getI())==(long)0)
				{
					
				
					return(true);
				}
				
			}
			
		}
		catch (Exception e)
		{
			return(false);
		}
	
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
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
				System.out.print(printTime());
				Thread.currentThread();
			
				//Thread sleeping for 1 sec
				Thread.sleep(1000);
			
				
			}
			if(checkTime()==true)
			{
				//saveSettings.deleteEndTime(varContainer.i);
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
				System.out.println(varContainer.getSavVar().getNameOfWecker(varContainer.getI()));
				playTheSong();
			
				
				Thread.currentThread();
			
				
			}	
	
			
		}
		catch (Exception e)
		{
			System.out.println("Exception in Thread Sleep :"+e);
		}
	}

}
