package ChronoTimer;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import Event.EventInterface;
import Event.IndEvent;


public class ChronoTimer {

	public static boolean power;   //true = on, false = off
	public static Channel [] channels =  new Channel [8];  //2 channels for first sprint, initialized to 8 disarmed channels	
	public static ArrayList<Competitor> toStart = new ArrayList<Competitor>();  //the racers who have not yet started
	public static  List <ArrayList<Competitor>> toFinish  = new  ArrayList <ArrayList<Competitor> >();   //the racers who have started but not finished yet
	public static ArrayList<Competitor> completedRacers = new ArrayList<Competitor>();   //racers who have finished   
	public static EventInterface typeEvent  = new IndEvent();
	public static FileOutputStream eventlog ;   
	public static String logStr [] = new String [50] ;  //  50 strings (event logs) for 50 runs
	public static int run  = 1 ;  // default run number
	protected boolean nope = false ;
	
	public static Competitor [] numbers ;    // this field holds the num of the competitors that have been 
	
		
	
	
	public static boolean getPower(){
		return power;
	}
	
	
	public static boolean isArmed(int i){
		return channels[i-1] == null ? false : channels[i-1].isArmed();
	}
	

	public static void armChannel(int index)
	{
		if(power){
		if(index>channels.length) throw new IllegalArgumentException (channels.length + " Channels");
		if(channels[index-1] == null) throw new IllegalArgumentException ("Channel not initialized" );
		channels[index-1].arm();
		}

	}

	public static void disarmChannel(int index)
	{
		if(power){
		if(index>channels.length) throw new IllegalArgumentException (channels.length + " Channels");
		if(channels[index-1] == null) throw new IllegalArgumentException ("Channel not initialized");
		channels[index-1].disArm();
		}
	}

	/**Disarms all the channels
	 * Precondition: The system must be on 
	 * 
	 */
	public static void disarmAll()
	{
		if(!power) throw new IllegalStateException("Timer is OFF") ;

		for (Channel ch : channels){
			if(ch == null);
			else
				ch.disArm();
		}

	}
	
	
	public static void log (double time, String event){
		//logStr [run-1] += Time.toString(time) + "\t" + event +  System.getProperty("line.separator")  ;
	}
	public static void log (Competitor [] number){
		String o= "," ;
		if (logStr [run-1].equalsIgnoreCase("[")) o = "";
		for (Competitor n : number)
		logStr [run-1] += o+"{\"bib\":\"" +n.getNumber() + "\"" +",\"runNumber\":\""+ n.getRunNumber()+ ",\"totalTime\":\""+ (n.isDNF() ? "DNF\"}" : String.format("%.2f", n.calculateTotalTime())+ "\"}" ) ;
	
	}
	
	/**
	public Competitor getCompetitor(int bibNumber)
	{
		for(Competitor c : toStart)
		{
			if(bibNumber == c.getNumber())
				return c;
		}

		for(Competitor c : toFinish)
		{
			if(bibNumber == c.getNumber())
				return c;
		}

		for(Competitor c : completedRacers)
		{
			if(bibNumber == c.getNumber())
				return c;
		}

		return null;
	}
	
	
	
	 */
	

}