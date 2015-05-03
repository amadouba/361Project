package Command;

import ChronoTimer.ChronoTimer;


/** Ends the current run
 * verifies if string log at next run is null meaning that is the current run
 * hasn't been ended if not null (open) then it is already been ended but a new run has 
 * not been started
 * @precondition
 * 		a new run has been started but not yet ended
 */
public class EndRun implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if (ChronoTimer.logStr[ ChronoTimer.run ] != null) throw new IllegalStateException("Open a new run first");               // run ended already because otherwise next run would be null
		
		if (!ChronoTimer.logStr[ChronoTimer.run -1].endsWith("]")) ChronoTimer.logStr[ ChronoTimer.run -1] += "]" ;
		ChronoTimer.logStr[ChronoTimer.run] = "[";   	                                  // when we end a run we open a new string log for the next run
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
