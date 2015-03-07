
import java.util.*;
public class ChronoTimer {

	private static boolean power;   //true = on, false = off
	private static Channel [] channels = {new Channel(1), new Channel(2)};  //2 channels for first sprint, initialized to 8 disarmed channels	
	private static ArrayList<Competitor> toStart = new ArrayList<Competitor>();  //the racers who have not yet started
	private static Queue<Competitor> toFinish  = new LinkedList<Competitor>();   //the racers who have started but not finished yet
	private static ArrayList<Competitor> completedRacers = new ArrayList<Competitor>();   //racers who have finished   
	//private static Time time;  //used for recording the system time at any given moment in time


	public static void powerOn()
	{
		power = true;
	}

	public static void powerOff()
	{
		power = false;
	}

	public static void exit()
	{
		if(power == true)
			System.exit(0);   //might need to be redone, not sure what exit is supposed to do
	}

	public static void reset()
	{
		//default values
		if(power == true)
		{
			toStart.clear();
			toFinish.clear();
			completedRacers.clear();
			disarmAll();
		}
	}
/**
 * Connects a sensor to a channel
 * @param type the type of sensor to be connected
 * @param index the channel 
 */
	public static void connectChannel(String type, int index)   // we don't need this, all 8 channels are connected by default
	{
		if(index>channels.length||index<1) throw new IllegalArgumentException(channels.length + " Channels");
		channels[index-1].connectSensor(new Sensor(type));
	}

	public static void armChannel(int index)
	{
		if(index>channels.length) throw new IllegalArgumentException(channels.length + " Channels");
		if(channels[index-1] == null) throw new IllegalArgumentException();
		channels[index-1].arm();

	}

	public static void disarmChannel(int index)
	{
		if(index>channels.length) throw new IllegalArgumentException(channels.length + " Channels");
		if(channels[index-1] == null) throw new IllegalArgumentException();
		channels[index-1].disArm();
	}

	/**Disarms all the channels
	 * Precondition: The system must be on 
	 * 
	 */
	public static void disarmAll()
	{
		if(!power) throw new IllegalStateException();

		for (Channel ch : channels){
			if(ch == null);
			else
				ch.disArm();
		}

	}
	
	public static void TriggerChannel (int index){
		channels[index-1].channelTrigger();
	}
	/**
	 * Switch the state of a channel from armed/disarmed to disarmed/armed
	 * @param index represents the channel
	 */
	public static void toggleChannel (int index){
		channels[index - 1].toggle();
	}


	/**
	 * adds a competitor as the next to start 
	 * Precondition: System must be on
	 * @param c the competitor
	 */
	public static void addCompetitor(Competitor c)
	{
		if(!power) throw new IllegalStateException("Timer is OFF");
		if(toStart != null && toStart.contains(c)) throw new IllegalArgumentException();
		toStart.add(c);    	   	
	}
	
	/**Takes the next competitor in line and set its start time
	 * @precondition System is on and the queue of competitors isnt empty
	 * @postcondition the first competitor in line has been added to the line for finish
	 * 
	 */
	public static void start()  //start, finish, dnf, and cancel are either called manually (in driver/test class)
	{														        // or called in trigger() (in Channel)
		if(!power) throw new IllegalStateException("Timer is OFF");
		if(toStart.isEmpty()) throw new IllegalStateException("NO Competitor in queue");

		Competitor c = toStart.remove(0); 
		c.setStartTime(Time.getCurrentTime());
		toFinish.add(c);

	}
	/**Takes the first competitor to have started the run and finishes it
	 * @PRECONDITION system is on and there are competitors running
	 */
	public static void finish()
	{
		if(!power) throw new IllegalStateException("Timer is OFF");
		if(toFinish.peek() == null) throw new IllegalStateException("No competitor");


		//completedRacers.add(toFinish.remove().setFinishTime(Time.getCurrentTime()));

		Competitor c = toFinish.remove();
		c.setFinishTime(Time.getCurrentTime());
		completedRacers.add(c);

	}
/**Marks the next running competitor as did not finish
 * 
 */
	public static void dnf()
	{
		if(!power) throw new IllegalStateException("Timer is OFF");
		if(toFinish.peek() == null) throw new IllegalStateException();

		//completedRacers.add(toFinish.remove().setFinishTime(-1));
		Competitor c = toFinish.remove();
		c.setFinishTime(Double.NaN); //maybe add "did not finish" variable in competitor? TODO TODO TODO
		completedRacers.add(c);
	}

	public static void cancel()
	{
		if(!power) throw new IllegalStateException();
		if(toFinish.peek() == null) throw new IllegalStateException();

		//toStart.set(0,toFinish.remove().setStartTime(0));
		Competitor c = toFinish.remove();   
		c.setStartTime(0.0);  //clear the start time
		toStart.set(0, c);   //adds the canceled racer back to the head of toStart so they can redo the start.

	}

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
	public static void print()
	{
		if(!power) throw new IllegalStateException();
		System.out.println("Run \t BIB \t TIME");
		for (Competitor c : completedRacers) {
			//System.out.println(c.getNumber() + ": " + c.getStartTime());
			if(!c.isDNF())
				System.out.println("1 \t" + c.getNumber() + "\t" + String.format("%.2f", c.calculateTotalTime()));
			else
				System.out.println("1 \t" + c.getNumber() + "\t" + "DNF");
		}

	}
}