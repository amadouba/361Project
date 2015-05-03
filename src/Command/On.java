package Command;

import ChronoTimer.Channel;
import ChronoTimer.ChronoTimer;
//import


public class On implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChronoTimer.power = true ;
		if (ChronoTimer.run == 1 ) ChronoTimer.logStr [ChronoTimer.run-1] = "[" ;
		int i = 0 ;
		for (;i < 8 ; i++)
			ChronoTimer.channels[i] = new Channel (i+1);
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
