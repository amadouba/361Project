package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;

public class Add  implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(int n) {
		// TODO Auto-generated method stub
		ChronoTimer.addCompetitor(new Competitor(n));
	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub

	}

}
