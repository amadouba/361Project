
public class ParIndEvent extends ChronoTimer implements EventInterface {
int ch = 0 ;
IndEvent n1 ;
IndEvent n2 ;
IndEvent n3 ;
IndEvent n4 ;

	ParIndEvent (){
			n1 = new IndEvent (1);
			n2 = new IndEvent (2);
			n3 = new IndEvent (3);
			n4 = new IndEvent (4);
	} 
	@Override
	public Competitor[] st() {
		// TODO Auto-generated method stub
		switch (ch){
		  case 1 :  return n1.st(); 
		  case 3 : return n2.st() ;
		  case 5 : return n3.st();
		  case 7: return n4.st() ;
		}
		return n1.st();
	}

	@Override
	public Competitor[] fn() {
		// TODO Auto-generated method stub
		switch (ch){
		  case 2 :  return n1.fn(); 
		  case 4 : return n2.fn() ;
		  case 6 : return n3.fn();
		  case 8: return n4.fn() ;
		}
		return n1.fn();
	}
	

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
