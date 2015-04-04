
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
  		
		
	
		//Builds GUI 
		 new ChronoTimerGUI ();
		
	
	// Scanner Loop for input
		
	c : while(loop==true  ){
	      try {
	    	if (latency > 0) { latency = (latency >= 2000 ) ? 1000 :  latency +200 ; Thread.sleep(latency);  }    // when simulating with a file as input
			ts = Time.getCurrentTime();
			System.out.println(Time.toString(ts)+ "\t" + s);
			if (stdIn.hasNextLine() ) s = stdIn.nextLine()  ;
			
			if(s == null || s.equals("EXIT")) {
				loop = false;
				stdIn.close();
			}			
			else{			
				ChronoTimer.log(ts, s);

				InputParser.parseInput(s);
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
	
	
	
	

}