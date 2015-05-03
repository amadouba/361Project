package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Competitor;

public class Print implements Command {

	public void execute() {
		// TODO Auto-generated method stub
		if(!ChronoTimer.power) throw new IllegalStateException ("Timer is OFF") ;
		System.out.println("Run \t BIB \t TIME");
		for (Competitor c : ChronoTimer.completedRacers) {
			//System.out.println(c.getNumber() + ": " + c.getStartTime());
			if(!c.isDNF())
				System.out.println(c.getRunNumber() + "\t" + c.getNumber() + "\t" + String.format("%.2f", c.calculateTotalTime()));
			else
				System.out.println(c.getRunNumber() + "\t" + c.getNumber() + "\t" + "DNF");
			
			Competitor a [] = {c}  ;
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
