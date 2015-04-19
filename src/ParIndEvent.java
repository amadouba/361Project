
public class ParIndEvent extends ChronoTimer implements EventInterface {
int ch = 0 ;
IndEvent n1 ;
IndEvent n2 ;


	ParIndEvent (){
			n1 = new IndEvent ();
			n2 = new IndEvent ();
			
	} 
	@Override
	public Competitor[] st() {
		// TODO Auto-generated method stub]
		
		switch (ch){
		  case 1 :  return n1.st(); 
		  case 3 : return n2.st() ;
		 
		}
		return n1.st();
	}

	@Override
	public Competitor[] fn() {
		// TODO Auto-generated method stub
		switch (ch){
		  case 2 :  return n1.fn(); ///
		  case 4 : return n2.fn() ;
		  
		}
		return n1.fn();
	}
	
	 public void swap(){};
	 

	@Override
	public void TriggerCh(int index) {
		// TODO Auto-generated method stub
		
		ch = index ;
		channels[index-1].channelTrigger();
		
	}
	@Override
	public Competitor[] cancl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Competitor[] dnfinish() {
		// TODO Auto-generated method stub
		return null;
	}

}
