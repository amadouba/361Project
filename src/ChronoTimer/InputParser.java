package ChronoTimer;
import java.util.Observable;
import java.util.Observer;

import Command.*;


public class InputParser extends ChronoTimerGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Command c ;
	InputParser(Observer updateView){
		
		//addObserver(updateView);
	}
	public static void parseInput(String s)throws IllegalArgumentException{
		String[] strAr = s.split(" ");
		String on = "";
		int flag = 0 ;
		//ON, OFF, START, FIN, DNF, CANCEL, PRINT
		if(strAr.length==1){
			if(strAr[0].equals("ON")){
				c = new On();
				c.execute();
				ChronoTimer.powerOn();
				repaintCP();
			}
			else if(strAr[0].equals("OFF")){
				c = new Off();
				c.execute();
				ChronoTimer.powerOff();
				repaintCP();
			}
			else if(strAr[0].equals("SWAP")) {
				c = new Swap();
				c.execute();
			}
			else if(strAr[0].equals("Power"))
				 if ( ChronoTimer.getPower() ){
					 c = new On();
					 c.execute();
				 }else{
					 c = new Off();
					 c.execute();
				 }
			else if(strAr[0].equals("START")){
				c = new Start();
				c.execute();
				if (!timer.isRunning()) timer.start();
				repaintQP();
			}
			else if(strAr[0].equals("FIN")){
				c = new Finish();
				c.execute();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				repaintRP();
				repaintFP();
			}
			else if(strAr[0].equals("DNF")){
				c = new DNF();
				c.execute();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				repaintRP();
				repaintFP();
			}
			else if(strAr[0].equals("CANCEL")){
				c = new Cancel();
				c.execute();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				repaintRP();
				repaintFP();
			}
			else if(strAr[0].equals("PRINT")){
				c = new Print();
				c.execute();
			}
				
			
			else if(strAr[0].equals("NEWRUN")){
				c = new NewRun();
				c.execute();
			}
			else if(strAr[0].equals("ENDRUN")){
				c = new EndRun();
				c.execute();
			}
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		//TOGGLE, NUM, TIME
		//if(strAr.length == 2)
		else if(strAr.length==2){
			if(strAr[0].equals("TOGGLE")){
				c = new Toggle();
				c.execute(Integer.parseInt(strAr[1]));
				repaintChP();
			}
			else if(strAr[0].equals("NUM")){
				c =new Add();
				c.execute(Integer.parseInt(strAr[1]));
			    repaintQP();
			}
			else if(strAr[0].equals("EXPORT")){
				c = new Export();
				c.execute(Integer.parseInt(strAr[1]));
			}
			else if(strAr[0].equals("EVENT")){
				c = new ChangeEvent();
				c.execute (strAr[1]);
			}			
			else if(strAr[0].equals("TIME")){
				c = new SetTime();
				c.execute(strAr[1]);
			}
			else if (strAr[0].equals("TRIG")){
				c = new Trig();
				c.execute(Integer.parseInt(strAr[1]));
				if (!timer.isRunning()) {timer.start(); repaintQP();}                      // in this case it is a start trigger
				else {                                                                    // in this case it is a finish trigger
					if (ChronoTimer.toFinish.isEmpty()) timer.stop();
					repaintRP();
					repaintFP(); 
				}                                                
			}
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		//CONN
		else if(strAr.length==3){
			if(strAr[0].equals("CONN")){
				c = new ConnectChannel();
				c.execute(strAr[1] +" "+ strAr[2]);
			}
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		else
			throw new IllegalArgumentException("ILLEGAL COMMAND");///

	}

}
