package Command;

import ChronoTimer.ChronoTimer;
//import


public class On implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChronoTimer.powerOn();
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
