import org.junit.*;


public ChronoTest{
	
	private Competitor[] comps = {new Competitor(1), new Competitor(2), new Competitor(3)};
	public void testResetOnCompetitors(){
		ChronoTimer.powerOn();
		ChronoTimer.addCompetitor(comps[0]);
		ChronoTimer.addCompetitor(comps[1]);
		ChronoTimer.addCompetitor(comps[2]);
		ChronoTimer.start();
		ChronoTimer.start();
		Chronotimer.finsih();
		// competitior in toStart , ToFinish, and Finished
		ChronoTimer.reset();
		AssertEqual("reset not removing waiting competitors", 0, ChronoTimer.toStart.size());
		AssertEqual("reset not removing competitors currently racing", 0, ChronoTimer.toFinish.size());
		AssertEqual("reset not removing completed racers", 0, ChronoTimer.completedRacers.size());
	}
	public void testResetOnEvent(){
	    ChronoTimer.changeEvent("GRP");
	    ChronoTimer.reset();
	    //i don't know if this actually works
	    AssertTrue("reset not resetting Event", ChronoTimer.typeEvent instanceof IndEvent); 
	}
	public void testPowerOnCompetitors(){
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    ChronoTimer.reset();
	    ChronoTimer.addCompetitor(comps[0]);
	    ChronoTimer.addCompetitor(comps[1]);
	    ChronoTimer.addCompetitor(comps[2]);
	    ChronoTimer.start();
	    ChronoTimer.start();
	    Chronotimer.finsih();
	    // competitior in toStart , ToFinish, and Finished 
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    AssertEqual("Power not removing waiting competitors", 0, ChronoTimer.toStart.size());
	    AssertEqual("Power not removing competitors currently racing", 0, ChronoTimer.toFinish.size());
	    AssertEqual("Power not removing completed racers", 0, ChronoTimer.completedRacers.size());

	}
	public void testPowerOnEvent(){
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    ChronoTimer.reset();
	    ChronoTimer.changeEvent("GRP");
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    AssertTrue("Power not resetting Event", ChronoTimer.typeEvent instanceof IndEvent);
	}
	public void testChangeEvent(){
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    ChronoTimer.reset();
	    ChronoTimer.changeEvent("GRP");
	    AssertTrue("Event type is not changing", ChronoTimer.typeEvent instanceof IndEvent);
	}
	
}
