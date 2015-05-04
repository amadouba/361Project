package Command;

import ChronoTimer.ChronoTimer;

public class Reset implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//default values
				if(ChronoTimer.power == true)
				{
					ChronoTimer.toStart.clear();
					ChronoTimer.toFinish.clear();
					ChronoTimer.completedRacers.clear();
					
					ChronoTimer.disarmAll();
				}
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
