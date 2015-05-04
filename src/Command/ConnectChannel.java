package Command;

import ChronoTimer.ChronoTimer;
import ChronoTimer.Sensor;


/**
* Connects a sensor to a channel
* @param type the type of sensor to be connected
* @param index the channel 
*/
public class ConnectChannel implements Command {

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
		String [] newS = s.split(" ");
		int index = Integer.parseInt(newS[1]);
		String type = newS[0];
		if(index> ChronoTimer.channels.length||index<1) throw new IllegalArgumentException(ChronoTimer.channels.length + " Channels");
		ChronoTimer.channels[index-1].connectSensor(new Sensor(type));
	}

}
