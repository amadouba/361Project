//import java.nio.channels.Channel;
import java.util.*;
public class ChronoTimer {

	private static boolean power;   //true = on, false = off
	private static Channel [] channels = {new Channel(0), new Channel(1)};  //2 channels for first sprint, initialized to 8 disarmed channels	
	private static ArrayList<Competitor> toStart;  //the racers who have not yet started
	private static Queue<Competitor> toFinish;   //the racers who have started but not finished yet
	private static ArrayList<Competitor> completedRacers;   //racers who have finished   
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

	public static void connectChannel(String type, int index)   // we don't need this, all 8 channels are connected by default
	{
		if(index>channels.length||index<1) throw new IllegalArgumentException();
		channels[index-1].connectSensor(new Sensor(type));
	}

	public static void armChannel(int index)
	{
		if(index>=channels.length) throw new IllegalArgumentException();
		if(channels[index] == null) throw new IllegalArgumentException();
		channels[index].arm();

	}

	public static void disarmChannel(int index)
	{
		if(index>=channels.length) throw new IllegalArgumentException();
		if(channels[index] == null) throw new IllegalArgumentException();
		channels[index].disArm();
	}

	public static void disarmAll()
	{
		if(!power) throw new IllegalStateException();

		for (Channel ch : channels){
			if(ch == null);
			else
				ch.disArm();
		}

	}


	public static void addCompetitor(Competitor c)
	{
		if(!power) throw new IllegalStateException();
		if(toStart.contains(c)) throw new IllegalArgumentException();
		toStart.add(c);    	   	
	}

	public static void start()  //start, finish, dnf, and cancel are either called manually (in driver/test class)
	{														        // or called in trigger() (in Channel)
		if(!power) throw new IllegalStateException();
		if(toStart.isEmpty()) throw new IllegalStateException();

		Competitor c = toStart.remove(0); 
		c.setStartTime(Time.getCurrentTime());
		toFinish.add(c);

	}

	public static void finish()
	{
		if(!power) throw new IllegalStateException();
		if(toFinish.peek() == null) throw new IllegalStateException();


		//completedRacers.add(toFinish.remove().setFinishTime(Time.getCurrentTime()));

				Competitor c = toFinish.remove();
				c.setFinishTime(Time.getCurrentTime());
				completedRacers.add(c);

	}

	public static void dnf()
	{
		if(!power) throw new IllegalStateException();
		if(toFinish.peek() == null) throw new IllegalStateException();

		//completedRacers.add(toFinish.remove().setFinishTime(-1));
				Competitor c = toFinish.remove();
				c.setFinishTime(-1); //maybe add "did not finish" variable in competitor? TODO TODO TODO
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
	

	public static void print()
	{
		if(!power) throw new IllegalStateException();
		System.out.println("Run /t BIB /t TIME");
		for (Competitor c : completedRacers) {
			if(!c.isDNF())
				System.out.println("1 /t" + c.getNumber() + "/t" + c.calculateTotalTime());
			else
				System.out.println("1 /t" + c.getNumber() + "/t" + "DNF");
		}

	}
}