package Command;

import ChronoTimer.Time;

public class SetTime implements Command {

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
		Time.setStartTime(Time.fromString(s));

	}

}
