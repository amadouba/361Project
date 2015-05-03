package ChronoTimer;
import java.util.Observable;
import java.util.Observer;

import Command.*;


public class InputParser extends Observable {

	public static Command c ;
	InputParser(Observer updateView){
		
		addObserver(updateView);
	}
	public  void parseInput(String s)throws IllegalArgumentException{
		String[] strAr = s.split(" ");
		int flag = 0 ;                      // 1 for repaintCP -- 2 for repaintRP&repaintFP -- 3 for repaintQP -- 4 for repaintChP -- 5 is dependent
		//ON, OFF, START, FIN, DNF, CANCEL, PRINT
		if(strAr.length==1){
			if(strAr[0].equals("ON")){
				c = new On();
				c.execute();
				flag = 1 ;
				
			}
			else if(strAr[0].equals("OFF")){
				c = new Off();
				c.execute();
				flag = 1;
				
			}
			else if(strAr[0].equals("SWAP")) {
				c = new Swap();
				c.execute();
			}
			else if(strAr[0].equals("Power")){
				if ( ChronoTimer.getPower() ){
					 c = new Off();
					 c.execute();
				 }else{
					 c = new On();
					 c.execute();
				 }
				flag = 1;
				
				
			}
				 
			else if(strAr[0].equals("START")){
				c = new Start();
				c.execute();
				flag = 3; 
			}
			else if(strAr[0].equals("FIN")){
				c = new Finish();
				c.execute();
				flag = 2 ;
			}
			else if(strAr[0].equals("DNF")){
				c = new DNF();
				c.execute();
				flag = 2 ;
			}
			else if(strAr[0].equals("CANCEL")){
				c = new Cancel();
				c.execute();
				flag = 2 ;
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
				flag = 4 ;
				
			}
			else if(strAr[0].equals("NUM")){
				c =new Add();
				c.execute(Integer.parseInt(strAr[1]));
				flag = 3 ;
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
				flag = 5 ;
//				                                
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
			
		
		
		setChanged();
		notifyObservers(flag);
	}
	
}
