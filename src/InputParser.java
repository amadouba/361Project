
public class InputParser extends ChronoTimerGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static void parseInput(String s)throws IllegalArgumentException{
		String[] strAr = s.split(" ");
		//ON, OFF, START, FIN, DNF, CANCEL, PRINT
		if(strAr.length==1){
			if(strAr[0].equals("ON")){
				ChronoTimer.powerOn();
				repaintCP();
			}
			else if(strAr[0].equals("OFF")){
				ChronoTimer.powerOff();
				repaintCP();
			}
			else if(strAr[0].equals("Power"))
				 if ( ChronoTimer.getPower() ) ChronoTimer.powerOff() ; else  ChronoTimer.powerOn() ;
			else if(strAr[0].equals("START")){
				ChronoTimer.start();
				if (!timer.isRunning()) timer.start();
				repaintQP();
			}
			else if(strAr[0].equals("FIN")){
				ChronoTimer.finish();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				repaintRP();
				repaintFP();
			}
			else if(strAr[0].equals("DNF")){
				ChronoTimer.dnf();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				repaintRP();
				repaintFP();
			}
			else if(strAr[0].equals("CANCEL")){
				ChronoTimer.cancel();
				if (ChronoTimer.toFinish.isEmpty()) timer.stop();
				repaintRP();
				repaintFP();
			}
			else if(strAr[0].equals("PRINT"))
				ChronoTimer.print();
			
			else if(strAr[0].equals("NEWRUN"))
				ChronoTimer.newRun();
			else if(strAr[0].equals("ENDRUN"))
				ChronoTimer.endRun();
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		//TOGGLE, NUM, TIME
		//if(strAr.length == 2)
		else if(strAr.length==2){
			if(strAr[0].equals("TOGGLE")){
				ChronoTimer.toggleChannel(Integer.parseInt(strAr[1]));
				repaintChP();
			}
			else if(strAr[0].equals("NUM")){
			    ChronoTimer.addCompetitor(new Competitor(Integer.parseInt(strAr[1])));
			    repaintQP();
			}
			else if(strAr[0].equals("EXPORT"))
				ChronoTimer.export(Integer.parseInt(strAr[1]));
			else if(strAr[0].equals("EVENT"))
				ChronoTimer.changeEvent(strAr[1]);
			else if(strAr[0].equals("TIME"))
				Time.setStartTime(Time.fromString(strAr[1]));
			else if (strAr[0].equals("TRIG")){
				ChronoTimer.TriggerChannel(Integer.parseInt(strAr[1]));
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
			if(strAr[0].equals("CONN"))
				ChronoTimer.connectChannel(strAr[1], Integer.parseInt(strAr[2]));
			else
				throw new IllegalArgumentException ("ILLEGAL COMMAND");
		}
		else
			throw new IllegalArgumentException("ILLEGAL COMMAND");

	}

}
