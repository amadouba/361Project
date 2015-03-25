
import java.util.Scanner;
public class Driver {



private static double ts ;



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdIn = new Scanner(System.in);
		boolean loop = true;
		ChronoTimer.logStr[0] = "";
	
	c : while(loop==true){
	      try {
			ts = Time.getCurrentTime();
			System.out.print(Time.toString(ts)+ "\t");
			String s = stdIn.nextLine();
			ChronoTimer.log(ts, s);
			if(s.equals("EXIT")) {
				loop = false;
				stdIn.close();
			}
			else{
				parseInput(s);
			}
			
	   }catch (IllegalStateException e){
		   ChronoTimer.log(Time.getCurrentTime(), "Illegal State Exception " + e.getMessage());
			continue c ;
		}catch (IllegalArgumentException e){
		   ChronoTimer.log(Time.getCurrentTime(), "Illegal Argument Exception " + e.getMessage());
			continue c ;
		}catch (Exception e){
		   ChronoTimer.log(Time.getCurrentTime(), "Exception " + e.getMessage());
			continue c ;
		}
	      
		}

	
		
	}

	private static void parseInput(String s)throws IllegalArgumentException{
		String[] strAr = s.split(" ");
		//ON, OFF, START, FIN, DNF, CANCEL, PRINT
		if(strAr.length==1){
			if(strAr[0].equals("ON"))
				ChronoTimer.powerOn();
			else if(strAr[0].equals("OFF"))
				ChronoTimer.powerOff();
			else if(strAr[0].equals("START"))
				ChronoTimer.start();
			else if(strAr[0].equals("FIN"))
				ChronoTimer.finish();
			else if(strAr[0].equals("DNF"))
				ChronoTimer.dnf();
			else if(strAr[0].equals("CANCEL"))
				ChronoTimer.cancel();
			else if(strAr[0].equals("PRINT"))
				ChronoTimer.print();
			else if(strAr[0].equals("EXPORT"))
				ChronoTimer.export();
			else if(strAr[0].equals("NEWRUN"))
				ChronoTimer.newRun();
			else if(strAr[0].equals("ENDRUN"))
				ChronoTimer.endRun();
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		//TOGGLE, NUM, TIME
		//if(strAr.length == 1){}
		else if(strAr.length==2){
			if(strAr[0].equals("TOGGLE"))
				ChronoTimer.toggleChannel(Integer.parseInt(strAr[1]));
			else if(strAr[0].equals("NUM"))
				ChronoTimer.addCompetitor(new Competitor(Integer.parseInt(strAr[1])));

			else if(strAr[0].equals("TIME"))
				Time.setStartTime(Time.fromString(strAr[1]));
			else if (strAr[0].equals("TRIG"))
				ChronoTimer.TriggerChannel(Integer.parseInt(strAr[1]));
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		//CONN
		else if(strAr.length==3){
			if(strAr[0].equals("CONN"))
				ChronoTimer.connectChannel(strAr[1], Integer.parseInt(strAr[2]));
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		else
			throw new IllegalArgumentException("ILLEGAL COMMAND");

	}
	
	

}