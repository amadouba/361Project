package ChronoTimer;
import org.junit.internal.runners.statements.RunAfters;

import Command.*;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class ChronoTest{
	//setup
	private Competitor[] comps = {new Competitor(1), new Competitor(2), new Competitor(3)};
	private Command start = new Start();
	private Command finish = new Finish();
	private Command add = new Add();
	private Command cancel = new Cancel();
	private Command changeEvent = new ChangeEvent();
	private Command connectChannel = new ConnectChannel();
	private Command dnf = new DNF();
	private Command endRun = new EndRun();
	private Command export = new Export();
	private Command newRun = new NewRun();
	private Command off = new Off();
	private Command on = new On();
	private Command print = new Print();
	private Command reset = new Reset();
	private Command setTime = new SetTime();
	private Command swap = new Swap();
	private Command trig = new Trig();
	
	public void testResetOnCompetitors(){
		ChronoTimer.powerOn();
		ChronoTimer.addCompetitor(comps[0]);
		ChronoTimer.addCompetitor(comps[1]);
		ChronoTimer.addCompetitor(comps[2]);
		ChronoTimer.start();
		ChronoTimer.start();
		ChronoTimer.finsih();
		// competitior in toStart , ToFinish, and Finished
		ChronoTimer.reset();
		assertEquals(0, ChronoTimer.toStart.size());
		assertEquals("reset not removing competitors currently racing", 0, ChronoTimer.toFinish.size());
		assertEquals("reset not removing completed racers", 0, ChronoTimer.completedRacers.size());
	}
	
	public void testResetOnEvent(){
	    ChronoTimer.changeEvent("GRP");
	    ChronoTimer.reset();
	    //i don't know if this actually works
	    assertTrue("reset not resetting Event", ChronoTimer.typeEvent instanceof IndEvent); 
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
	    assertEquals("Power not removing waiting competitors", 0, ChronoTimer.toStart.size());
	    assertEquals("Power not removing competitors currently racing", 0, ChronoTimer.toFinish.size());
	    assertEquals("Power not removing completed racers", 0, ChronoTimer.completedRacers.size());

	}
	public void testPowerOnEvent(){
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    ChronoTimer.reset();
	    ChronoTimer.changeEvent("GRP");
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    assertTrue("Power not resetting Event", ChronoTimer.typeEvent instanceof IndEvent);
	}
	public void testChangeEvent(){
	    ChronoTimer.powerOff();
	    ChronoTimer.powerOn();
	    ChronoTimer.reset();
	    ChronoTimer.changeEvent("GRP");
	    assertTrue("Event type is not changing", ChronoTimer.typeEvent instanceof IndEvent);
	}
	
}
