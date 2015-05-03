package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;

public class Cancel implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException("Timer is OFF") ;
		
		Competitor[] b = ChronoTimer.typeEvent.cancl();
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
