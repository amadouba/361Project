package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;


/**
 * adds a competitor as the next to start 
 * Precondition: System must be on
 * @param c the competitor
 */
public class Add  implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(int n) {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException("Timer is OFF");
		Competitor c = new Competitor(n);
		if(ChronoTimer.toStart != null && ChronoTimer.toStart.contains(c)) throw new IllegalStateException("Competitor already in queue");
		
		ChronoTimer.toStart.add(c);
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub

	}

}
