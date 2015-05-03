package Command;

import ChronoTimer.ChronoTimer;

public class Trig implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(int index) {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException("Timer is OFF");
		ChronoTimer.typeEvent.TriggerCh(index);
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub
		
		
	}

}
