package Event;
import java.util.ArrayList;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;
import ChronoTimer.Time;


public class GrpEvent extends ChronoTimer implements EventInterface {
	ArrayList <Competitor>finish = new ArrayList <Competitor>() ; 
	public GrpEvent (){
		toFinish.add(finish);
		grant  = Integer.MAX_VALUE ;
	}
	@Override
	public Competitor[] st() {
		// TODO Auto-generated method stub
		if(! finish.isEmpty()) throw new IllegalStateException ("Grp Run must end first");
		Competitor a [] =  new Competitor [toStart.size()]  ;
		int i = 0 ;
		double time = Time.getCurrentTime() ;
		while (!toStart.isEmpty()){
			a[i] = toStart.remove(0); 
			
			a[i].setStartTime(time);
			finish.add(a[i++]);
		}
	
		return  a;
	}

	@Override
	public Competitor[] fn() {
		// TODO Auto-generated method stub
		
		if(finish.isEmpty() ) throw new IllegalStateException ("No competitor running") ;

		Competitor c = finish.remove(0);
		c.setFinishTime(Time.getCurrentTime());
		completedRacers.add(c);
		
		Competitor a [] = {c}  ;
		return  a;
	}

	@Override
	public void TriggerCh(int index) {
		// TODO Auto-generated method stub
		channels[index -1 ].channelTrigger();
	}

	 public void swap(){
		    Competitor a = finish.remove(0);
		    Competitor b = finish.remove(1);
		    finish.add(0,b);
		    finish.add(1,a);
		}
	 
	@Override
	public Competitor[] cancl() {
		// TODO Auto-generated method stub
		if(finish.isEmpty()) throw new IllegalStateException("No competitor running") ;
		
		Competitor [] c = new Competitor [finish.size()] ;
		int i = 0 ;
		
		while (!finish.isEmpty()){
			c [i] = finish.remove(0);   
			c[i].setStartTime(0.0);  //clear the start time
			toStart.add(0, c[i++]);   //adds the canceled racer back to the head of toStart so they can redo the start.

		}
		
		return  c;
	}

	@Override
	public Competitor[] dnfinish() {
		// TODO Auto-generated method stub
		Competitor c = finish.remove(0);
		c.setFinishTime(Double.NaN); //maybe add "did not finish" variable in competitor? TODO TODO TODO
		completedRacers.add(c);
		
		Competitor a [] = {c}  ;
		return  a;
	}

}
///