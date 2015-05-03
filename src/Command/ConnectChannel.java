package Command;

import ChronoTimer.ChronoTimer;

public class ConnectChannel implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub
		String [] newS = s.split(" ");
		ChronoTimer.connectChannel(newS[0], Integer.parseInt(newS[1]));
	}

}
