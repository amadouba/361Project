import java.util.Scanner;
public class Driver {







	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdIn = new Scanner(System.in);
		boolean loop = true;
		while(loop==true){
			System.out.print(Time.toString(Time.getCurrentTime())+ "\t");
			String s = stdIn.nextLine();
			if(s.equals("EXIT")) loop = false;
			else{
				parseInput(s);
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
			else
				throw new IllegalArgumentException();
		}
		//TOGGLE, NUM, TIME
		//if(strAr.length == 1){}
		else if(strAr.length==2){
			if(strAr[0].equals("TOGGLE"))
				ChronoTimer.armChannel(Integer.parseInt(strAr[1]));
			else if(strAr[0].equals("NUM"))
				ChronoTimer.addCompetitor(new Competitor(Integer.parseInt(strAr[1])));

			else if(strAr[0].equals("TIME"))
				Time.setStartTime(Time.fromString(strAr[1]));
			else
				throw new IllegalArgumentException();
		}
		//CONN
		else if(strAr.length==3){
			if(strAr[0].equals("CONN"))
				ChronoTimer.connectChannel(strAr[1], Integer.parseInt(strAr[2]));
			else
				throw new IllegalArgumentException();
		}
		else
			throw new IllegalArgumentException();

	}

}