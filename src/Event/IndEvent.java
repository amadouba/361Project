package Event;
import java.util.ArrayList;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;
import ChronoTimer.Time;



public class IndEvent   extends ChronoTimer  implements EventInterface{
	ArrayList <Competitor>finish = new ArrayList<Competitor>() ; 
	
	public IndEvent (){
		toFinish.add(finish);
		grant = Integer.MAX_VALUE ;
	}
	
	
	public Competitor[] st()  //start, finish, dnf, and cancel are either called manually (in driver/test class)
	{														        // or called in trigger() (in Channel)
		
		Competitor c = toStart.remove(0); 
		c.setStartTime(Time.getCurrentTime());
		finish.add(c);
		
		Competitor a [] = {c}  ;
		return  a;

	}
	/**Takes the first competitor to have started the run and finishes it
	 * @PRECONDITION system is on and there are competitors running
	 */
	public Competitor[] fn()
	{
		if(finish.isEmpty()) throw new IllegalStateException ("No competitor running") ;

		Competitor c = finish.remove(0);
		c.setFinishTime(Time.getCurrentTime());
		completedRacers.add(c);
		
		Competitor a [] = {c}  ;
		return  a;

	}
	
	 public void swap(){
		    Competitor a = finish.remove(0);
		    Competitor b = finish.remove(1);
		    finish.add(0,b);
		    finish.add(1,a);
		}
	 
	@Override
	public void TriggerCh(int index) {
		// TODO Auto-generated method stub
		
		channels[index-1].channelTrigger();
		
	}
	@Override
	public Competitor[] cancl() {
		// TODO Auto-generated method stub
		if(finish.isEmpty()) throw new IllegalStateException("No competitor running") ;

		//toStart.set(0,toFinish.remove().setStartTime(0));
		Competitor c = finish.remove(0);   
		c.setStartTime(0.0);  //clear the start time
		toStart.add(0, c);   //adds the canceled racer back to the head of toStart so they can redo the start.
		
		Competitor a [] = {c}  ;
		return  a;
	}
	@Override
	public Competitor[] dnfinish() {
		// TODO Auto-generated method stub
		if(finish.isEmpty()) throw new IllegalStateException("No competitor running") ;

				Competitor c = finish.remove(0);
				c.setFinishTime(Double.NaN); //maybe add "did not finish" variable in competitor? TODO TODO TODO
				completedRacers.add(c);
				
				Competitor a [] = {c}  ;
				return  a;
	}///
	
	
}
