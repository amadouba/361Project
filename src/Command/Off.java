package Command;

import ChronoTimer.ChronoTimer;

public class Off implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		new EndRun().execute();
		ChronoTimer.disarmAll();
		ChronoTimer.power = false ;
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
