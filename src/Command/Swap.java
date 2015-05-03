package Command;

import ChronoTimer.ChronoTimer;

public  class Swap implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if (ChronoTimer.toFinish.isEmpty()) throw new IllegalStateException("No Competitor running");
		ChronoTimer.typeEvent.swap();
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
