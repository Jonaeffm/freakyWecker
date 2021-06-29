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
	
	public static int getCurrentMinutes()
	{
		return(int)(System.currentTimeMillis()/1000/60)%60;
	}

	public static int getMinutes(int i)
	{
		int time;
		
		
			time =(int) (varContainer.savVar.getEndTime(i)/1000/60)%60;
			//System.out.println(time+" Minuten");
		return time;
	}
	
	
	public static long getTimer(int i)
	{
		long time;
		
		
			time = varContainer.savVar.getEndTime(i)/1000 - System.currentTimeMillis()/1000;
			//System.out.println("hallo");
		return time;
	}
	
	
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
		
		System.out.println("Hauptmenü");
	    System.out.println("Wahl: ");
	    System.out.println();
	    System.out.println("1. Optionen");
		System.out.println("2. Start");
		System.out.println("3. Beenden");
		System.out.println("4. Alarm off");
		
		
	
	    
	  
		//System.exit(0);
		
        
        
		
	}
	
	public static boolean checkTime()
	{
		try
		{
			for(varContainer.i=0;varContainer.i<varContainer.savVar.sizeOfEndTimes();varContainer.i++)
			{
				/*if(!sameMinute(varContainer.i)&&varContainer.minuteChecked==true)
					varContainer.minuteChecked=false;*/
				if(getTimer(varContainer.i)==(long)0)
				{
					//(varContainer.minuteChecked = true;
					//long minuten = getMinutes(varContainer.i);
					//System.out.println("true");
					//saveSettings.deleteEndTime(i);
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
	
	public static String printTime()
	{		
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");    
		Date resultdate = new Date(yourmilliseconds);
		return(sdf.format(resultdate));

	}
	
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
