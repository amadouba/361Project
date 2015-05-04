package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;
import Event.ParGrpEvent;
import Event.ParIndEvent;


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
		
		
		if (ChronoTimer.typeEvent instanceof ParIndEvent){
			if (ChronoTimer.toStart.size() < 2) ChronoTimer.toStart.add(c);
		}else if (ChronoTimer.typeEvent instanceof ParGrpEvent){
			if (ChronoTimer.toStart.size() < 8) ChronoTimer.toStart.add(c);
		}else
			 ChronoTimer.toStart.add(c);
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub

	}

}
