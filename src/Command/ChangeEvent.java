package Command;

import ChronoTimer.ChronoTimer;
import Event.*;

/** Handles types of events */
public class ChangeEvent implements Command{

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
		switch (s){
		case "IND": ChronoTimer.typeEvent = new IndEvent (); break ;
		case "PARIND": ChronoTimer.typeEvent = new ParIndEvent (); break ;
		case "PARGRP": ChronoTimer.typeEvent = new ParGrpEvent (); break ;
		case "GRP": ChronoTimer.typeEvent = new GrpEvent() ; break ;
	}
	}

}
