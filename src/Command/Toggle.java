package Command;

import ChronoTimer.ChronoTimer;


/**
 * Switch the state of a channel from armed/disarmed to disarmed/armed
 * @param index represents the channel
 */
public class Toggle implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void execute(int index) {
		// TODO Auto-generated method stub
		if (ChronoTimer.power){
			if (ChronoTimer.channels[index - 1] != null)
				ChronoTimer.channels[index - 1].toggle();
		}

	}

	@Override
	public void execute(String s) {
		// TODO Auto-generated method stub
		
	}

}
