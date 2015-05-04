package Command;

import ChronoTimer.ChronoTimer;

public class Finish implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException("Timer is OFF");
		if(ChronoTimer.channels[1].isArmed() == false)  throw new IllegalStateException("Not enough channels connected");
		
		ChronoTimer.numbers = ChronoTimer.typeEvent.fn();
		ChronoTimer.log (ChronoTimer.numbers ) ;
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
