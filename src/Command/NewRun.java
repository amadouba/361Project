package Command;

import ChronoTimer.ChronoTimer;


/**Opens a new run
 * verifies first if string log at next run is null meaning that the current run has not ended
 * because when it has, string log at next run is always open 
 * @precondition 
 * 		current run has been ended;next string log is not null
 * 
 */
public class NewRun implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if (ChronoTimer.logStr[ChronoTimer.run ] == null && (!ChronoTimer.toStart.isEmpty() || !ChronoTimer.toFinish.isEmpty() || !ChronoTimer.completedRacers.isEmpty())  ) throw new IllegalStateException("End a run first");  // run has not ended because otherwise the next string log would be open
		
		++ChronoTimer.run ;
		if (!ChronoTimer.logStr[ChronoTimer.run-1].equalsIgnoreCase("[")) ChronoTimer.logStr[ChronoTimer.run-1] = "[" ;  
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
