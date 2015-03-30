
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JFrame {



private static double ts ;
private static Timer timer ;
private final static ChronoTimerGUI m = new ChronoTimerGUI () ;
private static int delay = 10 ;


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner stdIn ;
		boolean loop = true;
		ChronoTimer.logStr[0] = "";
		int latency = 0  ; 
		String s = "" ;
	
		//In case of a test file input
		if (args.length > 0 ){
			stdIn = new Scanner (new File (args[0]));
			latency = 1000 ;
		}
		else 
			stdIn = new Scanner(System.in);
  
		//Builds the GUI 
		
		m.setSize(800, 800);
		m .setVisible(true);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up a timer here for frame updates 
	   //Updates every 100th of second while a competitor is running 
	  //Timer starts when start is called, only if timer is not running already
	 //       stops when finish is called, only if Chronotimer.toFinish is empty
		
		ActionListener task = new ActionListener (){
			public void actionPerformed (ActionEvent e){
				m.repaintRP();
			}
		};
		timer = new Timer(delay,task);
		
		
	
	// Scanner Loop for input
		
	c : while(loop==true  ){
	      try {
	    	if (latency > 0) { latency = (latency >= 2000 ) ? 1000 :  latency +200 ; Thread.sleep(latency);  }    // when simulating with a file as input
			ts = Time.getCurrentTime();
			System.out.println(Time.toString(ts)+ "\t" + s);
			s = stdIn.nextLine()  ;
			
			if(s == null || s.equals("EXIT")) {
				loop = false;
				stdIn.close();
			}			
			else{			
				ChronoTimer.log(ts, s);

				parseInput(s);
				
			}
			
			// Throws Exception and gets back in the loop
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

	System.exit(1);
		
	}
	
     // GUI updates. Updates the "Queue" panel when {start,num,set,clr} is called
	//               Updates the "Running" panel when {finish,dnf,cancel,start} is called only if timer is not running
   //                Updates the "Finished" panel when {finish,dnf,cancel} is called 
	
	
	
	
	// Input Parsing
	
	private static void parseInput(String s)throws IllegalArgumentException{
		String[] strAr = s.split(" ");
		//ON, OFF, START, FIN, DNF, CANCEL, PRINT
		if(strAr.length==1){
			if(strAr[0].equals("ON"))
				ChronoTimer.powerOn();
			else if(strAr[0].equals("OFF"))
				ChronoTimer.powerOff();
			else if(strAr[0].equals("START")){
				ChronoTimer.start();
				if (!timer.isRunning()) timer.start();
				m.repaintQP();
			}
			else if(strAr[0].equals("FIN")){
				ChronoTimer.finish();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				m.repaintRP();
				m.repaintFP();
			}
			else if(strAr[0].equals("DNF")){
				ChronoTimer.dnf();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				m.repaintRP();
				m.repaintFP();
			}
			else if(strAr[0].equals("CANCEL")){
				ChronoTimer.cancel();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				m.repaintRP();
				m.repaintFP();
			}
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
			else if(strAr[0].equals("NUM")){
			    ChronoTimer.addCompetitor(new Competitor(Integer.parseInt(strAr[1])));
			    m.repaintQP();
			}
			else if(strAr[0].equals("TIME"))
				Time.setStartTime(Time.fromString(strAr[1]));
			else if (strAr[0].equals("TRIG")){
				ChronoTimer.TriggerChannel(Integer.parseInt(strAr[1]));
				if (!timer.isRunning()) {timer.start(); m.repaintQP();}                      // in this case it is a start trigger
				else {                                                                    // in this case it is a finish trigger
					if (ChronoTimer.toFinish.isEmpty()) timer.stop();
					m.repaintRP();
					m.repaintFP(); 
				}                                                
			}
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