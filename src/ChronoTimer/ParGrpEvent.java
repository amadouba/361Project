package ChronoTimer;
import java.util.ArrayList;


public class ParGrpEvent extends ChronoTimer implements EventInterface {
	ArrayList <Competitor>finish = new ArrayList<Competitor>() ; 
	int ch = 0 ;
	
	Competitor [] toArray ;
	
	ParGrpEvent (){
		toFinish.add(finish);
		
	}
	@Override
	public Competitor[] st() {
		// TODO Auto-generated method stub
		if (!finish.isEmpty()) throw new IllegalStateException ("ParGrp  Run must end first");
		//Competitor a [] =  new Competitor [toStart.size()]  ;
		toArray = new Competitor [toStart.size()]  ;
		int i = 0 ;
		
		double time = Time.getCurrentTime() ;
		while (!toStart.isEmpty()){
			toArray [i] = toStart.remove(0); 
			
			toArray[i].setStartTime(time);
			finish.add(toArray[i++]);
			
		}
			
		return  toArray;
	}

	@Override
	public Competitor[] fn() {
		// TODO Auto-generated method stub
		switch (ch){
			case 0 : return doFn(1);
		}
		return doFn(ch);
	}
	
	private Competitor [] doFn(int ch) {
		if(finish.isEmpty()) throw new IllegalStateException ("No competitor running") ;
		//if ( toArray [index-1] == null )throw new IllegalArgumentException ("No competitor on this lane");
		
		
        Competitor c = toArray [ch-1] ;
        toArray [ch-1] = null ;
        finish.remove(c);
		
		c.setFinishTime(Time.getCurrentTime());
		completedRacers.add(c);
		
		Competitor a [] = {c}  ;
		return  a;
	}

	@Override
	public void TriggerCh(int index) {
		// TODO Auto-generated method stub
		ch = index ;
		if (finish.isEmpty())
			channels[index - 1 ].channelTrigger();
		else{
			
			log (fn());
		}
			
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

	 public void swap(){}
	 
	@Override
	public Competitor[] dnfinish() {
		// TODO Auto-generated method stub///
		Competitor c = finish.remove(0);
		c.setFinishTime(Double.NaN); //maybe add "did not finish" variable in competitor? TODO TODO TODO
		completedRacers.add(c);
		
		Competitor a [] = {c}  ;
		return  a;
	}

}
