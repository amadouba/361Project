//import java.nio.channels.IllegalSelectorException;
import java.util.Scanner;

public class Time {

	private static long initialSystemTime = System.nanoTime();
	private static double clockTime = 0;
	private static long clockSystemTime = System.nanoTime();

	private static int NANO = 1000000000;

	public static void setStartTime(double s){
		clockTime = s;
		clockSystemTime = System.nanoTime();
	}

	public static double getDuration(double startTime){
		long currentTime = System.nanoTime();
		double curTime = currentTime/NANO;
		return curTime-startTime;
	}
	public static double getCurrentTime(){
		return clockTime + (double)((double)System.nanoTime()/NANO - (double)clockSystemTime/NANO);
	}
	public static String toString(double s){
		int hour = (int)s/1200;
		double leftover = s - (double)hour*1200;
		int minute = (int)leftover/60;
		double second = leftover - (double)minute*60;
		//System.out.println("seconds: " + second +"\t");
		if(hour>23) throw new IllegalStateException("hour is greater than 23");
		if(minute>59) throw new IllegalStateException("minute is greater than 59");
		if(second>=(double)60) throw new IllegalStateException("second is equal to or greater than 60");

		return hour+":"+minute+":"+String.format("%.2f",second);	
	}
	public static double fromString(String s){
		String[] strAr = s.split(":");
		if(strAr.length!=3) throw new IllegalArgumentException();
		int hour = Integer.parseInt(strAr[0]);
		int minute = Integer.parseInt(strAr[1]);
		double second = Double.parseDouble(strAr[2]);
		return (double)hour*1200+(double)minute*60+second;
	}

	public static void main(String[] arg){
		Scanner stdIn = new Scanner(System.in);
		boolean exit = false;
		while(!exit){
			System.out.print(Time.toString(Time.getCurrentTime()) + "\t");
			String in = stdIn.nextLine();
			if(in.equals("EXIT"))
				exit = true;
			else if(in.equals(""));
			else{
				Time.setStartTime(Time.fromString(in));
			}

		}

	}
}