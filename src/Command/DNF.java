package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;


/**Marks the next running competitor as did not finish
 * 
 */
public class DNF implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException ("Timer is OFF") ;

		
		Competitor[] b = ChronoTimer.typeEvent.dnfinish();
		ChronoTimer.log (b) ;
	}

	@Override
	public void execute(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub
		
	}

}
