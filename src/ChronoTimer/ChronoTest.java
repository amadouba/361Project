package ChronoTimer;
import java.util.Iterator;


import org.junit.internal.runners.statements.RunAfters;

import Event.*;
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
		on.execute();
		add.execute(1);
		add.execute(2);
		add.execute(3);
		start.execute();
		start.execute();
		finish.execute();
		// competitior in toStart , ToFinish, and Finished
		reset.execute();
		assertEquals(0, ChronoTimer.toStart.size());
		assertEquals("reset not removing competitors currently racing", 0, ChronoTimer.toFinish.size());
		assertEquals("reset not removing completed racers", 0, ChronoTimer.completedRacers.size());
	}

	public void testResetOnEvent(){
		changeEvent.execute("GRP");
		reset.execute();
		//i don't know if this actually works
		assertTrue("reset not resetting Event", ChronoTimer.typeEvent instanceof IndEvent); 
	}
	public void testPowerOnCompetitors(){
		off.execute();
		on.execute();
		reset.execute();
		add.execute(1);
		add.execute(2);
		add.execute(3);
		start.execute();
		start.execute();
		finish.execute();
		// competitior in toStart , ToFinish, and Finished 
		off.execute();
		on.execute();
		assertEquals("Power not removing waiting competitors", 0, ChronoTimer.toStart.size());
		assertEquals("Power not removing competitors currently racing", 0, ChronoTimer.toFinish.size());
		assertEquals("Power not removing completed racers", 0, ChronoTimer.completedRacers.size());

	}
	public void testPowerOnEvent(){
		off.execute();
		on.execute();
		reset.execute();
		changeEvent.execute("GRP");
		off.execute();
		on.execute();
		assertTrue("Power not resetting Event", ChronoTimer.typeEvent instanceof IndEvent);
	}
	public void testChangeEvent(){
		off.execute();
		on.execute();
		reset.execute();
		changeEvent.execute("GRP");
		assertTrue("Event type is not changing", ChronoTimer.typeEvent instanceof GrpEvent);
	}
	public void testParaGroupCompetitors(){
		reset.execute();
		changeEvent.execute("PARAGRP");
		add.execute(1);
		add.execute(2);
		add.execute(3);
		add.execute(4);
		add.execute(5);
		add.execute(6);
		add.execute(7);
		add.execute(8);
		add.execute(9);
		assertTrue(ChronoTimer.toStart.size() == 8);
	}
	public void testParaIndCompetitors(){
		reset.execute();
		changeEvent.execute("PARAIND");
		add.execute(1);
		add.execute(2);
		add.execute(3);
		assertTrue(ChronoTimer.toStart.size() == 2);
	}
	//cancel should undo the last start
	public void testGrpCancel(){
		reset.execute();
		changeEvent.execute("GRP");
		Competitor c1 = new Competitor(1);
		ChronoTimer.toStart.add(c1);
		add.execute(3);
		start.execute();
		cancel.execute();
		
		assertTrue(ChronoTimer.toFinish.contains(c1) && Double.isNaN(c1.getFinishTime()));

	}
	//all racers should be put back in to finish
	public void testParaGrpCancel(){
		reset.execute();
		changeEvent.execute("PARAGRP");
		Competitor c1 = new Competitor(1);
		Competitor c2 = new Competitor(2);
		Competitor c3 = new Competitor(3);
		start.execute();
		cancel.execute();
		assertTrue(ChronoTimer.toStart.contains(c1)&& Double.isNaN(c1.getFinishTime()));
		assertTrue(ChronoTimer.toStart.contains(c2)&& Double.isNaN(c2.getFinishTime()));
		assertTrue(ChronoTimer.toStart.contains(c3)&& Double.isNaN(c3.getFinishTime()));
		
	}
	public void testStart(){
		reset.execute();
		reset.execute();
		try{
			start.execute();
			assertFalse(true);
		}
		catch(RuntimeException ex){
			assertTrue(ex instanceof IllegalStateException );
		}
	}
	public void testFinish(){
		reset.execute();
		try{
			finish.execute();
			assertFalse(true);
		}
		catch(RuntimeException ex){
			assertTrue(ex instanceof IllegalStateException );
		}
	}
	public void testSwap(){
		reset.execute();
		Competitor c1 = new Competitor(1);
		Competitor c2 = new Competitor(2);
		ChronoTimer.toStart.add(c1);
		ChronoTimer.toStart.add(c2);
		start.execute();
		start.execute();
		swap.execute();
		assertTrue(ChronoTimer.toStart.get(0).equals(c2) && ChronoTimer.toStart.get(1).equals(c1));
		
}
	


}
