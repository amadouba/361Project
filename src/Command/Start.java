package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;



/**Takes the next competitor in line and set its start time
 * @precondition System is on and the queue of competitors isnt empty
 * @postcondition the first competitor in line has been added to the line for finish
 * 
 */
public class Start implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException("Timer is OFF");
		if(ChronoTimer.toStart.isEmpty()) throw new IllegalStateException("NO Competitor in queue");
        if(ChronoTimer.channels[0].isArmed() == false)  throw new IllegalStateException("Not enough channels connected");

        ChronoTimer.numbers = ChronoTimer.typeEvent.st();
		for(Competitor c : ChronoTimer.numbers)
		{
			c.setRunNumber(ChronoTimer.run);
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
