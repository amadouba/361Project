import java.util.LinkedList;
import java.util.Queue;



public class IndEvent   extends ChronoTimer  implements EventInterface{
	Queue <Competitor>finish = new LinkedList<Competitor>() ; 
	IndEvent (){
		channels [0] = new Channel (1);
		channels [1] = new Channel (2);
		toFinish.add(finish);
	}
	IndEvent (int i ){
		channels [(2*i)-2] = new Channel ((2*i) -1);
		channels [(2*i)-1] = new Channel ((2*i));
		toFinish.add(finish);
		
	}
	
	public int [] st()  //start, finish, dnf, and cancel are either called manually (in driver/test class)
	{														        // or called in trigger() (in Channel)
		
		Competitor c = toStart.remove(0); 
		c.setStartTime(Time.getCurrentTime());
		finish.add(c);
		
		int a [] = {c.getNumber()}  ;
		return  a;

	}
	/**Takes the first competitor to have started the run and finishes it
	 * @PRECONDITION system is on and there are competitors running
	 */
	public int[] fn()
	{
		if(finish.peek() == null) throw new IllegalStateException ("No competitor running") ;

		Competitor c = finish.remove();
		c.setFinishTime(Time.getCurrentTime());
		completedRacers.add(c);
		
		int a [] = {c.getNumber()}  ;
		return  a;

	}
	@Override
	public void TriggerCh(int index) {
		// TODO Auto-generated method stub
		channels[index-1].channelTrigger();
		
	}
	@Override
	public int[] cancl() {
		// TODO Auto-generated method stub
		if(finish.peek() == null) throw new IllegalStateException("No competitor running") ;

		//toStart.set(0,toFinish.remove().setStartTime(0));
		Competitor c = finish.remove();   
		c.setStartTime(0.0);  //clear the start time
		toStart.add(0, c);   //adds the canceled racer back to the head of toStart so they can redo the start.
		
		int a [] = {c.getNumber()}  ;
		return  a;
	}
	@Override
	public int [] dnfinish() {
		// TODO Auto-generated method stub
				Competitor c = finish.remove();
				c.setFinishTime(Double.NaN); //maybe add "did not finish" variable in competitor? TODO TODO TODO
				completedRacers.add(c);
				
				int a [] = {c.getNumber()}  ;
				return  a;
	}
	
	
}
